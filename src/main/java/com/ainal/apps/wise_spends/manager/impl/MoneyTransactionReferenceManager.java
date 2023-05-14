package com.ainal.apps.wise_spends.manager.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionReference;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.service.ref.IMoneyTransactionReferenceService;
import com.ainal.apps.wise_spends.form.view.object.MoneyTransactionReferenceFormVO;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionCategoryReferenceManager;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionReferenceManager;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionReferenceVO;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class MoneyTransactionReferenceManager implements IMoneyTransactionReferenceManager {
	@Autowired
	private ICurrentUserManager currentUserManager;

	@Autowired
	private IBaseManager baseManager;

	@Autowired
	private IMoneyTransactionCategoryReferenceManager categoryReferenceManager;

	@Autowired
	private IMoneyTransactionReferenceService moneyTransactionReferenceService;

	@Override
	public List<MoneyTransactionReferenceVO> populateMoneyTransactionReferenceVOList(HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		List<MoneyTransactionReference> moneyTransactionReferenceVOList = moneyTransactionReferenceService
				.findMoneyTransactionReferenceListByUser(currentUser);
		return moneyTransactionReferenceVOList.stream().map(mtr -> new MoneyTransactionReferenceVO(mtr))
				.collect(Collectors.toList());
	}

	@Override
	public MoneyTransactionReference getMoneyTransactionReferenceById(Long moneyTransactionReferenceId) {
		return moneyTransactionReferenceService.findMoneyTransactionReferenceById(moneyTransactionReferenceId);
	}

	@Override
	public MoneyTransactionReference addNewMoneyTransactionReferenceForCurrentUser(HttpServletRequest request,
			MoneyTransactionReferenceFormVO moneyTransactionReferenceFormVO) {

		MoneyTransactionReference moneyTransactionReference = new MoneyTransactionReference();
		setMoneyTransactionReferenceData(moneyTransactionReference, moneyTransactionReferenceFormVO, request);

		return moneyTransactionReferenceService.saveMoneyTransactionReference(moneyTransactionReference);
	}

	@Override
	public MoneyTransactionReference updateMoneyTransactionReferenceForCurrentUser(HttpServletRequest request,
			MoneyTransactionReferenceFormVO moneyTransactionReferenceFormVO) {

		MoneyTransactionReference moneyTransactionReference = getMoneyTransactionReferenceById(
				moneyTransactionReferenceFormVO.getId());
		setMoneyTransactionReferenceData(moneyTransactionReference, moneyTransactionReferenceFormVO, request);

		return moneyTransactionReferenceService.saveMoneyTransactionReference(moneyTransactionReference);
	}

	@Override
	public boolean deleteMoneyTransactionReferenceById(Long moneyTransactionReferenceId) {
		moneyTransactionReferenceService.deleteMoneyTransactionReferenceById(moneyTransactionReferenceId);
		return true;
	}

	private void setMoneyTransactionReferenceData(MoneyTransactionReference moneyTransactionReference,
			MoneyTransactionReferenceFormVO formVO, HttpServletRequest request) {
		baseManager.setBaseEntityAttributes(moneyTransactionReference, request);
		moneyTransactionReference.setTitle(formVO.getTitle());
		moneyTransactionReference.setDescription(formVO.getDescription());
		moneyTransactionReference
				.setCode((StringUtils.isBlank(formVO.getCode()) ? Strings.EMPTY : formVO.getCode()).toUpperCase());
		moneyTransactionReference.setMoneyTransactionCategoryReference(
				categoryReferenceManager.getMoneyTransactionCategoryReferenceById(formVO.getCategoryReferenceId()));
		;

	}

}
