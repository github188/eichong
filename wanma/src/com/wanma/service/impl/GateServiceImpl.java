package com.wanma.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.model.common.DwzAjaxResult;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.TblGateserviceMapper;
import com.wanma.model.TblGateservice;
import com.wanma.service.GateService;

@Service
public class GateServiceImpl implements GateService{

	@Autowired
	private TblGateserviceMapper tblGateserviceMapper;

	@Override
	public List<TblGateservice> getGateList() {
		// TODO Auto-generated method stub
		return tblGateserviceMapper.find(null);
	}

	@SystemControllerLog(description = "增加Gate服务器操作")
	@Override
	public void addGate(TblGateservice tblGateservice) {
		// TODO Auto-generated method stub
		tblGateservice.setGtseCreatetime(new Date());
		tblGateservice.setGtseUpdatetime(new Date());
		tblGateserviceMapper.insert(tblGateservice);
	}

	@Override
	public TblGateservice getGateById(int pkGateid) {
		// TODO Auto-generated method stub
		return tblGateserviceMapper.get(pkGateid);
	}

	@SystemControllerLog(description = "修改Gate服务器操作")
	@Override
	public void modifyGate(TblGateservice tblGateservice) {
		// TODO Auto-generated method stub
		TblGateservice tblGateservice1=tblGateserviceMapper.get(tblGateservice.getPkGateid());
		
		if(!StringUtils.isBlank(tblGateservice.getGtseGatename())){
			tblGateservice1.setGtseGatename(tblGateservice.getGtseGatename());
		}
		if(!StringUtils.isBlank(tblGateservice.getGtseGateip())){
			tblGateservice1.setGtseGateip(tblGateservice.getGtseGateip());
		}
		if(tblGateservice.getGtseGateport()>0){
			tblGateservice1.setGtseGateport(tblGateservice.getGtseGateport());
		}
		tblGateservice1.setGtseUpdatetime(new Date());
		
		tblGateserviceMapper.update(tblGateservice1);
	}

	@SystemControllerLog(description = "删除Gate服务器操作")
	@Override
	public DwzAjaxResult deleteGate(String pkGateid) {
		DwzAjaxResult result=new DwzAjaxResult("200", "删除成功","getGateList", "","");
		String errorCode=""; 
		TblGateservice tblGateservice1=new TblGateservice();
		String[] gateIds=null;
		List<TblGateservice> tempList=new ArrayList<TblGateservice>();
		if(pkGateid.lastIndexOf(",")>0){
			gateIds=pkGateid.split(",");
		}else{
			gateIds=new String[]{pkGateid};
		}
		
		for (int i = 0; i < gateIds.length; i++) {
			tblGateservice1.setPkGateid(JudgeNullUtils.nvlInteger(gateIds[i]));
			tblGateservice1=tblGateserviceMapper.findElectricByGateCount(tblGateservice1);
			if(tblGateservice1.getGateCount()<=0){//未绑定电桩GATE服务器可以删除
				TblGateservice tblGateservice=tblGateserviceMapper.get(JudgeNullUtils.nvlInteger(gateIds[i]));
				tblGateservice.setGateState(2);//GATE服务器状态 1-正常 2-移
				//tblGateserviceMapper.update(tblGateservice);
				tempList.add(tblGateservice);
			}else{
				errorCode+=tblGateservice1.getGtseGatename();
			}
		}
		//有绑定电桩的 返回错误结果
		if(StringUtils.isNotBlank(errorCode)){
			result.setStatusCode("300");
			result.setMessage("已绑定电桩:"+errorCode);
		}else{//全部正确，删除返回正确结果
			for(TblGateservice tblGateservice:tempList){
				tblGateserviceMapper.update(tblGateservice);
			}
		}
		return result;
	}
	
	@Override
	public long findElectricByGateCount(Integer pkGateid) {
		// TODO Auto-generated method stub
		TblGateservice tblGateservice1 =new TblGateservice();
		tblGateservice1.setPkGateid(pkGateid);
		return tblGateserviceMapper.findElectricByGateCount(tblGateservice1).getGateCount();
	}
	
	@Override
	public boolean checkGateName(String name) {
		return tblGateserviceMapper.getCountByName(name) == 0;
	}
}
