package com.keno8.struts.action.gameUser.mark;

public class GameUserBBTJDTO {
	
	private int userID;
	private String accounts;
	private String nickName;
	private double score;               //剩余额度
	private double totalGoldXZ;         //下注总额
	private double winlostMoney;        //输赢
	private double qk;                  //取款
	private double ck;                  //存款
	
	private String peroidnum;   //期号
	private int roomId;         //游戏ID
	private String createTime;  //用户下注的时间 日期
	private String roomName;
	private String displayName;
    private double rebate;       //返水
	
    private long balance;      //差额   差额=存款总额-取款总额+输赢总额+返水金额-现有金额
    private double yesterdayGold;
    private String yesterdayGoldName;
    

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getTotalGoldXZ() {
		return totalGoldXZ;
	}

	public void setTotalGoldXZ(double totalGoldXZ) {
		this.totalGoldXZ = totalGoldXZ;
	}

	public double getWinlostMoney() {
		return winlostMoney;
	}

	public void setWinlostMoney(double winlostMoney) {
		this.winlostMoney = winlostMoney;
	}

	public double getQk() {
		return qk;
	}

	public void setQk(double qk) {
		this.qk = qk;
	}

	public double getCk() {
		return ck;
	}

	public void setCk(double ck) {
		this.ck = ck;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public double getRebate() {
		return rebate;
	}

	public void setRebate(double rebate) {
		this.rebate = rebate;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public double getYesterdayGold() {
		return yesterdayGold;
	}

	public void setYesterdayGold(double yesterdayGold) {
		this.yesterdayGold = yesterdayGold;
	}

	public String getYesterdayGoldName() {
		return yesterdayGoldName;
	}

	public void setYesterdayGoldName(String yesterdayGoldName) {
		this.yesterdayGoldName = yesterdayGoldName;
	}
    

}
