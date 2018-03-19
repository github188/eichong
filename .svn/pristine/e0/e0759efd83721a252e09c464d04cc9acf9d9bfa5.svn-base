package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.FinBillAccountDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.controller.vo.FinBillAccountVO;


/**
 * 账单科目
 * @author bingo
 * @date 2017-06-13
 */
public interface FinBillAccountService {
	/**
	* <p>Description: 获取账单科目下拉列表</p>
	* @param
	* @author bingo
	* @date 2017-7-21
	 */
	public List<FinBillAccountVO> getFinBillAccountComboBox(FinBillAccountDO finBillAccout) throws Exception;
	
	/**
	* <p>Description: 获取账单科目数量</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public Long getFinBillAccountCount(FinBillAccountDO finBillAccout);
	
	/**
	* <p>Description: 获取账单科目数据</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public List<FinBillAccountDO> getFinBillAccountList(FinBillAccountDO finBillAccout);
	
	/**
	* <p>Description: 新增账单科目</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public int addFinBillAccount(FinBillAccountDO finBillAccout, UserDO user) throws Exception;
	
	/**
	* <p>Description: 修改账单科目</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public int modifyFinBillAccount(FinBillAccountDO finBillAccout, UserDO user) throws Exception;
	
	/**
	* <p>Description: 删除账单科目</p>
	* @param
	* @author bingo
	* @date 2017-6-14
	 */
	public int removeFinBillAccount(FinBillAccountDO finBillAccout) throws Exception;
}
