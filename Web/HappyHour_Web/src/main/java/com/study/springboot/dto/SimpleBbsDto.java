package com.study.springboot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SimpleBbsDto {
	int bId;
	int bI;
	String bName;
	String bTitle;
	String bContent;
	//Date bDate;
	Timestamp bDate;
	String sDate;
	int bHit;
	int bGroup;
	int bStep;
	int bIndent;

	String Id;
	String bMenu;
	
	//추가내용
	String IMG_EXTENSION; //이미지명 
	String MENU_NAME; //메뉴명
	String STAR; //별점

}
