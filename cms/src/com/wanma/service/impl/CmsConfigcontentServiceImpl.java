package com.wanma.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.common.WanmaConstants;
import com.wanma.dao.CmsConfigcontentMapper;
import com.wanma.model.TblConfigcontent;
import com.wanma.service.CmsConfigcontentService;

/**
 * @Description: 配置参数内容业务处理实现类
 * @author songjf
 * @createTime：2015-4-2 下午02:06:54
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class CmsConfigcontentServiceImpl implements CmsConfigcontentService {
	/* 配置参数内容操作dao */
	@Autowired
	private CmsConfigcontentMapper configcontentMapper;


	/**
	 * @Title: findContent
	 * @Description: 获取配置参数内容
	 * @param params
	 * @return
	 */
	@Override
	public TblConfigcontent findContent(Integer pkConfigcontent) {
		return configcontentMapper.findContent(pkConfigcontent);
	}

	/**
	 * @Title: findContentList
	 * @Description: 获取配置参数内容列表
	 * @param params
	 * @return
	 */
	@Override
	public List<TblConfigcontent> findContentList(
			TblConfigcontent tblConfigcontent) {
		return configcontentMapper.findContentList(tblConfigcontent);
	}

	/**
	 * @Title: findCount
	 * @Description: 获取配置参数内容总数
	 * @param params
	 * @return
	 */
	@Override
	public long findCount(TblConfigcontent tblConfigcontent) {
		return configcontentMapper.findCount(tblConfigcontent);
	}

	/**
	 * @Title: insertContent
	 * @Description: 新增配置参数内容
	 * @param params
	 * @return
	 */
	@Override
	public int insertContent(TblConfigcontent tblConfigcontent) {
		tblConfigcontent.setCocoCreatedate(new Date());
		tblConfigcontent.setCocoUpdatedate(new Date());
		tblConfigcontent
				.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		return configcontentMapper.insertContent(tblConfigcontent);
	}

	/**
	 * @Title: updateContent
	 * @Description: 更新配置参数内容
	 * @param params
	 * @return
	 */
	@Override
	public int updateContent(TblConfigcontent tblConfigcontent) {
		tblConfigcontent.setCocoUpdatedate(new Date());
		return configcontentMapper.updateContent(tblConfigcontent);
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除配置参数内容
	 * @param params
	 * @return
	 */
	@Override
	public int deleteById(TblConfigcontent tblConfigcontent) {
		tblConfigcontent
				.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_INVALID);
		return configcontentMapper.deleteById(tblConfigcontent);
	}
	


	/**
	 * 根据id获取配置表相关列表
	 * @return
	 */
	public List<Map<String, Object>> getConfigContentListByCpId(String cpId){
		return configcontentMapper.getConfigContentListByCpId(cpId);
	}
	
	/**
	 * 获取全部配置表相关列表
	 * @return
	 */
	public List<Map<String, Object>> getAllConfigContentList(){
		return configcontentMapper.getAllConfigContentList();
	}
	
	/**
	 * 根据内容获取配置表id
	 * @return
	 */
	@Override
	public int getConfigIdByContent(Map<String, String> map){
		return configcontentMapper.getConfigIdByContent(map);
	}
}
