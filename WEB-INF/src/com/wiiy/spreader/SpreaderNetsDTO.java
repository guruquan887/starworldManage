package com.wiiy.spreader;

public class SpreaderNetsDTO {

	private int userID;
	private String accounts;
	private int onLineTime;
	private long uponLineTimeGold;
	private float gameTax;
	private float ssfh;
	
	

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

}
