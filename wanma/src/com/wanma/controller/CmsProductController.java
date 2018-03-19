package com.wanma.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.HtmlRegexpUtil;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblCarinfo;
import com.wanma.model.TblCarmaker;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblProduct;
import com.wanma.model.TblProductcategory;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCarinfoService;
import com.wanma.service.CmsCarmakerService;
import com.wanma.service.CmsConfigcontentService;
import com.wanma.service.CmsProductService;

@Controller
@RequestMapping("/admin/business")
public class CmsProductController extends BaseController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsProductController.class);

	@Autowired
	private CmsProductService productService;

	/* 制造厂商service */
	@Autowired
	private CmsCarmakerService carmakerService;

	@Autowired
	private CmsConfigcontentService configcontentService;

	@Autowired
	CmsCarinfoService carinfoService;

	/**
	 * 取得商品列表处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/productList")
	public String getProductList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblProduct tblProduct,
			@ModelAttribute TblProductcategory tblCategory, Model model,HttpServletRequest request) {

		// 商品信息
		List<TblProduct> ProductList = null;
		List<TblProductcategory> categoryList = null;
		
		//登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		if(loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS_NORMAL){//个体商家
			tblProduct.setProdUser(loginUser.getUserId()+"");
		}else if(loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS_NORMAL){//纯商家
			tblProduct.setProdUser(loginUser.getUserAccount());
		}
		
		// 商品总数
		long total = 0;

		if (tblProduct == null) {
			// 取得商品列表
			ProductList = productService.getProductList();
		} else {
			// 商品总数
			total = productService.searchProductCount(tblProduct);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblProduct.setPager(pager);

			// 获取所属类目
			categoryList = productService.searchCategoryList(tblCategory);

			// 取得商品列表
			ProductList = productService.searchProductList(tblProduct);
			pager.setTotal(total);
		}
		// 将商品信息放到会话中
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("tblProduct", tblProduct);
		model.addAttribute("ProductList", ProductList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "backstage/product/listProduct";
	}

	/**
	 * 商品添加初始化处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/newProduct")
	public String newProduct(@ModelAttribute TblProductcategory tblCategory,
			Model model) {

		// 获取所属类目
		List<TblProductcategory> categoryList = productService
				.searchCategoryList(tblCategory);

		// 生产厂家
		List<TblCarmaker> markList = carmakerService.getAll(null);

		TblConfigcontent tblConfigcontent = new TblConfigcontent();
		tblConfigcontent
				.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		tblConfigcontent.setCocoConfigparameterid(3);
		// 获取充电方式
		List<TblConfigcontent> chargeList = configcontentService
				.findContentList(tblConfigcontent);

		// 获取接口标准
		tblConfigcontent.setCocoConfigparameterid(5);
		List<TblConfigcontent> connectorList = configcontentService
				.findContentList(tblConfigcontent);

		// 30:电池类型
		tblConfigcontent.setCocoConfigparameterid(30);
		List<TblConfigcontent> batteryList = configcontentService
				.findContentList(tblConfigcontent);

		// 充电时间
		List<TblCarinfo> chargingTimeList = carinfoService.selectChargingTime();
		// 续航里程
		List<TblCarinfo> MaxOdometerList = carinfoService.selectMaxOdometer();

		TblProduct tblProduct = new TblProduct();
		// 将商品信息设置到画面显示对象
		model.addAttribute("tblProduct", tblProduct);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("markList", markList);
		model.addAttribute("chargeList", chargeList);
		model.addAttribute("connectorList", connectorList);
		model.addAttribute("batteryList", batteryList);
		model.addAttribute("chargingTimeList", chargingTimeList);
		model.addAttribute("MaxOdometerList", MaxOdometerList);
		// 跳转至商品添加页面
		return "backstage/product/newProductInfo";
	}

	/**
	 * 商品添加处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param TblProduct
	 *            商品输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 */
	@RequestMapping(value = "/saveProduct", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveProduct(
			@ModelAttribute("TblProduct") TblProduct tblProduct,
			BindingResult result,
			@RequestParam(value = "ListImage", required = false) MultipartFile[] ListImage,
			@RequestParam(value = "DetailImage", required = false) MultipartFile[] DetailImage,
			HttpServletRequest request) throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "ProductAddPage", "",
					"");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		try {
			List<TblProduct> prolist = new ArrayList<TblProduct>();

			// 保存列表图片
			TblProduct p1 = new TblProduct();
			p1.setAllMultiFile(ListImage);
			p1.setProdpicType(WanmaConstants.MULTI_LIST_IMAGE);
			prolist.add(p1);

			// 保存详情图片
			TblProduct p2 = new TblProduct();
			p2.setAllMultiFile(DetailImage);
			p2.setProdpicType(WanmaConstants.MULTI_DETAIL_IMAGE);
			prolist.add(p2);

			// 执行商品添加处理，并取得添加成功的商品详细信息
			tblProduct.setProdUpdatedate(new Date());
			tblProduct.setProdProductdiscount(new BigDecimal(0));
			tblProduct.setProdDiscountprice(tblProduct.getProdMarketprice());
			tblProduct.setProdSoldquantity(0);
			tblProduct.setProdBrowsenum(0);
			tblProduct.setProdProducttag(0);
			tblProduct.setProdQrcodepic("");
			tblProduct.setProdDetailimage("");
			tblProduct.setProdProductimage("");
			tblProduct.setProdStatus(1);
			//登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);
			if(loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS_NORMAL){//个体商家
				tblProduct.setProdUser(loginUser.getUserId()+"");
			}else{
				tblProduct.setProdUser(loginUser.getUserAccount());
			}
			productService.saveProduct(tblProduct, prolist);

			// 设置成功并返回商品一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "productList",
					"closeCurrent", "");
			// 如果添加商品处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			System.out.println(e);
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "ProductAddPage",
					"", "");

		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 商品编辑初始化处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pkProductinfo
	 *            商品ID
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/editProduct", method = RequestMethod.GET)
	public String editProduct(@RequestParam("pkProduct") String pkProduct,
			@ModelAttribute TblProductcategory tblCategory, Model model) {

		// 获取所属类目
		List<TblProductcategory> categoryList = productService
				.searchCategoryList(tblCategory);

		// 生产厂家
		List<TblCarmaker> markList = carmakerService.getAll(null);

		TblConfigcontent tblConfigcontent = new TblConfigcontent();
		tblConfigcontent
				.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		tblConfigcontent.setCocoConfigparameterid(3);
		// 获取充电方式
		List<TblConfigcontent> chargeList = configcontentService
				.findContentList(tblConfigcontent);

		// 获取接口标准
		tblConfigcontent.setCocoConfigparameterid(5);
		List<TblConfigcontent> connectorList = configcontentService
				.findContentList(tblConfigcontent);

		// 30:电池类型
		tblConfigcontent.setCocoConfigparameterid(30);
		List<TblConfigcontent> batteryList = configcontentService
				.findContentList(tblConfigcontent);

		// 充电时间
		List<TblCarinfo> chargingTimeList = carinfoService.selectChargingTime();
		// 续航里程
		List<TblCarinfo> MaxOdometerList = carinfoService.selectMaxOdometer();

		// 取得编辑对象商品信息
		TblProduct tblProduct = productService.findProduct(pkProduct);
		tblProduct.setProdParameters(new HtmlRegexpUtil().replaceTag(tblProduct.getProdParameters()));
		// 将商品信息设置到画面显示对象
		model.addAttribute("tblProduct", tblProduct);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("markList", markList);
		model.addAttribute("chargeList", chargeList);
		model.addAttribute("connectorList", connectorList);
		model.addAttribute("batteryList", batteryList);
		model.addAttribute("chargingTimeList", chargingTimeList);
		model.addAttribute("MaxOdometerList", MaxOdometerList);

		// 跳转至商品编辑页面
		return "backstage/product/editProductInfo";
	}

	/**
	 * 商品编辑处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param ProductModel
	 *            商品输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/modifyProduct", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String modifyProduct(
			@ModelAttribute("tblProduct") TblProduct tblProduct,
			BindingResult result,
			@RequestParam(value = "ListImage", required = false) MultipartFile[] ListImage,
			@RequestParam(value = "DetailImage", required = false) MultipartFile[] DetailImage,
			HttpServletRequest request) throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "ProductEditPage", "",
					"");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		try {
			List<TblProduct> prolist = new ArrayList<TblProduct>();

			// 保存列表图片
			TblProduct p1 = new TblProduct();
			p1.setAllMultiFile(ListImage);
			p1.setProdpicType(WanmaConstants.MULTI_LIST_IMAGE);
			prolist.add(p1);

			// 保存详情图片
			TblProduct p2 = new TblProduct();
			p2.setAllMultiFile(DetailImage);
			p2.setProdpicType(WanmaConstants.MULTI_DETAIL_IMAGE);
			prolist.add(p2);

			// 执行商品添加处理
			productService.modifyProduct(tblProduct, prolist);

			// 设置成功并返回商品一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "productList",
					"closeCurrent", "");
			// 如果更新商品处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误",
					"ProductEditPage", "", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 删除商品图片
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param ProductModel
	 *            商品输入信息
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/deletePic", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String deletePic(
			@ModelAttribute("tblProduct") TblProduct tblProduct,
			BindingResult result,
			@RequestParam(value = "ListImage", required = false) MultipartFile[] ListImage,
			@RequestParam(value = "DetailImage", required = false) MultipartFile[] DetailImage,
			HttpServletRequest request) throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "ProductEditPage", "",
					"");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		try {
			List<TblProduct> prolist = new ArrayList<TblProduct>();

			// 保存列表图片
			TblProduct p1 = new TblProduct();
			p1.setAllMultiFile(ListImage);
			p1.setProdpicType(WanmaConstants.MULTI_LIST_IMAGE);
			prolist.add(p1);

			// 保存详情图片
			TblProduct p2 = new TblProduct();
			p2.setAllMultiFile(DetailImage);
			p2.setProdpicType(WanmaConstants.MULTI_DETAIL_IMAGE);
			prolist.add(p2);

			// 执行商品添加处理
			productService.deleteImage(tblProduct, prolist);

			// 设置成功并返回商品一览画面信息
			dwzResult = new DwzAjaxResult("200", "删除成功", "productList",
					"closeCurrent", "");
			// 如果更新商品处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误",
					"ProductEditPage", "", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 删除商品
	 * 
	 * @author xiay
	 * @param pkProductinfo
	 * @return
	 */
	@RequestMapping("/removeProduct")
	@ResponseBody
	public String removeProduct(TblProduct tblProduct) {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		try {
			// 执行删除处理
			productService.deleteProduct(tblProduct);

			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("200", "删除成功", "productList", "", "");
			// 如果更新商品处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误", "productList",
					"", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 批量删除商品
	 * 
	 * @author xiay
	 * @param pkProductinfo
	 * @return
	 */
	@RequestMapping("/removeProductAll")
	@ResponseBody
	public String removeProductAll(@RequestParam("pkProducts") String pkProducts) {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		try {
			// 执行删除处理
			productService.deleteProductAll(pkProducts);

			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("200", "批量删除成功", "productList", "",
					"");
			// 如果更新商品处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "批量删除失败:系统错误", "productList",
					"", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 商品查看初始化处理
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/viewProduct", method = RequestMethod.GET)
	public String viewProduct(@RequestParam("pkProduct") String pkProduct,
			Model model) {
		// 取得商品信息
		TblProduct tblProduct = productService.findProduct(pkProduct);
		tblProduct.setProdParameters(new HtmlRegexpUtil().replaceTag(tblProduct.getProdParameters()));
		// 将商品画面显示对象
		model.addAttribute("tblProduct", tblProduct);

		// 跳转至商品查看页面
		return "backstage/product/viewProductInfo";
	}

	/**
	 * 上架
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @throws 无
	 */
	@RequestMapping("/updateStatusList")
	@ResponseBody
	public String updateStatusList(TblProduct record) {
		log.info("************编辑用户状态-begin************");

		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			productService.updateStatus(record);
			dwzResult = new DwzAjaxResult("200", "编辑成功", "productList", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", "productList", "", "");
		}
		log.info("************编辑用户状态-end************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 批量上架
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @throws 无
	 */
	@RequestMapping("/updateStatusAll")
	@ResponseBody
	public String updateStatusAll(@RequestParam("pkProducts") String pkProducts) {
		log.info("************批量上架用户状态-begin************");

		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		String message = null;
		try {
			TblProduct tblProduct = productService.findProduct(pkProducts);
			if (tblProduct.getProdProductlsadd() == 1) {
				productService.updateStatusAll(pkProducts);
				message = "上架";

			} else {
				message = "已上架";
				dwzResult = new DwzAjaxResult("300", message, "productList",
						"", "");
			}
			message = message + "成功";
			dwzResult = new DwzAjaxResult("200", message, "productList", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());

			message = "批量上架失败";
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", message, "productList", "", "");
		}
		log.info("************批量上架用户状态-end************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}
