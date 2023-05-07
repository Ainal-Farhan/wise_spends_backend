package com.ainal.apps.wise_spends.security.config;

import java.io.IOException;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ainal.apps.wise_spends.security.config.service.IJwtService;
import com.ainal.apps.wise_spends.security.config.service.IUserJwtViewObjectService;
import com.ainal.apps.wise_spends.security.config.vo.UserJwtViewObject;
import com.ainal.apps.wise_spends.util.properties.ThymeleafPropertiesUtils;
import com.ainal.apps.wise_spends.util.properties.WiseSpendsPropertiesUtils;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private IJwtService jwtService;

	@Autowired
	private IUserJwtViewObjectService userJwtViewObjectService;

	@Autowired
	private WiseSpendsPropertiesUtils wiseSpendsPropertiesUtils;

	@Autowired
	private ThymeleafPropertiesUtils thymeleafPropertiesUtils;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String usernameOrEmail;
		final String startWith = "Bearer ";
		String jwt;

		thymeleafPropertiesUtils.getHeaderFragment();

		// Retrieve the token from the cookie
		Cookie[] cookies = request.getCookies();
		String token = Strings.EMPTY;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (wiseSpendsPropertiesUtils.JWT_COOKIE_ACCESS_TOKEN_NAME().equals(cookie.getName())) {
					token = cookie.getValue();
					break;
				}
			}
		}

		if (StringUtils.isBlank(token) && (StringUtils.isBlank(authHeader) || !authHeader.startsWith(startWith))) {
			filterChain.doFilter(request, response);
			return;
		}

		jwt = token;
		if (StringUtils.isBlank(token)) {
			jwt = authHeader.substring(startWith.length());
		}
		usernameOrEmail = jwtService.extractUsernameOrEmail(jwt);

		if (!StringUtils.isBlank(usernameOrEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserJwtViewObject userJwtViewObject = this.userJwtViewObjectService
					.loadUserJwtViewObjectByUsernameOrEmail(usernameOrEmail).orElse(null);

			if (jwtService.isTokenValid(jwt, userJwtViewObject)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userJwtViewObject, null, userJwtViewObject.getGrantedAuthorities());

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		filterChain.doFilter(request, response);
	}

}
