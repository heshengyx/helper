package com.root.helper.entity;

import java.io.Serializable;
import java.util.Date;

public class SuperFile implements Serializable {

	/**  */
	private static final long serialVersionUID = -2171693918476539245L;
	private String id;
	private String result;
	private String downloadUrl;
	private String packageMd5;
	private String status;
	private Date createTime;
	private Date updateTime;
	private String remarks;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getPackageMd5() {
		return packageMd5;
	}
	public void setPackageMd5(String packageMd5) {
		this.packageMd5 = packageMd5;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
