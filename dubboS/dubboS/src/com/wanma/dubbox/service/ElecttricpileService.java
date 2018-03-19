package com.wanma.dubbox.service;

import com.wanma.dubbox.model.TblElectricpile;

public interface ElecttricpileService {
	/**
	 * 通过ID获取电桩数据
	 */
	public TblElectricpile getElectricpileById(String eId);
}