package com.wanma.dao;


import java.util.List;

import com.wanma.model.TblGateservice;

public interface TblGateserviceMapper {    
    
	public TblGateservice get(java.lang.Integer pkGateid);
	
	public  TblGateservice findOne(TblGateservice tblGateservice);
	
	public List<TblGateservice> find(TblGateservice tblGateservice);
	
	public int insert(TblGateservice tblGateservice);
	
	public int update(TblGateservice tblGateservice);
	
	public int delete(java.lang.Integer pkGateid );
	public List<TblGateservice> find1(TblGateservice tblGateservice);
	public void updateFailState(java.lang.Integer pkGateid);
}


