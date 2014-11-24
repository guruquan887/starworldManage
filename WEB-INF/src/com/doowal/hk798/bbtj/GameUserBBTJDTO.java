package com.doowal.hk798.bbtj;

public class GameUserBBTJDTO {
	
	private int userID; //用户ID
	private int gameID; //游戏ID
	private String accounts; //用户名
	private String regAccounts;//昵称
	private long totalScore; //总银子
	private long totalXxScore;
	private long incomeScore;//输赢金额 
	private long rechargeMoney; //充值金额
	private long revenue; //个人税收
	private long giftScore; //赠送金额
	private long transferScore; //转账金额
	private long exchangeMoney; //兑换金额
	private long inRevenue; //个人得到下线提成税收
	private int tax; //个人提成比例
	private String typeName; //提成类型
	private int childrenID;//子孙ID
	private String collectNote;
	
	private int drawID;
	private int chairID;
	private long score;
	private long sxScore;
	private int userMedal;
	private int playTimeCount;
	private String insertTime;
	
	private String userName; //管理员
	private String clientIP;
	private String collectDate;
	private long curGold;
	private long addGold;
	private String reason;
	
	private String operAccounts;
	private String shareName;
	private String cardName;
	private String orderID;
	private String serialID;
	private double cardPrice; 
	private long cardGold;
	private long beforeGold;
	private int cardTotal;
	private double orderAmount;
	private double payAmount;
	private String ipAddress;
	private String applyDate;
	
	private String kindName;
	private String serverName;
	private String sourceAccounts;
	private String targetAccounts;
	private long sourceGold;
	private long sourceBank;
	private long targetGold;
	private long targetBank;
	private long swapScore;
	
	
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getSourceAccounts() {
		return sourceAccounts;
	}
	public void setSourceAccounts(String sourceAccounts) {
		this.sourceAccounts = sourceAccounts;
	}
	public String getTargetAccounts() {
		return targetAccounts;
	}
	public void setTargetAccounts(String targetAccounts) {
		this.targetAccounts = targetAccounts;
	}
	public long getSourceGold() {
		return sourceGold;
	}
	public void setSourceGold(long sourceGold) {
		this.sourceGold = sourceGold;
	}
	public long getSourceBank() {
		return sourceBank;
	}
	public void setSourceBank(long sourceBank) {
		this.sourceBank = sourceBank;
	}
	public long getTargetGold() {
		return targetGold;
	}
	public void setTargetGold(long targetGold) {
		this.targetGold = targetGold;
	}
	public long getTargetBank() {
		return targetBank;
	}
	public void setTargetBank(long targetBank) {
		this.targetBank = targetBank;
	}
	public long getSwapScore() {
		return swapScore;
	}
	public void setSwapScore(long swapScore) {
		this.swapScore = swapScore;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}
	public long getCurGold() {
		return curGold;
	}
	public void setCurGold(long curGold) {
		this.curGold = curGold;
	}
	public long getAddGold() {
		return addGold;
	}
	public void setAddGold(long addGold) {
		this.addGold = addGold;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getDrawID() {
		return drawID;
	}
	public void setDrawID(int drawID) {
		this.drawID = drawID;
	}
	public int getChairID() {
		return chairID;
	}
	public void setChairID(int chairID) {
		this.chairID = chairID;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public int getUserMedal() {
		return userMedal;
	}
	public void setUserMedal(int userMedal) {
		this.userMedal = userMedal;
	}
	public int getPlayTimeCount() {
		return playTimeCount;
	}
	public void setPlayTimeCount(int playTimeCount) {
		this.playTimeCount = playTimeCount;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public long getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(long totalScore) {
		this.totalScore = totalScore;
	}
	public String getRegAccounts() {
		return regAccounts;
	}
	public void setRegAccounts(String regAccounts) {
		this.regAccounts = regAccounts;
	}
	public long getIncomeScore() {
		return incomeScore;
	}
	public void setIncomeScore(long incomeScore) {
		this.incomeScore = incomeScore;
	}
	public long getRechargeMoney() {
		return rechargeMoney;
	}
	public void setRechargeMoney(long rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	public long getRevenue() {
		return revenue;
	}
	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}
	public long getGiftScore() {
		return giftScore;
	}
	public void setGiftScore(long giftScore) {
		this.giftScore = giftScore;
	}
	public long getTransferScore() {
		return transferScore;
	}
	public void setTransferScore(long transferScore) {
		this.transferScore = transferScore;
	}
	public long getExchangeMoney() {
		return exchangeMoney;
	}
	public void setExchangeMoney(long exchangeMoney) {
		this.exchangeMoney = exchangeMoney;
	}
	public long getInRevenue() {
		return inRevenue;
	}
	public void setInRevenue(long inRevenue) {
		this.inRevenue = inRevenue;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public String getOperAccounts() {
		return operAccounts;
	}
	public void setOperAccounts(String operAccounts) {
		this.operAccounts = operAccounts;
	}
	public String getShareName() {
		return shareName;
	}
	public void setShareName(String shareName) {
		this.shareName = shareName;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public double getCardPrice() {
		return cardPrice;
	}
	public void setCardPrice(double cardPrice) {
		this.cardPrice = cardPrice;
	}
	public long getCardGold() {
		return cardGold;
	}
	public void setCardGold(long cardGold) {
		this.cardGold = cardGold;
	}
	public long getBeforeGold() {
		return beforeGold;
	}
	public void setBeforeGold(long beforeGold) {
		this.beforeGold = beforeGold;
	}
	public int getCardTotal() {
		return cardTotal;
	}
	public void setCardTotal(int cardTotal) {
		this.cardTotal = cardTotal;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getSerialID() {
		return serialID;
	}
	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getChildrenID() {
		return childrenID;
	}
	public void setChildrenID(int childrenID) {
		this.childrenID = childrenID;
	}
	public long getSxScore() {
		return sxScore;
	}
	public void setSxScore(long sxScore) {
		this.sxScore = sxScore;
	}
	public long getTotalXxScore() {
		return totalXxScore;
	}
	public void setTotalXxScore(long totalXxScore) {
		this.totalXxScore = totalXxScore;
	}
	public String getCollectNote() {
		return collectNote;
	}
	public void setCollectNote(String collectNote) {
		this.collectNote = collectNote;
	}

}
