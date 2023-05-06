package com.ainal.apps.wise_spends.security.config.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.ainal.apps.wise_spends.security.config.service.IJwtService;
import com.ainal.apps.wise_spends.security.config.vo.UserJwtViewObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;

@Service
public class JwtService implements IJwtService {
	private static final String SECRET_KEY = "24432646294A404E635266556A586E3272357538782F413F442A472D4B615064";

	@Override
	public String extractUsernameOrEmail(String token) {
		if (StringUtils.isBlank(token)) {
			return Strings.EMPTY;
		}
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	@Override
	public String generateToken(Map<String, Object> extraClaims, UserJwtViewObject userJwtViewObject) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userJwtViewObject.getUsernameOrEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	@Override
	public String generateToken(UserJwtViewObject userJwtViewObject) {
		return generateToken(new HashMap<>(), userJwtViewObject);
	}

	@Override
	public boolean isTokenValid(String token, UserJwtViewObject userJwtViewObject) {
		final String usernameOrEmail = extractUsernameOrEmail(token);
		return usernameOrEmail.equals(userJwtViewObject.getUsernameOrEmail()) && !isTokenExpired(token);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

}
