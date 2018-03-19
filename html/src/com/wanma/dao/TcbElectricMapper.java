package com.wanma.dao;

import java.util.List;

import com.wanma.model.TcbElectric;
import com.wanma.model.TcbStation;

public interface TcbElectricMapper {
	public List<TcbElectric> getElectricList(TcbElectric electric);

	public List<TcbStation> getStationList(TcbStation station);
}
