package com.doowal.struts.action.game;

public class GameListDTO {
	
	private int id;
	private String gameName;
	private String gameDes;
	private String gamephoto2;
	private String gameUrl;
	private int gameType;
	private int isRec;
	
	public int getIsRec() {
		return isRec;
	}
	public void setIsRec(int isRec) {
		this.isRec = isRec;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getGameDes() {
		return gameDes;
	}
	public void setGameDes(String gameDes) {
		this.gameDes = gameDes;
	}
	public String getGamephoto2() {
		return gamephoto2;
	}
	public void setGamephoto2(String gamephoto2) {
		this.gamephoto2 = gamephoto2;
	}
	public String getGameUrl() {
		return gameUrl;
	}
	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}
	public int getGameType() {
		return gameType;
	}
	public void setGameType(int gameType) {
		this.gameType = gameType;
	}
	
}
