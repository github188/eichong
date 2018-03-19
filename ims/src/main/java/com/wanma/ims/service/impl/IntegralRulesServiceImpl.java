package com.wanma.ims.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanma.ims.common.domain.IntegralRulesDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.mapper.IntegralRulesMapper;
import com.wanma.ims.service.IntegralRulesService;

@Service("integralRulesService")
public class IntegralRulesServiceImpl implements IntegralRulesService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private IntegralRulesMapper integralRulesMapper;
	
	@Override
	public Long getIntegralRulesCount(IntegralRulesDO integralRules) {
		return integralRulesMapper.getIntegralRulesCount(integralRules);
	}
	
	@Override
	public List<IntegralRulesDO> getIntegralRulesList(IntegralRulesDO integralRules) {
		return integralRulesMapper.getIntegralRulesList(integralRules);
	}
	
	@Override
	@Transactional
	public BaseResultDTO modifyIntegralRules(IntegralRulesDO integralRules) throws Exception{
		BaseResultDTO baseResultDTO = new BaseResultDTO();
		
		if (integralRules.getModifier() == null) {
			integralRules.setModifier("");
		}
		if (integralRules.getGmtModified() == null) {
			integralRules.setGmtModified(new Date());
		}
		
		int result =  integralRulesMapper.modifyIntegralRules(integralRules);
		if (result != 1) {
			log.error(this.getClass() + ".modifyIntegralRules() error : 修改积分规则失败!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("修改积分规则失败!");
		}
		return baseResultDTO;
	}

	@Override
	@Transactional
	public BaseResultDTO addIntegralRules(IntegralRulesDO integralRules) throws Exception {
		BaseResultDTO baseResultDTO = new BaseResultDTO();
		
		if (integralRules.getCreator() == null) {
			integralRules.setCreator("");
		}
		if (integralRules.getGmtCreate() == null) {
			integralRules.setGmtCreate(new Date());
		}
		
		int result =  integralRulesMapper.addIntegralRules(integralRules);
		if (result != 1) {
			log.error(this.getClass() + ".addIntegralRules() error : 增加积分规则失败!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("增加积分规则失败！");
		}
		return baseResultDTO;
	}
}