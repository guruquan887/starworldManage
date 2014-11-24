package com.doowal.hk798.admin;

public class NetCSDTO {

	private String statusName;
	private long statusValue;
	
	
	private int siteID;
	private String siteName;
	private String copyRight;
	private String wwwName;
	private String adsMsg;
	private String gameMsg;
	
	
	public String getAdsMsg() {
		return adsMsg;
	}
	public void setAdsMsg(String adsMsg) {
		this.adsMsg = adsMsg;
	}
	public String getGameMsg() {
		return gameMsg;
	}
	public void setGameMsg(String gameMsg) {
		this.gameMsg = gameMsg;
	}
	public String getWwwName() {
		return wwwName;
	}
	public void setWwwName(String wwwName) {
		this.wwwName = wwwName;
	}
	public int getSiteID() {
		return siteID;
	}
	public void setSiteID(int siteID) {
		this.siteID = siteID;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getCopyRight() {
		return copyRight;
	}
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public long getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(long statusValue) {
		this.statusValue = statusValue;
	}
	public String getStatusString() {
		return statusString;
	}
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	public String getStatusTip() {
		return statusTip;
	}
	public void setStatusTip(String statusTip) {
		this.statusTip = statusTip;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	private String statusString;
	private String statusTip;
	private String statusDescription;
	private String in_EnjoinInsure;
	private String in_EnjoinInsureNote; //保险柜服务
	private String in_EnjoinLogon;
	private String in_EnjoinLogonNote;  //登陆服务
	private String in_EnjoinRegister;
	private String in_EnjoinRegisterNote; //注册服务
	private String in_GrantIPCount;
	private String in_GrantIPCountNote;   //IP赠送限制
	private String in_GrantScoreCount;
	private String in_GrantScoreCountNote; //注册赠送银子
	private String in_RevenueRateTake;
	private String in_RevenueRateTakeNote;  //钱庄取款税收比例
	private String in_RevenueRateTransfer;
	private String in_RevenueRateTransferNote;  //钱庄转账税收比例
	private String in_BankPrerequisite;
	private String in_BankPrerequisiteNote;  //钱庄存取限制
	private String in_TransferPrerequisite;
	private String in_TransferPrerequisiteNote;  //钱庄转账限制
	private String in_MedalRate;
	private String in_MedalRateNote;   //奖牌返还比例
	private String in_RechargeRate;  //网银充值比率
	private String in_RechargeRateNote;
	private String in_PlayGameScoreCount;  //玩游戏赠送银子
	private String in_PlayGameScoreNote;
	
	public String getIn_PlayGameScoreCount() {
		return in_PlayGameScoreCount;
	}
	public void setIn_PlayGameScoreCount(String in_PlayGameScoreCount) {
		this.in_PlayGameScoreCount = in_PlayGameScoreCount;
	}
	public String getIn_PlayGameScoreNote() {
		return in_PlayGameScoreNote;
	}
	public void setIn_PlayGameScoreNote(String in_PlayGameScoreNote) {
		this.in_PlayGameScoreNote = in_PlayGameScoreNote;
	}
	public String getIn_BankPrerequisite() {
		return in_BankPrerequisite;
	}
	public void setIn_BankPrerequisite(String in_BankPrerequisite) {
		this.in_BankPrerequisite = in_BankPrerequisite;
	}
	public String getIn_BankPrerequisiteNote() {
		return in_BankPrerequisiteNote;
	}
	public void setIn_BankPrerequisiteNote(String in_BankPrerequisiteNote) {
		this.in_BankPrerequisiteNote = in_BankPrerequisiteNote;
	}
	public String getIn_EnjoinInsure() {
		return in_EnjoinInsure;
	}
	public void setIn_EnjoinInsure(String in_EnjoinInsure) {
		this.in_EnjoinInsure = in_EnjoinInsure;
	}
	public String getIn_EnjoinInsureNote() {
		return in_EnjoinInsureNote;
	}
	public void setIn_EnjoinInsureNote(String in_EnjoinInsureNote) {
		this.in_EnjoinInsureNote = in_EnjoinInsureNote;
	}
	public String getIn_EnjoinLogon() {
		return in_EnjoinLogon;
	}
	public void setIn_EnjoinLogon(String in_EnjoinLogon) {
		this.in_EnjoinLogon = in_EnjoinLogon;
	}
	public String getIn_EnjoinLogonNote() {
		return in_EnjoinLogonNote;
	}
	public void setIn_EnjoinLogonNote(String in_EnjoinLogonNote) {
		this.in_EnjoinLogonNote = in_EnjoinLogonNote;
	}
	public String getIn_EnjoinRegister() {
		return in_EnjoinRegister;
	}
	public void setIn_EnjoinRegister(String in_EnjoinRegister) {
		this.in_EnjoinRegister = in_EnjoinRegister;
	}
	public String getIn_EnjoinRegisterNote() {
		return in_EnjoinRegisterNote;
	}
	public void setIn_EnjoinRegisterNote(String in_EnjoinRegisterNote) {
		this.in_EnjoinRegisterNote = in_EnjoinRegisterNote;
	}
	public String getIn_GrantIPCount() {
		return in_GrantIPCount;
	}
	public void setIn_GrantIPCount(String in_GrantIPCount) {
		this.in_GrantIPCount = in_GrantIPCount;
	}
	public String getIn_GrantIPCountNote() {
		return in_GrantIPCountNote;
	}
	public void setIn_GrantIPCountNote(String in_GrantIPCountNote) {
		this.in_GrantIPCountNote = in_GrantIPCountNote;
	}
	public String getIn_GrantScoreCount() {
		return in_GrantScoreCount;
	}
	public void setIn_GrantScoreCount(String in_GrantScoreCount) {
		this.in_GrantScoreCount = in_GrantScoreCount;
	}
	public String getIn_GrantScoreCountNote() {
		return in_GrantScoreCountNote;
	}
	public void setIn_GrantScoreCountNote(String in_GrantScoreCountNote) {
		this.in_GrantScoreCountNote = in_GrantScoreCountNote;
	}
	public String getIn_RevenueRateTake() {
		return in_RevenueRateTake;
	}
	public void setIn_RevenueRateTake(String in_RevenueRateTake) {
		this.in_RevenueRateTake = in_RevenueRateTake;
	}
	public String getIn_RevenueRateTakeNote() {
		return in_RevenueRateTakeNote;
	}
	public void setIn_RevenueRateTakeNote(String in_RevenueRateTakeNote) {
		this.in_RevenueRateTakeNote = in_RevenueRateTakeNote;
	}
	public String getIn_RevenueRateTransfer() {
		return in_RevenueRateTransfer;
	}
	public void setIn_RevenueRateTransfer(String in_RevenueRateTransfer) {
		this.in_RevenueRateTransfer = in_RevenueRateTransfer;
	}
	public String getIn_RevenueRateTransferNote() {
		return in_RevenueRateTransferNote;
	}
	public void setIn_RevenueRateTransferNote(String in_RevenueRateTransferNote) {
		this.in_RevenueRateTransferNote = in_RevenueRateTransferNote;
	}
	public String getIn_TransferPrerequisite() {
		return in_TransferPrerequisite;
	}
	public void setIn_TransferPrerequisite(String in_TransferPrerequisite) {
		this.in_TransferPrerequisite = in_TransferPrerequisite;
	}
	public String getIn_TransferPrerequisiteNote() {
		return in_TransferPrerequisiteNote;
	}
	public void setIn_TransferPrerequisiteNote(String in_TransferPrerequisiteNote) {
		this.in_TransferPrerequisiteNote = in_TransferPrerequisiteNote;
	}
	public String getIn_MedalRate() {
		return in_MedalRate;
	}
	public void setIn_MedalRate(String in_MedalRate) {
		this.in_MedalRate = in_MedalRate;
	}
	public String getIn_MedalRateNote() {
		return in_MedalRateNote;
	}
	public void setIn_MedalRateNote(String in_MedalRateNote) {
		this.in_MedalRateNote = in_MedalRateNote;
	}
	public String getIn_RechargeRate() {
		return in_RechargeRate;
	}
	public void setIn_RechargeRate(String in_RechargeRate) {
		this.in_RechargeRate = in_RechargeRate;
	}
	public String getIn_RechargeRateNote() {
		return in_RechargeRateNote;
	}
	public void setIn_RechargeRateNote(String in_RechargeRateNote) {
		this.in_RechargeRateNote = in_RechargeRateNote;
	}
	
}
