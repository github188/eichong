package com.wanma.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.wanma.model.ElectricPileDetail;
import com.wanma.model.PowerElectricPileHead;

@Service
public class ElecPileStarMapServiceImpl {
	/*@Autowired
	private WebElecPileStarMapper epsMapper;*/
	
	/**
	 * 保存电桩星级评价
	 * @param param
	 *//*
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
	}*/
	
	public int getKongxianCount(ElectricPileDetail electricPileDetail){
		int count = 0;
		if("1".equals(electricPileDetail.getCommStatus())){
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
