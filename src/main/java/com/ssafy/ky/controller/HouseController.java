package com.ssafy.ky.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.ky.dto.DongCode;
import com.ssafy.ky.dto.HouseDeal;
import com.ssafy.ky.dto.HouseInfo;
import com.ssafy.ky.model.service.HouseService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/house")
@Slf4j
@CrossOrigin("*")
<<<<<<< HEAD
//@CrossOrigin("http://127.0.0.1:5500")
=======
>>>>>>> 876431a8114cc098c5fbd5f8d1a8f1628a77a8cf
public class HouseController {
	
	static String sidoC = null;
	static String gugunC = null;
	static String dongC = null;
	
	@Autowired
	HouseService houseService;
	
	// 전체 조회
	@GetMapping("sido")
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
	
	@GetMapping("gugun")
	@ResponseBody // 응답
	public ResponseEntity<?> getGugun(@RequestParam String sidoCode) throws SQLException{
		sidoC = sidoCode;
		log.debug(sidoCode);
		log.debug("getGugun() 메서드 실행");
		
		List<DongCode> list = houseService.getGugun(sidoCode);
		
		for (DongCode d : list) 
		{
			System.out.println(d);
		}
		return new ResponseEntity<List<DongCode>>(list, HttpStatus.OK); // 200
		
	}
	
	@GetMapping("dong")
	@ResponseBody // 응답
	public ResponseEntity<?> getDong(@RequestParam String gugunCode) throws SQLException{
		gugunC = gugunCode;
		log.debug(gugunCode);
		log.debug("getDong() 메서드 실행");
		
		List<DongCode> list = houseService.getDong(gugunCode);
		
		for (DongCode d : list) 
		{
			System.out.println(d);
		}
		return new ResponseEntity<List<DongCode>>(list, HttpStatus.OK); // 200
		
	}
	
	@GetMapping("dongCode")
	@ResponseBody // 응답
	public ResponseEntity<?> getDongCode(@RequestParam String Code) throws SQLException{
		
		log.debug("getDongCode 실행");
		String ReDongCode = Code;
		log.debug(ReDongCode);

		//dongC = houseService.getDCode(ReDongCode);


		List<HouseInfo> list = houseService.getHouseInfo(ReDongCode);

		/*
		 * JsonArray jsonArray =
		 * JsonParser.parseString(gson.toJson(list)).getAsJsonArray();
		 * 
		 * JsonObject jsonObject = new JsonObject();
		 */

		Map<String,Object> map = new HashMap<String, Object>();
		String add = sidoC + " " + gugunC;

		map.put("address", add);

		map.put("dongCode", ReDongCode);

		map.put("apt", list);

		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK); // 200
		
	}
	
	@GetMapping("tradeCode")
	@ResponseBody // 응답
	public ResponseEntity<?> getTradeCode(@RequestParam String Code) throws SQLException{
		
		log.debug("getTradeCode() 메서드 실행");
		log.debug(Code);
		List<HouseInfo> list = houseService.getHouseInfo(Code);

		List<HouseDeal> dealList = houseService.getHouseDeal(list.get(0).getAptCode());
		
		
		return new ResponseEntity<List<HouseDeal>>(dealList, HttpStatus.OK); // 200
		/**
		 * 
		 * {
		 * 	"dongC" : "dd",
		 * 	"list" : [
		 * 		{},
		 * 		{}
		 * 	]
		 * }
		 */
		
	}
	
}