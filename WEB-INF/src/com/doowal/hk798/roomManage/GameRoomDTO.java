package com.doowal.hk798.roomManage;

public class GameRoomDTO {
	
	private int roomID;
	private String roomName;
	private String androidUserScore;
	private int roomState;
	private String roomStateName;
	private String roomStateCss;
	private int maximum;
	private double tax;
	
	
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getAndroidUserScore() {
		return androidUserScore;
	}
	public void setAndroidUserScore(String androidUserScore) {
		this.androidUserScore = androidUserScore;
	}
	public int getRoomState() {
		return roomState;
	}
	public void setRoomState(int roomState) {
		this.roomState = roomState;
	}
	public String getRoomStateName() {
		return roomStateName;
	}
	public void setRoomStateName(String roomStateName) {
		this.roomStateName = roomStateName;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public String getRoomStateCss() {
		return roomStateCss;
	}
	public void setRoomStateCss(String roomStateCss) {
		this.roomStateCss = roomStateCss;
	}

}
