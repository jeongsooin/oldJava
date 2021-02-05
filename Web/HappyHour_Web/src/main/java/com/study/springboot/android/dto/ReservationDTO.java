package com.study.springboot.android.dto;

public class ReservationDTO {
	
	private int Rid;
	private String RName;
	private String RType;
	private String RPayment;
	private int Rnum;
	private int RTable;
	private String RDate;
	private String RTime;
	
	public ReservationDTO() { }

	public ReservationDTO(int rid, String rName, String rType, String rPayment, int rnum, int rTable, String rDate, String rTime) {
		Rid = rid;
		RName = rName;
		RType = rType;
		RPayment = rPayment;
		Rnum = rnum;
		RTable = rTable;
		RDate = rDate;
		RTime = rTime;
	}

	public int getRid() {
		return Rid;
	}

	public void setRid(int rid) {
		Rid = rid;
	}

	public String getRName() {
		return RName;
	}

	public void setRName(String rName) {
		RName = rName;
	}

	public String getRType() {
		return RType;
	}

	public void setRType(String rType) {
		RType = rType;
	}

	public String getRPayment() {
		return RPayment;
	}

	public void setRPayment(String rPayment) {
		RPayment = rPayment;
	}

	public int getRnum() {
		return Rnum;
	}

	public void setRnum(int rnum) {
		Rnum = rnum;
	}

	public int getRTable() {
		return RTable;
	}

	public void setRTable(int rTable) {
		RTable = rTable;
	}

	public String getRDate() {
		return RDate;
	}

	public void setRDate(String rDate) {
		RDate = rDate;
	}

	public String getRTime() {
		return RTime;
	}

	public void setRTime(String rTime) {
		RTime = rTime;
	}
	
}
