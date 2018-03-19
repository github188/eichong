package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bluemobi.product.common.dao.MultipartFileDao;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.common.WanmaConstants;
import com.wanma.dao.CmsDynamicMapper;
import com.wanma.model.TblDynamic;
import com.wanma.service.CmsDynamicService;

@Service
public class CmsDynamicServiceImpl implements CmsDynamicService{
	@Autowired
	private CmsDynamicMapper dynamicMapper;
	
	@Override
	public List<TblDynamic> list(TblDynamic dynamic) {
		return dynamicMapper.list(dynamic);
	}

	@Override
	public long count(TblDynamic dynamic) {
		return dynamicMapper.count(dynamic);
	}
	
	@Override
	public TblDynamic get(Integer pkRelease) {
		return dynamicMapper.get(pkRelease);
	}
	@Override
	public boolean add(TblDynamic dynamic,MultipartFile[] file) {
		dynamicMapper.add(dynamic);
		if (ObjectUtil.isFileNotEmpty(file)) {
			dynamic.setAllMultiFile(file);
			// 处理动态首图图片
			MultipartFileUtil.saveAllMulti(dynamic, WanmaConstants.DYNAMIC, dynamic.getPkRelease() + "");
		}
		return true;
	}

	@Override
	public boolean update(TblDynamic dynamic,MultipartFile[] file) {
		dynamicMapper.update(dynamic);
		if (ObjectUtil.isFileNotEmpty(file)) {
			dynamic.setAllMultiFile(file);
			MultipartFileDao multipartFileDao = new MultipartFileDao();
			List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(WanmaConstants.DYNAMIC, String.valueOf(dynamic.getPkRelease()));
	    	MultipartFileUtil.delelteMulti(allMultiFileName,WanmaConstants.DYNAMIC, String.valueOf(dynamic.getPkRelease()));
			// 处理动态首图图片
			MultipartFileUtil.saveAllMulti(dynamic, WanmaConstants.DYNAMIC, dynamic.getPkRelease() + "");
		}
		return true;
	}

	@Override
	public boolean disableDynamic(TblDynamic dynamic) {
		return dynamicMapper.disableDynamic(dynamic);
	}

	@Override
	public boolean deleteByIds(String ids) {
		return dynamicMapper.deleteByIds(ids);
	}

	@Override
	public String getUploadImgUrl(String busiType, String refId) {
		return dynamicMapper.getUploadImgUrl(busiType,refId);
	}

	

}
