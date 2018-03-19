package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblUserCardMapper;
import com.wanma.dubbox.model.TblUserCard;
import com.wanma.dubbox.model.TblUserNormal;
import com.wanma.dubbox.service.TblUserCardService;
/**
 * 电动车品牌数据查询接口
 * @author lhy
 *
 */
@Service
public class TblUserCardServiceImpl implements TblUserCardService {

	@Autowired
	TblUserCardMapper tblUserCardMapper;
	@Override
	public int insert(TblUserCard record) {
		return tblUserCardMapper.insert(record);
	}

	@Override
	public TblUserCard selectOne(TblUserCard model) {
		return tblUserCardMapper.selectOne(model);
	}

	@Override
	public int update(TblUserCard record) {
		return tblUserCardMapper.update(record);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblUserCard model) {
		return tblUserCardMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblUserCard> getList(TblUserCard model) {
		return tblUserCardMapper.getList(model);
	}
	

	/*   
	* Description: 查询可绑定卡用户列表
	* @param user
	* @return    
	*/
	@Override
	public List<TblUserNormal> getApplyCardUserList(TblUserCard model) {
		if("1".equals(model.getQkApply())){
			return tblUserCardMapper.getQuicklyApplyCardUserList(model);
		}else{
			return tblUserCardMapper.getApplyCardUserList(model);
		}
	}

	/*   
	* Description: 查询可绑定卡用户数量
	* @param user
	* @return    
	*/
	@Override
	public int getApplyCardUserListCount(TblUserCard model) {
		if("1".equals(model.getQkApply())){
			return tblUserCardMapper.getQuicklyApplyCardUserListCount(model);
		}else{
			return tblUserCardMapper.getApplyCardUserListCount(model);
		}
	}
	
}