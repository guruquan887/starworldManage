package com.keno8.struts.action.peroidnum;

public class GameResultDTO {
	private int id;
	private int gameKind;
	private String gameType;
	private String peroidnum;
	private String lastperoidnum;
	private String awardnum;
	private String seconds;
	private String gameUp;
	private int roomId;
	private double winGold;
	private int userId;
	private double pureGold;
	private String createTime;


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
	public int getGameKind() {
		return gameKind;
	}
	public void setGameKind(int gameKind) {
		this.gameKind = gameKind;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
	public String getAwardnum() {
		return awardnum;
	}
	public void setAwardnum(String awardnum) {
		this.awardnum = awardnum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPeroidnum() {
		return peroidnum;
	}
	public void setPeroidnum(String peroidnum) {
		this.peroidnum = peroidnum;
	}
	public String getLastperoidnum() {
		return lastperoidnum;
	}
	public void setLastperoidnum(String lastperoidnum) {
		this.lastperoidnum = lastperoidnum;
	}
	public String getSeconds() {
		return seconds;
	}
	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}
	public String getGameUp() {
		return gameUp;
	}
	public void setGameUp(String gameUp) {
		this.gameUp = gameUp;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
