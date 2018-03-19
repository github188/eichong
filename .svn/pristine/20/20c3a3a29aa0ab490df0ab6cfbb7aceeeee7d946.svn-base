package com.wanma.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseResult;
import com.base.util.DateUtil;
import com.pub.model.Entity;
import com.pub.model.Pager;
import com.wanma.service.StatisticRegisteService;
/**
 * 统计分析-app注册
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/admin/statistic")
public class StatisticRegisteController {
	
	@Autowired
	private StatisticRegisteService registeService;

	@RequestMapping("/userRegistePage")
	public String userRegistePage(){
		return "backstage/statistic/userRegiste";
	}
	/**
	 * app注册人数
	 * @return
	 */
	@RequestMapping("/userRegisteCount")
	@ResponseBody
	public String userRegisteCount(){
		Map<String,Object> map=registeService.userRegisteCount();
		return new BaseResult(map).toString();
	}
	
	/**
	 * app注册最近7天折线图数据
	 * @return
	 */
	@RequestMapping("/userRegisteLatest")
	@ResponseBody
	public String userRegisteLatest(){
		List<Map<String, Object>> list=registeService.userRegisteLatest();
		String d=DateUtil.getNow(DateUtil.TYPE_COMBINE_YMD);
		d=DateUtil.addDay(d, -7,DateUtil.TYPE_COMBINE_YMD,DateUtil.TYPE_COMBINE_YMD);
		String[] legends=new String[]{"android", "ios"};
		String[] columns=new String[7];
		String[] androids=new String[7];
		String[] ioss=new String[7];
		for(int i=0;i<7;i++){
			columns[i]=d;
			for(Map<String, Object> obj:list){
				if(obj.get("date").equals(d)){
					if(obj.get("type").equals("android")){
						androids[i]=obj.get("num").toString();
					}else if(obj.get("type").equals("ios")){
						ioss[i]=obj.get("num").toString();
					}
				}
			}
			if(androids[i]==null){
				androids[i]="0";
			}
			if(ioss[i]==null){
				ioss[i]="0";
			}
			//日期加一天
			d=DateUtil.addDay(d, 1,DateUtil.TYPE_COMBINE_YMD,DateUtil.TYPE_COMBINE_YMD);
		}
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("legend", legends);
		map.put("columns", columns);
		map.put("android",androids);
		map.put("ios", ioss);
		return new BaseResult(map).toString();
	}
	
	/**
	 * app注册最近7天折线图数据
	 * @return
	 */
	@RequestMapping("/userRegisteForMonth")
	@ResponseBody
	public String userRegisteForMonth(){
		List<Map<String, Object>> list=registeService.userRegisteForMonth();
		String d=list.get(0).get("month").toString();
		String[] legends=new String[]{"android", "ios"};
		String[] columns=new String[7];
		String[] androids=new String[7];
		String[] ioss=new String[7];
		for(int i=0;i<7;i++){
			columns[i]=d;
			for(Map<String, Object> obj:list){
				if(obj.get("month").equals(d)){
					if(obj.get("type").equals("android")){
						androids[i]=obj.get("num").toString();
					}else if(obj.get("type").equals("ios")){
						ioss[i]=obj.get("num").toString();
					}
				}
			}
			if(androids[i]==null){
				androids[i]="0";
			}
			if(ioss[i]==null){
				ioss[i]="0";
			}
			//日期加一个月
			d=DateUtil.addMonth(d, 1,DateUtil.TYPE_COMBINE_YM,DateUtil.TYPE_COMBINE_YM);
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("legend", legends);
		map.put("columns", columns);
		map.put("android", androids);
		map.put("ios",ioss);
		return new BaseResult(map).toString();
	}
	
	
	/**
	 * app注册列表
	 * @return
	 */
	@RequestMapping("/userRegisteList")
	@ResponseBody
	public String userRegisteList( Entity entity,Pager pager){
		long total = registeService.userRegisteListCount();
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		entity.setPager(pager);
		pager.setTotal(total);
		List<Map<String,Object>> list = registeService.userRegisteList(entity);
		return new BaseResult(list,pager).toString();
	}
}
