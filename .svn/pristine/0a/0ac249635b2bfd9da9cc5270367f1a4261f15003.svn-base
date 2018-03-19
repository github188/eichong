package com.wanma.controller;

import java.io.IOException;
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

import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblFilterWords;
import com.wanma.model.TblUser;
import com.wanma.service.CmsFilterWordsService;
import com.wanma.service.impl.RedisService;

/**
 * 过滤字控制器
 * 
 * @author xiay
 *
 */
@Controller
@RequestMapping("admin/evaluate")
public class CmsFilterWordsController extends BaseController{
	//日志输出对象
	private static Logger log = Logger.getLogger(CmsFilterWordsController.class);
	
	/** 过滤字处理对象 */
	@Autowired
	private CmsFilterWordsService filterWordsService;
	@Autowired
	private RedisService redisService;
	
	/**
	 * 查询过滤字
	 * 
	 * @author xiay
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/FilterWordList")
	public String getFilterWordList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblFilterWords tblFilterWords,
			Model model,HttpServletRequest request){
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}else{
			//过滤字信息
			List<String> filterWordsList = null;
			
			//过滤字总数
			long total = 0;
				
			//取得过滤字信息
			filterWordsList =redisService.listGetAll(WanmaConstants.SENSITIVE_WORD_LIST);
			// 取得登录用户信息
			TblUser loginUser = SessionMgr.getWebUser(request);
			//将过滤字信息放到会话中
			model.addAttribute("loginUserLevel", loginUser.getUserLevel());			
			model.addAttribute("tblFilterWords", tblFilterWords);
			model.addAttribute("filterWordsList", filterWordsList);
			model.addAttribute("pager", pager);
				
			//跳转至过滤字信息
			return "backstage/evaluate/newFilterWords";
		}	
	}
	
	/**
	 * 跳转过滤字页面
	 * 
	 * @author xiay
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newFilterWord")
	public String newFilterWord(Model model){
		String filterwordsId = "";
		TblFilterWords tblFilterWords = new TblFilterWords();
		
		filterwordsId = ProcessInfoCommon.getRandomKey();
		
		tblFilterWords.setFilterwordsId(filterwordsId);
		
		model.addAttribute("tblFilterWords", tblFilterWords);
		
		return "backstage/evaluate/addFilterWords";
	}

	/**
	 * 添加过滤字
	 * 
	 * @author xiay
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveFilterWord", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveFilterWord(@ModelAttribute("tblFilterWords") TblFilterWords tblFilterWords,
			BindingResult result, HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "filterWordList", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		try {
			
//			// 执行过滤字添加处理，并取得添加成功的过滤字详细信息
//			tblFilterWords.setCreatedate(new Date());
//			tblFilterWords.setUpdatedate(new Date());
//			tblFilterWords.setStatus("0");
//			filterWordsService.addFilterWords(tblFilterWords);
			List sensitiveWordList=redisService.listGetAll(WanmaConstants.SENSITIVE_WORD_LIST);
			if(sensitiveWordList.contains(tblFilterWords.getWordsName())){
				// 设置处理错误信息
				dwzResult = new DwzAjaxResult("300", "保存失败:敏感词已存在", "filterWordList",
						"", "");
			}else{
				redisService.listSet(WanmaConstants.SENSITIVE_WORD_LIST,tblFilterWords.getWordsName());
				// 设置成功并返回过滤字一览画面信息
				dwzResult = new DwzAjaxResult("200", "保存成功", "filterWordList",
						"closeCurrent", "");
			}
			// 如果添加过滤字处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "filterWordList",
					"", "");

		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * 删除过滤字
	 * 
	 * @author xiay
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteFilterWord")
	@ResponseBody
	public String deleteFilterWord(@RequestParam("filterWord") String filterWord){
		// 处理结果信息
		DwzAjaxResult dwzResult;

		try {
			/*TblFilterWords tblFilterWords = new TblFilterWords();
			tblFilterWords.setFilterwordsId(filterwordsId);
					
			// 执行删除处理
			filterWordsService.deleteFilterWord(tblFilterWords);*/
			String filterWord2=new String(filterWord.getBytes("iso-8859-1"),"utf-8");
			redisService.listRemove(WanmaConstants.SENSITIVE_WORD_LIST, filterWord2);
			//redisClient.deleteSensitiveWord(filterWord2);

			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("200", "删除成功", "filterWordList", "", "");
			// 如果更新过滤字处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误", "filterWordList", "",
							"");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}