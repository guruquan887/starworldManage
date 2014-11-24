package com.keno8.struts.dto;

public class UserGoldFlowDTO {
	
	private int userID;
	private double beforeMoney;
	private double afterMoney;
	private double diffMoney;
	private String beizhu;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public double getBeforeMoney() {
		return beforeMoney;
	}
	public void setBeforeMoney(double beforeMoney) {
		this.beforeMoney = beforeMoney;
	}
	public double getAfterMoney() {
		return afterMoney;
	}
	public void setAfterMoney(double afterMoney) {
		this.afterMoney = afterMoney;
	}
	public double getDiffMoney() {
		return diffMoney;
	}
	public void setDiffMoney(double diffMoney) {
		this.diffMoney = diffMoney;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	
	

}
