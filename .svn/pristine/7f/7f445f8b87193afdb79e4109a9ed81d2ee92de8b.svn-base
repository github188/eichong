package com.pub.service;


import java.util.List;
import java.util.Map;

import com.pub.model.TblCommitLog;

	/**
	 * 日志管理处理器
	 * 
	 * @author txd
	 *
	 */
	public interface TblCommitLogService {
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
	public List<TblCommitLog> getCommitLogList();
	
	/**
	 * 查询日志个数
	 * 
	 * @param TblCommitLog
	 * @return
	 */
	public long searchCommitLogCount(TblCommitLog commitLog);

	
	/**
	 * 查询日志列表
	 * 
	 * @param TblCommitLog
	 * @return
	 */
	public List<TblCommitLog> searchCommitLogList(TblCommitLog commitLog);
	
	
	/**
	 * @Title: deleteById
	 * @Description: 删除
	 * @param params
	 * @return
	 */
	public int deleteById(TblCommitLog commitLog);

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


