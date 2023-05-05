package com.ainal.apps.wise_spends.util.logging;

import java.io.Serializable;

import jakarta.transaction.SystemException;

public class GenericLogger implements Serializable {
	private static final long serialVersionUID = 1L;
	private transient org.slf4j.Logger logger;

	public GenericLogger() throws SystemException {
		super();
		StackTraceElement myCaller = Thread.currentThread().getStackTrace()[2];
		String className = myCaller.getClassName();
		try {
			Class<?> clazz = Class.forName(className);
			this.logger = org.slf4j.LoggerFactory.getLogger(clazz);
		} catch (ClassNotFoundException e) {
			throw new SystemException(e.getMessage());
		}
	}

	public GenericLogger(Class<?> clazz) {
		super();
		this.logger = org.slf4j.LoggerFactory.getLogger(clazz);
	}

	public void error(String message, Exception e) {
		logger.error(message, e);
	}

	public void error(String message) {
		this.logger.error(message);
	}

	public void error(String message, Object... args) {
		this.logger.error(message, args);
	}

	public void error(Exception e) {
		String message = e.getMessage();
		this.logger.error(message, e);
	}

	public void warn(String message) {
		logger.warn(message);
	}

	public void warn(String message, Object... args) {
		logger.warn(message, args);
	}

	public void warn(Exception e) {
		logger.warn(e.getMessage(), e);
	}

	public void info(String message) {
		logger.info(message);
	}

	public void info(String message, Object... args) {
		org.slf4j.Logger logger = this.logger;
		logger.info(message, args);
	}

	public void info(Exception e) {
		org.slf4j.Logger logger = this.logger;
		String message = e.getMessage();
		logger.info(message, e);
	}

	public void debug(String message) {
		org.slf4j.Logger logger = this.logger;
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	public void debug(String message, Object... args) {
		org.slf4j.Logger logger = this.logger;
		if (logger.isDebugEnabled()) {
			logger.debug(message, args);
		}
	}

	public void trace(String message) {
		if (logger.isTraceEnabled()) {
			logger.trace(message);
		}
	}

	public void trace(String message, Object... args) {
		if (logger.isTraceEnabled()) {
			logger.trace(message, args);
		}
	}

	public void trace(Exception e) {
		if (logger.isTraceEnabled()) {
			logger.trace(e.getMessage(), e);
		}
	}

	public void trace(Throwable e) {
		if (logger.isTraceEnabled()) {
			logger.trace(e.getMessage(), e);
		}
	}
}
