package com.ainal.apps.wise_spends.manager.impl;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;
import com.ainal.apps.wise_spends.common.domain.mny.Saving;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.reference.MoneyTransactionTypeEnum;
import com.ainal.apps.wise_spends.common.reference.YesNoEnum;
import com.ainal.apps.wise_spends.common.service.mny.ICreditCardService;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyStorageService;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyTransactionService;
import com.ainal.apps.wise_spends.common.service.mny.ISavingService;
import com.ainal.apps.wise_spends.common.service.ref.IMoneyTransactionReferenceService;
import com.ainal.apps.wise_spends.constant.MoneyTransactionConstant;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.manager.IMoneyTransactionManager;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionVO;
import com.ainal.apps.wise_spends.view.object.form.MoneyTransactionFormVO;

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

		return moneyTransactionList.stream().map(mt -> {
			String fromString = Strings.EMPTY;

			if (mt.getFlagSaving()) {
				fromString = MoneyTransactionConstant.SAVING_PREFIX;
				Saving saving = savingService.findSavingById(mt.getFromId());
				fromString += saving == null || StringUtils.isBlank(saving.getShortName()) ? Strings.EMPTY
						: saving.getShortName();
			} else if (mt.getFlagCreditCard()) {
				fromString = MoneyTransactionConstant.CREDIT_CARD_PREFIX;
				CreditCard creditCard = creditCardService.findCreditCardById(mt.getFromId());
				fromString += creditCard == null || StringUtils.isBlank(creditCard.getShortName()) ? Strings.EMPTY
						: creditCard.getShortName();
			} else if (mt.getFlagMoneyStorage()) {
				fromString = MoneyTransactionConstant.MONEY_STORAGE_PREFIX;
				MoneyStorage moneyStorage = moneyStorageService.findMoneyStorageById(mt.getFromId());
				fromString += moneyStorage == null || StringUtils.isBlank(moneyStorage.getFullName()) ? Strings.EMPTY
						: moneyStorage.getFullName();
			}

			return new MoneyTransactionVO(mt, fromString);
		}).collect(Collectors.toList());
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
		afterSave(moneyTransaction, request, moneyTransactionFormVO);
		return moneyTransaction;
	}

	@Override
	public MoneyTransaction updateMoneyTransactionForCurrentUser(HttpServletRequest request,
			MoneyTransactionFormVO moneyTransactionFormVO) {
		MoneyTransaction moneyTransaction = getMoneyTransactionById(moneyTransactionFormVO.getId());
		setMoneyTransactionData(moneyTransaction, moneyTransactionFormVO, request);
		moneyTransaction = moneyTransactionService.save(moneyTransaction);
		afterSave(moneyTransaction, request, moneyTransactionFormVO);
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
		moneyTransaction.setFromId(Long.parseLong(moneyTransactionFormVO.getSource().getValue().toString()));

		moneyTransaction.setFlagCreditCard(false);
		moneyTransaction.setFlagMoneyStorage(false);
		moneyTransaction.setFlagSaving(false);

		if (moneyTransactionFormVO.getSource().getTitle().contains(MoneyTransactionConstant.MONEY_STORAGE_PREFIX)) {
			moneyTransaction.setFlagMoneyStorage(true);
		} else if (moneyTransactionFormVO.getSource().getTitle()
				.contains(MoneyTransactionConstant.CREDIT_CARD_PREFIX)) {
			moneyTransaction.setFlagCreditCard(true);
		} else if (moneyTransactionFormVO.getSource().getTitle().contains(MoneyTransactionConstant.SAVING_PREFIX)) {
			moneyTransaction.setFlagSaving(true);
		}

	}

	private void afterSave(MoneyTransaction moneyTransaction, HttpServletRequest request,
			MoneyTransactionFormVO moneyTransactionFormVO) {
		BigDecimal amountSource = BigDecimal.ZERO;
		BigDecimal amountTarget = BigDecimal.ZERO;

		if (MoneyTransactionTypeEnum.IN.equals(moneyTransaction.getType())) {
			amountSource = amountSource.add(moneyTransactionFormVO.getAmount());
		} else if (MoneyTransactionTypeEnum.OUT.equals(moneyTransaction.getType())
				|| MoneyTransactionTypeEnum.PAY.equals(moneyTransaction.getType())) {
			amountSource = amountSource.subtract(moneyTransactionFormVO.getAmount());
		}

		if (MoneyTransactionTypeEnum.IN.equals(moneyTransactionFormVO.getTypeTarget())) {
			amountTarget = amountTarget.add(moneyTransactionFormVO.getAmount());
		} else if (MoneyTransactionTypeEnum.OUT.equals(moneyTransactionFormVO.getTypeTarget())
				|| MoneyTransactionTypeEnum.PAY.equals(moneyTransactionFormVO.getTypeTarget())) {
			amountTarget = amountTarget.subtract(moneyTransactionFormVO.getAmount());
		}

		if (moneyTransaction.getFlagMoneyStorage()) {
			MoneyStorage moneyStorage = moneyStorageService.findMoneyStorageById(moneyTransaction.getFromId());
			moneyStorage.setTotalAmount(moneyStorage.getTotalAmount().add(amountSource));

			baseManager.setBaseEntityAttributes(moneyStorage, request);
			moneyStorageService.saveNewMoneyStorage(moneyStorage);
		} else if (moneyTransaction.getFlagCreditCard()) {
			CreditCard creditCard = creditCardService.findCreditCardById(moneyTransaction.getFromId());
			creditCard.setCreditAmount(creditCard.getCreditAmount().add(amountSource));

			baseManager.setBaseEntityAttributes(creditCard, request);
			creditCardService.saveCreditCard(creditCard);
		} else if (moneyTransaction.getFlagSaving()) {
			Saving saving = savingService.findSavingById(moneyTransaction.getFromId());
			saving.setCurrentAmount(saving.getCurrentAmount().add(amountSource));

			baseManager.setBaseEntityAttributes(saving, request);
			savingService.saveSaving(saving);
		}

		if (YesNoEnum.YES.equals(moneyTransactionFormVO.getIsWithinSystem())
				& moneyTransactionFormVO.getTarget() != null) {
			Long id = Long.parseLong(moneyTransactionFormVO.getTarget().getValue().toString());
			if (moneyTransactionFormVO.getTarget().getTitle().contains(MoneyTransactionConstant.MONEY_STORAGE_PREFIX)) {
				MoneyStorage moneyStorage = moneyStorageService.findMoneyStorageById(id);
				moneyStorage.setTotalAmount(moneyStorage.getTotalAmount().add(amountTarget));

				baseManager.setBaseEntityAttributes(moneyStorage, request);
				moneyStorageService.saveNewMoneyStorage(moneyStorage);
			} else if (moneyTransactionFormVO.getTarget().getTitle()
					.contains(MoneyTransactionConstant.CREDIT_CARD_PREFIX)) {
				CreditCard creditCard = creditCardService.findCreditCardById(id);
				creditCard.setCreditAmount(creditCard.getCreditAmount().add(amountTarget));

				baseManager.setBaseEntityAttributes(creditCard, request);
				creditCardService.saveCreditCard(creditCard);
			} else if (moneyTransactionFormVO.getTarget().getTitle().contains(MoneyTransactionConstant.SAVING_PREFIX)) {
				Saving saving = savingService.findSavingById(id);
				saving.setCurrentAmount(saving.getCurrentAmount().add(amountTarget));

				baseManager.setBaseEntityAttributes(saving, request);
				savingService.saveSaving(saving);
			}
		}
	}

	@Override
	public List<MoneyTransactionVO> populateMoneyTransactionVOListLast30Days(HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		List<MoneyTransaction> moneyTransactionList = moneyTransactionService
				.findByUserAndAtleastFromNumDays(currentUser, 30);

		return moneyTransactionList.stream().map(mt -> {
			String fromString = Strings.EMPTY;

			if (mt.getFlagSaving()) {
				fromString = MoneyTransactionConstant.SAVING_PREFIX;
				Saving saving = savingService.findSavingById(mt.getFromId());
				fromString += saving == null || StringUtils.isBlank(saving.getShortName()) ? Strings.EMPTY
						: saving.getShortName();
			} else if (mt.getFlagCreditCard()) {
				fromString = MoneyTransactionConstant.CREDIT_CARD_PREFIX;
				CreditCard creditCard = creditCardService.findCreditCardById(mt.getFromId());
				fromString += creditCard == null || StringUtils.isBlank(creditCard.getShortName()) ? Strings.EMPTY
						: creditCard.getShortName();
			} else if (mt.getFlagMoneyStorage()) {
				fromString = MoneyTransactionConstant.MONEY_STORAGE_PREFIX;
				MoneyStorage moneyStorage = moneyStorageService.findMoneyStorageById(mt.getFromId());
				fromString += moneyStorage == null || StringUtils.isBlank(moneyStorage.getFullName()) ? Strings.EMPTY
						: moneyStorage.getFullName();
			}

			return new MoneyTransactionVO(mt, fromString);
		}).collect(Collectors.toList());
	}

}
