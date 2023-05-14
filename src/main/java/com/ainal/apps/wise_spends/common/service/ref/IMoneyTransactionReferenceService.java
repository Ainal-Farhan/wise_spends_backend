package com.ainal.apps.wise_spends.common.service.ref;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionReference;
import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IMoneyTransactionReferenceService {
	List<MoneyTransactionReference> findMoneyTransactionReferenceListByUser(User user);

	MoneyTransactionReference findMoneyTransactionReferenceById(Long moneyTransactionReferenceId);

	MoneyTransactionReference saveMoneyTransactionReference(MoneyTransactionReference moneyTransactionReference);

	void deleteMoneyTransactionReferenceById(Long moneyTransactionReferenceId);
}
