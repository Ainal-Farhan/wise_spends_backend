package com.ainal.apps.wise_spends.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ainal.apps.wise_spends.manager.IUserManager;
import com.ainal.apps.wise_spends.util.logging.GenericLogger;

@Controller
public class HomeViewController {
	@Autowired
	IUserManager userManager;
	
	@GetMapping(path = {"/", "/home"})
	public String home() {
		new GenericLogger(this.getClass()).info(userManager.getUserAuthorities(4L).size() + "");
		return "/public/home";
	}
}
