package com.ssafy.ky.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ky.dto.DongCode;
import com.ssafy.ky.model.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	HouseMapper houseMapper;
	
	@Override
	public List<DongCode> getSido() throws SQLException {
		return houseMapper.getSido();
	}
}
