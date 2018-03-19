package com.wanma.frontendCtrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;

/**
 * FrontEndDispatcherCtrl
 * 
 * @author Haner
 */
@Controller
@RequestMapping("/wmacw/")
public class FrontEndDispatcherCtrl {

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String index() {
		return "frontend/index";
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
	 * 电桩模块
	 * 
	 * @param path
	 * @return
	 */
	@RequestMapping("electric/{path}")
	public String forgetpwd(@PathVariable("path") String path) {
		return "frontend/electric/electric-" + path;
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
	@RequestMapping("my/{path}")
	public String mycenter(@PathVariable("path") String path) {
		return "frontend/my/my-" + path;
	}

	/**
	 * 个人中心子模块
	 * 
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
