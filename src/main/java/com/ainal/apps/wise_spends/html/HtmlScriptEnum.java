package com.ainal.apps.wise_spends.html;

import org.apache.logging.log4j.util.Strings;

import com.ainal.apps.wise_spends.util.html.HtmlLibraryUtil;

public enum HtmlScriptEnum {
	JQUERY("vendor/jquery/jquery.min.js"), BOOTSTRAP("vendor/bootstrap/js/bootstrap.bundle.min.js"),
	JQUERY_EASING("vendor/jquery-easing/jquery.easing.min.js"), MAIN_SCRIPT("js/sb-admin-2.min.js"),
	CHART("vendor/chart.js/Chart.min.js"), CHART_AREA_DEMO("js/demo/chart-area-demo.js"),
	CHART_PIE_DEMO("js/demo/chart-pie-demo.js"), CHART_BAR_DEMO("js/demo/chart-bar-demo.js"),
	DATATABLE_JQUERY("vendor/datatables/jquery.dataTables.min.js"),
	DATATABLE_BOOTSTRAP4("vendor/datatables/dataTables.bootstrap4.min.js"),
	DATATABLE_DEMO("js/demo/datatables-demo.js");

	private String src;
	private boolean flagLink;

	private HtmlScriptEnum(String src) {
		this.src = src;
		this.flagLink = false;
	}

	private HtmlScriptEnum(String src, boolean flagLink) {
		this.src = src;
		this.flagLink = flagLink;
	}

	public String getSrc() {
		return src;
	}

	public boolean isFlagLink() {
		return flagLink;
	}

	@Override
	public String toString() {
		return String.format("<script src=\"%s\"></script>",
				((this.flagLink ? HtmlLibraryUtil.DOMAIN : Strings.EMPTY) + this.src));
	}

}
