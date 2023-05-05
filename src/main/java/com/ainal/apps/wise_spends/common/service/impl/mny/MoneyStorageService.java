package com.ainal.apps.wise_spends.common.service.impl.mny;

import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.mny.IMoneyStorageService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MoneyStorageService extends BaseService implements IMoneyStorageService{

}
