package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.FinAccountConfigRelaDO;
import com.wanma.ims.common.domain.UserDO;


/**
 * 账务配置
 * @author bingo
 * @date 2017-06-13
 */
public interface FinAccountConfigRelaService {
	/**
	* <p>Description: 获取账务配置数量</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public Long getFinAccountConfigRelaCount(FinAccountConfigRelaDO finAccountConfigRela);
	
	/**
	* <p>Description: 获取账务配置数据</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public List<FinAccountConfigRelaDO> getFinAccountConfigRelaList(FinAccountConfigRelaDO finAccountConfigRela);
	
	/**
	* <p>Description: 新增账务配置</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public int addFinAccountConfigRela(FinAccountConfigRelaDO finAccountConfigRela, UserDO user) throws Exception;
	
	/**
	* <p>Description: 修改账务配置</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public int modifyFinAccountConfigRela(FinAccountConfigRelaDO finAccountConfigRela, UserDO user) throws Exception;
	
	/**
	* <p>Description: 删除账务配置</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public int removeFinAccountConfigRela(FinAccountConfigRelaDO finAccountConfigRela) throws Exception;
	
	/**
	* <p>Description: 获取渠道对应的账务配置列表</p>
	* @param
	* @author bingo
	* @date 2017-8-21
	 */
	public List<FinAccountConfigRelaDO> getFinAccountConfigRela4Cpy(FinAccountConfigRelaDO finAccountConfigRela);
}
