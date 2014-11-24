package com.keno8.struts.dto;

public class GameDhScoreDTO {
	
	private int userID;
	private String accounts;
	private String nickName;
	private long dhScore;
	private String createTime;
	private long beforeMoney;
	private long afterMoney;
	private long score;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public long getDhScore() {
		return dhScore;
	}
	public void setDhScore(long dhScore) {
		this.dhScore = dhScore;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public long getBeforeMoney() {
		return beforeMoney;
	}
	public void setBeforeMoney(long beforeMoney) {
		this.beforeMoney = beforeMoney;
	}
	public long getAfterMoney() {
		return afterMoney;
	}
	public void setAfterMoney(long afterMoney) {
		this.afterMoney = afterMoney;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	
}
