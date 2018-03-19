package com.wanma.ims.service;

import com.wanma.ims.common.domain.ConcentratorDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.ResultDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ConcentratorService {

	long getConcentratorCount(ConcentratorDO concentrator);

	List<ConcentratorDO> getConcentratorList(ConcentratorDO concentrator);

	ConcentratorDO getconcentratorInfo(ConcentratorDO concentrator);

	void modifyConcentrator(ConcentratorDO concentrator, String ids, UserDO loginUser,HttpServletRequest request) throws Exception;

	boolean addConcentrator(ConcentratorDO concentrator);

	ResultDTO<String> removeConcentrator(ConcentratorDO concentrator,HttpServletRequest request,
			UserDO loginUser);
	

}
