package com.wanma.web.support.global;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bluemobi.product.common.MessageManager;
import com.wanma.web.service.impl.WebConfigContentServiceImpl;

@SuppressWarnings("serial")
public class GlobalParamServlet extends HttpServlet{
	private static Logger log = LoggerFactory.getLogger(GlobalParamServlet.class);
	
	@Override
	public void init() throws ServletException {
		//敏感词
		//RedisClient client=new RedisClient();
 		//log.info("敏感词数量：" + client.getSensitiveWordList().size());
		ServletContext sc=getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		WebConfigContentServiceImpl webConfigContentServiceImpl=(WebConfigContentServiceImpl) ac.getBean("webConfigContentServiceImpl");
		List<Map<String, Object>> list= webConfigContentServiceImpl.getConfigContentListByCpId("");
		//key值定义,1:电桩类型 (壁挂式);2:电桩使用类型(电动车);3:充电方式;4:功率;5:接口;6:枪头状态;7:搜索范围 ;8:跟6重复;: ;: ;: ;
		//9:汽车品牌 ;10: ;11:制造厂商 ;12:电桩状态 ;13:绑定 ;14:预约支持 ;15:付款方式 ;29:故障类型 ;
		int key=(Integer) list.get(0).get("coCo_ConfigParameterID");
		Map<String, Object> tempMap=new LinkedHashMap<String, Object>();
		int paramKey=0;
		for(Map<String, Object> map:list){
			paramKey=(Integer)map.get("coCo_ConfigParameterID");
			if(key!=paramKey){
				sc.setAttribute("param"+key, tempMap);
				key=paramKey;
				tempMap=new LinkedHashMap<String, Object>();
				
			}
			int id=(Integer) map.get("pk_ConfigContent");
			tempMap.put(id+"", map);
		}
		//是否引用js压缩文件
		MessageManager messageManager=MessageManager.getMessageManager();
		String jsZip=messageManager.getSystemProperties("jsZip");
		sc.setAttribute("jsZip",jsZip);
		String appShareUrl=messageManager.getSystemProperties("appShareUrl");
		sc.setAttribute("appShareUrl",appShareUrl);
		String frontVersion=messageManager.getSystemProperties("frontVersion");
		sc.setAttribute("frontVersion",frontVersion);
	}
	
	
}
