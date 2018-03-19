package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.MessageManager;
import com.wanma.app.dao.TblElectricpileMapper;
import com.wanma.common.JudgeNullUtils;
import com.wanma.dao.CmsConcentratorMapper;
import com.wanma.model.TblConcentrator;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblTypespan;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsConcentratorService;
import com.wanma.web.support.utils.ApiUtil;
import com.wanma.web.support.utils.HttpsUtil;

/**
 * @Description: 集中器管理业务处理接口
 * @author lhy
 * @createTime：2015-11-30
 * @updator：
 * @updateTime：
 * @version：V2.0
 */
@Service
public class CmsConcentratorServiceImpl implements CmsConcentratorService {
	@Autowired
	CmsConcentratorMapper concentratorMapper;
	@Autowired
	private TblElectricpileMapper tblElectricpileMapper;
	@Autowired
	CmsCommitLogService commitLogService;

	/**
	 * 获取集中器列表
	 * 
	 * @param concentrator
	 * @return
	 */
	@Override
	public List<TblConcentrator> findConcentratorList(
			TblConcentrator concentrator) {
		return concentratorMapper.findConcentrators(concentrator);
	}

	/**
	 * 集中器列表条数
	 * 
	 * @param concentrator
	 * @return
	 */
	@Override
	public long selectConcentratorCount(TblConcentrator concentrator) {
		return concentratorMapper.selectConcentratorCount(concentrator);
	}

	/**
	 * 保存集中器
	 * 
	 * @param concentrator
	 * @throws Exception 
	 */
	@Override
	public void insert(TblConcentrator concentrator) throws Exception {
		concentratorMapper.insert(concentrator);
		bindingElectricpile(concentrator, 0);// 绑定电桩
	}

	@Override
	public String sendUpdateConcentrator(String id, String apiBaseUrl)
			throws Exception {
		TblTypespan tblTypespan = new TblTypespan();
		tblTypespan.setPkTypeSpanId(Integer.valueOf(id));
		// 调用接口更新集中器
		return HttpsUtil.getResponseParam(
				apiBaseUrl + "/app/net/sendConcentrator.do?cId=" + id + "&t="
						+ ApiUtil.getToken(), "status");

	}

	/**
	 * 修改集中器
	 * 
	 * @param concentrator
	 * @throws Exception 
	 */
	@Override
	public void updateConcentrator(TblConcentrator concentrator) throws Exception {
		concentratorMapper.update(concentrator);
		int bindedCount = tblElectricpileMapper
				.getbindedConcentratorElectricpileCount(concentrator);// 获取已绑定的电桩数量
		bindingElectricpile(concentrator, bindedCount);// 绑定电桩
	}

	/**
	 * 绑定电桩
	 * 
	 * @param concentrator
	 *            集中器model
	 * @param bindedCount
	 *            已绑定电桩的数量
	 * @throws Exception 
	 */
	private void bindingElectricpile(TblConcentrator concentrator,
			int bindedCount) throws Exception {
		String postEleids = concentrator.getPostEleids();
		if (!StringUtils.isBlank(postEleids)) {
			if (postEleids.indexOf(",") > 0) {
				String[] electricpileIds = postEleids.split(",");
				for (int i = 0; i < electricpileIds.length; i++) {
					TblElectricpile tblElectricpile = new TblElectricpile();
					tblElectricpile.setPkElectricpile(JudgeNullUtils
							.nvlInteger(electricpileIds[i]));
					tblElectricpile.setPkConcentratorID(concentrator
							.getPkConcentratorID());
					tblElectricpile.setConcentratorNum(bindedCount + i + 1);
					tblElectricpileMapper
							.electricPileBindConcentrator(tblElectricpile);
				}
			} else {
				TblElectricpile tblElectricpile = new TblElectricpile();
				tblElectricpile.setPkElectricpile(JudgeNullUtils
						.nvlInteger(postEleids));
				tblElectricpile.setPkConcentratorID(concentrator
						.getPkConcentratorID());
				tblElectricpile.setConcentratorNum(bindedCount + 1);
				tblElectricpileMapper
						.electricPileBindConcentrator(tblElectricpile);
			}
			try {
				sendUpdateConcentrator(concentrator.getPkConcentratorID().toString(), new MessageManager().getSystemProperties("apiRoot"));
				commitLogService.insert("集中器更新命令下发，主键：["
						+ concentrator.getPkConcentratorID() + "]");
			} catch (Exception e) {
				throw new Exception("集中器更新命令下发失败！");
			}
		}
	}

	/**
	 * 获取集中器
	 * 
	 * @param concentrator
	 */
	@Override
	public TblConcentrator findOne(TblConcentrator concentrator) {
		concentrator = concentratorMapper.findOne(concentrator);
		concentrator.setPileList((List<TblElectricpile>) tblElectricpileMapper
				.findBindedConcentratorElectricpileList(concentrator));
		return concentrator;
	}

	/**
	 * @param unbindPkElectricpile
	 *            需要解绑的电桩ID
	 * @param reSortPkElectricpiles
	 *            不需要解绑的电桩ID组
	 */
	@Override
	public void unbindConcentrator(String unbindPkElectricpile,
			String reSortPkElectricpiles) {
		// 解绑
		tblElectricpileMapper.unbindConcentrator(unbindPkElectricpile);
		// 重新排序剩下的电桩序号
		String[] pkElectricpiles = reSortPkElectricpiles.split(",");
		if (!"".equals(pkElectricpiles[0])) {
			int pkElectricpilesLength = pkElectricpiles.length;
			for (int i = 0; i < pkElectricpilesLength; i++) {
				TblElectricpile tblElectricpile = new TblElectricpile();
				tblElectricpile.setPkElectricpile(Integer
						.valueOf(pkElectricpiles[i]));
				tblElectricpile.setConcentratorNum(i + 1);
				tblElectricpileMapper
						.electricPileBindConcentrator(tblElectricpile);
			}
		}
	}

	/**
	 * 删除集中器
	 */
	@Override
	public void delete(String ids) {
		concentratorMapper.delete(makeIdsMap(ids));
	}

	/**
	 * 存在已绑定电桩的集中器不可删除
	 * 
	 * @param idsMap
	 * @return
	 */
	@Override
	public boolean checkDelete(String ids) {
		return concentratorMapper.getBindedCountByIds(makeIdsMap(ids)) == 0
				&& concentratorMapper.isOnlineCount(makeIdsMap(ids)) == 0;
	}

	private Map<String, String[]> makeIdsMap(String ids) {
		Map<String, String[]> idsMap = new HashMap<String, String[]>();
		idsMap.put("ids", ids.split(","));
		return idsMap;
	}
}
