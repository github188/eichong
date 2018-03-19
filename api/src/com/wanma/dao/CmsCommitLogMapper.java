package com.wanma.dao;


import java.util.List;
import java.util.Map;

import com.wanma.model.CommitLog;

    /**
    * 意见反馈
    * @author txd
    * 数据访问接口
    *
    */
	public interface CmsCommitLogMapper {
		
	/**
	* 根据ID查询
	* 
	* @param commitLog
	* @return
	*/
	public Map<String, Object> findCommitLog(String commitLog);	
		
		
	/**
	 * 取得日志列表
	 * 
	 * @return
	 */
	public List<CommitLog> getCommitLogList();
	
	/**
	 * 查询日志个数
	 * 
	 * @param CommitLog
	 * @return
	 */
	public long searchCommitLogCount(CommitLog commitLog);

	
	/**
	 * 查询日志列表
	 * 
	 * @param CommitLog
	 * @return
	 */
	public List<CommitLog> searchCommitLogList(CommitLog commitLog);
	
	/**
     * 删除日志列表
	 * 
	 * @param CommitLog
	 * @return
     */
    public int removeCommitLog(CommitLog commitLog);
    /**
     * 加入日志列表
	 * 
	 * @param CommitLog
	 * @return
     */
    public int insert(CommitLog commitLog);
}


