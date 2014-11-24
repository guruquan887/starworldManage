package com.doowal.hk798.gameUser;

public class GameUserDTO {

	
	//新程序
	private int registerGrantScore;
	private int playTimeGrantScore;
	private double fillGrantRate;
	private double balanceRate;
	private int minBalanceScore;
	private int type;
	private String collectNote;
	
	//2014新程序
	private int proxyID;
	private String proxyAccounts;
	private String nickName;
	private int prevProxy;
	private int isFreeze;
	private double brokerage;//佣金
	private double taxRate;//税收提成
	private double winner; //输赢股份
	private String createTime;
	private long gold;
	private String bankPass;
	
	private String ssAccounts;
	private int userID;
	private int kindID;
	private String accounts;
	private String spreaderAccounts;
	private String regAccounts;
	private String realName;
	private String compellation;
	private String safeEmail;
	private String question1;
	private int specialRight;
	private String response1;
	private String passportID;
	private String registerMobile;
	private int masterOrder;
	private int faceID;
	private long getScore;
	private long getInsureScore;
	private String score; //真人银子
	private String insureScore;//钱庄银子
	private String totalScore;//总银子
	private String jifen;//积分
	private String logonPass; //密文密码
	private String password;  //明文密码
	private String password1; //钱庄密码
	private String insurePass; //钱庄密文密码
	private long experience;
	private String spreaderID;
	private int gameID;
	private int userType;
	private String userTypeName;
	private int levelID;
	private double bjlZC;
	private double bjlYJ;
	private double tax;
	private int zhState; //账号状态 0--启用 1--停用
	private int state; //投注状态 0--启用 1--停用
	private long xxgold;//限额
	private String zhStateName;
	private String stateName;
	private String registerDate;
	private String registerIP;
	private String lastLogonIP;//最后登录IP
	private String zhStateCss;
	private String stateCss;
	private int interType;//内部账号还是普通用户
	private String bankAccounts;
	private int winRate;//个人胜率
	private int present;  //已兑换的魅力总和
	private int loveLiness; //魅力
	private int userMedal;  //金牌
	private int totalCount; //总局数
	private int isAndroid;  //是否机器人
	
	private int memberOrder; //会员级别
	private String serverTime;
	
	private double betRecord;
	private double goldRecord;
	private double totalBetGold;
	private double totalWinGold;
	private double spreaderScore;
	
	private String kindName;
	private String serverRoom; //房间名称
	private int winCount; //赢局
	private int loseCount; //输局
	private int drawCount; //逃局
	private int fleeCount; //平局
	private String jqType; //机器人类型
	private String createDate;//创建时间
	private int allLogonTimes; //登录次数
	
	private int sxUserID;
	private String email;
	private String telphone;
	private String c_PROTECTQUES;//密码问题
	private String c_PROTECTANSW;//密码答案
	private String C_IDNO;//身份证号
	private String upName;
	private String lastLoginTime;
	private String lastLoginIP;
	private int onLineTimeCount;//在线时间/分钟
	private int playTimeCount;//游戏时间
	private String underWrite;//个性签名
	private String revenue; //税收
	private String xxRevenue; //下线贡献税收
	private String jsRevenue; //已结算过的税收
	private int number; //代理人数
	private int mmnumber; //会员人数
	private String memberName;//会员级别名称
	private String onLineName;//在线状态
	private String bgcss;//状态背景色
	private int gender;//性别
	private int masterRight; //管理员
	private int userRight; //比赛账号
	private int groupID;//组别ID
	private int limit;
	private String rechargeMoney; //充值银子
	private String exchangeMoney; //兑换银子
	private String chaerMoney;  //可兑换银子
	private String transferRevenue; //转账税收
	private int gameLogonTimes; //游戏登陆次数
	private String serviceTime;
	//银子流向
	private double yeepayGold;
	
	private int giftMinute;
	private long giftScore;
	private int giftMinuteAgent;
	private long giftScoreAgent;
	//活动日志
	private String operateDetails;
	private String operateIP;
	private String operateTime;
	
	private String css;
	
	private String accountsType;
	private String accountsCss;
	
	private long totalAndroidScore;
	private int totalAndroidNum;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCollectNote() {
		return collectNote;
	}
	public void setCollectNote(String collectNote) {
		this.collectNote = collectNote;
	}
	public int getRegisterGrantScore() {
		return registerGrantScore;
	}
	public void setRegisterGrantScore(int registerGrantScore) {
		this.registerGrantScore = registerGrantScore;
	}
	public int getPlayTimeGrantScore() {
		return playTimeGrantScore;
	}
	public void setPlayTimeGrantScore(int playTimeGrantScore) {
		this.playTimeGrantScore = playTimeGrantScore;
	}
	public double getFillGrantRate() {
		return fillGrantRate;
	}
	public void setFillGrantRate(double fillGrantRate) {
		this.fillGrantRate = fillGrantRate;
	}
	public double getBalanceRate() {
		return balanceRate;
	}
	public void setBalanceRate(double balanceRate) {
		this.balanceRate = balanceRate;
	}
	public int getMinBalanceScore() {
		return minBalanceScore;
	}
	public void setMinBalanceScore(int minBalanceScore) {
		this.minBalanceScore = minBalanceScore;
	}
	public long getTotalAndroidScore() {
		return totalAndroidScore;
	}
	public void setTotalAndroidScore(long totalAndroidScore) {
		this.totalAndroidScore = totalAndroidScore;
	}
	public int getTotalAndroidNum() {
		return totalAndroidNum;
	}
	public void setTotalAndroidNum(int totalAndroidNum) {
		this.totalAndroidNum = totalAndroidNum;
	}
	public String getBankPass() {
		return bankPass;
	}
	public void setBankPass(String bankPass) {
		this.bankPass = bankPass;
	}
	public String getSsAccounts() {
		return ssAccounts;
	}
	public void setSsAccounts(String ssAccounts) {
		this.ssAccounts = ssAccounts;
	}
	public int getProxyID() {
		return proxyID;
	}
	public void setProxyID(int proxyID) {
		this.proxyID = proxyID;
	}
	public String getProxyAccounts() {
		return proxyAccounts;
	}
	public void setProxyAccounts(String proxyAccounts) {
		this.proxyAccounts = proxyAccounts;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getPrevProxy() {
		return prevProxy;
	}
	public void setPrevProxy(int prevProxy) {
		this.prevProxy = prevProxy;
	}
	public int getIsFreeze() {
		return isFreeze;
	}
	public void setIsFreeze(int isFreeze) {
		this.isFreeze = isFreeze;
	}
	public double getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(double brokerage) {
		this.brokerage = brokerage;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	public double getWinner() {
		return winner;
	}
	public void setWinner(double winner) {
		this.winner = winner;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public long getGold() {
		return gold;
	}
	public void setGold(long gold) {
		this.gold = gold;
	}
	public String getAccountsCss() {
		return accountsCss;
	}
	public void setAccountsCss(String accountsCss) {
		this.accountsCss = accountsCss;
	}
	public String getAccountsType() {
		return accountsType;
	}
	public void setAccountsType(String accountsType) {
		this.accountsType = accountsType;
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
	public double getSpreaderScore() {
		return spreaderScore;
	}
	public void setSpreaderScore(double spreaderScore) {
		this.spreaderScore = spreaderScore;
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
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getInsurePass() {
		return insurePass;
	}
	public void setInsurePass(String insurePass) {
		this.insurePass = insurePass;
	}
	public int getOnLineTimeCount() {
		return onLineTimeCount;
	}
	public void setOnLineTimeCount(int onLineTimeCount) {
		this.onLineTimeCount = onLineTimeCount;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getJifen() {
		return jifen;
	}
	public void setJifen(String jifen) {
		this.jifen = jifen;
	}
	public String getLastLogonIP() {
		return lastLogonIP;
	}
	public void setLastLogonIP(String lastLogonIP) {
		this.lastLogonIP = lastLogonIP;
	}
	public String getOnLineName() {
		return onLineName;
	}
	public void setOnLineName(String onLineName) {
		this.onLineName = onLineName;
	}
	public String getBgcss() {
		return bgcss;
	}
	public void setBgcss(String bgcss) {
		this.bgcss = bgcss;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getC_PROTECTQUES() {
		return c_PROTECTQUES;
	}
	public void setC_PROTECTQUES(String c_protectques) {
		c_PROTECTQUES = c_protectques;
	}
	public String getC_PROTECTANSW() {
		return c_PROTECTANSW;
	}
	public void setC_PROTECTANSW(String c_protectansw) {
		c_PROTECTANSW = c_protectansw;
	}
	public String getC_IDNO() {
		return C_IDNO;
	}
	public void setC_IDNO(String c_idno) {
		C_IDNO = c_idno;
	}
	public int getMasterRight() {
		return masterRight;
	}
	public void setMasterRight(int masterRight) {
		this.masterRight = masterRight;
	}
	public int getUserRight() {
		return userRight;
	}
	public void setUserRight(int userRight) {
		this.userRight = userRight;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getRechargeMoney() {
		return rechargeMoney;
	}
	public void setRechargeMoney(String rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	public String getExchangeMoney() {
		return exchangeMoney;
	}
	public void setExchangeMoney(String exchangeMoney) {
		this.exchangeMoney = exchangeMoney;
	}
	public String getChaerMoney() {
		return chaerMoney;
	}
	public void setChaerMoney(String chaerMoney) {
		this.chaerMoney = chaerMoney;
	}
	public String getUnderWrite() {
		return underWrite;
	}
	public void setUnderWrite(String underWrite) {
		this.underWrite = underWrite;
	}
	public int getMemberOrder() {
		return memberOrder;
	}
	public void setMemberOrder(int memberOrder) {
		this.memberOrder = memberOrder;
	}
	public String getOperateDetails() {
		return operateDetails;
	}
	public void setOperateDetails(String operateDetails) {
		this.operateDetails = operateDetails;
	}
	public String getOperateIP() {
		return operateIP;
	}
	public void setOperateIP(String operateIP) {
		this.operateIP = operateIP;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getInterType() {
		return interType;
	}
	public void setInterType(int interType) {
		this.interType = interType;
	}
	public String getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(String bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public int getPlayTimeCount() {
		return playTimeCount;
	}
	public void setPlayTimeCount(int playTimeCount) {
		this.playTimeCount = playTimeCount;
	}
	public int getWinRate() {
		return winRate;
	}
	public void setWinRate(int winRate) {
		this.winRate = winRate;
	}
	public String getTransferRevenue() {
		return transferRevenue;
	}
	public void setTransferRevenue(String transferRevenue) {
		this.transferRevenue = transferRevenue;
	}
	public long getXxgold() {
		return xxgold;
	}
	public void setXxgold(long xxgold) {
		this.xxgold = xxgold;
	}
	public int getMmnumber() {
		return mmnumber;
	}
	public void setMmnumber(int mmnumber) {
		this.mmnumber = mmnumber;
	}
	public int getPresent() {
		return present;
	}
	public void setPresent(int present) {
		this.present = present;
	}
	public int getLoveLiness() {
		return loveLiness;
	}
	public void setLoveLiness(int loveLiness) {
		this.loveLiness = loveLiness;
	}
	public int getGameLogonTimes() {
		return gameLogonTimes;
	}
	public void setGameLogonTimes(int gameLogonTimes) {
		this.gameLogonTimes = gameLogonTimes;
	}
	public String getServerRoom() {
		return serverRoom;
	}
	public void setServerRoom(String serverRoom) {
		this.serverRoom = serverRoom;
	}
	public int getWinCount() {
		return winCount;
	}
	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	public int getLoseCount() {
		return loseCount;
	}
	public void setLoseCount(int loseCount) {
		this.loseCount = loseCount;
	}
	public String getJqType() {
		return jqType;
	}
	public void setJqType(String jqType) {
		this.jqType = jqType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getUserMedal() {
		return userMedal;
	}
	public void setUserMedal(int userMedal) {
		this.userMedal = userMedal;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public int getDrawCount() {
		return drawCount;
	}
	public void setDrawCount(int drawCount) {
		this.drawCount = drawCount;
	}
	public int getFleeCount() {
		return fleeCount;
	}
	public void setFleeCount(int fleeCount) {
		this.fleeCount = fleeCount;
	}
	public int getAllLogonTimes() {
		return allLogonTimes;
	}
	public void setAllLogonTimes(int allLogonTimes) {
		this.allLogonTimes = allLogonTimes;
	}
	public String getLastLoginIP() {
		return lastLoginIP;
	}
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}
	public int getIsAndroid() {
		return isAndroid;
	}
	public void setIsAndroid(int isAndroid) {
		this.isAndroid = isAndroid;
	}
	public String getCompellation() {
		return compellation;
	}
	public void setCompellation(String compellation) {
		this.compellation = compellation;
	}
	public String getSafeEmail() {
		return safeEmail;
	}
	public void setSafeEmail(String safeEmail) {
		this.safeEmail = safeEmail;
	}
	public String getQuestion1() {
		return question1;
	}
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}
	public String getResponse1() {
		return response1;
	}
	public void setResponse1(String response1) {
		this.response1 = response1;
	}
	public String getPassportID() {
		return passportID;
	}
	public void setPassportID(String passportID) {
		this.passportID = passportID;
	}
	public String getRegisterMobile() {
		return registerMobile;
	}
	public void setRegisterMobile(String registerMobile) {
		this.registerMobile = registerMobile;
	}
	public int getFaceID() {
		return faceID;
	}
	public void setFaceID(int faceID) {
		this.faceID = faceID;
	}
	public int getMasterOrder() {
		return masterOrder;
	}
	public void setMasterOrder(int masterOrder) {
		this.masterOrder = masterOrder;
	}
	public int getSpecialRight() {
		return specialRight;
	}
	public void setSpecialRight(int specialRight) {
		this.specialRight = specialRight;
	}
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getServerTime() {
		return serverTime;
	}
	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}
	public int getKindID() {
		return kindID;
	}
	public void setKindID(int kindID) {
		this.kindID = kindID;
	}
	public long getGetScore() {
		return getScore;
	}
	public void setGetScore(long getScore) {
		this.getScore = getScore;
	}
	public long getGetInsureScore() {
		return getInsureScore;
	}
	public void setGetInsureScore(long getInsureScore) {
		this.getInsureScore = getInsureScore;
	}
	public String getSpreaderAccounts() {
		return spreaderAccounts;
	}
	public void setSpreaderAccounts(String spreaderAccounts) {
		this.spreaderAccounts = spreaderAccounts;
	}
	public int getGiftMinute() {
		return giftMinute;
	}
	public void setGiftMinute(int giftMinute) {
		this.giftMinute = giftMinute;
	}
	public long getGiftScore() {
		return giftScore;
	}
	public void setGiftScore(long giftScore) {
		this.giftScore = giftScore;
	}
	public int getGiftMinuteAgent() {
		return giftMinuteAgent;
	}
	public void setGiftMinuteAgent(int giftMinuteAgent) {
		this.giftMinuteAgent = giftMinuteAgent;
	}
	public long getGiftScoreAgent() {
		return giftScoreAgent;
	}
	public void setGiftScoreAgent(long giftScoreAgent) {
		this.giftScoreAgent = giftScoreAgent;
	}

}
