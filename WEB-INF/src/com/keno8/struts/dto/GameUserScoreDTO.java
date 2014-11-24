package com.keno8.struts.dto;

public class GameUserScoreDTO {
	private int userID;
	private String accounts;
	private String regAccounts;
	private int Score;
	private int gameLogonTimes;
	private String lastLogonIP;
	private int memberOrder;
	private int experience;

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getMemberOrder() {
		return memberOrder;
	}

	public void setMemberOrder(int memberOrder) {
		this.memberOrder = memberOrder;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

	public int getGameLogonTimes() {
		return gameLogonTimes;
	}

	public void setGameLogonTimes(int gameLogonTimes) {
		this.gameLogonTimes = gameLogonTimes;
	}

	public String getLastLogonIP() {
		return lastLogonIP;
	}

	public void setLastLogonIP(String lastLogonIP) {
		this.lastLogonIP = lastLogonIP;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getRegAccounts() {
		return regAccounts;
	}

	public void setRegAccounts(String regAccounts) {
		this.regAccounts = regAccounts;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
		userID++;
	}

}
