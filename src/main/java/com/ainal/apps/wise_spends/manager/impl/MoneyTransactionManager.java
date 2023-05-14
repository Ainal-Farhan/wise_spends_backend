package com.ainal.apps.wise_spends.manager.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;
import com.ainal.apps.wise_spends.common.domain.mny.Saving;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.reference.MoneyTransactionTypeEnum;
import com.ainal.apps.wise_spends.common.service.mny.ICreditCardService;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyStorageService;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyTransactionService;
import com.ainal.apps.wise_spends.common.service.mny.ISavingService;
import com.ainal.apps.wise_spends.common.service.ref.IMoneyTransactionReferenceService;
import com.ainal.apps.wise_spends.form.view.object.MoneyTransactionFormVO;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionManager;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionVO;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class MoneyTransactionManager implements IMoneyTransactionManager {
	@Autowired
	ICurrentUserManager currentUserManager;

	@Autowired
	IBaseManager baseManager;

	@Autowired
	IMoneyTransactionService moneyTransactionService;

	@Autowired
	IMoneyTransactionReferenceService moneyTransactionReferenceService;

	@Autowired
	ICreditCardService creditCardService;

	@Autowired
	ISavingService savingService;

	@Autowired
	IMoneyStorageService moneyStorageService;

	@Override
	public List<MoneyTransactionVO> populateMoneyTransactionVOList(HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		List<MoneyTransaction> moneyTransactionList = moneyTransactionService.findByUser(currentUser);

		return moneyTransactionList.stream().map(mt -> new MoneyTransactionVO(mt)).collect(Collectors.toList());
	}

	@Override
	public MoneyTransaction getMoneyTransactionById(Long moneyTransactionId) {
		return moneyTransactionService.findById(moneyTransactionId);
	}

	@Override
	public MoneyTransaction addNewMoneyTransactionForCurrentUser(HttpServletRequest request,
			MoneyTransactionFormVO moneyTransactionFormVO) {
		MoneyTransaction moneyTransaction = new MoneyTransaction();
		setMoneyTransactionData(moneyTransaction, moneyTransactionFormVO, request);
		moneyTransaction = moneyTransactionService.save(moneyTransaction);
		afterSave(moneyTransaction, moneyTransactionFormVO, request);
		return moneyTransaction;
	}

	@Override
	public MoneyTransaction updateMoneyTransactionForCurrentUser(HttpServletRequest request,
			MoneyTransactionFormVO moneyTransactionFormVO) {
		MoneyTransaction moneyTransaction = getMoneyTransactionById(moneyTransactionFormVO.getId());
		setMoneyTransactionData(moneyTransaction, moneyTransactionFormVO, request);
		moneyTransaction = moneyTransactionService.save(moneyTransaction);
		afterSave(moneyTransaction, moneyTransactionFormVO, request);
		return moneyTransaction;
	}

	@Override
	public boolean deleteMoneyTransactionById(Long moneyTransactionId) {
		moneyTransactionService.deleteById(moneyTransactionId);
		return true;
	}

	private void setMoneyTransactionData(MoneyTransaction moneyTransaction,
			MoneyTransactionFormVO moneyTransactionFormVO, HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		baseManager.setBaseEntityAttributes(moneyTransaction, currentUser);

		moneyTransaction.setUser(currentUser);
		moneyTransaction.setTransactionDate(Date
				.from(moneyTransactionFormVO.getTransactionDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		moneyTransaction.setAmount(moneyTransactionFormVO.getAmount());
		moneyTransaction.setMoneyTransactionReference(moneyTransactionReferenceService
				.findMoneyTransactionReferenceById(moneyTransactionFormVO.getMoneyTransactionReferenceId()));
		moneyTransaction.setType(moneyTransactionFormVO.getType());
		moneyTransaction.setFromId(Long.parseLong(moneyTransactionFormVO.getFrom().getValue().toString()));

		moneyTransaction.setFlagCreditCard(false);
		moneyTransaction.setFlagMoneyStorage(false);
		moneyTransaction.setFlagSaving(false);

		if (moneyTransactionFormVO.getFrom().getTitle().contains("Money Storage - ")) {
			moneyTransaction.setFlagMoneyStorage(true);
		} else if (moneyTransactionFormVO.getFrom().getTitle().contains("Credit Card - ")) {
			moneyTransaction.setFlagCreditCard(true);
		} else if (moneyTransactionFormVO.getFrom().getTitle().contains("Saving - ")) {
			moneyTransaction.setFlagSaving(true);
		}

	}

	private void afterSave(MoneyTransaction moneyTransaction, MoneyTransactionFormVO moneyTransactionFormVO,
			HttpServletRequest request) {
		if (moneyTransactionFormVO.getFrom().getTitle().contains("Money Storage - ")) {
			MoneyStorage moneyStorage = moneyStorageService.findMoneyStorageById(moneyTransaction.getFromId());
			if (MoneyTransactionTypeEnum.IN.equals(moneyTransactionFormVO.getType())) {
				moneyStorage.setTotalAmount(moneyStorage.getTotalAmount().add(moneyTransactionFormVO.getAmount()));
			} else if (MoneyTransactionTypeEnum.OUT.equals(moneyTransactionFormVO.getType())) {
				moneyStorage.setTotalAmount(moneyStorage.getTotalAmount().subtract(moneyTransactionFormVO.getAmount()));
			}
			baseManager.setBaseEntityAttributes(moneyStorage, request);
			moneyStorageService.saveNewMoneyStorage(moneyStorage);
		} else if (moneyTransactionFormVO.getFrom().getTitle().contains("Credit Card - ")) {
			CreditCard creditCard = creditCardService.findCreditCardById(moneyTransaction.getFromId());
			if (MoneyTransactionTypeEnum.IN.equals(moneyTransactionFormVO.getType())) {
				creditCard.setCreditAmount(creditCard.getCreditAmount().add(moneyTransactionFormVO.getAmount()));
			} else if (MoneyTransactionTypeEnum.PAY.equals(moneyTransactionFormVO.getType())) {
				creditCard.setCreditAmount(creditCard.getCreditAmount().subtract(moneyTransactionFormVO.getAmount()));
			}
			baseManager.setBaseEntityAttributes(creditCard, request);
			creditCardService.saveCreditCard(creditCard);
		} else if (moneyTransactionFormVO.getFrom().getTitle().contains("Saving - ")) {
			Saving saving = savingService.findSavingById(moneyTransaction.getFromId());
			if (MoneyTransactionTypeEnum.IN.equals(moneyTransactionFormVO.getType())) {
				saving.setCurrentAmount(saving.getCurrentAmount().add(moneyTransactionFormVO.getAmount()));
			} else if (MoneyTransactionTypeEnum.OUT.equals(moneyTransactionFormVO.getType())) {
				saving.setCurrentAmount(saving.getCurrentAmount().subtract(moneyTransactionFormVO.getAmount()));
			}
			baseManager.setBaseEntityAttributes(saving, request);
			savingService.saveSaving(saving);
		}
	}

}
