package com.ainal.apps.wise_spends.thymeleaf.vo.fragment;

import java.util.HashMap;

import org.apache.logging.log4j.util.Strings;

public class FragmentVO {
	private String thPath;
	private String thId;
	private String path;
	private String id;
	private HashMap<String, ThymeleafParamVO> params;

	public FragmentVO() {
		thPath = Strings.EMPTY;
		thId = Strings.EMPTY;
		path = Strings.EMPTY;
		id = Strings.EMPTY;
		params = new HashMap<>();
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

	public HashMap<String, ThymeleafParamVO> getParams() {
		return params;
	}

	public void setParams(HashMap<String, ThymeleafParamVO> params) {
		this.params = params;
	}
}
