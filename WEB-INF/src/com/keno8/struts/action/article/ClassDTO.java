package com.keno8.struts.action.article;

public class ClassDTO {

	private int classcode;
	private String classname;
	private String courseType;
	private String courseName;

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public int getClasscode() {
		return classcode;
	}

	public void setClasscode(int classcode) {
		this.classcode = classcode;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
}
