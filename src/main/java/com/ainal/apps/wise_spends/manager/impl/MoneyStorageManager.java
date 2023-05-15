package com.ainal.apps.wise_spends.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.mny.Saving;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.reference.MoneyStorageTypeEnum;
import com.ainal.apps.wise_spends.common.service.mny.ICreditCardService;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyStorageService;
import com.ainal.apps.wise_spends.common.service.mny.ISavingService;
import com.ainal.apps.wise_spends.constant.MoneyTransactionConstant;
import com.ainal.apps.wise_spends.form.view.object.MoneyStorageFormVO;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.manager.IMoneyStorageManager;
import com.ainal.apps.wise_spends.view.object.CreditCardVO;
import com.ainal.apps.wise_spends.view.object.MoneyStorageVO;
import com.ainal.apps.wise_spends.view.object.SavingVO;
import com.ainal.apps.wise_spends.view.object.SelectItemVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class MoneyStorageManager implements IMoneyStorageManager {
	@Autowired
	private ICurrentUserManager currentUserManager;

	@Autowired
	private IBaseManager baseManager;

	@Autowired
	private IMoneyStorageService moneyStorageService;

	@Autowired
	private ICreditCardService creditCardService;

	@Autowired
	private ISavingService savingService;

	@Override
	public List<MoneyStorageVO> populateMoneyStorageVOList(@NonNull HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		List<MoneyStorage> moneyStorageList = moneyStorageService.findMoneyStorageByUser(currentUser);

		if (CollectionUtils.isEmpty(moneyStorageList)) {
			return new ArrayList<>();
		}

		List<MoneyStorageVO> moneyStorageVOList = new ArrayList<>();
		for (MoneyStorage moneyStorage : moneyStorageList) {
			moneyStorageVOList.add(new MoneyStorageVO(moneyStorage));
		}
		return moneyStorageVOList;
	}

	@Override
	public MoneyStorage addNewMoneyStorageForCurrentUser(@NonNull HttpServletRequest request,
			MoneyStorageFormVO moneyStorageFormVO) {
		if (moneyStorageFormVO == null) {
			return null;
		}

		User currentUser = currentUserManager.getCurrentUser(request);
		MoneyStorage moneyStorage = new MoneyStorage();
		setMoneyStorageData(moneyStorageFormVO, moneyStorage);
		moneyStorage.setUser(currentUser);
		baseManager.setBaseEntityAttributes(moneyStorage, request);

		moneyStorage = moneyStorageService.saveNewMoneyStorage(moneyStorage);

		return moneyStorage;
	}

	@Override
	public boolean deleteMoneyStorage(@NonNull Long moneyStorageId) {
		moneyStorageService.deleteMoneyStorage(moneyStorageId);
		return true;
	}

	@Override
	public MoneyStorage updateMoneyStorageForCurrentUser(HttpServletRequest request,
			MoneyStorageFormVO moneyStorageFormVO) {
		MoneyStorage moneyStorage = getMoneyStorageById(moneyStorageFormVO.getId());
		setMoneyStorageData(moneyStorageFormVO, moneyStorage);
		baseManager.setBaseEntityAttributes(moneyStorage, request);
		moneyStorage = moneyStorageService.saveNewMoneyStorage(moneyStorage);

		return moneyStorage;
	}

	@Override
	public MoneyStorage getMoneyStorageById(Long moneyStorageId) {
		return moneyStorageService.findMoneyStorageById(moneyStorageId);
	}

	private void setMoneyStorageData(MoneyStorageFormVO moneyStorageFormVO, MoneyStorage moneyStorage) {
		moneyStorage.setFullName(moneyStorageFormVO.getFullName());
		moneyStorage.setAbbreviation(moneyStorageFormVO.getAbbreviation());
		moneyStorage.setType(
				moneyStorageFormVO.getType() == null ? MoneyStorageTypeEnum.SAVING : moneyStorageFormVO.getType());
		moneyStorage.setTotalAmount(moneyStorageFormVO.getTotalAmount() == null ? BigDecimal.valueOf(0)
				: moneyStorageFormVO.getTotalAmount());
	}

	@Override
	public List<SelectItemVO> populateMoneyStorageSavingCreditCardSelectItemVOList(HttpServletRequest request) {
		List<MoneyStorageVO> moneyStorageVOList = populateMoneyStorageVOList(request);
		List<CreditCardVO> creditCardVOList = populateCreditCardVOList(request);
		List<SavingVO> savingVOList = populateSavingVOList(request);

		List<SelectItemVO> selectItemVOList = moneyStorageVOList.stream()
				.map(ms -> new SelectItemVO(MoneyTransactionConstant.MONEY_STORAGE_PREFIX + ms.getName(), ms.getId()))
				.collect(Collectors.toList());

		selectItemVOList.addAll(creditCardVOList.stream().map(
				cc -> new SelectItemVO(MoneyTransactionConstant.CREDIT_CARD_PREFIX + cc.getShortName(), cc.getId()))
				.collect(Collectors.toList()));

		selectItemVOList.addAll(savingVOList.stream()
				.map(s -> new SelectItemVO(MoneyTransactionConstant.SAVING_PREFIX + s.getShortName(), s.getId()))
				.collect(Collectors.toList()));

		return selectItemVOList;
	}

	private List<CreditCardVO> populateCreditCardVOList(HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		List<CreditCard> cardCreditList = creditCardService.findCreditCardListByUser(currentUser);

		if (CollectionUtils.isEmpty(cardCreditList)) {
			return new ArrayList<>();
		}

		return cardCreditList.stream().map(cardCredit -> new CreditCardVO(cardCredit)).collect(Collectors.toList());

	}

	private List<SavingVO> populateSavingVOList(HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		List<Saving> savingList = savingService.findSavingListByUser(currentUser);

		if (CollectionUtils.isEmpty(savingList)) {
			return new ArrayList<>();
		}

		return savingList.stream().map(saving -> new SavingVO(saving)).collect(Collectors.toList());
	}

}
