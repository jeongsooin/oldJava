package com.study.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.android.dto.MVC_BoardDto;

@Mapper
public interface IMVC_BoardDao {
	public List<MVC_BoardDto> listDao(String bMenu);
	public List<MVC_BoardDto> reviewListDao();
	public List<Integer> reviewSearchNum(String menu_name);
	public List<Integer> replyNum(String bMenu);
	public List<MVC_BoardDto> contentDao(int bId);
	public int writeDao(Map<String, Object> map);
	public void upHit(int bId);
	public void deleteDao(int bId);
	public void modifyDao(Map<String, Object> map);
	public void replyDao(Map<String, Object> map);
	public void replyShape(String bGroup, String bStep);
	public void modifyReply(Map<String, Object> map);
	public List<String> getMenu();
}
