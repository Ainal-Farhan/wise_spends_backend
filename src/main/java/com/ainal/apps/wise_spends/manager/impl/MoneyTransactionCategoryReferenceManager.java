package com.ainal.apps.wise_spends.manager.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionCategoryReference;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.service.ref.IMoneyTransactionCategoryReferenceService;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionCategoryReferenceManager;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionCategoryReferenceVO;
import com.ainal.apps.wise_spends.view.object.form.MoneyTransactionCategoryReferenceFormVO;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class MoneyTransactionCategoryReferenceManager implements IMoneyTransactionCategoryReferenceManager {
	@Autowired
	private ICurrentUserManager currentUserManager;

	@Autowired
	private IBaseManager baseManager;

	@Autowired
	IMoneyTransactionCategoryReferenceService categoryReferenceService;

	@Override
	public List<MoneyTransactionCategoryReferenceVO> populateMoneyTransactionCategoryReferenceVOList(
			HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		List<MoneyTransactionCategoryReference> moneyTransactionCategoryReferenceList = categoryReferenceService
				.findByUser(currentUser);

		return moneyTransactionCategoryReferenceList.stream().map(mtcr -> new MoneyTransactionCategoryReferenceVO(mtcr))
				.collect(Collectors.toList());
	}

	@Override
	public MoneyTransactionCategoryReference getMoneyTransactionCategoryReferenceById(
			Long moneyTransactionReferenceId) {

		return categoryReferenceService.findById(moneyTransactionReferenceId);
	}

	@Override
	public MoneyTransactionCategoryReference addNewMoneyTransactionCategoryReferenceForCurrentUser(
			HttpServletRequest request,
			MoneyTransactionCategoryReferenceFormVO moneyTransactionCategoryReferenceFormVO) {
		MoneyTransactionCategoryReference categoryReference = new MoneyTransactionCategoryReference();
		setMoneyTransactionReferenceData(categoryReference, moneyTransactionCategoryReferenceFormVO, request);
		return categoryReferenceService.save(categoryReference);
	}

	@Override
	public MoneyTransactionCategoryReference updateMoneyTransactionCategoryReferenceForCurrentUser(
			HttpServletRequest request,
			MoneyTransactionCategoryReferenceFormVO moneyTransactionCategoryReferenceFormVO) {
		MoneyTransactionCategoryReference categoryReference = getMoneyTransactionCategoryReferenceById(
				moneyTransactionCategoryReferenceFormVO.getId());
		setMoneyTransactionReferenceData(categoryReference, moneyTransactionCategoryReferenceFormVO, request);
		return categoryReferenceService.save(categoryReference);
	}

	@Override
	public boolean deleteMoneyTransactionCategoryReferenceById(Long moneyTransactionCategoryReferenceId) {
		categoryReferenceService.deleteById(moneyTransactionCategoryReferenceId);
		return true;
	}

	private void setMoneyTransactionReferenceData(MoneyTransactionCategoryReference moneyTransactionCategoryReference,
			MoneyTransactionCategoryReferenceFormVO formVO, HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);

		baseManager.setBaseEntityAttributes(moneyTransactionCategoryReference, request);
		moneyTransactionCategoryReference.setUser(currentUser);
		moneyTransactionCategoryReference.setTitle(formVO.getTitle());
		moneyTransactionCategoryReference.setDescription(formVO.getDescription());
	}

}
