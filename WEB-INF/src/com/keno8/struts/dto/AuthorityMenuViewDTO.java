package com.keno8.struts.dto;

import java.io.Serializable;

public class AuthorityMenuViewDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2268503562450176409L;
	private int id;
	private int userid;
	private int menuid;
	private String menuName;
	private String menuPath;
	private int parentId;
	
	private int type;
	
	private Boolean insertdiv;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getMenuid() {
		return menuid;
	}
	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public boolean isInsertdiv() {
		return insertdiv;
	}
	public void setInsertdiv(boolean insertdiv) {
		this.insertdiv = insertdiv;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Boolean getInsertdiv() {
		return insertdiv;
	}
	public void setInsertdiv(Boolean insertdiv) {
		this.insertdiv = insertdiv;
	}
	
	

}
