package com.wanma.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.common.WebConst;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;

/**
  * @Description:禁止一个用户多次登录
  * @author songjf 
  * @createTime：2015-4-2 下午07:07:20 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class SingleFilter implements Filter{
	
	private static Logger log = Logger.getLogger(SingleFilter.class);	//日志记录
    private FilterConfig filterConfig;
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        /**
         * 如果用户没有登陆，则登陆，并将登陆信息放到application，
         * <P>
         * 信息为用户id，用户sessionid，用户登陆时间，登陆IP
         * <P>
         * 如果用户已登陆，则每次请求要检查application，
         * <P>
         * 一旦用户id相同而sessionid不同，即表明该用户在其它地方登陆，
         * <P>
         * 当前登陆无条件注销
         * <P>
         * 注销过程为：将当前session失效，转到登陆页面，
         * <P>
         * 提示用户该用户id已在哪台机器什么时间登陆了，当前登陆已注销
         */
        TblUser loginUser = SessionMgr.getWebUser(request);
       log.info("----SingleFilter.isLogin = "+loginUser);
       if (loginUser !=null) {
//    	   登录用户id
           String UserId = loginUser.getUserId()+"";
           String curSessionid = session.getId();
			@SuppressWarnings("unchecked")
			Map<String,Object> single = (Map<String,Object>) session.getServletContext().getAttribute(UserId); 
           // 如果已经有登陆信息
           if (single != null) {
               String hisSesssionid = (String) single.get("sessionId");
               String ip = (String) single.get("ip");
               Date date = (Date) single.get("date");
               String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
               if (!curSessionid.equals(hisSesssionid)) {
                   String jspMess = "当前用户已于" + dateString + "从" + ip + "登陆到了服务器上，当前登陆已被注销！";
                   request.setAttribute("erMessage", jspMess);
                   SessionMgr.removeWebUser(request);
                   MessageManager manager = MessageManager.getMessageManager();
                  String servicHost = manager.getSystemProperties("deploy.url");
                  request.setAttribute("servicHost", servicHost);
                  filterConfig.getServletContext().getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
                   return;
               }
           }
       }
        chain.doFilter(request, response);
    }
    
    public void destroy() {
    	
    }
    
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

}
