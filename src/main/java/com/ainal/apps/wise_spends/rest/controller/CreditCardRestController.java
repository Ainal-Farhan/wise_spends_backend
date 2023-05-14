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

import com.ainal.apps.wise_spends.form.view.object.CreditCardFormVO;
import com.ainal.apps.wise_spends.manager.ICreditCardManager;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/credit_card")
public class CreditCardRestController {
	@Autowired
	private ICreditCardManager creditCardManager;

	@PostMapping("")
	public RedirectView addCreditCard(@ModelAttribute(name = "creditCardFormVO") CreditCardFormVO creditCardFormVO,
			@NonNull HttpServletRequest request) {
		if (creditCardFormVO.getId() != null) {
			creditCardManager.updateCreditCardForCurrentUser(request, creditCardFormVO);
		} else {
			creditCardManager.addNewCreditCardForCurrentUser(request, creditCardFormVO);
		}
		return new RedirectView("/credit_card");
	}

	@DeleteMapping("/{creditCardId}")
	public ResponseEntity<String> deleteCreditCard(@PathVariable("creditCardId") Long creditCardId) {
		creditCardManager.deleteCreditCardById(creditCardId);

		return ResponseEntity.ok("Item deleted successfully");
	}
}
