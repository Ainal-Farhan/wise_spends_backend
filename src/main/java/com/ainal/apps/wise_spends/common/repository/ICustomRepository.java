package com.ainal.apps.wise_spends.common.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ICustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
