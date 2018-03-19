package com.wanma.ims.service.impl;

import com.google.common.base.Strings;
import com.wanma.ims.common.domain.*;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.mapper.JPushRecordMapper;
import com.wanma.ims.mapper.PurchaseHistoryMapper;
import com.wanma.ims.mapper.UserMapper;
import com.wanma.ims.service.AppJpushService;
import com.wanma.ims.service.JPushRecordService;
import com.wanma.ims.util.DateUtil;
import com.wanma.ims.util.JPushUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("jPushRecordService")
public class JPushRecordServiceImpl implements JPushRecordService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JPushRecordMapper jPushRecordMapper;
    @Autowired
    private AppJpushService appJpushService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PurchaseHistoryMapper purchaseHistoryMapper;

    /**
     * <p>Description: 通过订单推送记录</p>
     *
     * @author bingo
     * @date 2017-12-15
     */
    @Override
    public void doJPushRecordForBatch(OrderDO orderDO) {
        try {
            UserDO qryUser = new UserDO();
            qryUser.setUserId(orderDO.getUserId());
            List<UserDO> userList = userMapper.selectUserList(qryUser);
            if (userList == null || userList.size() == 0) {
                log.error(this.getClass() + ".doJPushRecordForBatch() info:订单编号" + orderDO.getOrderCode() + "没有用户数据！");
            } else {
                UserDO userDO = userList.get(0);
                StringBuilder contents = new StringBuilder();
                String title = "";
                if (userDO.getUserName() != null && !"".equals(userDO.getUserName())) {
                    contents.append("【").append(userDO.getUserName()).append("】");
                }
                if (orderDO.getOrderStatus() == 1) {
                    PurchaseHistoryDO qryPurchaseHistoryDO = new PurchaseHistoryDO();
                    qryPurchaseHistoryDO.setPuHiTransactionNumber(orderDO.getTransactionNumber());
                    qryPurchaseHistoryDO.setPuHiUserId(orderDO.getUserId());
                    List<PurchaseHistoryDO> list = purchaseHistoryMapper.getPurchaseHistoryForBatch(qryPurchaseHistoryDO);
                    if (list == null || list.size() == 0) {
                        log.error(this.getClass() + ".doJPushRecordForBatch() info:订单编号" + orderDO.getOrderCode() + "没有消费记录数据！");
                    } else {
                        PurchaseHistoryDO purchaseHistoryDO = list.get(0);
                        title = "您有一笔订单开始充电";
                        contents.append("您好，您的订单开始充电了。充电冻结金额【").append(purchaseHistoryDO.getPuHiMonetary()).append("】元。");
                    }
                } else {
                    title = "您有一笔充电订单已结算";
                    contents.append("您好，您的订单已经完成结算。本次充电【").
                            append(DateUtil.subtract(DateUtil.parse(orderDO.getEndChargeTime()), DateUtil.parse(orderDO.getStartChargeTime()), Calendar.MINUTE)).
                            append("】分钟，电量【").append(orderDO.getTotalElectricCharge()).append("】度，费用【").
                            append(orderDO.getChOrMoeny()).append("】元，优惠【").append(orderDO.getTotalfavMoney()).append("】元，实际扣费【").
                            append(new BigDecimal(orderDO.getChOrMoeny()).subtract(new BigDecimal(orderDO.getTotalfavMoney())).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString()).append("】元。");
                }

                BaseResultDTO result;
                JpushDO jpush = appJpushService.getByuserInfo(orderDO.getUserId());
                if (null != jpush && !Strings.isNullOrEmpty(jpush.getJpushRegistrationid())) {
                    JPushUtil.jpushNotice(title, contents.toString(), jpush.getJpushRegistrationid(), "12");
                    Map<String, String> msgMap = JPushUtil.getBasicMsgMap(title, contents.toString());
                    msgMap.put("orderid", orderDO.getOrderId().toString());
                    msgMap.put("userid", orderDO.getUserId().toString());
                    result = JPushUtil.jpushCustom(msgMap, jpush.getJpushRegistrationid(), "12", false);
                    if (result.isFailed()) {
                        log.error(this.getClass() + ".doJPushRecordForBatch() info:订单编号" + orderDO.getOrderCode() + "已经推送过消息出错！" + result.getResultCode());
                    }
                } else {
                    log.warn(this.getClass() + ".doJPushRecordForBatch() info:订单编号" + orderDO.getOrderCode() + "不是APP用户数据！");
                }
            }

            JpushRecordDO jpushRecordDO = new JpushRecordDO();
            jpushRecordDO.setChargingOrderId(orderDO.getOrderId().toString());
            jpushRecordDO.setChargingOrderStatus(orderDO.getOrderStatus() == 1 ? 1 : 2);
            jpushRecordDO.setCreator(orderDO.getCreator());
            jpushRecordDO.setModifier(orderDO.getCreator());
            jpushRecordDO.setGmtCreate(new Date());
            jpushRecordDO.setGmtModified(new Date());
            jPushRecordMapper.addJPushRecord(jpushRecordDO);
        } catch (Exception e) {
            log.error(this.getClass() + ".doJPushRecordForBatch() info:订单编号" + orderDO.getOrderCode() + "推送消息失败！" + e.toString(), e);
        }
    }

    /**
     * <p>Description: 获取最大的推送记录id</p>
     *
     * @param jpushRecordDO 推送记录
     * @author bingo
     * @date 2018-1-4
     */
    @Override
    public String getMaxChargingOrderId(JpushRecordDO jpushRecordDO) {
        return jPushRecordMapper.getMaxChargingOrderId(jpushRecordDO);
    }
}