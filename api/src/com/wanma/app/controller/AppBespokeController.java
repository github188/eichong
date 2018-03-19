package com.wanma.app.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.StringUtil;
import com.bluemobi.product.web.WebFilter;
import com.wanma.app.service.AppBespokeService;
import com.wanma.app.service.AppChargingrecordService;
import com.wanma.app.service.AppUserinfoService;
import com.wanma.app.util.Base64Coder;
import com.wanma.app.util.DateUtil;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblBespoke;
import com.wanma.model.TblUserinfo;

/**
 * @Description: 预约控制器
 * @author songjf
 * @createTime：2015-3-17 下午03:59:20
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/bespoke")
public class AppBespokeController extends BaseController {

	private static Logger log = Logger.getLogger(AppUserController.class);

	/** 预约业务处理对象 */
	@Autowired
	private AppBespokeService bespokeService;
	@Autowired
	private AppChargingrecordService chargeRecordService;
	@Autowired
	private AppUserinfoService userinfoService;
	/**
	 * @Title: selectBespoke
	 * @Description: 预约 获取电桩枪口名称 预约单价
	 * @param params
	 * @return
	 */
	@RequestMapping("/selectBespoke")
	@ResponseBody
	public String selectBespoke(@RequestParam Map<String, Object> params) {
		log.info("******************预约 获取电桩枪口名称 预约单价-begin************************");
		// 判断此枪头是否可用
		// TblElectricpilehead electricpilehead =
		// bespokeService.getById(params);
		// if (WanmaConstants.ELECTRICPILEHEAD_FREE != electricpilehead
		// .getEpheElectricpileheadstate()) {
		// return new AccessErrorResult("error.msg.invalid.electricpilehead")
		// .toString();
		// }

		// 判断此枪口是否被预约/充电中/停用
		Map<String,Object> map = bespokeService.isBespoke(Integer
				.parseInt((String) params.get("pkElectricpilehead")));
		long isBespoke = (Long) map.get("bespokeCount");
		if (isBespoke > 0) {
			return new AccessErrorResult(1001,"error.msg.invalid.electricpilehead")
					.toString();
		}
		int headState = (Integer) map.get("headState");
		if(headState == WanmaConstants.ELECTRICPILEHEAD_BATTERY){
			return new AccessErrorResult(1001,"error.msg.charging.electricpilehead")
			.toString();
		}else if(headState == WanmaConstants.ELECTRICPILEHEAD_STOP){
			return new AccessErrorResult(1001,"error.msg.stop.electricpilehead")
			.toString();
		}

		Map<String, Object> bespoke = null;

		try {
			bespoke = bespokeService.selectBespoke(params);
			BigDecimal unitPrice = (BigDecimal) bespoke.get("unitPrice");
			if (null == unitPrice) {
				bespoke.put("unitPrice", "0.00");
			}
			bespoke.put("bespBeginTime", DateUtil.getSeconds());
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("预约 获取电桩枪口名称 预约单价错误", e);
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************预约 获取电桩枪口名称 预约单价-end************************");
		return new AccessSuccessResult(bespoke).toString();
	}

	/**
	 * @Title: selectElectInfo
	 * @Description: 获取电桩，枪口信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/selectElectInfo")
	@ResponseBody
	public String selectElectInfo(@RequestParam Map<String, Object> params) {
		log.info("******************获取电桩，枪口信息-begin************************");

		Map<String, Object> elect = null;

		try {

			elect = bespokeService.selectElectInfo(params);
			if (!StringUtil.isEmpty((String) params.get("pkBespoke"))) {
				// 获取订单编号
				Map<String, Object> bespoke = bespokeService
						.selectBespokeById(params);
				elect.put("bespResePaymentCode",
						bespoke.get("bespResepaymentcode"));
				elect.put("pkBespoke", params.get("pkBespoke"));
			} else {
				elect.put("bespResePaymentCode", BluemobiCommon.getOrderNo());
				elect.put("pkBespoke", "");
			}

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩，枪口信息错误", e);
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************获取电桩，枪口信息-end************************");
		return new AccessSuccessResult(elect).toString();
	}

	/**
	 * @Title: insertBespoke
	 * @Description: 新增预约
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertBespoke")
	@ResponseBody
	public String insertBespoke(TblBespoke bespoke) {
		if(0 == bespoke.getOrg() || 0 == bespoke.getPayMod()){
			return new AccessErrorResult(1001, "当前版本不支持此功能，请升级应用").toString();
		}
		
		log.info("******************新增预约-begin************************");
		try {
			int pileState = bespokeService.getPileState(bespoke.getBespElectricpileid());
			if(pileState == 10)
				return new AccessErrorResult(7000,"预约失败,此电桩已下线").toString();
			Map<String, Object> params = null;
			// 如果预约id存在 说明是续约 ，否则为第一次预约
			String pokeCode = "";
			
			if (!ObjectUtil.isEmpty(bespoke.getPkBespoke()) && bespoke.getPkBespoke() > 0) {
				bespoke.setBespRealityTime(bespoke.getBespEndTime());
				TblBespoke bespoke2 = bespokeService.get(bespoke.getPkBespoke());
				bespoke2.setPayMod(bespoke.getPayMod());
				bespoke2.setOrg(bespoke.getOrg());
				//续约
				int result = bespokeService.contract(bespoke2,bespoke.getDid(),bespoke.getBespBespoketime());
				if(result == -1){
					return new AccessErrorResult(1002,"error.msg.nomoney.bespoke")
					.toString();
				}else if(result == -2){
					return new AccessErrorResult(1004,"error.msg.fail.checkDid").toString();
				}
				
				pokeCode = bespoke2.getBespResepaymentcode();
				bespoke.setBespResepaymentcode(pokeCode);
				WanmaConstants.bespkeState.put(JudgeNullUtils.nvlStr(pokeCode), 0);//WanmaConstants.BESPOKE_AFFIRM_ING);
				// 判断预约是否成功
				for (int i = 0; i < 20; i++) {
					Integer bespStatus=(Integer)WanmaConstants.bespkeState.get(JudgeNullUtils.nvlStr(pokeCode));
					if(bespStatus > 0){
						if (bespStatus == WanmaConstants.BESPOKE_CONTRACT) {
							break;
						}else{
							String msg = "";
							if(bespStatus == WanmaConstants.BESPOKE_AFFIRM_FAIL){
								bespStatus = 1001;
								msg = "续约失败";
							}else if(bespStatus == 1002){
								msg = "预约失败，余额不足，请先去充值";
							}else if(bespStatus == 6000){
								msg = "亲，预约失败，信号漂移。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6001){
								msg = "亲，预约失败，信号堵塞。请重新开始预约";
							}else if(bespStatus == 6100){
								msg = "亲，预约失败，电桩编码无效。请筛选--空闲中的其它电桩使用";
							}else if(bespStatus == 6101){
								msg = "亲，预约失败，电桩枪口编码无效。请筛选--空闲中的其它电桩使用";
							}else if(bespStatus == 6102){
								msg = "亲，预约失败，电桩无费率。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6104){
								msg = "亲，预约失败，电桩在升级。请稍等片刻使用或筛选--空闲中的电桩使用";
							}else if(bespStatus == 6200){
								msg = "亲，预约失败，枪口停用中。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6201){
								msg = "亲，预约失败，电桩未上线。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6202){
								msg = "亲，预约失败，电桩不开放。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6300){
								msg = "亲，预约失败，电桩已占用。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6301){
								msg = "亲，预约失败，电桩已占用（设置中）。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6401){
								msg = "亲，预约失败，用户长度无效。请致电我们检查用户是否有效";
							}else if(bespStatus == 6402){
								msg = "亲，预约失败，用户密码错误。请想想您的用户密码";
							}else if(bespStatus == 6404){
								msg = "亲，预约失败，您的账户无效。请致电我们检查您的账户是否有效";
							}else if(bespStatus == 6405){
								msg = "亲，预约失败，您的账户已冻结。请致电我们检查您的账户冻结原因";
							}else if(bespStatus == 6406){
								msg = "亲，预约失败，您的账户已有预约、充电操作，请结束之前操作后重新开始预约";
							}else if(bespStatus == 6500){ 
								msg = "亲，预约失败，您的预约时长未达30分钟，请重新开始预约";
							}else if(bespStatus == 6501){
								msg = "亲，续约失败，续约标识无效，请重新开始续约";
							}else if(bespStatus == 6502){
								msg = "亲，续约失败，预约编号长度无效，请重新开始预约。";
							}else if(bespStatus == 6600){
								msg = "亲，预约失败，您的账户已有预约操作，请结束之前操作后重新开始预约";
							}else if(bespStatus == 6601){
								msg = "亲，预约失败，电桩已占用（预约中）。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6602){
								msg = "亲，续约失败，非自操作预约。请筛选--空闲中的电桩使用";
							}else if(bespStatus ==6603){
								msg = "亲，预约失败，信号堵塞。请重新开始预约";
							}else if(bespStatus == 6604){
								msg = "亲，预约失败，您的账户已有充电操作，请结束之前操作后重新开始预约";
							}else if(bespStatus == 6605){
								msg = "亲，预约失败，电桩已占用（充电枪连接中）。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6606){
								msg = "亲，预约失败，预约编号不存在，不能续约";
							}else if(bespStatus == 6607){
								msg = "亲，预约失败，您的预约已到最大时长，感谢您的支持";
							}else if(bespStatus == 6608){
								msg = "预约冷却中,不能在其他桩续约";
							}else if(bespStatus == 6610){
								msg = "亲，没有预约，不能停止预约";
							}else if(bespStatus == 6620){
								msg = "亲，寻桩失败，您没有预约操作，请预约后才可寻桩";
							}else if(bespStatus == 6621){
								msg = "亲，您已在寻桩中，请注意电桩操作屏闪烁桩体，驶入";
							}else if(bespStatus == 6633){
								msg = "亲，充电失败，电桩已占用（预约中）。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6804){
								msg = "续约失败，充电桩故障";
							}
							log.error("续约失败，错误码：" + bespStatus + "。用户：" + bespoke.getBespUserinfo());
							return new AccessErrorResult(bespStatus,msg).toString();
						}
					}else{
						if (i == 19) {
							return new AccessErrorResult(6001,"请求处理中，请稍后去我的预约订单结果。").toString();
						}
					}
					
					Thread.sleep(1000);
				}
				
				WanmaConstants.bespkeState.remove(JudgeNullUtils.nvlStr(pokeCode));
				
				log.info("******************续约-end************************");
			} else {

				// 若此枪头为预约确认中，则不可以进行预约
				params = new HashMap<String, Object>();

				params.put("bespElectricpilehead",
						bespoke.getBespElectricpilehead());
				params.put("bespokeStatus", WanmaConstants.BESPOKE_AFFIRM_ING);

				//预约编号 时间戳去掉前两位+随机4位数
				Date date = new Date();
				pokeCode = String.valueOf(date.getTime()/1000);
				pokeCode = pokeCode.substring(2,pokeCode.length());
				pokeCode += (int)(Math.random()*9000+1000);
				bespoke.setBespResepaymentcode(pokeCode);
				bespoke.setBespRealityTime(bespoke.getBespEndTime());
				
				// 预约订单
				int pkBespoke = bespokeService.sendBespoke(bespoke);
				if(pkBespoke == 0){
					return new AccessErrorResult(1001,"error.msg.invalid.ep").toString();
				}else if(pkBespoke == -1){
					return new AccessErrorResult(1002,"error.msg.nomoney.bespoke").toString();
				}else if(pkBespoke == -2){
					return new AccessErrorResult(1004,"error.msg.fail.checkDid").toString();
				}
				// params 保存枪头id和状态

				params.put("epheElectricpileheadstate",
						WanmaConstants.ELECTRICPILEHEAD_APPOINTMENT);
				params.put("bespElectricpilehead",
						bespoke.getBespElectricpilehead());
				// 判断预约是否成功
				WanmaConstants.bespkeState.put(JudgeNullUtils.nvlStr(pokeCode), 0);//WanmaConstants.BESPOKE_AFFIRM_ING);
				
				for (int i = 0; i < 20; i++) {
					Integer bespStatus=(Integer)WanmaConstants.bespkeState.get(JudgeNullUtils.nvlStr(pokeCode));
					String msg = "";
					if(bespStatus > 0){
						if (bespStatus == WanmaConstants.BESPOKE_ING) {
							break;
						}else{ 
							if(bespStatus == WanmaConstants.BESPOKE_AFFIRM_FAIL){
								bespStatus = 1001;
								msg = "error.msg.fail.bespoke";
							}else if(bespStatus == 1002){
								msg = "预约失败，余额不足，请先去充值";
							}else if(bespStatus == 6000){
								msg = "亲，预约失败，信号漂移。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6001){
								msg = "亲，预约失败，信号堵塞。请重新开始预约";
							}else if(bespStatus == 6100){
								msg = "亲，预约失败，电桩编码无效。请筛选--空闲中的其它电桩使用";
							}else if(bespStatus == 6101){
								msg = "亲，预约失败，电桩枪口编码无效。请筛选--空闲中的其它电桩使用";
							}else if(bespStatus == 6102){
								msg = "亲，预约失败，电桩无费率。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6104){
								msg = "亲，预约失败，电桩在升级。请稍等片刻使用或筛选--空闲中的电桩使用";
							}else if(bespStatus == 6200){
								msg = "亲，预约失败，枪口停用中。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6201){
								msg = "亲，预约失败，电桩未上线。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6202){
								msg = "亲，预约失败，电桩不开放。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6300){
								msg = "亲，预约失败，电桩已占用。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6301){
								msg = "亲，预约失败，电桩已占用（设置中）。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6401){
								msg = "亲，预约失败，用户长度无效。请致电我们检查用户是否有效";
							}else if(bespStatus == 6402){
								msg = "亲，预约失败，用户密码错误。请想想您的用户密码";
							}else if(bespStatus == 6404){
								msg = "亲，预约失败，您的账户无效。请致电我们检查您的账户是否有效";
							}else if(bespStatus == 6405){
								msg = "亲，预约失败，您的账户已冻结。请致电我们检查您的账户冻结原因";
							}else if(bespStatus == 6406){
								msg = "亲，预约失败，您的账户已有预约、充电操作，请结束之前操作后重新开始预约";
							}else if(bespStatus == 6500){ 
								msg = "亲，预约失败，您的预约时长未达30分钟，请重新开始预约";
							}else if(bespStatus == 6501){
								msg = "亲，续约失败，续约标识无效，请重新开始续约";
							}else if(bespStatus == 6502){
								msg = "亲，续约失败，预约编号长度无效，请重新开始预约。";
							}else if(bespStatus == 6600){
								msg = "亲，预约失败，您的账户已有预约操作，请结束之前操作后重新开始预约";
							}else if(bespStatus == 6601){
								msg = "亲，预约失败，电桩已占用（预约中）。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6602){
								msg = "亲，续约失败，非自操作预约。请筛选--空闲中的电桩使用";
							}else if(bespStatus ==6603){
								msg = "亲，预约失败，信号堵塞。请重新开始预约";
							}else if(bespStatus == 6604){
								msg = "亲，预约失败，您的账户已有充电操作，请结束之前操作后重新开始预约";
							}else if(bespStatus == 6605){
								msg = "亲，预约失败，电桩已占用（充电枪连接中）。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6606){
								msg = "亲，预约失败，预约编号不存在，不能续约";
							}else if(bespStatus == 6607){
								msg = "亲，预约失败，您的预约已到最大时长，感谢您的支持";
							}else if(bespStatus == 6608){
								msg = "预约冷却中,不能在其他桩续约";
							}else if(bespStatus == 6610){
								msg = "亲，没有预约，不能停止预约";
							}else if(bespStatus == 6620){
								msg = "亲，寻桩失败，您没有预约操作，请预约后才可寻桩";
							}else if(bespStatus == 6621){
								msg = "亲，您已在寻桩中，请注意电桩操作屏闪烁桩体，驶入";
							}else if(bespStatus == 6633){
								msg = "亲，充电失败，电桩已占用（预约中）。请筛选--空闲中的电桩使用";
							}else if(bespStatus == 6804){
								msg = "续约失败，充电桩故障";
							}
							log.error("预约失败，错误码：" + bespStatus + "。用户：" + bespoke.getBespUserinfo());
							return new AccessErrorResult(bespStatus,msg).toString();
									
						}
					}else{
						if (i == 19) {
							return new AccessErrorResult(6001,"请求处理中，请稍后去我的预约查看结果。").toString();
						}
					}
					
					Thread.sleep(1000);
				}
				
				WanmaConstants.bespkeState.remove(JudgeNullUtils.nvlStr(pokeCode));
				
			}

			
			log.info("******************新增预约-end************************");
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增预约错误", e);
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
			
		}
		
		int pkBespoke = 0;
		try{
			Thread.sleep(1500);
			pkBespoke = bespokeService.getBespIdByBespCodeAndUid(bespoke);
			log.info("预约操作完成，预约id为：" + pkBespoke);
		}catch(Exception e){
			log.error("预约崩啦~~~~~~~~~~~~~");
			log.error(e.getMessage());
			return new AccessSuccessResult(0).toString();
		}
		return new AccessSuccessResult(pkBespoke).toString();
	}

	/**
	 * @Title: selectBespokes
	 * @Description: 获取我的预约
	 * @param params
	 *            用户id
	 * @return
	 */
	@RequestMapping("/selectBespokes")
	@ResponseBody
	public String selectBespokes(@RequestParam Map<String, Object> params) {
		log.info("******************获取我的预约-begin************************");

		List<Map<String, Object>> bespokeList = null;

		try {
			bespokeList = bespokeService.selectBespokes(params);
			if (!ObjectUtil.isEmpty(bespokeList)) {
				for (Map<String, Object> map : bespokeList) {
					// 时间转换成时间戳
					// 预约结束时间
					Date endTime = (Date) map.get("bespEndTime");
					long bespBeginTime = DateUtil.toLong((Date) map.get("bespBeginTime"));
					long bespEndTime = DateUtil.toLong(endTime);
					long bespCreatedate = DateUtil.toLong((Date) map.get("bespCreatedate"));
					// 预约结束时间和当前时间比较 如果结束时间大约当前时间 返回结果1 否则返回结果-1
					int compare = DateUtil.compareDate(endTime, new Date());
					if (compare == -1) {
						map.put("bespBespokestatus", WanmaConstants.BESPOKE_END);
					}

				}
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getMessage());
			log.error("获取我的预约错误", e);
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************获取我的预约-end************************");
		return new AccessSuccessResult(bespokeList).toString();
	}

	/**
	 * @Title: selectBespokeById
	 * @Description: 根据id获取我的预约信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/selectBespokeById")
	@ResponseBody
	public String selectBespokeById(@RequestParam Map<String, Object> params) {
		log.info("******************根据id获取我的预约信息-begin************************");

		Map<String, Object> bespoke = null;

		try {
			bespoke = bespokeService.selectBespokeById(params);
			if (!ObjectUtil.isEmpty(bespoke)) {
				long bespBeginTime = DateUtil.toLong((Date) bespoke
						.get("bespBeginTime"));
				bespoke.put("bespBeginTime", bespBeginTime);
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("根据id获取我的预约信息错误", e);
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************根据id获取我的预约信息-end************************");
		return new AccessSuccessResult(bespoke).toString();
	}

	/**
	 * @Title: updateBespStatus
	 * @Description: 更新预约状态 取消预约
	 * @param params
	 * @return
	 */
	@RequestMapping("/updateBespStatus")
	@ResponseBody
	public String updateBespStatus(@RequestParam Map<String, Object> params) {
		log.info("******************更新预约状态 取消预约-begin************************");
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			
			TblUserinfo userinfo = userinfoService.getUserById(Integer.parseInt(params.get("uId").toString()));
			if(!StringUtils.isEmpty(params.get("did"))){
				byte[] b = Base64Coder.decode(params.get("did").toString());
				String did = WebFilter.judgeRequest(new String(b));
				//如果传上来的设备码和用户登录的不匹配则不可预约
				if(!did.equals(userinfo.getDid())){
					return new AccessErrorResult(1004,"error.msg.fail.checkDid").toString();
				}
			}else{
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			
			// 请求电桩，取消预约
			String pokeCode = bespokeService.updateBespStatus(params);
			//String pkBespoke = (String) params.get("pkBespoke");
			WanmaConstants.bespkeState.put(JudgeNullUtils.nvlStr(pokeCode), 0);//WanmaConstants.BESPOKE_AFFIRM_ING);
			// 判断预约是否成功
			for (int i = 0; i < 20; i++) {
				Integer bespStatus=(Integer)WanmaConstants.bespkeState.get(JudgeNullUtils.nvlStr(pokeCode));
				// 预约状态 1：取消预约 2：结束预约 3：续预约 4：预约中
				if (bespStatus == WanmaConstants.BESPOKE_END) {
					WanmaConstants.bespkeState.remove(JudgeNullUtils.nvlStr(pokeCode));
					break;
				}else if(bespStatus >= 6000){
					String msg = "";
					if(bespStatus == 6000){
						msg = "取消预约失败，电桩通讯未连接，请稍后再试";
					}else if(bespStatus == 6002){
						msg = "取消预约失败，电桩服务通讯失败，请稍后再试";
					}else if(bespStatus == 6606){
						msg = "取消预约失败，未找到相应的预约号";
					}else if(bespStatus == 6300){
						msg = "预约失败，桩已经被别人使用";
					}
					log.error("续约失败，错误码：" + bespStatus + "。用户：" + params.get("uId"));
					WanmaConstants.bespkeState.remove(JudgeNullUtils.nvlStr(pokeCode));
					return new AccessErrorResult(bespStatus,msg).toString();
				}
				
				Thread.sleep(1000);
				
				if (i == 19) {
					WanmaConstants.bespkeState.remove(JudgeNullUtils.nvlStr(pokeCode));
					return new AccessErrorResult(6001,"请求处理中，请稍后去我的预约查看结果。").toString();
				}
			}
			//预约成功之后将预约费用和账户余额返回
			
			userinfo = userinfoService.getUserById(userinfo.getPkUserinfo());
			map.put("balance", userinfo.getUsinAccountbalance().toString());
			TblBespoke bespoke = bespokeService.get(Long.parseLong(params.get("pkBespoke").toString()));
			map.put("consumamt", bespoke.getBespFrozenamt() + "");
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getMessage());
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
		}
		
		log.info("******************更新预约状态 取消预约-end************************");
		return new AccessSuccessResult(map).toString();
	}
	
}
