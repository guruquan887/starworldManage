package com.keno8.struts.dto;

public class GameYeepayDTO {
	
	private int id;
	private String dh;
	private String username;
	private String username1;
	private long r3_Amt;
	private String xdtime;
	private String r2_TrxId;
	private int state;
	private String stateName;
	private long jine;
	private String telphone;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername1() {
		return username1;
	}
	public void setUsername1(String username1) {
		this.username1 = username1;
	}

	public String getXdtime() {
		return xdtime;
	}
	public void setXdtime(String xdtime) {
		this.xdtime = xdtime;
	}
	public String getR2_TrxId() {
		return r2_TrxId;
	}
	public void setR2_TrxId(String trxId) {
		r2_TrxId = trxId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public long getR3_Amt() {
		return r3_Amt;
	}
	public void setR3_Amt(long amt) {
		r3_Amt = amt;
	}
	public long getJine() {
		return jine;
	}
	public void setJine(long jine) {
		this.jine = jine;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

}
