package com.ainal.apps.wise_spends.error.view.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorViewController implements ErrorController {

	@GetMapping("/error")
	public String handleError(@NonNull HttpServletRequest request) {
		String errorPage = "error"; // default

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				// handle HTTP 404 Not Found error
				errorPage += "/404";

			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				// handle HTTP 403 Forbidden error
				errorPage += "/403";

			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				// handle HTTP 500 Internal Server error
				errorPage += "/500";

			}
		}

		return errorPage;
	}
}
