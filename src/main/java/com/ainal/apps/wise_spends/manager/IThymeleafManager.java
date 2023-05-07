package com.ainal.apps.wise_spends.manager;

import org.springframework.ui.Model;

import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;

public interface IThymeleafManager {
	void setMainTemplate(final Model model, final ThymeleafFragmentVO content);
}
