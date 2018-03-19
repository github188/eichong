package com.pub.dao;


import java.util.List;
import java.util.Map;

import com.pub.model.TblCommitLog;

    /**
    * 意见反馈
    * @author txd
    * 数据访问接口
    *
    */
	public interface TblCommitLogMapper {
		
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
     * 删除日志列表
	 * 
	 * @param TblCommitLog
	 * @return
     */
    public int removeCommitLog(TblCommitLog commitLog);
    /**
     * 加入日志列表
	 * 
	 * @param TblCommitLog
	 * @return
     */
    public int insert(TblCommitLog commitLog);
}


