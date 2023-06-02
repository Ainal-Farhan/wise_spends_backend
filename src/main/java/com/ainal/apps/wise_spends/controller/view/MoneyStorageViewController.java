package com.ainal.apps.wise_spends.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.reference.MoneyStorageTypeEnum;
import com.ainal.apps.wise_spends.manager.IMoneyStorageManager;
import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.view.object.MoneyStorageVO;
import com.ainal.apps.wise_spends.view.object.form.MoneyStorageFormVO;

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

		modelAndView.addObject("moneyStorageVOList", moneyStorageVOList);
		modelAndView.addObject("active", "moneyStorageList");

		return modelAndView;
	}

	@GetMapping(path = { "/add" })
	public ModelAndView getViewAddMoneyStoragePage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/money_storage/addMoneyStorage");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Money Storage");

		modelAndView.addObject("active", "addMoneyStorage");
		modelAndView.addObject("moneyStorageFormVO", new MoneyStorageFormVO());
		modelAndView.addObject("types", MoneyStorageTypeEnum.values());

		return modelAndView;
	}

	@GetMapping(path = { "/edit/{moneyStorageId}" })
	public ModelAndView getViewEditMoneyStoragePage(@PathVariable("moneyStorageId") Long moneyStorageId,
			@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/money_storage/addMoneyStorage");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Money Storage");

		MoneyStorage moneyStorage = moneyStorageManager.getMoneyStorageById(moneyStorageId);

		if (moneyStorage == null) {
			RedirectView redirectView = new RedirectView("/money_storage/add", true);
			redirectView.addStaticAttribute("requestType", "GET");
			return new ModelAndView(redirectView);
		}

		modelAndView.addObject("active", "addMoneyStorage");
		modelAndView.addObject("moneyStorageFormVO", new MoneyStorageFormVO(moneyStorage));
		modelAndView.addObject("types", MoneyStorageTypeEnum.values());

		return modelAndView;
	}
}
