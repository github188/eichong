package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanma.ims.common.domain.FinAccountConfigRelaDO;
import com.wanma.ims.common.domain.FinAccountRelationDO;
import com.wanma.ims.common.domain.FinBillAccountDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.controller.vo.FinBillAccountVO;
import com.wanma.ims.mapper.FinAccountConfigRelaMapper;
import com.wanma.ims.mapper.FinAccountRelationMapper;
import com.wanma.ims.mapper.FinBillAccountMapper;
import com.wanma.ims.service.FinBillAccountService;
import com.wanma.ims.util.DateUtil;

@Service("finBillAccountService")
public class FinBillAccountServiceImpl implements FinBillAccountService {
	@Autowired
	private FinBillAccountMapper finBillAccountMapper;

	@Autowired
	private FinAccountConfigRelaMapper finAccountConfigRelaMapper;

	@Autowired
	private FinAccountRelationMapper finAccountRelationMapper;

	@Override
	public List<FinBillAccountVO> getFinBillAccountComboBox(FinBillAccountDO finBillAccout) throws Exception {
		List<FinBillAccountDO> list = finBillAccountMapper.getFinBillAccountList(finBillAccout);
		if (list != null && list.size() > 0) {
			List<FinBillAccountVO> resultList = new ArrayList<FinBillAccountVO>();
			FinBillAccountVO result;
			for (FinBillAccountDO finBillAccountDO : list) {
				result = new FinBillAccountVO();
				result.setBillAccountCode(finBillAccountDO.getBillAccountCode());
				result.setBillAccountName(finBillAccountDO.getBillAccountName());
				resultList.add(result);
			}
			return resultList;
		}else{
			return new ArrayList<FinBillAccountVO>();
		}
	}
	
	@Override
	public Long getFinBillAccountCount(FinBillAccountDO finBillAccout) {
		return finBillAccountMapper.getFinBillAccountCount(finBillAccout);
	}

	@Override
	public List<FinBillAccountDO> getFinBillAccountList(FinBillAccountDO finBillAccout) {
		return finBillAccountMapper.getFinBillAccountList(finBillAccout);
	}

	@Override
	@Transactional
	public int addFinBillAccount(FinBillAccountDO finBillAccout, UserDO user) throws Exception {
		// 生成 账单科目编码 规则
		finBillAccout.setIsDel(0);

		finBillAccout.setCreator(user.getUserAccount());
		finBillAccout.setModifier(user.getUserAccount());
		// 取系统当前时间
		Date now = DateUtil.parse(DateUtil.getNow(DateUtil.TYPE_COM_YMDHMS));
		finBillAccout.setGmtCreate(now);
		finBillAccout.setGmtModified(now);

		return finBillAccountMapper.addFinBillAccount(finBillAccout);
	}

	@Override
	@Transactional
	public int modifyFinBillAccount(FinBillAccountDO finBillAccout, UserDO user) throws Exception {
		FinBillAccountDO qryFinBillAccout = new FinBillAccountDO();
		qryFinBillAccout.setPkId(finBillAccout.getPkId());
		Long qryResult = finBillAccountMapper.getFinBillAccountCount(qryFinBillAccout);
		if (qryResult < 1) {
			return 2;
		}

		finBillAccout.setModifier(user.getUserAccount());
		finBillAccout.setGmtModified(DateUtil.parse(DateUtil.getNow(DateUtil.TYPE_COM_YMDHMS)));

		return finBillAccountMapper.modifyFinBillAccount(finBillAccout);
	}

	@Override
	@Transactional
	public int removeFinBillAccount(FinBillAccountDO finBillAccout) throws Exception {
		// 判断是否有关联账务配置
		FinAccountConfigRelaDO qryFinAccountConfigRela = new FinAccountConfigRelaDO();
		qryFinAccountConfigRela.setBillAccountId(finBillAccout.getPkId());
		Long qryReslut = finAccountConfigRelaMapper.getFinAccountConfigRelaCount(qryFinAccountConfigRela);
		if (qryReslut > 0) {
			return 2;
		}

		// 判断是否有关联账户关系
		FinAccountRelationDO qryFinAccountRelation = new FinAccountRelationDO();
		qryFinAccountRelation.setBillAccountId(finBillAccout.getPkId());
		qryReslut = finAccountRelationMapper.getFinAccountRelationCount(qryFinAccountRelation);
		if (qryReslut > 0) {
			return 3;
		}

		return finBillAccountMapper.removeFinBillAccount(finBillAccout);
	}
}
