package com.ainal.apps.wise_spends.common.repository.bgt;

import java.util.List;

import com.ainal.apps.wise_spends.common.domain.budget.Budget;
import com.ainal.apps.wise_spends.common.domain.usr.User;
import com.ainal.apps.wise_spends.common.repository.IBaseEntityRepository;

public interface IBudgetRepository extends IBaseEntityRepository<Budget> {
	List<Budget> findByUser(User user);
}
