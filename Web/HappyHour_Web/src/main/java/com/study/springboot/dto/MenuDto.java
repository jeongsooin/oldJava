package com.study.springboot.dto;

import lombok.Data;

@Data
public class MenuDto {
	int MId;
	String MWriter;
	String MENU_NAME;
	String MENU_DESCRIPTION;
	int MENU_PRICE;
	String MENU_IMAGENAME;
	String MENU_EXTENSION;
	String serverFullName;
	int MENU_CODE;
	int MENU_QTY;
}
