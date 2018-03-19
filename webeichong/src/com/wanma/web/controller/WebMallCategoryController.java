package com.wanma.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.TblProduct;
import com.wanma.model.TblProductcategory;
import com.wanma.web.service.WebMallCategoryService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;

/**
 * 新商品城类目
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/mallCategory")
public class WebMallCategoryController {
	private static Log log = LogFactory.getLog(WebMallCategoryController.class);

	@Autowired
	private WebMallCategoryService mallCategoryService;

	/**
	 * 获取热门推荐
	 */
	@RequestMapping("/getHotRecommend")
	@ResponseBody
	public String getHotRecommend(HttpServletRequest request){

		// 热门推荐ID
		String prcaParentid = RequestParamUtil.getEncodeParam(request,
				"prcaParentid");

		List<TblProductcategory> TblProductcategoryList = new ArrayList();
		try {
			TblProductcategory tblProductcategory = new TblProductcategory();

			if (StringUtil.isEmpty(prcaParentid)) {
				// 未输入 电桩ID
				return new AccessErrorResult("error.msg.invalid.parameter")
						.toString();
			}
			tblProductcategory.setPrcaParentid(Integer.parseInt(prcaParentid));
			TblProductcategoryList = mallCategoryService
					.getHotRecommend(tblProductcategory);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取热门推荐数据失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
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
	public String getHotRecommendDetail(HttpServletRequest request) {

		// 价格 1-默认 2-按最优排序
		String priceSort = RequestParamUtil
				.getEncodeParam(request, "priceSort");
		// 充电模式
		String chargingModeId = RequestParamUtil.getEncodeParam(request,
				"productType");
		// 销售量排序 1-默认 2-按最优排序
		String soldQuantity = RequestParamUtil.getEncodeParam(request,
				"soldQuantity");
		// 2-热门推荐 3-电动汽车 4-电动自行车 5-充电设备 6-充电汽车配件
		String prodCategoryid = RequestParamUtil.getEncodeParam(request,
				"prodCategoryid");
		// 分页页码
		String begin = RequestParamUtil.getEncodeParam(request,
				"begin");
		// 分页每页总数
		String pageSize = RequestParamUtil.getEncodeParam(request,
				"pageSize");
		PageResponse<List<TblProduct>> result=null;
		try {

			TblProduct tblProduct = new TblProduct();
 
			if (!StringUtil.isEmpty(priceSort)) {
				tblProduct.setPriceSort(JudgeNullUtils.nvlStr(priceSort));
			}
			if (!StringUtil.isEmpty(chargingModeId)) {
				tblProduct.setProdChargingMode(JudgeNullUtils.nvlLang(
						chargingModeId).intValue());
			}
			if (!StringUtil.isEmpty(soldQuantity)) {
				tblProduct.setSoldQuantity(JudgeNullUtils.nvlStr(soldQuantity));
			}

			result= new PageResponse<List<TblProduct>>(JudgeNullUtils.nvlInteget(begin), JudgeNullUtils.nvlInteget(pageSize));
            tblProduct.setOffset(result.getBegin());
            tblProduct.setNumPerPage(result.getCount());
            tblProduct.setProdCategoryid(JudgeNullUtils.nvlInteget(prodCategoryid));
            List<TblProduct> tblProductList = mallCategoryService.getHotRecommendDetail(tblProduct);
            int productListCount=mallCategoryService.getHotRecommendDetailCount(tblProduct);
			result.setCountData(productListCount);
		    result.setDate(tblProductList);

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取热门推荐产品详情数据失败", e);
			// 返回登录信息错误信息
			
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return result.toString();
	}
}