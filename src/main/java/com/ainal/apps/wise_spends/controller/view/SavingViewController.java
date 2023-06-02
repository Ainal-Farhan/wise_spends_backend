package com.ainal.apps.wise_spends.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.common.domain.mny.Saving;
import com.ainal.apps.wise_spends.manager.IMoneyStorageManager;
import com.ainal.apps.wise_spends.manager.ISavingManager;
import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.view.object.MoneyStorageVO;
import com.ainal.apps.wise_spends.view.object.SavingVO;
import com.ainal.apps.wise_spends.view.object.form.SavingFormVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/saving")
public class SavingViewController {
	@Autowired
	IThymeleafManager thymeleafManager;

	@Autowired
	ISavingManager savingManager;

	@Autowired
	IMoneyStorageManager moneyStorageManager;

	@GetMapping(path = { "", "/" })
	public ModelAndView getViewListSavingPage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/saving/savingList");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Saving");

		List<SavingVO> savingVOList = savingManager.populateSavingVOList(request);

		modelAndView.addObject("savingVOList", savingVOList);
		modelAndView.addObject("active", "savingList");

		return modelAndView;
	}

	@GetMapping(path = { "/add" })
	public ModelAndView getViewAddSavingPage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/saving/addSaving");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Saving");

		List<MoneyStorageVO> moneyStorageVOList = moneyStorageManager.populateMoneyStorageVOList(request);

		modelAndView.addObject("active", "addSaving");
		modelAndView.addObject("savingFormVO", new SavingFormVO());
		modelAndView.addObject("moneyStorageVOList", moneyStorageVOList);

		return modelAndView;
	}

	@GetMapping(path = { "/edit/{savingId}" })
	public ModelAndView getViewEditSavingPage(@PathVariable("savingId") Long savingId,
			@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/saving/addSaving");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Saving");

		Saving saving = savingManager.getSavingById(savingId);

		if (saving == null) {
			RedirectView redirectView = new RedirectView("/saving/add", true);
			redirectView.addStaticAttribute("requestType", "GET");
			return new ModelAndView(redirectView);
		}

		List<MoneyStorageVO> moneyStorageVOList = moneyStorageManager.populateMoneyStorageVOList(request);

		modelAndView.addObject("active", "addSaving");
		modelAndView.addObject("savingFormVO", new SavingFormVO(saving));
		modelAndView.addObject("moneyStorageVOList", moneyStorageVOList);

		return modelAndView;
	}

}
