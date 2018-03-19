package com.wanma.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsElctrcplscrnconfgurtnMapper;
import com.wanma.model.TblElctrcplscrnconfgurtn;
import com.wanma.service.CmsElctrcplscrnconfgurtnService;

/**
 * @Description: 桩体配置参数业务处理实现类
 * @author songjf
 * @createTime：2015-4-1 上午11:33:44
 * @version：V1.0
 */
@Service
public class CmsElctrcplscrnconfgurtnServiceImpl implements
		CmsElctrcplscrnconfgurtnService {
	// 桩体配置参数操作dao
	@Autowired
	private CmsElctrcplscrnconfgurtnMapper elctrcplscrnconfgurtnMapper;

	/**
	 * @Title: findById
	 * @Description: 根据id获取桩体配置参数信息
	 * @param pkElctrcplscrnconfgurtn
	 *            主键
	 * @return
	 */
	@Override
	public TblElctrcplscrnconfgurtn findById(Integer pkElctrcplscrnconfgurtn) {
		return elctrcplscrnconfgurtnMapper.findById(pkElctrcplscrnconfgurtn);
	}

	/**
	 * @Title: findByEpscType
	 * @Description: 根据配置类型获取桩体配置参数信息
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	@Override
	public List<TblElctrcplscrnconfgurtn> findByEpscType(
			TblElctrcplscrnconfgurtn elctrcplscrnconfgurtn) {
		return elctrcplscrnconfgurtnMapper
				.findByEpscType(elctrcplscrnconfgurtn);
	}

	/**
	 * @Title: findCount
	 * @Description: 根据配置类型获取桩体配置参数总数
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	@Override
	public long findCount(TblElctrcplscrnconfgurtn elctrcplscrnconfgurtn) {
		return elctrcplscrnconfgurtnMapper.findCount(elctrcplscrnconfgurtn);
	}

	/**
	 * @Title: insert
	 * @Description: 新增桩体配置参数
	 * @param params
	 * @return
	 */
	@SystemControllerLog(description = "新增桩体配置参数")
	@Override
	public int insertInfo(TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn) {
		tblElctrcplscrnconfgurtn.setEpscCreatedate(new Date());
		tblElctrcplscrnconfgurtn.setEpscUpdatedate(new Date());
		return elctrcplscrnconfgurtnMapper.insertInfo(tblElctrcplscrnconfgurtn);
	}

	/**
	 * @Title: updateById
	 * @Description: 根据id更新桩体配置参数
	 * @param tblElctrcplscrnconfgurtn
	 * @return
	 */
	@SystemControllerLog(description = "更新桩体配置参数")
	@Override
	public int updateById(TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn) {
		tblElctrcplscrnconfgurtn.setEpscUpdatedate(new Date());
		return elctrcplscrnconfgurtnMapper.updateById(tblElctrcplscrnconfgurtn);
	}

	/**
	 * @Title: deleteById
	 * @Description: 根据id删除桩体配置参数
	 * @param params
	 * @return
	 */
	@SystemControllerLog(description = "删除桩体配置参数")
	@Override
	public int deleteById(Integer pkElctrcplscrnconfgurtn) {
		return elctrcplscrnconfgurtnMapper.deleteById(pkElctrcplscrnconfgurtn);
	}

	/**
	 * @Title: deleteEpscs
	 * @Description: 桩体配置参数，批量删除配置名称s
	 * @param pkElctrcplscrnconfgurtns
	 *            所有需要删除的主键，以英文逗号分隔
	 * @return
	 */
	@SystemControllerLog(description = "批量删除桩体配置参数")
	@Override
	public void deleteEpscs(String pkElctrcplscrnconfgurtns) {
		String[] pkEpscsArray = pkElctrcplscrnconfgurtns.split(",");
		for (int i = 0; i < pkEpscsArray.length; i++) {
			elctrcplscrnconfgurtnMapper.deleteById(Integer
					.parseInt(pkEpscsArray[i]));
		}
	}

}
