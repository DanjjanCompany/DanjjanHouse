package com.ssafy.ky.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ky.dto.DongCode;

@Mapper
public interface HouseMapper {

	List<DongCode> getSido() throws SQLException;
}
