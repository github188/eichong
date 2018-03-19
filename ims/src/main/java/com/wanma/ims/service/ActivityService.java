package com.wanma.ims.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.wanma.ims.common.domain.ActivityDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;


/**
 * 活动
 */
public interface ActivityService {
	/**
	 * 下拉栏活动
	 * @param activity
	 * @return
	 */
	List<ActivityDO> getActivityForList(ActivityDO activity);
	/**
	 * 活动列表数量
	 * @param activity
	 * @return
	 */
	long getActivityCount(ActivityDO activity);
	/**
	 * 活动列表
	 * @param activity
	 * @return
	 */
	List<ActivityDO> getActivityList(ActivityDO activity);
	/**
	 * 终止活动
	 * @author mb
	 * @param activity
	 * @return
	 */
	void modifyActStatus(ActivityDO activity);
	/**
	 * 新增活动
	 * @author mb
	 * @param activity
	 * @param headList 
	 * @return
	 */
	void addActivity(ActivityDO activity);
	/**
	 * 检查唯一性
	 * @author mb
	 * @param activity
	 * @return
	 */
	long checkActUnique(ActivityDO activity);
	/**
	 * 新增用户活动
	 * @author mb
	 * @param activity
	 * @return
	 */
	BaseResultDTO addUserActivity(ActivityDO activity, MultipartFile file,
			UserDO loginUser);
	/**
	 * 筛选框 城市通用 范围
	 * @author mb
	 */
	List<Map<String, String>> getCityScope();
	/**
	 * 筛选框  活动组织方/优惠券发行方/成本承担方
	 * @author mb
	 * @param activity 
	 */
	List<Map<String, String>> getCpyForList(ActivityDO activity);
	
	
	

}
