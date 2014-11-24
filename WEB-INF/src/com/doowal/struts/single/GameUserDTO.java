package com.doowal.struts.single;

public class GameUserDTO {

	private int userID;
	private String accounts;
	private String regAccounts;
	private String score; //真人银子
	private String insureScore;//钱庄银子
	private String totalScore;//总银子
	private String logonPass; //密文密码
	private String password;  //明文密码
	private long experience;
	private String spreaderID;
	private int userType;
	private String userTypeName;
	private int levelID;
	private double bjlZC;
	private double bjlYJ;
	private double tax;
	private int zhState; //账号状态 0--启用 1--停用
	private int state; //投注状态 0--启用 1--停用
	private String zhStateName;
	private String stateName;
	private String registerDate;
	private String registerIP;
	private String zhStateCss;
	private String stateCss;
	
	private double betRecord;
	private double goldRecord;
	private double totalBetGold;
	private double totalWinGold;
	private String spreaderScore;
	
	private int sxUserID;
	private String email;
	private String telphone;
	private String upName;
	private String lastLoginTime;
	private String revenue; //税收
	private String xxRevenue; //下线贡献税收
	private String jsRevenue; //已结算过的税收
	private int number; //人数
	
	//银子流向
	private double yeepayGold;
	
	
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
	public String getRegAccounts() {
		return regAccounts;
	}
	public void setRegAccounts(String regAccounts) {
		this.regAccounts = regAccounts;
	}
	public String getLogonPass() {
		return logonPass;
	}
	public void setLogonPass(String logonPass) {
		this.logonPass = logonPass;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getExperience() {
		return experience;
	}
	public void setExperience(long experience) {
		this.experience = experience;
	}
	public String getSpreaderID() {
		return spreaderID;
	}
	public void setSpreaderID(String spreaderID) {
		this.spreaderID = spreaderID;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getLevelID() {
		return levelID;
	}
	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}
	public double getBjlZC() {
		return bjlZC;
	}
	public void setBjlZC(double bjlZC) {
		this.bjlZC = bjlZC;
	}
	public double getBjlYJ() {
		return bjlYJ;
	}
	public void setBjlYJ(double bjlYJ) {
		this.bjlYJ = bjlYJ;
	}
	public int getZhState() {
		return zhState;
	}
	public void setZhState(int zhState) {
		this.zhState = zhState;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getRegisterIP() {
		return registerIP;
	}
	public void setRegisterIP(String registerIP) {
		this.registerIP = registerIP;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public String getZhStateName() {
		return zhStateName;
	}
	public void setZhStateName(String zhStateName) {
		this.zhStateName = zhStateName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getZhStateCss() {
		return zhStateCss;
	}
	public void setZhStateCss(String zhStateCss) {
		this.zhStateCss = zhStateCss;
	}
	public String getStateCss() {
		return stateCss;
	}
	public void setStateCss(String stateCss) {
		this.stateCss = stateCss;
	}
	public double getBetRecord() {
		return betRecord;
	}
	public void setBetRecord(double betRecord) {
		this.betRecord = betRecord;
	}
	public double getGoldRecord() {
		return goldRecord;
	}
	public void setGoldRecord(double goldRecord) {
		this.goldRecord = goldRecord;
	}
	public double getTotalBetGold() {
		return totalBetGold;
	}
	public void setTotalBetGold(double totalBetGold) {
		this.totalBetGold = totalBetGold;
	}
	public double getTotalWinGold() {
		return totalWinGold;
	}
	public void setTotalWinGold(double totalWinGold) {
		this.totalWinGold = totalWinGold;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public String getInsureScore() {
		return insureScore;
	}
	public void setInsureScore(String insureScore) {
		this.insureScore = insureScore;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public int getSxUserID() {
		return sxUserID;
	}
	public void setSxUserID(int sxUserID) {
		this.sxUserID = sxUserID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getUpName() {
		return upName;
	}
	public void setUpName(String upName) {
		this.upName = upName;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public double getYeepayGold() {
		return yeepayGold;
	}
	public void setYeepayGold(double yeepayGold) {
		this.yeepayGold = yeepayGold;
	}
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getXxRevenue() {
		return xxRevenue;
	}
	public void setXxRevenue(String xxRevenue) {
		this.xxRevenue = xxRevenue;
	}
	public String getJsRevenue() {
		return jsRevenue;
	}
	public void setJsRevenue(String jsRevenue) {
		this.jsRevenue = jsRevenue;
	}
	public String getSpreaderScore() {
		return spreaderScore;
	}
	public void setSpreaderScore(String spreaderScore) {
		this.spreaderScore = spreaderScore;
	}

	

}
