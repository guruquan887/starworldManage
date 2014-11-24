package com.doowal.hk798.gameUser;
import java.util.List;
import java.util.ArrayList;

public class GameScoreInfoDTO {

	private int userID;
	private String accounts;
	private int isAndroid;
	private int drawID;
	private int chairID;
	private long score;
	private long grade;
	private long revenue;
	private long waste;
	private int usermedal;
	private int kindID;
	private int serverID;
	private int tableID;
	private int userCount;
	private int androidCount;
	private String startTime;
	private int playTimeCount;
	private String concludeTime;
	private String insertTime;
	private String kindName;
	private String serverName;
	private List<GameScoreInfoDTO> list = new ArrayList<GameScoreInfoDTO>();
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getDrawID() {
		return drawID;
	}
	public void setDrawID(int drawID) {
		this.drawID = drawID;
	}
	public int getChairID() {
		return chairID;
	}
	public void setChairID(int chairID) {
		this.chairID = chairID;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public long getGrade() {
		return grade;
	}
	public void setGrade(long grade) {
		this.grade = grade;
	}
	public long getRevenue() {
		return revenue;
	}
	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}
	public int getUsermedal() {
		return usermedal;
	}
	public void setUsermedal(int usermedal) {
		this.usermedal = usermedal;
	}
	public int getKindID() {
		return kindID;
	}
	public void setKindID(int kindID) {
		this.kindID = kindID;
	}
	public int getServerID() {
		return serverID;
	}
	public void setServerID(int serverID) {
		this.serverID = serverID;
	}
	public int getTableID() {
		return tableID;
	}
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public int getAndroidCount() {
		return androidCount;
	}
	public void setAndroidCount(int androidCount) {
		this.androidCount = androidCount;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getConcludeTime() {
		return concludeTime;
	}
	public void setConcludeTime(String concludeTime) {
		this.concludeTime = concludeTime;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public long getWaste() {
		return waste;
	}
	public void setWaste(long waste) {
		this.waste = waste;
	}
	public List<GameScoreInfoDTO> getList() {
		return list;
	}
	public void setList(List<GameScoreInfoDTO> list) {
		this.list = list;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public int getIsAndroid() {
		return isAndroid;
	}
	public void setIsAndroid(int isAndroid) {
		this.isAndroid = isAndroid;
	}
	public int getPlayTimeCount() {
		return playTimeCount;
	}
	public void setPlayTimeCount(int playTimeCount) {
		this.playTimeCount = playTimeCount;
	}


}
