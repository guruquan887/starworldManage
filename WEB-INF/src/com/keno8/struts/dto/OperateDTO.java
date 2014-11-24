package com.keno8.struts.dto;

public class OperateDTO {
	
	private int id;
	private String operateName;
	private String operateDetails;
	private String operateIP;
	private String operateTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public String getOperateDetails() {
		return operateDetails;
	}
	public void setOperateDetails(String operateDetails) {
		this.operateDetails = operateDetails;
	}
	public String getOperateIP() {
		return operateIP;
	}
	public void setOperateIP(String operateIP) {
		this.operateIP = operateIP;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
}
