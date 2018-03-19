package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.UserCardDO;
import com.wanma.ims.common.domain.UserVinRelaDO;
import com.wanma.ims.controller.result.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import com.wanma.ims.common.domain.CarVinRelaDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;

/**
 * VIN码鉴权表
 */
public interface CarVinRelaService {

	/**
	 * VIN码鉴权表-新增
	 * @param
	 * */
	public BaseResultDTO addCarVinRela(CarVinRelaDO carVinRelaDO) throws Exception;
	
	/**
	* <p>Description: 取得VIN码鉴权表的数量</p>
	* @param
	* @author bingo
	* @date 2017-7-5
	 */
	public Long getCarVinRelaCount(CarVinRelaDO carVinRelaDO) throws Exception;
	
	/**
	 * VIN码鉴权表-查询
	 * @param
	 * */
	public List<CarVinRelaDO> getCarVinRelaList(CarVinRelaDO carVinRelaDO);
	
	/**
	 * 删除VIN码鉴权表
	 * @param
	 * */
	public Integer removeCarVinRela(CarVinRelaDO carVinRelaDO) throws Exception;
	
	/**
	* <p>Description: 导入VIN码</p>
	* @param
	* @author bingo
	* @date 2017-7-6
	 */
	public BaseResultDTO importCarVinRela(MultipartFile file, CarVinRelaDO carVinRelaDO, UserDO loginUser) throws Exception;

	/**
	 * 用户绑定vin码
	 * @param carVinRelaDO
	 * @param vinIds
	 * @return
	 */
	public BaseResultDTO bindVinCodeForUser(UserVinRelaDO carVinRelaDO, String vinIds);
	/**
	 * 根据用户主键获取用户的所有vin码
	 * @param userId
	 * @return
	 */
	List<UserVinRelaDO> getVinInfoByUserId(Long userId);

	/**
	 * 删除用户vin码关系表
	 * @param pkId
	 * @return
	 */
	boolean deleteUserVinRela(Long pkId);
	/**
	 * 用户绑卡修改vin码关系表
	 * @param userCard
	 * @param userDO
	 * @return
	 */
	BaseResultDTO updateUserVinRela(UserCardDO userCard, UserDO userDO);
}
