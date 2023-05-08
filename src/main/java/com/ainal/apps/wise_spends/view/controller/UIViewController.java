package com.ainal.apps.wise_spends.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;

@Controller
public class UIViewController {
	@Autowired
	IThymeleafManager thymeleafManager;

	@GetMapping("/test")
	public String testMainTemplate(Model model) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/public/ui/utilities-animation");
		thymeleafManager.setMainTemplate(model, content);
		return "/html/main-template";
	}
}
