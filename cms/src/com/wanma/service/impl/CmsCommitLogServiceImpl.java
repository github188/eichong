package com.wanma.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.base.common.GlobalSystem;
import com.base.common.HttpRequest;
import com.base.common.SessionMgr;
import com.base.util.JudgeNullUtils;
import com.pub.model.TblUser;
import com.wanma.dao.CmsCommitLogMapper;
import com.wanma.model.CommitLog;
import com.wanma.service.CmsCommitLogService;

@Service
public class CmsCommitLogServiceImpl implements CmsCommitLogService {

	@Autowired
	private CmsCommitLogMapper commitLogDao;

	/**
	 * 取得意见反馈表一览
	 * 
	 */
	@Override
	public List<CommitLog> getCommitLogList() {
		// 意见反馈表一览
		List<CommitLog> listCommitLog;

		// 取得意见反馈表一览
		listCommitLog = commitLogDao.getCommitLogList();

		// 返回意见反馈表一览
		return listCommitLog;
	}

	/**
	 * 查询意见反馈个数
	 * 
	 */
	public long searchCommitLogCount(CommitLog commitLog) {
		// 意见反馈个数
		long dataCount;

		// 取得意见反馈个数
		dataCount = commitLogDao.searchCommitLogCount(commitLog);

		// 返回意见反馈个数
		return dataCount;

	}

	/**
	 * 查询意见反馈表一览
	 * 
	 */
	@Override
	public List<CommitLog> searchCommitLogList(CommitLog commitLog) {
		// 意见反馈表一览
		List<CommitLog> listCommitLog;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		// 取得意见反馈表一览
		listCommitLog = commitLogDao.searchCommitLogList(commitLog);

		// 返回意见反馈表一览
		return listCommitLog;
	}

	@Override
	public Map<String, Object> findCommitLog(String commitLog) {
		// TODO Auto-generated method stub
		return commitLogDao.findCommitLog(commitLog);
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除rizhi
	 * @param params
	 * @return
	 */
	@Override
	public int deleteById(CommitLog commitLog) {
		return commitLogDao.removeCommitLog(commitLog);
	}

	/**
	 * @Title: deleteCommitLogs
	 * @Description: 批量删除rizhi
	 * @param params
	 * @return
	 */
	@Override
	public void deleteCommitLogs(String commitLogs) {
		String[] carinfoArray = commitLogs.split(",");
		CommitLog commitLog = new CommitLog();
		for (int i = 0; i < carinfoArray.length; i++) {
			commitLog.setCommitLog(carinfoArray[i]);
			commitLogDao.removeCommitLog(commitLog);
		}
	}

	/**
	 * 加入日志列表
	 * 
	 * @param logContent
	 *            操作内容
	 * @return
	 */
	@Override
	public void insert(String logContent) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		// 获取登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 请求的IP
		String ip = request.getRemoteAddr();
		CommitLog commitLog = new CommitLog();
		commitLog.setLogName(JudgeNullUtils.nvlStr(loginUser.getUserAccount()));
		commitLog.setIpAddress(ip);
		commitLog.setLogContent(logContent);
		commitLog.setCreateDate(new Date());
		commitLog.setUpdateDate(new Date());
		commitLog.setCoLoUserId(loginUser.getUserId().toString());
		commitLogDao.insert(commitLog);
		addHbaseLog(commitLog);
	}

	/**
	 * 添加日志到hbase
	 * @param commitLog
	 */
	private void addHbaseLog(CommitLog commitLog) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("logName", commitLog.getLogName());
		params.put("ipAddress", commitLog.getIpAddress());
		params.put("logContent", commitLog.getLogContent());
		params.put("coLoUserId", commitLog.getCoLoUserId());
		HttpRequest.post(GlobalSystem.getConfig("hbaseUrl")+"/addCommitLog", params);
	}
}
