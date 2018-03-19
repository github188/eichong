package com.wanma.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.common.dao.MultipartFileDao;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.app.dao.TblElectricpileMapper;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SHStopService;
import com.wanma.common.WanmaConstants;
import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsPowerstationMapper;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblRateinformation;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsPowerstationService;

/**
 * @Description: 充电点管理业务处理实现类
 * @author songjf
 * @createTime：2015-3-31 下午03:41:52
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class CmsPowerstationServiceImpl implements CmsPowerstationService {

	@Autowired
	private CmsPowerstationMapper powerstationMapper;
	@Autowired
	private TblElectricpileMapper tblElectricpileMapper;
	@Autowired
	CmsCommitLogService commitLogService;

	/**
	 * @Title: findPowers
	 * @Description: 获取充电点列表
	 * @param params
	 * @return
	 */
	@Override
	public List<TblPowerstation> findPowers(TblPowerstation tblPowerstation) {
		return powerstationMapper.findPowers(tblPowerstation);
	}

	/**
	 * 删除充电点
	 * @Title: findPowers
	 * @Description: 
	 * @param params
	 * @return
	 */
	@Override
	public void removePower(Integer pkPowerstation) {
		 powerstationMapper.delete(pkPowerstation);
		 commitLogService.insert("删除充电点，充电点主键：["+pkPowerstation+"]");
	}
	/**
	 * @Title: selectPowerCount
	 * @Description: 获取充电点总数
	 * @param params
	 * @return
	 */
	@Override
	public long selectPowerCount(TblPowerstation tblPowerstation) {
		return powerstationMapper.selectPowerCount(tblPowerstation);
	}
	/**
	 * @Title: selectPowerCount
	 * @Description: 通过Id获取充电点信息
	 * @param params
	 * @return
	 */
	@Override
	public TblPowerstation getPowersById(TblPowerstation tblPowerstation) {
		return powerstationMapper.get(tblPowerstation);
	}
	@Override
	public void changeStateById(String powersId,int powersStateType) {
		// TODO Auto-generated method stub
		TblPowerstation tblPowerstation=new TblPowerstation();
		tblPowerstation.setPkPowerstation(JudgeNullUtils.nvlInteger(powersId));
		tblPowerstation.setPostStatus(powersStateType);
		tblPowerstation.setRejectionReason("");
		tblPowerstation.setPostPic("");
		powerstationMapper.updateStateById(tblPowerstation);
	}
	
	@Override
	public void changeStateById_02(String powersId,int powersStateType,String rejectionReson) {
		// TODO Auto-generated method stub
		TblPowerstation tblPowerstation=new TblPowerstation();
		tblPowerstation.setPkPowerstation(JudgeNullUtils.nvlInteger(powersId));
		tblPowerstation.setPostStatus(powersStateType);
		tblPowerstation.setRejectionReason(rejectionReson);
		tblPowerstation.setPostPic("");
		powerstationMapper.updateStateById(tblPowerstation);
	}
	
	
	@SystemControllerLog(description = "充电点驳回")
	@Override
	public void changeElectricPileExamineReason(TblPowerstation tblPowerstation) {
		// TODO Auto-generated method stub
		powerstationMapper.changeElectricPileExamineReason(tblPowerstation);
	}
	/**
	 * 添加充电点数据
	 */
	 
	@Override
	public void addPowers(TblPowerstation tblPowerstation,MultipartFile[]  listImage,MultipartFile[] detailImage,TblUser loginUser) {
		tblPowerstation.setPostStatus(0);//提交审核中
		tblPowerstation.setPostCreatedate(new Date());
		tblPowerstation.setPostUpdatedate(new Date());
		tblPowerstation.setPostAreacode(1);
		tblPowerstation.setPostDetailpic("");
		tblPowerstation.setPostRemark("");
		tblPowerstation.setPostPoweruser(0);
		tblPowerstation.setPoStCreateUserId(loginUser.getUserId().toString());
		
		int userLevel = loginUser.getUserLevel();
		if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS)
			tblPowerstation.setPoStUserName(loginUser.getBusiCompany());
		else if (userLevel == WanmaConstants.USER_LEVEL_ADMIN
				|| userLevel == WanmaConstants.USER_LEVEL_SUPER)
			tblPowerstation.setPoStUserName(MessageManager.getMessageManager()
					.getSystemProperties("company.acw"));
		else
			tblPowerstation.setPoStUserName(loginUser.getNormRealName());
		
		if(StringUtils.isBlank(tblPowerstation.getPostPhone())){
			tblPowerstation.setPostPhone("4000850006");
		}
		powerstationMapper.insert(tblPowerstation);
		commitLogService.insert("添加充电点，充电点主键：["+tblPowerstation.getPkPowerstation()+"]");
		
		if(!StringUtils.isBlank(tblPowerstation.getPostEleids())){//绑定电桩操作
			String electricpileId=tblPowerstation.getPostEleids();//电桩Id
			if(electricpileId.indexOf(",")>0){
				String[] electricpileIds=electricpileId.split(",");
				for (int i = 0; i < electricpileIds.length; i++) {
					TblElectricpile tblElectricpile=new TblElectricpile();
					tblElectricpile.setPkElectricpile(JudgeNullUtils.nvlInteger(electricpileIds[i]));
					tblElectricpile.setRelevancePowerStation(tblPowerstation.getPkPowerstation());
					tblElectricpile.setElpiBinding(1);
					tblElectricpileMapper.electricPileBindPower(tblElectricpile);
				}
			}else{
				TblElectricpile tblElectricpile=new TblElectricpile();
				tblElectricpile.setPkElectricpile(JudgeNullUtils.nvlInteger(tblPowerstation.getPostEleids()));
				tblElectricpile.setRelevancePowerStation(tblPowerstation.getPkPowerstation());
				tblElectricpile.setElpiBinding(1);
				tblElectricpileMapper.electricPileBindPower(tblElectricpile);
			}
		}
		
		String image=StringUtils.removeEnd(tblPowerstation.getPostPic(),",");
		if(StringUtils.isNotBlank(image)){
			String[] idArr=image.split(",");
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("referenceId", tblPowerstation.getPkPowerstation());
			map.put("idArr", idArr);
			tblElectricpileMapper.updateImageInfo(map);
		}
	    /*if (ObjectUtil.isNotEmpty(listImage)) {//添加列表图片
	    	tblPowerstation.setAllMultiFile(listImage);
				MultipartFileUtil.saveAllMulti(tblPowerstation,
						WanmaConstants.MULTI_TYPE_POWER_LIST_IMAGE,
						tblPowerstation.getPkPowerstation() + "");
			}
	    if (ObjectUtil.isNotEmpty(detailImage)) {//添加详情图片
	    	tblPowerstation.setAllMultiFile(detailImage);
			MultipartFileUtil.saveAllMulti(tblPowerstation,
					WanmaConstants.MULTI_TYPE_POWER_DETAIL_IMAGE,
					tblPowerstation.getPkPowerstation() + "");
		}*/
	}
	/**
	 * 修改充电点数据
	 */
	@Override
	public void updatePowers(TblPowerstation tblPowerstation,MultipartFile[]  listImage,MultipartFile[] detailImage) {
		//tblPowerstation.setPostStatus(0);//提交审核中
		//tblPowerstation.setPostCreatedate(new Date());
		tblPowerstation.setPostUpdatedate(new Date());
		//tblPowerstation.setPostAreacode(1);
		//tblPowerstation.setPostPic("");
		//tblPowerstation.setPostDetailpic("");
		//tblPowerstation.setPostRemark("");
		//tblPowerstation.setPostIsappoint(1);
		tblPowerstation.setPostPic("");
		tblPowerstation.setPostDetailpic("");
		tblPowerstation.setPostRemark("");
		tblPowerstation.setPostPoweruser(0);
		if(StringUtils.isBlank(tblPowerstation.getPostPhone())){
			tblPowerstation.setPostPhone("4000850006");
		}
		powerstationMapper.updateByPowerId(tblPowerstation);
		commitLogService.insert("修改充电点，充电点主键：["+tblPowerstation.getPkPowerstation()+"]");
		/*if(!StringUtils.isBlank(tblPowerstation.getPostEleids())){//绑定电桩操作
			String electricpileId=tblPowerstation.getPostEleids();//电桩Id
			if(electricpileId.indexOf(",")>0){
				String[] electricpileIds=electricpileId.split(",");
				for (int i = 0; i < electricpileIds.length; i++) {
					TblElectricpile tblElectricpile=new TblElectricpile();
					tblElectricpile.setPkElectricpile(JudgeNullUtils.nvlInteger(electricpileIds[i]));
					tblElectricpile.setRelevancePowerStation(tblPowerstation.getPkPowerstation());
					tblElectricpile.setElpiBinding(1);
					tblElectricpileMapper.electricPileBindPower(tblElectricpile);
				}
			}else{
				TblElectricpile tblElectricpile=new TblElectricpile();
				tblElectricpile.setPkElectricpile(JudgeNullUtils.nvlInteger(tblPowerstation.getPostEleids()));
				tblElectricpile.setRelevancePowerStation(tblPowerstation.getPkPowerstation());
				tblElectricpile.setElpiBinding(1);
				tblElectricpileMapper.electricPileBindPower(tblElectricpile);
			}
		}*/
		
		 /*if (ObjectUtil.isNotEmpty(listImage)) {//添加列表图片
			 tblPowerstation.setAllMultiFile(listImage);
		    	if(MultipartFileUtil.verifyImageIsNotNull(tblPowerstation)){
		    		MultipartFileDao multipartFileDao = new MultipartFileDao();
					List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(WanmaConstants.MULTI_TYPE_POWER_LIST_IMAGE, tblPowerstation.getPkPowerstation() + "");
			    	MultipartFileUtil.delelteMulti(allMultiFileName, WanmaConstants.MULTI_TYPE_POWER_LIST_IMAGE, tblPowerstation.getPkPowerstation() + "");
			    	
			    	MultipartFileUtil.saveAllMulti(tblPowerstation,
								WanmaConstants.MULTI_TYPE_POWER_LIST_IMAGE,
								tblPowerstation.getPkPowerstation() + "");
					}
		    	}
		    if (ObjectUtil.isNotEmpty(detailImage)) {//添加详情图片
		    	tblPowerstation.setAllMultiFile(detailImage);
		    	if(MultipartFileUtil.verifyImageIsNotNull(tblPowerstation)){
		    		
		    		MultipartFileDao multipartFileDao = new MultipartFileDao();
					List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(WanmaConstants.MULTI_TYPE_POWER_DETAIL_IMAGE, tblPowerstation.getPkPowerstation() + "");
			    	MultipartFileUtil.delelteMulti(allMultiFileName, WanmaConstants.MULTI_TYPE_POWER_DETAIL_IMAGE, tblPowerstation.getPkPowerstation() + "");
			    	
			    	MultipartFileUtil.saveAllMulti(tblPowerstation,
							WanmaConstants.MULTI_TYPE_POWER_DETAIL_IMAGE,
							tblPowerstation.getPkPowerstation() + "");
		    	}
			}*/
	}
	/**
	 * @Title: deleteById
	 * @Description: 通过ID获取充电点信息
	 * @param params
	 * @return
	 */
	@Override
	public TblPowerstation getPowerById(TblPowerstation tblPowerstation){
		 tblPowerstation=powerstationMapper.get(tblPowerstation.getPkPowerstation());
		//获取电桩列表图片
			List<String> listImage=MultipartFileUtil.getAllMultiUrl(WanmaConstants.MULTI_TYPE_POWER_LIST_IMAGE,
					tblPowerstation.getPkPowerstation() + "");
			tblPowerstation.setPostPicUrl(listImage);
			/*//获取电桩详情图片
			List<String> detailImage=MultipartFileUtil.getAllMultiUrl(WanmaConstants.MULTI_TYPE_POWER_DETAIL_IMAGE,
					tblPowerstation.getPkPowerstation() + "");
			if(listImage.size()>0 && !listImage.isEmpty()){
				tblPowerstation.setPostPic(JudgeNullUtils.nvlStr(listImage.get(0)));
			}
			if(detailImage.size()>0 && !detailImage.isEmpty()){
				tblPowerstation.setPostDetailpic(JudgeNullUtils.nvlStr(detailImage.get(0)));
			}*/
			
		return tblPowerstation;
	}

	@Override
	public void electricPileBindPower(TblElectricpile pile) {
		tblElectricpileMapper.electricPileBindPower(pile);
	}


	@Override
	public int selectRateId(TblRateinformation rateinformation) {
		return powerstationMapper.selectRateId(rateinformation);
	}
}
