package com.ainal.apps.wise_spends.common.service.usr.impl;

import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.common.service.BaseService;
import com.ainal.apps.wise_spends.common.service.usr.ICredentialService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CredentialService extends BaseService implements ICredentialService {

}
