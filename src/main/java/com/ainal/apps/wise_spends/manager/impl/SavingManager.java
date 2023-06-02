package com.ainal.apps.wise_spends.manager.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.mny.Saving;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.service.mny.ISavingService;
import com.ainal.apps.wise_spends.manager.IBaseManager;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.manager.IMoneyStorageManager;
import com.ainal.apps.wise_spends.manager.ISavingManager;
import com.ainal.apps.wise_spends.view.object.SavingVO;
import com.ainal.apps.wise_spends.view.object.form.SavingFormVO;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class SavingManager implements ISavingManager {
	@Autowired
	private ICurrentUserManager currentUserManager;

	@Autowired
	private IBaseManager baseManager;

	@Autowired
	private IMoneyStorageManager moneyStorageManager;

	@Autowired
	private ISavingService savingService;

	@Override
	public List<SavingVO> populateSavingVOList(HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		List<Saving> savingList = savingService.findSavingListByUser(currentUser);

		if (CollectionUtils.isEmpty(savingList)) {
			return new ArrayList<>();
		}

		return savingList.stream().map(saving -> new SavingVO(saving)).collect(Collectors.toList());
	}

	@Override
	public Saving getSavingById(Long savingId) {
		return savingService.findSavingById(savingId);
	}

	@Override
	public Saving addNewSavingForCurrentUser(HttpServletRequest request, SavingFormVO savingFormVO) {
		if (savingFormVO == null) {
			return null;
		}

		Saving saving = new Saving();
		setSavingData(saving, savingFormVO, request);

		return savingService.saveSaving(saving);
	}

	@Override
	public Saving updateSavingForCurrentUser(HttpServletRequest request, SavingFormVO savingFormVO) {
		if (savingFormVO == null) {
			return null;
		}

		Saving saving = savingService.findSavingById(savingFormVO.getId());
		setSavingData(saving, savingFormVO, request);

		return savingService.saveSaving(saving);
	}

	@Override
	public boolean deleteSavingById(Long savingId) {
		savingService.deleteSavingById(savingId);
		return true;
	}

	private void setSavingData(Saving saving, SavingFormVO savingFormVO, HttpServletRequest request) {
		if (savingFormVO == null) {
			return;
		}

		baseManager.setBaseEntityAttributes(saving, request);
		if (savingFormVO.getMoneyStorageId() != null) {
			MoneyStorage moneyStorage = moneyStorageManager.getMoneyStorageById(savingFormVO.getMoneyStorageId());
			saving.setMoneyStorage(moneyStorage);
		}

		saving.setShortName(savingFormVO.getShortName());
		saving.setFullName(savingFormVO.getFullName());
		saving.setAbbreviation(savingFormVO.getAbbreviation());
		saving.setCurrentAmount(
				(savingFormVO.getCurrentAmount() == null ? BigDecimal.valueOf(0) : savingFormVO.getCurrentAmount())
						.setScale(2, RoundingMode.HALF_UP));
	}

}
