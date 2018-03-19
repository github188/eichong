package com.wanma.web.service.impl;

import com.bluemobi.product.utils.StringUtil;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.Response;
import com.wanma.web.support.common.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.TblEquipmentrepair;
import com.wanma.web.dao.WebEquipmentrepairMapper;
import com.wanma.web.service.WebEquipmentrepairService;

import java.util.Date;
import java.util.Map;

/**
 * @author chenb
 * @ClassName: TblEquipmentrepairServiceImpl
 * @Description: 设备维修服务层接口实现类
 * @date 2015年3月15日 下午4:47:14
 */
@Service
public class WebEquipmentrepairServiceImpl implements WebEquipmentrepairService {

    /**
     * 设备维修业务操作DAO
     */
    @Autowired
    private WebEquipmentrepairMapper tblEquipmentrepairMapper;

    @Override
    public Response<?> addTblEquipmentrepair(Map<String, Object> param) {
        //参数
        String eqreUserid = (String) param.get("eqreUserid"),
                eqreWarrantytypeid = (String) param.get("eqreWarrantytypeid"),
                eqreContent = (String) param.get("eqreContent");

        //参数错误
        if (StringUtil.isEmpty(eqreUserid))
            return new FailedResponse("请传入用户ID！");
        if (StringUtil.isEmpty(eqreWarrantytypeid))
            return new FailedResponse("请传入维修类型！");
        if (StringUtil.isEmpty(eqreContent))
            return new FailedResponse("请传入维修内容！");
        
        //db操作
        try {
            TblEquipmentrepair equipmentrepair = new TblEquipmentrepair();
            equipmentrepair.setEqreContent(eqreContent);
            equipmentrepair.setEqreUserid(Integer.parseInt(eqreUserid));
            equipmentrepair.setEqreWarrantytypeid(Integer.parseInt(eqreWarrantytypeid));
            equipmentrepair.setEqreWarrantystatus(1);
            equipmentrepair.setEqreStatus(0);
            equipmentrepair.setEqreCreatedate(new Date());
            equipmentrepair.setEqreUpdatedate(new Date());
            tblEquipmentrepairMapper.insert(equipmentrepair);
            return new SuccessResponse();
        } catch (Exception e) {
            return new FailedResponse("error.msg.invalid.parameter");
        }
    }

}
