package com.wanma.ims.service.parkinglock.impl;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wanma.ims.common.domain.*;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.BatchResultDTO;
import com.wanma.ims.constants.DownFileConstants;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.ParkingLockMapper;
import com.wanma.ims.service.InitialService;
import com.wanma.ims.service.parkinglock.ParkingLockService;
import com.wanma.ims.service.parkinglock.PlatformService;
import com.wanma.ims.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by xyc on 2018/1/26.
 * 地锁逻辑处理实现类
 */
@Service
public class ParkingLockServiceImpl implements ParkingLockService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    //降锁操作
    private static final int LOWER_PARKING_LOCK = 0;
    //升锁操作
    private static final int RISE_PARKING_LOCK = 1;

    @Autowired
    private ParkingLockMapper parkingLockMapper;
    @Autowired
    private InitialService initialService;
    @Autowired
    private PlatformService platformService;

    @Override
    public List<ParkingLockDO> getParkingLockList(ParkingLockDO searchModel, UserDO loginUser) {
        List<ParkingLockDO> resultList = parkingLockMapper.selectList(searchModel);
        Set<String> provinceCodeSet = new HashSet<>();
        Set<String> cityCodeSet = new HashSet<>();
        Set<String> areaCodeSet = new HashSet<>();

        for (ParkingLockDO parkingLock : resultList) {
            fillParkingLock(parkingLock);

            provinceCodeSet.add(parkingLock.getProvinceCode());
            cityCodeSet.add(parkingLock.getCityCode());
            areaCodeSet.add(parkingLock.getAreaCode());
        }

        Map<String, ProvinceDO> provinceMap = initialService.getProvinceMapByIds(new ArrayList<>(provinceCodeSet));
        Map<String, CityDO> cityMap = initialService.getCityMapByIds(new ArrayList<>(cityCodeSet));
        Map<String, AreaDO> areaMap = initialService.getAreaMapByIds(new ArrayList<>(areaCodeSet));

        for (ParkingLockDO parkingLock : resultList) {
            String address = "";
            ProvinceDO province = provinceMap.get(parkingLock.getProvinceCode());
            if (province != null) {
                address = address + province.getProvinceName();
            }

            CityDO city = cityMap.get(parkingLock.getCityCode());
            if (city != null) {
                address = address + city.getCityName();
            }

            AreaDO area = areaMap.get(parkingLock.getAreaCode());
            if (area != null) {
                address = address + area.getAreaName();
            }

            parkingLock.setAddress(address + parkingLock.getAddress());
        }

        return resultList;
    }

    private void fillParkingLock(ParkingLockDO parkingLock) {
        Integer status = parkingLock.getStatus();
        Long platform = parkingLock.getParkingLockPlatform();

        if (Objects.equals(status, WanmaConstants.PARKING_LOCK_NORMAL)) {
            parkingLock.setChStatus("正常");
        } else if (Objects.equals(status, WanmaConstants.PARKING_LOCK_USING)) {
            parkingLock.setChStatus("正在使用");
        } else if (Objects.equals(status, WanmaConstants.PARKING_LOCK_ERROR)) {
            parkingLock.setChStatus("故障");
        }

        if (Objects.equals(platform, WanmaConstants.PARKING_LOCK_PLATFORM_EVCAT)) {
            parkingLock.setPlatformName("电喵");
        } else if (Objects.equals(platform, WanmaConstants.PARKING_LOCK_PLATFORM_WIPARKING)) {
            parkingLock.setPlatformName("慧泊金");
        }
    }

    @Override
    public long countParkingLock(ParkingLockDO searchModel, UserDO loginUser) {
        return parkingLockMapper.count(searchModel);
    }

    @Override
    public ParkingLockDO getParkingLockDetail(Long parkingLockId) {
        return parkingLockMapper.selectLockDetailById(parkingLockId);
    }

    @Override
    public BaseResultDTO addParkingLock(ParkingLockDO parkingLock, UserDO loginUser) {
        BaseResultDTO result = checkParkingLock(parkingLock, loginUser);
        if (result != null) {
            return result;
        }

        fillParkingLockInfo(parkingLock, loginUser);

        if (parkingLockMapper.insertParkingLock(parkingLock) < 1) {
            LOGGER.warn(this.getClass() + "-addParkingLock is failed, insertParkingLock is error|newParkingLock={}|loginUser={}", SerializationUtil.gson2String(parkingLock), SerializationUtil.gson2String(loginUser));
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新增地锁失败，请刷新页面后重试！");
        }

        return new BaseResultDTO();
    }

    private BaseResultDTO checkParkingLock(ParkingLockDO parkingLock, UserDO loginUser) {
        if (parkingLock == null) {
            LOGGER.warn(this.getClass() + "-addParkingLock is failed, parkingLock is null|loginUser={}", SerializationUtil.gson2String(loginUser));
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新增地锁不能为空，请刷新页面后重试！");
        }

        if (null == parkingLock.getParkingLockPlatform()) {
            LOGGER.warn(this.getClass() + "-addParkingLock is failed, parkingLockPlatform is null|loginUser={}", SerializationUtil.gson2String(loginUser));
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新增地锁时，地锁供应商不能为空，请刷新页面后重试！");
        }

        if (Strings.isNullOrEmpty(parkingLock.getPlatformLockKey())) {
            LOGGER.warn(this.getClass() + "-addParkingLock is failed, platformLockKey is null|loginUser={}", SerializationUtil.gson2String(loginUser));
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "新增地锁时，地锁对接key不能为空，请刷新页面后重试！");
        }

        ParkingLockDO oldLock = parkingLockMapper.selectByPlatformLockKey(parkingLock.getParkingLockPlatform(), parkingLock.getPlatformLockKey());
        if (oldLock != null) {
            LOGGER.warn(this.getClass() + "-addParkingLock is failed, oldLock is not null|loginUser={}|oldLock={}", SerializationUtil.gson2String(loginUser), SerializationUtil.gson2String(oldLock));
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "对接key为" + oldLock.getPlatformLockKey() + "的地锁已存在请勿重复增加，请刷新页面后重试！");
        }

        if (!platformService.getLockStatus(parkingLock)) {
            LOGGER.warn(this.getClass() + "-addParkingLock is failed, check lock to platform is error|loginUser={}|parkingLock={}", SerializationUtil.gson2String(loginUser), SerializationUtil.gson2String(parkingLock));
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "调用第三方地锁平台获取对接key为" + parkingLock.getPlatformLockKey() + "的地锁最新状态时失败，请刷新页面后重试！");
        }

        return null;
    }

    private void fillParkingLockInfo(ParkingLockDO parkingLock, UserDO loginUser) {
        parkingLock.setCode(PKUtil.generateParkingLockCode(parkingLock.getParkingLockPlatform(), parkingLock.getPlatformLockKey()));
        parkingLock.setCreatorId(loginUser.getUserId());
        parkingLock.setCreator(loginUser.getUserAccount());
        parkingLock.setModifier(loginUser.getUserAccount());
    }

    @Override
    public BaseResultDTO modifyParkingLock(ParkingLockDO parkingLock, UserDO loginUser) {
        ParkingLockDO oldParkingLock = parkingLockMapper.selectById(parkingLock.getId());
        if (oldParkingLock == null) {
            LOGGER.warn(this.getClass() + "-modifyParkingLock is failed, parkingLock is null|loginUser={}", SerializationUtil.gson2String(loginUser));
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "找不到要修改的地锁，请刷新页面后重试！");
        }

        if (parkingLockMapper.updateByIdSelective(parkingLock) < 1) {
            LOGGER.warn(this.getClass() + "-modifyParkingLock is failed, updateParkingLock is error|modifyParkingLock={}|loginUser={}", SerializationUtil.gson2String(parkingLock), SerializationUtil.gson2String(loginUser));
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "修改地锁失败，请刷新页面后重试！");
        }

        return new BaseResultDTO();
    }

    @Override
    public BaseResultDTO operatingParkingLock(Long parkingLockId, Integer operating, UserDO loginUser) {
        ParkingLockDO oldParkingLock = parkingLockMapper.selectById(parkingLockId);
        if (oldParkingLock == null) {
            LOGGER.warn(this.getClass() + "-modifyParkingLock is failed, parkingLock is null|loginUser={}", SerializationUtil.gson2String(loginUser));
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "找不到要修改的地锁，请刷新页面后重试！");
        }

        ParkingLockDO parkingLock = new ParkingLockDO();
        parkingLock.setId(parkingLockId);
        parkingLock.setParkingLockPlatform(oldParkingLock.getParkingLockPlatform());
        parkingLock.setPlatformLockKey(oldParkingLock.getPlatformLockKey());
        switch (operating) {
            case LOWER_PARKING_LOCK:
                if (!platformService.lowerLock(parkingLock)) {
                    return new BaseResultDTO(false, ResultCodeConstants.FAILED, "降锁失败，请选择正确的操作后重试！");
                }
                parkingLock.setStatus(WanmaConstants.PARKING_LOCK_USING);
                return modifyParkingLock(parkingLock, loginUser);
            case RISE_PARKING_LOCK:
                if (!platformService.riseLock(parkingLock)) {
                    return new BaseResultDTO(false, ResultCodeConstants.FAILED, "升锁失败，请选择正确的操作后重试！");
                }
                parkingLock.setStatus(WanmaConstants.PARKING_LOCK_NORMAL);
                return modifyParkingLock(parkingLock, loginUser);
            default:
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "地锁操作不正确，请选择正确的操作后重试！");
        }
    }

    @Override
    public BaseResultDTO importParkingLock(MultipartFile multipartFile, UserDO loginUser) throws Exception {
        if (multipartFile == null || multipartFile.isEmpty()) {
            LOGGER.warn(this.getClass() + "-importElectricPile is failed, multipartFile is null|loginUser={}", SerializationUtil.gson2String(loginUser));
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "请导入正确的地锁excel文件！");
        }

        try {
            BatchResultDTO<List<String>> result = ExcelImportUtil.importListFromExcel(multipartFile);
            if (result.isFailed()) {
                return result;
            }

            return processingData(result.getModule(), loginUser);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-importParkingLock is error|fileName={}|loginUser={}", multipartFile.getOriginalFilename(), SerializationUtil.gson2String(loginUser), e);
            String errorMsg = "导入地锁失败";
            if (e.getMessage().contains(errorMsg)) {
                throw e;
            } else {
                throw new Exception("系统错误，导入地锁失败！");
            }
        }
    }

    private BaseResultDTO processingData(List<List<String>> data, UserDO loginUser) throws Exception {
        BaseResultDTO result = new BaseResultDTO();
        Map<String, ProvinceDO> provinceMap = Maps.uniqueIndex(initialService.getProvinceListByIds(null), new Function<ProvinceDO, String>() {
            @Override
            public String apply(ProvinceDO input) {
                return input.getProvinceName();
            }
        });
        Map<String, CityDO> cityMap = Maps.uniqueIndex(initialService.getCityListByIds(null, null), new Function<CityDO, String>() {
            @Override
            public String apply(CityDO input) {
                return input.getCityName();
            }
        });
        List<AreaDO> areaList = initialService.getAreaListByIds(null, null);
        Map<String, Map<String, AreaDO>> areaMap = new HashMap<>(1000);
        for (AreaDO area : areaList) {
            Map<String, AreaDO> cityAreaMap = areaMap.get(area.getCityId());
            if (cityAreaMap == null) {
                cityAreaMap = new HashMap<>(1000);
                areaMap.put(area.getCityId(), cityAreaMap);
            }
            cityAreaMap.put(area.getAreaName(), area);
        }
        Map<String, Long> platformMap = Maps.newHashMap();
        platformMap.put("电喵", 0L);
        platformMap.put("慧泊金", 1L);

        data.remove(0);
        data.remove(0);
        //行数
        int lineNum = 3;
        List<ParkingLockDO> parkingLockList = new ArrayList<>();

        for (List<String> line : data) {
            //存入数据库
            if (CollectionUtils.isEmpty(line)) {
                continue;
            }

            parkingLockList.add(processingLine(lineNum, line, provinceMap, cityMap, areaMap, platformMap));
            lineNum++;
        }

        for (ParkingLockDO parkingLock : parkingLockList) {
            result = addParkingLock(parkingLock, loginUser);
            if (result.isFailed()) {
                return result;
            }
        }

        return result;
    }

    private ParkingLockDO processingLine(int lineNum, List<String> line, Map<String, ProvinceDO> provinceMap, Map<String, CityDO> cityMap, Map<String, Map<String, AreaDO>> areaMap, Map<String, Long> platformMap) throws Exception {
        ParkingLockDO parkingLock = new ParkingLockDO();

        parkingLock.setPlatformLockKey((String) ExcelImportUtil.getNotNullData(lineNum, line.get(0), 0, this.getClass().getName(), "importParkingLock", "地锁"));

        parkingLock.setParkingLockPlatform((Long) ExcelImportUtil.getNotNullData(lineNum, platformMap.get(line.get(1)), 1, this.getClass().getName(), "importParkingLock", "地锁"));

        ProvinceDO province = (ProvinceDO) ExcelImportUtil.getNotNullData(lineNum, provinceMap.get(line.get(2)), 2, this.getClass().getName(), "importParkingLock", "地锁");
        parkingLock.setProvinceCode(province.getProvinceId());

        CityDO city = (CityDO) ExcelImportUtil.getNotNullData(lineNum, cityMap.get(line.get(3)), 3, this.getClass().getName(), "importParkingLock", "地锁");
        parkingLock.setCityCode(city.getCityId());

        AreaDO area = (AreaDO) ExcelImportUtil.getNotNullData(lineNum, areaMap.get(parkingLock.getCityCode()).get(line.get(4)), 4, this.getClass().getName(), "importParkingLock", "地锁");
        parkingLock.setAreaCode(area.getAreaId());

        parkingLock.setAddress((String) ExcelImportUtil.getNotNullData(lineNum, line.get(5), 5, this.getClass().getName(), "importParkingLock", "地锁"));

        return parkingLock;
    }

    @Override
    public void exportParkingLock(HttpServletResponse response, ParkingLockDO searchModel, UserDO loginUser) {
        List<ParkingLockDO> parkingLockList = getParkingLockList(searchModel, loginUser);
        if (CollectionUtils.isEmpty(parkingLockList)) {
            LOGGER.warn(this.getClass() + "-exportParkingLock is failed, exportParkingLockList is empty|searchModel={}|loginUser={}", SerializationUtil.gson2String(searchModel), SerializationUtil.gson2String(loginUser));
            ErrorHtmlUtil.createErrorPage(response, "您导出的数据不存在，请修改正确的查询条件后再导出！");
        }

        List<String> attrList = Lists.newArrayList("code", "chStatus", "platformName", "powerStationName", "address");
        List<String> header = Lists.newArrayList("地锁编码", "地锁状态", "供应商", "充电点名称", "地址");
        ExcelExporterUtil.exportExcel(response, attrList, header, parkingLockList, ParkingLockDO.class, DownFileConstants.FILE_PARKING_LOCK_EXPORT, DownFileConstants.FILE_PARKING_LOCK_EXPORT_SHEET);

    }

    @Override
    public BaseResultDTO batchBindPowerStation(String parkingLockIds, Long powerStationId, UserDO loginUser) {
        if (Strings.isNullOrEmpty(parkingLockIds)) {
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "请选择要绑定充电点的地锁！");
        }

        List<Long> parkingLockIdList = SplitterUtil.splitToLongList(parkingLockIds, ",", null);
        for (Long parkingLockId : parkingLockIdList) {
            ParkingLockDO modifyLock = new ParkingLockDO();
            modifyLock.setId(parkingLockId);
            modifyLock.setPowerStationId(powerStationId);
            BaseResultDTO result = modifyParkingLock(modifyLock, loginUser);
            if (result.isFailed()) {
                return result;
            }
        }
        return new BaseResultDTO();
    }
}
