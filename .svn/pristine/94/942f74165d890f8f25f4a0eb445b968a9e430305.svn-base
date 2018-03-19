package com.bluemobi.product.controller;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.appCore.netty.buffer.DynamicByteBuffer;
import com.bluemobi.product.utils.SpringContextHolder;
import com.wanma.app.service.AppBespokeService;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblBespoke;
import com.wanma.net.BackDataObject;
import com.wanma.net.ElectricPieUtil;


public class DemoControllerTest {
	
	
	/**
	 * 预约充电  测试
	 * 
	 * @param bussinessId
	 *            业务ID
	 * @param electricPieNumber
	 *            电桩编号
	 * @param buyTime
	 *            预约买断时间
	 * @param bookStartTime
	 *            预约开始时间
	 * @param renewalFlag
	 *            续约标识（0：第一次预约，1：续约）
	 * @param accountId
	 *            用户账号
	 * @param remainAmount
	 *            用户余额
	 * @param tipId
	 *            电桩枪口
	 * @param electricpileHeadId
	 *            电桩枪口编号
	 * @return
	 */
	public static void bookElectricPie(String bussinessId,
			String electricPieNumber, String buyTime, Date bookStartTime,
			int renewalFlag, String accountId, int remainAmount, int tipId,
			int electricpileHeadId) {
		/********因为没有电桩后台调用，此处默认请求后台成功，调用回调方法********/
		DynamicByteBuffer byteBuffer = DynamicByteBuffer.allocate();
		
		// 业务ID
		byteBuffer.putString(bussinessId);

		// 电桩编号
		byteBuffer.putString(electricPieNumber);
		// 电桩枪口编号
//		byteBuffer.putInt(electricpileHeadId);
		byteBuffer.putShort((short) 1);
		
		byteBuffer.putString("test");
		
		byte[] bb = byteBuffer.getBytes();

		bookElectricPieBack(bb);

	}

	/**
	 * 预约充电返回处理
	 * 
	 * @param byteBuffer
	 *            返回信息
	 * @return
	 */
	public static void bookElectricPieBack(byte[] bb) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(bb);
		
		BackDataObject backDataObject = ElectricPieUtil
				.hanleBackMsg(byteBuffer);

		/** 预约业务处理对象 */
		AppBespokeService bespokeService = (AppBespokeService) SpringContextHolder
				.getSpringContext().getBean("appBespokeService");
		Map<String, Object> params = new HashMap<String, Object>();
		if (backDataObject.getStatus() == 1) {
			TblBespoke bespoke = bespokeService.get(Integer
					.parseInt(backDataObject.getBussinessId()));
			// 如果状态为预约确认中，则表示第一次预约
			if (bespoke.getBespBespokestatus() == WanmaConstants.BESPOKE_AFFIRM_ING) {
				params.put("bespBespokestatus", WanmaConstants.BESPOKE_ING);
			} else if (bespoke.getBespBespokestatus() == WanmaConstants.BESPOKE_ING) {
				params.put("bespBespokestatus", WanmaConstants.BESPOKE_CONTRACT);
			}
			params.put("pkBespoke", backDataObject.getBussinessId());
			bespokeService.updateStatus(params);
		}

	}
	
	/**
	 * 取消预约 测试
	 * 
	 * @param bussinessId
	 *            业务ID
	 * @param electricPieNumber
	 *            电桩编号
	 * @param tipId
	 *            电桩枪口
	 * @param electricpileHeadId
	 *            电桩枪口编号
	 * @return
	 */
	/*public static void cancelBookElectricPie(String bussinessId,
			String electricPieNumber, int electricpileHeadId) {

		DynamicByteBuffer byteBuffer = DynamicByteBuffer.allocate();

		// 业务ID
		byteBuffer.putString(bussinessId);

		// 电桩编号
		byteBuffer.putString(electricPieNumber);
		// 电桩枪口编号
//		byteBuffer.putInt(electricpileHeadId);
		byteBuffer.putShort((short) 1);
		
		byteBuffer.putString("test");
		
		byteBuffer.putLong(DateUtil.toLong(new Date()));

		byte[] bb = byteBuffer.getBytes();

		cancelBookElectricPieBack(bb);

	}*/

	/**
	 * 取消预约充电返回处理
	 * 
	 * @param byteBuffer
	 *            返回信息
	 * @return
	 */
	public static void cancelBookElectricPieBack(byte[] bb) {

		ByteBuffer byteBuffer = ByteBuffer.wrap(bb);
		
		BackDataObject backDataObject = ElectricPieUtil
				.hanleCancelBackMsg(byteBuffer);
		
		// 实现预约充电完成业务逻辑
		AppBespokeService bespokeService = (AppBespokeService) SpringContextHolder
				.getSpringContext().getBean("appBespokeService");
		Map<String, Object> params = new HashMap<String, Object>();
		if (backDataObject.getStatus() == 1) {
			params.put("bespBespokestatus", WanmaConstants.BESPOKE_CANCEL);

			// 获取预约信息
			TblBespoke bespoke = bespokeService.get(Integer
					.parseInt(backDataObject.getBussinessId()));
			// 预约开始时间
			Date bespokeBeginTime = bespoke.getBespBeginTime();
			// 预约结束时间
			Date bespRealityTime = backDataObject.getBespokeEndTime();

			long beginTime = bespokeBeginTime.getTime();
			long endTime = bespRealityTime.getTime();
			// 获取开始时间与结束 时间的时间差
			Long bespBespoketime = (endTime - beginTime) / (1000 * 60);
			// 获取电桩单价
			Double unitPrice = bespokeService.selectUnitPrice(bespoke
					.getBespElectricpilehead());
			
			// 重新计算用户预约费用, 时间为30分钟的倍数，比如40分钟就算做60分钟
			Double bespFrozenamt = Math.ceil(bespBespoketime.doubleValue() / 30.0)
					* 30 * unitPrice;
			params.put("bespFrozenamt", bespFrozenamt);
			params.put("bespBespoketime", bespBespoketime);
			params.put("bespRealityTime", bespRealityTime);
			params.put("bespBespokestatus", WanmaConstants.BESPOKE_CANCEL);
			//更新枪口状态数据
			params.put("bespUpdatedate", new Date());
			params.put("epheElectricpileheadstate",
					WanmaConstants.ELECTRICPILEHEAD_FREE);
			params.put("bespElectricpilehead", bespoke.getBespElectricpilehead());
//			更新用户余额数据
			params.put("pkUserinfo", bespoke.getBespUserinfo());
			params.put("bespFrozenamt", bespFrozenamt);
			
			params.put("pkBespoke", backDataObject.getBussinessId());
			
			bespokeService.cancelBespoke(params);
			
		}

	}
	
	public static void main(String[] args){
		/*DateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse("2000-05-05 00:11:22");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Date date = new Date(1433417501000l);
		System.out.println(date);
	}
}
