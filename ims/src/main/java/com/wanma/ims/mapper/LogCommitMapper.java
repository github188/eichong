package com.wanma.ims.mapper;

import java.util.List;

import com.wanma.ims.common.domain.LogCommitDO;


public interface LogCommitMapper {
   
	public Integer countLogCommitList(LogCommitDO logCommitDO);
	
	public List<LogCommitDO> selectLogCommitList(LogCommitDO logCommitDO);
	
	public LogCommitDO selectLogCommitById(Long logId);
	
	public Integer insertLogCommit(LogCommitDO logCommitDO);

	public Integer deleteLogCommitById(Long logId);
}
