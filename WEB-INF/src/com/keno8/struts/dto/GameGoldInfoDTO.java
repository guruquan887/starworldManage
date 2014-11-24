package com.keno8.struts.dto;

public class GameGoldInfoDTO {

	private int userID;
	private int gameID;
	private String accounts;
	private long score;
	private long revenue;
	private long insureScore;
	private long jifen;
	private int kindId;
	private String kindName;
	
	private long totalScore;

	public int getKindId() {
		return kindId;
	}

	public void setKindId(int kindId) {
		this.kindId = kindId;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

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

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public long getRevenue() {
		return revenue;
	}

	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}

	public long getInsureScore() {
		return insureScore;
	}

	public void setInsureScore(long insureScore) {
		this.insureScore = insureScore;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public long getJifen() {
		return jifen;
	}

	public void setJifen(long jifen) {
		this.jifen = jifen;
	}

	public long getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(long totalScore) {
		this.totalScore = totalScore;
	}
	
	

}
