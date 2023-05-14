package com.ainal.apps.wise_spends.manager;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionCategoryReference;
import com.ainal.apps.wise_spends.form.view.object.MoneyTransactionCategoryReferenceFormVO;
import com.ainal.apps.wise_spends.view.object.MoneyTransactionCategoryReferenceVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

public interface IMoneyTransactionCategoryReferenceManager {
	List<MoneyTransactionCategoryReferenceVO> populateMoneyTransactionCategoryReferenceVOList(
			@NonNull HttpServletRequest request);

	MoneyTransactionCategoryReference getMoneyTransactionCategoryReferenceById(
			@NonNull Long moneyTransactionReferenceId);

	MoneyTransactionCategoryReference addNewMoneyTransactionCategoryReferenceForCurrentUser(
			@NonNull HttpServletRequest request,
			MoneyTransactionCategoryReferenceFormVO moneyTransactionCategoryReferenceFormVO);

	MoneyTransactionCategoryReference updateMoneyTransactionCategoryReferenceForCurrentUser(
			@NonNull HttpServletRequest request,
			MoneyTransactionCategoryReferenceFormVO moneyTransactionCategoryReferenceFormVO);

	boolean deleteMoneyTransactionCategoryReferenceById(@NonNull Long moneyTransactionCategoryReferenceId);

}
