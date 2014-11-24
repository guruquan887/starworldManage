package com.keno8.struts.action.gameRecord;

public class GameRecordDTO {
	
	private int userID;
	private String accounts;
	private String betSerial;
	private String peroidnum;
	private int kindId;
	private String kindName;
	private String betGameArea;
	private double betGold;
	private String createTime;
	private String createTime1;
	private double betResult;
	private float betRate;
	private String winArea;
	private String gameAreaName;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPeroidnum() {
		return peroidnum;
	}
	public void setPeroidnum(String peroidnum) {
		this.peroidnum = peroidnum;
	}
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
	public String getBetGameArea() {
		return betGameArea;
	}
	public void setBetGameArea(String betGameArea) {
		this.betGameArea = betGameArea;
	}
	public double getBetGold() {
		return betGold;
	}
	public void setBetGold(double betGold) {
		this.betGold = betGold;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateTime1() {
		return createTime1;
	}
	public void setCreateTime1(String createTime1) {
		this.createTime1 = createTime1;
	}
	public double getBetResult() {
		return betResult;
	}
	public void setBetResult(double betResult) {
		this.betResult = betResult;
	}
	public float getBetRate() {
		return betRate;
	}
	public void setBetRate(float betRate) {
		this.betRate = betRate;
	}
	public String getWinArea() {
		return winArea;
	}
	public void setWinArea(String winArea) {
		this.winArea = winArea;
	}
	public String getGameAreaName() {
		return gameAreaName;
	}
	public void setGameAreaName(String gameAreaName) {
		this.gameAreaName = gameAreaName;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public String getBetSerial() {
		return betSerial;
	}
	public void setBetSerial(String betSerial) {
		this.betSerial = betSerial;
	}
	

}
