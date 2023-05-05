package com.ainal.apps.wise_spends.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String usernameOrEmail;
		final String startWith = "Bearer ";

		if (StringUtils.isBlank(authHeader) || authHeader.startsWith(startWith)) {
			filterChain.doFilter(request, response);
			return;
		}

		jwt = authHeader.substring(startWith.length());
		usernameOrEmail = jwtService.extractUsernameOrEmail(jwt);

		if (!StringUtils.isBlank(usernameOrEmail) && SecurityContextHolder.getContext().getAuthentication() != null) {
			UserJwtViewObject userJwtViewObject = this.userJwtViewObjectService
					.loadUserJwtViewObjectByUsernameOrEmail(usernameOrEmail);

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
