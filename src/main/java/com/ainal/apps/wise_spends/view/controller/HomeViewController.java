package com.ainal.apps.wise_spends.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {
	@GetMapping(path = { "/", "/home" })
	public String home() {
		return "/public/home";
	}
}
