package com.ainal.apps.wise_spends.manager;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionReference;
import com.ainal.apps.wise_spends.form.view.object.MoneyTransactionReferenceFormVO;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionReferenceVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

public interface IMoneyTransactionReferenceManager {
	List<MoneyTransactionReferenceVO> populateMoneyTransactionReferenceVOList(@NonNull HttpServletRequest request);

	MoneyTransactionReference getMoneyTransactionReferenceById(@NonNull Long moneyTransactionReferenceId);

	MoneyTransactionReference addNewMoneyTransactionReferenceForCurrentUser(@NonNull HttpServletRequest request,
			MoneyTransactionReferenceFormVO moneyTransactionReferenceFormVO);

	MoneyTransactionReference updateMoneyTransactionReferenceForCurrentUser(@NonNull HttpServletRequest request,
			MoneyTransactionReferenceFormVO moneyTransactionReferenceFormVO);

	boolean deleteMoneyTransactionReferenceById(@NonNull Long moneyTransactionReferenceId);

}
