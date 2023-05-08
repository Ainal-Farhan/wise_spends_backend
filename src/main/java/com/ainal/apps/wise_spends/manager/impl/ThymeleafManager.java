package com.ainal.apps.wise_spends.manager.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.ainal.apps.wise_spends.manager.IThymeleafManager;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafParamAttributeVO;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafTemplateVO;
import com.ainal.apps.wise_spends.util.properties.ThymeleafPropertiesUtils;

import io.micrometer.common.util.StringUtils;

@Component
public class ThymeleafManager implements IThymeleafManager {
	@Autowired
	ThymeleafPropertiesUtils thymeleafPropertiesUtils;

	@Override
	public void setMainTemplate(final Model model, final ThymeleafFragmentVO content) {
		ThymeleafTemplateVO templateVO = thymeleafPropertiesUtils.getMainTemplate();
		setMainTemplateContent(templateVO, content, null, model, false);
	}

	@Override
	public ModelAndView getMainTemplateModelAndView(final ThymeleafFragmentVO content, String title) {
		ThymeleafTemplateVO templateVO = thymeleafPropertiesUtils.getMainTemplate();
		ModelAndView mainTemplateModelAndView = new ModelAndView(templateVO.getPath());

		setMainTemplateContent(templateVO, content, mainTemplateModelAndView, null, false);

		if (!StringUtils.isBlank(title)) {
			mainTemplateModelAndView.addObject("title", title);
		}

		return mainTemplateModelAndView;
	}

	@Override
	public ModelAndView getExampleMainTemplateModelAndView(ThymeleafFragmentVO content, String title) {
		ThymeleafTemplateVO templateVO = new ThymeleafTemplateVO(
				ThymeleafPropertiesUtils.DIR_EXAMPLE_TEMPLATE + "html/main-template",
				thymeleafPropertiesUtils.getThymeleafParamAttributeVO(new HashMap<>(),
						"flagSidebar:true::b,flagTopbar:true::b,flagFooter:true::b,flagScrollTopButton:true::b", true));

		ModelAndView mainTemplateModelAndView = new ModelAndView(templateVO.getPath());

		setMainTemplateContent(templateVO, content, mainTemplateModelAndView, null, true);
		if (!StringUtils.isBlank(title)) {
			mainTemplateModelAndView.addObject("title", title);
		}

		return mainTemplateModelAndView;
	}

	@Override
	public ModelAndView getTemplateHeaderJsOnlyModelAndView(ThymeleafFragmentVO content, String title) {
		ThymeleafTemplateVO templateVO = thymeleafPropertiesUtils.getTemplateHeaderJsOnly();
		ModelAndView mainTemplateModelAndView = new ModelAndView(templateVO.getPath());

		addAllToModelAndViewThymeleafParamAttributeVO(mainTemplateModelAndView, templateVO.getAttributes());

		ThymeleafFragmentVO header = thymeleafPropertiesUtils.getHeaderFragment();
		ThymeleafFragmentVO defaultContent = thymeleafPropertiesUtils.getDefaultContentFragment();
		ThymeleafFragmentVO js = thymeleafPropertiesUtils.getJsFragment();

		if (content != null) {
			content.setThId(defaultContent.getThId());
			content.setThPath(defaultContent.getThPath());
			if (StringUtils.isBlank(content.getId()) || StringUtils.isBlank(content.getPath())) {
				content.setId(defaultContent.getId());
				content.setPath(defaultContent.getPath());
			}
		}

		List<ThymeleafFragmentVO> fragments = Arrays.asList(header, (content == null ? defaultContent : content), js);
		for (ThymeleafFragmentVO fragment : fragments) {
			addAllToModelAndViewThymeleafParamAttributeVO(mainTemplateModelAndView, fragment.getParams());
			mainTemplateModelAndView.addObject(fragment.getThPath(), fragment.getPath());
			mainTemplateModelAndView.addObject(fragment.getThId(), fragment.getId());
		}
		return mainTemplateModelAndView;
	}

	@Override
	public ModelAndView getExampleTemplateHeaderJsOnlyModelAndView(ThymeleafFragmentVO content, String title) {
		final String path = ThymeleafPropertiesUtils.DIR_EXAMPLE_TEMPLATE + "html/";

		ThymeleafTemplateVO templateVO = new ThymeleafTemplateVO(path + "template-header-js-only", new HashMap<>());
		ModelAndView mainTemplateModelAndView = new ModelAndView(templateVO.getPath());

		addAllToModelAndViewThymeleafParamAttributeVO(mainTemplateModelAndView, templateVO.getAttributes());

		ThymeleafFragmentVO header = new ThymeleafFragmentVO("header", path + "header", "headerPathThFragment",
				"headerThFragment",
				thymeleafPropertiesUtils.getThymeleafParamAttributeVO(new HashMap<>(),
						"title:Wise Spends::s,flagFontAwesome:true::b,flagFontsGoogleapis:true::b,flagStyle:true::b",
						false));
		ThymeleafFragmentVO defaultContent = new ThymeleafFragmentVO("content", path + "default-content",
				"contentPathThFragment", "contentThFragment", new HashMap<>());
		ThymeleafFragmentVO js = new ThymeleafFragmentVO("js", path + "js", "jsPathThFragment", "jsThFragment",
				thymeleafPropertiesUtils.getThymeleafParamAttributeVO(new HashMap<>(),
						"flagBootstrap:true::b,flagJquery:true::b,flagCustomScript:true::b,flagChartJs:false::b,"
								+ "flagChartAreaDemo:false::b,flagChartPieDemo:false::b,flagChartBarDemo:false::b,"
								+ "flagDataTable:false::b,flagDataTableDemo:false::b",
						false));

		if (content != null) {
			content.setThId(defaultContent.getThId());
			content.setThPath(defaultContent.getThPath());
			if (StringUtils.isBlank(content.getId()) || StringUtils.isBlank(content.getPath())) {
				content.setId(defaultContent.getId());
				content.setPath(defaultContent.getPath());
			}
		}

		List<ThymeleafFragmentVO> fragments = Arrays.asList(header, (content == null ? defaultContent : content), js);
		for (ThymeleafFragmentVO fragment : fragments) {
			addAllToModelAndViewThymeleafParamAttributeVO(mainTemplateModelAndView, fragment.getParams());
			mainTemplateModelAndView.addObject(fragment.getThPath(), fragment.getPath());
			mainTemplateModelAndView.addObject(fragment.getThId(), fragment.getId());
		}
		return mainTemplateModelAndView;
	}

	private void setMainTemplateContent(final ThymeleafTemplateVO templateVO, final ThymeleafFragmentVO content,
			ModelAndView modelAndView, Model model, boolean isExample) {
		if (model != null) {
			addAllToModelThymeleafParamAttributeVO(model, templateVO.getAttributes());
		}

		if (modelAndView != null) {
			addAllToModelAndViewThymeleafParamAttributeVO(modelAndView, templateVO.getAttributes());
		}

		ThymeleafFragmentVO header = thymeleafPropertiesUtils.getHeaderFragment();
		ThymeleafFragmentVO sidebar = thymeleafPropertiesUtils.getSidebarFragment();
		ThymeleafFragmentVO topbar = thymeleafPropertiesUtils.getTopbarFragment();
		ThymeleafFragmentVO defaultContent = thymeleafPropertiesUtils.getDefaultContentFragment();
		ThymeleafFragmentVO footer = thymeleafPropertiesUtils.getFooterFragment();
		ThymeleafFragmentVO modal = thymeleafPropertiesUtils.getModalFragment();
		ThymeleafFragmentVO js = thymeleafPropertiesUtils.getJsFragment();

		if (isExample) {
			final String path = ThymeleafPropertiesUtils.DIR_EXAMPLE_TEMPLATE + "html/";
			header = new ThymeleafFragmentVO("header", path + "header", "headerPathThFragment", "headerThFragment",
					thymeleafPropertiesUtils.getThymeleafParamAttributeVO(new HashMap<>(),
							"title:Wise Spends::s,flagFontAwesome:true::b,flagFontsGoogleapis:true::b,flagStyle:true::b",
							false));
			sidebar = new ThymeleafFragmentVO("sidebar", path + "sidebar", "sidebarPathThFragment", "sidebarThFragment",
					new HashMap<>());
			topbar = new ThymeleafFragmentVO("topbar", path + "topbar", "topbarPathThFragment", "topbarThFragment",
					new HashMap<>());
			defaultContent = new ThymeleafFragmentVO("content", path + "default-content", "contentPathThFragment",
					"contentThFragment", new HashMap<>());
			footer = new ThymeleafFragmentVO("footer", path + "footer", "footerPathThFragment", "footerThFragment",
					new HashMap<>());
			modal = new ThymeleafFragmentVO("modal", path + "modal", "modalPathThFragment", "modalThFragment",
					thymeleafPropertiesUtils.getThymeleafParamAttributeVO(new HashMap<>(), "flagModalLogout:true::b",
							false));
			js = new ThymeleafFragmentVO("js", path + "js", "jsPathThFragment", "jsThFragment",
					thymeleafPropertiesUtils.getThymeleafParamAttributeVO(new HashMap<>(),
							"flagBootstrap:true::b,flagJquery:true::b,flagCustomScript:true::b,flagChartJs:true::b,"
									+ "flagChartAreaDemo:true::b,flagChartPieDemo:true::b,flagChartBarDemo:false::b,"
									+ "flagDataTable:false::b,flagDataTableDemo:false::b",
							false));
		}

		if (content != null) {
			content.setThId(defaultContent.getThId());
			content.setThPath(defaultContent.getThPath());
			if (StringUtils.isBlank(content.getId()) || StringUtils.isBlank(content.getPath())) {
				content.setId(defaultContent.getId());
				content.setPath(defaultContent.getPath());
			}
		}

		List<ThymeleafFragmentVO> fragments = Arrays.asList(header, sidebar, topbar,
				(content == null ? defaultContent : content), footer, modal, js);
		for (ThymeleafFragmentVO fragment : fragments) {
			if (model != null) {
				addAllToModelThymeleafParamAttributeVO(model, fragment.getParams());
				model.addAttribute(fragment.getThPath(), fragment.getPath());
				model.addAttribute(fragment.getThId(), fragment.getId());
			}

			if (modelAndView != null) {
				addAllToModelAndViewThymeleafParamAttributeVO(modelAndView, fragment.getParams());
				modelAndView.addObject(fragment.getThPath(), fragment.getPath());
				modelAndView.addObject(fragment.getThId(), fragment.getId());
			}
		}
	}

	private void addAllToModelThymeleafParamAttributeVO(Model model,
			HashMap<String, ThymeleafParamAttributeVO> thymeleafParamAttributeVO) {
		if (!thymeleafParamAttributeVO.isEmpty()) {
			for (HashMap.Entry<String, ThymeleafParamAttributeVO> entry : thymeleafParamAttributeVO.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue().getValue();
				model.addAttribute(key, value);
			}
		}
	}

	private void addAllToModelAndViewThymeleafParamAttributeVO(ModelAndView modelAndView,
			HashMap<String, ThymeleafParamAttributeVO> thymeleafParamAttributeVO) {
		if (!thymeleafParamAttributeVO.isEmpty()) {
			for (HashMap.Entry<String, ThymeleafParamAttributeVO> entry : thymeleafParamAttributeVO.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue().getValue();
				modelAndView.addObject(key, value);
			}
		}
	}

}
