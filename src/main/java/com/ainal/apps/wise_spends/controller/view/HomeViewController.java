package com.ainal.apps.wise_spends.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;

@Controller
public class HomeViewController {
	@Autowired
	IThymeleafManager thymeleafManager;

	@GetMapping(path = { "/home" })
	public ModelAndView home() {
		return thymeleafManager.getMainTemplateModelAndView(new ThymeleafFragmentVO("content", "/public/home"), "Home");
	}
}
