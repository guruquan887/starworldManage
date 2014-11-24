package com.keno8.struts.action.single;

public class NoteDTO {
	
	private int userID;
	private String peroidnum;
	private int roomId;
	private String roomName;
	private long totalGoldBet;
	private long totalSingle;
	private long totalPairs;
	private long totalLarge;
	private long totalSmall;
	private long totalSerpent;
	private long totalPeace;
	private long totalTiger;
	private long totalRate;
	private String createTime;
	private String createTime1;
	
	//个人的投注记录
	private String accounts;
	private long goldBet;
	private long single;
	private long pairs;
	private long large;
	private long small;
	private long serpent;
	private long peace;
	private long tiger;
	private long rate;
	//个人的投注记录字段
	private String [] count;
	
	private String [] color;
	
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
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public long getTotalGoldBet() {
		return totalGoldBet;
	}
	public void setTotalGoldBet(long totalGoldBet) {
		this.totalGoldBet = totalGoldBet;
	}
	public long getTotalSingle() {
		return totalSingle;
	}
	public void setTotalSingle(long totalSingle) {
		this.totalSingle = totalSingle;
	}
	public long getTotalPairs() {
		return totalPairs;
	}
	public void setTotalPairs(long totalPairs) {
		this.totalPairs = totalPairs;
	}
	public long getTotalLarge() {
		return totalLarge;
	}
	public void setTotalLarge(long totalLarge) {
		this.totalLarge = totalLarge;
	}
	public long getTotalSmall() {
		return totalSmall;
	}
	public void setTotalSmall(long totalSmall) {
		this.totalSmall = totalSmall;
	}
	public long getTotalSerpent() {
		return totalSerpent;
	}
	public void setTotalSerpent(long totalSerpent) {
		this.totalSerpent = totalSerpent;
	}
	public long getTotalPeace() {
		return totalPeace;
	}
	public void setTotalPeace(long totalPeace) {
		this.totalPeace = totalPeace;
	}
	public long getTotalTiger() {
		return totalTiger;
	}
	public void setTotalTiger(long totalTiger) {
		this.totalTiger = totalTiger;
	}
	public long getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(long totalRate) {
		this.totalRate = totalRate;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String[] getCount() {
		return count;
	}
	public void setCount(String[] count) {
		this.count = count;
	}
	public String[] getColor() {
		return color;
	}
	public void setColor(String[] color) {
		this.color = color;
	}
	public long getGoldBet() {
		return goldBet;
	}
	public void setGoldBet(long goldBet) {
		this.goldBet = goldBet;
	}
	public long getSingle() {
		return single;
	}
	public void setSingle(long single) {
		this.single = single;
	}
	public long getPairs() {
		return pairs;
	}
	public void setPairs(long pairs) {
		this.pairs = pairs;
	}
	public long getLarge() {
		return large;
	}
	public void setLarge(long large) {
		this.large = large;
	}
	public long getSmall() {
		return small;
	}
	public void setSmall(long small) {
		this.small = small;
	}
	public long getSerpent() {
		return serpent;
	}
	public void setSerpent(long serpent) {
		this.serpent = serpent;
	}
	public long getPeace() {
		return peace;
	}
	public void setPeace(long peace) {
		this.peace = peace;
	}
	public long getTiger() {
		return tiger;
	}
	public void setTiger(long tiger) {
		this.tiger = tiger;
	}
	public long getRate() {
		return rate;
	}
	public void setRate(long rate) {
		this.rate = rate;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
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

}
