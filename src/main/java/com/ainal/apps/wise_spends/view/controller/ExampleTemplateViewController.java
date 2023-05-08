package com.ainal.apps.wise_spends.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.util.properties.ThymeleafPropertiesUtils;

@Controller
@RequestMapping("/ui/examples/template")
public class ExampleTemplateViewController {
	@Autowired
	IThymeleafManager thymeleafManager;

	@GetMapping(path = { "", "/", "/dashboard" })
	public ModelAndView dashboard(Model model) {
		return getModelAndView("dashboard", "Dashboard");
	}

	@GetMapping("/buttons")
	public ModelAndView buttons() {
		return getModelAndView("buttons", "Buttons");
	}

	@GetMapping("/cards")
	public ModelAndView cards() {
		return getModelAndView("cards", "Cards");
	}

	@GetMapping("/utilities-color")
	public ModelAndView utilitiesColor(Model model) {
		return getModelAndView("utilities-color", "WS - Utilities Color");
	}

	@GetMapping("/utilities-animation")
	public ModelAndView utilitiesAnimation(Model model) {
		return getModelAndView("utilities-animation", "WS - Utilities Animation");
	}

	@GetMapping("/utilities-border")
	public ModelAndView utilitiesBorder(Model model) {
		return getModelAndView("utilities-border", "WS - Utilities Border");
	}

	@GetMapping("/utilities-other")
	public ModelAndView utilitiesOther(Model model) {
		return getModelAndView("utilities-other", "WS - Utilities Other");
	}

	@GetMapping("/blank")
	public ModelAndView utilitiesBlank() {
		return getModelAndView("blank", "Blank");
	}
	
	@GetMapping("/error/404")
	public ModelAndView error404() {
		return getModelAndView("404", "Error 404");
	}

	@GetMapping("/tables")
	public ModelAndView utilitiesTables() {
		ModelAndView modelAndView = getModelAndView("tables", "Tables");
		modelAndView.addObject("flagDataTable", true);
		modelAndView.addObject("flagDataTableDemo", true);
		return modelAndView;
	}

	@GetMapping("/charts")
	public ModelAndView utilitiesCharts() {
		ModelAndView modelAndView = getModelAndView("charts", "Charts");
		modelAndView.addObject("flagChartJs", true);
		modelAndView.addObject("flagChartAreaDem", true);
		modelAndView.addObject("flagChartPieDemo", true);
		modelAndView.addObject("flagChartBarDemo", true);
		return modelAndView;
	}

	@GetMapping(path = { "/login" })
	public ModelAndView getLoginView() {
		return getModelAndView2("login", "Login");
	}

	@GetMapping("/register")
	public ModelAndView register() {			
		return getModelAndView2("register", "Register");
	}

	@GetMapping("/forgot-password")
	public ModelAndView forgotPassword() {
		return getModelAndView2("forgot-password", "Forgot Password");
	}

	private ModelAndView getModelAndView(String html, String title) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content",
				ThymeleafPropertiesUtils.DIR_EXAMPLE_TEMPLATE + html);
		return thymeleafManager.getExampleMainTemplateModelAndView(content, title);
	}

	private ModelAndView getModelAndView2(String html, String title) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content",
				ThymeleafPropertiesUtils.DIR_EXAMPLE_TEMPLATE + html);
		return thymeleafManager.getExampleTemplateHeaderJsOnlyModelAndView(content, title);
	}

}
