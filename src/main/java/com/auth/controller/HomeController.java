package com.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@RequestMapping("/welcome")
	public String welcome() {
		String text = "this is private page ";
				text+="this page n is not allowed to unauthenticatd users ";
		
		return text ;
		
	}
	

}
