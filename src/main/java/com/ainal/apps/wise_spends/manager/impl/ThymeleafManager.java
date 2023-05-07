package com.ainal.apps.wise_spends.manager.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

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
		if (!templateVO.getAttributes().isEmpty()) {
			for (HashMap.Entry<String, ThymeleafParamAttributeVO> entry : templateVO.getAttributes().entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue().getValue();
				model.addAttribute(key, value);
			}
		}

		ThymeleafFragmentVO header = thymeleafPropertiesUtils.getHeaderFragment();
		ThymeleafFragmentVO sidebar = thymeleafPropertiesUtils.getSidebarFragment();
		ThymeleafFragmentVO topbar = thymeleafPropertiesUtils.getTopbarFragment();
		ThymeleafFragmentVO defaultContent = thymeleafPropertiesUtils.getDefaultContentFragment();
		ThymeleafFragmentVO footer = thymeleafPropertiesUtils.getFooterFragment();
		ThymeleafFragmentVO modal = thymeleafPropertiesUtils.getModalFragment();
		ThymeleafFragmentVO js = thymeleafPropertiesUtils.getJsFragment();

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

			if (!fragment.getParams().isEmpty()) {
				for (HashMap.Entry<String, ThymeleafParamAttributeVO> entry : fragment.getParams().entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue().getValue();
					model.addAttribute(key, value);
				}
			}

			model.addAttribute(fragment.getThPath(), fragment.getPath());
			model.addAttribute(fragment.getThId(), fragment.getId());
		}

	}

}
