package com.ainal.apps.wise_spends.view.object;

public class SelectItemVO implements IVO {

	private static final long serialVersionUID = 5130687968842285793L;

	private String title;
	private Object value;

	public SelectItemVO(String fromString) {
		String[] strList = fromString.split("<<>>");
		this.title = strList[0];
		this.value = strList[1];
	}

	public SelectItemVO() {
	}

	public SelectItemVO(String title, Object value) {
		this.title = title;
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return title + "<<>>" + value;
	}

}
