package com.keno8.struts.dto;

public class GameShopCateDTO {

	private int id;
	private String mallName;
	private String descript;
	private double price_score;
	private double price_gold;
	private double vipPrice;
	private String imagePath;
	private int dhCount; // 兑换个数
	private int count; // 剩余个数
	private String generateTime;
	private int typeID;
	private String typeName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}


	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getDhCount() {
		return dhCount;
	}

	public void setDhCount(int dhCount) {
		this.dhCount = dhCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}

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

	public double getPrice_score() {
		return price_score;
	}

	public void setPrice_score(double price_score) {
		this.price_score = price_score;
	}

	public double getPrice_gold() {
		return price_gold;
	}

	public void setPrice_gold(double price_gold) {
		this.price_gold = price_gold;
	}

	public double getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(double vipPrice) {
		this.vipPrice = vipPrice;
	}

}
