package com.doowal.hk798.gameUser;

public class GameRoomInfo {
	
	private int serverID;
	private String serverRoom;
	private int kindID;
	private int userID;
	private int minPlayDraw;
	private int maxPlayDraw;
	private long minTakeScore;
	private long maxTakeScore;
	private int minReposeTime;
	private int maxReposeTime;
	private int serviceTime;
	private int serviceGender;
	private int nullity;
	private String createDate;
	private String androidNote;
	
	
	public int getServerID() {
		return serverID;
	}
	public void setServerID(int serverID) {
		this.serverID = serverID;
	}
	public String getServerRoom() {
		return serverRoom;
	}
	public void setServerRoom(String serverRoom) {
		this.serverRoom = serverRoom;
	}
	public int getKindID() {
		return kindID;
	}
	public void setKindID(int kindID) {
		this.kindID = kindID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getMinPlayDraw() {
		return minPlayDraw;
	}
	public void setMinPlayDraw(int minPlayDraw) {
		this.minPlayDraw = minPlayDraw;
	}
	public int getMaxPlayDraw() {
		return maxPlayDraw;
	}
	public void setMaxPlayDraw(int maxPlayDraw) {
		this.maxPlayDraw = maxPlayDraw;
	}
	public long getMinTakeScore() {
		return minTakeScore;
	}
	public void setMinTakeScore(long minTakeScore) {
		this.minTakeScore = minTakeScore;
	}
	public long getMaxTakeScore() {
		return maxTakeScore;
	}
	public void setMaxTakeScore(long maxTakeScore) {
		this.maxTakeScore = maxTakeScore;
	}
	public int getMinReposeTime() {
		return minReposeTime;
	}
	public void setMinReposeTime(int minReposeTime) {
		this.minReposeTime = minReposeTime;
	}
	public int getMaxReposeTime() {
		return maxReposeTime;
	}
	public void setMaxReposeTime(int maxReposeTime) {
		this.maxReposeTime = maxReposeTime;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	public int getServiceGender() {
		return serviceGender;
	}
	public void setServiceGender(int serviceGender) {
		this.serviceGender = serviceGender;
	}
	public int getNullity() {
		return nullity;
	}
	public void setNullity(int nullity) {
		this.nullity = nullity;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getAndroidNote() {
		return androidNote;
	}
	public void setAndroidNote(String androidNote) {
		this.androidNote = androidNote;
	}
	
}
