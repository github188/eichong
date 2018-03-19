package com.wanma.web.service.impl;

import com.wanma.model.WebProvince;
import com.wanma.web.dao.WebProvinceMapper;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.common.Response;
import com.wanma.web.support.common.ResultResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Aaron on 2015/4/3.
 */
@Service
public class WebProvinceServiceImpl {
    @Autowired
    private WebProvinceMapper provinceMapper;

    public Response<?> getAll(Map<String,String> param){

        Integer begin = null, pageSize = null;
        try {
            //获取分页参数
            if (!StringUtils.isEmpty(param.get("begin"))) {
                begin = Integer.valueOf(param.get("begin"));
            }
            if (!StringUtils.isEmpty(param.get("pageSize"))) {
                pageSize = Integer.valueOf(param.get("pageSize"));
            }
        } catch (Exception e) {
            //数据类型强转出错
            System.out.println(e.getMessage());
            begin = null;
            pageSize = null;
        }
        if(pageSize!=null&&begin!=null){
            PageResponse<List<WebProvince>> result = new PageResponse<List<WebProvince>>(begin, pageSize);
            param.put("begin", result.getBegin()+"");
            param.put("pageSize", result.getCount()+"");
            List<WebProvince> data = provinceMapper.getAll(param);
            result.setCountData(provinceMapper.getCount(param));
            result.setDate(data);
            return result;
        }
        return new ResultResponse<List<WebProvince>>(provinceMapper.getAll(param));
    }


}
