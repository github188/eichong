package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.TblFeedbackMapper;
import com.wanma.app.service.TblFeedbackService;
import com.wanma.model.TblFeedback;

/**
 * @ClassName: TblFeedbackServiceImpl
 * @Description: 意见反馈服务实现类
 * @author chenb
 * @date 2015年3月15日 下午6:39:51
 */
@Service
public class TblFeedbackServiceImpl implements TblFeedbackService {
	/** 意见反馈业务操作DAO */
	@Autowired
	private TblFeedbackMapper tblFeedbackMapper;

	@Override
	public void addTblFeedbac(TblFeedback tblFeedbac) {
		tblFeedbackMapper.insert(tblFeedbac);
	}
	
	/**
	 * 获取接收到的反馈列表
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getMyFeedbackList(Map<String, Object> params) {
		
		return tblFeedbackMapper.getMyFeedbackList(params);
	}

	@Override
	public Map<String, Object> getMyFeedbackContent(int feedbackId) {
		
		return tblFeedbackMapper.getMyFeedbackContent(feedbackId);
	}

}
