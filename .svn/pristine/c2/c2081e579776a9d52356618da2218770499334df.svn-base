package com.wanma.ims.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wanma.ims.common.domain.LogCommitDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.mapper.LogCommitMapper;
import com.wanma.ims.service.LogCommitService;
import com.wanma.ims.util.ObjectUtil;

@Service
public class LogCommitServiceImpl implements LogCommitService{

	@Autowired
	private LogCommitMapper logCommitMapper;

	@Override
	public List<LogCommitDO> getLogCommitList(LogCommitDO logCommitDO) {
		return logCommitMapper.selectLogCommitList(logCommitDO);
	}

	@Override
	public Integer countLogCommit(LogCommitDO logCommitDO) {
		return logCommitMapper.countLogCommitList(logCommitDO);
	}

	@Override
	public LogCommitDO getLogCommitById(Long logId) {
		return logCommitMapper.selectLogCommitById(logId);
	}

	@Override
	public boolean addLogCommit(LogCommitDO logCommitDO) {
		return logCommitMapper.insertLogCommit(logCommitDO) > 0 ?true:false;
	}

	@Override
	public boolean removeLogCommitById(Long logId) {
		return logCommitMapper.deleteLogCommitById(logId) > 0 ?true:false;
	}

	@Override
	public void addHbaseLogCommit(String logContent, UserDO loginUser) {
		 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	        // 请求的IP
	        String ip = request.getRemoteAddr();
	        LogCommitDO logCommit = new LogCommitDO();
	        logCommit.setLogName(ObjectUtil.nvlStrEmpty(loginUser.getUserAccount()));
	        logCommit.setLogIpAddress(ip);
	        logCommit.setLogContent(logContent);
	        logCommit.setIsDel(0);
	        logCommit.setCreatorId(loginUser.getUserId());
	        logCommit.setGmtCreate(new Date());
	        logCommit.setGmtModified(new Date());
	        logCommitMapper.insertLogCommit(logCommit);
//	        addHbaseLog(logCommit);
	}
	/**
     * 添加日志到hbase
     */
//    private void addHbaseLog(LogCommitDO logCommitDO) {
//        Map<String, String> params = new HashMap<String, String>();
//        GlobalSystem.putConfig("logName", logCommitDO.getLogName());
//        GlobalSystem.putConfig("logIpAddress", logCommitDO.getLogIpAddress());
//        GlobalSystem.putConfig("logContent", logCommitDO.getLogContent());
//        GlobalSystem.putConfig("creatorId", String.valueOf(logCommitDO.getCreatorId()));
//        HttpRequestUtil.post(GlobalSystem.getConfig("hbaseUrl") + "/addLogCommit", params);
//    }
}
