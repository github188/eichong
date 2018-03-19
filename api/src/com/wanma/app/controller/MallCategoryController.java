package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.MallCategoryService;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.TblProduct;
import com.wanma.model.TblProductcategory;


/**
 *  新商品城类目
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-11 下午04:25:53 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Controller
@RequestMapping("/app/mallCategory")
public class MallCategoryController {
    private static Logger log = Logger.getLogger(MallCategoryController.class);
	
    @Autowired
    private MallCategoryService mallCategoryService;

    
    /**
     * 获取热门推荐
     */
    @RequestMapping("/getHotRecommend")
	@ResponseBody
	public String  getHotRecommend(HttpServletRequest request) throws AppRequestErrorException {
    	
    	//热门推荐ID
		String prcaParentid = RequestParamUtil.getEncodeParam(request, "prcaParentid");
		
		List<TblProductcategory> TblProductcategoryList=new ArrayList();
    	try {
    		TblProductcategory tblProductcategory=new TblProductcategory();
    		
			if (StringUtil.isEmpty(prcaParentid)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			tblProductcategory.setPrcaParentid(Integer.parseInt(prcaParentid));
			TblProductcategoryList=mallCategoryService.getHotRecommend(tblProductcategory);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取热门推荐数据失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(TblProductcategoryList).toString();
	}
    /**
     * 获取热门推荐产品详情
     */
    @RequestMapping("/getHotRecommendDetail")
	@ResponseBody
	public String  getHotRecommendDetail(HttpServletRequest request) throws AppRequestErrorException {
    	
    	//产品分类
		String prodCategoryid = RequestParamUtil.getEncodeParam(request, "prodCategoryid");
		//价格 1-默认 2-按最优排序
		String priceSort = RequestParamUtil.getEncodeParam(request, "priceSort");
		//充电模式
		String chargingModeId = RequestParamUtil.getEncodeParam(request, "chargingModeId");
		//销售量排序 1-默认 2-按最优排序
		String soldQuantity = RequestParamUtil.getEncodeParam(request, "soldQuantity");
		
    	List<TblProduct> tblProductList=new ArrayList();
    	try {
    		TblProduct tblProduct=new TblProduct();
    		
			if (StringUtil.isEmpty(prodCategoryid)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			if (!StringUtil.isEmpty(priceSort)) {
				tblProduct.setPriceSort(JudgeNullUtils.nvlStr(priceSort));
			}
			if (!StringUtil.isEmpty(chargingModeId)) {
				tblProduct.setProdChargingMode(JudgeNullUtils.nvlLang(chargingModeId).intValue());
			}
			if (!StringUtil.isEmpty(soldQuantity)) {
				tblProduct.setSoldQuantity(JudgeNullUtils.nvlStr(soldQuantity));
			}
			tblProduct.setProdCategoryid(Integer.parseInt(prodCategoryid));
			tblProductList=mallCategoryService.getHotRecommendDetail(tblProduct);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取热门推荐产品详情数据失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(tblProductList).toString();
	}
}