package com.wanma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblPublishEp;
import com.wanma.model.TblUser;
import com.wanma.service.impl.CmsPublishEpServiceImpl;

/**
 * @Description: 桩体分享controller
 * @author wubc
 * @createTime：2015-5-27
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/publishEp")
public class CmsPublishEpController extends BaseController{
	private static Log log = LogFactory.getLog(CmsPublishEpController.class);
	@Autowired
	CmsPublishEpServiceImpl cmsPublishEpService;
	private String navTabId="publishEp";

	@RequestMapping("/findPublishEpList")
	public String findCarList(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblPublishEp publishEp, Model model,HttpServletRequest request) {
		log.info("******************获取桩体分享列表-begin************************");
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}
		//将结束时间延伸到本日结束
		if(!StringUtils.isEmpty(publishEp.getEndDate())){
			publishEp.setEndDate(publishEp.getEndDate() + " 23:59:59");
		}
		// 桩体分享总数
		long total = cmsPublishEpService.getCount(publishEp);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		publishEp.setPager(pager);

		List<TblPublishEp> publishEpList = cmsPublishEpService
				.getAll(publishEp);

		pager.setTotal(total);
		//使用完毕后将时间还原回去
		if(!StringUtils.isEmpty(publishEp.getEndDate())){
			publishEp.setEndDate(publishEp.getEndDate().replace(" 23:59:59", ""));
		}
		// 取得登录用户信息
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 将查询结果显示到画面
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("publishEpList", publishEpList);
		model.addAttribute("pager", pager);
		model.addAttribute("publishEp", publishEp);
		log.info("******************获取桩体分享列表-end************************");
		// 跳转至桩体分享列表页面
		return "backstage/publishEp/publishEp-list";
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除桩体分享
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(TblPublishEp TblPublishEp) {
		log.info("******************删除桩体分享-begin************************");
		DwzAjaxResult dwzResult = null;

		try {
			// 删除其他配置参数中配置名称
			cmsPublishEpService.delete(TblPublishEp.getId());
			dwzResult = new DwzAjaxResult("200", "删除成功", navTabId,
					"", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", navTabId,
					"", "");
		}

		log.info("******************删除桩体分享-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: editPublishEp
	 * @Description: 跳转至处理桩体分享页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/editPublishEp")
	public String editpublishEp(TblPublishEp tblPublishEp, Model model) {
		log.info("******************跳转至处理桩体分享页面-begin************************");
		// 根据id获取桩体分享
		TblPublishEp publishEp = cmsPublishEpService.getOne(tblPublishEp);
		// 将查询结果显示到页面
		model.addAttribute("publishEp", publishEp);
		//桩体分享图片
		List<String> images=cmsPublishEpService.getImages(tblPublishEp);
		model.addAttribute("images", images);
		//图片路径设置
		MessageManager messageManager = MessageManager.getMessageManager();
		// 完整路径
		String fullPath = "";
		// 正式文件存放路径
		String relFilePath = messageManager.getSystemProperties(CommonConsts.PICTURE_SERVICE_SCANURL);
		// 图片存放相对路径
		String relativePath = messageManager.getSystemProperties(WanmaConstants.MULTI_TYPE_ELECTRICT_PUBLISH_IMG);
		fullPath = relFilePath + "/" + relativePath;
		model.addAttribute("path",fullPath);
		log.info("******************跳转至处理桩体分享页面-end************************");
		return "backstage/publishEp/publishEp-edit";
	}

	/**
	 * @Title: updatepublishEp
	 * @Description: 处理桩体分享
	 * @param publishEp
	 * @return
	 */
	@RequestMapping("/updatePublishEp")
	@ResponseBody
	public String updatepublishEp(TblPublishEp publishEp) {
		log.info("******************处理桩体分享-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			cmsPublishEpService.update(publishEp);
			dwzResult = new DwzAjaxResult("200", "处理成功", navTabId,
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "处理失败", navTabId,
					"", "");
		}
		log.info("******************处理桩体分享-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deletepublishEps
	 * @Description: 批量删除桩体分享
	 * @param pkpublishEps
	 *            主键，多个以英文逗号分隔
	 * @return
	 */
	@RequestMapping("/deletePublishEps")
	@ResponseBody
	public String deletepublishEps(@RequestParam("ids") String pkpublishEps) {
		log.info("******************批量删除桩体分享-begin************************");

		DwzAjaxResult dwzResult = null;

		try {
			// 批量删除桩体分享
			cmsPublishEpService.deleteBatch(pkpublishEps);

			dwzResult = new DwzAjaxResult("200", "删除成功", navTabId,
					"", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", navTabId,
					"", "");
		}

		log.info("******************批量删除桩体分享-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

}
