package com.study.jsp.dto;

import java.sql.Timestamp;

public class FDto {
	int fId;
	String fName;
	String fTitle;
	String fContent;
	Timestamp fDate;
	int fHit;
	String f_name;
	long fDate_long;
	
	public FDto() {

	}

	public FDto(int fId, String fName, String fTitle, String fContent, Timestamp fDate, int bHit, String f_name, long fDate_long) {
		this.fId = fId;
		this.fName = fName;
		this.fTitle = fTitle;
		this.fContent = fContent;
		this.fDate = fDate;
		this.fHit = bHit;
		this.f_name = f_name;
		this.fDate_long = fDate_long;
	}

	public String getf_name() {
		return f_name;
	}

	public void setf_name(String f_name) {
		this.f_name = f_name;
	}

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfTitle() {
		return fTitle;
	}

	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}

	public Timestamp getfDate() {
		return fDate;
	}

	public void setfDate(Timestamp fDate) {
		this.fDate = fDate;
	}

	public int getbHit() {
		return fHit;
	}

	public void setbHit(int bHit) {
		this.fHit = bHit;
	}

	public long getbDate_long() {
		return fDate_long;
	}

	public void setbDate_long(long bDate_long) {
		this.fDate_long = bDate_long;
	}
	
}
