package com.wanma.polling.tasker;

import java.util.List;
import java.util.TimerTask;

import com.bluemobi.product.utils.SpringContextHolder;
import com.wanma.app.service.ElectricPileListService;
import com.wanma.model.TblElectricpile;

public class OkjTimer extends TimerTask {

	@Override
	public void run() {
		ElectricPileListService electricPileListService = (ElectricPileListService) SpringContextHolder
				.getSpringContext().getBean(ElectricPileListService.class);
		// 获取需要下线的电桩
		List<TblElectricpile> electricpileList = electricPileListService
				.getOfflineElectric();
		if (null != electricpileList && electricpileList.size() > 0) {
			for (TblElectricpile electricpile : electricpileList) {
				electricpile.setElpiState(10);
				electricpile.setOfflineTime("");
				electricPileListService.updateEleState(electricpile);
			}
		}
	}

}
