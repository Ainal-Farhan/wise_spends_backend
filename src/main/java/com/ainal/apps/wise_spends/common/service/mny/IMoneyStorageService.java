package com.ainal.apps.wise_spends.common.service.mny;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IMoneyStorageService {
	List<MoneyStorage> findMoneyStorageByUser(User user);

	MoneyStorage findMoneyStorageById(Long moneyStorageId);

	MoneyStorage saveNewMoneyStorage(MoneyStorage moneyStorage);

	void deleteMoneyStorage(Long moneyStorageId);
}
