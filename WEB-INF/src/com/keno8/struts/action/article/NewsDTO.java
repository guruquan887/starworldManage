package com.keno8.struts.action.article;

public class NewsDTO {

	private int id;
	private String newstitle;
	private String newsdate;
	private String newsinfo;
	private String classcode;
	private String classname;
	private int state;
	private String stateName;
	private String courseType;
	private String courseName;
	private String image;
	
	
	private String newscu;
	private String newszz;
	
	

	public String getNewscu() {
		return newscu;
	}

	public void setNewscu(String newscu) {
		this.newscu = newscu;
	}

	public String getNewszz() {
		return newszz;
	}

	public void setNewszz(String newszz) {
		this.newszz = newszz;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getClasscode() {
		return classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNewstitle() {
		return newstitle;
	}

	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}

	public String getNewsinfo() {
		return newsinfo;
	}

	public void setNewsinfo(String newsinfo) {
		this.newsinfo = newsinfo;
	}

	public String getNewsdate() {
		return newsdate;
	}

	public void setNewsdate(String newsdate) {
		this.newsdate = newsdate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
