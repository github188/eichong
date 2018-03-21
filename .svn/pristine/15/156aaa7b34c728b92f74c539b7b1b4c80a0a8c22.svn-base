package com.wanma.ims.controller.statistics.monitor;


import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.Gson;
import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.mapper.ElectricPileMapper;
import com.wanma.ims.service.MultipartFileService;
import com.wanma.ims.service.PowerStationStatisticService;
import javax.servlet.http.HttpServletRequest;
import com.wanma.ims.redis.RedisDataCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.DataCenterService;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 数据中心-mysql部分
 * @author mb
 *
 */
@Controller
@RequestMapping("/manage/dataCenter")
public class DataCenterController extends BaseController{

	 private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	 @Autowired
	 private DataCenterService dataCenterService;
	 @Autowired
	 private RedisDataCenter redisDataCenter;
	@Autowired
	private PowerStationStatisticService powerStationStatisticService;
	@Autowired
	private MultipartFileService multipartFileService;
	@Autowired
	private ElectricPileMapper electricPileMapper;
	/**
	 * 历史数据
	 */
	@RequestMapping(value="/getHistoryData")
	public void getHistoryData(String cpyId,String provinceCode,String cityCode,int type,HttpServletRequest request){
		JsonResult result = new JsonResult();
		UserDO loginUser = getCurrentLoginUser();
		try {
			Map<String,String> params=new HashMap<>();
			params.put("cpyId", cpyId);
			params.put("provinceCode", provinceCode);
			params.put("cityCode", cityCode);
			Map<String,Object> map = new HashMap<>();
			if (type==WanmaConstants.ORDER_DIMENSION) {
				map = dataCenterService.getHistoryDataForOrder(params);
			}else if (type==WanmaConstants.ELECTRIC_DIMENSION) {
				map = dataCenterService.getHistoryDataForElectric(params);
			}else if (type==WanmaConstants.USER_DIMENSION) {
				map = dataCenterService.getHistoryDataForUser(params);
			}
			result.setDataObject(map);
			responseJson(result);
		} catch (Exception e) {
			LOGGER.error(this.getClass() + "-getHistoryData is error", e.getMessage());
		}
	}
	
	/**
	 * 省份-城市-公司下拉选择 只请求一次
	 */
	@RequestMapping(value="/getSelectScope")
	public void getSelectScope(){
		JsonResult result = new JsonResult();
		try {
			List<Map<String, Object>> cpyList = dataCenterService.getSelectScope();
			result.setDataObject(cpyList);
			responseJson(result); 
		} catch (Exception e) {
			LOGGER.error(this.getClass()+".getSelectScope() error:",e.getMessage());
		}
	}

	/**
	 * 地图数据
	 */
	@RequestMapping(value="/getMapData")
	public void getMapData(String cpyId,String provinceCode,String cityCode,int type,HttpServletRequest request){
		JsonResult result = new JsonResult();
		UserDO loginUser = getCurrentLoginUser();
		try {
			Map<String,Object> params=new HashMap<>();
			params.put("cpyId", cpyId);
			params.put("provinceCode", provinceCode);
			params.put("cityCode", cityCode);
			List<Map<String,Object>> list = new ArrayList<>();
			if (type==WanmaConstants.ORDER_DIMENSION) {
				list = dataCenterService.getMapDataForOrder(params);
			}else if (type==WanmaConstants.ELECTRIC_DIMENSION) {
				list = dataCenterService.getMapDataForElectric(params);
			}else if (type==WanmaConstants.USER_DIMENSION) {
				list = dataCenterService.getMapDataForUser(params);
			}
			result.setDataObject(list);
			responseJson(result);
		} catch (Exception e) {
			LOGGER.error(this.getClass() + "-getMapData is error", e.getMessage());
		}
	}

	/**
	 * 充电实时信息
	 */
	@RequestMapping(value = "getHeadRealTimeInfo")
		public void getHeadRealTimeInfo(String epCode,String headId){
		JsonResult result = new JsonResult();
		try {
			ElectricPileDO electricPileDO =electricPileMapper.selectByElectricPileCode(epCode);
			String key = "epdata:current_status:" + epCode + "_" + headId;
			String redisHeadInfo = (String) redisDataCenter.strGet(key);
			Gson gson = new Gson();
			Map<String, Object> map = new HashMap<String, Object>();
			map = gson.fromJson(redisHeadInfo, map.getClass());
			LOGGER.info("getHeadRealTimeInfo::" + map);
			Map<String,Object> resultMap =new HashMap<>();
			if (map!=null){
				resultMap = dataCenterService.getHeadRealTimeInfo(map,electricPileDO);
			}
			result.setDataObject(resultMap);
			responseJson(result);
		} catch (Exception e) {
			LOGGER.error(this.getClass() + "-getHeadRealTimeInfo is error", e.getMessage());
		}
	}

	/**
	 * 获取单个充电点电桩枪头详情
	 */
	@RequestMapping("/getPowerStationPileHeadDetail")
	public void getPowerStationPileHeadDetail(Long powerStationId, Pager pager) {
		JsonResult result = new JsonResult();
		Map<String, Object> searchModel = new HashMap<>();
		searchModel.put("powerStationId", powerStationId);
		searchModel.put("pager", pager);
		long total = powerStationStatisticService.countPowerStationPileHeadDetail(searchModel);
		if (total <= pager.getOffset()) {
			pager.setPageNo(1L);
		}
		pager.setTotal(total);
		result.setPager(pager);
		result.setDataObject(powerStationStatisticService.getPowerStationPileHeadDetail(searchModel));
		responseJson(result);
	}
	/**
	 * 获取电站中 电桩/枪头在线状态
	 */
	@RequestMapping("/getPowerStationPileHeadOnlineState")
	public void getPowerStationPileHeadOnlineState(Long powerStationId,String type) {
		JsonResult result = new JsonResult();
		Map<String, Object> searchModel = new HashMap<>();
		searchModel.put("powerStationId", powerStationId);
		searchModel.put("type", type);
		result.setDataObject(dataCenterService.getPowerStationPileHeadOnlineState(searchModel));
		responseJson(result);
	}
	/**
	 * 获取电站中 地锁状态
	 */
	@RequestMapping("/getParkingLockState")
	public void getParkingLockState(Long powerStationId) {
		JsonResult result = new JsonResult();
		Map<String, Object> searchModel = new HashMap<>();
		searchModel.put("powerStationId", powerStationId);
		result.setDataObject(dataCenterService.getParkingLockState(searchModel));
		responseJson(result);
	}

	/**
	 * 获取充电点图片列表
	 */
	@RequestMapping("/getPowerStationPicList")
	public void getPowerStationPicList(Long powerStationId) {
		JsonResult result = new JsonResult();
		List<String> picImgList = multipartFileService.getAllMultiUrl(WanmaConstants.POWERSTATION_PIC, powerStationId+"");
		result.setDataObject(picImgList);
		responseJson(result);
	}

	/**
	 * 获取充电点历史数据
	 */
	@RequestMapping("/getPowerStationHistoryData")
	public void getPowerStationHistoryData(Long powerStationId) {
		JsonResult result = new JsonResult();
		Map<String, Object> searchModel = new HashMap<>();
		searchModel.put("powerStationId", powerStationId);
		result.setDataObject(dataCenterService.getPowerStationHistoryData(searchModel));
		responseJson(result);
	}

	/**
	 * 获取实时充电电量曲线
	 */
	@RequestMapping("/getChargePowerCurve")
	public void getChargePowerCurve(String cityCode,Long cpyId) {
		JsonResult result = new JsonResult();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String time = sdf.format(new Date());
		String key = "epdata:today_qudao_charge_vol_"+time+":"+cityCode+":"+cpyId;
		Map<String, Object> map = dataCenterService.getChargePowerCurve(key);
		result.setDataObject(map);
		responseJson(result);
	}
	/**
	 * 获取用户维度开始充电记录列表
	 */
	@RequestMapping("/getUserStartChargeRecord")
	public void getUserStartChargeRecord(String cityCode) {
		JsonResult result = new JsonResult();
		String key = "epdata:start_charge_list_city:"+cityCode;
		List<Map<String,Object>> list = dataCenterService.getStartChargeRecord(key);
		result.setDataObject(list);
		responseJson(result);
	}
	/**
	 * 获取电桩维度开始充电记录列表
	 */
	@RequestMapping("/getEpStartChargeRecord")
	public void getEpStartChargeRecord(String cityCode,Long cpyId) {
		JsonResult result = new JsonResult();
		String key = "epdata:start_charge_list_city_company:"+cityCode+":"+cpyId;
		List<Map<String,Object>> list = dataCenterService.getStartChargeRecord(key);
		result.setDataObject(list);
		responseJson(result);
	}

	/**
	 * 充电实时数据
	 */
	@RequestMapping("/getChargeRealTimeDate")
	@ResponseBody
	public Map<String,Object> getChargeRealTimeDate(String cpyId,String provinceCode,String cityCode,int type) {
		JsonResult result = new JsonResult();
		Map<String,String> params=new HashMap<>();
		params.put("cpyId", cpyId);
		params.put("provinceCode", provinceCode);
		params.put("cityCode", cityCode);
		Map<String,Object> map = new HashMap<>();
		if (type==WanmaConstants.ORDER_DIMENSION) {
			map = dataCenterService.getRealTimeDateForOrder(params);
		}else if (type==WanmaConstants.ELECTRIC_DIMENSION) {
			map = dataCenterService.getRealTimeDateForElectric(params);
		}else if (type==WanmaConstants.USER_DIMENSION) {
			map = dataCenterService.getRealTimeDateForUser(params);
		}
		return  map;
	}
	/**
	 * 用户充电排行榜
	 */
	@RequestMapping("/getUserChargeRank")
	public void getUserChargeRank(String provinceCode,String cityCode) {
		JsonResult result = new JsonResult();
		Map<String,String> params=new HashMap<>();
		params.put("provinceCode", provinceCode);
		params.put("cityCode", cityCode);
		result.setDataObject(dataCenterService.getUserChargeRank(params));
		responseJson(result);
	}
	/**
	 * 近5日充电量
	 */
	@RequestMapping("/getChargeCount5Days")
	public void getChargeCount5Days(String provinceCode,String cityCode,String cpyId) {
		JsonResult result = new JsonResult();
		Map<String,String> params=new HashMap<>();
		params.put("provinceCode", provinceCode);
		params.put("cityCode", cityCode);
		params.put("cpyId", cpyId);
		result.setDataObject(dataCenterService.getChargeCount5Days(params));
		responseJson(result);
	}
	/**
	*充电点详情静态数据
	*/
	@RequestMapping("/getDetailStaticData")
	public void getDetailStaticData(String epCode) {
		UserDO login = getCurrentLoginUser();
		JsonResult result = new JsonResult();
		Map<String,Object> map=dataCenterService.getDetailStaticData(epCode,login);
		result.setDataObject(map);
		responseJson(result);
	}


}
