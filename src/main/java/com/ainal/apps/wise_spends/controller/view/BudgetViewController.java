package com.ainal.apps.wise_spends.controller.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.view.object.BudgetItemVO;
import com.ainal.apps.wise_spends.view.object.BudgetVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/budget")
public class BudgetViewController {
	@Autowired
	IThymeleafManager thymeleafManager;

	@GetMapping(path = { "", "/" })
	public ModelAndView getViewListBudgetPage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/budget/budgetList");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Budget");

		List<BudgetVO> budgetVOList = new ArrayList<>();

		modelAndView.addObject("budgetVOList", budgetVOList);
		modelAndView.addObject("active", "budgetList");

		return modelAndView;
	}

	@GetMapping(path = { "/add" })
	public ModelAndView getViewAddBudgetPage(@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/budget/addBudget");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Budget");

		modelAndView.addObject("active", "addBudget");

		return modelAndView;
	}

	@GetMapping(path = { "/edit/{budgetId}" })
	public ModelAndView getViewEditBudgetPage(@PathVariable("budgetId") Long budgetId,
			@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/budget/addBudget");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Budget");

		modelAndView.addObject("active", "addBudget");

		return modelAndView;
	}

	@GetMapping(path = { "/item/{budgetId}" })
	public ModelAndView getViewListBudgetItemPage(@PathVariable("budgetId") Long budgetId,
			@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/budget/budgetItemList");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Budget");

		List<BudgetItemVO> budgetItemVOList = new ArrayList<>();

		modelAndView.addObject("budgetItemVOList", budgetItemVOList);
		modelAndView.addObject("active", "budgetList");

		return modelAndView;
	}

	@GetMapping(path = { "/item/add/{budgetId}" })
	public ModelAndView getViewAddBudgetItemPage(@PathVariable("budgetId") Long budgetId,
			@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/budget/addBudgetItem");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Budget Item");

		modelAndView.addObject("active", "budgetList");

		return modelAndView;
	}

	@GetMapping(path = { "/item/edit/{budgetItemId}" })
	public ModelAndView getViewEditBudgetItemPage(@PathVariable("budgetItemId") Long budgetItemId,
			@NonNull HttpServletRequest request) {
		ThymeleafFragmentVO content = new ThymeleafFragmentVO("content", "/private/view/budget/addBudgetItem");
		ModelAndView modelAndView = thymeleafManager.getMainTemplateModelAndView(content, "Budget Item");

		modelAndView.addObject("active", "budgetList");

		return modelAndView;
	}
}
