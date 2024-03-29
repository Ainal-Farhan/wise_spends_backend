package com.ainal.apps.wise_spends.manager;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionVO;
import com.ainal.apps.wise_spends.view.object.form.MoneyTransactionFormVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

public interface IMoneyTransactionManager {
	List<MoneyTransactionVO> populateMoneyTransactionVOList(@NonNull HttpServletRequest request);

	List<MoneyTransactionVO> populateMoneyTransactionVOListLast30Days(@NonNull HttpServletRequest request);

	MoneyTransaction getMoneyTransactionById(@NonNull Long moneyTransactionId);

	MoneyTransaction addNewMoneyTransactionForCurrentUser(@NonNull HttpServletRequest request,
			MoneyTransactionFormVO moneyTransactionFormVO);

	MoneyTransaction updateMoneyTransactionForCurrentUser(@NonNull HttpServletRequest request,
			MoneyTransactionFormVO moneyTransactionFormVO);

	boolean deleteMoneyTransactionById(@NonNull Long moneyTransactionId);

}
