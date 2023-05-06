package com.ainal.apps.wise_spends.util.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class WiseSpendsPropertiesUtils {
	@Autowired
	private Environment env;

	public String JWT_SECRET_KEY() {
		return env.getProperty("ws.jwt_secret");
	}

	public String WS_DOMAIN() {
		return env.getProperty("ws.domain");

	}

	public String JWT_COOKIE_ACCESS_TOKEN_NAME() {
		return env.getProperty("ws.jwt.token.name");

	}
}
