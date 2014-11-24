package com.doowal.hk798.mall;

public class GameShopCateTypeDTO {

	private int typeID;
	private String typeName;
	private String descript;
	private String note;
	private int parentsID;
	private int levelID;

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getParentsID() {
		return parentsID;
	}

	public void setParentsID(int parentsID) {
		this.parentsID = parentsID;
	}

	public int getLevelID() {
		return levelID;
	}

	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

}
