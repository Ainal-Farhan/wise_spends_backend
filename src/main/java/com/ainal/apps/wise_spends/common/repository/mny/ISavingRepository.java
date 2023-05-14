package com.ainal.apps.wise_spends.common.repository.mny;

import com.ainal.apps.wise_spends.common.domain.mny.Saving;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.BaseEntityRepository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;

public interface ISavingRepository extends BaseEntityRepository<Saving> {
	List<Saving> findByMoneyStorage(MoneyStorage moneyStorage);

	@Query("SELECT s FROM Saving s WHERE s.moneyStorage IN (SELECT ms FROM MoneyStorage ms WHERE ms.user = :user)")
	List<Saving> findByUser(@Param("user") User user);
}
