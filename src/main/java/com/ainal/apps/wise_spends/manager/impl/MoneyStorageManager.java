package com.ainal.apps.wise_spends.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyStorageService;
import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.manager.IMoneyStorageManager;
import com.ainal.apps.wise_spends.view.object.MoneyStorageVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class MoneyStorageManager implements IMoneyStorageManager {
	@Autowired
	ICurrentUserManager currentUserManager;

	@Autowired
	IMoneyStorageService moneyStorageService;

	@Override
	public List<MoneyStorageVO> populateMoneyStorageVOList(@NonNull HttpServletRequest request) {
		User currentUser = currentUserManager.getCurrentUser(request);
		List<MoneyStorage> moneyStorageList = moneyStorageService.findMoneyStorageByUser(currentUser);

		if (CollectionUtils.isEmpty(moneyStorageList)) {
			return new ArrayList<>();
		}

		List<MoneyStorageVO> moneyStorageVOList = new ArrayList<>();
		for (MoneyStorage moneyStorage : moneyStorageList) {
			final MoneyStorageVO moneyStorageVO = new MoneyStorageVO();
			moneyStorageVO.setName(moneyStorage.getFullName());
			moneyStorage.setTotalAmount(moneyStorage.getTotalAmount());
		}
		return moneyStorageVOList;
	}

}
