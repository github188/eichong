package com.wanma.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wanma.model.TblPartner;
import com.wanma.service.CommonService;
import com.wanma.service.TcbPartnerService;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.CecPost;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private TcbPartnerService partnerService;
	@Autowired
	private RedisService redisService;
	private static final Logger log = LoggerFactory
			.getLogger(CommonServiceImpl.class);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, String> getCecToken(String operatorID) {
		String eichongperatorID = "321895837";
		Map<String, String> map = new HashMap<>();
		try {
			String tkVal = redisService.strGet(WanmaConstants.THIRD_TONKEN
					+ operatorID);

			if (tkVal != null) {
				JSONObject jasonObject = JSONObject.parseObject(tkVal);
				map = (Map) jasonObject;

			} else {
				TblPartner partner = partnerService.PartnerInfo(operatorID);

				Map<String, Object> mapl = new HashMap<>();
				mapl.put("OperatorID", eichongperatorID);
				mapl.put("OperatorSecret", partner.getThirdTokenSecret());

				JSONObject obiects = CecPost.HttpPost(
						partner.getThirdTokenUrl(), eichongperatorID, null,
						partner.getSigSecret(),
						new JSONObject(mapl).toString(),
						partner.getAesSecret(), partner.getAesIv());
				String tokenAvailableTime = obiects.get("TokenAvailableTime")
						.toString();
				if (Long.parseLong(tokenAvailableTime) > 100) {
					redisService.setKeyLiveTime(WanmaConstants.THIRD_TONKEN
							+ operatorID, obiects.toString(),
							Long.parseLong(tokenAvailableTime) - 100,
							TimeUnit.SECONDS);
					map = (Map) obiects;
				}
			}

		} catch (Exception e) {
			log.error("获取token失败！");
		}

		return map;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, String> getShPakingToken(String operatorID) {
		String eichongperatorID = "MA27W7H33";
		Map<String, String> map = new HashMap<>();
		try {
			String tkVal = redisService.strGet(WanmaConstants.THIRD_TONKEN
					+ operatorID);

			if (tkVal != null) {
				JSONObject jasonObject = JSONObject.parseObject(tkVal);
				map = (Map) jasonObject;

			} else {
				TblPartner partner = partnerService.PartnerInfo(operatorID);
				Map<String, Object> mapl = new HashMap<>();
				mapl.put("OperatorID", eichongperatorID);
				mapl.put("OperatorSecret", partner.getThirdTokenSecret());

				JSONObject obiects = CecPost.HttpPost(
						partner.getThirdTokenUrl(), eichongperatorID, null,
						partner.getSigSecret(),
						new JSONObject(mapl).toString(),
						partner.getAesSecret(), partner.getAesIv());
				String tokenAvailableTime = obiects.get("TokenAvailableTime")
						.toString();
				if (Long.parseLong(tokenAvailableTime) > 100) {
					redisService.setKeyLiveTime(WanmaConstants.THIRD_TONKEN
							+ operatorID, obiects.toString(),
							Long.parseLong(tokenAvailableTime) - 100,
							TimeUnit.SECONDS);
					map = (Map) obiects;
				}
			}

		} catch (Exception e) {
			log.error("获取上海停车办token失败！");
		}

		return map;
	}

}
