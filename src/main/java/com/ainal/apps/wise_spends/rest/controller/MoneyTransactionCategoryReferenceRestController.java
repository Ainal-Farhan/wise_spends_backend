package com.ainal.apps.wise_spends.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.form.view.object.MoneyTransactionCategoryReferenceFormVO;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionCategoryReferenceManager;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/transaction_category_reference")
public class MoneyTransactionCategoryReferenceRestController {
	@Autowired
	private IMoneyTransactionCategoryReferenceManager categoryReferenceManager;

	@PostMapping("")
	public RedirectView addMoneyTransactionCategoryReference(
			@ModelAttribute(name = "moneyTransactionCategoryReferenceFormVO") MoneyTransactionCategoryReferenceFormVO categoryReferenceFormVO,
			@NonNull HttpServletRequest request) {
		if (categoryReferenceFormVO.getId() != null) {
			categoryReferenceManager.updateMoneyTransactionCategoryReferenceForCurrentUser(request,
					categoryReferenceFormVO);
		} else {
			categoryReferenceManager.addNewMoneyTransactionCategoryReferenceForCurrentUser(request,
					categoryReferenceFormVO);
		}
		return new RedirectView("/transaction_category_reference");
	}

	@DeleteMapping("/{moneyTransactionCategoryReferenceId}")
	public ResponseEntity<String> deleteMoneyTransactionCategoryReference(
			@PathVariable("moneyTransactionCategoryReferenceId") Long moneyTransactionCategoryReferenceId) {
		categoryReferenceManager.deleteMoneyTransactionCategoryReferenceById(moneyTransactionCategoryReferenceId);

		return ResponseEntity.ok("Item deleted successfully");
	}

}
