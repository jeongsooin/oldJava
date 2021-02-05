package com.study.springboot.dto;

import lombok.Data;

@Data
public class ReservationDto {
	private String rname;
	private String rtype;
	private String rpayment;
	private int rnum;
	private int rtable;
	private String rdate;
	private String rtime;
}
