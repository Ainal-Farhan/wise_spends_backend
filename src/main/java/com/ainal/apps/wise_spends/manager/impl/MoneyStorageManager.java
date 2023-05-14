package com.ainal.apps.wise_spends.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.reference.MoneyStorageTypeEnum;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyStorageService;
import com.ainal.apps.wise_spends.form.view.object.MoneyStorageFormVO;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.manager.IMoneyStorageManager;
import com.ainal.apps.wise_spends.view.object.MoneyStorageVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class MoneyStorageManager implements IMoneyStorageManager {
	@Autowired
	private ICurrentUserManager currentUserManager;

	@Autowired
	private IMoneyStorageService moneyStorageService;

	@Autowired
	private IBaseManager baseManager;

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

}
