package com.wanma.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblCarinfo;

/**
 * @Description: 电动车品牌类型
 * @author songjf
 * @createTime：2015-4-1 下午07:33:56
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface WebCarCompanyMapper {
	
	public final static String PREFIX = WebCarCompanyMapper.class.getName();
	/**
	 * 增加汽车厂家
	 * @param params
	 */
	public  void  insertCarCompany(Map<String, Object> params);
	
	/**
	 * 修改汽车厂家
	 * @param params
	 */
    public void  updateCarCompany(Map<String, Object> params);
    
    /**
     * 通过ID删除汽车厂家
     * @param params
     */
    public void deleteCarCompanyById(Map<String, Object> params);
    
    /**
     * 查询汽车厂家列表
     * @param params
     * @return
     */
    public List<HashMap<String, Object>> findCarCompanyList(Map<String, Object> params);
    
    public long findCarCompanyCount(Map<String, Object> params);
    
    /**
     * 根据ID查找摸个汽车厂家
     * @param params
     * @return
     */
    
    public HashMap<String, Object> findCarCompanyById(Map<String, Object> params);
    
    /**
     * 根据ID查找摸个汽车厂家 是否有用该名字命名的厂家
     * @param params
     * @return
     */
    
    public HashMap<String, Object> getByProperty(Map<String, Object> params);

  

}
