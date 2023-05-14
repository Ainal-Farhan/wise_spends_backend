package com.ainal.apps.wise_spends.common.service.mny;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.mny.Saving;
import com.ainal.apps.wise_spends.common.domain.usr.User;

import io.micrometer.common.lang.NonNull;

public interface ISavingService {
	List<Saving> findSavingListByMoneyStorage(@NonNull MoneyStorage moneyStorage);

	List<Saving> findSavingListByUser(@NonNull User user);

	Saving findSavingById(Long savingId);

	Saving saveSaving(Saving saving);

	void deleteSavingById(Long savingId);

}
