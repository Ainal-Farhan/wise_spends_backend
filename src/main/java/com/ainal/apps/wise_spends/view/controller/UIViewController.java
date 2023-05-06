package com.ainal.apps.wise_spends.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIViewController {

	@GetMapping("/buttons")
	public String buttons() {
		return "/public/ui/buttons";
	}

	@GetMapping("/cards")
	public String cards() {
		return "/public/ui/cards";
	}

	@GetMapping("/utilities-color")
	public String utilitiesColor(Model model) {
		model.addAttribute("content", "utilities_color");
		model.addAttribute("title", "WS - Utilities Color");
		return "/public/index";
	}

	@GetMapping("/utilities-animation")
	public String utilitiesAnimation(Model model) {
		model.addAttribute("content", "utilities_animation");
		model.addAttribute("title", "WS - Utilities Animation");
		return "/public/index";
	}

	@GetMapping("/utilities-border")
	public String utilitiesBorder(Model model) {
		model.addAttribute("content", "utilities_border");
		model.addAttribute("title", "WS - Utilities Border");
		return "/public/index";
	}

	@GetMapping("/utilities-other")
	public String utilitiesOther(Model model) {
		model.addAttribute("content", "utilities_other");
		model.addAttribute("title", "WS - Utilities Other");
		return "/public/index";
	}

	@GetMapping("/tables")
	public String utilitiesTables() {
		return "/public/ui/tables";
	}

	@GetMapping("/charts")
	public String utilitiesCharts() {
		return "/public/ui/charts";
	}

	@GetMapping("/blank")
	public String utilitiesBlank() {
		return "/public/blank";
	}

	@GetMapping("/register")
	public String register() {
		return "/public/register";
	}

	@GetMapping("/forgot-password")
	public String forgotPassword() {
		return "/public/forgot-password";
	}
}
