package com.ainal.apps.wise_spends.manager;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;

public interface IThymeleafManager {
	void setMainTemplate(final Model model, final ThymeleafFragmentVO content);

	ModelAndView getMainTemplateModelAndView(final ThymeleafFragmentVO content, String title);

	ModelAndView getTemplateHeaderJsOnlyModelAndView(final ThymeleafFragmentVO content, String title);

	ModelAndView getExampleTemplateHeaderJsOnlyModelAndView(final ThymeleafFragmentVO content, String title);

	ModelAndView getExampleMainTemplateModelAndView(final ThymeleafFragmentVO content, String title);
}
