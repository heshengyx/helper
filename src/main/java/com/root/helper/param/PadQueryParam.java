package com.root.helper.param;

import java.io.Serializable;

public class PadQueryParam implements Serializable {

	private String name;
private String title;

	
	public void setName(String name) {
	this.name = name;
}
public String getName() {
	return name;
}
public void setTitle(String title) {
	this.title = title;
}
public String getTitle() {
	return title;
}

}