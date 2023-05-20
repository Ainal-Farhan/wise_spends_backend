package com.ainal.apps.wise_spends.common.repository;

import org.springframework.data.repository.NoRepositoryBean;

import com.ainal.apps.wise_spends.common.domain.base.BaseEntity;

@NoRepositoryBean
public interface IBaseEntityRepository<E extends BaseEntity>
		extends ICustomRepository<E, Long> {

}

