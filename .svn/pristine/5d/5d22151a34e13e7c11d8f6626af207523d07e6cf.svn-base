package com.wanma.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.common.SessionMgr;
import com.pub.model.TblUser;
import com.wanma.dao.CmsActivityMapper;
import com.wanma.dao.CmsCouponDetailMapper;
import com.wanma.model.TblActivity;
import com.wanma.service.CmsActivityService;

@Service
public class CmsActivityServiceImpl implements CmsActivityService {

	@Autowired
	private CmsActivityMapper cmsActivityMapper;

	@Autowired
	private CmsCouponDetailMapper cmsCouponDetailMapper;

	@Override
	public long getActivityCount(TblActivity tblActivity) {
		
		return cmsActivityMapper.getActivityCount(tblActivity);
	}

	@Override
	public List<TblActivity> getActivityList(TblActivity tblActivity) {
		
		return cmsActivityMapper.getActivityList(tblActivity);
	}

	@Override
	public void modifyActStatus(Map<String, Object> params) {
		// 修改活动表状态及结束时间
		cmsActivityMapper.changeActStatus(params);
		// 修改优惠券表失效时间
	//	cmsCouponDetailMapper.changeEndDate(params);
	}

	public boolean addActivity(TblActivity tblActivity,
			HttpServletRequest request) {
		try {

			TblUser loginUser = SessionMgr.getWebUser(request);

			List<TblActivity> headList = tblActivity.getHeadList();

			tblActivity.setActCreateuserid(loginUser.getUserAccount());
			tblActivity.setActUpdateuserid(loginUser.getUserAccount());

			tblActivity.setActType(2);
			// 活动主表添加一条记录
			cmsActivityMapper.addMainActivity(tblActivity);

			// 根据优惠券数量，添加优惠券明细
			for (TblActivity item : headList) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				item.setPkActivity(tblActivity.getPkActivity());
				// 活动附表添加n条数据
				cmsActivityMapper.addScheActivity(item);

				for (int i = 0; i < item.getNum(); i++) {
					Map<String, Object> obj = new HashMap<String, Object>();
					String code = getRandomKey(5);
					obj.put("pkActivity", item.getPkActivity());
					obj.put("code", code);
					obj.put("pkCouponVariety", item.getPkCouponVariety());
					obj.put("actActivityname", tblActivity.getActActivityname());
					obj.put("cpBeginDate", tblActivity.getActEnddates());
					obj.put("cpEndDate", tblActivity.getActEnddates());
					list.add(obj);

				}
				cmsActivityMapper.generateCode(list);

			}

		} catch (Exception e) {
			throw e;

		}
		return true;
	}

	public static String getRandomKey(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;
		if (length < 1) {
			return null;
		}

		randGen = new Random();
		numbersAndLetters = ("123456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ").toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(55)];
		}
		return new String(randBuffer);
	}

	public boolean addUserActivity(TblActivity tblActivity,
			HttpServletRequest request) {
		try {
			TblUser loginUser = SessionMgr.getWebUser(request);
			List<TblActivity> headList = tblActivity.getHeadList();

			tblActivity.setActCreateuserid(loginUser.getUserAccount());
			tblActivity.setActUpdateuserid(loginUser.getUserAccount());
			tblActivity.setActType(1);
			// 活动主表添加一条记录
			cmsActivityMapper.addMainActivity(tblActivity);

			// 根据优惠券数量，添加优惠券明细
			for (TblActivity item : headList) {
				item.setPkActivity(tblActivity.getPkActivity());
				// 活动附表添加n条数据
				cmsActivityMapper.addScheActivity(item);

			}

		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	@Override
	public long checkActUnique(TblActivity tblActivity) {
		return cmsActivityMapper.checkActUnique(tblActivity);
	}

	@Override
	public List<TblActivity> getChannelType() {
		// TODO Auto-generated method stub
		return cmsActivityMapper.getChannelType();
	}

}
