package com.wanma.ims.mapper;

import java.util.List;

import com.wanma.ims.common.domain.UserBlackWhiteDO;

public interface UserBlackWhiteMapper {
   
	public List<UserBlackWhiteDO> selectUserBlackWhiteByParams(UserBlackWhiteDO userBlackWhiteDO);
	
	public Long getUserBlackWhiteCount(UserBlackWhiteDO userBlackWhiteDO);

	public Integer insertUserBlackWhite(UserBlackWhiteDO userBlackWhiteDO);

	public Integer batchInsertUserBlackWhite(List<UserBlackWhiteDO> list);

	public Integer updateUserBlackWhite(UserBlackWhiteDO userBlackWhiteDO);

	public Integer removeUserBlackWhite(UserBlackWhiteDO userBlackWhiteDO);
	
	public List<UserBlackWhiteDO> getUserBlackWhite4Cpy(UserBlackWhiteDO userBlackWhiteDO);
}
