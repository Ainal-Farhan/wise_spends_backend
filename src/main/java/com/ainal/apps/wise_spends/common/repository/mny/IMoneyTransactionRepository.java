package com.ainal.apps.wise_spends.common.repository.mny;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyTransaction;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.IBaseEntityRepository;

public interface IMoneyTransactionRepository extends IBaseEntityRepository<MoneyTransaction> {

	@Query("select mt from MoneyTransaction mt where mt.user = :user order by mt.transactionDate desc")
	List<MoneyTransaction> findByUser(@Param("user") User user);

	@Query("select mt from MoneyTransaction mt where mt.user = :user and mt.transactionDate >= :startDate order by mt.transactionDate desc")
	List<MoneyTransaction> findByUserAndStartDate(@Param("user") User user, @Param("startDate") Date startDate);
}
