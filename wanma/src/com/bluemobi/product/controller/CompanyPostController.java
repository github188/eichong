/**
 * FileName:PostController.java
 * Author: Administrator
 * Create: 2014年7月4日
 * Last Modified: 2014年7月4日
 * Version: V1.0 
 *//*
package com.bluemobi.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.common.WebConst;
import com.bluemobi.product.model.CompanyPostModel;
import com.bluemobi.product.model.UserModel;
import com.bluemobi.product.model.UserPostModel;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.model.common.TreeModel;
import com.bluemobi.product.service.CompanyPostService;
import com.bluemobi.product.service.UserService;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.TreeUtil;
import com.bluemobi.product.utils.UserUtil;

*//**
 * 职位相关处理控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月4日
 *//*

@Controller
@RequestMapping("/admin/post")
public class CompanyPostController {

	*//** 日志文件生成器 *//*
	private static Logger log = Logger.getLogger(CompanyPostController.class);

	*//** 职位业务处理对象 *//*
	@Autowired
	private CompanyPostService postService;

	*//** 用户业务处理对象 *//*
	@Autowired
	private UserService userService;

	*//**
	 * 职位登录处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/postList")
	public String getPostList(@ModelAttribute("pager") DwzPagerMySQL pager,
			Model model) {

		// 登录职位信息
		List<CompanyPostModel> postList = null;

		// 取得职位列表
		postList = postService.getCompanyPostList();

		// 将职位信息放到会话中
		model.addAttribute("postList", postList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "admin/post/listPost";
	}

	*//**
	 * 职位添加初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/newPost")
	public String newPost(@RequestParam("companyId") String companyId,
			Model model, HttpServletRequest request) {

		// 清空职位用户Session中的信息
		clearPostUserSession(request);

		String postId = "";
		CompanyPostModel postModel = new CompanyPostModel();

		postId = ProcessInfoCommon.getRandomKey();

		postModel.setPostId(postId);
		postModel.setCompanyId(companyId);
		// 将职位信息设置到画面显示对象
		model.addAttribute("postModel", postModel);
		// 跳转至职位添加页面
		return "admin/post/post";
	}

	*//**
	 * 职位添加处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postModel
	 *            职位输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/savePost", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String savePost(
			@ModelAttribute("postModel") CompanyPostModel postModel,
			BindingResult result, HttpServletRequest request, Model model)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 职位添加的结果信息
		String processResult;

		// 职位用户列表
		List<UserPostModel> userPostList = null;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "postAddPage", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setCreateUserInfo(postModel, request);

		// 取得职位用户列表
		userPostList = getPostUserBySession(postModel, request);

		try {
			// 执行职位添加处理
			postService.addCompanyPost(postModel, userPostList);
			processResult = CommonConsts.PROCESS_STATUS_OK;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			processResult = CommonConsts.PROCESS_STATUS_NG;
		}

		// 如果更新职位处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回职位一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "postM", "", "");
			// 如果更新职位处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "postM", "", "");
		}
		// 刷新职位树
		refreshPostTree(model);

		// 将职位信息设置到画面显示对象
		model.addAttribute("postModel", postModel);

		request.getSession().setAttribute(WebConst.SESS_POST_INFO, postModel);
		request.getSession().setAttribute(WebConst.SESS_POST_USERS, postModel);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	*//**
	 * 职位拷贝初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postId
	 *            职位ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/copyPost", method = RequestMethod.GET)
	public String copyPost(@RequestParam("companyId") String companyId,
			@RequestParam("postId") String postId, Model model) {

		// 取得拷贝对象职位信息
		CompanyPostModel postModel = getPostData(companyId, postId);

		// 新职位信息的设置
		postModel.setPostId(ProcessInfoCommon.getRandomKey());

		// 清空职位信息
		ProcessInfoCommon.clearProcessUser(postModel);

		// 将职位信息设置到画面显示对象
		model.addAttribute("postModel", postModel);

		// 跳转至职位编辑页面
		return "admin/post/post";
	}

	*//**
	 * 职位编辑初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postId
	 *            职位ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/editPost", method = RequestMethod.GET)
	public String editPost(@RequestParam("modelKey") String modelKeys,
			Model model, HttpServletRequest request) {

		// 清空职位用户Session中的信息
		clearPostUserSession(request);

		// 职位key
		String[] postIds = modelKeys.split(",");

		// 公司ID
		String companyId = "";
		// 职位ID
		String postId = "";
		// 职位用户列表
		List<UserModel> userList = null;

		// 对象职位信息
		CompanyPostModel postModel = null;

		if (postIds.length == 1) {
			companyId = postIds[0];
			return "redirect:/admin/post/newPost.do?companyId=" + companyId;
		}

		if (postIds.length == 2) {
			// 公司ID
			companyId = postIds[0];
			// 职位ID
			postId = postIds[1];

			// 取得对象职位信息
			postModel = getPostData(companyId, postId);

			// 职位用户列表
			userList = getPostUserList(companyId, postId);
		}

		// 将职位信息设置到画面显示对象
		model.addAttribute("postModel", postModel);

		// 将用户信息放到会话中
		model.addAttribute("userList", userList);

		request.getSession().setAttribute(WebConst.SESS_POST_INFO, postModel);
		request.getSession().setAttribute(WebConst.SESS_POST_USERS, userList);

		// 跳转至职位编辑页面
		return "admin/post/post";
	}

	*//**
	 * 职位编辑处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postModel
	 *            职位输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/modifyPost", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyPost(
			@ModelAttribute("postModel") CompanyPostModel postModel,
			BindingResult result, Model model, HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;
		// 更新处理结果
		String processResult = "";
		// 职位用户列表
		List<UserPostModel> userPostList = null;

		// 对象职位信息
		CompanyPostModel newModel = null;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "postM", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		// 设置更新者用户信息
		ProcessInfoCommon.setUpdateUserInfo(postModel, request);

		// 取得职位用户列表
		userPostList = getPostUserBySession(postModel, request);

		try {
			// 执行职位更新处理
			postService.modifyCompanyPost(postModel, userPostList);
			processResult = CommonConsts.PROCESS_STATUS_OK;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			processResult = CommonConsts.PROCESS_STATUS_NG;
		}

		// 如果更新职位处理成功
		if (CommonConsts.PROCESS_STATUS_OK.equals(processResult)) {
			// 设置成功并返回职位一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "postM", "", "");
			// 如果更新职位处理不成功
		} else {
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "postM", "", "");
		}

		// 刷新画面职位树
		refreshPostTree(model);

		// 取得对象职位信息
		newModel = getPostData(postModel.getCompanyId(), postModel.getPostId());

		// 将职位信息设置到画面显示对象
		model.addAttribute("postModel", newModel);

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	*//**
	 * 职位删除处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postId
	 *            职位ID
	 * @param model
	 *            画面显示对象
	 * @return String 处理结果信息
	 * @throws 无
	 *//*
	@RequestMapping(value = "/removePost")
	@ResponseBody
	public String removePost(
			@ModelAttribute("postModel") CompanyPostModel postModel) {

		// 执行删除处理
		postService.removeCompanyPost(postModel);

		// 设置处理结果信息
		DwzAjaxResult result = new DwzAjaxResult("200", "删除成功", "postM", "", "");

		// 返回处理结果信息
		return new JsonObject(result).toString();
	}

	*//**
	 * 职位用户删除处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/removePostUser")
	public String removePostUser(@RequestParam("userId") String userId,
			Model model, HttpServletRequest request) {

		// 用户列表
		List<UserModel> userList = null;

		// 取得选择对象用户信息
		UserModel userModel = userService.findUser(userId);

		// 追加用户对象到session
		userList = deletePostUser(userModel, request);
		//
		// 将用户信息放到画面显示对象中
		//
		model.addAttribute("userSelectList", userList);

		// // 跳转至用户列表显示AJAX画面
		return "admin/common/userAddAjax";
	}

	*//**
	 * 删除并返回用户列表（将修改后的用户列表放到session）
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param userModel
	 *            用户对象
	 * @param request
	 *            画面请求对象
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@SuppressWarnings("unchecked")
	private List<UserModel> deletePostUser(UserModel userModel,
			HttpServletRequest request) {

		// 用户列表
		List<UserModel> userList = null;
		// 追加用户
		List<UserModel> userAddList = null;
		// 删除用户
		List<UserModel> userDeleteList = null;

		//
		// 从会话中取得用户列表
		//
		userList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_POST_USERS);
		userAddList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_POST_ADD_USERS);
		userDeleteList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_POST_DELETE_USERS);

		if (ObjectUtil.isEmpty(userList)) {
			userList = new ArrayList<UserModel>();
		}

		if (ObjectUtil.isEmpty(userDeleteList)) {
			userDeleteList = new ArrayList<UserModel>();
		}

		//
		// 如果当前用户列表中含有该用户，从列表中删除
		//
		if (UserUtil.containsUser(userList, userModel)) {
			UserUtil.removeUser(userList, userModel);
			if (UserUtil.containsUser(userAddList, userModel)) {
				UserUtil.removeUser(userAddList, userModel);
			} else {
				userDeleteList.add(userModel);
			}
		}

		//
		// 将用户列表放到会话中
		//
		request.getSession().setAttribute(WebConst.SESS_POST_ADD_USERS,
				userAddList);
		request.getSession().setAttribute(WebConst.SESS_POST_DELETE_USERS,
				userDeleteList);
		request.getSession().setAttribute(WebConst.SESS_POST_USERS, userList);

		// 返回用户列表
		return userList;
	}

	*//**
	 * 职位唯一性检查
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param postId
	 *            职位ID
	 * @return String 处理结果信息
	 * @throws 无
	 *//*
	@RequestMapping(value = "/checkPostUnique")
	@ResponseBody
	public String checkPostUnique(@RequestParam("companyId") String companyId,
			@RequestParam("postId") String postId) {

		// 职位对象
		CompanyPostModel postModel = new CompanyPostModel();
		postModel.setCompanyId(companyId);
		postModel.setPostId(postId);

		// 返回处理结果信息
		return postService.checkPostUnique(postModel);
	}

	*//**
	 * 用户查看初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/postMain")
	public String postMain(Model model, HttpServletRequest request) {

		String strHtml = getPostTree(false);

		// 将用户信息设置到画面显示对象
		model.addAttribute("postTreeModel", strHtml);

		// 跳转至用户查看页面
		return "admin/post/mainFrame";
	}

	*//**
	 * 用户查看初始化处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@RequestMapping(value = "/postPostTree")
	public String postPostTree(Model model, HttpServletRequest request) {
		
		 * TreeUtil treeUtil = new TreeUtil(); List<TreeModel> treeList =
		 * treeUtil.getPostTreeData(); treeUtil.setHref("post/editPost.do");
		 * treeUtil.setIsPutParameter(true); treeUtil.setTarget("ajax");
		 * treeUtil.setRel("jbsxBox");
		 
		String strHtml = getPostTree(true);

		// 将用户信息设置到画面显示对象
		model.addAttribute("postTreeModel", strHtml);

		// 跳转至用户查看页面
		return "admin/post/mainFrame";
	}

	*//**
	 * 刷新职位画面
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param model
	 *            画面显示对象
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 *//*
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/refreshPostPage")
	public String refreshPostPage(Model model, HttpServletRequest request) {

		// 职位用户列表
		List<UserModel> userList = null;

		// 对象职位信息
		CompanyPostModel postModel = null;

		postModel = (CompanyPostModel) request.getSession().getAttribute(
				WebConst.SESS_POST_INFO);
		userList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_POST_USERS);

		// 将职位信息设置到画面显示对象
		model.addAttribute("postModel", postModel);

		// 将用户信息放到会话中
		model.addAttribute("userList", userList);

		// 跳转至用户查看页面
		return "admin/post/post";

	}

	private String getPostTree(boolean isPost) {
		String strHtml = "";
		TreeUtil treeUtil = new TreeUtil();
		List<TreeModel> treeList = null;

		treeList = treeUtil.getPostTreeData();
		treeUtil.setHref("post/editPost.do");
		treeUtil.setRel("postBox");

		treeUtil.setIsPutParameter(true);
		treeUtil.setTarget("ajax");
		strHtml = treeUtil.writeTree(treeList);

		return strHtml;
	}

	public void refreshPostTree(Model model) {
		String strHtml = getPostTree(false);

		// 将用户信息设置到画面显示对象
		model.addAttribute("postTreeModel", strHtml);

	}

	*//**
	 * 取得职位信息
	 * 
	 * @param companyId
	 *            职位ID
	 * @param postId
	 *            职位ID
	 * @param CompanyPostModel
	 *            职位信息
	 *//*
	public CompanyPostModel getPostData(String companyId, String postId) {

		// 查询用职位对象
		CompanyPostModel searchModel = new CompanyPostModel();

		// 职位ID
		searchModel.setCompanyId(companyId);
		// 职位ID
		searchModel.setPostId(postId);

		// 取得职位信息
		CompanyPostModel postModel = postService.findCompanyPost(searchModel);

		// 返回职位
		return postModel;

	}

	*//**
	 * 取得职位用户列表
	 * 
	 * @param companyId
	 *            职位ID
	 * @param postId
	 *            职位ID
	 * @param List
	 *            <UserModel> 职位用户列表
	 *//*
	private List<UserModel> getPostUserList(String companyId, String postId) {

		// 职位用户列表
		List<UserModel> userList = null;
		// 查询用职位对象
		CompanyPostModel searchModel = new CompanyPostModel();

		// 职位ID
		searchModel.setCompanyId(companyId);
		// 职位ID
		searchModel.setPostId(postId);

		// 取得职位用户列表
		userList = userService.getPostUserList(searchModel);

		// 返回职位用户列表
		return userList;

	}

	*//**
	 * 清空职位用户Session中的信息
	 * 
	 * @param request
	 *//*
	@SuppressWarnings("unchecked")
	private List<UserPostModel> getPostUserBySession(
			CompanyPostModel postModel, HttpServletRequest request) {

		// 职位用户列表
		List<UserPostModel> userPostList = null;
		// 追加对象职位用户列表
		List<UserModel> addUserList = null;
		// 删除对象职位用户列表
		List<UserModel> deleteUserList = null;

		addUserList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_POST_ADD_USERS);
		deleteUserList = (List<UserModel>) request.getSession().getAttribute(
				WebConst.SESS_POST_DELETE_USERS);

		userPostList = UserUtil.mergeProcessUser(addUserList, deleteUserList,
				postModel);

		// 职位用户
		// request.getSession().setAttribute(WebConst.SESS_POST_USERS, null);
		// 追加对象职位用户
		request.getSession().setAttribute(WebConst.SESS_POST_ADD_USERS, null);
		// 删除对象职位用户
		request.getSession()
				.setAttribute(WebConst.SESS_POST_DELETE_USERS, null);

		return userPostList;
	}

	*//**
	 * 清空职位用户Session中的信息
	 * 
	 * @param request
	 *//*
	private void clearPostUserSession(HttpServletRequest request) {
		// 职位用户
		request.getSession().setAttribute(WebConst.SESS_POST_USERS, null);
		// 追加对象职位用户
		request.getSession().setAttribute(WebConst.SESS_POST_ADD_USERS, null);
		// 删除对象职位用户
		request.getSession()
				.setAttribute(WebConst.SESS_POST_DELETE_USERS, null);
	}
}
*/