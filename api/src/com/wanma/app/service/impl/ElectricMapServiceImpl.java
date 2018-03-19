package com.wanma.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.ElectricMapMapper;
import com.wanma.app.service.ElectricMapService;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.ElectricMap;
import com.wanma.model.RateInfo;

@Service
public class ElectricMapServiceImpl implements ElectricMapService {
	@Autowired
	private ElectricMapMapper mapMapper;

	@Override
	public List<ElectricMap> getElectricMap(ElectricMap map) {
		// TODO Auto-generated method stub

		List<ElectricMap> powerList_data = mapMapper.getElectricMap(map);
		List<ElectricMap> powerList = new ArrayList<ElectricMap>();
		;

		for (int i = 0; i < powerList_data.size(); i++) {
			ElectricMap item = powerList_data.get(i);

			if (item.getRateId() == null) {
				item.setCurrentRate(new BigDecimal(0));
			} else {
				RateInfo de = new RateInfo();
				de.setRateId(item.getRateId());
				RateInfo rate = mapMapper.getRateInfo(de);

				// 电费格式化
				String mark = PowerStationDetailServiceImpl
						.getCurrentPowerRateMark(JudgeNullUtils.nvlStr(rate
								.getRaInQuantumDate()));
				switch (mark) {
				case "1":
					item.setCurrentRate(new BigDecimal(JudgeNullUtils
							.nvlStr(rate.getRaInTipTimeTariff())).add(new BigDecimal(JudgeNullUtils
									.nvlStr(rate.getRaInServiceCharge()))));
					break;
				case "2":
					item.setCurrentRate(new BigDecimal(JudgeNullUtils
							.nvlStr(rate.getRaInPeakElectricityPrice())).add(new BigDecimal(JudgeNullUtils
									.nvlStr(rate.getRaInServiceCharge()))));
					break;
				case "3":
					item.setCurrentRate(new BigDecimal(JudgeNullUtils
							.nvlStr(rate.getRaInUsualPrice())).add(new BigDecimal(JudgeNullUtils
									.nvlStr(rate.getRaInServiceCharge()))));
					break;
				case "4":
					item.setCurrentRate(new BigDecimal(JudgeNullUtils
							.nvlStr(rate.getRaInValleyTimePrice())).add(new BigDecimal(JudgeNullUtils
									.nvlStr(rate.getRaInServiceCharge()))));
					break;
				default:
					item.setCurrentRate(new BigDecimal(0));
				}
			}
			powerList.add(item);

		}

		return powerList;
	}

	@Override
	public List<ElectricMap> getElectricMapBysearch(ElectricMap map) {
		// TODO Auto-generated method stub
		List<ElectricMap> powerList_data = mapMapper.getElectricMapBysearch(map);
		
		List<ElectricMap> powerList = new ArrayList<ElectricMap>();
		

		for (int i = 0; i < powerList_data.size(); i++) {
			ElectricMap item = powerList_data.get(i);

			if (item.getRateId() == null) {
				item.setCurrentRate(new BigDecimal(0));
			} else {
				RateInfo de = new RateInfo();
				de.setRateId(item.getRateId());
				RateInfo rate = mapMapper.getRateInfo(de);

				// 电费格式化
				String mark = PowerStationDetailServiceImpl
						.getCurrentPowerRateMark(JudgeNullUtils.nvlStr(rate
								.getRaInQuantumDate()));
				switch (mark) {
				case "1":
					item.setCurrentRate(new BigDecimal(JudgeNullUtils
							.nvlStr(rate.getRaInTipTimeTariff())).add(new BigDecimal(JudgeNullUtils
									.nvlStr(rate.getRaInServiceCharge()))));
					break;
				case "2":
					item.setCurrentRate(new BigDecimal(JudgeNullUtils
							.nvlStr(rate.getRaInPeakElectricityPrice())).add(new BigDecimal(JudgeNullUtils
									.nvlStr(rate.getRaInServiceCharge()))));
					break;
				case "3":
					item.setCurrentRate(new BigDecimal(JudgeNullUtils
							.nvlStr(rate.getRaInUsualPrice())).add(new BigDecimal(JudgeNullUtils
									.nvlStr(rate.getRaInServiceCharge()))));
					break;
				case "4":
					item.setCurrentRate(new BigDecimal(JudgeNullUtils
							.nvlStr(rate.getRaInValleyTimePrice())).add(new BigDecimal(JudgeNullUtils
									.nvlStr(rate.getRaInServiceCharge()))));
					break;
				default:
					item.setCurrentRate(new BigDecimal(0));
				}
			}
			powerList.add(item);

		}

		return powerList;

	}

}
