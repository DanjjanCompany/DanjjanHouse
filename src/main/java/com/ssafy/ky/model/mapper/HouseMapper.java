package com.ssafy.ky.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ky.dto.DongCode;
import com.ssafy.ky.dto.HouseDeal;
import com.ssafy.ky.dto.HouseInfo;

@Mapper
public interface HouseMapper {

	List<DongCode> getSido() throws SQLException;
	List<DongCode> getGugun(String sidoCode) throws SQLException;
	List<DongCode> getDong(String gugunCode);
	String getDongCode(String Code);
	List<HouseInfo> getHouseInfo(String Code);
	List<HouseDeal> getHouseDeal(String aptCode);
}
