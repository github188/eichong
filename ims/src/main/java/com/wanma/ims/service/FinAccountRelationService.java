package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.FinAccountRelationDO;
import com.wanma.ims.common.domain.UserCardDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.UserNormalDO;
import com.wanma.ims.common.dto.BaseResultDTO;


/**
 * 账户关系
 * @author bingo
 * @date 2017-06-13
 */
public interface FinAccountRelationService {
	/**
	* <p>Description: 获取账户关系数量</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public Long getFinAccountRelationCount(FinAccountRelationDO finAccountRelation);
	
	/**
	* <p>Description: 获取账户关系数据</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public List<FinAccountRelationDO> getFinAccountRelationList(FinAccountRelationDO finAccountRelation);
	
	/**
	* <p>Description: 新增账户关系</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public BaseResultDTO addFinAccountRelation(FinAccountRelationDO finAccountRelation, UserDO user) throws Exception;
	
	/**
	* <p>Description: 修改账户关系</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public int modifyFinAccountRelation(FinAccountRelationDO finAccountRelation, UserDO user) throws Exception;
	
	/**
	* <p>Description: 删除账户关系</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public BaseResultDTO removeFinAccountRelation(FinAccountRelationDO finAccountRelation) throws Exception;
	
	/**
	* <p>Description: 获取用户首页中账户关系数据</p>
	* @param
	* @author bingo
	* @date 2017-6-16
	 */
	public List<FinAccountRelationDO> getFinAccountRelation4User(UserNormalDO userNormal);
	
	/**
	* <p>Description: 获取卡首页中账户关系数据</p>
	* @param
	* @author bingo
	* @date 2017-6-21
	 */
	public List<FinAccountRelationDO> getFinAccountRelation4Card(UserCardDO userCard);
	
	/**
	* <p>Description: 获取公司首页中账务关系数据</p>
	* @param
	* @author bingo
	* @date 2017-7-10
	 */
	public List<FinAccountRelationDO> getFinAccountRelation4Cpy(String accountId);
}
