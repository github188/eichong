/**
 * FileName:InitDeployUrlServelt.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.bluemobi.product.batch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.common.dao.InitDeployUrlDao;
import com.bluemobi.product.utils.SpringContextHolder;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblConfigcontent;
import com.wanma.service.impl.CmsConfigcontentServiceImpl;
import com.wanma.service.impl.CmsRateInfoServiceImpl;

/**
 * 初始化发布环境信息
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class InitDeployUrlServelt extends HttpServlet {
	private static final Logger logger = Logger
			.getLogger(InitDeployUrlServelt.class);

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -1447273313093174751L;

	/**
	 * 初始化发布环境信息
	 */
	public void init() throws ServletException {
		String deployUrl;
		String parentPath;
		String imagesCanUrl;
		ServletContext servletContext = this.getServletContext();

		MessageManager manager = MessageManager.getMessageManager();

		deployUrl = manager.getSystemProperties("deploy.url");
		parentPath = manager.getSystemProperties("storage.path.real.file");
		imagesCanUrl = manager.getSystemProperties("picture.service.scanUrl");
		if (StringUtil.isEmpty(deployUrl)) {
			deployUrl = "http://localhost:8080";
		}

		if (StringUtil.isEmpty(parentPath)) {
			parentPath = "storage";
		}

		InitDeployUrlDao dao = new InitDeployUrlDao();
		dao.initDeployInfo(deployUrl, parentPath, imagesCanUrl);
		String path = servletContext
				.getRealPath("/WEB-INF/classes/data-auth-setting.xml");
		String confiPath = "";
		String os = this.getOSName();
		if (os.startsWith("windows")) {
			confiPath = path.substring(0, path.lastIndexOf("\\"));
		} else {
			confiPath = path.substring(0, path.lastIndexOf("/"));
		}
		System.setProperty("user.dir", confiPath);
		// long begin = System.currentTimeMillis();

		/*
		 * GameServer gameServer; try { // 启动网络服务器 gameServer =
		 * GameServer.getInstance(); // gameServer.start(); } catch (Exception
		 * e) { e.printStackTrace(); }
		 */

		// long end = System.currentTimeMillis();
		// logger.info("服务器启动成功！启动耗时：【" + (end - begin) / 1000d + "】秒");
		ApplicationContext context = SpringContextHolder.getSpringContext();
		CmsRateInfoServiceImpl rateInfoService = (CmsRateInfoServiceImpl) context
				.getBean("cmsRateInfoServiceImpl");
		CmsConfigcontentServiceImpl configContentServiceImpl = (CmsConfigcontentServiceImpl) context
				.getBean("cmsConfigcontentServiceImpl");
		List<HashMap<String, Object>> proList = rateInfoService
				.searchProvinceList(null);
		List<HashMap<String, Object>> cityList = rateInfoService
				.searchCityList(null);
		List<HashMap<String, Object>> areaList = rateInfoService
				.searchAreaList(null);
		Map<String, Object> provinceMap = new HashMap<String, Object>();
		Map<String, List<String>> provinceCityMap = new HashMap<String, List<String>>();
		for (HashMap<String, Object> map : proList) {
			provinceMap.put(map.get("PROVINCE_ID").toString(), map);
			provinceCityMap.put(map.get("PROVINCE_ID").toString(),
					new ArrayList<String>());
		}
		Map<String, Object> cityMap = new HashMap<String, Object>();
		Map<String, List<String>> cityAreaMap = new HashMap<String, List<String>>();
		String tempId = "";
		String tempParentId = "";
		List<String> tempList = null;
		for (HashMap<String, Object> map : cityList) {
			tempId = map.get("CITY_ID").toString();
			tempParentId = map.get("PROVINCE_ID").toString();
			cityMap.put(tempId, map);
			tempList = provinceCityMap.get(tempParentId);
			tempList.add(tempId);
			provinceCityMap.put(tempParentId, tempList);
			cityAreaMap.put(tempId, new ArrayList<String>());
		}
		Map<String, Object> areaMap = new HashMap<String, Object>();
		int i = 0;
		for (HashMap<String, Object> map : areaList) {
			tempId = map.get("area_id").toString();
			tempParentId = map.get("city_id").toString();
			areaMap.put(tempId, map);
			tempList = cityAreaMap.get(tempParentId);
			if (tempList != null) {
				tempList.add(tempId);
			}
			cityAreaMap.put(tempParentId, tempList);

		}

		List<Map<String, Object>> list = configContentServiceImpl
				.getConfigContentListByCpId("");
		// key值定义,1:电桩类型 (壁挂式);2:电桩使用类型(电动车);3:充电方式;4:功率;5:接口;6:枪头状态;7:搜索范围
		// ;8:跟6重复;: ;: ;: ;
		// 9: ;10: ;11:制造厂商 ;12:电桩状态 ;13:绑定 ;14:预约支持 ;15:付款方式 ;29:故障类型 ;
		Integer key = (Integer) list.get(0).get("coCo_ConfigParameterID");

		Map<String, Object> tempMap = new LinkedHashMap<String, Object>();
		int paramKey = 0;
		for (Map<String, Object> map : list) {
			paramKey = (Integer) map.get("coCo_ConfigParameterID");
			if (key != paramKey) {
				servletContext.setAttribute("param" + key, tempMap);
				key = paramKey;
				tempMap = new LinkedHashMap<String, Object>();

			}
			Integer id = (Integer) map.get("pk_ConfigContent");
			tempMap.put(id + "", map);
		}
		// 最后的属性循环中未设置，此处补上
		servletContext.setAttribute("param" + key, tempMap);
		// 功率需要排序下~~
		list = configContentServiceImpl.getConfigContentListByCpId("4");
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				String s1 = (String) o1.get("coCo_Content");
				String s2 = (String) o2.get("coCo_Content");
				if (Float.parseFloat(s1.substring(0, s1.length() - 2)) > Float
						.parseFloat(s2.substring(0, s2.length() - 2))) {
					return 1;
				}
				return -1;
			}
		});
		tempMap = new LinkedHashMap<String, Object>();
		for (Map<String, Object> map : list) {
			paramKey = (Integer) map.get("coCo_ConfigParameterID");
			Integer id = (Integer) map.get("pk_ConfigContent");
			tempMap.put(id + "", map);
		}
		servletContext.setAttribute("param4", tempMap);
		WanmaConstants.provinceMap = provinceMap;
		WanmaConstants.provinceCityMap = provinceCityMap;
		WanmaConstants.cityMap = cityMap;
		WanmaConstants.cityAreaMap = cityAreaMap;
		WanmaConstants.areaMap = areaMap;
		servletContext.setAttribute("provinceMap", provinceMap);
	}

	/**
	 * 获取当前操作系统名称. return 操作系统名称 例如:windows xp,linux 等.
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}
}
