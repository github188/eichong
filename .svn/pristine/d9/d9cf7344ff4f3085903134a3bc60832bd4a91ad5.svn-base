package com.wanma.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluemobi.product.utils.AccessErrorResult;
import com.wanma.model.TblApplyPartner;
import com.wanma.web.service.WebApplyPartnerService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.SuccessResponse;


@Controller
@RequestMapping("/admin/applyPartner")
public class WebApplyPartnerConteoller {
	
	//日志输出对象
	private static Logger log = Logger.getLogger(WebApplyPartnerConteoller.class);
		@Autowired
		WebApplyPartnerService  webApplyPartnerService;
				
		@RequestMapping("/insert")
		@ResponseBody
		public String insertWebApplyPartner(HttpServletRequest req,TblApplyPartner tblApplyPartner) {
			MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
			List<MultipartFile> files = request.getFiles("file1");
			MultipartFile[] array=new MultipartFile[files.size()];
			for(int i=0;i<files.size();i++){
				array[i]=files.get(i);
			}
			if (files.isEmpty()) {
				return new AccessErrorResult("error.msg.invalid.file").toString();
			}
			tblApplyPartner.setAllMultiFile(array);						
        try{
        	
        	tblApplyPartner.setApPaPartnerType("1");
        	webApplyPartnerService.insertWebApplyPartner(tblApplyPartner);
        }catch (Exception e) {
            // 保存错误LOG
            log.error(e.getLocalizedMessage());
            log.error("申请合作伙伴数据提交失败", e);
            
            return new FailedResponse("申请合作伙伴提交失败").toString();
        }
        	// 返回处理成功信息
	        return new SuccessResponse().toString();
		}
		
		
		@RequestMapping("/companyApplyInsert")
		@ResponseBody
		public String insertCompanyApplyPartner(HttpServletRequest req,TblApplyPartner tblApplyPartner) {							
        try{
        	tblApplyPartner.setApPaPartnerType("2");
        	webApplyPartnerService.insertWebApplyPartner(tblApplyPartner);
        }catch (Exception e) {
            // 保存错误LOG
            log.error(e.getLocalizedMessage());
            log.error("申请合作伙伴数据提交失败", e);
            
            return new FailedResponse("申请合作伙伴提交失败").toString();
        }
        	// 返回处理成功信息
	        return new SuccessResponse().toString();
		}

		
}
