package com.wanma.service;

import java.util.List;

import com.wanma.model.TblInstalldetail;

/**
 * @Description: 预约安装详情业务处理接口
 * @author songjf
 * @createTime：2015-4-2 下午02:06:54
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface CmsInstalldetailService {
	/**
	 * @Title: findInstalldetailList
	 * @Description: 获取订单详情
	 * @param tblInstalldetail
	 * @return
	 */
	public List<TblInstalldetail> findInstalldetailList(TblInstalldetail tblInstalldetail);
	
	/**
	 * @Title: findInstalldetailCount
	 * @Description: 获取预约安装订单详情总数
	 * @param tblInstalldetail
	 * @return
	 */
	public long findInstalldetailCount(TblInstalldetail tblInstalldetail);
	
	/**
	 * @Title: getInstalldetail
	 * @Description: 根据主键获取预约安装订单详情
	 * @param pkInstalldetail
	 *        主键
	 * @return
	 */
	public TblInstalldetail getInstalldetail(int pkInstalldetail);
}
