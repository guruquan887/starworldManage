package com.doowal.struts.action.bbtj;

public class GameUserBBTJDTO {
	
	private int userID;
	private String accounts;
	private long totalBetScore;
    private long totalGoldScore;
    private long score;
    private long yesterdayGold;
    private String yesterdayGoldName;
    private String regAccounts;
    private float bjlZC;
    private float bjlYJ;
    
    //新程序
    private int lStatus;
    private int rStatus;
    private int proxyID;  //代理ID
    private int accountType; //级别
    private String accountTypeName; //级别

	private long tzScore; //投注金额
    private long winlostScore;  //输赢结果
    private long xmScore;  //洗码量
    private long xmyjScore;  //洗码佣金
    private double brokerage;  //佣金占成
    private long totalScore; //总金额
    private double winner; //输赢占成
    private double agentWinner; //上交输赢占成
    private long sjWinnerScore; //上交输赢金额
    private double taxrate; //税收占成
    private long revenue; //税收
    private long revenueScore;  //税收分成
    private long gxScore;  //贡献金额
    private long sjRevenueScore; //上交税收金额
    private double agentTaxrate; //上交税收占成
    private long sjTzScore;//上交投注金额
    private long sjXimaScore;//上交洗码量
    private String gsRadio;//公司获利比
    private String kindName;//游戏名称
    private int count;
    private int prevProxy;//上级ID
    private int kindId;//游戏ID号
    
    private int levelID;
    
    private long totalTzScore;
    private long totalWinloseScore;
    private long totalXmScore;
    private long totalXmYjScore;
    private long totalAllBetScore;
    private long totalSjWinnerScore;
    private long totalRevenue;
    
	public int getPrevProxy() {
		return prevProxy;
	}
	public void setPrevProxy(int prevProxy) {
		this.prevProxy = prevProxy;
	}
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public long getSjTzScore() {
		return sjTzScore;
	}
	public void setSjTzScore(long sjTzScore) {
		this.sjTzScore = sjTzScore;
	}
	public long getSjXimaScore() {
		return sjXimaScore;
	}
	public void setSjXimaScore(long sjXimaScore) {
		this.sjXimaScore = sjXimaScore;
	}
	public void setGsRadio(String gsRadio) {
		this.gsRadio = gsRadio;
	}

	public String getGsRadio() {
		return gsRadio;
	}
	public int getLStatus() {
		return lStatus;
	}
	public void setLStatus(int status) {
		lStatus = status;
	}
	public int getRStatus() {
		return rStatus;
	}
	public void setRStatus(int status) {
		rStatus = status;
	}
	public int getLevelID() {
		return levelID;
	}
	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}
	public int getProxyID() {
		return proxyID;
	}
	public void setProxyID(int proxyID) {
		this.proxyID = proxyID;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public String getAccountTypeName() {
		return accountTypeName;
	}
	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	public long getTzScore() {
		return tzScore;
	}
	public void setTzScore(long tzScore) {
		this.tzScore = tzScore;
	}
	public long getWinlostScore() {
		return winlostScore;
	}
	public void setWinlostScore(long winlostScore) {
		this.winlostScore = winlostScore;
	}
	public long getXmScore() {
		return xmScore;
	}
	public void setXmScore(long xmScore) {
		this.xmScore = xmScore;
	}
	public long getXmyjScore() {
		return xmyjScore;
	}
	public void setXmyjScore(long xmyjScore) {
		this.xmyjScore = xmyjScore;
	}
	public double getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(double brokerage) {
		this.brokerage = brokerage;
	}
	public long getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(long totalScore) {
		this.totalScore = totalScore;
	}
	public double getWinner() {
		return winner;
	}
	public void setWinner(double winner) {
		this.winner = winner;
	}
	public double getAgentWinner() {
		return agentWinner;
	}
	public void setAgentWinner(double agentWinner) {
		this.agentWinner = agentWinner;
	}
	public long getSjWinnerScore() {
		return sjWinnerScore;
	}
	public void setSjWinnerScore(long sjWinnerScore) {
		this.sjWinnerScore = sjWinnerScore;
	}
	public double getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(double taxrate) {
		this.taxrate = taxrate;
	}
	public long getRevenue() {
		return revenue;
	}
	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}
	public long getRevenueScore() {
		return revenueScore;
	}
	public void setRevenueScore(long revenueScore) {
		this.revenueScore = revenueScore;
	}
	public long getGxScore() {
		return gxScore;
	}
	public void setGxScore(long gxScore) {
		this.gxScore = gxScore;
	}
	public long getSjRevenueScore() {
		return sjRevenueScore;
	}
	public void setSjRevenueScore(long sjRevenueScore) {
		this.sjRevenueScore = sjRevenueScore;
	}
	public double getAgentTaxrate() {
		return agentTaxrate;
	}
	public void setAgentTaxrate(double agentTaxrate) {
		this.agentTaxrate = agentTaxrate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getTotalTzScore() {
		return totalTzScore;
	}
	public void setTotalTzScore(long totalTzScore) {
		this.totalTzScore = totalTzScore;
	}
	public long getTotalWinloseScore() {
		return totalWinloseScore;
	}
	public void setTotalWinloseScore(long totalWinloseScore) {
		this.totalWinloseScore = totalWinloseScore;
	}
	public long getTotalXmScore() {
		return totalXmScore;
	}
	public void setTotalXmScore(long totalXmScore) {
		this.totalXmScore = totalXmScore;
	}
	public long getTotalXmYjScore() {
		return totalXmYjScore;
	}
	public void setTotalXmYjScore(long totalXmYjScore) {
		this.totalXmYjScore = totalXmYjScore;
	}
	public long getTotalAllBetScore() {
		return totalAllBetScore;
	}
	public void setTotalAllBetScore(long totalAllBetScore) {
		this.totalAllBetScore = totalAllBetScore;
	}
	public long getTotalSjWinnerScore() {
		return totalSjWinnerScore;
	}
	public void setTotalSjWinnerScore(long totalSjWinnerScore) {
		this.totalSjWinnerScore = totalSjWinnerScore;
	}
	public long getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(long totalRevenue) {
		this.totalRevenue = totalRevenue;
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
	public long getTotalBetScore() {
		return totalBetScore;
	}
	public void setTotalBetScore(long totalBetScore) {
		this.totalBetScore = totalBetScore;
	}
	public long getTotalGoldScore() {
		return totalGoldScore;
	}
	public void setTotalGoldScore(long totalGoldScore) {
		this.totalGoldScore = totalGoldScore;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public long getYesterdayGold() {
		return yesterdayGold;
	}
	public void setYesterdayGold(long yesterdayGold) {
		this.yesterdayGold = yesterdayGold;
	}
	public String getYesterdayGoldName() {
		return yesterdayGoldName;
	}
	public void setYesterdayGoldName(String yesterdayGoldName) {
		this.yesterdayGoldName = yesterdayGoldName;
	}
	public String getRegAccounts() {
		return regAccounts;
	}
	public void setRegAccounts(String regAccounts) {
		this.regAccounts = regAccounts;
	}
	public float getBjlZC() {
		return bjlZC;
	}
	public void setBjlZC(float bjlZC) {
		this.bjlZC = bjlZC;
	}
	public float getBjlYJ() {
		return bjlYJ;
	}
	public void setBjlYJ(float bjlYJ) {
		this.bjlYJ = bjlYJ;
	}
    
}
