package com.ssafy.ky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/go")
public class PageController {

	@GetMapping("/member-list")
	public String memberList() {
		return "user/list";
	}
	
	@GetMapping("/searchHouse")
	public String goHouse() {
		return "house/search";
	}
	
	@GetMapping("/home")
	public String goHome() {
		return "index";
	}
}

