package com.wanma.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.TblApplyElecPile;
import com.wanma.web.service.WebApplyElecPileService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.SuccessResponse;


@Controller
@RequestMapping("/admin/applyElecPile")
public class WebApplyElecPileConteoller {
	
	//日志输出对象
	private static Logger log = Logger.getLogger(WebApplyElecPileConteoller.class);
		@Autowired
		WebApplyElecPileService  webApplyElecPileService;
				
		@RequestMapping("/insert")
		@ResponseBody
		public String insertWebApplyElecPile(HttpServletRequest req,TblApplyElecPile tblApplyElecPile) {
        try{

/*       	tblApplyElecPile.setApEpPointAddress(""+tblApplyElecPile.getApEpProvinceAddress()+"省"+tblApplyElecPile.getApEpCityAddress()+
        			"市"+tblApplyElecPile.getApEpAreaAddress()+"区"+tblApplyElecPile.getApEpStreetAddress()+"街道"+tblApplyElecPile.getApEpRoomNumber());
       		 System.out.println(tblApplyElecPile.getApEpParkingOwner());
        	if(!(("".equals(tblApplyElecPile.getApEpParkingOwner()))&&(null==tblApplyElecPile.getApEpParkingOwner()))){
           		if(tblApplyElecPile.getApEpParkingOwner().equals("2")){
            		tblApplyElecPile.setApEpParkingOwner("2:"+tblApplyElecPile.getApEpParkingOwnerYear()+"年");
            	}   
            	if(tblApplyElecPile.getApEpParkingOwner().equals("3")){
            		tblApplyElecPile.setApEpParkingOwner("3:"+tblApplyElecPile.getApEpParkingOwnerYear());
            	}
        	}
        	if(StringUtils.isEmpty(tblApplyElecPile.getApEpPowerPosition())){
        		if(tblApplyElecPile.getApEpPowerPosition().equals("3")){
            		tblApplyElecPile.setApEpPowerPosition("3:"+tblApplyElecPile.getApEpPowerPositionOther());
            	}	
        	}
        	
        	if(!"".equals(tblApplyElecPile.getApEpDistance())){
        		if(tblApplyElecPile.getApEpDistance().equals("3")){
            		tblApplyElecPile.setApEpDistance("3:"+tblApplyElecPile.getApEpDistanceOther()+"M");
            	}
        	}*/
        	
        	webApplyElecPileService.insertWebApplyElecPile(tblApplyElecPile);
        }catch (Exception e) {
            // 保存错误LOG
            log.error(e.getLocalizedMessage());
            log.error("申请电桩数据提交失败", e);
            
            return new FailedResponse("申请电桩数据提交失败").toString();
        }
        	// 返回处理成功信息
	        return new SuccessResponse().toString();
		}				
}
