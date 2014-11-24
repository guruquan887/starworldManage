package com.doowal.struts.action.news;

public class IssueDTO {
	
	private int issueID;
	private String issueTitle;
	private String issueContent;
	private int nullity;
	private String collectDate;
	private String modifyDate;
	
	
	private int kindID;
	private String kindName;
	
	
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
	public int getIssueID() {
		return issueID;
	}
	public void setIssueID(int issueID) {
		this.issueID = issueID;
	}
	public String getIssueTitle() {
		return issueTitle;
	}
	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}
	public String getIssueContent() {
		return issueContent;
	}
	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}
	public int getNullity() {
		return nullity;
	}
	public void setNullity(int nullity) {
		this.nullity = nullity;
	}
	public String getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
