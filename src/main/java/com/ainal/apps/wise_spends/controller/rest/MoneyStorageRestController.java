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

import com.ainal.apps.wise_spends.manager.IMoneyStorageManager;
import com.ainal.apps.wise_spends.reference.api.rest.RestApiReference;
import com.ainal.apps.wise_spends.view.object.form.MoneyStorageFormVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(RestApiReference.ENDPOINT_MONEY_STORAGE)
public class MoneyStorageRestController {

	@Autowired
	private IMoneyStorageManager moneyStorageManager;

	@PostMapping("")
	public RedirectView addMoneyStorage(
			@ModelAttribute(name = "moneyStorageFormVO") MoneyStorageFormVO moneyStorageFormVO,
			@NonNull HttpServletRequest request) {
		if (moneyStorageFormVO.getId() != null) {
			moneyStorageManager.updateMoneyStorageForCurrentUser(request, moneyStorageFormVO);
		} else {
			moneyStorageManager.addNewMoneyStorageForCurrentUser(request, moneyStorageFormVO);
		}
		return new RedirectView("/money_storage");
	}

	@DeleteMapping("/{moneyStorageId}")
	public ResponseEntity<String> deleteMoneyStorage(@PathVariable("moneyStorageId") Long moneyStorageId) {
		moneyStorageManager.deleteMoneyStorage(moneyStorageId);

		return ResponseEntity.ok("Item deleted successfully");
	}
}
