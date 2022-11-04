package com.ssafy.ky.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.ky.dto.DongCode;
import com.ssafy.ky.dto.HouseDeal;
import com.ssafy.ky.dto.HouseInfo;

public interface HouseService {

	List<DongCode> getSido() throws SQLException;

	List<DongCode> getGugun(String sidoCode) throws SQLException;

	List<DongCode> getDong(String gugunCode) throws SQLException;

	String getDongCode(String Code) throws SQLException;

	List<HouseInfo> getHouseInfo(String Code);

	List<HouseDeal> getHouseDeal(String aptCode);

	String getDCode(String reDongCode);
}
