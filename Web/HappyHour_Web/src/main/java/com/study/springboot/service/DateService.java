package com.study.springboot.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {
	public static String getDateFormat(int type, long time) {
		String now = null;
		String pattern = "yyyy/MM/dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		//long 타입을 근거로 해서 시간을 뺌
		now = sdf.format(new Date(time));
		if(type==1) {
			now = now.split(" ")[0]; //날짜
		}else if(type==2) {
			now = now.split(" ")[1]; //시간
		}
		
		return now;
	}
	
}
