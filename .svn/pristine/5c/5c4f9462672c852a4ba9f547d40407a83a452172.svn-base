/*
 * 实现ServletContextListener，获取spring context的类文件
 * @Author John.liu 
 * Created: 2014/04/21
 * Updated: 2014/04/21
 */
package com.bluemobi.product.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bluemobi.product.model.Area;
import com.bluemobi.product.model.City;
import com.bluemobi.product.model.Province;
import com.bluemobi.product.service.impl.AreaServiceImpl;
import com.bluemobi.product.service.impl.CityServiceImpl;
import com.bluemobi.product.service.impl.ProvinceServiceImpl;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblConfigparameter;
import com.wanma.service.impl.CmsConfigcontentServiceImpl;
import com.wanma.service.impl.CmsConfigparameterServiceImpl;

/**
 * 实现ServletContextListener，获取spring context的类 需要在web.xml中配置listener并引用该类
 * 
 * @author John.liu
 */
public class SpringContextHolder implements ServletContextListener {
	// Spring Context
	private static ApplicationContext applicationContext;

	public SpringContextHolder() {
		super();
	}

	/**
	 * 获取WebApplicationContext的方法 该方法必须在启动Spring Listener之后使用
	 * 
	 * @return
	 */
	public static ApplicationContext getSpringContext() {
		return applicationContext;
	}

	/**
	 * 获取Spring管理的数据源
	 * 
	 * @return
	 */
	public static DataSource getSpringPooledDataSource() {
		if (null == applicationContext)
			return null;
		return applicationContext.getBean(DataSource.class);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("web application context destroyed...");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("web application context initialized...");
		ServletContext sc=event.getServletContext();
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		//全局变量初始化进WanmaConstants常量
		CmsConfigparameterServiceImpl configParaService=(CmsConfigparameterServiceImpl) applicationContext
				.getBean("cmsConfigparameterServiceImpl");
		List<TblConfigparameter>  configParaList=configParaService.findConParaList(null);
		CmsConfigcontentServiceImpl configcontentService = (CmsConfigcontentServiceImpl) applicationContext
				.getBean("cmsConfigcontentServiceImpl");
		List<TblConfigcontent> configList=configcontentService.findContentList(null);
		Map<Integer,List<TblConfigcontent>> configMap=new LinkedHashMap<Integer,List<TblConfigcontent>>();
		List<TblConfigcontent> tempList=null;
		int key=0;
		for(TblConfigparameter param:configParaList){
			key=param.getPkConfigparameter();
			tempList=new ArrayList<TblConfigcontent>();
			for(TblConfigcontent config:configList){
				if(config.getCocoConfigparameterid()==key){
					tempList.add(config);
				}
			}
			configMap.put(key, tempList);
		}
		WanmaConstants.configMap=configMap;
		ProvinceServiceImpl provinceService=(ProvinceServiceImpl) applicationContext .getBean("provinceService");
		List<Province> provinceList=provinceService.getProvinceList();
		WanmaConstants.provinceList=provinceList;
		CityServiceImpl cityService=(CityServiceImpl) applicationContext .getBean("cityServiceImpl");
		List<City> cityList=cityService.getCityList(null);
		WanmaConstants.cityList=cityList;
		AreaServiceImpl areaService=(AreaServiceImpl) applicationContext .getBean("areaServiceImpl");
		List<Area> areaList=areaService.getAreaList(null);
		WanmaConstants.areaList=areaList;
		
	}

}
