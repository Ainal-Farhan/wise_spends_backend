package com.ainal.apps.wise_spends.common.repository.mny;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.repository.IBaseEntityRepository;
import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface IMoneyStorageRepository extends IBaseEntityRepository<MoneyStorage> {
	List<MoneyStorage> findMoneyStorageByUser(User user);
}
