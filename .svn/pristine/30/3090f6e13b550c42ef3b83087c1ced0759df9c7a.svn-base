package com.wanma.ims.task;

import java.util.*;

import com.wanma.ims.common.domain.*;
import com.wanma.ims.common.domain.bo.IntegralActivityAndRulesBO;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.core.GlobalSystem;
import com.wanma.ims.core.InitAccountSplitDetails;
import com.wanma.ims.mapper.*;
import com.wanma.ims.service.*;
import com.wanma.ims.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ChargingOrderTask {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private IntegralActivityMapper integralActivityMapper;

	@Autowired
	private JPushRecordService jPushRecordService;

	@Autowired
	private IntegralDetailsService integralDetailsService;

	@Autowired
	private FinAccountSplitDetailsService finAccountSplitDetailsService;

	@Autowired
	private FavCouponService couponService;

	@Scheduled(cron = "0 */1 * * * ?")
	public void doChargingOrderBatch(){
		String isExecuteBatch = GlobalSystem.getConfig("isExecuteBatch");
		if(isExecuteBatch != null && isExecuteBatch != "" && "1".equals(isExecuteBatch)){
			if(InitAccountSplitDetails.batchFlag){
				log.info(this.getClass() + ".doChargingOrderBatch() info:订单数据已经在批量处理中......");
				return;
			}
			InitAccountSplitDetails.batchFlag = true;
			//订单
			List<OrderDO> orderDOList = orderMapper.getOrdersForBatch(new OrderDO());
			Set<String> accountSplitDetailsSet = InitAccountSplitDetails.accountSplitDetailsSet;
			String maxChargingOrderId = jPushRecordService.getMaxChargingOrderId(null);
			if(maxChargingOrderId == null){
				maxChargingOrderId = "";
			}
			IntegralActivityDO qryActivityDO = new IntegralActivityDO();
			qryActivityDO.setIntegralDate(new Date());
			qryActivityDO.setActivityStatus(WanmaConstants.INTEGRAL_ACTIVITY_STATUS_OPEN);
			List<IntegralActivityAndRulesBO> activityList = integralActivityMapper.getIntegralActivityForBatch(qryActivityDO);
			List<OrderDO> resultList = new ArrayList<OrderDO>();
			for(OrderDO orderDO : orderDOList){
				if(orderDO.getOrderStatus().intValue() == 1 && orderDO.getOrderId().toString().compareTo(maxChargingOrderId) > 0){
					resultList.add(orderDO);
				}else if((orderDO.getOrderStatus().intValue() == 2 || orderDO.getOrderStatus().intValue() == 3)
						&& !accountSplitDetailsSet.contains(orderDO.getOrderId().toString())){
					resultList.add(orderDO);
				}
			}
			if(resultList.size() == 0){
				log.info(this.getClass() + ".doChargingOrderBatch() info:订单数据已经全部处理！");
				InitAccountSplitDetails.batchFlag = false;
				return;
			}

			Collections.reverse(resultList);
			for(OrderDO orderDO : resultList){
				orderDO.setCreator("admin");
				//爱充用户、结算已到账的订单状态才有消费赠送积分和优惠券
				if(orderDO.getOrgNo() == null){
					log.error(this.getClass() + ".doIntegralsBatch() info:订单编号" + orderDO.getOrderCode() + "对应的组织机构编号为空！");
				}else {
					if (orderDO.getOrgNo().intValue() == 1000) {
						if(orderDO.getOrderStatus() == null) {
							log.info(this.getClass() + ".doIntegralsBatch() info:订单编号" + orderDO.getOrderCode() + "对应的订单状态为空！");
						}else if(orderDO.getOrderStatus().intValue() == 1){
							jPushRecordService.doJPushRecordForBatch(orderDO);
						}else if(orderDO.getOrderStatus().intValue() == 2 || orderDO.getOrderStatus().intValue() == 3){
							if(activityList != null && activityList.size() > 0){
								integralDetailsService.doIntegralForBatch(orderDO, activityList);
							}
							couponService.doCouponForBatch(orderDO);
							jPushRecordService.doJPushRecordForBatch(orderDO);
						}
					}
				}

				if(orderDO.getOrderStatus().intValue() == 2 || orderDO.getOrderStatus().intValue() == 3) {
					//分账
					finAccountSplitDetailsService.doFinAccountSplitDetailsForBatch(orderDO);
					accountSplitDetailsSet.add(orderDO.getOrderId().toString());
				}
			}
			InitAccountSplitDetails.setData(accountSplitDetailsSet);
			InitAccountSplitDetails.batchFlag = false;
		}
	}
}
