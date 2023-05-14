package com.ainal.apps.wise_spends.common.repository.ref;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionReference;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.BaseEntityRepository;

public interface IMoneyTransactionReferenceRepository extends BaseEntityRepository<MoneyTransactionReference> {

	@Query("select mtr from MoneyTransactionReference mtr where mtr.moneyTransactionCategoryReference in (select mtcr from MoneyTransactionCategoryReference mtcr where mtcr.user = :user)")
	List<MoneyTransactionReference> findByUser(@Param("user") User user);
}
