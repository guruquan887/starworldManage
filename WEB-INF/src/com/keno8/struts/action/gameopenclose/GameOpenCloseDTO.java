package com.keno8.struts.action.gameopenclose;

public class GameOpenCloseDTO {
	
	private int gameID;
	private String displayName;
	private int gameopenclose;
	private String gameopencloseName;
	
	private int handicap;
	private String handicapName;
	
	private String dataInter;
	
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public int getGameopenclose() {
		return gameopenclose;
	}
	public void setGameopenclose(int gameopenclose) {
		this.gameopenclose = gameopenclose;
	}
	public String getGameopencloseName() {
		return gameopencloseName;
	}
	public void setGameopencloseName(String gameopencloseName) {
		this.gameopencloseName = gameopencloseName;
	}
	public int getHandicap() {
		return handicap;
	}
	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}
	public String getHandicapName() {
		return handicapName;
	}
	public void setHandicapName(String handicapName) {
		this.handicapName = handicapName;
	}
	public String getDataInter() {
		return dataInter;
	}
	public void setDataInter(String dataInter) {
		this.dataInter = dataInter;
	}
	
}
