package com.doowal.struts.action.bbtj;

public class GameRecordDTO {
	private int id;
	private int userID;
	private String accounts;
	private String betSerial;// 游戏期数（局数）
	private String gameAreaName;//下注区域
	private double betGold; //下注金额
	private double betRate; //下注区域汇率
	private String winArea; //开牌结果
	private String kindName; //游戏名称
	private String gameResult; //开牌牌数
	private double betResult; //下注输赢
	private String generateTime; //下注时间
	private String generateTime1; //开牌时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getBetSerial() {
		return betSerial;
	}
	public void setBetSerial(String betSerial) {
		this.betSerial = betSerial;
	}
	public String getGameAreaName() {
		return gameAreaName;
	}
	public void setGameAreaName(String gameAreaName) {
		this.gameAreaName = gameAreaName;
	}
	public double getBetGold() {
		return betGold;
	}
	public void setBetGold(double betGold) {
		this.betGold = betGold;
	}
	public double getBetRate() {
		return betRate;
	}
	public void setBetRate(double betRate) {
		this.betRate = betRate;
	}
	public String getWinArea() {
		return winArea;
	}
	public void setWinArea(String winArea) {
		this.winArea = winArea;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getGameResult() {
		return gameResult;
	}
	public void setGameResult(String gameResult) {
		this.gameResult = gameResult;
	}
	public double getBetResult() {
		return betResult;
	}
	public void setBetResult(double betResult) {
		this.betResult = betResult;
	}
	public String getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}
	public String getGenerateTime1() {
		return generateTime1;
	}
	public void setGenerateTime1(String generateTime1) {
		this.generateTime1 = generateTime1;
	}
	
}
