package com.wanma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.WebConst;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.model.CommitLog;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCommitLogService;

/**
 * 日志管理
 * 
 * @author txd
 * 
 */
@Controller
@RequestMapping("/admin/commitlog")
public class CmsCommitLogController {
	
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsCommitLogController.class);

	/** 日志处理器 */
	@Autowired
	private CmsCommitLogService commitLogService;

	@RequestMapping(value = "/commitlogList")
	public String getCommitLogList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute CommitLog commitLog,
			Model model,HttpServletRequest request){

		// 获取登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		commitLog.setUserLevel(loginUser.getUserLevel().toString());
		//日志信息
		List<CommitLog> commitLogList = null;
		//日志总数
		long total = 0;
		if(commitLog == null){
			//取得日志信息
			commitLogList = commitLogService.getCommitLogList();
		}else {
			//将当日包含进去
			if(StringUtils.isNotBlank(commitLog.getEnd_create_date()))
				commitLog.setEnd_create_date(commitLog.getEnd_create_date() + " 23:59:59");
			total = commitLogService.searchCommitLogCount(commitLog);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			commitLog.setPager(pager);
			commitLogList = commitLogService.searchCommitLogList(commitLog);
			//用完之后将时间设置回去
			if(StringUtils.isNotBlank(commitLog.getEnd_create_date()))
				commitLog.setEnd_create_date(commitLog.getEnd_create_date().replace(" 23:59:59", ""));
			pager.setTotal(total);
		}
		
		//将日志信息放到会话中
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("commitLog", commitLog);
		model.addAttribute("commitLogList", commitLogList);
		model.addAttribute("pager", pager);
			
		//跳转至日志信息
		return "backstage/commitlog/listcommitlog";
	}

	/**
	 * 删除日志列表
	 * 
     */
    @RequestMapping( "/removecommitlog" )
    @ResponseBody
    public String removeCommitLog(CommitLog commitLog)
    {
        log.info("删除日志列表");
        // 处理结果信息
        DwzAjaxResult dwzResult;

        try
        {  
            // 执行删除处理
            commitLogService.deleteById(commitLog);
            // 设置处理结果信息
            dwzResult = new DwzAjaxResult( "200", "删除成功", "commitlogList", "", "" );
            // 如果更新用户处理不成功
        } catch ( Exception e )
        {
            // 出错日志
            log.error( e.getLocalizedMessage() );

            // 设置处理错误信息
            dwzResult = new DwzAjaxResult( "300", "删除失败:系统错误", "commitlogList",
                    "", "" );
        }

        // 返回处理结果信息
        return new JsonObject(dwzResult).toString();
    }

    /**
     * 批量删除日志列表
	 * 
     */
    @RequestMapping( "/removecommitlogs" )
    @ResponseBody
    public String removefeedbacks(@RequestParam("ids") String commitLogs )
    {
        // 处理结果信息
        DwzAjaxResult dwzResult;
        try
        {
        	commitLogService.deleteCommitLogs(commitLogs);
            dwzResult = new DwzAjaxResult( "200", "删除成功", "commitlogList", "", "" );
        } catch ( Exception e )
        {
            // 出错日志
            log.error( e.getLocalizedMessage() );
            // 设置处理错误信息
            dwzResult = new DwzAjaxResult( "300", "删除失败", "commitlogList",
                    "closeCurrent", "" );
        }
        // 返回处理结果信息
        return new JsonObject(dwzResult).toString();
    }
}
