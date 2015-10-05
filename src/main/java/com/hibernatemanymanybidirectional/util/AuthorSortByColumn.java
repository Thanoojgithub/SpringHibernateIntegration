package com.hibernatemanymanybidirectional.util;

public enum AuthorSortByColumn {

	authorId("authorId"), authorName("authorName");

	String value;

	public String getValue() {
		return value;
	}

	AuthorSortByColumn(String value) {
		this.value = value;
	}
}
