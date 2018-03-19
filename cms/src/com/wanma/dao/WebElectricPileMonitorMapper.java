package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.ElectricPileList;
import com.wanma.model.TblElectricpile;

	public interface WebElectricPileMonitorMapper {
		public TblElectricpile getPileMonitorByUserId(String s);

		public List<ElectricPileList> getElectricPileMonitorForList(
				Map<String, Object> params);
}
