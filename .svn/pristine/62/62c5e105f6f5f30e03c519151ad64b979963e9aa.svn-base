package com.wanma.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.model.common.DwzAjaxResult;
import com.wanma.common.WanmaConstants;
import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsCarinfoMapper;
import com.wanma.model.TblCarinfo;
import com.wanma.service.CmsCarinfoService;

/**
 * @Description: 电动车品牌类型业务处理接口
 * @author songjf
 * @createTime：2015-4-1 上午11:33:44
 * @version：V1.0
 */
@Service
public class CmsCarinfoServiceImpl implements CmsCarinfoService {
	/* 电动车品牌类型操作dao */
	@Autowired
	private CmsCarinfoMapper carinfoMapper;

	/**
	 * @Title: findCarList
	 * @Description: 获取车型号列表
	 * @param params
	 * @return
	 */
	@Override
	public List<TblCarinfo> findCarList(TblCarinfo tblCarinfo) {
		List<TblCarinfo> carList=carinfoMapper.findCarList(tblCarinfo);
		return carList;
	}

	/**
	 * @Title: findCount
	 * @Description: 获取车型号数量
	 * @param params
	 * @return
	 */
	@Override
	public long findCount(TblCarinfo tblCarinfo) {
		return carinfoMapper.findCount(tblCarinfo);
	}

	/**
	 * @Title: insertCarinfo
	 * @Description: 新增电动车品牌类型
	 * @param params
	 * @return
	 */
	@SystemControllerLog(description = "新增电动车品牌类型")
	@Override
	public int insertCarinfo(TblCarinfo tblCarinfo) {
		tblCarinfo.setCarinfoCreatedate(new Date());
		tblCarinfo.setCarinfoUpdatedate(new Date());
		tblCarinfo
				.setCarInfocarstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		return carinfoMapper.insertCarinfo(tblCarinfo);
	}

	/**
	 * @Title: findById
	 * @Description: 获取电动车品牌类型
	 * @param pkCarinfo
	 * @return
	 */
	@Override
	public TblCarinfo findById(TblCarinfo tblCarinfo) {
		tblCarinfo
				.setCarInfocarstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		return carinfoMapper.findById(tblCarinfo);
	}

	/**
	 * @Title: updateCarinfo
	 * @Description: 更新电动车品牌类型
	 * @param params
	 * @return
	 */
	@SystemControllerLog(description = "更新电动车品牌类型")
	@Override
	public int updateCarinfo(TblCarinfo tblCarinfo) {
		tblCarinfo.setCarinfoUpdatedate(new Date());
		return carinfoMapper.updateCarinfo(tblCarinfo);
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除电动车品牌类型
	 * @param params
	 * @return
	 */
	@SystemControllerLog(description = "删除电动车品牌类型")
	@Override
	public int deleteById(TblCarinfo tblCarinfo) {
		tblCarinfo.setCarInfocarstatus(WanmaConstants.CONFIG_PARAMETER_INVALID);
		return carinfoMapper.deleteById(tblCarinfo);
	}

	/**
	 * @Title: deleteCarinfos
	 * @Description: 批量删除电动车品牌类型
	 * @param params
	 * @return
	 */
	@SystemControllerLog(description = "批量删除电动车品牌类型")
	@Override
	public void deleteCarinfos(String pkCarinfos) {
		String[] carinfoArray = pkCarinfos.split(",");
		TblCarinfo carinfo = new TblCarinfo();
		for (int i = 0; i < carinfoArray.length; i++) {
			carinfo.setCarInfocarstatus(WanmaConstants.CONFIG_PARAMETER_INVALID);
			carinfo.setPkCarinfo(Integer.parseInt(carinfoArray[i]));
			carinfoMapper.deleteById(carinfo);
		}

	}

	/**
	 * @Title: selectChargingTime
	 * @Description: 获取充电时间 
	 * @param params
	 * @return
	 */
	@Override
	public List<TblCarinfo> selectChargingTime() {
		return carinfoMapper.selectChargingTime();
	}

	/**
	 * @Title: selectMaxOdometer
	 * @Description: 获取最大续航里程
	 * @param params
	 * @return
	 */
	@Override
	public List<TblCarinfo> selectMaxOdometer() {
		return carinfoMapper.selectMaxOdometer();
	}
	
	/**
	 * 根据汽车厂商得到汽车车型列表
	 * @param pkCarcompany
	 * @return
	 */
	@Override
	public List<TblCarinfo> searchCarinfoList(Integer pkCarcompany){
		return carinfoMapper.searchCarinfoList(pkCarcompany);
	}


	/**
	 * 判断名称是否重复
	 * @param params
	 * @return
	 */
	@Override
	public HashMap<String, Object> getByProperty(Map<String, Object> params) {
		return carinfoMapper.getByProperty(params);
	}

	@Override
	public DwzAjaxResult checkDelete(String pkCarinfos) {
		DwzAjaxResult dwzResult = null;
		String[] idArr = pkCarinfos.split(",");
		Map<String,Object> map = new HashMap<String, Object>();;
		map.put("idArr", idArr);
		int bindedCount = carinfoMapper.getBindedUserCount(map);
		if(bindedCount > 0)
			dwzResult = new DwzAjaxResult("300", "存在绑定用户的车型，不可删除！", "", "", "");
		return dwzResult;
	}

}
