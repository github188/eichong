package com.echong.service.impl;

import com.alibaba.fastjson.JSON;
import com.ec.usrcore.server.CommonServer;
import com.echong.constant.CommonConsts;
import com.echong.constant.OperatelLabelConsts;
import com.wanma.model.PileState;
import com.echong.model.ElectricLabel;
import com.echong.model.Result;
import com.echong.service.OperateLabelService;
import com.echong.utils.HttpUtils;
import com.wanma.dao.TblElectricpileMapper;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zangyaoyi on 2017/1/5.
 */
@Service
public class OperateLabelServiceImpl implements OperateLabelService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OperateLabelServiceImpl.class);
	private static CommonServer commonServer = CommonServer.getInstance();
	@Autowired
	private TblElectricpileMapper tblElectricpileMapper;

	@Override
	public Result startOperate(ElectricLabel electricLabel) {
		short chargeStyle = 1;
		String userId = null == electricLabel.getUser_id() ? "" : electricLabel
				.getUser_id().toString();
		String sessionId = null == electricLabel.getSession_id() ? ""
				: electricLabel.getSession_id().toString();
		int ret = 1;
		try {
			ret = commonServer.startChange(WanmaConstants.ECW_CODE, userId,
					electricLabel.getPile_code(), electricLabel.getInter_no(),
					chargeStyle, 50000, 2, "", "", 0, sessionId);
		} catch (Exception e) {
			LOGGER.error(
					"commonServer.startChange is fail ; electricLabel:{}|e:{}",
					JSON.toJSON(electricLabel), e);
		}
		LOGGER.info("OperateLabelService startOperate is end ,ret:{}", ret);
		return getResult(ret);
	}

	@Override
	public Result stopOperate(ElectricLabel electricLabel) {
		int ret = 1;
		try {
			ret = commonServer.stopChange(electricLabel.getPile_code(),
					electricLabel.getInter_no(), WanmaConstants.ECW_CODE,
					electricLabel.getUser_id().toString(), electricLabel
							.getSession_id().toString());
		} catch (Exception e) {
			LOGGER.error(
					"commonServer.stopOperate is fail ; electricLabel:{}|e:{}",
					JSON.toJSON(electricLabel), e);
		}
		LOGGER.info("OperateLabelService stopOperate is end,ret:{}", ret);
		return getResult(ret);
	}

	@Override
	public void queryOffLinePile(Integer companyOrg, Integer stationID) {
		List<PileState> pileStates = tblElectricpileMapper
				.queryOffLinePileByCompanyNumber(companyOrg, 979);
		LOGGER.info(
				"OperateLabelService queryOffLinePile is begin,pileStates.length:{}",
				pileStates.size());
		Map<String, String> errorMap = new HashMap<>();
		Set<String> onlineMap = new HashSet<>();
		for (PileState pileState : pileStates) {
			PileState.convertPileState(pileState);
			if (7 == pileState.getInter_work_state()) {
				onlineMap.add(pileState.getPile_code());
			}
			String result = HttpUtils.send2EChong(
					CommonConsts.E_CHONG_STATUS_CHANGE_URL,
					JSON.toJSONString(pileState));
			if (!result.substring(result.indexOf("ret") + 5,
					result.indexOf("msg") - 2).equals("0")) {
				errorMap.put(pileState.getPile_code(), result);
			}
		}
		LOGGER.error(
				"queryOffLinePile is end;count:{}|size:{}|info:{}|online size:{}|online:{}",
				pileStates.size(), errorMap.size(), JSON.toJSON(errorMap),
				onlineMap.size(), onlineMap);

	}

	private static Result getResult(int ret) {
		Result result = new Result();
		if (ret > 0) {
			result.setRet("6001");
			result.setMsg("系统错误:" + OperatelLabelConsts.getMeg(ret));
			LOGGER.error("OperateLabelService is fail,errorCode:{}", ret);
		}
		return result;
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("aaaaaaaa", "asdasd:asdasd");
		LOGGER.error("queryOffLinePile is fail; size:{}|info:{}", map.size(),
				JSON.toJSON(map));
	}
}
