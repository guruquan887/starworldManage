package com.keno8.struts.dto;

public class AdminDTO {

	private int id;
	private String userName;
	private String password;
	private String loginTime;
	private String loginIP;
	private String realName;
	private String phone;
	private String address;
	private String uplevelUser;
	private int type;
	private int roleId;
	private String roleName;
	private int parent;
	private String parents;
	private String machineserial;
	private String loginMachineserial;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUplevelUser() {
		return uplevelUser;
	}

	public void setUplevelUser(String uplevelUser) {
		this.uplevelUser = uplevelUser;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getParents() {
		return parents;
	}

	public void setParents(String parents) {
		this.parents = parents;
	}

	/**
	 * @return the machineserial
	 */
	public String getMachineserial() {
		return machineserial;
	}

	/**
	 * @param machineserial the machineserial to set
	 */
	public void setMachineserial(String machineserial) {
		this.machineserial = machineserial;
	}

	/**
	 * @return the loginMachineserial
	 */
	public String getLoginMachineserial() {
		return loginMachineserial;
	}

	/**
	 * @param loginMachineserial the loginMachineserial to set
	 */
	public void setLoginMachineserial(String loginMachineserial) {
		this.loginMachineserial = loginMachineserial;
	}

}
