package com.manage.struts.video;

public class VideoPlayListDTO {
	
	private int playID;       //播放列表ID
	private int videoID;      //视频ID
	private String videoName; //视频名称
	private String fileName;  //视频文件名称
	private String result;
	private int videolength;
	private String hgName;
	private String hg2Name;
	private int videoType;
	private int state;
	private int xh;
	private String createTime;
	private String stateName;
	private String videoTypeName;
	private int resultTime;
	private int startTime;
	private int endTime;
	private int resultAnd;
	
	
	public String getStateName() {
		return stateName;
	}
	public String getVideoTypeName() {
		return videoTypeName;
	}
	public int getPlayID() {
		return playID;
	}
	public void setPlayID(int playID) {
		this.playID = playID;
	}
	public int getVideoID() {
		return videoID;
	}
	public void setVideoID(int videoID) {
		this.videoID = videoID;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getVideolength() {
		return videolength;
	}
	public void setVideolength(int videolength) {
		this.videolength = videolength;
	}
	public String getHgName() {
		return hgName;
	}
	public void setHgName(String hgName) {
		this.hgName = hgName;
	}
	public String getHg2Name() {
		return hg2Name;
	}
	public void setHg2Name(String hg2Name) {
		this.hg2Name = hg2Name;
	}
	public int getVideoType() {
		return videoType;
	}
	public void setVideoType(int videoType) {
		this.videoType = videoType;
		if(videoType ==0 ){
			videoTypeName = "等待下注";
		}else if(videoType == 1){
			videoTypeName = "开牌视频";
		}else if(videoType == 2){
			videoTypeName = "换人";
		}else if(videoType == 3){
			videoTypeName = "洗牌";
		}else{
			videoTypeName = "黄牌、红牌";
		}
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
		if(state == 1){
			stateName = "已播放";
		}else if(state == 0){
			stateName = "未播放";
		}else{
			stateName = "正在播放";
		}	
	}
	public int getXh() {
		return xh;
	}
	public void setXh(int xh) {
		this.xh = xh;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getResultTime() {
		return resultTime;
	}
	public void setResultTime(int resultTime) {
		this.resultTime = resultTime;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getResultAnd() {
		return resultAnd;
	}
	public void setResultAnd(int resultAnd) {
		this.resultAnd = resultAnd;
	}

}
