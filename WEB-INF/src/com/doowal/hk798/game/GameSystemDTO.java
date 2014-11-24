package com.doowal.hk798.game;

public class GameSystemDTO {
	
	private int dbInfoID;
	private String dbAddr;
	private int dbPort;
	private String dbUser;
	private String dbPassword;
	private String machineID;
	private String information;
	
	
	private int serverID;
	private String serverName;
	private String presentMember;
	private int maxDatePresent;
	private int maxPresent;
	private int cellPlayPresnet;
	private int cellPlayTime;
	private int startPlayTime;
	private int cellOnlinePresent;
	private int cellOnlineTime;
	private int startOnlineTime;
	private int isPlayPresent;
	private String isPlayPresentName;
	private int isOnlinePresent;
	private String isOnlinePresentName;
	private String collectDate;
	
	
	private int gameID;
	private String gameName;
	private String dataBaseAddr;
	private String dataBaseName;
	private String serverVersion;
	private String clientVersion;
	private String serverDLLName;
	private String clientExeName;
	private int suporType;
	
	
	private int typeID;
	private int joinID;
	private int sortID;
	private String typeName;
	private int nullity;
	private String nullityName;
	
	private int kindID;
	private String kindName;
	private String processName;
	private String gameruleURL;
	private String downloadURL;
	
	private int nodeID;
	private String nodeName;
	
	private int pageID;
	private int operateType;
	private String displayName;
	private String responseUrl;
	
	
	//道具管理
	private int id;
	private String name;
	private double cash;
	private long gold;
	private int disCount;
	private int issueArea;
	private int serviceArea;
	private long sendLoveLiness;
	private long recvLoveLiness;
	
	//系统消息
	private int messageType;
	private String messageTypeName;
	private String startTime;
	private String concludeTime;
	private int timeRate;
	private String messageString;
	private String serverRange;
	
	
	//房间管理
	private int tableCount;
	private int serverType;
	private int serverPort;
	private long cellScore;
	private int revenueRatio;
	private long serviceScore;
	private long restrictScore;
	private long minTableScore;
	private long minEnterScore;
	private long maxEnterScore;
	private int minEnterMember;
	private int maxEnterMember;
	private int maxPlayer;
	private int serverRule;
	private int distributeRule;
	private int minDistributeUser;
	private int maxDistributeUser;
	private int distributeTimeSpace;
	private int distributeDrawCount;
	private int distributeStartDelay;
	private int attachUserRight;
	private String serviceMachine;
	private String customRule;
	private String createDateTime;
	private String modifyDateTime;
	
	
	
	public int getSuporType() {
		return suporType;
	}
	public void setSuporType(int suporType) {
		this.suporType = suporType;
	}
	public String getServerDLLName() {
		return serverDLLName;
	}
	public void setServerDLLName(String serverDLLName) {
		this.serverDLLName = serverDLLName;
	}
	public String getClientExeName() {
		return clientExeName;
	}
	public void setClientExeName(String clientExeName) {
		this.clientExeName = clientExeName;
	}
	public int getTableCount() {
		return tableCount;
	}
	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;
	}
	public int getServerType() {
		return serverType;
	}
	public void setServerType(int serverType) {
		this.serverType = serverType;
	}
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public long getCellScore() {
		return cellScore;
	}
	public void setCellScore(long cellScore) {
		this.cellScore = cellScore;
	}
	public int getRevenueRatio() {
		return revenueRatio;
	}
	public void setRevenueRatio(int revenueRatio) {
		this.revenueRatio = revenueRatio;
	}
	public long getServiceScore() {
		return serviceScore;
	}
	public void setServiceScore(long serviceScore) {
		this.serviceScore = serviceScore;
	}
	public long getRestrictScore() {
		return restrictScore;
	}
	public void setRestrictScore(long restrictScore) {
		this.restrictScore = restrictScore;
	}
	public long getMinTableScore() {
		return minTableScore;
	}
	public void setMinTableScore(long minTableScore) {
		this.minTableScore = minTableScore;
	}
	public long getMinEnterScore() {
		return minEnterScore;
	}
	public void setMinEnterScore(long minEnterScore) {
		this.minEnterScore = minEnterScore;
	}
	public long getMaxEnterScore() {
		return maxEnterScore;
	}
	public void setMaxEnterScore(long maxEnterScore) {
		this.maxEnterScore = maxEnterScore;
	}
	public int getMinEnterMember() {
		return minEnterMember;
	}
	public void setMinEnterMember(int minEnterMember) {
		this.minEnterMember = minEnterMember;
	}
	public int getMaxEnterMember() {
		return maxEnterMember;
	}
	public void setMaxEnterMember(int maxEnterMember) {
		this.maxEnterMember = maxEnterMember;
	}
	public int getMaxPlayer() {
		return maxPlayer;
	}
	public void setMaxPlayer(int maxPlayer) {
		this.maxPlayer = maxPlayer;
	}
	public int getServerRule() {
		return serverRule;
	}
	public void setServerRule(int serverRule) {
		this.serverRule = serverRule;
	}
	public int getDistributeRule() {
		return distributeRule;
	}
	public void setDistributeRule(int distributeRule) {
		this.distributeRule = distributeRule;
	}
	public int getMinDistributeUser() {
		return minDistributeUser;
	}
	public void setMinDistributeUser(int minDistributeUser) {
		this.minDistributeUser = minDistributeUser;
	}
	public int getMaxDistributeUser() {
		return maxDistributeUser;
	}
	public void setMaxDistributeUser(int maxDistributeUser) {
		this.maxDistributeUser = maxDistributeUser;
	}
	public int getDistributeTimeSpace() {
		return distributeTimeSpace;
	}
	public void setDistributeTimeSpace(int distributeTimeSpace) {
		this.distributeTimeSpace = distributeTimeSpace;
	}
	public int getDistributeDrawCount() {
		return distributeDrawCount;
	}
	public void setDistributeDrawCount(int distributeDrawCount) {
		this.distributeDrawCount = distributeDrawCount;
	}
	public int getDistributeStartDelay() {
		return distributeStartDelay;
	}
	public void setDistributeStartDelay(int distributeStartDelay) {
		this.distributeStartDelay = distributeStartDelay;
	}
	public int getAttachUserRight() {
		return attachUserRight;
	}
	public void setAttachUserRight(int attachUserRight) {
		this.attachUserRight = attachUserRight;
	}
	public String getServiceMachine() {
		return serviceMachine;
	}
	public void setServiceMachine(String serviceMachine) {
		this.serviceMachine = serviceMachine;
	}
	public String getCustomRule() {
		return customRule;
	}
	public void setCustomRule(String customRule) {
		this.customRule = customRule;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getModifyDateTime() {
		return modifyDateTime;
	}
	public void setModifyDateTime(String modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public String getMessageTypeName() {
		return messageTypeName;
	}
	public void setMessageTypeName(String messageTypeName) {
		this.messageTypeName = messageTypeName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getConcludeTime() {
		return concludeTime;
	}
	public void setConcludeTime(String concludeTime) {
		this.concludeTime = concludeTime;
	}
	public int getTimeRate() {
		return timeRate;
	}
	public void setTimeRate(int timeRate) {
		this.timeRate = timeRate;
	}
	public String getMessageString() {
		return messageString;
	}
	public void setMessageString(String messageString) {
		this.messageString = messageString;
	}
	public String getServerRange() {
		return serverRange;
	}
	public void setServerRange(String serverRange) {
		this.serverRange = serverRange;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public long getGold() {
		return gold;
	}
	public void setGold(long gold) {
		this.gold = gold;
	}
	public int getDisCount() {
		return disCount;
	}
	public void setDisCount(int disCount) {
		this.disCount = disCount;
	}
	public int getIssueArea() {
		return issueArea;
	}
	public void setIssueArea(int issueArea) {
		this.issueArea = issueArea;
	}
	public int getServiceArea() {
		return serviceArea;
	}
	public void setServiceArea(int serviceArea) {
		this.serviceArea = serviceArea;
	}
	public long getSendLoveLiness() {
		return sendLoveLiness;
	}
	public void setSendLoveLiness(long sendLoveLiness) {
		this.sendLoveLiness = sendLoveLiness;
	}
	public long getRecvLoveLiness() {
		return recvLoveLiness;
	}
	public void setRecvLoveLiness(long recvLoveLiness) {
		this.recvLoveLiness = recvLoveLiness;
	}
	public int getPageID() {
		return pageID;
	}
	public void setPageID(int pageID) {
		this.pageID = pageID;
	}
	public int getOperateType() {
		return operateType;
	}
	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getResponseUrl() {
		return responseUrl;
	}
	public void setResponseUrl(String responseUrl) {
		this.responseUrl = responseUrl;
	}
	public int getNodeID() {
		return nodeID;
	}
	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public int getKindID() {
		return kindID;
	}
	public void setKindID(int kindID) {
		this.kindID = kindID;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getGameruleURL() {
		return gameruleURL;
	}
	public void setGameruleURL(String gameruleURL) {
		this.gameruleURL = gameruleURL;
	}
	public String getDownloadURL() {
		return downloadURL;
	}
	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}
	public String getNullityName() {
		return nullityName;
	}
	public void setNullityName(String nullityName) {
		this.nullityName = nullityName;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public int getJoinID() {
		return joinID;
	}
	public void setJoinID(int joinID) {
		this.joinID = joinID;
	}
	public int getSortID() {
		return sortID;
	}
	public void setSortID(int sortID) {
		this.sortID = sortID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getNullity() {
		return nullity;
	}
	public void setNullity(int nullity) {
		this.nullity = nullity;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getDataBaseAddr() {
		return dataBaseAddr;
	}
	public void setDataBaseAddr(String dataBaseAddr) {
		this.dataBaseAddr = dataBaseAddr;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public String getServerVersion() {
		return serverVersion;
	}
	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}
	public String getClientVersion() {
		return clientVersion;
	}
	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}
	public int getServerID() {
		return serverID;
	}
	public void setServerID(int serverID) {
		this.serverID = serverID;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getPresentMember() {
		return presentMember;
	}
	public void setPresentMember(String presentMember) {
		this.presentMember = presentMember;
	}
	public int getMaxDatePresent() {
		return maxDatePresent;
	}
	public void setMaxDatePresent(int maxDatePresent) {
		this.maxDatePresent = maxDatePresent;
	}
	public int getMaxPresent() {
		return maxPresent;
	}
	public void setMaxPresent(int maxPresent) {
		this.maxPresent = maxPresent;
	}
	public int getCellPlayPresnet() {
		return cellPlayPresnet;
	}
	public void setCellPlayPresnet(int cellPlayPresnet) {
		this.cellPlayPresnet = cellPlayPresnet;
	}
	public int getCellPlayTime() {
		return cellPlayTime;
	}
	public void setCellPlayTime(int cellPlayTime) {
		this.cellPlayTime = cellPlayTime;
	}
	public int getStartPlayTime() {
		return startPlayTime;
	}
	public void setStartPlayTime(int startPlayTime) {
		this.startPlayTime = startPlayTime;
	}
	public int getCellOnlinePresent() {
		return cellOnlinePresent;
	}
	public void setCellOnlinePresent(int cellOnlinePresent) {
		this.cellOnlinePresent = cellOnlinePresent;
	}
	public int getCellOnlineTime() {
		return cellOnlineTime;
	}
	public void setCellOnlineTime(int cellOnlineTime) {
		this.cellOnlineTime = cellOnlineTime;
	}
	public int getStartOnlineTime() {
		return startOnlineTime;
	}
	public void setStartOnlineTime(int startOnlineTime) {
		this.startOnlineTime = startOnlineTime;
	}
	public int getIsPlayPresent() {
		return isPlayPresent;
	}
	public void setIsPlayPresent(int isPlayPresent) {
		this.isPlayPresent = isPlayPresent;
	}
	public String getIsPlayPresentName() {
		return isPlayPresentName;
	}
	public void setIsPlayPresentName(String isPlayPresentName) {
		this.isPlayPresentName = isPlayPresentName;
	}
	public int getIsOnlinePresent() {
		return isOnlinePresent;
	}
	public void setIsOnlinePresent(int isOnlinePresent) {
		this.isOnlinePresent = isOnlinePresent;
	}
	public String getIsOnlinePresentName() {
		return isOnlinePresentName;
	}
	public void setIsOnlinePresentName(String isOnlinePresentName) {
		this.isOnlinePresentName = isOnlinePresentName;
	}
	public String getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}
	public int getDbInfoID() {
		return dbInfoID;
	}
	public void setDbInfoID(int dbInfoID) {
		this.dbInfoID = dbInfoID;
	}
	public String getDbAddr() {
		return dbAddr;
	}
	public void setDbAddr(String dbAddr) {
		this.dbAddr = dbAddr;
	}
	public int getDbPort() {
		return dbPort;
	}
	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}
	public String getDbUser() {
		return dbUser;
	}
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public String getMachineID() {
		return machineID;
	}
	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	

}
