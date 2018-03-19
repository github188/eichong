package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.UserBlackWhiteDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.bo.UserBlackWhiteBO;

/**
 * 黑白名单-APP用户或渠道用户
 */
public interface UserBlackWhiteService {

	/**
	 * 黑白名单-新增
	 * @param
	 * */
	public Integer addUserBlackWhite(UserBlackWhiteBO userBlackWhiteBO, UserDO loginUser) throws Exception;

	/**
	 * 黑白名单-批量新增
	 * @param
	 * */
	public boolean batchAddUserBlackWhite(List<UserBlackWhiteDO> list) throws Exception;
	
	/**
	* <p>Description: 取得黑白名单的数量</p>
	* @param
	* @author bingo
	* @date 2017-7-5
	 */
	public Long getUserBlackWhiteCount(UserBlackWhiteDO userBlackWhiteDO) throws Exception;
	
	/**
	 * 黑白名单-查询
	 * @param 
	 * 用户ID:userId,渠道ID:cpyId,桩ID:electricPileId
	 * */
	public List<UserBlackWhiteDO> getUserBlackWhiteByParam(UserBlackWhiteDO userBlackWhiteDO);

	/**
	 * 黑白名单-修改
	 * @param userBlackWhiteDO
	 * type，userType如果一个参数需要更新，必须两个参数一起传递
	 * */
	public boolean modifyUserBlackWhite(UserBlackWhiteDO userBlackWhiteDO);
	
	/**
	 * 删除黑白名单
	 * @param
	 * */
	public Integer removeUserBlackWhite(UserBlackWhiteDO userBlackWhiteDO) throws Exception;
	
	/**
	* <p>Description: 取得公司的黑白名单数量</p>
	* @param
	* @author bingo
	* @date 2017-7-10
	 */
	public List<UserBlackWhiteDO> getUserBlackWhite4Cpy(UserBlackWhiteDO userBlackWhiteDO) throws Exception;
}
