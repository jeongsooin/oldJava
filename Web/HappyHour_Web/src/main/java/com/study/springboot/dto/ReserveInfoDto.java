package com.study.springboot.dto;

import lombok.Data;

@Data
public class ReserveInfoDto {
	private String email;
	private String pw;
	private String name;
	private String phone;
	private String isadmin;
	private String invalid;
	private String alert;
	private String birth;
	private String gender;
}
