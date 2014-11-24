package com.wiiy.spreader;

public class Spreader implements java.io.Serializable {

	private int UserId;
	private String accounts;
	private int number;
	private double income;// 收入
	private String spreaderName;
	private int totalOnLineTimeCount;// 下线总时间
	private double score;// 下线充值总额
	private double gxjinbi;
	private int onLineTime;
	private long uponLineTimeGold;
	private float gameTax;
	private float ssfh;
	private double lostSum;    //赤字
	private double xxLostSum;  //下线赤字
	private String createTime; //提成结算时间
	
	private String searchAccounts;

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getTotalOnLineTimeCount() {
		return totalOnLineTimeCount;
	}

	public void setTotalOnLineTimeCount(int totalOnLineTimeCount) {
		this.totalOnLineTimeCount = totalOnLineTimeCount;
	}



	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getGxjinbi() {
		return gxjinbi;
	}

	public void setGxjinbi(double gxjinbi) {
		this.gxjinbi = gxjinbi;
	}

	public int getOnLineTime() {
		return onLineTime;
	}

	public void setOnLineTime(int onLineTime) {
		this.onLineTime = onLineTime;
	}

	public long getUponLineTimeGold() {
		return uponLineTimeGold;
	}

	public void setUponLineTimeGold(long uponLineTimeGold) {
		this.uponLineTimeGold = uponLineTimeGold;
	}

	public float getGameTax() {
		return gameTax;
	}

	public void setGameTax(float gameTax) {
		this.gameTax = gameTax;
	}

	public float getSsfh() {
		return ssfh;
	}

	public void setSsfh(float ssfh) {
		this.ssfh = ssfh;
	}

	public String getSpreaderName() {
		return spreaderName;
	}

	public void setSpreaderName(String spreaderName) {
		this.spreaderName = spreaderName;
	}

	public String getSearchAccounts() {
		return searchAccounts;
	}

	public void setSearchAccounts(String searchAccounts) {
		this.searchAccounts = searchAccounts;
	}

	public double getLostSum() {
		return lostSum;
	}

	public void setLostSum(double lostSum) {
		this.lostSum = lostSum;
	}

	public double getXxLostSum() {
		return xxLostSum;
	}

	public void setXxLostSum(double xxLostSum) {
		this.xxLostSum = xxLostSum;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
