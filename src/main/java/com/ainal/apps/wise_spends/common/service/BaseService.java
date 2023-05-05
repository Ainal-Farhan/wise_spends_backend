package com.ainal.apps.wise_spends.common.service;

import com.ainal.apps.wise_spends.util.logging.GenericLogger;

public abstract class BaseService {
	protected final GenericLogger LOGGER;
	
	public BaseService() {
        super();
        this.LOGGER = new GenericLogger(this.getClass());
    }
}
