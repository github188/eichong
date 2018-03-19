package com.wanma.ims.service.parkinglock.wiparking;


import com.wanma.ims.service.parkinglock.constant.PlatformConfig;

/**
 * Created by xyc on 2018/1/29.
 * 慧泊金请求参数实体类
 */
public class WiParkingRequest {

    /**
     * 合作者id
     */
    private Integer sdkId;

    /**
     * Token值 慧铂金提供
     */
    private String token;

    /**
     * 车位id即地锁code
     */
    private String spaceId;

    /**
     * 操作命令 ha:用户降锁,hb:用户起锁,hg:管理员控制车锁鸣叫,hh:管理员休眠锁,hi:管理员唤醒锁,hj:控制锁停止报警,hk:管理员查询状态
     */
    private String lockCode;

    /**
     * 秒(车锁鸣叫时传)
     */
    private String second;

    public static WiParkingRequest valueOf(String parkingLockCode, String lockOperating) {
        WiParkingRequest request = new WiParkingRequest();
        request.setSdkId(PlatformConfig.WIPARKING_PLATFORM_SDK_ID);
        request.setToken(PlatformConfig.WIPARKING_PLATFORM_TOKEN);
        request.setSpaceId(parkingLockCode);
        request.setLockCode(lockOperating);
        return request;
    }

    public Integer getSdkId() {
        return sdkId;
    }

    public void setSdkId(Integer sdkId) {
        this.sdkId = sdkId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public String getLockCode() {
        return lockCode;
    }

    public void setLockCode(String lockCode) {
        this.lockCode = lockCode;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
