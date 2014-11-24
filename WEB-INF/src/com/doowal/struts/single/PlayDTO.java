package com.doowal.struts.single;

public class PlayDTO {
	
	private int	kindID;
	private String KindName;
	private int gameAreaID;
	private String gameAreaName;
	private int playTotalNumbers;
	
	public int getKindID() {
		return kindID;
	}
	public void setKindID(int kindID) {
		this.kindID = kindID;
	}
	public String getKindName() {
		return KindName;
	}
	public void setKindName(String kindName) {
		KindName = kindName;
	}
	public int getGameAreaID() {
		return gameAreaID;
	}
	public void setGameAreaID(int gameAreaID) {
		this.gameAreaID = gameAreaID;
	}
	public String getGameAreaName() {
		return gameAreaName;
	}
	public void setGameAreaName(String gameAreaName) {
		this.gameAreaName = gameAreaName;
	}
	public int getPlayTotalNumbers() {
		return playTotalNumbers;
	}
	public void setPlayTotalNumbers(int playTotalNumbers) {
		this.playTotalNumbers = playTotalNumbers;
	}
	
	
}
