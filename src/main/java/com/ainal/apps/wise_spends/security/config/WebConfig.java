package com.ainal.apps.wise_spends.security.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;

@Configuration
public class WebConfig extends WebMvcAutoConfiguration {

	@Bean
	FormHttpMessageConverter formHttpMessageConverter() {
		return new FormHttpMessageConverter();
	}

}
