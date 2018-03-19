package com.wanma.app.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.web.WebFilter;
import com.wanma.app.dao.AppBespokeMapper;
import com.wanma.app.dao.AppUserinfoMapper;
import com.wanma.app.dao.TblElectricpileMapper;
import com.wanma.app.dao.TblElectricpileheadMapper;
import com.wanma.app.service.AppBespokeService;
import com.wanma.app.util.Base64Coder;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblBespoke;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblUserinfo;
import com.wanma.net.ElectricPieUtil;

/**
 * @Description: 预约业务处理类
 * @author songjf
 * @createTime：2015-3-17 下午03:54:12
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service("appBespokeService")
public class AppBespokeServiceImpl implements AppBespokeService {
	/* 预约表操作dao */
	@Autowired
	private AppBespokeMapper bespokeMapper;
	/* 枪头表操作dao */
	@Autowired
	private TblElectricpileheadMapper electricpileheadMapper;
	/* 桩体操作dao */
	@Autowired
	private TblElectricpileMapper electricpileMapper;
	/* 用户操作dao */
	@Autowired
	private AppUserinfoMapper userinfoMapper;

	/**
	 * @Title: selectBespoke
	 * @Description: 预约 获取电桩枪口名称 预约单价
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> selectBespoke(Map<String, Object> params) {
		return bespokeMapper.selectBespoke(params);
	}

	/**
	 * @Title: selectElectInfo
	 * @Description: 获取电桩，枪口信息
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> selectElectInfo(Map<String, Object> params) {
		// 获取冻结金额
		// Double FrozenAmt = bespokeMapper.selectFrozenAmt(params);
		// 获取电桩，枪口信息,账户余额
		Map<String, Object> map = bespokeMapper.selectElectInfo(params);
		//账户余额
		BigDecimal usInAccountBalance = (BigDecimal) map.get("usInAccountBalance");
		//冻结金额
		BigDecimal frozenAmt = (BigDecimal) map.get("frozenAmt");
		if(frozenAmt==null){
			frozenAmt = new BigDecimal(0);
		}
		usInAccountBalance = usInAccountBalance.subtract(frozenAmt);
		map.put("usInAccountBalance", usInAccountBalance);
		// Double usInAccountBalance = (Double) map.get("usInAccountBalance");
		// usInAccountBalance = usInAccountBalance - FrozenAmt;
		// map.put("usInAccountBalance", usInAccountBalance);
		return map;
	}

	/**
	 * @Title: insertBespoke
	 * @Description: 新增预约
	 * @param params
	 * @return
	 * @throws IOException
	 */
	@Override
	public int sendBespoke(TblBespoke bespoke) throws IOException {
		
		// 获取电桩编号 用户账户 用户余额
		TblUserinfo userinfo = bespokeMapper.selectUserAndElectInfo(bespoke);
		if(StringUtils.isEmpty(userinfo.getElPiElectricPileCode())){
			return 0;
		}
		
		if(!StringUtils.isEmpty(bespoke.getDid())){
			byte[] b = Base64Coder.decode(bespoke.getDid());
			String did = WebFilter.judgeRequest(new String(b));
			//如果传上来的设备码和用户登录的不匹配则不可预约
			if(!did.equals(userinfo.getDid())){
				return -2;
			}
		}else{
			return -2;
		}
		
		// 续预约需要冻结的金额
		BigDecimal bespFrozenamt = bespoke.getBespFrozenamt();
		//bespFrozenamt = frozenamt + bespFrozenamt;
		//账户余额
		BigDecimal usinAccountbalance = userinfo.getUsinAccountbalance();
		BigDecimal frozenMoney = bespFrozenamt;
		if(usinAccountbalance.compareTo(frozenMoney)<0){
			return -1;
		}
			
		bespoke.setBespUpdatedate(new Date());
		bespoke.setBespBespokestatus(WanmaConstants.BESPOKE_AFFIRM_ING);

		//bespokeMapper.insert(bespoke);
		
		ElectricPieUtil.bookElectricPie(bespoke.getBespResepaymentcode(),bespoke.getBespElectricpileid(), 
				userinfo.getElPiElectricPileCode(), bespoke.getBespBespoketime(),
				bespoke.getBespBeginTime(), 0, bespoke.getBespUserinfo(), userinfo.getUsinPhone(),
				 bespoke.getBespElectricpilehead(), userinfo.getElectricpileHeadId(),bespoke.getOrg(),bespoke.getPayMod());

		return 1;
	}

	/**
	 * @Title: selectBespokes
	 * @Description: 获取我的预约
	 * @param params
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectBespokes(Map<String, Object> params) {
		return bespokeMapper.selectBespokes(params);
	}

	/**
	 * @Title: contract
	 * @Description: 续约
	 * @param params
	 * @return
	 */
	@Override
	public int contract(TblBespoke bespoke,String did,String buyTime) {
		// 获取电桩编号 用户账户 用户余额 桩体编号
		TblUserinfo userinfo = bespokeMapper.selectUserAndElectInfo(bespoke);
		
		if(!StringUtils.isEmpty(did)){
			byte[] b = Base64Coder.decode(did);
			did = WebFilter.judgeRequest(new String(b));
			//如果传上来的设备码和用户登录的不匹配则不可预约
			if(!did.equals(userinfo.getDid())){
				return -2;
			}
		}else{
			return -2;
		}
		
		//TblBespoke bespoke2 = get(bespoke.getPkBespoke());
		// 已冻结金额
		/*BigDecimal frozenamt = bespoke.getBespFrozenamt();
		// 续预约需要冻结的金额
		BigDecimal bespFrozenamt = bespoke.getBespFrozenamt();
		//bespFrozenamt = frozenamt + bespFrozenamt;
		//账户余额
		BigDecimal usinAccountbalance = userinfo.getUsinAccountbalance();
		BigDecimal frozenMoney = bespFrozenamt;
		if(usinAccountbalance.compareTo(frozenMoney)<0){
			return -1;
		}*/

		ElectricPieUtil.bookElectricPie(bespoke.getBespResepaymentcode(),bespoke.getBespElectricpileid(), 
				userinfo.getElPiElectricPileCode(), buyTime,bespoke.getBespBeginTime(), 
				1, bespoke.getBespUserinfo(), userinfo.getUsinPhone(),
				bespoke.getBespElectricpilehead(), userinfo.getElectricpileHeadId(),bespoke.getOrg(),bespoke.getPayMod());
		return 0;
	}

	/**
	 * @Title: update
	 * @Description: 更新预约
	 * @param params
	 * @return
	 */
	@Override
	public int update(TblBespoke bespoke) {

		return bespokeMapper.update(bespoke);
	}

	/**
	 * @Title: getById
	 * @Description: 根据id获取枪头数据
	 * @param params
	 * @return
	 */
	@Override
	public TblElectricpilehead getById(Map<String, Object> params) {
		return electricpileheadMapper.get(params);
	}

	/**
	 * @Title: selectBespokeById
	 * @Description: 根据id获取预约信息
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> selectBespokeById(Map<String, Object> params) {
		return bespokeMapper.selectBespokeById(params);
	}

	/**
	 * @Title: updateBespStatus
	 * @Description: 请求电桩，取消预约
	 * @param params
	 * @return
	 */
	@Override
	public String updateBespStatus(Map<String, Object> params) {

		// 获取枪头编号 电桩编号
		Map<String, Object> map = electricpileMapper.getbyPkElecpileHead(params);
		
		TblBespoke bespoke = bespokeMapper.get(Integer.parseInt(params.get("pkBespoke").toString()));
		
		ElectricPieUtil.cancelBookElectricPie(bespoke.getPkBespoke(),bespoke.getBespResepaymentcode(),
				(String) map.get("electricPileCode"),(int)map.get("electricpileHeadId"));

		return bespoke.getBespResepaymentcode();
	}

	/**
	 * @Title: cancelBespoke
	 * @Description: 取消预约 更新预约结束时间 费用等信息
	 * @param params
	 * @return
	 */
	@Override
	public void cancelBespoke(Map<String, Object> params) {
		// params.put("bespUpdatedate", new Date());
		// params.put("bespBespokestatus", WanmaConstants.BESPOKE_CANCEL);
		// params.put("epheElectricpileheadstate",
		// WanmaConstants.ELECTRICPILEHEAD_FREE);
		// 更新枪口状态为空闲中
		electricpileheadMapper.updateState(params);
		// 获取预约开始时间
		// Date begin = DateUtil.parse((String) params.get("bespBeginTime"),
		// "yyyy-MM-dd HH:mm:ss");
		// // 预约结束时间
		// Date end = new Date();
		// long beginTime = begin.getTime();
		// long endTime = end.getTime();
		// // 获取开始时间与结束 时间的时间差
		// Long bespBespoketime = (endTime - beginTime) / (1000 * 60);
		//
		// Double unitPrice = bespokeMapper.selectUnitPrice(Integer
		// .parseInt((String) params.get("bespElectricpilehead")));
		// // 重新计算用户预约费用, 时间为30分钟的倍数，比如40分钟就算做60分钟
		// Double bespFrozenamt = Math.ceil(bespBespoketime.doubleValue() /
		// 30.0)
		// * 30 * unitPrice;
		//
		// params.put("bespFrozenamt", bespFrozenamt);
		// params.put("bespBespoketime", bespBespoketime);
		// params.put("bespEndTime", end);
		
		//取消预约返回里不处理预约单状态，在事件里由gate端处理
		//bespokeMapper.updateBespStatus(params);

		// 获取用户信息
		/*Map<String, Object> userinfo = userinfoMapper.findOne((Integer) params
				.get("pkUserinfo"));
		// 账户余额
		BigDecimal usinAccountbalance = (BigDecimal) userinfo
				.get("usinAccountbalance");
		// BigDecimal frozenamt = new BigDecimal(bespFrozenamt);
		BigDecimal frozenamt = new BigDecimal(
				(Double) params.get("bespFrozenamt"));
		BigDecimal balance = usinAccountbalance.subtract(frozenamt);

		params.put("usinAccountbalance", balance);
		
		//用户费用在取消预约事件里由gate端处理，返回不处理
		// 更新用户账户余额
		userinfoMapper.updateAccountbalance(params);*/

	}

	/**
	 * @Title: isBespoke
	 * @Description: 判断此枪口是否被预约/充电中/停用
	 * @param params
	 * @return
	 */
	@Override
	public Map<String,Object> isBespoke(int bespElectricPileHead) {
		return bespokeMapper.isBespoke(bespElectricPileHead);
	}

	/**
	 * @Title: updateStatus
	 * @Description: 更新预约状态
	 * @param params
	 * @return
	 */
	public int updateStatus(Map<String, Object> params) {
		return bespokeMapper.updateBespStatus(params);
	}

	/**
	 * @Title: updateState
	 * @Description: 更新枪头预约状态
	 * @param params
	 * @return
	 */
	@Override
	public int updateState(Map<String, Object> params) {
		return electricpileheadMapper.updateState(params);
	}

	/**
	 * @Title: get
	 * @Description: 获取预约信息
	 * @param pkBespoke
	 *            预约id
	 * @return
	 */
	@Override
	public TblBespoke get(long pkBespoke) {
		return bespokeMapper.get(pkBespoke);
	}

	/**
	 * @Title: selectLockPilehead
	 * @Description: 判断此枪口是否正在预约中
	 * @param params
	 * @return
	 */
	@Override
	public long selectLockPilehead(Map<String, Object> params) {
		return bespokeMapper.selectLockPilehead(params);
	}

	/**
	 * @Title: selectUnitPrice
	 * @Description: 获取电桩单价
	 * @param params
	 * @return
	 */
	@Override
	public Double selectUnitPrice(int pkElectricPile) {
		return bespokeMapper.selectUnitPrice(pkElectricPile);
	}

	/**
	 * @Title: selectCountByUserId
	 * @Description: 根据用户id判断此用户当前是否有预约枪口
	 * @param params
	 * @return
	 */
	@Override
	public int selectCountByUserId(TblBespoke bespoke) {
		return bespokeMapper.selectCountByUserId(bespoke);
	}

	public int getUnpayNumByUserId(TblBespoke bespoke){
		return bespokeMapper.getUnpayNumByUserId(bespoke);
	}
	
	/**
	 * @Title: selectAtEndTime
	 * @Description: 获取到预约结束时间还未处理的预约
	 * @return
	 */
	@Override
	public List<TblBespoke> selectAtEndTime() {
		return bespokeMapper.selectAtEndTime();
	}

	/**
	 * 根据用户id和枪头id获取预约编号
	 */
	@Override
	public String getBpByUidAndHeadid(int uid,int headid,int epid){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("uid", uid);
		map.put("headid", headid);
		map.put("epid", epid);
		return bespokeMapper.getBpByUidAndHeadid(map);
	}
	
	public void deleteById(int pkBespoke){
		//Map<String, Integer> map = new HashMap<String, Integer>();
		//map.put("pkBespoke", pkBespoke);
		bespokeMapper.delete(pkBespoke);
	}
	
	public int getBespIdByBespCodeAndUid(TblBespoke tblBespoke){
		return bespokeMapper.getBespIdByBespCodeAndUid(tblBespoke);
	}

	@Override
	public int getPileState(int pileId){
		return electricpileMapper.getPileState(pileId);
	}
}
