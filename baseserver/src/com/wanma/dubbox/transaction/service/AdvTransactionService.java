/**     
 * @Title:  TblUserService.java   
 * @Package com.wanma.service   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年4月28日 下午3:59:00   
 * @version V1.0     
 */
package com.wanma.dubbox.transaction.service;


import com.wanma.dubbox.model.TblAdvertisement;

/**
 * @author lhy
 *
 */
public interface AdvTransactionService {

	public void insert(TblAdvertisement model);

	public void update(TblAdvertisement model);
}