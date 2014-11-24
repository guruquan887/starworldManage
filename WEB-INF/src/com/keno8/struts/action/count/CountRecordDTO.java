package com.keno8.struts.action.count;

public class CountRecordDTO {   
	
	private int id;    
	private int userID;
	private long totalGoldBet;  //统计总银子
	private String peroidnum;   //期号
	private int roomId;         //游戏ID
	private String createTime;  //用户下注的时间
	private String roomName;
	private String displayName;
	
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
	public long getTotalGoldBet() {
		return totalGoldBet;
	}
	public void setTotalGoldBet(long totalGoldBet) {
		this.totalGoldBet = totalGoldBet;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
}
