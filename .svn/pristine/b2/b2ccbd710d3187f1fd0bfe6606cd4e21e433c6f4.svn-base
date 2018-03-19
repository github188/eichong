package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.FavCouponDO;
import com.wanma.ims.common.domain.IntegralActivityDO;
import com.wanma.ims.common.domain.IntegralDO;
import com.wanma.ims.common.domain.IntegralDetailsDO;
import com.wanma.ims.common.domain.OrderDO;
import com.wanma.ims.common.domain.UserNormalDO;
import com.wanma.ims.common.domain.bo.IntegralActivityAndRulesBO;
import com.wanma.ims.common.domain.bo.IntegralBO;
import com.wanma.ims.common.domain.bo.IntegralResultBO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.ElectricPileMapper;
import com.wanma.ims.mapper.FavCouponMapper;
import com.wanma.ims.mapper.IntegralActivityMapper;
import com.wanma.ims.mapper.IntegralDetailsMapper;
import com.wanma.ims.mapper.IntegralMapper;
import com.wanma.ims.mapper.UserNormalMapper;
import com.wanma.ims.service.IntegralDetailsService;
import com.wanma.ims.util.DateUtil;

@Service("integralDetailsService")
public class IntegralDetailsServiceImpl implements IntegralDetailsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private IntegralDetailsMapper integralDetailsMapper;
	
	@Autowired
    private IntegralMapper integralMapper;
	
	@Autowired
    private IntegralActivityMapper integralActivityMapper;
	
	@Autowired
    private UserNormalMapper userNormalMapper;
	
	@Autowired
    private FavCouponMapper favCouponMapper;

	@Autowired
	private ElectricPileMapper electricPileMapper;

	@Override
	public Long getIntegralDetailsCount(IntegralDetailsDO integralDetails) {
		return integralDetailsMapper.getIntegralDetailsCount(integralDetails);
	}
	
	@Override
	public List<IntegralDetailsDO> getIntegralDetailsList(IntegralDetailsDO integralDetails) {
		return integralDetailsMapper.getIntegralDetailsList(integralDetails);
	}

	/**
	* <p>Description: 积分明细接口</p>
	* @param integralBO：积分临时对象
	* 		 defaultActivity：积分活动和规则临时对象
	* @author bingo
	* @date 2017-8-15
	 */
	@Override
	@Transactional
	public BaseResultDTO addIntegralDetails(IntegralBO integralBO, IntegralActivityAndRulesBO defaultActivity) throws Exception {
		BaseResultDTO baseResultDTO = new BaseResultDTO();
		IntegralResultBO integralResultBO = new IntegralResultBO();
		if (integralBO == null) {
			log.error(this.getClass() + ".addIntegralDetails() error : 积分对象不允许为空！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("积分对象不允许为空！");
			return baseResultDTO;
		}
		
		if (integralBO.getIntegralActivityId() == null) {
			log.error(this.getClass() + ".addIntegralDetails() error : 积分活动不允许为空！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("积分活动不允许为空！");
			return baseResultDTO;
		}
		
		if (integralBO.getDirection() == null) {
			log.error(this.getClass() + ".addIntegralDetails() error : 积分变化方向不允许为空！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("积分变化方向不允许为空！");
			return baseResultDTO;
		}
		
		if (integralBO.getUserId() == null) {
			log.error(this.getClass() + ".addIntegralDetails() error : 用户Id不允许为空！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("用户Id不允许为空！");
			return baseResultDTO;
		}
		UserNormalDO user = userNormalMapper.selectUserNormalByUserId(integralBO.getUserId());
		if (user == null) {
			log.error(this.getClass() + ".addIntegralDetails() error : 用户不存在");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("用户不存在");
			return baseResultDTO;
		}

		int direction = integralBO.getDirection();//积分方向（0：获取；1：消耗）
		Long integralActivityId = integralBO.getIntegralActivityId();//积分活动Id
		int integralActivityIdToInt = integralActivityId.intValue();
		Long electricPileId = integralBO.getElectricPileId();//桩
		Long integralValue = 0l;//积分
		int choiceCount = 0;//抽奖次数
		String chargingOrderId = ""; //充电消费订单Id
		Double moneyInvolved = integralBO.getMoneyInvolved();//金额
		Date completeDate = integralBO.getCompleteDate(); //交易完成时间
		List<IntegralActivityAndRulesBO> integralActivityList = new ArrayList<IntegralActivityAndRulesBO>();
		
		//取相应的积分活动
		IntegralActivityAndRulesBO integralActivity;
		if (defaultActivity != null && defaultActivity.getPkId() != null) {
			integralActivity = defaultActivity;
		}else{
			/****** 查询积分活动由于区域存在，且区域可能维护是"全国"，判断的逻辑不能只判断到区域点，需要再判断"是否全国有效"这个标识  ******/
			IntegralActivityDO qryIntegralActivity = new IntegralActivityDO();
			qryIntegralActivity.setPkId(integralActivityId);
			qryIntegralActivity.setActivityStatus(WanmaConstants.INTEGRAL_ACTIVITY_STATUS_OPEN);
			qryIntegralActivity.setIntegralDate(integralBO.getCompleteDate());
			if (electricPileId != null) {
				qryIntegralActivity.setElectricPileId(electricPileId);
			}
			integralActivityList = integralActivityMapper.getIntegralActivityAndRulesList(qryIntegralActivity);
			if (integralActivityList == null || integralActivityList.size() == 0) {
				//查询是否全国有效
				qryIntegralActivity = new IntegralActivityDO();
				qryIntegralActivity.setPkId(integralActivityId);
				qryIntegralActivity.setActivityStatus(WanmaConstants.INTEGRAL_ACTIVITY_STATUS_OPEN);
				qryIntegralActivity.setIntegralDate(integralBO.getCompleteDate());
				qryIntegralActivity.setIsWhole(1);
				integralActivityList = integralActivityMapper.getIntegralActivityAndRulesList(qryIntegralActivity);
				
				if (integralActivityList == null || integralActivityList.size() == 0) {
					String errorMsg = "积分活动Id不存在或不在积分活动的有效时间范围内！";
					if (electricPileId != null) {
						errorMsg = "没有查找到电桩Id对应的积分活动！";
					}
					log.error(this.getClass() + ".addIntegralDetails() error : " + errorMsg);
					baseResultDTO.setSuccess(false);
					baseResultDTO.setErrorDetail(errorMsg);
					return baseResultDTO;
				}
			}
			
			integralActivity = integralActivityList.get(0);
			if (integralActivity.getDirection().intValue() != direction) {
				log.error(this.getClass() + ".addIntegralDetails() error : 积分变化方向错误！");
				baseResultDTO.setSuccess(false);
				baseResultDTO.setErrorDetail("积分变化方向错误！");
				return baseResultDTO;
			}
		}

		//用户积分记录
		IntegralDO qryIntegral = new IntegralDO();
		qryIntegral.setUserId(integralBO.getUserId());
		List<IntegralDO> integralList = integralMapper.getIntegralList(qryIntegral);
		if (integralList == null || integralList.size() == 0) {
			//如果是消耗,需要先判断用户是否有积分
			if(direction == 1) {
				log.error(this.getClass() + ".addIntegralDetails() error : 用户无积分记录！");
				baseResultDTO.setSuccess(false);
				baseResultDTO.setErrorDetail("用户无积分记录！");
				return baseResultDTO;
			}
		}
		
		Date startDate = integralActivity.getStartDate();//活动开始日期
		Date endDate = integralActivity.getEndDate();//活动结束日期
		if (completeDate.getTime() < startDate.getTime() || completeDate.getTime() > endDate.getTime()) {
			log.error(this.getClass() + ".addIntegralDetails() error : 不在积分活动的有效时间范围内");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("不在积分活动的有效时间范围内");
			return baseResultDTO;
		}

		if (direction == 0) {
			/************************************** 获取积分逻辑start **************************************/
			if (integralActivityIdToInt == WanmaConstants.INTEGRAL_RECHARGE
					|| integralActivityIdToInt == WanmaConstants.INTEGRAL_CONSUME ) {
				/****** 充电/消费赠送积分、抽奖机会 ******/
				if (integralBO.getCompleteDate() == null) {
					log.error(this.getClass() + ".addIntegralDetails() error : 交易完成时间不允许为空！");
					baseResultDTO.setSuccess(false);
					baseResultDTO.setErrorDetail("交易完成时间不允许为空！");
					return baseResultDTO;
				}
				
				if (integralBO.getMoneyInvolved() == null) {
					log.error(this.getClass() + ".addIntegralDetails() error : 金额不允许为空！");
					baseResultDTO.setSuccess(false);
					baseResultDTO.setErrorDetail("金额不允许为空！");
					return baseResultDTO;
				}
				
				Long fixedIntegralValue = integralActivity.getFixedIntegralValue();
				Long ratioIntegralValue = integralActivity.getRatioIntegralValue();
				Long choiceMoney = integralActivity.getChoiceMoney();
				
				if (integralActivityIdToInt == 2) {//消费
					if(integralActivity.getMinValue() != null  && integralActivity.getMinValue().doubleValue() > moneyInvolved){
						log.error(this.getClass() + ".addIntegralDetails() error : 充电消费金额小于系统设置的阈值金额");
						baseResultDTO.setSuccess(false);
						baseResultDTO.setErrorDetail("充电消费金额小于系统设置的阈值金额");
						return baseResultDTO;
					}
					
					chargingOrderId = integralBO.getChargingOrderId();
				}
				
				//积分值
				if (fixedIntegralValue != null && !fixedIntegralValue.equals(0L)) {
					integralValue = fixedIntegralValue;
				}else {
					if (ratioIntegralValue == null || ratioIntegralValue.equals(0L)) {
						log.error(this.getClass() + ".addIntegralDetails() error : 充值或充电活动规则中固定分值和按比例必须维护一种！");
					}else{
						integralValue = new Long(((Double)(moneyInvolved / ratioIntegralValue.doubleValue())).intValue());
					}
				}

				//消费送的积分必须小于等于阀值积分
				if(integralActivityIdToInt == 2 && integralValue  > integralActivity.getLimitIntegral()) {
					integralValue = integralActivity.getLimitIntegral();
				}
				
				//抽奖次数
				if (integralActivity.getIsChoice() != null && integralActivity.getIsChoice().intValue() == 0) {
					if (choiceMoney != null && !choiceMoney.equals(0L)) {
						choiceCount = ((Double)(moneyInvolved / choiceMoney.doubleValue())).intValue();
					}
				}
			}else if (integralActivityIdToInt == WanmaConstants.INTEGRAL_RECEIVE) {
				//判断是否已经领取过了
				IntegralDetailsDO qryIntegralDetailsDO = new IntegralDetailsDO();
				qryIntegralDetailsDO.setUserId(integralBO.getUserId());
				qryIntegralDetailsDO.setIntegralDate(integralBO.getCompleteDate());
				qryIntegralDetailsDO.setIntegralActivityId(integralBO.getIntegralActivityId());
				Long count = integralDetailsMapper.getIntegralDetailsNum(qryIntegralDetailsDO);
				if (count != null && count > 0L) {
					log.error(this.getClass() + ".addIntegralDetails() error : 积分已领取");
					baseResultDTO.setSuccess(false);
					baseResultDTO.setErrorDetail("积分已领取");
					return baseResultDTO;
				}

				//每日领取
				integralValue = integralActivity.getFixedIntegralValue();

				//如果节假日，用节假日积分
				IntegralActivityDO qryIntegralActivity = new IntegralActivityDO();
				qryIntegralActivity.setPkId(WanmaConstants.INTEGRAL_HOLIDAY.longValue());
				qryIntegralActivity.setActivityStatus(WanmaConstants.INTEGRAL_ACTIVITY_STATUS_OPEN);
				integralActivityList = integralActivityMapper.getIntegralActivityAndRulesList(qryIntegralActivity);
				if (integralActivityList != null && integralActivityList.size() > 0) {
					integralActivity = integralActivityList.get(0);
					if (integralActivity.getStartDate().getTime() <= integralBO.getCompleteDate().getTime()
							&& integralBO.getCompleteDate().getTime() <= integralActivity.getEndDate().getTime()) {							
						integralValue = integralActivity.getFixedIntegralValue();
					}
				}
				
				//如果生日当天，用生日积分
				if (user.getNormBirthday().equals(DateUtil.format(integralBO.getCompleteDate()))) {
					qryIntegralActivity = new IntegralActivityDO();
					qryIntegralActivity.setPkId(WanmaConstants.INTEGRAL_BIRTHDAY.longValue());
					qryIntegralActivity.setActivityStatus(WanmaConstants.INTEGRAL_ACTIVITY_STATUS_OPEN);
					integralActivityList = integralActivityMapper.getIntegralActivityAndRulesList(qryIntegralActivity);
					if (integralActivityList != null && integralActivityList.size() > 0) {
						integralActivity = integralActivityList.get(0);
						if (integralActivity.getStartDate().getTime() <= integralBO.getCompleteDate().getTime()
								&& integralBO.getCompleteDate().getTime() <= integralActivity.getEndDate().getTime()) {	
							integralValue = integralActivity.getFixedIntegralValue();
						}
					}
				}
			}else if (integralActivityIdToInt == WanmaConstants.INTEGRAL_MODIFY) {
				//判断该用户是否已经有完善资料
				IntegralDetailsDO qryIntegralDetailsDO = new IntegralDetailsDO();
				qryIntegralDetailsDO.setUserId(integralBO.getUserId());
				qryIntegralDetailsDO.setIntegralActivityId(integralBO.getIntegralActivityId());
				Long count = integralDetailsMapper.getIntegralDetailsNum(qryIntegralDetailsDO);
				if (count != null && count > 0L) {
					log.error(this.getClass() + ".addIntegralDetails() error : 完善资料已赠送过积分");
					baseResultDTO.setSuccess(false);
					baseResultDTO.setErrorDetail("完善资料已赠送过积分");
					return baseResultDTO;
				}
				
				//修改资料
				integralValue = integralActivity.getFixedIntegralValue();
			}else if (integralActivityIdToInt == WanmaConstants.INTEGRAL_SHARE) {
				/****** 分享赠送积分、抽奖机会 ******/
				chargingOrderId = integralBO.getChargingOrderId();
				Integer isShareChoice = integralActivity.getIsShareChoice();

				if(chargingOrderId == null){
					log.error(this.getClass() + ".addIntegralDetails() error : 关联订单Id不能为空");
					baseResultDTO.setSuccess(false);
					baseResultDTO.setErrorDetail("关联订单Id不能为空");
					return baseResultDTO;
				}

				if(isShareChoice == null){
					//充电消费分享不赠送积分、抽奖机会
					log.error(this.getClass() + ".addIntegralDetails() error : 充电消费分享不赠送积分、抽奖机会");
					baseResultDTO.setSuccess(false);
					baseResultDTO.setErrorDetail("充电消费分享不赠送积分、抽奖机会");
					return baseResultDTO;
				}else if (isShareChoice.equals(WanmaConstants.INTEGRAL_ACTIVITY_SHARE_ONLY_ONE)) {//首次分享
					//查找是否已经有该订单分享的积分明细
					IntegralDetailsDO qryIntegralDetails = new IntegralDetailsDO();
					qryIntegralDetails.setIntegralActivityId(WanmaConstants.INTEGRAL_SHARE.longValue());
					qryIntegralDetails.setChargingOrderId(integralBO.getChargingOrderId());
					Long result = integralDetailsMapper.getIntegralDetailsCount(qryIntegralDetails);
					if (result != null && result.intValue() > 0) {
						log.error(this.getClass() + ".addIntegralDetails() error : 已经赠送过积分");
						baseResultDTO.setSuccess(false);
						baseResultDTO.setErrorDetail("已经赠送过积分");
						return baseResultDTO;
					}else{
						integralValue = integralActivity.getShareIntegralValue();
						
						Integer shareChoice = integralActivity.getShareChoice();
						if (shareChoice != null && shareChoice.equals(0)) {
							choiceCount = 1;
						}
					}
				}else if (isShareChoice.equals(WanmaConstants.INTEGRAL_ACTIVITY_SHARE_EVERY)) {//每次分享
					integralValue = integralActivity.getShareIntegralValue();
					
					Integer shareChoice = integralActivity.getShareChoice();
					if (shareChoice != null && shareChoice.equals(0)) {
						choiceCount = 1;
					}
				}
			}
			/************************************** 获取积分逻辑end **************************************/
		}else{
			/************************************** 消耗积分逻辑start **************************************/
			if (integralBO.getIntegralValue() == null || integralBO.getIntegralValue().equals(0L)) {
				log.error(this.getClass() + ".addIntegralDetails() error : 消耗积分值不能为空");
				baseResultDTO.setSuccess(false);
				baseResultDTO.setErrorDetail("消耗积分值不能为空");
				return baseResultDTO;
			}
			
			qryIntegral = new IntegralDO();
			qryIntegral.setUserId(integralBO.getUserId());
			IntegralDO resultIntegral = integralMapper.getIntegralById(qryIntegral);
			if (resultIntegral == null) {
				log.error(this.getClass() + ".addIntegralDetails() error : 用户没有可用积分");
				baseResultDTO.setSuccess(false);
				baseResultDTO.setErrorDetail("用户没有可用积分");
				return baseResultDTO;
			}
			
			if (resultIntegral.getAvailableIntegrals() < integralBO.getIntegralValue()) {
				log.error(this.getClass() + ".addIntegralDetails() error : 用户积分不足");
				baseResultDTO.setSuccess(false);
				baseResultDTO.setErrorDetail("用户积分不足");
				return baseResultDTO;
			}
			
			integralValue = integralBO.getIntegralValue();
			if (integralActivityIdToInt == WanmaConstants.INTEGRAL_CHANGE) {
				//积分兑换
				FavCouponDO favCouponDO = new FavCouponDO();
				favCouponDO.setCpUserid(integralBO.getUserId().intValue());
				favCouponDO.setCpCouponcode(integralBO.getCpCouponcode());
				favCouponDO.setCpUpdatedate(DateUtil.getNow());
				int result = favCouponMapper.modifyCoupon(favCouponDO);
				if (result == 0) {
					log.error(this.getClass() + ".addIntegralDetails() error : 用户绑定积分兑换的优惠券失败");
					baseResultDTO.setSuccess(false);
					baseResultDTO.setErrorDetail("用户绑定积分兑换的优惠券失败");
					return baseResultDTO;
				}
			}else if (integralActivityIdToInt == WanmaConstants.INTEGRAL_LOTTERY_DRAW) {
				//积分抽奖
				//积分扣减动作，无其它处理
			}
			/************************************** 消耗积分逻辑end **************************************/
		}

		//处理总积分
		Long integralId = 0L;//总积分Id
		if (integralList == null || integralList.size() == 0) {
			IntegralDO integral = new IntegralDO();
			integral.setUserId(integralBO.getUserId());
			integral.setTotalIntegrals(integralValue);
			integral.setAvailableIntegrals(integralValue);
			integral.setUsedIntegrals(0l);
			integral.setCreator(integralBO.getCreator() == null ? "" : integralBO.getCreator());
			integral.setGmtCreate(new Date());
			integralMapper.addIntegral(integral);
			integralId = integral.getPkId();
			if (integralId == 0L) {
				log.error(this.getClass() + ".addIntegralDetails() error : 增加积分明细时，增加总积分失败！");
				baseResultDTO.setSuccess(false);
				baseResultDTO.setErrorDetail("增加积分明细时，增加总积分失败！");
				return baseResultDTO;
			}
		}else {
			IntegralDO integral = integralList.get(0);
			integralId = integral.getPkId();
			if (direction == 0) {
				integral.setTotalIntegrals(integral.getTotalIntegrals() + integralValue);
				integral.setAvailableIntegrals(integral.getAvailableIntegrals() + integralValue);
			}else if (direction == 1){
				if(integral.getAvailableIntegrals() < integralValue){
					log.error(this.getClass() + ".addIntegralDetails() error : 用户积分不足！");
					baseResultDTO.setSuccess(false);
					baseResultDTO.setErrorDetail("用户积分不足！");
					return baseResultDTO;
				}
				integral.setAvailableIntegrals(integral.getAvailableIntegrals() - integralValue);
				integral.setUsedIntegrals(integral.getUsedIntegrals() + integralValue);
			}
			integral.setModifier(integral.getCreator() == null ? "" : integralBO.getCreator());
			integral.setGmtModified(new Date());
			
			int modifyResult = integralMapper.modifyIntegral(integral);
			if (modifyResult == 0) {
				log.error(this.getClass() + ".addIntegralDetails() error : 增加积分明细时，修改总积分失败！");
				baseResultDTO.setSuccess(false);
				baseResultDTO.setErrorDetail("增加积分明细时，修改总积分失败！");
				return baseResultDTO;
			}
		}
		
		//处理积分明细
		IntegralDetailsDO integralDetails = new IntegralDetailsDO();
		integralDetails.setIntegralId(integralId);
	  	integralDetails.setIntegralActivityId(integralActivityId);
	  	integralDetails.setMoneyInvolved(moneyInvolved == null ? 0D : moneyInvolved);
	  	integralDetails.setChargingOrderId(chargingOrderId);
		integralDetails.setIntegralId(integralId);
		integralDetails.setDirection(direction);
		integralDetails.setIntegralValue(integralValue);
		integralDetails.setIntegralDate(integralBO.getCompleteDate() == null ? new Date() : integralBO.getCompleteDate());
		integralDetails.setCreator(integralBO.getCreator() == null ? "" : integralBO.getCreator());
		int result = integralDetailsMapper.addIntegralDetails(integralDetails);
		if (result == 0) {
			log.error(this.getClass() + ".addIntegralDetails() error : 增加积分明细失败！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("增加积分明细失败！");
		}
		
		integralResultBO.setIntegralValue(integralValue);
		integralResultBO.setChoiceCount(choiceCount);
		baseResultDTO.setObj(integralResultBO);
		return baseResultDTO;
	}

	/**
	 * <p>Description: 通过订单处理积分</p>
	 * @param
	 * @author bingo
	 * @date 2017-12-8
	 */
	public void doIntegralForBatch(OrderDO orderDO, List<IntegralActivityAndRulesBO> activityList){
		try {
			//桩
			ElectricPileDO electricPileDO = electricPileMapper.getDataForBatch(orderDO.getElectricPileCode());
			if (electricPileDO == null) {
				log.error(this.getClass() + ".doIntegralForBatch() info:电桩编号" + orderDO.getElectricPileCode() + "没有数据！");
			} else {
				orderDO.setElectricPileId(electricPileDO.getId());
				orderDO.setPowerStationId(electricPileDO.getPowerStationId());
				orderDO.setCityCode(electricPileDO.getPsCityCode());
				orderDO.setProvinceCode(electricPileDO.getPsProvinceCode());

				//消费赠送活动
				if(activityList != null && activityList.size() > 0){
					for (IntegralActivityAndRulesBO activity : activityList) {
						//是否全国性活动
						if(activity.getIsWhole().equals(1)){
							editIntegralDetailsData(orderDO, activity);
							break;
						}else {
							if (electricPileDO.getId().equals(activity.getElectricPileId())) {
								editIntegralDetailsData(orderDO, activity);
								break;
							}
						}
					}
				}else {
					log.info(this.getClass() + ".doIntegralForBatch() info : " + orderDO.getOrderCode() + "无消费赠送积分活动！");
				}
			}
		}catch (Exception e){
			log.error(this.getClass() + ".doIntegralForBatch() info:订单编号" + orderDO.getOrderCode() + "消费赠送积分失败！" + e.toString(), e);
		}
	}

	@Transactional
	private void editIntegralDetailsData(OrderDO orderDO, IntegralActivityAndRulesBO activity) throws Exception{
		IntegralBO integralBO = new IntegralBO();
		integralBO.setIntegralActivityId(WanmaConstants.INTEGRAL_CONSUME.longValue());
		integralBO.setMoneyInvolved(orderDO.getChOrMoeny() - (orderDO.getTotalfavMoney() == null ? 0 : Double.parseDouble(orderDO.getTotalfavMoney())));
		integralBO.setDirection(0);//获取
		integralBO.setUserId(orderDO.getUserId());
		integralBO.setElectricPileId(orderDO.getElectricPileId());
		integralBO.setChargingOrderId(orderDO.getOrderId().toString());
		integralBO.setCompleteDate(DateUtil.parse(orderDO.getEndChargeTime()));
		integralBO.setCreator(orderDO.getCreator());
		addIntegralDetails(integralBO, activity);
	}
}