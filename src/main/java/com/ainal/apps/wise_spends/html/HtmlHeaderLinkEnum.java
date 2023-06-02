package com.ainal.apps.wise_spends.html;

import org.apache.logging.log4j.util.Strings;

import com.ainal.apps.wise_spends.util.html.HtmlLibraryUtil;

public enum HtmlHeaderLinkEnum {
	FONT_AWESOME("vendor/fontawesome-free/css/all.min.css", "stylesheet", HtmlHeaderLinkTypeEnum.CSS, false),
	FONT_GOOGLEAPIS(
			"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i",
			"stylesheet", HtmlHeaderLinkTypeEnum.CSS, true),
	MAIN_STYLE("/css/sb-admin-2.css", "stylesheet", HtmlHeaderLinkTypeEnum.CSS, false);

	private HtmlHeaderLinkEnum(String href, String rel, HtmlHeaderLinkTypeEnum type, boolean flagLink) {
		this.href = href;
		this.rel = rel;
		this.type = type;
		this.flagLink = flagLink;
	}

	private boolean flagLink;

	private String href;

	private String rel;

	private HtmlHeaderLinkTypeEnum type;

	public String getHref() {
		return href;
	}

	public String getRel() {
		return rel;
	}

	public HtmlHeaderLinkTypeEnum getType() {
		return type;
	}

	public boolean isFlagLink() {
		return flagLink;
	}

	@Override
	public String toString() {
		return String.format("<link href=\"%s\" rel=\"%s\" type=\"%s\">",
				((this.flagLink ? HtmlLibraryUtil.DOMAIN : Strings.EMPTY) + this.href), this.rel, this.type);
	}

}
