package com.suncity.struts.betlimit;

public class UserLimitDTO {
	
	private int id;
	private int limit_Down;
	private int limit_Up;
	private int chip_1;
	private int chip_2;
	private int chip_3;
	private int chip_4;
	private int chip_5;
	private String chip;
	
	private int chipID;
	private int chipValue;
	private int checked; //0 未选中，1选中.
	
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLimit_Down() {
		return limit_Down;
	}
	public void setLimit_Down(int limit_Down) {
		this.limit_Down = limit_Down;
	}
	public int getLimit_Up() {
		return limit_Up;
	}
	public void setLimit_Up(int limit_Up) {
		this.limit_Up = limit_Up;
	}
	public int getChip_1() {
		return chip_1;
	}
	public void setChip_1(int chip_1) {
		this.chip_1 = chip_1;
	}
	public int getChip_2() {
		return chip_2;
	}
	public void setChip_2(int chip_2) {
		this.chip_2 = chip_2;
	}
	public int getChip_3() {
		return chip_3;
	}
	public void setChip_3(int chip_3) {
		this.chip_3 = chip_3;
	}
	public int getChip_4() {
		return chip_4;
	}
	public void setChip_4(int chip_4) {
		this.chip_4 = chip_4;
	}
	public int getChip_5() {
		return chip_5;
	}
	public void setChip_5(int chip_5) {
		this.chip_5 = chip_5;
	}
	public String getChip() {
		return chip;
	}
	public void setChip(String chip) {
		this.chip = chip;
	}
	public int getChipID() {
		return chipID;
	}
	public void setChipID(int chipID) {
		this.chipID = chipID;
	}
	public int getChipValue() {
		return chipValue;
	}
	public void setChipValue(int chipValue) {
		this.chipValue = chipValue;
	}
	
	
}
