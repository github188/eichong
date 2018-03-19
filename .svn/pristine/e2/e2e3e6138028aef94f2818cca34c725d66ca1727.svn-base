/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.common.dao.MultipartFileDao;
import com.bluemobi.product.dao.AreaMapper;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.controller.ElectricPileListController;
import com.wanma.app.dao.TblElectricpileMapper;
import com.wanma.app.dao.TblElectricpileheadMapper;
import com.wanma.app.dao.TblPowerstationMapper;
import com.wanma.app.service.ElectricPileListService;
import com.wanma.common.ApplicationConsts;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.common.log.SystemControllerLog;
import com.wanma.model.ElectricPileList;
import com.wanma.model.PowerElectricPile;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblElectricpileheadForSH;
import com.wanma.model.TblEquipmentVersion;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblTypespan;
import com.wanma.model.TblUser;
import com.wanma.model.TcbElectric;
import com.wanma.service.CmsCommitLogService;

/***
 * 
 * 电桩查找(列表模式)
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-13 下午04:51:34
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class ElectricPileListServiceImpl implements ElectricPileListService {

	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(ElectricPileListController.class);
	@Autowired
	TblElectricpileheadMapper tblElectricpileheadMapper;
	@Autowired
	TblElectricpileMapper tblElectricpileMapper;
	@Autowired
	TblPowerstationMapper tblPowerstationMapper;
	@Autowired
	AreaMapper areaMapper;
	@Autowired
	CmsCommitLogService commitLogService;

	@Override
	public List<?> findRelevancePowerStation(TblElectricpile tblElectricpile) {

		return tblElectricpileMapper.findRelevancePowerStation(tblElectricpile);
	}

	/**
	 * 后台电桩列表获取数据
	 */
	@Override
	public List<?> getElectricpileByCondition(TblElectricpile tblElectricpile) {
		return tblElectricpileMapper
				.getElectricpileByCondition(tblElectricpile);
	}

	/**
	 * 后台电桩列表获取总数据
	 */
	@Override
	public long getElectricpileByConditionCount(TblElectricpile tblElectricpile) {

		return tblElectricpileMapper
				.getElectricpileByConditionCount(tblElectricpile);
	}

	/**
	 * 后台费率下发电桩列表获取数据
	 */
	@Override
	public List<?> getRateElectricpileByCondition(
			TblElectricpile tblElectricpile) {
		return tblElectricpileMapper
				.getRateElectricpileByCondition(tblElectricpile);
	}

	/**
	 * 后台费率下发电桩列表获取总数据
	 */
	@Override
	public long getRateElectricpileByConditionCount(
			TblElectricpile tblElectricpile) {

		return tblElectricpileMapper
				.getRateElectricpileByConditionCount(tblElectricpile);
	}

	/**
	 * 获取地图模式电桩列表
	 * 
	 * @param electricTypeId
	 *            汽车类型ID
	 * @param distance
	 *            距离 m
	 * @param price
	 *            价格
	 * @param evaluate
	 *            好评
	 */
	@Override
	public List<ElectricPileList> getElectricPileList(
			TblPowerstation tblPowerstation, TblElectricpile tblElectricpile) {

		List<ElectricPileList> electricPileList = new ArrayList<ElectricPileList>();

		// 01:获取充电点列表
		List<?> powersList = tblPowerstationMapper
				.getPowerstation(tblPowerstation);
		for (int i = 0; i < powersList.size(); i++) {
			ElectricPileList electricPileLists = new ElectricPileList();
			Map<String, Object> powersLMap = (Map<String, Object>) powersList
					.get(i);
			electricPileLists.setElectricId(JudgeNullUtils.nvlStr(powersLMap
					.get("pk_PowerStation")));
			electricPileLists
					.setElectricType(ApplicationConsts.ElectricPileManager.POWERSTATION);
			electricPileLists.setElectricName(JudgeNullUtils.nvlStr(powersLMap
					.get("poSt_Name")));
			electricPileLists.setElectricPileSum(JudgeNullUtils
					.nvlStr(powersLMap.get("electricPileCount")));
			electricPileLists.setElectricImage(JudgeNullUtils.nvlStr(powersLMap
					.get("poSt_Pic")));
			electricPileLists.setElectricAddress(JudgeNullUtils
					.nvlStr(powersLMap.get("poSt_Address")));
			electricPileLists.setElectricDistance(JudgeNullUtils
					.nvlStr(powersLMap.get("distance")));
			electricPileLists.setElectricLatitude(JudgeNullUtils
					.nvlStr(powersLMap.get("poSt_Latitude")));
			electricPileLists.setElectricLongitude(JudgeNullUtils
					.nvlStr(powersLMap.get("poSt_Longitude")));
			electricPileList.add(electricPileLists);
		}
		// 01:获取电桩列表
		tblElectricpile.setElpiBinding(0);
		List<?> electricpileList = tblElectricpileMapper
				.getElectricpile(tblElectricpile);
		for (int i = 0; i < electricpileList.size(); i++) {
			ElectricPileList electricPileLists = new ElectricPileList();
			Map<String, Object> electricPileMap = (Map<String, Object>) electricpileList
					.get(i);
			electricPileLists.setElectricId(JudgeNullUtils
					.nvlStr(electricPileMap.get("pk_ElectricPile")));
			electricPileLists
					.setElectricType(ApplicationConsts.ElectricPileManager.ELECTRICPILE);
			electricPileLists.setElectricName(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_ElectricPileName")));
			electricPileLists.setElectricImage(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_Image")));

			electricPileLists.setElectricUse(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_PowerUser")));//
			electricPileLists.setElectriChargingMode(JudgeNullUtils
					.nvlStr(electricPileMap.get("chargingModeName")));//
			electricPileLists.setElectricPowerInterface(JudgeNullUtils
					.nvlStr(electricPileMap.get("powerName")));//
			electricPileLists.setElectricPowerSize(JudgeNullUtils
					.nvlStr(electricPileMap.get("powerSizeName")));
			electricPileLists.setElectricMaxElectricity(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_MaxElectricity")));
			electricPileLists.setElectricAddress(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_ElectricPileAddress")));
			electricPileLists.setElectricDistance(JudgeNullUtils
					.nvlStr(electricPileMap.get("distance")));
			electricPileLists.setElectricLatitude(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_Latitude")));
			electricPileLists.setElectricLongitude(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_Longitude")));
			electricPileList.add(electricPileLists);

		}
		return electricPileList;
	}

	/**
	 * 新的快速检索
	 * 
	 * @param params
	 *            keyword 关键字 elecType 类型（1充电点，2电桩，3自行车充电电） orderType
	 *            排序（1距离，2价格，3好评） Longitude 经度 Latitude 纬度
	 * */
	public List<Map<String, String>> getElecSearchList(
			Map<String, String> params) {
		// List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		String elecType = params.get("elecType");
		if ("1".equals(elecType)) {
			return tblPowerstationMapper.getSearchPowerStationList(params);
		} else if ("2".equals(elecType) || "3".equals(elecType)) {
			return tblElectricpileMapper.getSearchElecPileList(params);
		}
		return new ArrayList<Map<String, String>>();
	}

	/**
	 * 获取列表模式电桩列表
	 * 
	 * @param distance
	 *            距离 m
	 * @param price
	 *            价格
	 * @param evaluate
	 *            好评
	 */
	@Override
	public List<ElectricPileList> getElectricPileListN(
			TblPowerstation tblPowerstation, TblElectricpile tblElectricpile) {

		List<ElectricPileList> electricPileList = new ArrayList<ElectricPileList>();

		// 4是搜索电动自行车充电点
		// if(!"4".equals(tblElectricpile.getScreenType())){
		// 01:获取充电点列表
		List<?> powersList = tblPowerstationMapper
				.getPowerstationN(tblPowerstation);
		for (int i = 0; i < powersList.size(); i++) {
			ElectricPileList electricPileLists = new ElectricPileList();
			Map<String, Object> powersLMap = (Map<String, Object>) powersList
					.get(i);
			electricPileLists.setElectricId(JudgeNullUtils.nvlStr(powersLMap
					.get("pk_PowerStation")));
			electricPileLists
					.setElectricType(ApplicationConsts.ElectricPileManager.POWERSTATION);
			electricPileLists.setElectricName(JudgeNullUtils.nvlStr(powersLMap
					.get("poSt_Name")));
			electricPileLists.setElectricPileSum(JudgeNullUtils
					.nvlStr(powersLMap.get("electricPileCount")));
			electricPileLists.setElectricImage(JudgeNullUtils.nvlStr(powersLMap
					.get("poSt_Pic")));
			electricPileLists.setElectricAddress(JudgeNullUtils
					.nvlStr(powersLMap.get("poSt_Address")));
			electricPileLists.setElectricDistance(JudgeNullUtils
					.nvlStr(powersLMap.get("distance")));
			electricPileLists.setServiceCharge(JudgeNullUtils.nvlStr(powersLMap
					.get("raIn_ServiceCharge")));
			electricPileLists.setCommentStart(JudgeNullUtils.nvlStr(powersLMap
					.get("prCo_CommentStart")));
			electricPileLists.setElectricLatitude(JudgeNullUtils
					.nvlStr(powersLMap.get("poSt_Latitude")));
			electricPileLists.setElectricLongitude(JudgeNullUtils
					.nvlStr(powersLMap.get("poSt_Longitude")));
			electricPileList.add(electricPileLists);
		}
		// }
		// 01:获取电桩列表
		List<?> electricpileList = tblElectricpileMapper
				.getElectricpileN(tblElectricpile);
		for (int i = 0; i < electricpileList.size(); i++) {
			ElectricPileList electricPileLists = new ElectricPileList();
			Map<String, Object> electricPileMap = (Map<String, Object>) electricpileList
					.get(i);
			electricPileLists.setElectricId(JudgeNullUtils
					.nvlStr(electricPileMap.get("pk_ElectricPile")));
			electricPileLists
					.setElectricType(ApplicationConsts.ElectricPileManager.ELECTRICPILE);
			electricPileLists.setElectricName(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_ElectricPileName")));
			electricPileLists.setElectricImage(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_Image")));

			electricPileLists.setElectricUse(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_PowerUser")));//
			electricPileLists.setElectriChargingMode(JudgeNullUtils
					.nvlStr(electricPileMap.get("chargingModeName")));//
			electricPileLists.setElectricPowerInterface(JudgeNullUtils
					.nvlStr(electricPileMap.get("powerName")));//
			electricPileLists.setElectricPowerSize(JudgeNullUtils
					.nvlStr(electricPileMap.get("powerSizeName")));
			electricPileLists.setElectricMaxElectricity(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_MaxElectricity")));
			electricPileLists.setElectricAddress(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_ElectricPileAddress")));
			electricPileLists.setElectricDistance(JudgeNullUtils
					.nvlStr(electricPileMap.get("distance")));
			electricPileLists.setServiceCharge(JudgeNullUtils
					.nvlStr(electricPileMap.get("raIn_ServiceCharge")));
			electricPileLists.setCommentStart(JudgeNullUtils
					.nvlStr(electricPileMap.get("prCo_CommentStart")));
			electricPileLists.setElectricLatitude(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_Latitude")));
			electricPileLists.setElectricLongitude(JudgeNullUtils
					.nvlStr(electricPileMap.get("elPi_Longitude")));
			electricPileList.add(electricPileLists);

		}
		return electricPileList;
	}

	/**
	 * 添加电桩数据
	 */
	@Override
	public void addElectricpile(TblElectricpile tblElectricpile,
			MultipartFile[] listImage, MultipartFile[] detailImage,
			TblUser loginUser) {
		tblElectricpile.setElpiElectricpilecode(BluemobiCommon.getOrderNo());
		tblElectricpile.setElpiState(0);// 提交审核中
		tblElectricpile.setElpiCreatedate(new Date());
		tblElectricpile.setElpiUpdatedate(new Date());
		tblElectricpile.setElpiBinding(0);
		tblElectricpile.setElpiUserid(loginUser.getUserId() + "");
		tblElectricpile.setElpiUsertype(JudgeNullUtils.nvlInteger(loginUser
				.getUserLevel()));
		int userLevel = loginUser.getUserLevel();
		if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS)
			tblElectricpile.setElPiUserName(loginUser.getBusiCompany());
		else if (userLevel == WanmaConstants.USER_LEVEL_ADMIN
				|| userLevel == WanmaConstants.USER_LEVEL_SUPER)
			tblElectricpile.setElPiUserName(MessageManager.getMessageManager()
					.getSystemProperties("company.acw"));
		else {
			tblElectricpile.setElPiUserName(loginUser.getNormRealName());
			tblElectricpile.setCompanyNumber("0");
		}

		if (StringUtils.isBlank(tblElectricpile.getElPi_Tell())) {
			tblElectricpile.setElPi_Tell("4000850006");
		}

		tblElectricpileMapper.insert(tblElectricpile);
		List<TblElectricpilehead> headList = tblElectricpile.getHeadList();
		if (headList != null && headList.size() > 0) {
			for (int i = 0; i < headList.size(); i++) {
				TblElectricpilehead headInfo = headList.get(i);
				headInfo.setPkElectricpile(tblElectricpile.getPkElectricpile());
				headInfo.setEpheElectricpileheadstate(0);
				headInfo.setEpheElectricpileheadname(i + 1 + "号枪头");
				headInfo.setHeadId(i + 1);
				tblElectricpileheadMapper.insert(headInfo);
			}
		}
		String elpiImage = StringUtils.removeEnd(
				tblElectricpile.getElpiImage(), ",");
		if (StringUtils.isNotBlank(elpiImage)) {
			String[] idArr = elpiImage.split(",");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("referenceId", tblElectricpile.getPkElectricpile());
			map.put("idArr", idArr);
			tblElectricpileMapper.updateImageInfo(map);
		}
		/*
		 * if (ObjectUtil.isNotEmpty(listImage)) {// 添加列表图片
		 * tblElectricpile.setAllMultiFile(listImage);
		 * MultipartFileUtil.saveAllMulti(tblElectricpile,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_IMAGE,
		 * tblElectricpile.getPkElectricpile() + ""); // 保存子图记录
		 * MultipartFileUtil.saveAllMultiNoPicture(tblElectricpile,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_SUBGRAPH,
		 * tblElectricpile.getPkElectricpile() + ""); // 获取原图 File
		 * sourceFilePath = MultipartFileUtil.getSourceFilePath(
		 * tblElectricpile, WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_IMAGE,
		 * tblElectricpile.getPkElectricpile() + ""); try { // 获取子图 Map<String,
		 * Object> fileInfo = MultipartFileUtil
		 * .getTargetFilePath(tblElectricpile, WanmaConstants.IMAGE_TEMP,
		 * tblElectricpile.getPkElectricpile() + ""); if (fileInfo != null) { //
		 * 切图 ImageUtils.compress(sourceFilePath,
		 * JudgeNullUtils.nvlStr(fileInfo.get("filePath")),
		 * WanmaConstants.PICTRUE_W, WanmaConstants.PICTRUE_H); // 正式环境 使用
		 * --start // 切图完成，备份图片到图片服务器 // 正式环境 使用 --start FileInputStream input =
		 * new FileInputStream(
		 * JudgeNullUtils.nvlStr(fileInfo.get("filePath")));
		 * MoveFileUtil.fileCopybyFtp( input,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_SUBGRAPH + "/" +
		 * JudgeNullUtils.nvlStr(fileInfo .get("fileName"))); // 正式环境 使用 --end }
		 * } catch (Exception e) { // TODO: handle exception log.error("切图异常：",
		 * e); } }
		 */

		/*
		 * if (ObjectUtil.isNotEmpty(detailImage)) {// 添加详情图片
		 * tblElectricpile.setAllMultiFile(detailImage);
		 * 
		 * MultipartFileUtil.saveAllMulti(tblElectricpile,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_IMAGE,
		 * tblElectricpile.getPkElectricpile() + ""); }
		 */
		/*
		 * if (ObjectUtil.isNotEmpty(detailImage)) {// 添加详情图片
		 * tblElectricpile.setAllMultiFile(detailImage);
		 * MultipartFileUtil.saveAllMulti(tblElectricpile,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_IMAGE,
		 * tblElectricpile.getPkElectricpile() + ""); // 保存子图记录
		 * MultipartFileUtil.saveAllMultiNoPicture(tblElectricpile,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_SUBGRAPH,
		 * tblElectricpile.getPkElectricpile() + ""); // 获取原图 File
		 * sourceFilePath = MultipartFileUtil.getSourceFilePath(
		 * tblElectricpile, WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_IMAGE,
		 * tblElectricpile.getPkElectricpile() + ""); try { // 获取子图 Map<String,
		 * Object> fileInfo = MultipartFileUtil
		 * .getTargetFilePath(tblElectricpile, WanmaConstants.IMAGE_TEMP,
		 * tblElectricpile.getPkElectricpile() + ""); if (fileInfo != null) { //
		 * 切图 ImageUtils.compress(sourceFilePath,
		 * JudgeNullUtils.nvlStr(fileInfo.get("filePath")),
		 * WanmaConstants.PICTRUE_W, WanmaConstants.PICTRUE_H); //
		 * 切图完成，备份图片到图片服务器\ // 正式环境 使用 --start FileInputStream input = new
		 * FileInputStream( JudgeNullUtils.nvlStr(fileInfo.get("filePath")));
		 * MoveFileUtil.fileCopybyFtp( input,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_SUBGRAPH + "/" +
		 * JudgeNullUtils.nvlStr(fileInfo .get("fileName"))); // 正式环境 使用 --end }
		 * } catch (Exception e) { // TODO: handle exception log.error("切图异常：",
		 * e); } }
		 */
		commitLogService.insert("添加电桩，电桩主键：["
				+ tblElectricpile.getPkElectricpile() + "]");
	}

	/**
	 * 修改电桩数据
	 */
	@Override
	public void changeElectricpile(TblElectricpile tblElectricpile,
			MultipartFile[] listImage, MultipartFile[] detailImage,
			TblUser loginUser) {// tblElectricpile.setElpiCreatedate(new
								// Date());

		int elpiTypeSpanId = tblElectricpileMapper
				.getTypeSpanId(tblElectricpile.getPkElectricpile());
		tblElectricpile.setElpiUpdatedate(new Date());
		// tblElectricpile.setElpiBinding(0);
		// tblElectricpile.setElpiIsappoint(1);
		tblElectricpile.setElpiUserid(loginUser.getUserId() + "");
		tblElectricpile.setElpiUsertype(JudgeNullUtils.nvlInteger(loginUser
				.getUserLevel()));
		if (StringUtils.isBlank(tblElectricpile.getElPi_Tell())) {
			tblElectricpile.setElPi_Tell("4000850006");
		}
		if (StringUtil.isEmpty(tblElectricpile.getOfflineTime())) {
			tblElectricpile.setOfflineTime("");
		}
		tblElectricpileMapper.updateByElecId(tblElectricpile);
		List<TblElectricpilehead> headList = tblElectricpile.getHeadList();
		for (TblElectricpilehead headInfo : headList) {
			if (headInfo.getPkElectricpilehead() == null)
				tblElectricpileheadMapper.insert(headInfo);
			else
				tblElectricpileheadMapper.update(headInfo);
		}
		commitLogService.insert("修改电桩，电桩主键：["
				+ tblElectricpile.getPkElectricpile() + "]");

		/*
		 * if (ObjectUtil.isNotEmpty(listImage)) {// 添加列表图片
		 * tblElectricpile.setAllMultiFile(listImage); if
		 * (MultipartFileUtil.verifyImageIsNotNull(tblElectricpile)) {
		 * MultipartFileDao multipartFileDao = new MultipartFileDao();
		 * List<String> allMultiFileName = multipartFileDao
		 * .getAllMultiFileName( WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_IMAGE,
		 * tblElectricpile.getPkElectricpile() + "");
		 * MultipartFileUtil.delelteMulti(allMultiFileName,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_IMAGE,
		 * tblElectricpile.getPkElectricpile() + "");
		 * 
		 * List<String> allMultiFileName1 = multipartFileDao
		 * .getAllMultiFileName(
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_SUBGRAPH,
		 * tblElectricpile.getPkElectricpile() + "");
		 * MultipartFileUtil.delelteMulti(allMultiFileName1,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_SUBGRAPH,
		 * tblElectricpile.getPkElectricpile() + "");
		 * 
		 * MultipartFileUtil.saveAllMulti(tblElectricpile,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_IMAGE,
		 * tblElectricpile.getPkElectricpile() + ""); // 保存子图记录
		 * MultipartFileUtil.saveAllMultiNoPicture(tblElectricpile,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_SUBGRAPH,
		 * tblElectricpile.getPkElectricpile() + ""); // 获取原图 File
		 * sourceFilePath = MultipartFileUtil.getSourceFilePath(
		 * tblElectricpile, WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_IMAGE,
		 * tblElectricpile.getPkElectricpile() + ""); try { // 获取子图 Map<String,
		 * Object> fileInfo = MultipartFileUtil
		 * .getTargetFilePath(tblElectricpile, WanmaConstants.IMAGE_TEMP,
		 * tblElectricpile.getPkElectricpile() + ""); if (fileInfo != null) { //
		 * 切图 ImageUtils .compress(sourceFilePath, JudgeNullUtils
		 * .nvlStr(fileInfo.get("filePath")), WanmaConstants.PICTRUE_W,
		 * WanmaConstants.PICTRUE_H); // 切图完成，备份图片到图片服务器 // 正式环境 使用 --start
		 * FileInputStream input = new FileInputStream(
		 * JudgeNullUtils.nvlStr(fileInfo.get("filePath"))); MoveFileUtil
		 * .fileCopybyFtp( input,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_SUBGRAPH + "/" +
		 * JudgeNullUtils.nvlStr(fileInfo .get("fileName"))); // 正式环境 使用 --end }
		 * } catch (Exception e) { // TODO: handle exception log.error("切图异常：",
		 * e); } }
		 * 
		 * } if (ObjectUtil.isNotEmpty(detailImage)) {// 添加详情图片
		 * tblElectricpile.setAllMultiFile(detailImage); if
		 * (MultipartFileUtil.verifyImageIsNotNull(tblElectricpile)) {
		 * 
		 * MultipartFileDao multipartFileDao = new MultipartFileDao();
		 * List<String> allMultiFileName = multipartFileDao
		 * .getAllMultiFileName(
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_IMAGE,
		 * tblElectricpile.getPkElectricpile() + "");
		 * MultipartFileUtil.delelteMulti(allMultiFileName,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_IMAGE,
		 * tblElectricpile.getPkElectricpile() + "");
		 * 
		 * List<String> allMultiFileName1 = multipartFileDao
		 * .getAllMultiFileName(
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_SUBGRAPH,
		 * tblElectricpile.getPkElectricpile() + "");
		 * MultipartFileUtil.delelteMulti(allMultiFileName1,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_SUBGRAPH,
		 * tblElectricpile.getPkElectricpile() + "");
		 * 
		 * MultipartFileUtil.saveAllMulti(tblElectricpile,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_IMAGE,
		 * tblElectricpile.getPkElectricpile() + ""); // 保存子图记录
		 * MultipartFileUtil.saveAllMultiNoPicture(tblElectricpile,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_SUBGRAPH,
		 * tblElectricpile.getPkElectricpile() + ""); // 获取原图 File
		 * sourceFilePath = MultipartFileUtil.getSourceFilePath(
		 * tblElectricpile, WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_IMAGE,
		 * tblElectricpile.getPkElectricpile() + ""); try { // 获取子图 Map<String,
		 * Object> fileInfo = MultipartFileUtil
		 * .getTargetFilePath(tblElectricpile, WanmaConstants.IMAGE_TEMP,
		 * tblElectricpile.getPkElectricpile() + ""); if (fileInfo != null) { //
		 * 切图 ImageUtils .compress(sourceFilePath, JudgeNullUtils
		 * .nvlStr(fileInfo.get("filePath")), WanmaConstants.PICTRUE_W,
		 * WanmaConstants.PICTRUE_H); // 切图完成，备份图片到图片服务器 // 正式环境 使用 --start
		 * FileInputStream input = new FileInputStream(
		 * JudgeNullUtils.nvlStr(fileInfo.get("filePath"))); MoveFileUtil
		 * .fileCopybyFtp( input,
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_SUBGRAPH + "/" +
		 * JudgeNullUtils.nvlStr(fileInfo .get("fileName"))); // 正式环境 使用 --end }
		 * } catch (Exception e) { // TODO: handle exception log.error("切图异常：",
		 * e); } } }
		 */
	}

	/**
	 * 通过ID获取电桩数据
	 */
	@Override
	public TblElectricpile getElectricpileById(TblElectricpile tblElectricpile) {

		tblElectricpile = tblElectricpileMapper.get(tblElectricpile);
		TblElectricpilehead head = new TblElectricpilehead();
		head.setPkElectricpile(tblElectricpile.getPkElectricpile());
		tblElectricpile
				.setHeadList((ArrayList<TblElectricpilehead>) tblElectricpileheadMapper
						.find(head));
		// 获取电桩列表图片
		List<String> listImage = MultipartFileUtil.getAllMultiUrl(
				WanmaConstants.MULTI_TYPE_ELECTRICT_LIST_IMAGE,
				tblElectricpile.getPkElectricpile() + "");
		/*
		 * // 获取电桩详情图片 List<String> detailImage =
		 * MultipartFileUtil.getAllMultiUrl(
		 * WanmaConstants.MULTI_TYPE_ELECTRICT_DETAIL_IMAGE,
		 * tblElectricpile.getPkElectricpile() + "");
		 */
		if (listImage.size() > 0 && !listImage.isEmpty()) {
			tblElectricpile.setElpiImageUrl(listImage);
			// .setElpiImage(JudgeNullUtils.nvlStr(listImage.get(0)));
		}
		/*
		 * if (detailImage.size() > 0 && !detailImage.isEmpty()) {
		 * tblElectricpile.setElpiDetailimage(JudgeNullUtils
		 * .nvlStr(detailImage.get(0))); }
		 */
		return tblElectricpile;
	}

	@Override
	public String checkElectricUnique(String elpiElectricpilecode) {
		// TODO Auto-generated method stub
		// 处理结果
		String processResult = CommonConsts.CHECK_RESULT_OK;
		TblElectricpile tblElectricpile = new TblElectricpile();
		tblElectricpile.setElpiElectricpilecode(elpiElectricpilecode);
		long elecCount = tblElectricpileMapper
				.searchElectricCountByElecCode(tblElectricpile);
		// 如果取得的用户数大于0个，返回错误标识
		if (elecCount > 0) {
			return CommonConsts.CHECK_RESULT_NG;
		}

		// 返回处理结果标识
		return processResult;
	}

	/**
	 * 修改电桩状态
	 * 
	 * @param feedback
	 */
	@Override
	public void updateElectricPileSate(String elpiElectricpilecode,
			int elpiState, String companyNumber) {
		TblElectricpile electricpile = new TblElectricpile();
		electricpile.setElpiElectricpilecode(elpiElectricpilecode);
		electricpile.setElpiState(elpiState);
		electricpile.setElpiRejectionreason("");
		electricpile.setCompanyNumber(companyNumber);
		tblElectricpileMapper.updateElectricPileSate(electricpile);
		commitLogService.insert("修改电桩状态，电桩编号：[" + elpiElectricpilecode + "]");
	}

	/**
	 * 驳回电桩
	 */
	@Override
	public long changeElectricPileExamineReason(TblElectricpile tblElectricpile) {
		commitLogService.insert("电桩驳回状态，电桩编号：["
				+ tblElectricpile.getElpiElectricpilecode() + "]");
		return tblElectricpileMapper
				.updateElectricPileExamineReason(tblElectricpile);
	}

	/**
	 * 后台电桩列表获取数据
	 */
	@Override
	public List<?> getElectricpileByCondition1(TblElectricpile tblElectricpile) {
		List<Map<String, Object>> electricList = (List<Map<String, Object>>) tblElectricpileMapper
				.getElectricpileByCondition1(tblElectricpile);
		// 下面注释的部分代码转到前台jstl实现了
		/*
		 * for (int i = 0; i < electricList.size(); i++) { //
		 * {pkElectricpile:'1', elpiElectricpilename:'技术部'} Map<String, Object>
		 * electricpileMap = (Map<String, Object>) electricList .get(i);
		 * electricpileMap.put( "electricpileValue", "{pkElectricpile:'" +
		 * JudgeNullUtils.nvlLang(electricpileMap .get("pk_ElectricPile")) +
		 * "', " + "elpiElectricpilename:'" +
		 * JudgeNullUtils.nvlStr(electricpileMap .get("elPi_ElectricPileName"))
		 * + "'}"); }
		 */
		return electricList;
	}

	/**
	 * 后台电桩列表获取数据
	 */
	@Override
	public List<?> getElectricpileForConcentrator(
			TblElectricpile tblElectricpile) {
		List<Map<String, Object>> electricList = (List<Map<String, Object>>) tblElectricpileMapper
				.getElectricpileForConcentrator(tblElectricpile);
		for (int i = 0; i < electricList.size(); i++) {
			Map<String, Object> electricpileMap = (Map<String, Object>) electricList
					.get(i);
			electricpileMap.put(
					"electricpileValue",
					"{pkElectricpile:'"
							+ JudgeNullUtils.nvlLang(electricpileMap
									.get("pk_ElectricPile"))
							+ "', "
							+ "elpiElectricpilename:'"
							+ JudgeNullUtils.nvlStr(electricpileMap
									.get("elPi_ElectricPileName")) + "'}");
		}
		return electricList;
	}

	@Override
	public long getElectricpileForConcentratorCount(
			TblElectricpile tblElectricpile) {
		return tblElectricpileMapper
				.getElectricpileForConcentratorCount(tblElectricpile);
	}

	/**
	 * 后台电桩列表获取总数据
	 */
	@Override
	public long getElectricpileByConditionCount1(TblElectricpile tblElectricpile) {

		return tblElectricpileMapper
				.getElectricpileByConditionCount1(tblElectricpile);
	}

	/**
	 * 电桩绑定充电点
	 */
	@Override
	public void electricPileBindPower(TblElectricpile tblElectricpile) {

		tblElectricpileMapper.electricPileBindPower(tblElectricpile);
	}

	/**
	 * 删除电桩
	 */
	@Override
	public void removeElectricPile(TblElectricpile tblElectricpile) {
		if (tblElectricpile.getElpiState() == 0
				|| tblElectricpile.getElpiState() == 5) {
			tblElectricpileMapper.delete(tblElectricpile);
		} else {
			tblElectricpileMapper.updateDeleteFlag(tblElectricpile);
		}
		commitLogService.insert("删除电桩，电桩主键：["
				+ tblElectricpile.getPkElectricpile() + "]");
	}

	/**
	 * 在app启动时初始化电桩数据到本地
	 * 
	 * @param epId
	 *            暂时无用，后期可能会根据id来获取单条记录
	 * @return
	 */
	public List<Map<String, Object>> initEpFromDB(int epId) {
		return tblElectricpileMapper.initEpFromDB(epId);
	}

	/**
	 * 根据电桩编号获取电桩信息
	 * 
	 * @param elpiElectricpilecode
	 */
	@Override
	public TblElectricpile getElectricPileByCode(String elpiElectricpilecode) {
		return tblElectricpileMapper
				.getElectricPileByCode(elpiElectricpilecode);
	}

	/**
	 * 修改电桩状态，电桩编号
	 * 
	 * @param elpiElectricpilecode
	 *            电桩编号
	 * @param elpiState
	 *            电桩状态
	 */
	@Override
	public String updateSateAndCode(String elpiElectricpilecode, int elpiState) {
		TblElectricpile electricpile = tblElectricpileMapper
				.getElectricPileInfo(elpiElectricpilecode);
		// 充电方式
		String ChargingMode = electricpile.getChargingMode();
		if (ChargingMode.indexOf("直流") > -1) {
			ChargingMode = WanmaConstants.ELECTRIC_TYPE_DIRECT;
		} else {
			ChargingMode = WanmaConstants.ELECTRIC_TYPE_COMMUNICATION;
		}
		// 相同地区下的最大电桩编号最后7位
		String bigElectricpilecode = electricpile.getElpiElectricpilecode();
		if (StringUtils.isEmpty(bigElectricpilecode)) {
			bigElectricpilecode = "0000001";
		} else {
			int a = Integer.parseInt(bigElectricpilecode) + 1;
			bigElectricpilecode = String.format("%07d", a);
		}
		// 订单编号 (6位地区编号+1位电桩类型（直流|交流）+ 2位厂家编号 + 7位序号（一个地区下面的电桩排序号）)
		String newElpiElectricpilecode = electricpile.getElPiOwnCountyCode()
				+ ChargingMode + electricpile.getMakerRemark()
				+ bigElectricpilecode;
		electricpile.setElpiElectricpilecode(elpiElectricpilecode);
		electricpile.setElpiState(elpiState);
		electricpile.setElpiRejectionreason("");
		electricpile.setNewElpiElectricpilecode(newElpiElectricpilecode);
		tblElectricpileMapper.updateSateAndCode(electricpile);
		commitLogService.insert("修改电桩状态和编号，原编号：[" + elpiElectricpilecode
				+ "]，新编号：[" + newElpiElectricpilecode + "]");
		return newElpiElectricpilecode;
	}

	/**
	 * 获取需要下线的桩体
	 * 
	 * @return
	 */
	@Override
	public List<TblElectricpile> getOfflineElectric() {
		return tblElectricpileMapper.getOfflineElectric();
	}

	/**
	 * 根据id更新电桩状态
	 * 
	 * @return
	 */
	@Override
	public int updateEleState(TblElectricpile tblElectricpile) {
		return tblElectricpileMapper.updateEleState(tblElectricpile);
	}

	/**
	 * 查询电桩以及其所属枪头
	 */
	@Override
	public List<PowerElectricPile> getElectricPileHeadList(
			TblElectricpile tblElectricpile) {

		return tblElectricpileMapper.getElectricpileHeadN(tblElectricpile);
	}

	/**
	 * 查询电桩以及其所属枪头数量
	 */
	public int getCountElectricPileHeadList(TblElectricpile tblElectricpile) {
		return tblElectricpileMapper.getCountElectricpileHeadN(tblElectricpile);
	}

	/**
	 * 获取省市树结构
	 */
	@Override
	public String getAreaTree() {
		StringBuilder sb = WanmaConstants.areaSb;
		if (sb == null) {
			sb = new StringBuilder();
			List<Map<String, String>> datas = areaMapper
					.getProvincCityAreaList();
			for (Map<String, String> data : datas) {
				sb.append("{id:'" + data.get("id") + "', pId:'"
						+ data.get("pId") + "', name:'" + data.get("name")
						+ "'},");
			}
			WanmaConstants.areaSb = sb;
		}
		return "[" + sb.substring(0, sb.length() - 1) + "]";
	}

	@Override
	public boolean isBespokeOrCharging(TblElectricpile tblElectricpile) {
		return tblElectricpileMapper
				.getBespokeOrChargingHeadCount(tblElectricpile) > 0;
	}

	@Override
	public List<TblTypespan> getTypespanList() {

		return tblElectricpileMapper.getTypespanList();
	}

	@Override
	public List<TblElectricpileheadForSH> getList(TblElectricpileheadForSH head) {
		return tblElectricpileheadMapper.getList(head);
	}

	@Override
	public List<TcbElectric> getElectricpileListByPsId(Integer pkPowerstation) {
		return tblElectricpileMapper.getElectricpileListByPsId(pkPowerstation);
	}

	@Override
	public List<TblElectricpile> getElpiMaker() {
		return tblElectricpileMapper.getElpiMaker();
	}

	@Override
	public void insertElectricpile(List<TblElectricpile> pileList,
			MultipartFile[] listImage, MultipartFile[] detailImage,
			TblUser loginUser) {

		for (TblElectricpile tblElectricpile : pileList) {

			tblElectricpile
					.setElpiElectricpilecode(BluemobiCommon.getOrderNo());
			tblElectricpile.setElpiState(0);// 提交审核中
			tblElectricpile.setElpiCreatedate(new Date());
			tblElectricpile.setElpiUpdatedate(new Date());
			tblElectricpile.setElpiBinding(0);
			tblElectricpile.setElpiUserid(loginUser.getUserId() + "");
			tblElectricpile.setElpiUsertype(JudgeNullUtils.nvlInteger(loginUser
					.getUserLevel()));
			int userLevel = loginUser.getUserLevel();
			if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS)
				tblElectricpile.setElPiUserName(loginUser.getBusiCompany());
			else if (userLevel == WanmaConstants.USER_LEVEL_ADMIN
					|| userLevel == WanmaConstants.USER_LEVEL_SUPER)
				tblElectricpile
						.setElPiUserName(MessageManager.getMessageManager()
								.getSystemProperties("company.acw"));
			else {
				tblElectricpile.setElPiUserName(loginUser.getNormRealName());
				tblElectricpile.setCompanyNumber("0");
			}

			if (StringUtils.isBlank(tblElectricpile.getElPi_Tell())) {
				tblElectricpile.setElPi_Tell("4000850006");
			}

			tblElectricpileMapper.insert(tblElectricpile);
			List<TblElectricpilehead> headList = tblElectricpile.getHeadList();
			if (headList != null && headList.size() > 0) {
				for (int i = 0; i < headList.size(); i++) {
					TblElectricpilehead headInfo = headList.get(i);
					headInfo.setPkElectricpile(tblElectricpile
							.getPkElectricpile());
					headInfo.setEpheElectricpileheadstate(0);
					headInfo.setEpheElectricpileheadname(i + 1 + "号枪头");
					headInfo.setHeadId(i + 1);
					tblElectricpileheadMapper.insert(headInfo);
				}
			}
			String elpiImage = StringUtils.removeEnd(
					tblElectricpile.getElpiImage(), ",");
			if (StringUtils.isNotBlank(elpiImage)) {
				String[] idArr = elpiImage.split(",");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("referenceId", tblElectricpile.getPkElectricpile());
				map.put("idArr", idArr);
				tblElectricpileMapper.updateImageInfo(map);
			}
			commitLogService.insert("添加电桩，电桩主键：["
					+ tblElectricpile.getPkElectricpile() + "]");
		}
	}
}
