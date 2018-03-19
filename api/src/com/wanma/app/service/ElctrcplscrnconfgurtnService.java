/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service;

import java.util.List;

import com.wanma.model.TblElctrcplscrnconfgurtn;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface ElctrcplscrnconfgurtnService {

	/**
	 *  获取电桩类型
	 *  
	 * @return
	 */
	public List<TblElctrcplscrnconfgurtn> getScreenTypeList(TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn);
	/**
	 *   获取电桩状态
	 *  
	 * @return
	 */
	public List<TblElctrcplscrnconfgurtn> getScreenStateList(TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn);
	/**
	 *  获取电桩搜索半径
	 *  
	 * @return
	 */
	public List<TblElctrcplscrnconfgurtn> getScreenRadiusList(TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn);
	/**
	 *  获取电桩是否支持预约
	 *  
	 * @return
	 */
	public List<TblElctrcplscrnconfgurtn> getScreenSubscribeList(TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn);

}
