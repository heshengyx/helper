package com.root.helper.entity;

import java.io.Serializable;
import java.util.Date;

public class Pad implements Serializable {

	private String id;
	private String name;
private String title;

	private String status;
	private Date createTime;
	private Date updateTime;
	private String remarks;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}