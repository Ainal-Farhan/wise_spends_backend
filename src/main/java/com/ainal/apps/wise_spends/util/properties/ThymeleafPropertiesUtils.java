package com.ainal.apps.wise_spends.util.properties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ainal.apps.wise_spends.thymeleaf.vo.fragment.FragmentVO;
import com.ainal.apps.wise_spends.thymeleaf.vo.fragment.ThymeleafParamVO;

import io.micrometer.common.util.StringUtils;

@Component
public class ThymeleafPropertiesUtils {
	private static final String THYMELEAF_ENV_DATA_REF = "thymeleaf.reference.data.type.";
	private static final String THYMELEAF_ENV_DATA_REF_BOOLEAN = THYMELEAF_ENV_DATA_REF + "boolean";
	private static final String THYMELEAF_ENV_DATA_REF_STRING = THYMELEAF_ENV_DATA_REF + "string";

	private static final String THYMELEAF_ENV_MAIN = "thymeleaf.fragment.";
	private static final String THYMELEAF_ENV_NAME = ".name";
	private static final String THYMELEAF_ENV_PATH = ".path";
	private static final String THYMELEAF_ENV_ID = ".id";
	private static final String THYMELEAF_ENV_ID_TH = ".id.th";
	private static final String THYMELEAF_ENV_PARAMS = ".params";

	private static final String THYMELEAF_DOMAIN_HEADER = "header";
	private static final String THYMELEAF_DOMAIN_SIDEBAR = "sidebar";
	private static final String THYMELEAF_DOMAIN_TOPBAR = "topbar";
	private static final String THYMELEAF_DOMAIN_DEFAULT_CONTENT = "content";
	private static final String THYMELEAF_DOMAIN_MODAL = "modal";
	private static final String THYMELEAF_DOMAIN_FOOTER = "footer";
	private static final String THYMELEAF_DOMAIN_JS = "js";

	@Autowired
	private Environment env;

	public FragmentVO getHeaderFragment() {
		return getFragmentVO(THYMELEAF_DOMAIN_HEADER);
	}

	public FragmentVO getSidebarFragment() {
		return getFragmentVO(THYMELEAF_DOMAIN_SIDEBAR);
	}

	public FragmentVO getTopbarFragment() {
		return getFragmentVO(THYMELEAF_DOMAIN_TOPBAR);
	}

	public FragmentVO getDefaultContentFragment() {
		return getFragmentVO(THYMELEAF_DOMAIN_DEFAULT_CONTENT);
	}

	public FragmentVO getFooterFragment() {
		return getFragmentVO(THYMELEAF_DOMAIN_FOOTER);
	}

	public FragmentVO getModalFragment() {
		return getFragmentVO(THYMELEAF_DOMAIN_MODAL);
	}

	public FragmentVO getJsFragment() {
		return getFragmentVO(THYMELEAF_DOMAIN_JS);
	}

	public String getBooleanDataType() {
		return getProperty(THYMELEAF_ENV_DATA_REF_BOOLEAN);
	}

	public String getStringDataType() {
		return getProperty(THYMELEAF_ENV_DATA_REF_STRING);
	}

	private FragmentVO getFragmentVO(final String domain) {
		final String key = THYMELEAF_ENV_MAIN + domain;
		FragmentVO fragmentVO = new FragmentVO();

		fragmentVO.setId(getProperty(key + THYMELEAF_ENV_ID));
		fragmentVO.setPath(getProperty(key + THYMELEAF_ENV_PATH));
		fragmentVO.setThId(getProperty(key + THYMELEAF_ENV_ID_TH));
		fragmentVO.setThPath(getProperty(key + THYMELEAF_ENV_NAME));
		fragmentVO.setParams(getFragmentVOParams(fragmentVO.getParams(), getProperty(key + THYMELEAF_ENV_PARAMS)));

		return fragmentVO;
	}

	private HashMap<String, ThymeleafParamVO> getFragmentVOParams(HashMap<String, ThymeleafParamVO> params,
			String paramString) {
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

			ThymeleafParamVO paramVO = new ThymeleafParamVO(raw, this);
			params.put(paramVO.getName(), paramVO);
		}

		return params;
	}

	private String getProperty(String key) {
		return env.getProperty(key);
	}
}
