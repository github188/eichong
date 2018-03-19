package com.wanma.ims.controller.config.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.ims.common.domain.AppLoginRecord;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.AppLoginRecordService;

/**
 * 
 * @author mb
 *
 */
@Controller
@RequestMapping(value="/manage/app/loginRecord")
public class AppLoginRecordController extends BaseController{
	 private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	 @Autowired
	 private AppLoginRecordService appLoginRecordService;
	 	/**
	     * 查询app登陆记录列表 
	     */
	    @RequestMapping(value = "/getAppLoginRecordList")
	    @ResponseBody
	    public void getAppLoginRecordList(Pager pager,AppLoginRecord appLoginRecord){
	    	JsonResult result = new JsonResult();
	    	try {
	    		long total = appLoginRecordService.getAppLoginRecordCount(appLoginRecord);
	    		 if (total <= pager.getOffset()) {
	                 pager.setPageNo(1L);
	             }
	             pager.setTotal(total);
	             appLoginRecord.setPager(pager);
	             List<AppLoginRecord> appLoginRecordList = appLoginRecordService.getAppLoginRecordList(appLoginRecord);
	             result.setPager(pager); 
	             result.setDataObject(appLoginRecordList);
	             responseJson(result);
			} catch (Exception e) {
				LOGGER.error(this.getClass()+".getAppLoginRecordList() error:",e);
			}
	    }
	    
	    
}
