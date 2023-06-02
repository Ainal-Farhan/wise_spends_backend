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

import com.ainal.apps.wise_spends.manager.IMoneyTransactionReferenceManager;
import com.ainal.apps.wise_spends.view.object.form.MoneyTransactionReferenceFormVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/transaction_reference")
public class MoneyTransactionReferenceRestController {
	@Autowired
	private IMoneyTransactionReferenceManager moneyTransactionReferenceManager;

	@PostMapping("")
	public RedirectView addMoneyTransactionReference(
			@ModelAttribute(name = "moneyTransactionReferenceFormVO") MoneyTransactionReferenceFormVO moneyTransactionReferenceFormVO,
			@NonNull HttpServletRequest request) {
		if (moneyTransactionReferenceFormVO.getId() != null) {
			moneyTransactionReferenceManager.updateMoneyTransactionReferenceForCurrentUser(request,
					moneyTransactionReferenceFormVO);
		} else {
			moneyTransactionReferenceManager.addNewMoneyTransactionReferenceForCurrentUser(request,
					moneyTransactionReferenceFormVO);
		}
		return new RedirectView("/transaction_reference");
	}

	@DeleteMapping("/{moneyTransactionReferenceId}")
	public ResponseEntity<String> deleteMoneyTransactionReference(
			@PathVariable("moneyTransactionReferenceId") Long moneyTransactionReferenceId) {
		moneyTransactionReferenceManager.deleteMoneyTransactionReferenceById(moneyTransactionReferenceId);

		return ResponseEntity.ok("Item deleted successfully");
	}

}
