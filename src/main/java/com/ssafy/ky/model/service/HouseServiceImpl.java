package com.ssafy.ky.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ky.dto.DongCode;
import com.ssafy.ky.dto.HouseDeal;
import com.ssafy.ky.dto.HouseInfo;
import com.ssafy.ky.model.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	HouseMapper houseMapper;
	
	@Override
	public List<DongCode> getSido() throws SQLException {
		return houseMapper.getSido();
	}
	
	@Override
	public List<DongCode> getGugun(String sidoCode) throws SQLException {
		return houseMapper.getGugun(sidoCode);
	}
	
	@Override
	public List<DongCode> getDong(String gugunCode) throws SQLException {
		return houseMapper.getDong(gugunCode);
	}
	
	@Override
	public String getDongCode(String Code) throws SQLException {
		return houseMapper.getDongCode(Code);
	}

	@Override
	public List<HouseInfo> getHouseInfo(String Code) {
		return houseMapper.getHouseInfo(Code);
	}

	@Override
	public List<HouseDeal> getHouseDeal(String aptCode) {
		return houseMapper.getHouseDeal(aptCode);
	}
}
