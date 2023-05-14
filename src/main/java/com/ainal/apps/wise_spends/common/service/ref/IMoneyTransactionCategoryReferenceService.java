package com.ainal.apps.wise_spends.common.service.ref;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionCategoryReference;
import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IMoneyTransactionCategoryReferenceService {
	List<MoneyTransactionCategoryReference> findByUser(User user);

	MoneyTransactionCategoryReference findById(Long id);

	MoneyTransactionCategoryReference save(MoneyTransactionCategoryReference categoryReference);

	void deleteById(Long id);
}
