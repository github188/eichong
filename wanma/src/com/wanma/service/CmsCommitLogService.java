package com.wanma.service;


import java.util.List;
import java.util.Map;
import com.wanma.model.CommitLog;

	/**
	 * 日志管理处理器
	 * 
	 * @author txd
	 *
	 */
	public interface CmsCommitLogService {
	/**
	* 根据Id查询
	* 
	* @return
	*/
	public  Map<String, Object> findCommitLog(String commitLog);
		
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
	 * @Title: deleteById
	 * @Description: 删除
	 * @param params
	 * @return
	 */
	public int deleteById(CommitLog commitLog);

	/**
	 * @Title: deleteCommitLogs
	 * @Description: 批量删除
	 * @param params
	 * @return
	 */
	public void deleteCommitLogs(String commitLogs);
	
	 /**
     * 加入日志列表
	 * 
	 * @param logContent
	 *        操作内容
     */
    public void insert(String logContent);

}


