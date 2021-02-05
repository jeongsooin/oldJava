package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.MemberDto;
import com.study.springboot.dto.MenuDto;
import com.study.springboot.dto.ResDto;
import com.study.springboot.dto.SimpleBbsDto;

public interface ISimpleBbsService {
	
	public List<SimpleBbsDto> list(int nStart, int nEnd);
	public List<MenuDto> Menu(int nStart, int nEnd);
	public SimpleBbsDto view(String id, String kind);
	public int write(Map<String, Object> map);
	public int delete(@Param("_id")String id);
	public int Cdelete(@Param("_id")String id);
	public int count();
	public MemberDto member(String id);
	public List<ResDto> check(String RDate, String RTime);
	public int updatehit(String bId);//조회수 증가

	
}
