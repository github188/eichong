/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.utils.MultipartFileUtil;
import com.wanma.app.dao.AppProductMapper;
import com.wanma.app.service.AppProductService;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblProduct;

/**
 * 产品业务处理实现类
 * 
 * @Description:
 * @author songjf
 * @createTime：2015-3-15 下午05:17:20
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class AppProductServiceImpl implements AppProductService {

	/** 产品表操作用DAO */
	@Autowired
	AppProductMapper appProductMapper;
	
	/**
	 * @Title: getShareProduct
	 * @Description: 获取 分享商品信息
	 * @param params
	 * @return
	 */
	@Override
	public TblProduct getShareProduct(TblProduct tblProduct) {
		TblProduct newTblProduct=appProductMapper.getShareProduct(tblProduct);
		//获取商品图片图片
		List<String> listImage=MultipartFileUtil.getAllMultiUrl(WanmaConstants.MULTI_LIST_IMAGE,
				newTblProduct.getPkProduct() + "");
		if(listImage.size()==0){
			newTblProduct.setProdProductimage("http://localhost:8080/wanma/upload/shareImage/share.jpg");
		}else{
			newTblProduct.setProdProductimage(JudgeNullUtils.nvlStr(listImage.get(0)));
		}	
		return newTblProduct;
	}

	/**
	 * @Title: findProducts
	 * @Description: 获取产品列表
	 * @param params
	 * @return
	 */
	@Override
	public List<TblProduct> findProducts(Map<String, Object> params) {
		List<TblProduct> productList = appProductMapper.findProducts(params);
		if(productList !=null){
			for(TblProduct product:productList){
				String prodProductimage = product.getProdProductimage();
				if(prodProductimage == null){
					product.setProdProductimage("");
				}
			}
		}
		
		return appProductMapper.findProducts(params);
	}

	/**
	 * @Title: findProductDetail
	 * @Description: 获取产品详情
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> findProductDetail(Map<String, Object> params) {
		return appProductMapper.findProductDetail(params);
	}

	/**
	 * @Title: selectById
	 * @Description: 立即购买 获取商品信息
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> selectById(Map<String, Object> params) {
		return appProductMapper.selectById(params);
	}

}
