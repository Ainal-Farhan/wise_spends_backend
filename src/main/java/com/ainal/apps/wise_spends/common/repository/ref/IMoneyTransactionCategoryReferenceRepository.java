package com.ainal.apps.wise_spends.common.repository.ref;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ainal.apps.wise_spends.common.domain.ref.MoneyTransactionCategoryReference;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.BaseEntityRepository;

public interface IMoneyTransactionCategoryReferenceRepository
		extends BaseEntityRepository<MoneyTransactionCategoryReference> {

	@Query("select mtcr from MoneyTransactionCategoryReference mtcr where mtcr.user = :user")
	List<MoneyTransactionCategoryReference> findByUser(@Param("user") User user);
}
