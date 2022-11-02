package com.ssafy.ky.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.ky.dto.DongCode;
import com.ssafy.ky.model.service.HouseService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/house")
@Slf4j
@CrossOrigin("http://127.0.0.1:5500")
public class HouseController {
	
	@Autowired
	HouseService houseService;
	
	// 전체 조회
	@GetMapping("getSido")
	@ResponseBody // 응답
	public ResponseEntity<?> getSido() throws SQLException{
		
		log.debug("getSido() 메서드 실행");
		
		List<DongCode> list = houseService.getSido();
		
		for (DongCode d : list) 
		{
			System.out.println(d);
		}
		return new ResponseEntity<List<DongCode>>(list, HttpStatus.OK); // 200
		
	}
	
}