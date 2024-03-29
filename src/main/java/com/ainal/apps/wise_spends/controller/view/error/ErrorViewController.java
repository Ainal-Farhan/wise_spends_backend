package com.ainal.apps.wise_spends.controller.view.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ainal.apps.wise_spends.manager.ICurrentUserManager;
import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorViewController implements ErrorController {

	@Autowired
	private ICurrentUserManager currentUserManager;

	@Autowired
	IThymeleafManager thymeleafManager;

	@GetMapping("/error")
	public ModelAndView handleError(@NonNull HttpServletRequest request) {
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

			if (currentUserManager.getCurrentUser(request) != null) {
				ModelAndView modelAndView = thymeleafManager
						.getMainTemplateModelAndView(new ThymeleafFragmentVO("content", errorPage), errorPage);
				modelAndView.addObject("flagLogin", true);
				return modelAndView;
			}

			ModelAndView modelAndView = thymeleafManager
					.getTemplateHeaderJsOnlyModelAndView(new ThymeleafFragmentVO("content", errorPage), errorPage);
			modelAndView.addObject("bodyClass", "");
			modelAndView.addObject("flagLogin", false);

			return modelAndView;
		}

		return new ModelAndView(errorPage);
	}
}
