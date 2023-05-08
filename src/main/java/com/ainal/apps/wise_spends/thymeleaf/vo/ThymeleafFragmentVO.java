package com.ainal.apps.wise_spends.thymeleaf.vo;

import java.util.HashMap;

import org.apache.logging.log4j.util.Strings;

public class ThymeleafFragmentVO {
	private String thPath;
	private String thId;
	private String path;
	private String id;
	private HashMap<String, ThymeleafParamAttributeVO> params;

	public ThymeleafFragmentVO() {
		thPath = Strings.EMPTY;
		thId = Strings.EMPTY;
		path = Strings.EMPTY;
		id = Strings.EMPTY;
		params = new HashMap<>();
	}

	public ThymeleafFragmentVO(String id, String path) {
		this.path = path;
		this.id = id;
		params = new HashMap<>();
	}

	public ThymeleafFragmentVO(String id, String path, HashMap<String, ThymeleafParamAttributeVO> params) {
		this.path = path;
		this.id = id;
		if (params == null) {
			params = new HashMap<>();
		}
		this.params = params;
	}

	public ThymeleafFragmentVO(String id, String path, String thPath, String thId,
			HashMap<String, ThymeleafParamAttributeVO> params) {
		this.path = path;
		this.id = id;
		this.thPath = thPath;
		this.thId = thId;
		if (params == null) {
			params = new HashMap<>();
		}
		this.params = params;
	}

	public String getThPath() {
		return thPath;
	}

	public void setThPath(String thPath) {
		this.thPath = thPath;
	}

	public String getThId() {
		return thId;
	}

	public void setThId(String thId) {
		this.thId = thId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<String, ThymeleafParamAttributeVO> getParams() {
		return params;
	}

	public void setParams(HashMap<String, ThymeleafParamAttributeVO> params) {
		this.params = params;
	}
}
