/**
 * FileName:InitDeployUrlServelt.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.bluemobi.product.batch;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.utils.HttpUtil;
import com.bluemobi.product.utils.StringUtil;
import com.bluemobi.server.GameServer;
import com.wanma.app.service.AppCityService;
import com.wanma.common.ApplicationContextUtils;
import com.wanma.common.WanmaConstants;

/**
 * 初始化发布环境信息
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class InitDeployUrlServelt extends HttpServlet {
	private static final Logger logger = Logger.getLogger(InitDeployUrlServelt.class);

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

		/*InitDeployUrlDao dao = new InitDeployUrlDao();
		dao.initDeployInfo(deployUrl, parentPath,imagesCanUrl);*/
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
		long begin = System.currentTimeMillis();

		GameServer gameServer;
		try {
			// 启动网络服务器
			gameServer = GameServer.getInstance();
			cacheLocationData();
			// gameServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("服务器启动成功！启动耗时：【" + (end - begin) / 1000d + "】秒");
	}
	
	private void cacheLocationData() throws IOException{
		AppCityService appCityService = (AppCityService) ApplicationContextUtils.getBean("appCityServiceImpl");
		List<Map<String, String>> list = appCityService.getCityAndProvince();
		Map<String, String> jwdMap = WanmaConstants.jwdMap;
		for(Map<String, String> map:list){
			String pId = map.get("pId");
			String cId = map.get("cId");
			String pName = map.get("pName");
			String cName = map.get("cName");
			Object jwd = jwdMap.get(pId);
			if(jwd == null)
				setLocation(pId, pName, jwdMap);
			setLocation(cId, cName, jwdMap);
		}
	}
	
	private static void setLocation(String code,String searchName,Map<String, String> jwdMap) throws IOException{
		String url = WanmaConstants.GEOCODE_WEBAPI_ROOTPATH+"geo?key="+WanmaConstants.GEOCODE_MAP_KEY+"&address="+searchName;
		JSONArray geocodes = JSON.parseArray(JSON.parseObject(HttpUtil.getGeocodeMapStr(url)).get("geocodes").toString());
		if(geocodes.size() > 0)
			jwdMap.put(code, ((JSONObject)geocodes.get(0)).get("location").toString());
	}

	/**
	 * 获取当前操作系统名称. return 操作系统名称 例如:windows xp,linux 等.
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}
}
