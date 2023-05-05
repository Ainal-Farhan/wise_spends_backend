package com.ainal.apps.wise_spends.common.repository.mny;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ainal.apps.wise_spends.common.domain.mny.MoneyStorage;

public interface IMoneyStorageRepository extends JpaRepository<MoneyStorage, Long> {

}
