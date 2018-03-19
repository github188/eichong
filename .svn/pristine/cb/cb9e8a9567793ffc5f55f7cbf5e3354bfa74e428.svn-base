/**     
 * @Title:  TblElectricpileService.java   
 * @Package com.wanma.service   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年11月19日 下午4:24:10   
 * @version V1.0     
 */  
package com.wanma.service;

import com.wanma.model.TblElectricpilehead;

import java.util.List;
import java.util.Map;

/**
 * @author bc
 *
 */
public interface TblElectricpileHeadService {
	
	public TblElectricpilehead getBespokeHeadDetailByPile(TblElectricpilehead head);
	public TblElectricpilehead getBespokeHeadDetailByStation(TblElectricpilehead head);
	List<TblElectricpilehead> getList(TblElectricpilehead head);
	public List<TblElectricpilehead> getListByPsId(TblElectricpilehead hModel);
	public List<Map<String, Object>> getHeadStsByPsId(TblElectricpilehead hModel);
	public List<Map<String, Object>> echongGetHeadStsByPsId(TblElectricpilehead hModel);
    public TblElectricpilehead getHeadByQrCode(String qrCode);
    /**
     * 根据枪口主键查询枪头信息
     * @param hModel
     * @return
     */
    public Map<String, Object>  getHeadByPkhead(TblElectricpilehead hModel);

    /**
     * 根据枪口主键进行设备认证
     * @param maph
     * @return
     */
	public int getEquipAuthByEleHead(Map<String, Object> maph);
	
	/**
	 * 查询设备状态(南京南瑞)
	 * @param hModel
	 * @return
	 */
	public List<Map<String, Object>> NariGetHeadStsByPsId(TblElectricpilehead hModel);
	
	/**
	 * 中电联-设备认证-检验设备是否在白名单
	 * @param mapl
	 * @return
	 */
	public int checkEquipIsRela(Map<String, Object> mapl);
}
