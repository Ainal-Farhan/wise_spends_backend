package com.ainal.apps.wise_spends.common.service.mny.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.mny.Saving;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.mny.ISavingRepository;
import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.mny.ISavingService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SavingService extends BaseService implements ISavingService {
	@Autowired
	ISavingRepository savingRepository;

	@Override
	public List<Saving> findSavingListByMoneyStorage(MoneyStorage moneyStorage) {
		return savingRepository.findByMoneyStorage(moneyStorage);
	}

	@Override
	public List<Saving> findSavingListByUser(User user) {
		return savingRepository.findByUser(user);
	}

	@Override
	public Saving findSavingById(Long savingId) {
		return savingRepository.findById(savingId).orElse(null);
	}

	@Override
	public Saving saveSaving(Saving saving) {
		return savingRepository.save(saving);
	}

	@Override
	public void deleteSavingById(Long savingId) {
		savingRepository.deleteById(savingId);
	}

}
