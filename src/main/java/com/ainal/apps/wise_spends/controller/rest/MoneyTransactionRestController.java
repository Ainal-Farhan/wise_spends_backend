package com.ainal.apps.wise_spends.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.manager.IMoneyTransactionManager;
import com.ainal.apps.wise_spends.reference.api.rest.RestApiReference;
import com.ainal.apps.wise_spends.view.object.form.MoneyTransactionFormVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(RestApiReference.ENDPOINT_MONEY_TRANSACTION)
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
