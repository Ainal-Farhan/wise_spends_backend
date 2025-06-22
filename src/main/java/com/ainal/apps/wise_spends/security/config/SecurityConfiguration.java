package com.ainal.apps.wise_spends.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ainal.apps.wise_spends.util.properties.WiseSpendsPropertiesUtils;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private WiseSpendsPropertiesUtils wiseSpendsPropertiesUtils;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/v1/auth/**").permitAll()
						.requestMatchers("/login/**").permitAll()
						.requestMatchers("/ui/examples/template/**").permitAll().requestMatchers("/error/**")
						.permitAll().requestMatchers("/css/**").permitAll().requestMatchers("/img/**").permitAll()
						.requestMatchers("/js/**").permitAll().requestMatchers("/scss/**").permitAll()
						.requestMatchers("/vendor/**").permitAll().anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/login/auth").permitAll())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessHandler(logoutSuccessHandler()).addLogoutHandler(logoutHandler())
						.invalidateHttpSession(true)
						.deleteCookies(wiseSpendsPropertiesUtils.JWT_COOKIE_ACCESS_TOKEN_NAME()).permitAll())
				.exceptionHandling(
						exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint()));
		return http.build();
	}

	@Bean
	AuthenticationEntryPoint authenticationEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint("/login/auth");
	}

	@Bean
	LogoutSuccessHandler logoutSuccessHandler() {
		return new SimpleUrlLogoutSuccessHandler();
	}

	@Bean
	LogoutHandler logoutHandler() {
		return new SecurityContextLogoutHandler();
	}
}
