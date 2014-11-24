package com.doowal.hk798.gameUser;

public class GameMasterRightDTO {
	private int uid;
	private String accounts;
	private int masterRight;
	/*#define UR_CAN_LIMIT_PLAY				0x00000001L				//允许禁止游戏
	#define UR_CAN_LIMIT_LOOKON				0x00000002L				//允许禁止旁观
	#define UR_CAN_LIMIT_WISPER				0x00000004L				//允许禁止私聊
	#define UR_CAN_LIMIT_ROOM_CHAT			0x00000008L				//允许禁止聊天
	#define UR_CAN_LIMIT_GAME_CHAT			0x00000010L				//允许禁止聊天
	#define UR_CAN_CUT_USER					0x00000020L				//允许踢出用户
	#define UR_CAN_FORBID_ACCOUNTS			0x00000040L				//允许封锁帐号
	#define UR_CAN_CONFINE_IP				0x00000080L				//允许禁止地址
	#define UR_CAN_SEE_USER_IP				0x00000100L				//允许查看地址
	#define UR_CAN_SEND_WARNING				0x00000200L				//允许发送警告
	#define UR_CAN_ISSUE_MESSAGE			0x00000400L				//允许发布消息
	#define UR_CAN_BIND_GAME				0x00000800L				//允许游戏绑定
	#define UR_CAN_BIND_GLOBAL				0x00001000L				//允许全局绑定
	#define UR_CAN_SERVER_OPTION			0x00002000L				//允许配置房间
	*/
	private String master_CAN_LIMIT_PLAY;
	private String master_CAN_LIMIT_LOOKON;
	private String master_CAN_LIMIT_WISPER;
	private String master_CAN_LIMIT_ROOM_CHAT;
	private String master_CAN_LIMIT_GAME_CHAT;
	private String master_CAN_CUT_USER;
	private String master_CAN_FORBID_ACCOUNTS;
	private String master_CAN_CONFINE_IP;
	private String master_CAN_SEE_USER_IP;
	private String master_CAN_SEND_WARNING;
	private String master_CAN_ISSUE_MESSAGE;
	private String master_CAN_BIND_GAME;
	private String master_CAN_BIND_GLOBAL;
	private String master_CAN_SERVER_OPTION;
	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	/**
	 * @return the masterRight
	 */
	public int getMasterRight() {
		return masterRight;
	}
	/**
	 * @param masterRight the masterRight to set
	 */
	public void setMasterRight(int masterRight) {
		this.masterRight = masterRight;
	}
	/**
	 * @return the master_CAN_LIMIT_PLAY
	 */
	public String getMaster_CAN_LIMIT_PLAY() {
		return master_CAN_LIMIT_PLAY;
	}
	/**
	 * @param master_CAN_LIMIT_PLAY the master_CAN_LIMIT_PLAY to set
	 */
	public void setMaster_CAN_LIMIT_PLAY(String master_CAN_LIMIT_PLAY) {
		this.master_CAN_LIMIT_PLAY = master_CAN_LIMIT_PLAY;
	}
	/**
	 * @return the master_CAN_LIMIT_LOOKON
	 */
	public String getMaster_CAN_LIMIT_LOOKON() {
		return master_CAN_LIMIT_LOOKON;
	}
	/**
	 * @param master_CAN_LIMIT_LOOKON the master_CAN_LIMIT_LOOKON to set
	 */
	public void setMaster_CAN_LIMIT_LOOKON(String master_CAN_LIMIT_LOOKON) {
		this.master_CAN_LIMIT_LOOKON = master_CAN_LIMIT_LOOKON;
	}
	/**
	 * @return the master_CAN_LIMIT_WISPER
	 */
	public String getMaster_CAN_LIMIT_WISPER() {
		return master_CAN_LIMIT_WISPER;
	}
	/**
	 * @param master_CAN_LIMIT_WISPER the master_CAN_LIMIT_WISPER to set
	 */
	public void setMaster_CAN_LIMIT_WISPER(String master_CAN_LIMIT_WISPER) {
		this.master_CAN_LIMIT_WISPER = master_CAN_LIMIT_WISPER;
	}
	/**
	 * @return the master_CAN_LIMIT_ROOM_CHAT
	 */
	public String getMaster_CAN_LIMIT_ROOM_CHAT() {
		return master_CAN_LIMIT_ROOM_CHAT;
	}
	/**
	 * @param master_CAN_LIMIT_ROOM_CHAT the master_CAN_LIMIT_ROOM_CHAT to set
	 */
	public void setMaster_CAN_LIMIT_ROOM_CHAT(String master_CAN_LIMIT_ROOM_CHAT) {
		this.master_CAN_LIMIT_ROOM_CHAT = master_CAN_LIMIT_ROOM_CHAT;
	}
	/**
	 * @return the master_CAN_LIMIT_GAME_CHAT
	 */
	public String getMaster_CAN_LIMIT_GAME_CHAT() {
		return master_CAN_LIMIT_GAME_CHAT;
	}
	/**
	 * @param master_CAN_LIMIT_GAME_CHAT the master_CAN_LIMIT_GAME_CHAT to set
	 */
	public void setMaster_CAN_LIMIT_GAME_CHAT(String master_CAN_LIMIT_GAME_CHAT) {
		this.master_CAN_LIMIT_GAME_CHAT = master_CAN_LIMIT_GAME_CHAT;
	}
	/**
	 * @return the master_CAN_CUT_USER
	 */
	public String getMaster_CAN_CUT_USER() {
		return master_CAN_CUT_USER;
	}
	/**
	 * @param master_CAN_CUT_USER the master_CAN_CUT_USER to set
	 */
	public void setMaster_CAN_CUT_USER(String master_CAN_CUT_USER) {
		this.master_CAN_CUT_USER = master_CAN_CUT_USER;
	}
	/**
	 * @return the master_CAN_FORBID_ACCOUNTS
	 */
	public String getMaster_CAN_FORBID_ACCOUNTS() {
		return master_CAN_FORBID_ACCOUNTS;
	}
	/**
	 * @param master_CAN_FORBID_ACCOUNTS the master_CAN_FORBID_ACCOUNTS to set
	 */
	public void setMaster_CAN_FORBID_ACCOUNTS(String master_CAN_FORBID_ACCOUNTS) {
		this.master_CAN_FORBID_ACCOUNTS = master_CAN_FORBID_ACCOUNTS;
	}
	/**
	 * @return the master_CAN_CONFINE_IP
	 */
	public String getMaster_CAN_CONFINE_IP() {
		return master_CAN_CONFINE_IP;
	}
	/**
	 * @param master_CAN_CONFINE_IP the master_CAN_CONFINE_IP to set
	 */
	public void setMaster_CAN_CONFINE_IP(String master_CAN_CONFINE_IP) {
		this.master_CAN_CONFINE_IP = master_CAN_CONFINE_IP;
	}
	/**
	 * @return the master_CAN_SEE_USER_IP
	 */
	public String getMaster_CAN_SEE_USER_IP() {
		return master_CAN_SEE_USER_IP;
	}
	/**
	 * @param master_CAN_SEE_USER_IP the master_CAN_SEE_USER_IP to set
	 */
	public void setMaster_CAN_SEE_USER_IP(String master_CAN_SEE_USER_IP) {
		this.master_CAN_SEE_USER_IP = master_CAN_SEE_USER_IP;
	}
	/**
	 * @return the master_CAN_SEND_WARNING
	 */
	public String getMaster_CAN_SEND_WARNING() {
		return master_CAN_SEND_WARNING;
	}
	/**
	 * @param master_CAN_SEND_WARNING the master_CAN_SEND_WARNING to set
	 */
	public void setMaster_CAN_SEND_WARNING(String master_CAN_SEND_WARNING) {
		this.master_CAN_SEND_WARNING = master_CAN_SEND_WARNING;
	}
	/**
	 * @return the master_CAN_ISSUE_MESSAGE
	 */
	public String getMaster_CAN_ISSUE_MESSAGE() {
		return master_CAN_ISSUE_MESSAGE;
	}
	/**
	 * @param master_CAN_ISSUE_MESSAGE the master_CAN_ISSUE_MESSAGE to set
	 */
	public void setMaster_CAN_ISSUE_MESSAGE(String master_CAN_ISSUE_MESSAGE) {
		this.master_CAN_ISSUE_MESSAGE = master_CAN_ISSUE_MESSAGE;
	}
	/**
	 * @return the master_CAN_BIND_GAME
	 */
	public String getMaster_CAN_BIND_GAME() {
		return master_CAN_BIND_GAME;
	}
	/**
	 * @param master_CAN_BIND_GAME the master_CAN_BIND_GAME to set
	 */
	public void setMaster_CAN_BIND_GAME(String master_CAN_BIND_GAME) {
		this.master_CAN_BIND_GAME = master_CAN_BIND_GAME;
	}
	/**
	 * @return the master_CAN_BIND_GLOBAL
	 */
	public String getMaster_CAN_BIND_GLOBAL() {
		return master_CAN_BIND_GLOBAL;
	}
	/**
	 * @param master_CAN_BIND_GLOBAL the master_CAN_BIND_GLOBAL to set
	 */
	public void setMaster_CAN_BIND_GLOBAL(String master_CAN_BIND_GLOBAL) {
		this.master_CAN_BIND_GLOBAL = master_CAN_BIND_GLOBAL;
	}
	/**
	 * @return the master_CAN_SERVER_OPTION
	 */
	public String getMaster_CAN_SERVER_OPTION() {
		return master_CAN_SERVER_OPTION;
	}
	/**
	 * @param master_CAN_SERVER_OPTION the master_CAN_SERVER_OPTION to set
	 */
	public void setMaster_CAN_SERVER_OPTION(String master_CAN_SERVER_OPTION) {
		this.master_CAN_SERVER_OPTION = master_CAN_SERVER_OPTION;
	}
	/**
	 * @return the accounts
	 */
	public String getAccounts() {
		return accounts;
	}
	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	
	
	
}
