package com.ainal.apps.wise_spends.util.properties;

import org.springframework.stereotype.Component;

@Component
public class WiseSpendsPropertiesUtils {
	public static final String JWT_SECRET_KEY;
	public static final String WS_DOMAIN;
	public static final String JWT_COOKIE_ACCESS_TOKEN_NAME;

	static {
		JWT_SECRET_KEY = System.getProperty("ws.jwt_secret");
		WS_DOMAIN = System.getProperty("ws.domain");
		JWT_COOKIE_ACCESS_TOKEN_NAME = System.getProperty("ws.jwt.token.name");
	}
}
