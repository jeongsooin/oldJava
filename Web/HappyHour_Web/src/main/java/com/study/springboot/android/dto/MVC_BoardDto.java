package com.study.springboot.android.dto;

import java.sql.Timestamp;

import com.study.springboot.android.dto.MVC_BoardDto;

import lombok.Data;

@Data
public class MVC_BoardDto {
	private int bId;
    private String bName;
    private String bTitle;
    private String bContent;
    private int bHit;
    private Timestamp bDate;
    private int bGroup;
    private int bIndent;
    private int bStep;
    private String ID;
    private String bMenu;
    private String bImageName;
    private String img_extension;
    private String menu_name;
    private String secret;
    private int star;
    
    private int reply; // 답변 갯수
}
