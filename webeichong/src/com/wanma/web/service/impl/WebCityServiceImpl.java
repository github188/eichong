/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0
 */
package com.wanma.web.service.impl;

import com.wanma.model.TblCity;
import com.wanma.web.dao.WebCityMapper;
import com.wanma.web.service.WebCityService;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.common.Response;
import com.wanma.web.support.common.ResultResponse;
import com.wanma.web.support.utils.PinYin4jUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 反馈信息业务处理类
 *
 * @author Administrator
 * @version V1.0
 * @date 2014年6月26日
 */
@Service
public class WebCityServiceImpl implements WebCityService {

    /**
     * 聊天主表操作用DAO
     */
    @Autowired
    WebCityMapper appCityMapper;

    String[] letter;

    /**
     * 获取所有城市接口
     *
     * @return
     */
    @Override
    public Response<?> getAll(Map<String, String> param) {

        Integer begin = null, pageSize = null;


        boolean hasLetter = true;
        try {
            //获取分页参数
            if (!StringUtils.isEmpty(param.get("begin"))) {
                begin = Integer.valueOf(param.get("begin"));
            }
            if (!StringUtils.isEmpty(param.get("pageSize"))) {
                pageSize = Integer.valueOf(param.get("pageSize"));
            }
            if (StringUtils.isEmpty(param.get("hasLetter"))) {
                hasLetter = false;
            }
        } catch (Exception e) {
            //数据类型强转出错
            System.out.println(e.getMessage());
            begin = null;
            pageSize = null;
        }

        if (pageSize != null && begin != null&&!hasLetter) {
            PageResponse<Object> result = new PageResponse<Object>(begin, pageSize);
            param.put("begin", result.getBegin() + "");
            param.put("pageSize", result.getCount() + "");
            result.setCountData(appCityMapper.getCount(param));
            List<TblCity> res = appCityMapper.getAll(param);
            result.setDate(res);
            return result;
        }

        if(!hasLetter){
            return new ResultResponse<Object>(appCityMapper.getAll(param));
        }

        String firstLetter = null;
        try {
            param.put("cityName", URLDecoder.decode(param.get("cityName"),"UTF-8"));
            firstLetter = param.get("cityName");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(StringUtils.isEmpty(firstLetter)){
            param.remove("cityName");
            return new ResultResponse<Object>(orderByCity(appCityMapper.getAll(param)));
        }

        //检索首字母
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher matcher = pattern.matcher(firstLetter.substring(0,1));
        //字母全查
        if(!matcher.matches()){
            param.remove("cityName");
            return new ResultResponse<Object>(orderByCity(appCityMapper.getAll(param),firstLetter));
        }
        //汉字
        firstLetter = PinYin4jUtil.getFirstLetter(firstLetter);
        return new ResultResponse<Object>(orderByCity(appCityMapper.getAll(param),firstLetter));
    }

    /**
     * 字母筛选
     *
     * @param list
     * @return
     */
    private List<Map<String, List>> orderByCity(List list) {
        return orderByCity(list,null);
    }
    private List<Map<String, List>> orderByCity(List list,String firstLetter) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        letter = new String[]
                {"A", "B", "C", "D", "E", "F"
                        , "G", "H", "J", "K", "L"
                        , "M", "N", "O", "P", "Q", "R"
                        , "S", "T", "W", "X", "Y", "Z"};
        if(StringUtils.isNotEmpty(firstLetter)){
            letter = new String[]{firstLetter};
        }
        List<Map<String, List>> rest = new ArrayList<Map<String, List>>();
        for (String key : letter) {
            Map<String, List> res = new HashMap<String, List>();
            List<TblCity> item = new ArrayList<TblCity>();
            for (int i = 0; i < list.size(); i++) {
                TblCity city = (TblCity) list.get(i);
                if (key.equalsIgnoreCase(PinYin4jUtil.getFirstLetter(city.getCityName()))) {
                    item.add(city);
                }
            }
            res.put(key, item);
            rest.add(res);
        }
        return rest;
    }

}
