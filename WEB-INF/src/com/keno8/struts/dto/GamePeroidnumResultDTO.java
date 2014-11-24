package com.keno8.struts.dto;

public class GamePeroidnumResultDTO {
	private int id;
	private int roomId;
	private String peroidnum;
	private String awardnum;
	private int single;
	private int serpent;
	private int large;
	private int peace;
	private int tiger;
	private int small;
	private int pairs;
	private String createTime;
	private String openTime;
	private int retroType;
	
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public int getRetroType() {
		return retroType;
	}
	public void setRetroType(int retroType) {
		this.retroType = retroType;
	}
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
	public String getPeroidnum() {
		return peroidnum;
	}
	public void setPeroidnum(String peroidnum) {
		this.peroidnum = peroidnum;
	}
	public int getSingle() {
		return single;
	}
	public void setSingle(int single) {
		this.single = single;
	}
	public int getSerpent() {
		return serpent;
	}
	public void setSerpent(int serpent) {
		this.serpent = serpent;
	}
	public int getLarge() {
		return large;
	}
	public void setLarge(int large) {
		this.large = large;
	}
	public int getPeace() {
		return peace;
	}
	public void setPeace(int peace) {
		this.peace = peace;
	}
	public int getTiger() {
		return tiger;
	}
	public void setTiger(int tiger) {
		this.tiger = tiger;
	}
	public int getSmall() {
		return small;
	}
	public void setSmall(int small) {
		this.small = small;
	}
	public int getPairs() {
		return pairs;
	}
	public void setPairs(int pairs) {
		this.pairs = pairs;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAwardnum() {
		return awardnum;
	}
	public void setAwardnum(String awardnum) {
		this.awardnum = awardnum;
	}
}
