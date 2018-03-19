package com.wanma.app.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.WebTblUserMapper;
import com.wanma.app.dao.WebPurchasehistoryMapper;
import com.wanma.app.service.GovBusiPayService;
import com.wanma.model.TblPurchasehistory;
import com.wanma.model.TblUser;
import com.wanma.app.dao.GovBusiRecordMapper;
import com.wanma.app.service.GovBusiRecordService;

/**
 * @author gx
 * @Description: 用户信息业务处理类
 * @createTime：2017-10-14 上午10:31:56
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class GovBusiRecordServiceImpl implements GovBusiRecordService {
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * 用户信息业务操作DAO
	 */
	@Autowired
	private GovBusiRecordMapper mapper;

	@Override
	public List<Map<String, Object>> getorder(Map<String, Object> params) {
		return mapper.getorder(params);
	}

	@Override
	public List<Map<String, Object>> getbill(Map<String, Object> params) {
		return mapper.getbill(params);
	}

}
