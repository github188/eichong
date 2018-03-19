package com.sgcc.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgcc.constant.CommonConsts;
import org.apache.commons.codec.digest.HmacUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * 中国充电联盟测试例子
 */
public class AppTool {
    private static final Logger log = LoggerFactory.getLogger(AppTool.class);

    //上一次自增序列
    public static long lastTimeStamp = 0;
    public static int lastSeq = 1;
    /**
     * API版本号
     */
    private static String API_VERSION = "v0.0.1";

    /**
     * 8.2　获取接口同步数据认证
     * 获取token，并调用其它接口
     */

    public void testToken() throws IOException {
        System.out.println("testToken");
        //构造参数信息
        HashMap<String, String> map = new HashMap<>();
        map.put("OperatorID", CommonConsts.SGCC_OPERATOR_ID);
        map.put("OperatorSecret", CommonConsts.SGCC_OPERATOR_SECRET);

        System.out.println("testToken");
        String url = CommonConsts.SGCC_BASE_URL + CommonConsts.SGCC_QUERY_TOKEN;
        String Data = JSON.toJSONString(map);
        //发送请求
        JSONObject jsonObject = sendData(url, null, Data);
        if (!jsonObject.containsKey("Data")) {
            System.out.println("请求失败");
        }
        jsonObject = jsonObject.getJSONObject("Data");
        if (!jsonObject.containsKey("SuccStat")
                || jsonObject.getInteger("SuccStat") != 0
                || !jsonObject.containsKey("AccessToken")) {
            System.out.println("请求失败");
        }
        String accessToken = jsonObject.getString("AccessToken");
        System.out.println("accessToken=" + accessToken);
        //测试其它接口
        testIsTokenRight(accessToken);
        testSyncStationInfoController(accessToken);
        testSyncEquipmentInfoController(accessToken);
        testSyncConnectorInfoController(accessToken);
        testSyncStationStatsInfoController(accessToken);
        testSyncEquipmentStatsInfoController(accessToken);
    }


    /**
     * 测试token和加密解密是否正确
     */
    public void testIsTokenRight(String accessToken) {
        System.out.println("testIsTokenRight");
        String url = CommonConsts.SGCC_BASE_URL + "is_token_right";
        String Data = "[{\"name\":\"te测试st\"}]";
        //发送请求
        sendData(url, accessToken, Data);
    }


    /**
     * 8.3　同步充电站信息
     */
    public void testSyncStationInfoController(String accessToken) {
        System.out.println("testSyncStationInfoController");

        String operatorInfoUrl = CommonConsts.SGCC_BASE_URL + "sync_station_info";

        String Data = "[{\"StationID\":\"YFYSTATIONID130\",\"OperatorID\":\"321895837\",\"EquipmentOwnerID\":\"123456789\",\"StationUrl\":\"012345678\",\"StationName\":\"站点YFY101\",\"CountryCode\":\"CN\",\"AreaCode\":\"230623\",\"Address\":\"测试地址1\",\"StationTel\":\"站点电话\",\"ServiceTel\":\"服务电话\",\"StationType\":1,\"StationStatus\":50,\"ParkNums\":3,\"StationLng\":116.293058,\"StationLat\":40.40672,\"SiteGuide\":\"左转直行50米，右转就到了\",\"Construction\":1,\"Pictures\":\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png,http://www.a.com/b.png\",\"MatchCars\":\"大巴\",\"ParkInfo\":\"地下一层，4\",\"BusineHours\":\"08:00-20:00\",\"ElectricityFee\":\"0.8/度\",\"ServiceFee\":\"10元\",\"ParkFee\":\"10元/小时\",\"Payment\":\"刷卡\",\"SupportOrder\":0,\"Remark\":\"备注12\",\"OpenForBusinessDate\":\"2014-01-01\",\"StationUrl\":\"http://www.baidu.com\"}]";
        //发送请求
        sendData(operatorInfoUrl, accessToken, Data);


    }

    /**
     * 8.4　同步充电设备信息
     */
    public void testSyncEquipmentInfoController(String accessToken) {
        System.out.println("testSyncEquipmentInfoController");
        String operatorInfoUrl = CommonConsts.SGCC_BASE_URL + "sync_equipment_info";
        String Data = "[{\"StationID\":\"YFYSTATIONID130\",\"EquipmentID\":\"10005\",\"ManufacturerID\":\"012345678\",\"EquipmentModel\":\"YFY101\",\"EquipmentName\":\"充电桩YFY101\",\"ProductionDate\":\"2016-11-01\",\"EquipmentType\":1,\"EquipmentLng\":116.293058,\"EquipmentLat\":40.40672,\"Power\":1000.123,\"ServiceTypeId\":1,\"OpenForBusinessDate\":\"2015-12-01\",\"EquipmentStatus\":\"50\"}]";
        //发送请求
        sendData(operatorInfoUrl, accessToken, Data);


    }

    /**
     * 8.5　同步充电接口信息
     */
    public void testSyncConnectorInfoController(String accessToken) {
        System.out.println("testSyncConnectorInfoController");
        String operatorInfoUrl = CommonConsts.SGCC_BASE_URL + "sync_connector_info";
        String Data = "[{\"EquipmentID\":\"10005\",\"ConnectorID\":\"C002\",\"ConnectorName\":\"充电接口YFY001\",\"ConnectorType\":2,\"VoltageUpperLimits\":380,\"VoltageLowerLimits\":110,\"Current\":10,\"Power\":1000.123,\"ParkNo\":\"B123\",\"NationalStandard\":2}]";
        //发送请求
        sendData(operatorInfoUrl, accessToken, Data);

    }


    /**
     * 8.6　同步充电站统计信息
     */
    public void testSyncStationStatsInfoController(String accessToken) {
        System.out.println("testSyncStationStatsInfoController");
        String operatorInfoUrl = CommonConsts.SGCC_BASE_URL + "sync_station_stats_info";
        String Data = "[{\"StationID\":\"YFYSTATIONID130\",\"StationChargeTime\":12.34,\"StatsDate\":\"2017-02-21\",\"StationElectricity\":12.34}]";
        //发送请求
        sendData(operatorInfoUrl, accessToken, Data);
    }

    /**
     * 8.7　同步充电设备统计信息
     */
    public void testSyncEquipmentStatsInfoController(String accessToken) {
        System.out.println("testSyncEquipmentStatsInfoController");
        String operatorInfoUrl = CommonConsts.SGCC_BASE_URL + "sync_equipment_stats_info";
        String Data = "[{\"EquipmentID\":\"10005\",\"EquipmentElectricity\":1.234,\"EquipmentChargeTime\":12.34,\"StatsDate\":\"2017-02-21\"}]";
        //发送请求
        sendData(operatorInfoUrl, accessToken, Data);

    }


    /**
     * 获取当前时间，格式为yyyyMMddHHmmss
     */
    public static long getNowTimeStamp() {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String ctime = formatter.format(new Date());
        return Long.parseLong(ctime);
    }

    /**
     * 4位自增序列，同一秒内自增，如0001
     */
    public static int getSeq(long thisTimeStamp) {
        //第一次时
        if (lastTimeStamp == thisTimeStamp) {
            lastSeq++;
            return lastSeq;
        }
        lastSeq = 1;
        lastTimeStamp = thisTimeStamp;
        return lastSeq;
    }

    /**
     * 生成签名
     */
    public static HashMap<String, String> makeSig(String Data) {
        Long TimeStamp = getNowTimeStamp();
        int Seq = getSeq(TimeStamp);

        HashMap<String, String> map = new LinkedHashMap();
        map.put("TimeStamp", TimeStamp + "");
        String SeqStr = String.format("%04d", Seq);
        map.put("Seq", SeqStr);
        map.put("OperatorID", CommonConsts.SGCC_OPERATOR_ID);
        map.put("Data", Data);

        String sig = HmacUtils.hmacMd5Hex(CommonConsts.SGCC_SIG_SECRET, CommonConsts.SGCC_OPERATOR_ID + Data + TimeStamp + SeqStr).toUpperCase();
        map.put("Sig", sig);
        return map;
    }


    /**
     * 生成签名
     */
    public static boolean verifySig(JSONObject result) {
        String sig = HmacUtils.hmacMd5Hex(CommonConsts.SGCC_SIG_SECRET, result.getString("Ret") + result.getString("Msg") + result.getString("Data")).toUpperCase();
        return sig.equals(result.getString("Sig"));
    }

    /**
     * 发送请求到服务端
     */
    public JSONObject sendData(String url, String Data) {
        System.out.println("Data=" + Data);
        //加密数据
        try {
            Data = AESUtils.encrypt(Data, CommonConsts.SGCC_DATA_SECRET, CommonConsts.SGCC_DATA_SECRET_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //生成签名
        HashMap<String, String> map = makeSig(Data);
        //把token放到头部
        HashMap<String, String> headers = new HashMap();
        //发送请求
        String answerStr = HttpUtilsOfSGCC.doPost(url, headers, map);

        System.out.println("服务端返回原始数据：" + answerStr);
        JSONObject result = JSON.parseObject(answerStr);
        if (result == null && !result.containsKey("Ret")) {
            System.out.println("请求失败,返回数据不是JSON");
        }
        if (!verifySig(result)) {
            System.out.println("返回签名验证失败");
        }
        String DataStr = null;

        try {
            DataStr = AESUtils.decrypt(result.getString("Data"), CommonConsts.SGCC_DATA_SECRET, CommonConsts.SGCC_DATA_SECRET_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (DataStr == null) {
            System.out.println("解密失败");
        }
        result.put("Data", JSON.parseObject(DataStr));
        System.out.println("服务端返回解密数据：" + JSON.toJSONString(result));
        System.out.println("------------------------------------------------------------------------------------------------\n\n");
        if (0 != (int) result.get("Ret")) {
            System.out.println("提交数据失败,返回值不是0");
        }
        return result;
    }

    /**
     * 发送请求到服务端
     */
    public JSONObject sendData(String url, String accessToken, String Data) {
        System.out.println("Data=" + Data);
        //加密数据
        try {
            Data = AESUtils.encrypt(Data, CommonConsts.SGCC_DATA_SECRET, CommonConsts.SGCC_DATA_SECRET_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //生成签名
        HashMap<String, String> map = makeSig(Data);
        //把token放到头部
        HashMap<String, String> headers = new HashMap();
        headers.put("Authorization", "Bearer " + accessToken);
        //发送请求
        String answerStr = HttpUtilsOfSGCC.doPost(url, headers, map);

        System.out.println("服务端返回原始数据：" + answerStr);
        JSONObject result = JSON.parseObject(answerStr);
        if (result == null && !result.containsKey("Ret")) {
            System.out.println("请求失败,返回数据不是JSON");
        }
        if (!verifySig(result)) {
            System.out.println("返回签名验证失败");
        }
        String DataStr = null;

        try {
            DataStr = AESUtils.decrypt(result.getString("Data"), CommonConsts.SGCC_DATA_SECRET, CommonConsts.SGCC_DATA_SECRET_IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (DataStr == null) {
            System.out.println("解密失败");
        }
        result.put("Data", JSON.parseObject(DataStr));
        System.out.println("服务端返回解密数据：" + JSON.toJSONString(result));
        System.out.println("------------------------------------------------------------------------------------------------\n\n");
        if (0 != (int) result.get("Ret")) {
            System.out.println("提交数据失败,返回值不是0");
        }
        return result;
    }

    public static String ensureToken() {
        HashMap<String, String> map = new HashMap<>();
        map.put("OperatorID", CommonConsts.SGCC_OPERATOR_ID);
        map.put("OperatorSecret", CommonConsts.SGCC_OPERATOR_SECRET);
        String url = CommonConsts.SGCC_BASE_URL + CommonConsts.SGCC_QUERY_TOKEN;
        String Data = JSON.toJSONString(map);
        //发送请求
        JSONObject result = HttpUtilsOfSGCC.sendData(url, null, Data);
        if (null == result || !result.containsKey("Data")) {
            log.info("ensureToken is fail:", result);
        }
        result = result.getJSONObject("Data");
        if (!result.containsKey("SuccStat") || result.getInteger("SuccStat") != 0 || !result.containsKey("AccessToken")) {
            log.info("ensureToken is fail:", result);
        }
        String accessToken = result.getString("AccessToken");
        return accessToken;
    }

    public void tokenIsRight(String accessToken) {
        System.out.println("testIsTokenRight");
        String url = CommonConsts.SGCC_BASE_URL + "is_token_right";
        String Data = "[{\"name\":\"te测试st\"}]";
        //发送请求
        sendData(url, accessToken, Data);
    }
}

