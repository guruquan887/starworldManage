package com.doowal.hk798.gameUser;

public class DrawAmountDTO {
	
	private int userID;
	private int gameID;
	private String accounts;
	private long amount;//提款金额
	private int type;//提款类别
	private String bank;//开户钱庄
	private String bankid;//开户钱庄卡类型
	private String bankaccount;//开户钱庄账号
	private String bankaddress;//开户行地址
	private String bankholdername;//持卡人姓名
	private String phone;
	private String email;
	private String drawdate;//申请日期
	private int applytype;//申请状态
	private String applystate;
	private String express_ID;
	private String typeName;
	private int cancelType;
	private double realRMB;
	
	
	
	public double getRealRMB() {
		return realRMB;
	}
	public void setRealRMB(double realRMB) {
		this.realRMB = realRMB;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}
	public String getBankaddress() {
		return bankaddress;
	}
	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}
	public String getBankholdername() {
		return bankholdername;
	}
	public void setBankholdername(String bankholdername) {
		this.bankholdername = bankholdername;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDrawdate() {
		return drawdate;
	}
	public void setDrawdate(String drawdate) {
		this.drawdate = drawdate;
	}
	public int getApplytype() {
		return applytype;
	}
	public void setApplytype(int applytype) {
		this.applytype = applytype;
	}
	public String getApplystate() {
		return applystate;
	}
	public void setApplystate(String applystate) {
		this.applystate = applystate;
	}
	public String getExpress_ID() {
		return express_ID;
	}
	public void setExpress_ID(String express_ID) {
		this.express_ID = express_ID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getCancelType() {
		return cancelType;
	}
	public void setCancelType(int cancelType) {
		this.cancelType = cancelType;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
}
