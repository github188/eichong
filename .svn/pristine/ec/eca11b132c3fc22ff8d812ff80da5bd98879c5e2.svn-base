package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.base.common.WanmaConstants;
import com.base.util.MultiFileUtil;
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
		if (isFileNotEmpty(file)) {
			// 处理动态首图图片
			MultiFileUtil.saveFile(file, WanmaConstants.DYNAMIC, dynamic.getPkRelease() + "", false);
		}
		return true;
	}
	
	private boolean isFileNotEmpty(MultipartFile[] files) {
		boolean flag=true;
		if(files!=null&&!files.equals("")){
			if(files.length>0){
				for(MultipartFile file:files){
					if(file.getSize()==0){
						flag=false;
						break;
					}
				}
			}			
		}		
		return flag;
	}

	@Override
	public boolean update(TblDynamic dynamic,MultipartFile[] file) {
		dynamicMapper.update(dynamic);
		if (isFileNotEmpty(file)) {
			MultiFileUtil.delelteFile(WanmaConstants.DYNAMIC, String.valueOf(dynamic.getPkRelease()));
			// 处理动态首图图片
	    	MultiFileUtil.saveFile(file, WanmaConstants.DYNAMIC, dynamic.getPkRelease() + "",false);
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
