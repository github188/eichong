package com.wanma.frontendCtrl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;
import com.wanma.model.TblUsercollect;
import com.wanma.web.service.WebUsercollectService;
import com.wanma.web.service.WebUserinfoService;

/**
 * FrontEndDispatcherCtrl
 * web跳转控制路径
 * @author Haner
 */
@Controller
@RequestMapping("/")
public class FrontEndDispatcherCtrl {
	@Autowired
    private WebUsercollectService usercollectService;
    @Autowired
    private WebUserinfoService userinfoService;
	/**
	 * 服务与支持-业务纵览
	 * @return
	 */
	@RequestMapping("/serverbusiness")
	public String serverbusiness()  { 
		return "frontend/web2/serverBusiness";
	}
	
	/**
	 * 服务与支持-联系我们
	 * @return
	 */
	@RequestMapping("/contactus")
	public String contactus()  { 
		return "frontend/web2/contactUs";
	}
	
	/**
	 * 服务与支持-信息反馈
	 * @return
	 */
	@RequestMapping("/inforfeedback")
	public String inforfeedback()  { 
		return "frontend/web2/inforFeedback";
	}
	
	/**
	 * 关于我们-公司介绍
	 * @return
	 */
	@RequestMapping("/companyintroduction")
	public ModelAndView companyintroduction(HttpServletRequest request)  { 
		String lb=request.getParameter("lb");
		String detailId=request.getParameter("detailId");
		ModelMap map = new ModelMap();
		map.put("lb", lb);
		map.put("detailId", detailId);
		return new ModelAndView("frontend/web2/companyIntroduction", map);
	}
	
	
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "frontend/index2";
    }

    /**
     * 登陆
     *
     * @return
     */
    @RequestMapping("login")
    public String login() {
        return "frontend/login";
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping("regist")
    public String reg() {
        return "frontend/register";
    }

    
    /**
     * 忘记密码
     *
     * @return
     */
    @RequestMapping("reset")
    public String forgetpwd() {
        return "frontend/reset";
    }


    /**
     * 选择地址
     *
     * @return
     */
    @RequestMapping("chooseCity")
    public String chooseCity() {
        return "frontend/choose-city";
    }

    /**
     * 关于我们
     *
     * @return
     */
    @RequestMapping("about")
    public String about() {
        return "frontend/about";
    }

    /**
     * 电桩查询
     *
     * @param path
     * @return
     */
    @RequestMapping("electric/search")
    public String electric() {
    	ModelMap map = new ModelMap();
		map.put("lb", 1);
		return "frontend/web2/electric-search";
    }
    
    
    /**
     * 电站模块
     *
     * @param path
     * @return
     */
    @RequestMapping("electric/station")
    public ModelAndView electricStation() {
    	ModelMap map = new ModelMap();
		map.put("lb", 1);
		return new ModelAndView("frontend/web2/companyIntroduction", map);
    }


    /**
     * 新能源模块
     *
     * @param path
     * @return
     */
    @RequestMapping("energy/{path}")
    public String energy(@PathVariable("path") String path) {
        return "frontend/energy/energy-" + path;
    }

    /**
     * 服务与支持模块
     *
     * @param path
     * @return
     */
    @RequestMapping("support/{path}")
    public String support(@PathVariable("path") String path) {
        return "frontend/support/support-" + path;
    }

    /**
     * 个人中心模块首页
     *
     * @param path
     * @return
     */
    @RequestMapping("/mycenter")
    public ModelAndView mycenter(HttpServletRequest request) {
    	TblUser user=SessionMgr.getWebUser(request);
    	ModelMap map=new ModelMap();
    	map.put("user", user);
    	TblUsercollect collect=new TblUsercollect();
    	collect.setUscoUserid(user.getUserId().intValue());
    	//用户收藏
    	List<TblUsercollect> collectList=usercollectService.getUserCollect(collect);
    	Integer collectListSize = collectList.size();
    	map.put("collectListSize", collectListSize);
    	map.put("collectList", collectList);
    	String lb=request.getParameter("lb");
    	map.put("lb", lb);
        return new ModelAndView("frontend/web2/mycenter",map);
    }

    /**
     * 个人中心子模块
     * @param model
     * @param item
     * @return
     */
    @RequestMapping("my/{model}/{item}")
    public String mycenteritem(@PathVariable("model") String model,
                               @PathVariable("item") String item) {
        return "frontend/my/my-" + model + "/my-" + item;
    }

}

































