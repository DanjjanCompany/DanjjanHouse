package com.ssafy.ky.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.ky.dto.DongCode;

public interface HouseService {

	List<DongCode> getSido() throws SQLException;
}
