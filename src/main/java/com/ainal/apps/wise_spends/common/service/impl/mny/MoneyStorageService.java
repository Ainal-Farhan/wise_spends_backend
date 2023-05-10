package com.ainal.apps.wise_spends.common.service.impl.mny;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.mny.IMoneyStorageRepository;
import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyStorageService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MoneyStorageService extends BaseService implements IMoneyStorageService {
	@Autowired
	IMoneyStorageRepository moneyStorageRepository;

	@Override
	public List<MoneyStorage> findMoneyStorageByUser(User user) {
		List<MoneyStorage> moneyStorageList = moneyStorageRepository.findMoneyStorageByUser(user);
		return moneyStorageList == null ? new ArrayList<>() : moneyStorageList;
	}

}
