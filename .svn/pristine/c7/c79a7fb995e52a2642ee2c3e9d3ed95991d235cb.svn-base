package com.wanma.web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.ElectricPileDetail;
import com.wanma.model.PowerElectricPileHead;
import com.wanma.model.PowerStationElictric;
import com.wanma.web.dao.WebElecPileStarMapper;

@Service
public class WebElecPileStarServiceImpl {
	@Autowired
	private WebElecPileStarMapper epsMapper;
	
	/**
	 * 保存电桩星级评价
	 * @param param
	 */
	public void insertEpStar(Map<String, Object> param){		
		epsMapper.insert(param);
	}
	
	public int  getEpCommentStar(Map<String, Object> params){
		Map<String,Object> map=epsMapper.getEpCommentStar(params);
		if(map==null||map.isEmpty()){
			return 0;
		}else{
			return ((Double)map.get("eps_CommentStar")).intValue();
		}
	}
	
	public int getKongxianCount(ElectricPileDetail electricPileDetail){
		int count = 0;
		if("1".equals(electricPileDetail.getElectricPileConnStatus())){
			List<PowerElectricPileHead> headList = electricPileDetail.getPileHeadList();
			for(PowerElectricPileHead headInfo:headList){
				if("0".equals(headInfo.getPileHeadState())){
					count++;
					continue;
				}
			}
		}
		return count;
	}
}
