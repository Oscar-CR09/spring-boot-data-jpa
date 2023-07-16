package com.examples.springboot.app.controllers;

import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LocaleController {

	public String locale(HttpServletRequest request) {
		String ultimaUrl = request.getHeader("referer");
		
		return "redirect:".concat(ultimaUrl);
	}
}
