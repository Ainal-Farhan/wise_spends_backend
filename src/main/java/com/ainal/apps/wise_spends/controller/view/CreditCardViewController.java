package com.ainal.apps.wise_spends.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;
import com.ainal.apps.wise_spends.manager.ICreditCardManager;
import com.ainal.apps.wise_spends.manager.IMoneyStorageManager;
import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.view.object.CreditCardVO;
import com.ainal.apps.wise_spends.view.object.MoneyStorageVO;
import com.ainal.apps.wise_spends.view.object.form.CreditCardFormVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/credit_card")
public class CreditCardViewController {
	@Autowired
	IThymeleafManager thymeleafManager;

	@Autowired
	ICreditCardManager creditCardManager;

	@Autowired
	IMoneyStorageManager moneyStorageManager;

	@GetMapping(path = { "", "/" })
	public ModelAndView getViewListCreditCardPage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/creditCard/creditCardList");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Credit Card");

		List<CreditCardVO> creditCardVOList = creditCardManager.populateCreditCardVOList(request);

		modelAndView.addObject("creditCardVOList", creditCardVOList);
		modelAndView.addObject("active", "creditCardList");

		return modelAndView;
	}

	@GetMapping(path = { "/add" })
	public ModelAndView getViewAddCreditCardPage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/creditCard/addCreditCard");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Credit Card");

		List<MoneyStorageVO> moneyStorageVOList = moneyStorageManager.populateMoneyStorageVOList(request);

		modelAndView.addObject("active", "addCreditCard");
		modelAndView.addObject("creditCardFormVO", new CreditCardFormVO());
		modelAndView.addObject("moneyStorageVOList", moneyStorageVOList);

		return modelAndView;
	}

	@GetMapping(path = { "/edit/{creditCardId}" })
	public ModelAndView getViewEditCreditCardPage(@PathVariable("creditCardId") Long creditCardId,
			@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/creditCard/addCreditCard");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Credit Card");

		CreditCard creditCard = creditCardManager.getCreditCardById(creditCardId);

		if (creditCard == null) {
			RedirectView redirectView = new RedirectView("/credit_card/add", true);
			redirectView.addStaticAttribute("requestType", "GET");
			return new ModelAndView(redirectView);
		}

		List<MoneyStorageVO> moneyStorageVOList = moneyStorageManager.populateMoneyStorageVOList(request);

		modelAndView.addObject("active", "addCreditCard");
		modelAndView.addObject("creditCardFormVO", new CreditCardFormVO(creditCard));
		modelAndView.addObject("moneyStorageVOList", moneyStorageVOList);

		return modelAndView;
	}
}
