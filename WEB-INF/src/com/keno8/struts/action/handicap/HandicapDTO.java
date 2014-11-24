package com.keno8.struts.action.handicap;

public class HandicapDTO {
	
	private int id;
	private String peroidnum;
	private int roomId;
	private String roomName;
	private String openHandTime;
	private String openLotteryTime;
	private String closeHandTime;
	private int retroType;
	private int lotteryType;   //开奖类型
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getPeroidnum() {
		return peroidnum;
	}
	public void setPeroidnum(String peroidnum) {
		this.peroidnum = peroidnum;
	}
	public String getOpenHandTime() {
		return openHandTime;
	}
	public void setOpenHandTime(String openHandTime) {
		this.openHandTime = openHandTime;
	}
	public String getCloseHandTime() {
		return closeHandTime;
	}
	public void setCloseHandTime(String closeHandTime) {
		this.closeHandTime = closeHandTime;
	}
	public int getRetroType() {
		return retroType;
	}
	public void setRetroType(int retroType) {
		this.retroType = retroType;
	}
	public String getOpenLotteryTime() {
		return openLotteryTime;
	}
	public void setOpenLotteryTime(String openLotteryTime) {
		this.openLotteryTime = openLotteryTime;
	}
	public int getLotteryType() {
		return lotteryType;
	}
	public void setLotteryType(int lotteryType) {
		this.lotteryType = lotteryType;
	}
	
}
