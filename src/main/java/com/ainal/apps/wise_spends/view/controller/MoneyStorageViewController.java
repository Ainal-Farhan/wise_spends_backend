package com.ainal.apps.wise_spends.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ainal.apps.wise_spends.manager.IMoneyStorageManager;
import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.view.object.MoneyStorageVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/money_storage")
public class MoneyStorageViewController {
	@Autowired
	IThymeleafManager thymeleafManager;

	@Autowired
	IMoneyStorageManager moneyStorageManager;

	@GetMapping(path = { "", "/" })
	public ModelAndView getViewListMoneyStoragePage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content",
				"/private/view/money_storage/moneyStorageList");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Money Storage");

		List<MoneyStorageVO> moneyStorageVOList = moneyStorageManager.populateMoneyStorageVOList(request);

		modelAndView.addObject("moneyStorageList", moneyStorageVOList);
		modelAndView.addObject("active", "moneyStorageList");

		return modelAndView;
	}

	@GetMapping(path = { "/add" })
	public ModelAndView getViewAddMoneyStoragePage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/money_storage/addMoneyStorage");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Money Storage");

		modelAndView.addObject("active", "addMoneyStorage");

		return modelAndView;
	}
}
