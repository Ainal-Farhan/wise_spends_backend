package com.ainal.apps.wise_spends.common.service.mny;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;
import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IMoneyTransactionService {
	List<MoneyTransaction> findByUser(User user);

	MoneyTransaction findById(Long id);

	MoneyTransaction save(MoneyTransaction categoryReference);

	void deleteById(Long id);

}
