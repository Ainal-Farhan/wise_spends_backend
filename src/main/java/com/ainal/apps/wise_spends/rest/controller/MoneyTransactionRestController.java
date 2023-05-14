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

import com.ainal.apps.wise_spends.form.view.object.MoneyTransactionFormVO;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionManager;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/transaction")
public class MoneyTransactionRestController {
	@Autowired
	private IMoneyTransactionManager moneyTransactionManager;

	@PostMapping("")
	public RedirectView addMoneyTransaction(
			@ModelAttribute(name = "moneyTransactionReferenceFormVO") MoneyTransactionFormVO moneyTransactionFormVO,
			@NonNull HttpServletRequest request) {
		if (moneyTransactionFormVO.getId() != null) {
			moneyTransactionManager.updateMoneyTransactionForCurrentUser(request, moneyTransactionFormVO);
		} else {
			moneyTransactionManager.addNewMoneyTransactionForCurrentUser(request, moneyTransactionFormVO);
		}
		return new RedirectView("/transaction");
	}

	@DeleteMapping("/{moneyTransactionId}")
	public ResponseEntity<String> deleteMoneyTransaction(@PathVariable("moneyTransactionId") Long moneyTransactionId) {
		moneyTransactionManager.deleteMoneyTransactionById(moneyTransactionId);

		return ResponseEntity.ok("Item deleted successfully");
	}

}
