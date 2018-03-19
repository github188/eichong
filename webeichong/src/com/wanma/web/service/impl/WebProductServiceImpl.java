/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service.impl;

import com.wanma.model.TblProduct;
import com.wanma.web.dao.WebProductMapper;
import com.wanma.web.service.WebProductService;
import com.wanma.web.support.common.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 产品业务处理实现类
 *
 * @author songjf
 * @Description:
 * @createTime：2015-3-15 下午05:17:20
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class WebProductServiceImpl implements WebProductService {

    /**
     * 产品表操作用DAO
     */
    @Autowired
    WebProductMapper appProductMapper;

    /**
     * @param params
     * @return
     * @Title: findProducts
     * @Description: 获取产品列表
     */
    @Override
    public Response<?> findProducts(Map<String, Object> params) {
        Integer begin = null, pageSize = null;
        try {
            //获取分页参数
            if (!StringUtils.isEmpty((String) params.get("begin"))) {
                begin = Integer.valueOf((String) params.get("begin"));
            }
            if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
                pageSize = Integer.valueOf((String) params.get("pageSize"));
            }
        } catch (Exception e) {
            //数据类型强转出错
            System.out.println(e.getMessage());
            begin = null;
            pageSize = null;
        }
        PageResponse<List<TblProduct>> result = new PageResponse<List<TblProduct>>(begin, pageSize);
        params.put("begin", result.getBegin());
        params.put("pageSize", result.getCount());
        List<TblProduct> data = appProductMapper.findProducts(params);
        result.setCountData(appProductMapper.countProducts(params));
        result.setDate(data);
        return result;
    }


    /**
     * @param params
     * @return
     * @Title: findProductDetail
     * @Description: 获取产品详情
     */
    @Override
    public Response<?> findProductDetail(Map<String, Object> params) {
        Map<String, Object> product;
        try{
            product = appProductMapper.findProductDetail(params);
        }catch (Exception e){
            return  new ErrorResponse(e.getMessage());
        }
        return new ResultResponse<Map<String, Object>>(product);
    }


}
