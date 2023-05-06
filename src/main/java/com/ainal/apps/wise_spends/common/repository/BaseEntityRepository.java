package com.ainal.apps.wise_spends.common.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;

@NoRepositoryBean
public interface BaseEntityRepository<E extends BaseEntity>
		extends CustomRepository<E, Long> {

}

