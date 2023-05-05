package com.ainal.apps.wise_spends.security.config.service;

import java.util.Map;
import java.util.function.Function;

import com.ainal.apps.wise_spends.security.config.vo.UserJwtViewObject;

import io.jsonwebtoken.Claims;

public interface IJwtService {
	String extractUsernameOrEmail(String token);

	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);

	String generateToken(Map<String, Object> extraClaims, UserJwtViewObject userJwtViewObject);

	String generateToken(UserJwtViewObject userJwtViewObject);
	
	boolean isTokenValid(String token, UserJwtViewObject userJwtViewObject);
}
