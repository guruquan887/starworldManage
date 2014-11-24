package com.keno8.struts.dto;

public class MonitorUserRecordDTO {
	
	private int userID;
	private int gameID;
	private String accounts;
	private long iscore;
	private long oscore;
	private long ioscore;
	private String recordTime;
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public long getIscore() {
		return iscore;
	}
	public void setIscore(long iscore) {
		this.iscore = iscore;
	}
	public long getOscore() {
		return oscore;
	}
	public void setOscore(long oscore) {
		this.oscore = oscore;
	}
	public long getIoscore() {
		return ioscore;
	}
	public void setIoscore(long ioscore) {
		this.ioscore = ioscore;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	
	

}
