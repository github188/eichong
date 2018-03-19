package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsInstalldetailMapper;
import com.wanma.model.TblInstalldetail;
import com.wanma.service.CmsInstalldetailService;
/**
 * @Description: 预约安装详情业务处理实现类
 * @author songjf
 * @createTime：2015-4-2 下午02:06:54
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class CmsInstalldetailServiceImpl implements CmsInstalldetailService {

	/**预约安装详情表操作dao*/
	@Autowired
	private CmsInstalldetailMapper installdetailMapper;
	
	/**
	 * @Title: findInstalldetailList
	 * @Description: 获取订单详情
	 * @param tblInstalldetail
	 * @return
	 */
	@Override
	public List<TblInstalldetail> findInstalldetailList(TblInstalldetail tblInstalldetail) {
		return installdetailMapper.findInstalldetailList(tblInstalldetail);
	}

	/**
	 * @Title: findInstalldetailCount
	 * @Description: 获取预约安装订单详情总数
	 * @param tblInstalldetail
	 * @return
	 */
	@Override
	public long findInstalldetailCount(TblInstalldetail tblInstalldetail) {
		return installdetailMapper.findInstalldetailCount(tblInstalldetail);
	}

	/**
	 * @Title: getInstalldetail
	 * @Description: 根据主键获取预约安装订单详情
	 * @param pkInstalldetail
	 *        主键
	 * @return
	 */
	@Override
	public TblInstalldetail getInstalldetail(int pkInstalldetail) {
		return installdetailMapper.getInstalldetail(pkInstalldetail);
	}

}
