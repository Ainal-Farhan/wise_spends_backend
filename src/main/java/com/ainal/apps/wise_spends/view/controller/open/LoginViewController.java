package com.ainal.apps.wise_spends.view.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.security.auth.AuthenticationRequest;
import com.ainal.apps.wise_spends.security.auth.AuthenticationResponse;
import com.ainal.apps.wise_spends.security.auth.AuthenticationService;
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
	private AuthenticationService authenticationService;

	@GetMapping(path = { "/auth" })
	String getLoginView() {
		return "/public/login";
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

		Cookie tokenCookie = new Cookie("access_token", authenticationResponse.getToken());
		tokenCookie.setHttpOnly(true);
		tokenCookie.setDomain(request.getServerName());
		tokenCookie.setPath("/");
		response.addCookie(tokenCookie);

		return new RedirectView("/");
	}
}
