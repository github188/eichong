package com.wanma.ims.mapper;


import java.util.List;

import com.wanma.ims.common.domain.FinAccountRelationDO;
import com.wanma.ims.common.domain.UserCardDO;
import com.wanma.ims.common.domain.UserNormalDO;

/**
 * 账户关系 
 * @author bingo
 * @date 2017-06-13
 */
public interface FinAccountRelationMapper {
	
	public Long getFinAccountRelationCount(FinAccountRelationDO finAccountRelation);
	
	public List<FinAccountRelationDO> getFinAccountRelationList(FinAccountRelationDO finAccountRelation);
	
	public int addFinAccountRelation(FinAccountRelationDO finAccountRelation);
	
	public int modifyFinAccountRelation(FinAccountRelationDO finAccountRelation);
	
	public int removeFinAccountRelation(FinAccountRelationDO finAccountRelation);
	
	public List<FinAccountRelationDO> getFinAccountRelation4User(UserNormalDO userNormal);
	
	public List<FinAccountRelationDO> getFinAccountRelation4Card(UserCardDO userCard);
	
	public List<FinAccountRelationDO> getFinAccountRelation4Cpy(String accountId);
}