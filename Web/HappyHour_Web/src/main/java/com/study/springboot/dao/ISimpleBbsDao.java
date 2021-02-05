package com.study.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.MemberDto;
import com.study.springboot.dto.MenuDto;
import com.study.springboot.dto.ResDto;
import com.study.springboot.dto.ReservationDto;
import com.study.springboot.dto.ReserveChk;
import com.study.springboot.dto.ReserveInfoDto;
import com.study.springboot.dto.SDateDto;
import com.study.springboot.dto.SimpleBbsDto;
import com.study.springboot.dto.UserMenuDto;

@Mapper
public interface ISimpleBbsDao {
	
	public List<SimpleBbsDto> listDao(int nStart, int nEnd);
	public List<SimpleBbsDto> board1Dao(int nStart, int nEnd);
	public List<SimpleBbsDto> board2Dao(int nStart, int nEnd);
	public List<SimpleBbsDto> board3Dao(int nStart, int nEnd);
	public List<SimpleBbsDto> searchDao(String find_name, int nStart, int nEnd);
	public SimpleBbsDto viewDao(String sId, String bKind);
	public MenuDto MviewDao(String mId, String bKind);
	public MenuDto ModiViewDao(String mId, String bKind);
	public SimpleBbsDto reply_viewDao(String sId, String bKind);
	public int writeDao(Map<String, Object> map);
	public int RwriteDao(Map<String, Object> map);
	public int menuWriteDao(Map<String, Object> map);
	public int menuModiDao(Map<String, Object> map);
	public int replyDao(Map<String, Object> map);
	public int replyShape(String strGroup, String strStep);
	public int deleteDao(@Param("_id")String id);
	public int CdeleteDao(@Param("_id")String id);
	public int articleCount();
	public MemberDto getMember(String id);
	public List<MenuDto> MenuDao(int nStart, int nEnd);
	public int HitDao(String bId);
	public MenuDto imgFile(String MENU_CODE);
	public int SDateDao(Map<String, String> map);
	public SDateDto TableDao(String MDATE);
	public int RreplyDao(Map<String, Object> map);
	public int RreplyShape(String strGroup, String sStep);
	public SimpleBbsDto RreplySelect(String bGroup, int sStep);
	public List<ResDto> checkDao(String RDate, String RTime); //실시간 예약조회
	public List<ResDto> tbDao(String RDate, String RTime); //예약날짜 선택해서 조회
	public SimpleBbsDto RModiViewDao(String bId, String bKind); //리뷰답글 수정 폼 모달
	public int RModiDao(Map<String, String> map); //리뷰답글 수정
	public int RVModiDao(Map<String, String> map); //리뷰답글 수정
	
	//JUN 부분
		public List<UserMenuDto> userMenu();
		public List<ReservationDto> reservationList(String rname, String rdate);
		public ReserveInfoDto getMemberInfo(String email);
		public ReserveChk duplicateChk(String rname, String rdate);
		public ReserveChk vacantTableCnt(String rtime, String rdate);
		public int waiting(Map<String, Object> map);
		public int makeReservation(Map<String, Object> map);
		public ReservationDto vacantTableCheck(String rtime, String rdate, int number);
		public int cancelReservation(Map<String, Object> map);

	
}
