package com.manage.struts.video;

public class VideoDTO {
	
	private int videoID;
	private String videoName; //视频名称
	private String fileName;  //文件名称
	private String result;
	private int videolength;
	private int result1Time;
	private int result2Time;
	private int result3Time;
	private int result4Time;
	private int result5Time;
	private int result6Time;
	private String result1;
	private String result2;
	private String result3;
	private String result4;
	private String result5;
	private String result6;
	private String hgName;
	private String hg2Name;
	private int videoType;
    private String resultOfWinLost;
    private int count;
    private String resultOfWinLostName;
	private String videoTypeName;
	private int roomType;
	
	private int roomID;
	private String roomName;
	private int selectresult1Type;
	private int selectresult2Type;
	private int selectresult3Type;
	private int selectresult4Type;
	private int selectresult5Type;
	private int selectresult6Type;
	private String selectresult1Points;
	private String selectresult2Points;
	private String selectresult3Points;
	private String selectresult4Points;
	private String selectresult5Points;
	private String selectresult6Points;
	private String selectresult1ZX;
	private String selectresult2ZX;
	private String selectresult3ZX;
	private String selectresult4ZX;
	private String selectresult5ZX;
	private String selectresult6ZX;
	
	
	
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
	public int getResult1Time() {
		return result1Time;
	}
	public void setResult1Time(int result1Time) {
		this.result1Time = result1Time;
	}
	public int getResult2Time() {
		return result2Time;
	}
	public void setResult2Time(int result2Time) {
		this.result2Time = result2Time;
	}
	public int getResult3Time() {
		return result3Time;
	}
	public void setResult3Time(int result3Time) {
		this.result3Time = result3Time;
	}
	public int getResult4Time() {
		return result4Time;
	}
	public void setResult4Time(int result4Time) {
		this.result4Time = result4Time;
	}
	public int getResult5Time() {
		return result5Time;
	}
	public void setResult5Time(int result5Time) {
		this.result5Time = result5Time;
	}
	public int getResult6Time() {
		return result6Time;
	}
	public void setResult6Time(int result6Time) {
		this.result6Time = result6Time;
	}
	public String getResult1() {
		return result1;
	}
	public void setResult1(String result1) {
		this.result1 = result1;
	}
	public String getResult2() {
		return result2;
	}
	public void setResult2(String result2) {
		this.result2 = result2;
	}
	public String getResult3() {
		return result3;
	}
	public void setResult3(String result3) {
		this.result3 = result3;
	}
	public String getResult4() {
		return result4;
	}
	public void setResult4(String result4) {
		this.result4 = result4;
	}
	public String getResult5() {
		return result5;
	}
	public void setResult5(String result5) {
		this.result5 = result5;
	}
	public String getResult6() {
		return result6;
	}
	public void setResult6(String result6) {
		this.result6 = result6;
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
		}else if(videoType == 3 ){
			videoTypeName = "黄牌或红牌,洗牌";
		}
	}
	public String getVideoTypeName() {
		return videoTypeName;
	}
	public String getResultOfWinLost() {
		return resultOfWinLost;
	}
	public void setResultOfWinLost(String resultOfWinLost) {
		this.resultOfWinLost = resultOfWinLost;
		if("ZY".equals(resultOfWinLost)){
			resultOfWinLostName = "庄赢";
		}if("XY".equals(resultOfWinLost)){
			resultOfWinLostName = "闲赢";
		}
	}
	public String getResultOfWinLostName() {
		return resultOfWinLostName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRoomType() {
		return roomType;
	}
	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getSelectresult1Type() {
		return selectresult1Type;
	}
	public void setSelectresult1Type(int selectresult1Type) {
		this.selectresult1Type = selectresult1Type;
	}
	public int getSelectresult2Type() {
		return selectresult2Type;
	}
	public void setSelectresult2Type(int selectresult2Type) {
		this.selectresult2Type = selectresult2Type;
	}
	public int getSelectresult3Type() {
		return selectresult3Type;
	}
	public void setSelectresult3Type(int selectresult3Type) {
		this.selectresult3Type = selectresult3Type;
	}
	public int getSelectresult4Type() {
		return selectresult4Type;
	}
	public void setSelectresult4Type(int selectresult4Type) {
		this.selectresult4Type = selectresult4Type;
	}
	public int getSelectresult5Type() {
		return selectresult5Type;
	}
	public void setSelectresult5Type(int selectresult5Type) {
		this.selectresult5Type = selectresult5Type;
	}
	public int getSelectresult6Type() {
		return selectresult6Type;
	}
	public void setSelectresult6Type(int selectresult6Type) {
		this.selectresult6Type = selectresult6Type;
	}
	public String getSelectresult1Points() {
		return selectresult1Points;
	}
	public void setSelectresult1Points(String selectresult1Points) {
		this.selectresult1Points = selectresult1Points;
	}
	public String getSelectresult2Points() {
		return selectresult2Points;
	}
	public void setSelectresult2Points(String selectresult2Points) {
		this.selectresult2Points = selectresult2Points;
	}
	public String getSelectresult3Points() {
		return selectresult3Points;
	}
	public void setSelectresult3Points(String selectresult3Points) {
		this.selectresult3Points = selectresult3Points;
	}
	public String getSelectresult4Points() {
		return selectresult4Points;
	}
	public void setSelectresult4Points(String selectresult4Points) {
		this.selectresult4Points = selectresult4Points;
	}
	public String getSelectresult5Points() {
		return selectresult5Points;
	}
	public void setSelectresult5Points(String selectresult5Points) {
		this.selectresult5Points = selectresult5Points;
	}
	public String getSelectresult6Points() {
		return selectresult6Points;
	}
	public void setSelectresult6Points(String selectresult6Points) {
		this.selectresult6Points = selectresult6Points;
	}
	public String getSelectresult1ZX() {
		return selectresult1ZX;
	}
	public void setSelectresult1ZX(String selectresult1ZX) {
		this.selectresult1ZX = selectresult1ZX;
	}
	public String getSelectresult2ZX() {
		return selectresult2ZX;
	}
	public void setSelectresult2ZX(String selectresult2ZX) {
		this.selectresult2ZX = selectresult2ZX;
	}
	public String getSelectresult3ZX() {
		return selectresult3ZX;
	}
	public void setSelectresult3ZX(String selectresult3ZX) {
		this.selectresult3ZX = selectresult3ZX;
	}
	public String getSelectresult4ZX() {
		return selectresult4ZX;
	}
	public void setSelectresult4ZX(String selectresult4ZX) {
		this.selectresult4ZX = selectresult4ZX;
	}
	public String getSelectresult5ZX() {
		return selectresult5ZX;
	}
	public void setSelectresult5ZX(String selectresult5ZX) {
		this.selectresult5ZX = selectresult5ZX;
	}
	public String getSelectresult6ZX() {
		return selectresult6ZX;
	}
	public void setSelectresult6ZX(String selectresult6ZX) {
		this.selectresult6ZX = selectresult6ZX;
	}
	public void setResultOfWinLostName(String resultOfWinLostName) {
		this.resultOfWinLostName = resultOfWinLostName;
	}
	public void setVideoTypeName(String videoTypeName) {
		this.videoTypeName = videoTypeName;
	}

	
	

}
