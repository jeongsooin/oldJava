package com.study.springboot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberDto {
	private String id;
	private String pw;
	private String name;
	private String eMail;
	private Timestamp rDate;
	private String address;
}
