package com.wanma.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblRateinformation;
import com.wanma.model.TblUser;

/**
 * @Description: 充电点管理业务处理接口
 * @author songjf
 * @createTime：2015-3-31 下午03:41:52
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface CmsPowerstationService {

	/**
	 * @Title: findPowers
	 * @Description: 获取充电点列表
	 * @param params
	 * @return
	 */
	public List<TblPowerstation> findPowers(TblPowerstation tblPowerstation);

	/**
	 * @Title: selectPowerCount
	 * @Description: 获取充电点总数
	 * @param params
	 * @return
	 */
	public long selectPowerCount(TblPowerstation tblPowerstation);
	/**
	 * @Title: selectPowerCount
	 * @Description: 通过Id获取充电点信息
	 * @param params
	 * @return
	 */
	public TblPowerstation getPowersById(TblPowerstation tblPowerstation);
	/**
	 * @Title: selectPowerCount
	 * @Description: 通过Id修改充电点状态
	 * @param params
	 * @return
	 */
	public void changeStateById(String powersId,int powersStateType);
	/**
	 * @Title: selectPowerCount
	 * @Description: 通过Id修改充电点驳回原因
	 * @param params
	 * @return
	 */
	public void changeElectricPileExamineReason(TblPowerstation tblPowerstation);
	/**
	 * 添加充电点数据
	 */
	public void addPowers(TblPowerstation tblPowerstation,MultipartFile[]  listImage,MultipartFile[] detailImage,TblUser loginUser);
	/**
	 * @Title: deleteById
	 * @Description: 通过ID获取充电点信息
	 * @param params
	 * @return
	 */
	public TblPowerstation getPowerById(TblPowerstation tblPowerstation);
	/**
	 * 修改充电点数据
	 */
	public void updatePowers(TblPowerstation tblPowerstation,MultipartFile[]  listImage,MultipartFile[] detailImage);
	/**
	 * 删除充电点
	 * @Title: findPowers
	 * @Description: 
	 * @param params
	 * @return
	 */
	public void removePower(Integer pkPowerstation);

	/**
	 * @Description: 电桩绑定充电点，设置排序号
	 * @author wbc	
	 * 2016年1月19日 	
	 * @return: void 
	 */
	public void electricPileBindPower(TblElectricpile pile);

	public void changeStateById_02(String powersId, int powersStateType,String rejectionReson);

	public int selectRateId(TblRateinformation rateinformation);
	

}
