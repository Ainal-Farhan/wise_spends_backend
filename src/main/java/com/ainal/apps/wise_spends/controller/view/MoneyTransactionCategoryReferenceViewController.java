package com.ainal.apps.wise_spends.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionCategoryReference;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionCategoryReferenceManager;
import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionCategoryReferenceVO;
import com.ainal.apps.wise_spends.view.object.form.MoneyTransactionCategoryReferenceFormVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/transaction_category_reference")
public class MoneyTransactionCategoryReferenceViewController {
	private static final String VIEW_MAIN_PATH = "/private/view/money_transaction_reference";
	private static final String TITLE = "Transaction Reference";
	private static final String SIDEBAR_LIST = "transactionCategoryReferenceList";
	private static final String SIDEBAR_ADD = "addTransactionCategoryReference";
	private static final String SIDEBAR_EDIT = SIDEBAR_LIST;

	@Autowired
	IThymeleafManager thymeleafManager;

	@Autowired
	IMoneyTransactionCategoryReferenceManager categoryReferenceManager;

	@GetMapping(path = { "", "/" })
	public ModelAndView getViewListTransactionCategoryReferencePage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", VIEW_MAIN_PATH + "/categoryList");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, TITLE);

		List<MoneyTransactionCategoryReferenceVO> moneyTransactionCategoryReferenceVOList = categoryReferenceManager
				.populateMoneyTransactionCategoryReferenceVOList(request);

		modelAndView.addObject("moneyTransactionCategoryReferenceVOList", moneyTransactionCategoryReferenceVOList);
		modelAndView.addObject("active", SIDEBAR_LIST);

		return modelAndView;
	}

	@GetMapping(path = { "/add" })
	public ModelAndView getViewAddTransactionCategoryReferencePage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", VIEW_MAIN_PATH + "/categoryForm");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, TITLE);

		modelAndView.addObject("active", SIDEBAR_ADD);
		modelAndView.addObject("categoryReferenceFormVO", new MoneyTransactionCategoryReferenceFormVO());

		return modelAndView;
	}

	@GetMapping(path = { "/edit/{moneyTransactionCategoryReferenceId}" })
	public ModelAndView getViewEditTransactionCategoryReferencePage(
			@PathVariable("moneyTransactionCategoryReferenceId") Long moneyTransactionCategoryReferenceId,
			@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", VIEW_MAIN_PATH + "/categoryForm");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, TITLE);

		MoneyTransactionCategoryReference moneyTransactionCategoryReference = categoryReferenceManager
				.getMoneyTransactionCategoryReferenceById(moneyTransactionCategoryReferenceId);

		if (moneyTransactionCategoryReference == null) {
			RedirectView redirectView = new RedirectView("/transaction_category_reference/add", true);
			redirectView.addStaticAttribute("requestType", "GET");
			return new ModelAndView(redirectView);
		}

		modelAndView.addObject("active", SIDEBAR_EDIT);
		modelAndView.addObject("categoryReferenceFormVO",
				new MoneyTransactionCategoryReferenceFormVO(moneyTransactionCategoryReference));

		return modelAndView;
	}

}
