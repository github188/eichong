package com.wanma.common.log;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wanma.app.controller.ElectricPileListController;
import com.wanma.dao.CmsCommitLogMapper;
import com.wanma.service.CmsCommitLogService;

/** 
 * 切点类 
 * @author tiangai 
 * @since 2014-08-05 Pm 20:35 
 * @version 1.0 
 */  
@Aspect  
@Component  
public  class SystemLogAspect {  
	
    //注入Service用于把日志保存数据库  
	@Autowired
	private CmsCommitLogMapper commitLogDao;
	@Autowired
	private CmsCommitLogService commitLogService;
	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(ElectricPileListController.class);
	
    //Controller层切点  
	@Pointcut("@annotation(com.wanma.common.log.SystemControllerLog)")
     public  void controllerAspect() {  
    }  
  
    /** 
     * 前置通知 用于拦截Controller层记录用户的操作 
     * 
     * @param joinPoint 切点 
     */  
    @Before("controllerAspect()")  
     public  void doBefore(JoinPoint joinPoint) {  
        //读取session中的用户  
     // 获取登陆用户
        //请求的IP  
         try {
 			commitLogService.insert(getControllerMethodDescription(joinPoint));
            System.out.println("=====前置通知结束=====");  
        }  catch (Exception e) {  
            //记录本地异常日志  
        	log.error("拦截日志异常",e);
        }  
    }  
  
  
    /** 
     * 获取注解中对方法的描述信息 用于Controller层注解 
     * 
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */  
     public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
         for (Method method : methods) {  
             if (method.getName().equals(methodName)) {  
                Class[] clazzs = method.getParameterTypes();  
                 if (clazzs.length == arguments.length) {  
                    description = method.getAnnotation(SystemControllerLog. class).description();  
                     break;  
                }  
            }  
        }  
         return description;  
    }  
}  