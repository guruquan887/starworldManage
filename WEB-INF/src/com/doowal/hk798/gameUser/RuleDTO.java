package com.doowal.hk798.gameUser;

public class RuleDTO {
	
	private int ruleID;         //权限ID
	private String ruleName;    //权限内容
	private String ruleMark;    //备注
	private int ruleState;      //权限状态    0,无；1有
	
	
	public int getRuleState() {
		return ruleState;
	}
	public void setRuleState(int ruleState) {
		this.ruleState = ruleState;
	}
	public int getRuleID() {
		return ruleID;
	}
	public void setRuleID(int ruleID) {
		this.ruleID = ruleID;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleMark() {
		return ruleMark;
	}
	public void setRuleMark(String ruleMark) {
		this.ruleMark = ruleMark;
	}
	

}
