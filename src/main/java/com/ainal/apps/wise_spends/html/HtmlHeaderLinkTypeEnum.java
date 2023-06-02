package com.ainal.apps.wise_spends.html;

import org.apache.logging.log4j.util.Strings;

public enum HtmlHeaderLinkTypeEnum {
	EMPTY(Strings.EMPTY), CSS("type/css");

	private String type;

	private HtmlHeaderLinkTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
