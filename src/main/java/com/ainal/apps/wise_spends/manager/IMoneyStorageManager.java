package com.ainal.apps.wise_spends.manager;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.view.object.MoneyStorageVO;
import com.ainal.apps.wise_spends.view.object.SelectItemVO;
import com.ainal.apps.wise_spends.view.object.form.MoneyStorageFormVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

public interface IMoneyStorageManager {
	List<MoneyStorageVO> populateMoneyStorageVOList(@NonNull HttpServletRequest request);

	List<SelectItemVO> populateMoneyStorageSavingCreditCardSelectItemVOList(@NonNull HttpServletRequest request);

	MoneyStorage addNewMoneyStorageForCurrentUser(@NonNull HttpServletRequest request,
			MoneyStorageFormVO moneyStorageFormVO);

	MoneyStorage updateMoneyStorageForCurrentUser(@NonNull HttpServletRequest request,
			MoneyStorageFormVO moneyStorageFormVO);

	boolean deleteMoneyStorage(@NonNull Long moneyStorageId);

	MoneyStorage getMoneyStorageById(@NonNull Long moneyStorageId);
}
