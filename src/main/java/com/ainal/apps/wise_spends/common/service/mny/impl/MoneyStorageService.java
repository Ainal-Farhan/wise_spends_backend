package com.ainal.apps.wise_spends.common.service.mny.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.mny.ICreditCardRepository;
import com.ainal.apps.wise_spends.common.repository.mny.IMoneyStorageRepository;
import com.ainal.apps.wise_spends.common.repository.mny.ISavingRepository;
import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyStorageService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MoneyStorageService extends BaseService implements IMoneyStorageService {
	@Autowired
	IMoneyStorageRepository moneyStorageRepository;

	@Autowired
	ISavingRepository savingRepository;

	@Autowired
	ICreditCardRepository creditCardRepository;

	@Override
	public List<MoneyStorage> findMoneyStorageByUser(User user) {
		List<MoneyStorage> moneyStorageList = moneyStorageRepository.findMoneyStorageByUser(user);
		if (!CollectionUtils.isEmpty(moneyStorageList)) {
			for (MoneyStorage moneyStorage : moneyStorageList) {
				moneyStorage.fetchCreditCardList();
				moneyStorage.fetchSavingList();
			}
		}
		return moneyStorageList == null ? new ArrayList<>() : moneyStorageList;
	}

	@Override
	public MoneyStorage saveNewMoneyStorage(MoneyStorage moneyStorage) {
		return moneyStorageRepository.save(moneyStorage);
	}

	@Override
	public void deleteMoneyStorage(Long moneyStorageId) {
		MoneyStorage moneyStorage = moneyStorageRepository.findById(moneyStorageId).orElse(null);
		if (moneyStorage != null) {
			moneyStorage.fetchCreditCardList();
			moneyStorage.fetchSavingList();
		}

		if (!CollectionUtils.isEmpty(moneyStorage.getSavingList())) {
			savingRepository.deleteAll(moneyStorage.getSavingList());
		}

		if (!CollectionUtils.isEmpty(moneyStorage.getCreditCardList())) {
			creditCardRepository.deleteAll(moneyStorage.getCreditCardList());
		}

		moneyStorageRepository.delete(moneyStorage);
	}

	@Override
	public MoneyStorage findMoneyStorageById(Long moneyStorageId) {
		return moneyStorageRepository.findById(moneyStorageId).orElse(null);
	}

}
