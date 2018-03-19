package com.wanma.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluemobi.product.model.common.DwzAjaxResult;
import com.wanma.model.TblCarinfo;

/**
 * @Description: 电动车品牌类型业务处理接口
 * @author songjf
 * @createTime：2015-4-1 上午11:33:44
 * @version：V1.0
 */
public interface CmsCarinfoService {

	/**
	 * @Title: findCarList
	 * @Description: 获取车型号列表
	 * @param params
	 * @return
	 */
	public List<TblCarinfo> findCarList(TblCarinfo tblCarinfo);

	/**
	 * @Title: findCount
	 * @Description: 获取车型号数量
	 * @param params
	 * @return
	 */
	public long findCount(TblCarinfo tblCarinfo);

	/**
	 * @Title: insertCarinfo
	 * @Description: 新增电动车品牌类型
	 * @param params
	 * @return
	 */
	public int insertCarinfo(TblCarinfo tblCarinfo);

	/**
	 * @Title: findById
	 * @Description: 获取电动车品牌类型
	 * @param pkCarinfo
	 * @return
	 */
	public TblCarinfo findById(TblCarinfo tblCarinfo);

	/**
	 * @Title: updateCarinfo
	 * @Description: 更新电动车品牌类型
	 * @param params
	 * @return
	 */
	public int updateCarinfo(TblCarinfo tblCarinfo);

	/**
	 * @Title: deleteById
	 * @Description: 删除电动车品牌类型
	 * @param params
	 * @return
	 */
	public int deleteById(TblCarinfo tblCarinfo);

	/**
	 * @Title: deleteCarinfos
	 * @Description: 批量删除电动车品牌类型
	 * @param params
	 * @return
	 */
	public void deleteCarinfos(String pkCarinfos);
	
	/**
	 * @Title: selectChargingTime
	 * @Description: 获取充电时间 
	 * @param params
	 * @return
	 */
	public List<TblCarinfo> selectChargingTime();
	
	/**
	 * @Title: selectMaxOdometer
	 * @Description: 获取最大续航里程
	 * @param params
	 * @return
	 */
	public List<TblCarinfo> selectMaxOdometer();
	
	
	/**
	 * 根据汽车厂商得到汽车车型列表
	 * @param pkCarcompany
	 * @return
	 */
	public List<TblCarinfo> searchCarinfoList(Integer pkCarcompany);


	/**
	 * 判断名称是否重复
	 * @param params
	 * @return
	 */
	public HashMap<String, Object> getByProperty(Map<String, Object> params);

	public DwzAjaxResult checkDelete(String pkCarinfos);

}
