/**     
 * @Title:  TblElectricpileHeadServiceImpl.java   
 * @Package com.wanma.service.impl   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年4月28日 下午2:26:22   
 * @version V1.0     
 */
package com.wanma.service.impl;

import com.wanma.dao.TblElectricpileHeadMapper;
import com.wanma.model.TblElectricpilehead;
import com.wanma.service.TblElectricpileHeadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author bc
 *
 */
@Service
public class TblElectricpileHeadServiceImpl implements
		TblElectricpileHeadService {
	@Autowired
	private TblElectricpileHeadMapper electricpileheadMapper;

	@Override
	public TblElectricpilehead getBespokeHeadDetailByPile(
			TblElectricpilehead head) {
		return electricpileheadMapper.getBespokeHeadDetailByPile(head);
	}

	@Override
	public TblElectricpilehead getBespokeHeadDetailByStation(
			TblElectricpilehead head) {
		return electricpileheadMapper.getBespokeHeadDetailByStation(head);
	}

	@Override
	public List<TblElectricpilehead> getList(TblElectricpilehead head) {
		return electricpileheadMapper.getList(head);
	}

	@Override
	public List<TblElectricpilehead> getListByPsId(TblElectricpilehead hModel) {
		return electricpileheadMapper.getListByPsId(hModel);
	}

	@Override
	public List<Map<String, Object>> getHeadStsByPsId(TblElectricpilehead hModel) {
		return electricpileheadMapper.getHeadStsByPsId(hModel);
	}

	@Override
	public TblElectricpilehead getHeadByQrCode(String qrCode) {

		return electricpileheadMapper.getHeadByQrCode(qrCode,
				System.currentTimeMillis() / 1000L);
	}

	@Override
	public List<Map<String, Object>> echongGetHeadStsByPsId(
			TblElectricpilehead hModel) {
		return electricpileheadMapper.echongGetHeadStsByPsId(hModel);
	}

	@Override
	public Map<String, Object> getHeadByPkhead(TblElectricpilehead hModel) {
		return electricpileheadMapper.getHeadByPkhead(hModel);
	}

	@Override
	public int getEquipAuthByEleHead(Map<String, Object> maph) {
		return electricpileheadMapper.getEquipAuthByEleHead(maph);
	}

	@Override
	public List<Map<String, Object>> NariGetHeadStsByPsId(
			TblElectricpilehead hModel) {
		return electricpileheadMapper.NariGetHeadStsByPsId(hModel);
	}

	@Override
	public int checkEquipIsRela(Map<String, Object> mapl) {
		return electricpileheadMapper.checkEquipIsRela(mapl);
	}
}
