package com.keno8.struts.action.peroidnum;

public class GamePickRecordDTO {
	private int id;
	private int userId;
	private int roomId;
	private String peroidnum;
	private String totalGoldBet;
	private int type;
	private String hundred;
	private String ten;
	private String one;
	private int jiesuan;
	private String firstMoney;
	private String lastMoney;
	private String pureMoney;
	private String createTime;
	private int multiple;
	

	public int getMultiple() {
		return multiple;
	}
	public void setMultiple(int multiple) {
		this.multiple = multiple;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPureMoney() {
		return pureMoney;
	}
	public void setPureMoney(String pureMoney) {
		this.pureMoney = pureMoney;
	}
	public int getId() {
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getTotalGoldBet() {
		return totalGoldBet;
	}
	public void setTotalGoldBet(String totalGoldBet) {
		this.totalGoldBet = totalGoldBet;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getHundred() {
		return hundred;
	}
	public void setHundred(String hundred) {
		this.hundred = hundred;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}
	public int getJiesuan() {
		return jiesuan;
	}
	public void setJiesuan(int jiesuan) {
		this.jiesuan = jiesuan;
	}

	public String getLastMoney() {
		return lastMoney;
	}
	public void setLastMoney(String lastMoney) {
		this.lastMoney = lastMoney;
	}
	public String getFirstMoney() {
		return firstMoney;
	}
	public void setFirstMoney(String firstMoney) {
		this.firstMoney = firstMoney;
	}
}
