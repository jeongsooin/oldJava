package com.study.springboot.android.dto;

public class MenuDTO {
	private int MId;
	private String MENU_NAME;
	private String MENU_DESCRIPTION;
	private int MENU_PRICE;
	private String MENU_IMAGENAME;
	private String MENU_EXTENSION;
	private int MENU_CODE;
	private int MENU_QTY;
	private String MWRITER;
	
	public MenuDTO() { }

	public MenuDTO(int mId, String mENU_NAME, String mENU_DESCRIPTION, int mENU_PRICE, String mENU_IMAGENAME, String mENU_EXTENSION, int mENU_CODE, int mENU_QTY, String mMWRITER) {
		this.MId = mId;
		this.MENU_NAME = mENU_NAME;
		this.MENU_DESCRIPTION = mENU_DESCRIPTION;
		this.MENU_PRICE = mENU_PRICE;
		this.MENU_IMAGENAME = mENU_IMAGENAME;
		this.MENU_EXTENSION = mENU_EXTENSION;
		this.MENU_CODE = mENU_CODE;
		this.MENU_QTY = mENU_QTY;
		this.MWRITER = mMWRITER;
	}

	public int getMId() {
		return MId;
	}

	public void setMId(int mId) {
		this.MId = mId;
	}

	public String getMENU_NAME() {
		return MENU_NAME;
	}

	public void setMENU_NAME(String mENU_NAME) {
		this.MENU_NAME = mENU_NAME;
	}

	public String getMENU_DESCRIPTION() {
		return MENU_DESCRIPTION;
	}

	public void setMENU_DESCRIPTION(String mENU_DESCRIPTION) {
		this.MENU_DESCRIPTION = mENU_DESCRIPTION;
	}

	public int getMENU_PRICE() {
		return MENU_PRICE;
	}

	public void setMENU_PRICE(int mENU_PRICE) {
		this.MENU_PRICE = mENU_PRICE;
	}

	public String getMENU_IMAGENAME() {
		return MENU_IMAGENAME;
	}

	public void setMENU_IMAGENAME(String mENU_IMAGENAME) {
		this.MENU_IMAGENAME = mENU_IMAGENAME;
	}

	public String getMENU_EXTENSION() {
		return MENU_EXTENSION;
	}

	public void setMENU_EXTENSION(String mENU_EXTENSION) {
		this.MENU_EXTENSION = mENU_EXTENSION;
	}

	public int getMENU_CODE() {
		return MENU_CODE;
	}

	public void setMENU_CODE(int mENU_CODE) {
		this.MENU_CODE = mENU_CODE;
	}

	public int getMENU_QTY() {
		return MENU_QTY;
	}

	public void setMENU_QTY(int mENU_QTY) {
		this.MENU_QTY = mENU_QTY;
	}

	public String getMWRITER() {
		return MWRITER;
	}

	public void setMWRITER(String mWRITER) {
		MWRITER = mWRITER;
	}
	

	
}
