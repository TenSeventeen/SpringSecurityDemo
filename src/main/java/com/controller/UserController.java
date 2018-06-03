package com.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	//login 请求方式不能写Post 会报错 且SpringSecurity自带
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String myMethod() {
		return "login";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String toIndex() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		System.out.println(userDetails);
		return "index";
	}
	
}
