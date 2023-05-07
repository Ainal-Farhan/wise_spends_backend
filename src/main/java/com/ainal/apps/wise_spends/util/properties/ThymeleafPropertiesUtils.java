package com.ainal.apps.wise_spends.util.properties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafAttributeVO;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafFragmentVO;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafParamAttributeVO;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafParamVO;
import com.ainal.apps.wise_spends.thymeleaf.vo.ThymeleafTemplateVO;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.util.StringUtils;

@Component
public class ThymeleafPropertiesUtils {
	private static final String THYMELEAF_ENV_DATA_REF = "thymeleaf.reference.data.type.";
	private static final String THYMELEAF_ENV_DATA_REF_BOOLEAN = THYMELEAF_ENV_DATA_REF + "boolean";
	private static final String THYMELEAF_ENV_DATA_REF_STRING = THYMELEAF_ENV_DATA_REF + "string";

	// fragments
	private static final String THYMELEAF_ENV_FRAGMENT = "thymeleaf.fragment.";
	private static final String THYMELEAF_ENV_FRAGMENT_PATH_TH = ".path.th";
	private static final String THYMELEAF_ENV_FRAGMENT_PATH = ".path";
	private static final String THYMELEAF_ENV_FRAGMENT_ID = ".id";
	private static final String THYMELEAF_ENV_FRAGMENT_ID_TH = ".id.th";
	private static final String THYMELEAF_ENV_FRAGMENT_PARAMS = ".params";

	private static final String THYMELEAF_FRAGMENT_HEADER = "header";
	private static final String THYMELEAF_FRAGMENT_SIDEBAR = "sidebar";
	private static final String THYMELEAF_FRAGMENT_TOPBAR = "topbar";
	private static final String THYMELEAF_FRAGMENT_DEFAULT_CONTENT = "content";
	private static final String THYMELEAF_FRAGMENT_MODAL = "modal";
	private static final String THYMELEAF_FRAGMENT_FOOTER = "footer";
	private static final String THYMELEAF_FRAGMENT_JS = "js";

	// templates
	private static final String THYMELEAF_ENV_TEMPLATE = "thymeleaf.template.";
	private static final String THYMELEAF_ENV_TEMPLATE_PATH = ".path";
	private static final String THYMELEAF_ENV_TEMPLATE_MODEL_ATTRS = ".model.attrs";

	private static final String THYMELEAF_TEMPLATE_MAIN = "main";

	@Autowired
	private Environment env;

	public ThymeleafTemplateVO getMainTemplate() {
		return getTemplateVO(THYMELEAF_TEMPLATE_MAIN);
	}

	public ThymeleafFragmentVO getHeaderFragment() {
		return getFragmentVO(THYMELEAF_FRAGMENT_HEADER);
	}

	public ThymeleafFragmentVO getSidebarFragment() {
		return getFragmentVO(THYMELEAF_FRAGMENT_SIDEBAR);
	}

	public ThymeleafFragmentVO getTopbarFragment() {
		return getFragmentVO(THYMELEAF_FRAGMENT_TOPBAR);
	}

	public ThymeleafFragmentVO getDefaultContentFragment() {
		return getFragmentVO(THYMELEAF_FRAGMENT_DEFAULT_CONTENT);
	}

	public ThymeleafFragmentVO getFooterFragment() {
		return getFragmentVO(THYMELEAF_FRAGMENT_FOOTER);
	}

	public ThymeleafFragmentVO getModalFragment() {
		return getFragmentVO(THYMELEAF_FRAGMENT_MODAL);
	}

	public ThymeleafFragmentVO getJsFragment() {
		return getFragmentVO(THYMELEAF_FRAGMENT_JS);
	}

	public String getBooleanDataType() {
		return getProperty(THYMELEAF_ENV_DATA_REF_BOOLEAN);
	}

	public String getStringDataType() {
		return getProperty(THYMELEAF_ENV_DATA_REF_STRING);
	}

	private ThymeleafFragmentVO getFragmentVO(final String fragment) {
		final String key = THYMELEAF_ENV_FRAGMENT + fragment;
		ThymeleafFragmentVO fragmentVO = new ThymeleafFragmentVO();

		fragmentVO.setId(getProperty(key + THYMELEAF_ENV_FRAGMENT_ID));
		fragmentVO.setPath(getProperty(key + THYMELEAF_ENV_FRAGMENT_PATH));
		fragmentVO.setThId(getProperty(key + THYMELEAF_ENV_FRAGMENT_ID_TH));
		fragmentVO.setThPath(getProperty(key + THYMELEAF_ENV_FRAGMENT_PATH_TH));
		fragmentVO.setParams(getThymeleafParamAttributeVO(fragmentVO.getParams(),
				getProperty(key + THYMELEAF_ENV_FRAGMENT_PARAMS), false));

		return fragmentVO;
	}

	private ThymeleafTemplateVO getTemplateVO(final String template) {
		final String key = THYMELEAF_ENV_TEMPLATE + template;
		ThymeleafTemplateVO templateVO = new ThymeleafTemplateVO();

		templateVO.setPath(getProperty(key + THYMELEAF_ENV_TEMPLATE_PATH));
		templateVO.setAttributes(getThymeleafParamAttributeVO(templateVO.getAttributes(),
				getProperty(key + THYMELEAF_ENV_TEMPLATE_MODEL_ATTRS), true));

		return templateVO;
	}

	private HashMap<String, ThymeleafParamAttributeVO> getThymeleafParamAttributeVO(
			HashMap<String, ThymeleafParamAttributeVO> params, String paramString, @NonNull final boolean isTemplate) {
		if (StringUtils.isBlank(paramString)) {
			return params;
		}

		final String seperatorParam = ",";

		List<String> rawParams = Arrays.asList(paramString.split(seperatorParam));

		if (CollectionUtils.isEmpty(rawParams)) {
			return params;
		}

		for (String raw : rawParams) {
			if (StringUtils.isBlank(raw)) {
				continue;
			}

			ThymeleafParamAttributeVO paramAttributeVO = isTemplate ? new ThymeleafAttributeVO(raw, this)
					: new ThymeleafParamVO(raw, this);
			params.put(paramAttributeVO.getName(), paramAttributeVO);
		}

		return params;
	}

	private String getProperty(String key) {
		return env.getProperty(key);
	}
}
