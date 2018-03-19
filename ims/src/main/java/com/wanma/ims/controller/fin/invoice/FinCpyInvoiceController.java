package com.wanma.ims.controller.fin.invoice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.ims.common.domain.InvoiceDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.service.FinInvoiceService;

@Controller
@RequestMapping("/manage/fin/invoice/cpy")
public class FinCpyInvoiceController extends BaseController{
	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(FinCpyInvoiceController.class);
	@Autowired
	private FinInvoiceService finInvoiceService;
	
	
	/**
	 * 获取公司开票记录列表
	 */
	@RequestMapping("/getFinCpyInvoiceList")
	public void getFinCpyInvoiceList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute InvoiceDO invoice,HttpServletRequest request){
		JsonResult result = new JsonResult();
		List<InvoiceDO> cpyInvoiceList = null;
		long total = 0;
		try {
			if(invoice.getInvoiceMode()==null){
				responseJson(new JsonResult(false,ResultCodeConstants.FAILED,"开票方式不能为空"));
				return;
			}
			total = finInvoiceService.getCpyInvoiceCount(invoice);
			if (total <= pager.getOffset()) {
				pager.setPageNo(1L);
			}
			invoice.setPager(pager);
			cpyInvoiceList = finInvoiceService.getCpyInvoiceList(invoice);
			pager.setTotal(total);
			
			result.setDataObject(cpyInvoiceList);
			result.setPager(pager);
		} catch (Exception e) {
			log.error(this.getClass()+".getFinCpyInvoiceList() error:",e);
		}
		responseJson(result);
		
	}
	/**
	 * 新增大客户开票
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addCpyInvoice")
	@ResponseBody
	public void addCpyInvoice(@ModelAttribute InvoiceDO invoice,String ids,HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			if(!finInvoiceService.addCpyInvoice(invoice,ids)){
				responseJson(new JsonResult(false,ResultCodeConstants.FAILED,"新增开票失败"));
				return;
			}
		} catch (Exception e) {
			log.error(this.getClass()+".confirmInvoice() error:",e);
		}
		responseJson(result);
	}
	
	/**
	 * 编辑大客户开票
	 * @author mb
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/modifyCpyInvoice")
	@ResponseBody
	public void modifyCpyInvoice(@ModelAttribute InvoiceDO invoice,String ids,HttpServletRequest request) {
		JsonResult result = new JsonResult();
		try {
			if(!finInvoiceService.modifyCpyInvoice(invoice,ids)){
				responseJson(new JsonResult(false,ResultCodeConstants.FAILED,"新增开票失败"));
				return;
			}
		} catch (Exception e) {
			log.error(this.getClass()+".confirmInvoice() error:",e);
		}
		responseJson(result);
	}
	
}
