package com.wanma.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wanma.common.SessionMgr;
import com.wanma.dao.CmsActivityMapper;
import com.wanma.dao.CmsCarVinMapper;
import com.wanma.dao.CmsCouponDetailMapper;
import com.wanma.model.TblActivity;
import com.wanma.model.TblCarVin;
import com.wanma.model.TblUser;
import com.wanma.service.CmsActivityService;

@Service
public class CmsActivityServiceImpl implements CmsActivityService {

	@Autowired
	private CmsActivityMapper cmsActivityMapper;
	@Autowired
	private CmsCarVinMapper cmsCarVinMapper;

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

		try {
			// 修改活动表状态及结束时间
			cmsActivityMapper.changeActStatus(params);
			// 修改优惠券表失效时间
			cmsCouponDetailMapper.changeEndDate(params);
		} catch (Exception e) {
			throw e;
		}
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
					obj.put("userId", 0);
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
		numbersAndLetters = ("123456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ")
				.toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(55)];
		}
		return new String(randBuffer);
	}

	public boolean addUserActivity(TblActivity tblActivity,
			HttpServletRequest request, ArrayList<String> userAccount) {
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
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				int num = 0;
				if(tblActivity.getActActivityrule()==7){//指定送活动 优惠劵数量要乘以用户人数
					num=item.getNum();
				item.setNum(item.getNum()*userAccount.size());	
				}
				item.setPkActivity(tblActivity.getPkActivity());
				// 活动附表添加n条数据
				cmsActivityMapper.addScheActivity(item);
				//指定送活动 增加优惠劵表
				if(tblActivity.getActActivityrule()==7){
					for (int i = 0; i < userAccount.size(); i++) {
						for(int j=0;j<num;j++){
							Map<String, Object> obj = new HashMap<String, Object>();
							obj.put("pkActivity", item.getPkActivity());
							obj.put("pkCouponVariety", item.getPkCouponVariety());
							obj.put("actActivityname", tblActivity.getActActivityname());
							obj.put("cpBeginDate", tblActivity.getActBegindates());
							obj.put("cpEndDate", tblActivity.getActCouponEndDates());
							obj.put("code", "");
							obj.put("userId", userAccount.get(i));
							list.add(obj);
						}
					}
					cmsActivityMapper.generateCode(list);
				}
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

	@Override
	public int checkCommonUnique(Map<String, String> map) {
		// TODO Auto-generated method stub
		return cmsActivityMapper.checkCommonUnique(map);
	}

	@Override
	public long getCarvinCount(TblCarVin tblCarVin) {
		// TODO Auto-generated method stub
		return cmsCarVinMapper.getCarvinCount(tblCarVin);
	}

	@Override
	public List<TblCarVin> getCarvinList(TblCarVin tblCarVin) {
		// TODO Auto-generated method stub
		return cmsCarVinMapper.getCarvinList(tblCarVin);
	}

	@Override
	public void deteleCarvin(String id) {
		cmsCarVinMapper.deteleCarvin(id);

	}

	@Override
	public void addCarVin(List<TblCarVin> pileList) {
		try {
			for (TblCarVin pile : pileList) {
				cmsCarVinMapper.addCarVin(pile);
			}

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<TblCarVin> getCarVinList_export(TblCarVin tblCarVin) {
		// TODO Auto-generated method stub
		return cmsCarVinMapper.getCarVinList_export(tblCarVin);
	}

	@Override
	public long checkVinCode(String vincode) {
		// TODO Auto-generated method stub
		return cmsCarVinMapper.checkVinCode(vincode);

	}

	@Override
	public HashMap<String, Object> getAllCouponCount(TblActivity activity) {
		return cmsActivityMapper.getAllCouponCount(activity);
	}

	@Override
	public HashMap<String, Object> getUsedCouponCount(TblActivity activity) {
		return cmsActivityMapper.getUsedCouponCount(activity);
	}

	@Override
	public HashMap<String, Object> getActDate(TblActivity activity) {
		return cmsActivityMapper.getActDate(activity);
	}

	@Override
	public Map<String, Object> splitDate(Date bgDate, Date edDate, int term,
			List<Map<String, Object>> dataList0,
			List<Map<String, Object>> dataList1) throws Exception {
		Map<String, Integer> data0 = new HashMap<String, Integer>();
		Map<String, Integer> data1 = new HashMap<String, Integer>();
		for (Map<String, Object> obj : dataList0)
			data0.put(obj.get("date").toString(),
					Integer.parseInt(obj.get("count").toString()));
		for (Map<String, Object> obj : dataList1)
			data1.put(obj.get("date").toString(),
					Integer.parseInt(obj.get("count").toString()));
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String nowStr = fmt.format(new Date());
		if (data0.get(nowStr) != null)
			data0.put(nowStr, 0);
		if (data1.get(nowStr) != null)
			data1.put(nowStr, 0);
		long bgTime = bgDate.getTime();
		long edTime = edDate.getTime();
		long difference = edTime - bgTime;
		int n = (int) (difference / (1000 * 3600 * 24)) + term;
		int n1 = (int) ((fmt.parse(fmt.format(new Date())+" 23:59:59").getTime() - bgTime) / (1000 * 3600 * 24));
		n = n >= n1 ? n1 - 1 : n;
		Calendar calendar = Calendar.getInstance();
		String[] ds = new String[n + 1];
		Integer[] vs0 = new Integer[n + 1];
		Integer[] vs1 = new Integer[n + 1];
		Integer valS0 = data0.get(fmt.format(bgDate));
		Integer valS1 = data1.get(fmt.format(bgDate));
		ds[0] = fmt.format(bgDate);
		if (valS0 == null)
			valS0 = 0;
		if (valS1 == null)
			valS1 = 0;
		vs0[0] = valS0;
		vs1[0] = valS1;
		for (int i = 1; i <= n; i++) {
			calendar.setTime(bgDate);
			calendar.add(Calendar.DATE, i);
			String dateStr = fmt.format(calendar.getTime());
			ds[i] = dateStr;
			Integer v0 = data0.get(dateStr);
			Integer v1 = data1.get(dateStr);
			if (v0 != null)
				valS0 += v0;
			if (v1 != null)
				valS1 += v1;
			vs0[i] = valS0;
			vs1[i] = valS1;
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("date", ds);// X轴
		json.put("val0", vs0);// Y轴
		json.put("val1", vs1);// Y轴
		return json;
	}

	@Override
	public void updateRepeatVinCode(TblCarVin tblCarVin) {
		cmsCarVinMapper.updateRepeatVinCode(tblCarVin);
	}

	@Override
	public TblCarVin findOne(TblCarVin tblCarVin) {
		return cmsCarVinMapper.findOne(tblCarVin);
	}



	@Override
	public String getUserIdByUseraccount(String userAccount) {
		return cmsActivityMapper.getUserIdByUseraccount(userAccount);
	}





}
