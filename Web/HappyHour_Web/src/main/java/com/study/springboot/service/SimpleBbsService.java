package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.ISimpleBbsDao;
import com.study.springboot.dto.MemberDto;
import com.study.springboot.dto.MenuDto;
import com.study.springboot.dto.ResDto;
import com.study.springboot.dto.SimpleBbsDto;

@Service
public class SimpleBbsService implements ISimpleBbsService {
	
	@Autowired
	ISimpleBbsDao dao;

	@Override
	public List<SimpleBbsDto> list(int nStart, int nEnd){
		return dao.listDao(nStart, nEnd);
	}
	
	@Override
	public List<MenuDto> Menu(int nStart, int nEnd){
		return dao.MenuDao(nStart, nEnd);
	}

	@Override
	public SimpleBbsDto view(String id, String bKind) {
		return dao.viewDao(id, bKind);
	}

	@Override
	public int write(Map<String, Object> map) {
		int nResult = dao.writeDao(map);
		return nResult;
	}

	@Override
	public int delete(String id) {
		int nResult = dao.deleteDao(id);
		return nResult;
	}
	
	@Override
	public int Cdelete(String id) {
		int nResult = dao.deleteDao(id);
		return nResult;
	}

	@Override
	public int count() {
		int nTotalCount = dao.articleCount();
		return nTotalCount;
	}
	
	@Override
	public List<ResDto> check(String RDate, String RTime) {
		return dao.checkDao(RDate, RTime);
	}
	
	 @Override 
	 public int updatehit(String bId) { 
		 int updatehit = dao.HitDao(bId);
		 return updatehit; 
	  }

	@Override
	public MemberDto member(String id) {
		return null;
	}
	 
}
