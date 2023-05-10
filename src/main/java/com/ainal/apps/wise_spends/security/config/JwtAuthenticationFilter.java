package com.ainal.apps.wise_spends.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.security.config.service.IJwtService;
import com.ainal.apps.wise_spends.security.config.service.IUserJwtViewObjectService;
import com.ainal.apps.wise_spends.security.config.vo.UserJwtViewObject;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private IJwtService jwtService;

	@Autowired
	private IUserJwtViewObjectService userJwtViewObjectService;

	@Autowired
	private ICurrentUserManager currentUserManager;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		String jwt = currentUserManager.retrieveToken(request);

		if (StringUtils.isBlank(jwt)) {
			filterChain.doFilter(request, response);
			return;
		}

		final String usernameOrEmail = jwtService.extractUsernameOrEmail(jwt);

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
