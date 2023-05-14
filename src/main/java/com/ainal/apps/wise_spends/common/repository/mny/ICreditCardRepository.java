package com.ainal.apps.wise_spends.common.repository.mny;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ainal.apps.wise_spends.common.domain.mny.CreditCard;
import com.ainal.apps.wise_spends.common.repository.BaseEntityRepository;
import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;
import com.ainal.apps.wise_spends.common.domain.usr.User;

public interface ICreditCardRepository extends BaseEntityRepository<CreditCard> {
	List<CreditCard> findByMoneyStorage(MoneyStorage moneyStorage);

	@Query("SELECT cc FROM CreditCard cc WHERE cc.moneyStorage IN (SELECT ms FROM MoneyStorage ms WHERE ms.user = :user)")
	List<CreditCard> findByUser(@Param("user") User user);
}
