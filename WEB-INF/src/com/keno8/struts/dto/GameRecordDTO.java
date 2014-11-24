package com.keno8.struts.dto;

public class GameRecordDTO {
	private int id;
	private int userId;
	private String accounts;
	private int roomId;
	private String peroidnum;
	private int gameRuleId;
	private double totalGoldBet;
	private String createTime;
	private String createTime1;
	private int single;
	private int serpent;
	private int large;
	private int peace;
	private int tiger;
	private int small;
	private int pairs;
	private int rate;
	private String jiesuan;
	private String rateNumber;
	private String firstMoney;
	private String lastMoney;
	private String winLostMoney;    //纯输赢。扣除本金后
	private String roomDisPlayName; //游戏房间名
	private double winGold;
	private double pureGold;
	private double rebate;
	private long lastGold;
	
	public double getRebate() {
		return rebate;
	}
	public void setRebate(double rebate) {
		this.rebate = rebate;
	}
	public String getRoomDisPlayName() {
		return roomDisPlayName;
	}
	public void setRoomDisPlayName(String roomDisPlayName) {
		this.roomDisPlayName = roomDisPlayName;
	}
	public String getWinLostMoney() {
		return winLostMoney;
	}
	public void setWinLostMoney(String winLostMoney) {
		this.winLostMoney = winLostMoney;
	}
	public String getFirstMoney() {
		return firstMoney;
	}
	public void setFirstMoney(String firstMoney) {
		this.firstMoney = firstMoney;
	}
	public String getLastMoney() {
		return lastMoney;
	}
	public void setLastMoney(String lastMoney) {
		this.lastMoney = lastMoney;
	}
	public String getRateNumber() {
		return rateNumber;
	}
	public void setRateNumber(String rateNumber) {
		this.rateNumber = rateNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getPeroidnum() {
		return peroidnum;
	}
	public void setPeroidnum(String peroidnum) {
		this.peroidnum = peroidnum;
	}
	public int getGameRuleId() {
		return gameRuleId;
	}
	public void setGameRuleId(int gameRuleId) {
		this.gameRuleId = gameRuleId;
	}

	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getSingle() {
		return single;
	}
	public void setSingle(int single) {
		this.single = single;
	}
	public int getSerpent() {
		return serpent;
	}
	public void setSerpent(int serpent) {
		this.serpent = serpent;
	}
	public int getLarge() {
		return large;
	}
	public void setLarge(int large) {
		this.large = large;
	}
	public int getPeace() {
		return peace;
	}
	public void setPeace(int peace) {
		this.peace = peace;
	}
	public int getTiger() {
		return tiger;
	}
	public void setTiger(int tiger) {
		this.tiger = tiger;
	}
	public int getSmall() {
		return small;
	}
	public void setSmall(int small) {
		this.small = small;
	}
	public int getPairs() {
		return pairs;
	}
	public void setPairs(int pairs) {
		this.pairs = pairs;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getJiesuan() {
		return jiesuan;
	}
	public void setJiesuan(String jiesuan) {
		this.jiesuan = jiesuan;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public double getWinGold() {
		return winGold;
	}
	public void setWinGold(double winGold) {
		this.winGold = winGold;
	}
	public double getPureGold() {
		return pureGold;
	}
	public void setPureGold(double pureGold) {
		this.pureGold = pureGold;
	}
	public double getTotalGoldBet() {
		return totalGoldBet;
	}
	public void setTotalGoldBet(double totalGoldBet) {
		this.totalGoldBet = totalGoldBet;
	}
	public String getCreateTime1() {
		return createTime1;
	}
	public void setCreateTime1(String createTime1) {
		this.createTime1 = createTime1;
	}
	public long getLastGold() {
		return lastGold;
	}
	public void setLastGold(long lastGold) {
		this.lastGold = lastGold;
	}
	
}
