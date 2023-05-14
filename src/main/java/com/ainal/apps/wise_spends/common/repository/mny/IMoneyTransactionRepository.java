package com.ainal.apps.wise_spends.common.repository.mny;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.BaseEntityRepository;

public interface IMoneyTransactionRepository extends BaseEntityRepository<MoneyTransaction> {

	@Query("select mt from MoneyTransaction mt where mt.user = :user")
	List<MoneyTransaction> findByUser(@Param("user") User user);
}
