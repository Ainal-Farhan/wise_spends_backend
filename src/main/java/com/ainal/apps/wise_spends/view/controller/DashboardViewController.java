package com.ainal.apps.wise_spends.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardViewController {
	@GetMapping(path = { "/", "/dashboard" })
	public String dashboard() {
		return "/public/dashboard";
	}
}
