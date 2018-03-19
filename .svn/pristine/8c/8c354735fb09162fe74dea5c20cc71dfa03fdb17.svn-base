package com.wanma.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wanma.model.TblDynamic;

public interface CmsDynamicService {

	
	public List<TblDynamic> list(TblDynamic dynamic);
	public long count(TblDynamic dynamic);
	public TblDynamic get(Integer pkRelease);
	public boolean add(TblDynamic dynamic,MultipartFile[] file);
	public boolean update(TblDynamic dynamic,MultipartFile[] file);
	public boolean disableDynamic(TblDynamic dynamic);
	public boolean deleteByIds(String ids);
	public String getUploadImgUrl(String busiType,String refId);
	
}
