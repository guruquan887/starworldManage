package com.doowal.struts.single;

public class NoteDTO {
	
	private int userID;
	private String peroidnum;
	private int roomId;
	private String roomName;
	private long totalGoldBet;
	private String betGameArea; //下注区
	private int countNumber; //期投注人数
	private String createTime;
	private String createTime1;
	
	//个人的投注记录
	private String accounts;
	private long goldBet;
	private long single;
	private long pairs;
	private long large;
	private long small;
	private long serpent;
	private long peace;
	private long tiger;
	private long rate;
	//个人的投注记录字段
	private String [] count;
	
	private String [] color;
	private String sameNameColor;
	
	//新百家乐即时注单
	private int bureauID; //局数
	private int status;  //状态
	private String statusName;
	private int leaveSeconds;
	private String leaveDataTime;
	private int tableID;//桌号
	private String serverName; //房间名称
	private String cardData;  //开牌结果
	private String winAreas; //赢的区域
	private String prevWinAreas;  //上局赢区域
	private long score; //下注金额
	private int betCount;
	private long zScore;
	private long xScore;
	
	
	public long getzScore() {
		return zScore;
	}
	public void setzScore(long zScore) {
		this.zScore = zScore;
	}
	public long getxScore() {
		return xScore;
	}
	public void setxScore(long xScore) {
		this.xScore = xScore;
	}
	public int getBetCount() {
		return betCount;
	}
	public void setBetCount(int betCount) {
		this.betCount = betCount;
	}
	public int getBureauID() {
		return bureauID;
	}
	public void setBureauID(int bureauID) {
		this.bureauID = bureauID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public int getLeaveSeconds() {
		return leaveSeconds;
	}
	public void setLeaveSeconds(int leaveSeconds) {
		this.leaveSeconds = leaveSeconds;
	}
	public String getLeaveDataTime() {
		return leaveDataTime;
	}
	public void setLeaveDataTime(String leaveDataTime) {
		this.leaveDataTime = leaveDataTime;
	}
	public int getTableID() {
		return tableID;
	}
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
	public String getCardData() {
		return cardData;
	}
	public void setCardData(String cardData) {
		this.cardData = cardData;
	}
	public String getWinAreas() {
		return winAreas;
	}
	public void setWinAreas(String winAreas) {
		this.winAreas = winAreas;
	}
	public String getPrevWinAreas() {
		return prevWinAreas;
	}
	public void setPrevWinAreas(String prevWinAreas) {
		this.prevWinAreas = prevWinAreas;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public String getSameNameColor() {
		return sameNameColor;
	}
	public void setSameNameColor(String sameNameColor) {
		this.sameNameColor = sameNameColor;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPeroidnum() {
		return peroidnum;
	}
	public void setPeroidnum(String peroidnum) {
		this.peroidnum = peroidnum;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public long getTotalGoldBet() {
		return totalGoldBet;
	}
	public void setTotalGoldBet(long totalGoldBet) {
		this.totalGoldBet = totalGoldBet;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String[] getCount() {
		return count;
	}
	public void setCount(String[] count) {
		this.count = count;
	}
	public String[] getColor() {
		return color;
	}
	public void setColor(String[] color) {
		this.color = color;
	}
	public long getGoldBet() {
		return goldBet;
	}
	public void setGoldBet(long goldBet) {
		this.goldBet = goldBet;
	}
	public long getSingle() {
		return single;
	}
	public void setSingle(long single) {
		this.single = single;
	}
	public long getPairs() {
		return pairs;
	}
	public void setPairs(long pairs) {
		this.pairs = pairs;
	}
	public long getLarge() {
		return large;
	}
	public void setLarge(long large) {
		this.large = large;
	}
	public long getSmall() {
		return small;
	}
	public void setSmall(long small) {
		this.small = small;
	}
	public long getSerpent() {
		return serpent;
	}
	public void setSerpent(long serpent) {
		this.serpent = serpent;
	}
	public long getPeace() {
		return peace;
	}
	public void setPeace(long peace) {
		this.peace = peace;
	}
	public long getTiger() {
		return tiger;
	}
	public void setTiger(long tiger) {
		this.tiger = tiger;
	}
	public long getRate() {
		return rate;
	}
	public void setRate(long rate) {
		this.rate = rate;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateTime1() {
		return createTime1;
	}
	public void setCreateTime1(String createTime1) {
		this.createTime1 = createTime1;
	}
	public int getCountNumber() {
		return countNumber;
	}
	public void setCountNumber(int countNumber) {
		this.countNumber = countNumber;
	}
	public String getBetGameArea() {
		return betGameArea;
	}
	public void setBetGameArea(String betGameArea) {
		this.betGameArea = betGameArea;
	}

}
