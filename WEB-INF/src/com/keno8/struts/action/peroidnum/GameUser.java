package com.keno8.struts.action.peroidnum;

public class GameUser {
	private int userID;
	private String account;
	private String nickName;
	private int sex;
	private double score;
	private int insureScore;
	private int memberOrder;
	private String type ;
	private double freezeScore;//冻结资金
	private double cunScore;//第一次存款金额
	private double experience;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}

	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getInsureScore() {
		return insureScore;
	}
	public void setInsureScore(int insureScore) {
		this.insureScore = insureScore;
	}
	public int getMemberOrder() {
		return memberOrder;
	}
	public void setMemberOrder(int memberOrder) {
		this.memberOrder = memberOrder;
	}
	public double getFreezeScore() {
		return freezeScore;
	}
	public void setFreezeScore(double freezeScore) {
		this.freezeScore = freezeScore;
	}
	public double getCunScore() {
		return cunScore;
	}
	public void setCunScore(double cunScore) {
		this.cunScore = cunScore;
	}
	public double getExperience() {
		return experience;
	}
	public void setExperience(double experience) {
		this.experience = experience;
	}
	
	
}
