package com.ainal.apps.wise_spends.view.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.security.auth.AuthenticationRequest;
import com.ainal.apps.wise_spends.security.auth.AuthenticationResponse;
import com.ainal.apps.wise_spends.security.auth.AuthenticationService;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.util.properties.WiseSpendsPropertiesUtils;
import com.ainal.apps.wise_spends.view.form.login.LoginForm;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping({ "/login" })
public class LoginViewController {
	@Autowired
	IThymeleafManager thymeleafManager;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private WiseSpendsPropertiesUtils wiseSpendsPropertiesUtils;

	@GetMapping(path = { "/auth" })
	public ModelAndView getLoginView() {
		ModelAndView modelAndView = thymeleafManager
				.getTemplateHeaderJsOnlyModelAndView(new ThymeleafFragmentVO("content", "/public/login"), "Login");
		modelAndView.addObject("flagSidebar", "false");
		modelAndView.addObject("flagTopbar", "false");
		modelAndView.addObject("flagFooter", "false");
		modelAndView.addObject("flagScrollTopButton", "false");
		return modelAndView;
	}

	@PostMapping(path = "/auth")
	public RedirectView login(@ModelAttribute(name = "loginForm") LoginForm loginForm,
			@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setUsernameOrEmail(loginForm.getUsername());
		authenticationRequest.setPassword(loginForm.getPassword());
		AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);

		if (StringUtils.isBlank(authenticationResponse.getToken())) {
			return new RedirectView("/login/auth");
		}

		Cookie tokenCookie = new Cookie(wiseSpendsPropertiesUtils.JWT_COOKIE_ACCESS_TOKEN_NAME(),
				authenticationResponse.getToken());
		tokenCookie.setHttpOnly(true);
		tokenCookie.setDomain(wiseSpendsPropertiesUtils.WS_DOMAIN());
		tokenCookie.setPath("/");
		response.addCookie(tokenCookie);

		return new RedirectView("/");
	}
}
