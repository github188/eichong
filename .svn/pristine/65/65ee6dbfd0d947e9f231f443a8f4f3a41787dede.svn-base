package com.wanma.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.WebConst;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JPushUtil;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.app.service.AppJpushService;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblJpush;
import com.wanma.model.TblUser;
import com.wanma.model.TblUsermessage;
import com.wanma.service.impl.CmsUserMessageServiceImpl;

/**
 * @Description: 消息controller
 * @author wubc
 * @createTime：2015-5-27 
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/message")
public class CmsMessageController extends BaseController{
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsMessageController.class);
	private String navTabId="message";
	@Autowired
	private CmsUserMessageServiceImpl cmsUserMessageService;
	@Autowired
	private AppJpushService jpushService;
	/**
	 * @Title: newmessage
	 * @Description: 跳转至消息推送页面
	 * @param message
	 * @return
	 */
	@RequestMapping("/toPush")
	public String toPush(Model model) {
		log.info("******************跳转至消息推送页面************************");
		return "backstage/message/message-push";
	}
	
	/**
	 * @Title: newmessage
	 * @Description: 消息推送
	 * @param message
	 * @return
	 */
	@RequestMapping("/push")
	@ResponseBody
	public String push(Model model,TblUsermessage message) {
		log.info("******************消息推送-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			message=cmsUserMessageService.getOne(message);
			int userId=message.getUserId();
			//推送消息
			if(userId==0){
				JPushUtil.pushAll(message.getTitle(),message.getContent());
			}else{
				TblJpush jpush = jpushService.getByuserInfo(userId);
				JPushUtil.point2point(message.getTitle(),message.getContent(),jpush.getJpushRegistrationid(),
						jpush.getJpushDevicetype(), "7");
			}
			dwzResult = new DwzAjaxResult("200", "推送成功", navTabId, "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "推送失败", navTabId, "", "");
		}
		log.info("******************消息推送-end************************");
		return new JsonObject(dwzResult).toString();
	}
	
	@RequestMapping("/findMessageList")
	public String findCarList(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblUsermessage	message, Model model,HttpServletRequest request) {
		log.info("******************获取消息列表-begin************************");
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}else{
			// 消息总数
			long total = cmsUserMessageService.getCount(message);
			if(total<=pager.getOffset()){
				pager.setPageNum(1L);
			}
			// 设置查询参数
			message.setPager(pager);
			
			List<TblUsermessage> messageList = cmsUserMessageService.getAll(message);
			
			pager.setTotal(total);
			
	
			// 取得登录用户信息
			TblUser loginUser = SessionMgr.getWebUser(request);
			// 将查询结果显示到画面
			model.addAttribute("loginUserLevel", loginUser.getUserLevel());
			model.addAttribute("messageList",messageList);
			model.addAttribute("pager", pager);
			model.addAttribute("message", message);
			log.info("******************获取消息列表-end************************");
			// 跳转至消息列表页面
			return "backstage/message/message-list";
		}	
	}
	
	/**
	 * @Title: newmessage
	 * @Description: 跳转至消息页面
	 * @param message
	 * @return
	 */
	@RequestMapping("/newMessage")
	public String newMessage(Model model) {
		log.info("******************跳转至消息页面************************");
		return "backstage/message/message-add";

	}

	/**
	 * @Title: insertCarMaker
	 * @Description: 新增消息页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertMessage")
	@ResponseBody
	public String insertMessage(HttpServletRequest request,TblUsermessage message,HttpSession session) {
		log.info("******************新增消息-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		//获取登陆用户，此处暂时只对超级管理员开放
		TblUser loginUser=SessionMgr.getWebUser(request);
		if(loginUser.getUserLevel()!=null&&loginUser.getUserLevel()>2){
			dwzResult = new DwzAjaxResult("300", "本功能暂时只对管理员开放", navTabId, "", "");
		}else{
			String fromUserId="0";
			String fromUserName=loginUser.getUserAccount();
			String toUserId=request.getParameter("org2.id");
			message.setFromUserid(new Integer(fromUserId));
			message.setFromUsername(fromUserName);
			message.setUserId(new Integer(toUserId));
			message.setCreateTime(new Date());
			message.setEditTime(new Date());
			try {
				//type 1:保存 2：保存并推送
				String type=request.getParameter("type");
				if("2".equals(type)){
					int userId=message.getUserId();
					if(message.getContent().length()<=58){
						//推送消息
						if(userId==0){
							JPushUtil.pushAll(message.getTitle(),message.getContent());
						}else{
							TblJpush jpush = jpushService.getByuserInfo(userId);
							JPushUtil.point2point(message.getTitle(),message.getContent(),jpush.getJpushRegistrationid(),
									jpush.getJpushDevicetype(), "7");
						}
					}else{
						dwzResult = new DwzAjaxResult("300", "发送信息长度不能超过58个字", navTabId, "", "");
						return new JsonObject(dwzResult).toString();
					}
					
				}
				// 新增消息
				cmsUserMessageService.insert(message);
				dwzResult = new DwzAjaxResult("200", "新增成功", navTabId,
						"closeCurrent", "");
			} catch (Exception e) {
				e.printStackTrace();
				// 出错日志
				log.info(e.getMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "新增失败", navTabId, "", "");
			}
		}
		
		log.info("******************新增消息-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除消息
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(TblUsermessage message) {
		log.info("******************删除消息-begin************************");
		DwzAjaxResult dwzResult = null;

		try {
			// 删除其他配置参数中配置名称
			cmsUserMessageService.delete(message.getId());
			dwzResult = new DwzAjaxResult("200", "删除成功", navTabId, "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", navTabId, "", "");
		}

		log.info("******************删除消息-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: editCarMaker
	 * @Description: 跳转至更新消息页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/editMessage")
	public String editMessage(TblUsermessage message, Model model) {
		log.info("******************跳转至更新消息页面-begin************************");
		// 根据id获取消息
		TblUsermessage message2 = cmsUserMessageService.getOne(message);
		// 将查询结果显示到页面
		model.addAttribute("message", message2);
		log.info("******************跳转至更新消息页面-end************************");
		return "backstage/message/message-edit";
	}

	/**
	 * @Title: updateCarMaker
	 * @Description: 更新消息
	 * @param message
	 * @return
	 */
	@RequestMapping("/updateMessage")
	@ResponseBody
	public String updateCarMaker(TblUsermessage message) {
		log.info("******************更新消息-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
	
		try {
			cmsUserMessageService.update(message);
			dwzResult = new DwzAjaxResult("200", "编辑成功", navTabId,
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", navTabId, "", "");
		}
		log.info("******************更新消息-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteCarMakers
	 * @Description: 批量删除消息
	 * @param pkmessages
	 *            主键，多个以英文逗号分隔
	 * @return
	 */
	@RequestMapping("/deleteMessages")
	@ResponseBody
	public String deleteMessages(@RequestParam("ids") String pks) {
		log.info("******************批量删除消息-begin************************");

		DwzAjaxResult dwzResult = null;

		try {
			// 批量删除消息
			cmsUserMessageService.deleteBatch(pks);

			dwzResult = new DwzAjaxResult("200", "删除成功", navTabId, "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", navTabId, "", "");
		}

		log.info("******************批量删除消息-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}
