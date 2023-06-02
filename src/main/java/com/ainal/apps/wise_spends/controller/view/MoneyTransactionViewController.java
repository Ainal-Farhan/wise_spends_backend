package com.ainal.apps.wise_spends.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;
import com.ainal.apps.wise_spends.common.reference.MoneyTransactionTypeEnum;
import com.ainal.apps.wise_spends.common.reference.YesNoEnum;
import com.ainal.apps.wise_spends.common.service.mny.ICreditCardService;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyStorageService;
import com.ainal.apps.wise_spends.common.service.mny.ISavingService;
import com.ainal.apps.wise_spends.constant.MoneyTransactionConstant;
import com.ainal.apps.wise_spends.manager.IMoneyStorageManager;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionManager;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionReferenceManager;
import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionReferenceVO;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionVO;
import com.ainal.apps.wise_spends.view.object.SelectItemVO;
import com.ainal.apps.wise_spends.view.object.form.MoneyTransactionFormVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/transaction")
public class MoneyTransactionViewController {
	private static final String VIEW_MAIN_PATH = "/private/view/money_transaction";
	private static final String TITLE = "Transaction";
	private static final String SIDEBAR_LIST = "transactionList";
	private static final String SIDEBAR_ADD = "makeTransaction";
	private static final String SIDEBAR_EDIT = SIDEBAR_LIST;

	@Autowired
	private IThymeleafManager thymeleafManager;

	@Autowired
	private IMoneyTransactionManager moneyTransactionManager;

	@Autowired
	private IMoneyTransactionReferenceManager moneyTransactionReferenceManager;

	@Autowired
	private IMoneyStorageManager moneyStorageManager;

	@Autowired
	private IMoneyStorageService moneyStorageService;

	@Autowired
	private ICreditCardService creditCardService;

	@Autowired
	private ISavingService savingService;

	@GetMapping(path = { "", "/" })
	public ModelAndView getViewListTransactionPage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", VIEW_MAIN_PATH + "/transactionList");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, TITLE);

		List<MoneyTransactionVO> moneyTransactionVOList = moneyTransactionManager
				.populateMoneyTransactionVOListLast30Days(request);

		modelAndView.addObject("moneyTransactionVOList", moneyTransactionVOList);
		modelAndView.addObject("active", SIDEBAR_LIST);

		return modelAndView;
	}

	@GetMapping(path = { "/add" })
	public ModelAndView getViewAddTransactionPage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", VIEW_MAIN_PATH + "/transactionForm");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, TITLE);

		List<MoneyTransactionReferenceVO> moneyTransactionReferenceVOList = moneyTransactionReferenceManager
				.populateMoneyTransactionReferenceVOList(request);

		modelAndView.addObject("active", SIDEBAR_ADD);
		modelAndView.addObject("moneyTransactionFormVO", new MoneyTransactionFormVO());
		modelAndView.addObject("referenceVOList", moneyTransactionReferenceVOList);
		modelAndView.addObject("types", MoneyTransactionTypeEnum.values());
		modelAndView.addObject("yesNoOptions", YesNoEnum.values());
		modelAndView.addObject("fromIdSelectItemList",
				moneyStorageManager.populateMoneyStorageSavingCreditCardSelectItemVOList(request));

		return modelAndView;
	}

	@GetMapping(path = { "/edit/{moneyTransactionId}" })
	public ModelAndView getViewEditTransactionPage(@PathVariable("moneyTransactionId") Long moneyTransactionId,
			@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", VIEW_MAIN_PATH + "/transactionForm");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, TITLE);

		MoneyTransaction moneyTransaction = moneyTransactionManager.getMoneyTransactionById(moneyTransactionId);

		if (moneyTransaction == null) {
			RedirectView redirectView = new RedirectView("/transaction/add", true);
			redirectView.addStaticAttribute("requestType", "GET");
			return new ModelAndView(redirectView);
		}

		SelectItemVO from = new SelectItemVO();
		from.setValue(moneyTransaction.getFromId());

		if (moneyTransaction.getFlagMoneyStorage()) {
			from.setTitle(MoneyTransactionConstant.MONEY_STORAGE_PREFIX
					+ moneyStorageService.findMoneyStorageById((Long) from.getValue()).getFullName());
		} else if (moneyTransaction.getFlagCreditCard()) {
			from.setTitle(MoneyTransactionConstant.CREDIT_CARD_PREFIX
					+ creditCardService.findCreditCardById((Long) from.getValue()).getShortName());
		} else if (moneyTransaction.getFlagSaving()) {
			from.setTitle(MoneyTransactionConstant.SAVING_PREFIX
					+ savingService.findSavingById((Long) from.getValue()).getShortName());
		}

		List<MoneyTransactionReferenceVO> moneyTransactionReferenceVOList = moneyTransactionReferenceManager
				.populateMoneyTransactionReferenceVOList(request);

		modelAndView.addObject("active", SIDEBAR_EDIT);
		modelAndView.addObject("moneyTransactionFormVO", new MoneyTransactionFormVO(moneyTransaction, from));
		modelAndView.addObject("referenceVOList", moneyTransactionReferenceVOList);
		modelAndView.addObject("types", MoneyTransactionTypeEnum.values());
		modelAndView.addObject("yesNoOptions", YesNoEnum.values());
		modelAndView.addObject("fromIdSelectItemList",
				moneyStorageManager.populateMoneyStorageSavingCreditCardSelectItemVOList(request));

		return modelAndView;
	}

}
