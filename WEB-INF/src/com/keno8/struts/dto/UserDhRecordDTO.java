package com.keno8.struts.dto;

public class UserDhRecordDTO {

	private String dhTime;
	private int userID;
	private long csAmount;
	private long dhAmount;
	private String mallName;
	private String accounts;
	private String recipient;
	private String telphone;
	private String recAddress;
	private String postalCode;
	private String eMail;
	private String imagePath;
	private int state;
	private String stateName;
	private String express_ID;
	private String express_IDNO;
	private String express_Name;
	private String express_BeiZhu;

	private long dhSumAmount;
	private int dhSumCount;

	public String getDhTime() {
		return dhTime;
	}

	public void setDhTime(String dhTime) {
		this.dhTime = dhTime;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public long getCsAmount() {
		return csAmount;
	}

	public void setCsAmount(long csAmount) {
		this.csAmount = csAmount;
	}

	public long getDhAmount() {
		return dhAmount;
	}

	public void setDhAmount(long dhAmount) {
		this.dhAmount = dhAmount;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getRecAddress() {
		return recAddress;
	}

	public void setRecAddress(String recAddress) {
		this.recAddress = recAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String mail) {
		eMail = mail;
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

	public String getExpress_ID() {
		return express_ID;
	}

	public void setExpress_ID(String express_ID) {
		this.express_ID = express_ID;
	}

	public String getExpress_Name() {
		return express_Name;
	}

	public void setExpress_Name(String express_Name) {
		this.express_Name = express_Name;
	}

	public String getExpress_BeiZhu() {
		return express_BeiZhu;
	}

	public void setExpress_BeiZhu(String express_BeiZhu) {
		this.express_BeiZhu = express_BeiZhu;
	}

	public long getDhSumAmount() {
		return dhSumAmount;
	}

	public void setDhSumAmount(long dhSumAmount) {
		this.dhSumAmount = dhSumAmount;
	}

	public int getDhSumCount() {
		return dhSumCount;
	}

	public void setDhSumCount(int dhSumCount) {
		this.dhSumCount = dhSumCount;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getExpress_IDNO() {
		return express_IDNO;
	}

	public void setExpress_IDNO(String express_IDNO) {
		this.express_IDNO = express_IDNO;
	}

}
