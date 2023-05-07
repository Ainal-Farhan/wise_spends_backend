package com.ainal.apps.wise_spends.thymeleaf.vo;

import java.util.HashMap;

import org.apache.logging.log4j.util.Strings;

public class ThymeleafTemplateVO {
	private String path;
	private HashMap<String, ThymeleafParamAttributeVO> attributes;

	public ThymeleafTemplateVO() {
		path = Strings.EMPTY;
		attributes = new HashMap<>();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public HashMap<String, ThymeleafParamAttributeVO> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, ThymeleafParamAttributeVO> attributes) {
		this.attributes = attributes;
	}

}
