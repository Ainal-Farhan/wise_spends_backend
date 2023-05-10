package com.ainal.apps.wise_spends.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.ainal.apps.wise_spends.manager.ICurrentUserManager;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/logout")
public class LogoutViewController {
	@Autowired
	private ICurrentUserManager currentUserManager;

	@GetMapping(path = { "", "/" })
	public RedirectView logout(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {
		currentUserManager.removeToken(request, response);
		return new RedirectView("/login/auth");
	}
}
