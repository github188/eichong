package com.wanma.ims.util;

import com.wanma.ims.controller.vo.DataCenterOrderVO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by xyc on 2018/3/20.
 * 构建订单树
 */
public class DataCenterHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataCenterHelper.class);
    private static final DataCenterHelper helper;

    private Map<Long, Map<Long, List<DataCenterOrderVO>>> dataCenterOrderMap = new HashMap<>();

    static {
        helper = new DataCenterHelper();
        helper.initOrderMap();
    }

    private DataCenterHelper() {
    }

    public static DataCenterHelper getInstance() {
        return helper;
    }

    public List<DataCenterOrderVO> searchOrder(Set<Long> provinceCodes, Set<Long> cityCodes) {
        List<DataCenterOrderVO> resultList = new ArrayList<>();

        Map<Long, Map<Long, List<DataCenterOrderVO>>> nowMap = dataCenterOrderMap;
        List<Map<Long, List<DataCenterOrderVO>>> provinceOrderList = new ArrayList<>();

        for (Long provinceCode : provinceCodes) {
            Map<Long, List<DataCenterOrderVO>> cityOrderMap = nowMap.get(provinceCode);
            if (cityOrderMap != null) {
                provinceOrderList.add(cityOrderMap);
            }
        }

        if (CollectionUtils.isEmpty(provinceOrderList)) {
            return resultList;
        }

        if (CollectionUtils.isEmpty(cityCodes)) {
            for (Map<Long, List<DataCenterOrderVO>> cityOrderMap : provinceOrderList) {
                for (Map.Entry<Long, List<DataCenterOrderVO>> entry : cityOrderMap.entrySet()) {
                    resultList.addAll(entry.getValue());
                }
            }
            return resultList;
        }

        for (Map<Long, List<DataCenterOrderVO>> cityOrderMap : provinceOrderList) {
            for (Long cityCode : cityCodes) {
                resultList.addAll(cityOrderMap.get(cityCode));
            }
        }

        return resultList;
    }

    /**
     * 初始化订单树
     */
    public Map initOrderMap() {
        List<DataCenterOrderVO> orders;
        orders = getDataCenterOrderToDb();
        if (orders != null) {
            addDataCenterOrderToHashMap(orders);
        } else {
            throw new RuntimeException("DataCenterOrder HashMap Create failed");
        }
        return dataCenterOrderMap;
    }

    private void addDataCenterOrderToHashMap(List<DataCenterOrderVO> orders) {
        dataCenterOrderMap = new HashMap<>(orders.size());
        for (DataCenterOrderVO order : orders) {
            Map<Long, List<DataCenterOrderVO>> cityOrderMap = dataCenterOrderMap.get(order.getProvinceCode());

            if (cityOrderMap == null) {
                cityOrderMap = new HashMap<>();
                dataCenterOrderMap.put(order.getProvinceCode(), cityOrderMap);
            }

            List<DataCenterOrderVO> cityOrderList = cityOrderMap.get(order.getCityCode());
            if (CollectionUtils.isEmpty(cityOrderList)) {
                cityOrderList = new ArrayList<>();
                cityOrderMap.put(order.getCityCode(), cityOrderList);
            }

            cityOrderList.add(order);
        }
    }

    private List<DataCenterOrderVO> getDataCenterOrderToDb() {
        List<DataCenterOrderVO> orders;
        Connection con;
        Statement stmt;
        try {
            con = JdbcUtil.getConnection();
            stmt = con.createStatement();
            ResultSet rs = JdbcUtil.doQuerySql(con, stmt, getOrderSql());
            orders = getOrderToResultSet(rs);
            JdbcUtil.closeConnection(stmt, null, con);
        } catch (Exception e) {
            LOGGER.warn("DataCenterOrder HashMap Create failed", e);
            return null;
        }

        return orders;
    }

    private String getOrderSql() {
        String sql = "select trade.chOr_OrgNo,trade.begin_charge_time,trade.end_charge_time,trade.chOr_QuantityElectricity,ele.elPi_OwnProvinceCode,ele.elPi_OwnCityCode,ele.elPi_ElectricPileCode\n" +
                "from tbl_chargingorder trade,tbl_electricpile ele where trade.chOr_PileNumber = ele.elPi_ElectricPileCode and trade.end_charge_time < '";

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        String dayStartTime = DateUtil.format(cal.getTime(), DateUtil.TYPE_COM_YMDHMS);
        return sql + dayStartTime + "' and trade.begin_charge_time < '" + dayStartTime + "' limit 10";
    }

    private List<DataCenterOrderVO> getOrderToResultSet(ResultSet rs) {
        List<DataCenterOrderVO> orders = new ArrayList<>();
        try {
            while (rs.next()) {
                DataCenterOrderVO order = new DataCenterOrderVO();
                order.setCpyNum(rs.getLong(1));
                order.setChargeTime(rs.getTimestamp(3).getTime() - rs.getTimestamp(2).getTime());
                order.setChargeNum(rs.getString(4));
                order.setProvinceCode(rs.getLong(5));
                order.setCityCode(rs.getLong(6));
                order.setCode(rs.getString(7));
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error("getOrderToResultSet is error", e);
            return null;
        }
        return orders;
    }
}
