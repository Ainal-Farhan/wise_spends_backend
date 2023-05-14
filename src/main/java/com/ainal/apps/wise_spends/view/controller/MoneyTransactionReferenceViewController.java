package com.ainal.apps.wise_spends.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionReference;
import com.ainal.apps.wise_spends.form.view.object.MoneyTransactionReferenceFormVO;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionCategoryReferenceManager;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionReferenceManager;
import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionCategoryReferenceVO;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionReferenceVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/transaction_reference")
public class MoneyTransactionReferenceViewController {
	private static final String VIEW_MAIN_PATH = "/private/view/money_transaction_reference";
	private static final String TITLE = "Transaction Reference";
	private static final String SIDEBAR_LIST = "transactionReferenceList";
	private static final String SIDEBAR_ADD = "addTransactionReference";
	private static final String SIDEBAR_EDIT = SIDEBAR_LIST;

	@Autowired
	IThymeleafManager thymeleafManager;

	@Autowired
	IMoneyTransactionReferenceManager moneyTransactionReferenceManager;

	@Autowired
	IMoneyTransactionCategoryReferenceManager categoryReferenceManager;

	@GetMapping(path = { "", "/" })
	public ModelAndView getViewListTransactionReferencePage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", VIEW_MAIN_PATH + "/referenceList");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, TITLE);

		List<MoneyTransactionReferenceVO> moneyTransactionReferenceVOList = moneyTransactionReferenceManager
				.populateMoneyTransactionReferenceVOList(request);

		modelAndView.addObject("moneyTransactionReferenceVOList", moneyTransactionReferenceVOList);
		modelAndView.addObject("active", SIDEBAR_LIST);

		return modelAndView;
	}

	@GetMapping(path = { "/add" })
	public ModelAndView getViewAddTransactionReferencePage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", VIEW_MAIN_PATH + "/referenceForm");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, TITLE);

		List<MoneyTransactionCategoryReferenceVO> moneyTransactionCategoryReferenceVOList = categoryReferenceManager
				.populateMoneyTransactionCategoryReferenceVOList(request);

		modelAndView.addObject("active", SIDEBAR_ADD);
		modelAndView.addObject("moneyTransactionReferenceFormVO", new MoneyTransactionReferenceFormVO());
		modelAndView.addObject("moneyTransactionCategoryReferenceVOList", moneyTransactionCategoryReferenceVOList);

		return modelAndView;
	}

	@GetMapping(path = { "/edit/{moneyTransactionReferenceId}" })
	public ModelAndView getViewEditTransactionReferencePage(
			@PathVariable("moneyTransactionReferenceId") Long moneyTransactionReferenceId,
			@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", VIEW_MAIN_PATH + "/referenceForm");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, TITLE);

		MoneyTransactionReference moneyTransactionReference = moneyTransactionReferenceManager
				.getMoneyTransactionReferenceById(moneyTransactionReferenceId);

		if (moneyTransactionReference == null) {
			RedirectView redirectView = new RedirectView("/transaction_reference/add", true);
			redirectView.addStaticAttribute("requestType", "GET");
			return new ModelAndView(redirectView);
		}

		List<MoneyTransactionCategoryReferenceVO> moneyTransactionCategoryReferenceVOList = categoryReferenceManager
				.populateMoneyTransactionCategoryReferenceVOList(request);

		modelAndView.addObject("active", SIDEBAR_EDIT);
		modelAndView.addObject("moneyTransactionReferenceFormVO",
				new MoneyTransactionReferenceFormVO(moneyTransactionReference));
		modelAndView.addObject("moneyTransactionCategoryReferenceVOList", moneyTransactionCategoryReferenceVOList);

		return modelAndView;
	}
}
