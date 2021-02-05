package com.study.springboot;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.HtmlUtils;

import com.study.springboot.android.dto.MVC_BoardDto;
import com.study.springboot.android.dto.MemberDTO;
import com.study.springboot.auth.PasswordEncoder;
import com.study.springboot.dao.IMVC_BoardDao;
import com.study.springboot.dao.ISimpleBbsDao;
import com.study.springboot.dao.MemberDAO;
import com.study.springboot.dto.BPageDto;
import com.study.springboot.dto.Greeting;
import com.study.springboot.dto.HelloMessage;
import com.study.springboot.dto.ReservationDto;
import com.study.springboot.dto.ReserveInfoDto;
import com.study.springboot.dto.UserMenuDto;

@Controller
public class MyController {
	
	private HttpSession session;
	
	int listCount = 10; // 한 페이지당 보여줄 게시물의 갯수
	int pageCount = 10; // 하단에 보여줄 페이지 리스트의 갯수
	
	@Autowired
	ISimpleBbsDao dao;
	
	@Autowired
	IMVC_BoardDao androidDao;
	
	@Value("${upload.path}")
    private String uploadPath;
	

	//JUN PART
	@RequestMapping(value="/reservationList", method = {RequestMethod.POST})
    public @ResponseBody Map<String, Object> reservationList(@RequestBody ReserveInfoDto dto) throws Exception {
		// 예약 내역 도출 : 날짜를 대조하여 오늘 전 예약은 제외한다.
		Calendar cal = Calendar.getInstance(); // 오늘 날짜 얻기 (Date is deprecated)
		int thisYear = cal.get(Calendar.YEAR);
		int thisMonth = cal.get(Calendar.MONTH);
		int today = cal.get(Calendar.DAY_OF_MONTH);
		String currDate = String.valueOf(thisYear)+"/"+String.valueOf(thisMonth)+"/"+String.valueOf(today);
				
		String rname = dto.getEmail();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("rsvlist", dao.reservationList(rname, currDate));	
				
		return map;
	}
	
	@RequestMapping(value="/userMenu", method = {RequestMethod.GET, RequestMethod.POST}) // 대기예약
    public @ResponseBody Map<String, Object> userMenu(@RequestBody UserMenuDto dto) throws Exception {
		// 4-1. JSON 형식으로 보내줄 준비(1.Map 만들기)
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("menu", dao.userMenu());	
				
		return map;
	}
	
	@RequestMapping(value="/dateCheck", method=RequestMethod.POST) 
    public @ResponseBody Map<String, Object> dateCheck(@RequestBody ReservationDto dto) throws Exception {
		// 예약 종류 판별 : 날짜를 대조하여 당일 혹은 일반예약인지 판별한다.
		
		Calendar cal = Calendar.getInstance(); // 오늘 날짜 얻기 (Date is deprecated)
		int thisMonth = cal.get(Calendar.MONTH);
		int today = cal.get(Calendar.DAY_OF_MONTH);
		
		String oriDate = dto.getRdate(); // 2019-06-20 이런 형식 (DB와 알맞게 바꾸어준다.)
		/*
		 * int rsvMonth = Integer.parseInt(oriDate.substring(5, 7)); int rsvDay =
		 * Integer.parseInt(oriDate.substring(8));
		 */
		
		int rsvMonth = Integer.parseInt(oriDate.substring(3, 5));
		int rsvDay = Integer.parseInt(oriDate.substring(6));
		
		// 4-1. JSON 형식으로 보내줄 준비(1.Map 만들기)
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (thisMonth == rsvMonth && today == rsvDay) {
			map.put("status", "todayReservation");  // 예약 날짜가 오늘일 때
		} else {
			map.put("status", "generalReservation"); // 예약 날짜가 다른 날일 때
		}		
		
		return map;
	}

	@RequestMapping(value="/reserveInfo", method={RequestMethod.POST, RequestMethod.GET}) // 이름 전화번호 자동입력
    public @ResponseBody Map<String, Object> reserveInfo(@RequestBody ReserveInfoDto dto) throws Exception {
		
		String email = dto.getEmail();
		String name;
		String phone;

		dto = dao.getMemberInfo(email);
		
		name = dto.getName();
		phone = dto.getPhone();
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("name", name); 
		map.put("phone", phone); 

		return map; 		
    }
	
	@RequestMapping(value="/openingHours", method=RequestMethod.POST) // 가게 여는 시간
    public @ResponseBody Map<String, Object> openingHours(@RequestBody ReservationDto dto) throws Exception {
		// 4-1. JSON 형식으로 보내줄 준비(1.Map 만들기)
		Map<String, Object> map = new HashMap<String, Object>();
		
		String sun = "";
		String textTime = "";
		
		String oriOpnTime = dto.getRtime(); // Open Time
		String oriOpnHr = oriOpnTime.substring(0, 2);
		String oriOpnMin = oriOpnTime.substring(3);
		
		String oriClsTime = dto.getRtime(); // Close Time
		String oriClsHr = oriClsTime.substring(0, 2);
		String oriClsMin = oriClsTime.substring(3);
				
		int OpnTimeHr = Integer.parseInt(oriOpnHr); 
		int OpnTimeMin = Integer.parseInt(oriOpnMin);
		
		int ClsTimeHr = Integer.parseInt(oriClsHr); 
		int ClsTimeMin = Integer.parseInt(oriClsMin);

		for (int i = OpnTimeHr; i<ClsTimeHr+1; i++) {
			
			if (i<12) {
				textTime = String.valueOf(i);
				if (i<10) {
					textTime = "0"+textTime;
				}				
				sun = "AM";
			} else if (i == 12) {
				textTime = String.valueOf(i);
				sun = "PM";
			} else {				
				textTime = String.valueOf(i-12);
				sun = "PM";
			}
		
			if (i == OpnTimeHr && OpnTimeMin == 30) {	
				map.put("OpeningHours", String.valueOf(i)+"30");
				map.put("txtOpeningHours", textTime+"30 "+sun);
			} else if (i == ClsTimeHr && ClsTimeMin == 00) {
				map.put("OpeningHours", String.valueOf(i)+"00");
				map.put("txtOpeningHours", textTime+"00 "+sun);
			} else {
				map.put("OpeningHours", String.valueOf(i)+"00");
				map.put("txtOpeningHours", textTime+"00 "+sun);
				
				map.put("OpeningHours", String.valueOf(i)+"30");
				map.put("txtOpeningHours", textTime+"30 "+sun);
			}
		}	
		return map;
	}
	
	
	@RequestMapping(value="/waiting", method=RequestMethod.POST) // 대기예약
    public @ResponseBody Map<String, Object> waiting(@RequestBody ReservationDto dto) throws Exception {
		// 4-1. JSON 형식으로 보내줄 준비(1.Map 만들기)
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		// 1. REQUEST JSON에서 뽑아낸 DATA (RName, RNum, RTime, RDate)
		String RName = dto.getRname(); // 예약자 아이디
		
		int RNum = dto.getRnum(); // 예약 인원 ("2" 이런 형식)
		
		String RTime = dto.getRtime(); // 예약 시간 (17:00 이런 형식)
		
		String oriDate = dto.getRdate(); // 2019-06-20 이런 형식 (DB와 다름)
		String RDate = oriDate.substring(2, 4) + "/" + oriDate.substring(5, 7) + "/" + oriDate.substring(8); 
		// substring 이용하여 DB에 알맞게 변환
		
		// 어차피 대기예약인 것 알기때문에 굳이 "타입 값" 받을 필요 없다.

		map.put("item1", RName); 
		map.put("item2", RNum); 
		map.put("item3", RDate);
		map.put("item4", RTime);
		int result = dao.waiting(map);
		
		map.clear();
		
		map.put("status", result); // 예약 성공.
		
		// 5. 보내기
		return map; 
	}
	
	@RequestMapping(value="/inquiriesOfTable", method=RequestMethod.POST) // 테이블 조회
    public @ResponseBody Map<String, Object> inquiriesOfTable(@RequestBody ReservationDto dto) throws Exception {
		
		// 4-1. JSON 형식으로 보내줄 준비(1.Map 만들기)
		Map<String, Object> map = new HashMap<String, Object>(); 
		int vacantTable = 0;
		/*
		 * int totalTable = 10; // default 10은 임시, managersv에서 값 가져와야한다.
		 */		
		int totalTable = 1; // for test
		int tableCalCount = 0;
		
		// 1. REQUEST JSON에서 뽑아낸 DATA (RName, RNum, RTime, RDate)
		String RName = dto.getRname(); // 예약자 아이디
		
		int RNum = dto.getRnum(); // 예약 인원 ("2" 이런 형식)
		int requiredTable = 0;
		if (RNum < 6) {
			requiredTable = 1;
		} else if (RNum > 9) {
			requiredTable = 3;
		} else {
			requiredTable = 2;
		} // 인원 당 필요한 테이블 개수 설정
		
		String oriTime = dto.getRtime(); // 예약 시간 (17:00 이런 형식)
		String oriHr = oriTime.substring(0, 2);
		String oriMin = oriTime.substring(3);
		String RTime = "00:00"; // 예약 시간 (17:00 이런 형식)	
		int RtimeHr = Integer.parseInt(oriHr); 
		// ex) 17:00 index 0 = 1, index 2 = : 이므로, substring (index 0이상 2미만); 은 "17"입니다.(시간을 의미)
		int RtimeMin = Integer.parseInt(oriMin);
		// ex) 17:00 index 3 = : 이므로, substring (index 3이상); 은 "00"입니다.(분을 의미)
		
		String oriDate = dto.getRdate(); // 2019-06-20 이런 형식 (DB와 다름)
		String RDate = oriDate.substring(2, 4) + "/" + oriDate.substring(5, 7) + "/" + oriDate.substring(8); 
		// substring 이용하여 DB에 알맞게 변환
	
		// 2. Query 실행영역 (요청 값은 변수에 저장하였으므로 dto를 재사용한다.)
		
		String dpcChk = dao.duplicateChk(RName, RDate).getRname(); // 예약 테이블에서 같은 이름이 있는지 중복 검사
		
		// 3. 실행 후 받아온 DATA
		if (dpcChk.equals(RName)) { // 중복 검사 결과
		// 4-2. JSON 형식으로 보내줄 준비(2.Map에 넣기)
			map.put("status", "duplicated"); // 예약 불가능하다, AJAX에서 Alert창 처리.	redirect Mypage.		
		} else {
			for (int i = 0; i < 5; i++) {
				
				switch (i) { 
					case 0 : 
						RTime = String.valueOf(RtimeHr-1)+":"+oriMin;
						break;
							
					case 1 : 
						if (RtimeMin == 30) {
							RTime = oriHr+":00";
						} else {
							RTime = String.valueOf(RtimeHr-1)+":30";
						} break;
						
					case 2 : 
						RTime = oriTime;
						break;
					
					case 3 :
						if (RtimeMin == 30) {
							RTime = String.valueOf(RtimeHr+1)+":00";
						} else {
							RTime = oriHr+":30";
						} break;
						
					case 4 :	
						RTime = String.valueOf(RtimeHr+1)+":"+oriMin;
						break;
				} // ± 30분 및 1시간까지, 선택한 시간 포함하여 총 5개의 시간을 같이 조회한다.
				
				
				vacantTable = totalTable-dao.vacantTableCnt(RTime, RDate).getTableCnt(); 
				// 원하는 시간대의 예약 가능한 테이블 개수 검색.(일단 totalTable 10개로 고정이나, 관리자 테이블에서 조정 가능하다.)
				map.put("tableTime"+String.valueOf(i+1), RTime);
				
				if (vacantTable == 0 || vacantTable < requiredTable) {
					map.put("status", "occupied"); // 예약 가능하며, 예약 가능 테이블을 조회한다.
					map.put("vacantTable"+String.valueOf(i+1), "만석!");
				} else { 
					if (requiredTable > 1 && tableCalCount == 0) {
						vacantTable = vacantTable/requiredTable;
						totalTable = totalTable/requiredTable;
						tableCalCount = 1;
					} // 인원이 table 1개를 넘어설 경우, 가용 table 개수 알맞게 재정의한다.
					map.put("vacantTable"+String.valueOf(i+1), String.valueOf(vacantTable)+"/"+String.valueOf(totalTable));
				} // 빈 테이블 값이 0일 경우엔, "만석, 대기예약"으로 표기해줍니다.
			}
		}
		// 5. 보내기
		return map; 		
    }

	// 추가한것
	@RequestMapping(value="/cancelReservation", method=RequestMethod.POST) // 대기예약 (결제 취소 어떻게 할 것인지?)
    public @ResponseBody Map<String, Object> cancelReservation(@RequestBody ReservationDto dto) throws Exception {
		// 4-1. JSON 형식으로 보내줄 준비(1.Map 만들기)
		Map<String, Object> map = new HashMap<String, Object>(); 

		String RName = dto.getRname(); // 예약자 아이디
		String RDate = dto.getRdate(); // 19/06/20이런 형식
		/*
		 * String oriDate = dto.getRdate(); // 2019-06-20 이런 형식 (DB와 다름) String RDate =
		 * oriDate.substring(2, 4) + "/" + oriDate.substring(5, 7) + "/" +
		 * oriDate.substring(8);
		 */
		
		map.put("item1", RDate);
		map.put("item2", RName);
				
		int result = dao.cancelReservation(map);
		
		map.clear();
		map.put("status", result); // 삭제 성공.
		
		// 5. 보내기
		return map; 
	}
	
	@RequestMapping(value="/makeReservation", method=RequestMethod.POST) // 일반예약
    public @ResponseBody Map<String, Object> makeReservation(@RequestBody ReservationDto dto) throws Exception {
		// 4-1. JSON 형식으로 보내줄 준비(1.Map 만들기)
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		String RName = dto.getRname(); // 예약자 아이디
		String RType = dto.getRtype(); // "당일예약" 또는 "일반예약"
		int RNum = dto.getRnum(); // 예약 인원 ("2" 이런 형식)
		String RTime = dto.getRtime(); // 예약 시간 (17:00 이런 형식)
		//String RDate = dto.getRdate(); // 19/06/20이런 형식
		
		  String oriDate = dto.getRdate(); // 2019-06-20 이런 형식 (DB와 다름) 
		  String RDate = oriDate.substring(2, 4) + "/" + oriDate.substring(5, 7) + "/" +
		  oriDate.substring(8);
		 
		String RPayment = dto.getRpayment(); // 총 결제한 금액
		
		int RTable = 0;
		int totalTable = 10; // Table Max count ※ 임시 값, 설정 필요!!!!!!!!! 
		int existTable = 1;
		
		for (int i = 1; i < totalTable + 1; i++) {
			existTable = dao.vacantTableCheck(RTime, RDate, i).getRtable(); // i값과 동일한 번호가 있으면 1 없으면 0
			if (existTable == 0) { // 없으면,
				RTable = i; // i 값이 테이블값이 된다.
				break;
			}
		}
		
		map.put("item1", RName);
		map.put("item2", RType); 
		map.put("item3", RPayment);
		map.put("item4", RNum); 
		map.put("item5", RTable);
		map.put("item6", RDate);
		map.put("item7", RTime);
				
		int result = dao.makeReservation(map);
		
		map.clear();
		map.put("status", result); // 예약 성공.
		
		// 5. 보내기
		return map; 
	}
	// 추가 종료
	
	@RequestMapping("/")
	public String root(HttpServletRequest request, Model model) 
			throws ServletException, IOException
	{
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		model.addAttribute("bmenu", "공지사항");
		model.addAttribute("list1", dao.board1Dao(nStart, nEnd)); 
		
		return "mainPage";
	
	}
	
	@RequestMapping("/RVModi") //리뷰 수정
	public String RModi(HttpServletRequest request, Model model)  {
		
		String bId = request.getParameter("bId");
		String bKind = request.getParameter("kind");
		String bContent = request.getParameter("bContent");
		System.out.println(bId);
		System.out.println(bContent);
		
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("item1", bId);
		map.put("item2", bContent); 

		int nResult = dao.RVModiDao(map);
		
		model.addAttribute("dto", dao.RModiViewDao(bId,bKind));
		System.out.println("menuWrite : " + nResult);
		
		return "mainPage"; 
	}
	
	@RequestMapping("guest/image")
	public String file(HttpServletRequest request, Model model) 
            throws ServletException, IOException
    {
		String MENU_CODE = request.getParameter("menu_code");
		System.out.println(MENU_CODE);
		model.addAttribute("imgFile", dao.imgFile(MENU_CODE));
		
		return "admin/image";
     }

	@RequestMapping("guest/title")
	public String title(HttpServletRequest request, Model model) 
            throws ServletException, IOException
    {
		String MENU_CODE = request.getParameter("menu_code");
		System.out.println(MENU_CODE);
		model.addAttribute("imgFile", dao.imgFile(MENU_CODE));
		
		return "admin/title";
     }
		
	@RequestMapping("/list")
	public String userlistPage(HttpServletRequest request, Model model) 
			                                 throws ServletException, IOException
	{
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("bmenu", "전체보기");
		model.addAttribute("list", dao.listDao(nStart, nEnd));
		return "bbs/list";
	}
	
	@RequestMapping("/board1")
	public String board1(HttpServletRequest request, Model model) 
			                                 throws ServletException, IOException
	{
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("bmenu", "공지사항");
		model.addAttribute("list1", dao.board1Dao(nStart, nEnd));
		return "bbs/board1";
	}
	
	@RequestMapping("/board2")
	public String board2(HttpServletRequest request, Model model) 
			                                 throws ServletException, IOException
	{
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("bmenu", "자유게시판");
		model.addAttribute("list2", dao.board2Dao(nStart, nEnd));
		return "bbs/board2";
	}
	
	public BPageDto articlePage(int curPage, int nTotalCount)
			throws ServletException, IOException 
	{
		/*
		 * if(bmenu.equals("자유게시판")) 
		 * {
		 *  nTotalCount = request.getIntHeader("free"); 
		 * }
		 * else if(bmenu.equals("자료실")) 
		 * { 
		 * nTotalCount = request.getIntHeader("data"); 
		 * }
		 * else if(bmenu.equals("공지사항")) 
		 * {
		 * nTotalCount = request.getIntHeader("noti"); 
		 * }
		 * else if(bmenu.equals("전체보기")) 
		 * {
		 * nTotalCount = request.getIntHeader("total"); 
		 *  }
		 */
		
		
		/*String bmenu = request.getParameter("bmenu");
		
		  if(bmenu.equals("자유게시판")) 
		  {
		   nTotalCount = request.getInt("free"); 
		  }
		  else if(bmenu.equals("자료실")) 
		  { 
		  nTotalCount = request.getInt("data"); 
		  }
		  else if(bmenu.equals("공지사항")) 
		  {
		  nTotalCount = request.getInt("noti"); 
		  }
		  else if(bmenu.equals("전체보기")) 
		  {
		  nTotalCount = request.getInt("total"); 
		   }
		 */
		
		// 총 페이지 수
		int totalPage = nTotalCount / listCount;
		if(nTotalCount % listCount > 0)
			totalPage++;
		
		// 현재 페이지
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		// 시작 페이지
		int startPage = ((myCurPage-1) / pageCount) * pageCount + 1;
		
		// 끝 페이지
		int endPage = startPage + pageCount - 1;
		if( endPage > totalPage)
			endPage = totalPage;
		
		BPageDto pinfo = new BPageDto();
		pinfo.setTotalCount(nTotalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
		
	}
	
	 @RequestMapping(value="/view", method=RequestMethod.GET) 
	 public String view(HttpServletRequest request, Model model) 
	 { 
		 
		 String sId = request.getParameter("bId");
		 String sName=request.getParameter("bName");
		 String bKind = request.getParameter("kind");
		 System.out.println("bId : " + sId);
		 System.out.println("bName : " + sName);
		 
		 model.addAttribute("dto", dao.HitDao(sId));
		 model.addAttribute("dto", dao.viewDao(sId,bKind)); 
		 return "admin/NotiView"; 
	  }
	 
	@RequestMapping("member/writeForm") 
	public String writeForm() 
	{
		return "bbs/writeForm"; 
	}
				  
	@RequestMapping("/notiForm") 
	public String notiForm() 
	{
		return "admin/notiForm"; 
	}
	
	@RequestMapping("/write") 
	public String write(HttpServletRequest request, Model model)
	{ 
		String sName = request.getParameter("bName"); 
		String sTitle = request.getParameter("bTitle"); 
		String sContent = request.getParameter("bContent");
		String Id = request.getParameter("Id");
		String bMenu = request.getParameter("bMenu");
	
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("item1", sName); 
		map.put("item2", sTitle); 
		map.put("item3", sContent);
		map.put("item4", Id);
		map.put("item5", bMenu);
				
		int nResult = dao.writeDao(map); 
		System.out.println("write : " + nResult);
	
		return "redirect:resNoti"; 
	}
	
	@RequestMapping(value="member/Rwrite", method=RequestMethod.POST)
	public String Rwrite(HttpServletRequest request, Model model)
	{ 
		System.out.println("RWrite 이동");
		try {
			// 서버의 물리적경로 가져오기
			String path = ResourceUtils.getFile(uploadPath).toPath().toString();
			System.out.println("aaa");
            /*
			 * 파일업로드 위한 MultipartHttpServletRequest객체 생성
			 * 객체 생성과 동시에 파일업로드 완료됨. 
			 * 나머지 폼값은 Multipart가 통째로 받아서 처리한다.
			 */
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
			System.out.println("bbb");
			// 업로드폼의 file속성 필드의 이름을 모두 읽음
			Iterator<String> itr = mhsr.getFileNames();
			System.out.println("ccc");
			MultipartFile mfile = null;			
			String fileName = "";		
			
			// 파일 하나의 정보를 저장하기 위한 List타입의 변수(원본파일명, 저장된파일명 등)
			List resultList = new ArrayList();
			System.out.println("ddd");
			String rName = request.getParameter("bName");  
			String rStar = request.getParameter("Star");
			String rMenu_Name = request.getParameter("Menu_Name");
			String rContent = request.getParameter("bContent");
			//String rImg_Extension = request.getParameter("img_Extension");
			String bMenu = request.getParameter("bMenu");
	
			System.out.println(rName);
			System.out.println(rStar);
			System.out.println(rMenu_Name);
			System.out.println(rContent);
			System.out.println(bMenu);
		
		// 업로드폼의 file속성의 필드의 갯수만큼 반복
			while (itr.hasNext()) {
						
				// userfile1, userfile2....출력됨
				fileName = (String)itr.next();
				//System.out.println(fileName);	
				if(fileName == null){
					break;
				}
				System.out.println("filename : " + fileName);
				// 서버로 업로드된 임시파일명 가져옴
				mfile = mhsr.getFile(fileName);
				//System.out.println(mfile);//CommonsMultipartFile@1366c0b 형태임
				
				// 한글깨짐방지 처리 후 업로드된 파일명을 가져온다.
				String MENU_IMAGENAME = 
				    mfile.getOriginalFilename();
				// new String(mfile.getOriginalFilename().getBytes(),"UTF-8"); // Linux
					System.out.println("upload:"+MENU_IMAGENAME);
						
				// 파일명이 공백이라면 while문의 처음으로 돌아간다.
				//System.out.println("originalName:"+originalName);

				// 파일명에서 확장자 가져오기
//				String ext = originalName.substring(originalName.lastIndexOf('.'));
						
				// 파일명을 UUID로 생성된값으로 변경함.
//				String saveFileName = getUuid() + ext;
				String rImg_Extension = getUuid() + "." + MENU_IMAGENAME;
				System.out.println(rImg_Extension);
				// 설정한 경로에 파일저장
				File serverFullName = new File(path + File.separator + rImg_Extension);
				System.out.println(serverFullName);
				// 업로드한 파일을 지정한 파일에 저장한다.
				mfile.transferTo(serverFullName);
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("item1", rName); 
		map.put("item2", rStar);
		map.put("item3", rMenu_Name);
		map.put("item4", rContent);
		map.put("item5", rImg_Extension);
		map.put("item6", bMenu);
		
		// 위 파일의 정보를 담은 Map을 List에 저장
		resultList.add(map);
		System.out.println("resultList"+resultList);
		
		//image,star, menu_name 추가되는 내용
		
		int nResult = dao.RwriteDao(map); 
		System.out.println("write : " + nResult);
	    }
					
		Map returnObj = new HashMap();
		returnObj.put("maps", resultList);
		System.out.println(returnObj);
					
		} catch(UnsupportedEncodingException e) {
				e.printStackTrace();
		} catch(IllegalStateException e) {
				e.printStackTrace();
		} catch(IOException e) {
				e.printStackTrace();
		}
		
	return "admin/resReview"; 
	}
	
	@RequestMapping("guest/bReply") 
	public String bReply(HttpServletRequest request, Model model)
	{ 
		String bContent = request.getParameter("bContent"); 
		String bGroup = request.getParameter("BGroup");
		String sStep = request.getParameter("bStep");
		String sIndent = request.getParameter("bIndent");
		String bMenu = request.getParameter("bMenu");
		String bName = request.getParameter("bName");

		System.out.println("bContent:"+bContent);
		System.out.println("bGroup:" + bGroup);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("item1", bContent);
		map.put("item2", bGroup);
		map.put("item3", Integer.parseInt(sStep)+1);
		map.put("item4", Integer.parseInt(sIndent)+1);
		map.put("item5", bMenu);
		map.put("item6", bName);
		
		System.out.println("bMenu:" + bMenu);
		System.out.println("bName:" + bName);
		
		model.addAttribute("bmenu", "리뷰");
		int nResult = dao.RreplyDao(map); //답변 인서트
		
		//int NoBId = Integer.parseInt(bId)+1;
		System.out.println("write : " + nResult);

		System.out.println("bGroup: " + bGroup);
		
		System.out.println("출력2");
		model.addAttribute("dto1", dao.RreplyShape(bGroup, sStep)); //답변 들여쓰기
		System.out.println("출력3");
		model.addAttribute("dto2", dao.RreplySelect(bGroup, Integer.parseInt(sStep)+1)); //그룹이 동일하고 step이 1인 내용 select 로 찾아서 보여주기
		System.out.println("출력4");
		return "admin/reply"; 
	}
	
	@RequestMapping("/bRP") 
	public String bRP(HttpServletRequest request, Model model)
	{
		String BGROUP = request.getParameter("bGroup");
		String BSTEP = request.getParameter("bStep");
		System.out.println(BGROUP);
		System.out.println(BSTEP);
		model.addAttribute("dto2", dao.RreplySelect(BGROUP, Integer.parseInt(BSTEP)+1)); //그룹이 동일하고 step이 1인 내용 select 로 찾아서 보여주기
		System.out.println("출력4");
		
		return "admin/image";
	}
	
	@RequestMapping("/loginForm")
	public String login(HttpServletRequest request, Model model) {
		return "security/loginForm";
	}
	
	@RequestMapping(value="/SDate", method=RequestMethod.POST)
	public String SDate(HttpServletRequest request, Model model) {
		System.out.println("aaa");
		String sDate = request.getParameter("MDATE"); 
		String Rtime = request.getParameter("OPENTIME"); 
		String Ctime = request.getParameter("CLOSETIME");
		String aTable = request.getParameter("MTABLE");
	
		System.out.println(sDate);
		
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("item1", sDate); 
		map.put("item2", aTable);
		map.put("item3", Rtime); 
		map.put("item4", Ctime);

				
		int nResult = dao.SDateDao(map); 
		System.out.println("write : " + nResult);
		
		return "admin/resSet";
	}
	
	@RequestMapping("/TDate")
	public String TDate(HttpServletRequest request, Model model) 
            throws ServletException, IOException
    {
		String MDate = request.getParameter("MDATE");
		System.out.println(MDate);
		model.addAttribute("Table", dao.TableDao(MDate));
		
		return "redirect:resSet";
     }

	@RequestMapping("/main")
	public String mainPage(HttpServletRequest request, Model model) 
			throws ServletException, IOException 
	{
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		model.addAttribute("bmenu", "공지사항");
		model.addAttribute("list1", dao.board1Dao(nStart, nEnd));

		return "mainPage";
	}
	
	@RequestMapping(value = "admin/resCheck", method= {RequestMethod.POST, RequestMethod.GET})
	public String resCheck(HttpServletRequest request, Model model)                   
	{
		
		
		Calendar cal = Calendar.getInstance();
		
		//현재 년도, 월, 일
		int year = cal.get ( cal.YEAR );
		int month = cal.get ( cal.MONTH ) + 1 ;
		int date = cal.get ( cal.DATE ) ;
		 
		 
		//현재 (시,분,초)
		int hour = cal.get ( cal.HOUR_OF_DAY ) ;
		int min = cal.get ( cal.MINUTE );
		int sec = cal.get ( cal.SECOND );

		
		
		//현재 날짜와 시간 동일 시
		if(Integer.toString(month).length()<2) {
			
			if(min >= 0 && min < 30) {
				min= 0;
				String months = "0" + Integer.toString(month);
				
				String RDate = Integer.toString(year) + "-" +
					       months + "-" +
					       Integer.toString(date);
				
				//현재시분
			  if(Integer.toString(min).length()<2) {
				String RTime = Integer.toString(hour) + ":" + 
				              "0"+Integer.toString(min);
				
				System.out.println("RTime: " + RTime);
				
				System.out.println("RDate: " + RDate);
				model.addAttribute("resCheck", dao.checkDao(RDate, RTime));
			  } else {
				  String RTime = Integer.toString(hour) + ":" + 
			               Integer.toString(min);
			
					System.out.println("RTime: " + RTime);
					
					System.out.println("RDate: " + RDate);
					model.addAttribute("resCheck", dao.checkDao(RDate, RTime));
			  }
			}
			
			if(min >= 30) {
				min = 30;
                String months = "0" + Integer.toString(month);
				
				String RDate = Integer.toString(year) + "-" +
					       months + "-" +
					       Integer.toString(date);
				
				//현재시분
				String RTime = Integer.toString(hour) + ":" + 
				             Integer.toString(min);
				
				System.out.println("RTime: " + RTime);
				
				System.out.println("RDate: " + RDate);
				model.addAttribute("resCheck", dao.checkDao(RDate, RTime));
			}
			
			//String RDate = "2019-06-12"; //테스트용
			//String RTime = "11:00"; // 테스트용
			
			
		}else {
			
			if(min > 0 && min < 30) {
			    min= 0;
				String RDate = Integer.toString(year) + "-" +
						Integer.toString(month) + "-" +
					       Integer.toString(date);
				
				//현재시분
				if(Integer.toString(min).length()<2) {
					String RTime = Integer.toString(hour) + ":" + 
					              "0"+ Integer.toString(min);
					
					
					//String RDate = "2019-06-12"; //테스트용
					//String RTime = "11:00"; //테스트용
					
					System.out.println("RTime: " + RTime);
					System.out.println("RDate: " + RDate);
					model.addAttribute("resCheck", dao.checkDao(RDate, RTime));
			    }else {
			    	String RTime = Integer.toString(hour) + ":" + 
				                Integer.toString(min);
				
				
				//String RDate = "2019-06-12"; //테스트용
				//String RTime = "11:00"; //테스트용
				
				System.out.println("RTime: " + RTime);
				System.out.println("RDate: " + RDate);
				model.addAttribute("resCheck", dao.checkDao(RDate, RTime));
			    }
		}
			
			if(min >= 30) {
				min = 30;
				
				String RDate = Integer.toString(year) + "-" +
						Integer.toString(month) + "-" +
					       Integer.toString(date);
				
				//현재시분
				String RTime = Integer.toString(hour) + ":" + 
				               Integer.toString(min);
				
				
				//String RDate = "2019-06-12"; //테스트용
				//String RTime = "11:00"; //테스트용
				
				System.out.println("RTime: " + RTime);
				System.out.println("RDate: " + RDate);
				model.addAttribute("resCheck", dao.checkDao(RDate, RTime));
				}
		}
		
		return "admin/resCheck";
	}
	
	//예약 날짜/시간에 따라 조회하기
	@RequestMapping("/tbView")
	public String tbView(HttpServletRequest request, Model model) 
			                             throws ServletException, IOException 
	{
		String RDate = request.getParameter("RDate");
		String RTime = request.getParameter("RTime");
		System.out.println("RDate:"+RDate);
		System.out.println("RTime:"+RTime);
		model.addAttribute("TresCheck", dao.tbDao(RDate, RTime));
		System.out.println("special");

		
		return "admin/resStatus";
	}
	@RequestMapping("/resStatus")
	public String resStatus(HttpServletRequest request, Model model) 
			                             throws ServletException, IOException 
	{
		//model.addAttribute("resCheck", dao.checkDao());
		
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("menu", dao.MenuDao(nStart, nEnd));
		return "admin/resStatus";
	}
	
	@RequestMapping("/resDB")
	public String resDB(HttpServletRequest request, Model model) 
			                             throws ServletException, IOException 
	{
		//model.addAttribute("resCheck", dao.checkDao());
		
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("menu", dao.MenuDao(nStart, nEnd));
		return "admin/resDB";
	}
		
	@RequestMapping("/resMenu")
	public String resMenu(HttpServletRequest request, Model model) 
			                             throws ServletException, IOException 
	{
		//model.addAttribute("resCheck", dao.checkDao());
		
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("menu", dao.MenuDao(nStart, nEnd));
		return "admin/resMenu";
	}
	
	@RequestMapping("/menuForm") 
	public String menuForm() 
	{
		return "admin/menuForm"; 
	}
		
	@RequestMapping(value="/menuWrite", method=RequestMethod.POST) 
	public String menuWrite(HttpServletRequest request, Model model)
	{ 
		System.out.println("menuWrite");
		// 뷰로 전달할 정보를 저장하기 위한 Map타입의 변수
		
	
		try {
			// 서버의 물리적경로 가져오기
			String path = ResourceUtils.getFile(uploadPath).toPath().toString();

            /*
			 * 파일업로드 위한 MultipartHttpServletRequest객체 생성
			 * 객체 생성과 동시에 파일업로드 완료됨. 
			 * 나머지 폼값은 Multipart가 통째로 받아서 처리한다.
			 */
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
			
			// 업로드폼의 file속성 필드의 이름을 모두 읽음
			Iterator<String> itr = mhsr.getFileNames();
			
			MultipartFile mfile = null;			
			String fileName = "";		
			
			// 파일 하나의 정보를 저장하기 위한 List타입의 변수(원본파일명, 저장된파일명 등)
			List resultList = new ArrayList();
						
			// 폼값받기 : 제목
			String MENU_NAME = mhsr.getParameter("MENU_NAME");
			String MENU_DESCRIPTION = mhsr.getParameter("MENU_DESCRIPTION"); 
			String MENU_PRICE = mhsr.getParameter("MENU_PRICE");
			/*String MENU_IMAGENAME = request.getParameter("MENU_IMAGENAME");
			String MENU_EXTENSION = request.getParameter("MENU_EXTENSION");
			String serverFullName = request.getParameter("serverFullName");*/
			String MENU_CODE = mhsr.getParameter("MENU_CODE");
			String MENU_QTY = mhsr.getParameter("MENU_QTY");
			String mWriter = mhsr.getParameter("MWriter");	
			
			// 업로드폼의 file속성의 필드의 갯수만큼 반복
			while (itr.hasNext()) {
				
				// userfile1, userfile2....출력됨
				fileName = (String)itr.next();
				//System.out.println(fileName);	
				if(fileName == null){
					break;
				}
				System.out.println("filename : " + fileName);
				// 서버로 업로드된 임시파일명 가져옴
				mfile = mhsr.getFile(fileName);
				//System.out.println(mfile);//CommonsMultipartFile@1366c0b 형태임
				
				// 한글깨짐방지 처리 후 업로드된 파일명을 가져온다.
				String MENU_IMAGENAME = 
					mfile.getOriginalFilename();
				   // new String(mfile.getOriginalFilename().getBytes(),"UTF-8"); // Linux
				System.out.println("upload:"+MENU_IMAGENAME);
				
				// 파일명이 공백이라면 while문의 처음으로 돌아간다.
				//System.out.println("originalName:"+originalName);

				// 파일명에서 확장자 가져오기
//				String ext = originalName.substring(originalName.lastIndexOf('.'));
				
				// 파일명을 UUID로 생성된값으로 변경함.
//				String saveFileName = getUuid() + ext;
				String MENU_EXTENSION = getUuid() + "." + MENU_IMAGENAME;
				System.out.println(MENU_EXTENSION);
				// 설정한 경로에 파일저장
				File serverFullName = new File(path + File.separator + MENU_EXTENSION);
				System.out.println(serverFullName);
				// 업로드한 파일을 지정한 파일에 저장한다.
				mfile.transferTo(serverFullName);
				
				Map<String, Object> map = new HashMap<String, Object>(); 
				map.put("item1", MENU_NAME); 
				map.put("item2", MENU_DESCRIPTION); 
				map.put("item3", Integer.parseInt(MENU_PRICE));
				map.put("item4", MENU_IMAGENAME);
				map.put("item5", MENU_EXTENSION);
				/* map.put("item6", serverFullName); */
				map.put("item6", Integer.parseInt(MENU_CODE));
				map.put("item7", Integer.parseInt(MENU_QTY));
				map.put("item8", mWriter);
				
				// 위 파일의 정보를 담은 Map을 List에 저장
				resultList.add(map);
				System.out.println("resultList"+resultList);
				
				int nResult = dao.menuWriteDao(map);
				System.out.println("menuWrite : " + nResult);
			}
			
			Map returnObj = new HashMap();
			returnObj.put("maps", resultList);
			System.out.println(returnObj);
			
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch(IllegalStateException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		  return "redirect:resMenu"; 
	}

	public static String getUuid(){
		String uuid = UUID.randomUUID().toString();
		//System.out.println(uuid);		
		uuid = uuid.replaceAll("-", "");
		//System.out.println("생성된UUID:"+ uuid);
		return uuid;
	}


	@RequestMapping("/MenuView")
	public String menuView(HttpServletRequest request, Model model)  {
		 
		 String mId = request.getParameter("MId");
		 String menu = request.getParameter("MENU_NAME");
		 String bKind = request.getParameter("kind");
		 System.out.println(mId);
		 System.out.println(menu);
		 
		 model.addAttribute("dto", dao.MviewDao(mId,bKind)); 
		 return "admin/MenuView"; 
	}
	
	@RequestMapping("/ReviewModiForm") //리뷰 답글 수정 모달 폼
	public String ReviewModiForm(HttpServletRequest request, Model model)  {
		String bId = request.getParameter("bId");
		String bKind = request.getParameter("kind");
		
		model.addAttribute("dto", dao.RModiViewDao(bId,bKind)); 
		return "admin/replModi";
	}
	
	@RequestMapping("/ReviewModi") //리뷰 답글 수정
	public String ReviewModi(HttpServletRequest request, Model model)  {
		
		String bId = request.getParameter("bId");
		String bContent = request.getParameter("bContent");
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("item1", bId);
		map.put("item2", bContent); 

		int nResult = dao.RModiDao(map);
		System.out.println("menuWrite : " + nResult);
		
		return "redirect:resReview"; 
	}
	
	
	@RequestMapping("/MenuModiForm")
	public String MenuModiForm(HttpServletRequest request, Model model)  {
		String mId = request.getParameter("MId");
		String bKind = request.getParameter("kind");
		
		 model.addAttribute("dto", dao.ModiViewDao(mId,bKind)); 
		return "admin/MenuModi_view";
	}
	
	@RequestMapping("/MenuModi")
	public String menuModi(HttpServletRequest request, Model model)  {
		 
		System.out.println("MenuModi");
		// 뷰로 전달할 정보를 저장하기 위한 Map타입의 변수
		
		try {
			// 서버의 물리적경로 가져오기
			String path = ResourceUtils.getFile(uploadPath).toPath().toString();

            /*
			 * 파일업로드 위한 MultipartHttpServletRequest객체 생성
			 * 객체 생성과 동시에 파일업로드 완료됨. 
			 * 나머지 폼값은 Multipart가 통째로 받아서 처리한다.
			 */
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
			
			// 업로드폼의 file속성 필드의 이름을 모두 읽음
			Iterator<String> itr = mhsr.getFileNames();
			
			MultipartFile mfile = null;			
			String fileName = "";		
			
			// 파일 하나의 정보를 저장하기 위한 List타입의 변수(원본파일명, 저장된파일명 등)
			List resultList = new ArrayList();
						
			// 폼값받기 : 제목
			String MENU_NAME = mhsr.getParameter("MENU_NAME");
			String MENU_DESCRIPTION = mhsr.getParameter("MENU_DESCRIPTION"); 
			String MENU_PRICE = mhsr.getParameter("MENU_PRICE");
			/*String MENU_IMAGENAME = request.getParameter("MENU_IMAGENAME");
			String MENU_EXTENSION = request.getParameter("MENU_EXTENSION");
			String serverFullName = request.getParameter("serverFullName");*/
			String MENU_CODE = mhsr.getParameter("MENU_CODE");
			String MENU_QTY = mhsr.getParameter("MENU_QTY");
			String mWriter = mhsr.getParameter("MWriter");
			String mId = mhsr.getParameter("MId");
			
			// 업로드폼의 file속성의 필드의 갯수만큼 반복
			while (itr.hasNext()) {
				
				// userfile1, userfile2....출력됨
				fileName = (String)itr.next();
				//System.out.println(fileName);	
				if(fileName == null){
					break;
				}
				System.out.println("filename : " + fileName);
				// 서버로 업로드된 임시파일명 가져옴
				mfile = mhsr.getFile(fileName);
				//System.out.println(mfile);//CommonsMultipartFile@1366c0b 형태임
				
				// 한글깨짐방지 처리 후 업로드된 파일명을 가져온다.
				String MENU_IMAGENAME = 
					mfile.getOriginalFilename();
				   // new String(mfile.getOriginalFilename().getBytes(),"UTF-8"); // Linux
				System.out.println("upload:"+MENU_IMAGENAME);
				
				// 파일명이 공백이라면 while문의 처음으로 돌아간다.
				//System.out.println("originalName:"+originalName);

				// 파일명에서 확장자 가져오기
//				String ext = originalName.substring(originalName.lastIndexOf('.'));
				
				// 파일명을 UUID로 생성된값으로 변경함.
//				String saveFileName = getUuid() + ext;
				String MENU_EXTENSION = getUuid() + "." + MENU_IMAGENAME;
				System.out.println(MENU_EXTENSION);
				// 설정한 경로에 파일저장
				File serverFullName = new File(path + File.separator + MENU_EXTENSION);
				System.out.println(serverFullName);
				// 업로드한 파일을 지정한 파일에 저장한다.
				mfile.transferTo(serverFullName);
				
				Map<String, Object> map = new HashMap<String, Object>(); 
				map.put("item1", MENU_NAME); 
				map.put("item2", MENU_DESCRIPTION); 
				map.put("item3", Integer.parseInt(MENU_PRICE));
				map.put("item4", MENU_IMAGENAME);
				map.put("item5", MENU_EXTENSION);
				/* map.put("item6", serverFullName); */
				map.put("item6", Integer.parseInt(MENU_CODE));
				map.put("item7", Integer.parseInt(MENU_QTY));
				map.put("item8", mWriter);
				map.put("item9", mId);
				
				// 위 파일의 정보를 담은 Map을 List에 저장
				resultList.add(map);
				System.out.println("resultList"+resultList);
				
				int nResult = dao.menuModiDao(map);
				System.out.println("menuWrite : " + nResult);
			}
			
			Map returnObj = new HashMap();
			returnObj.put("maps", resultList);
			System.out.println(returnObj);
			
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch(IllegalStateException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		  return "redirect:resMenu"; 
		
	}
	
	@RequestMapping("/resSet")
	public String resSet(HttpServletRequest request, Model model) 
			                             throws ServletException, IOException 
	{
		//model.addAttribute("resCheck", dao.checkDao());
		
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("menu", dao.MenuDao(nStart, nEnd));
		return "admin/resSet";
	}
	
	@RequestMapping("/resReview")
	public String resReview(HttpServletRequest request, Model model) 
			                             throws ServletException, IOException 
	{
		//model.addAttribute("resCheck", dao.checkDao());
		
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("bmenu", "리뷰");
		model.addAttribute("list2", dao.board2Dao(nStart, nEnd));
		
		return "admin/resReview";
	}
	
	
	@RequestMapping("guest/review")
	public String review(HttpServletRequest request, Model model) 
			                             throws ServletException, IOException 
	{
		//model.addAttribute("resCheck", dao.checkDao());
		
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("bmenu", "리뷰");
		model.addAttribute("list2", dao.board2Dao(nStart, nEnd));
		
		String bId = request.getParameter("bI");
		System.out.println("bId1:" + bId);
		//int MBID = Integer.parseInt(bId)+1;
		//System.out.println("BID:" + MBID);
		//model.addAttribute("list2", dao.ReplDao(BID));
		
		return "admin/review";
	}
	
	
	@RequestMapping("/resReq")
	public String resReq(HttpServletRequest request, Model model) 
			                             throws ServletException, IOException 
	{
		//model.addAttribute("resCheck", dao.checkDao());
		
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("bmenu", "문의사항");
		model.addAttribute("list2", dao.board3Dao(nStart, nEnd));
		
		return "admin/resReq";
	}
	
	@RequestMapping("/resNoti")
	public String resNoti(HttpServletRequest request, Model model) 
			                             throws ServletException, IOException 
	{
		//model.addAttribute("resCheck", dao.checkDao());
		
		session = request.getSession();
		int nPage = 1;
		
		try {
			if (request.getParameter("page") != null) {
				String sPage = request.getParameter("page");
				nPage = Integer.parseInt(sPage);
			} else {
				if (session.getAttribute("cpage") != null) {
					nPage = (int)session.getAttribute("cpage");
				}
			}
		}catch (Exception e) {
			
		}
		int nTotalPage = dao.articleCount();
		BPageDto pinfo = articlePage(nPage, nTotalPage);
		
		model.addAttribute("page", pinfo);
		nPage = pinfo.getCurPage();
		session.setAttribute("cpage", nPage);
		
		int nStart = (nPage - 1) * listCount + 1;
		int nEnd = (nPage - 1) * listCount + listCount;
		
		model.addAttribute("bmenu", "공지사항");
		
		
		/*
		 * BPageDto pinfo = articlePage(nPage, nTotalPage);
		 * 
		 * model.addAttribute("page", pinfo); 
		 * nPage = pinfo.getCurPage();
		 */	
	
		model.addAttribute("list1", dao.board1Dao(nStart, nEnd));
		
		/*
		 * Timestamp bDate = request.getTimestamp(); 
		 * String SDate =bDate.toString().substring(0,16); 
		 * System.out.println(SDate);
		 * model.addAttribute("list1", SDate);
		 */
		 
		return "admin/resNoti";
	}
	
//	@RequestMapping("/admin/fileList")
//	public String fileList() {
//		return "admin/FileUpload/fileList";
//	}
		
	  @RequestMapping("/delete") 
	  public String delete(HttpServletRequest request,Model model) {
		  String mId = request.getParameter("MId"); 
		  int nResult = dao.deleteDao(mId); 
		  System.out.println("Delete : " + nResult);
	      return "redirect:resMenu"; 
	 }
	  
	  @RequestMapping("/Cdelete") 
	  public String Cdelete(HttpServletRequest request,Model model) {
		  String cId = request.getParameter("bId"); 
		  int nResult = dao.CdeleteDao(cId); 
		  
		  System.out.println("bId : " + cId);
		  System.out.println("CDelete : " + nResult);
	      return "redirect:resNoti"; 
	 }
	  
	  @RequestMapping("/reply_view") 
	  public String reply_view(HttpServletRequest request, Model model) {
		  	 String bId = request.getParameter("bId");
		  	 String bKind = request.getParameter("kind");
			 System.out.println(bId);
			 
			 model.addAttribute("reply_view", dao.HitDao(bId));
			 model.addAttribute("reply_view", dao.reply_viewDao(bId, bKind)); 
			 return "bbs/reply_view";  

	 }
	  
	  @RequestMapping("/reply") 
	  public String reply(HttpServletRequest request, Model model) {
		  	String sName = request.getParameter("bName"); 
			String sTitle = request.getParameter("bTitle"); 
			String sContent = request.getParameter("bContent");
			String sGroup = request.getParameter("bGroup");
			String sStep = request.getParameter("bStep");
			String sIndent = request.getParameter("bIndent");
			
			System.out.println(sName);
			Map<String, Object> map = new HashMap<String, Object>(); 
			map.put("item1", sName); 
			map.put("item2", sTitle); 
			map.put("item3", sContent);
			map.put("item4", Integer.parseInt(sGroup));
			map.put("item5", Integer.parseInt(sStep)+1);
			map.put("item6", Integer.parseInt(sIndent)+1);
					
			int nResult = dao.replyDao(map); 
			System.out.println("reply : " + nResult);
		    model.addAttribute("dto", dao.replyShape(sGroup, sStep));
			return "redirect:list"; 
	 }
	  
	  @RequestMapping("/search")
		public String search(HttpServletRequest request, Model model) 
				                                 throws ServletException, IOException
		{
		    System.out.println("BSeachOK");
			session = request.getSession();
			int nPage = 1;
			String find_field;
			String find_name = null;
			
			try {
				if (request.getParameter("page") != null) {
					String sPage = request.getParameter("page");
					nPage = Integer.parseInt(sPage);
				} else {
					if (session.getAttribute("cpage") != null) {
						nPage = (int)session.getAttribute("cpage");
					}
				}
			}catch (Exception e) {
				
			}
			
			try {
				find_field = (String)request.getParameter("find_field");
				find_name = (String)request.getParameter("find_name");
				System.out.println(find_field);
				System.out.println(find_name);
				if(find_field != null) {
					session.setAttribute("find_field", find_field);
					session.setAttribute("find_name", find_name);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int nTotalPage = dao.articleCount();
			BPageDto pinfo = articlePage(nPage, nTotalPage);
			
			model.addAttribute("page", pinfo);
			nPage = pinfo.getCurPage();
			session.setAttribute("cpage", nPage);
			
			int nStart = (nPage - 1) * listCount + 1;
			int nEnd = (nPage - 1) * listCount + listCount;
	
			model.addAttribute("BSearch", dao.searchDao('%'+find_name+'%', nStart, nEnd));
			return "bbs/search";
		}
	  
	  public BPageDto sePage(int curPage, int nTotalCount)
				throws ServletException, IOException 
		{

			// 총 페이지 수
			int totalPage = nTotalCount / listCount;
			if(nTotalCount % listCount > 0)
				totalPage++;
			
			// 현재 페이지
			int myCurPage = curPage;
			if(myCurPage > totalPage)
				myCurPage = totalPage;
			if(myCurPage < 1)
				myCurPage = 1;
			
			// 시작 페이지
			int startPage = ((myCurPage-1) / pageCount) * pageCount + 1;
			
			// 끝 페이지
			int endPage = startPage + pageCount - 1;
			if( endPage > totalPage)
				endPage = totalPage;
			
			BPageDto pinfo = new BPageDto();
			pinfo.setTotalCount(nTotalCount);
			pinfo.setListCount(listCount);
			pinfo.setTotalPage(totalPage);
			pinfo.setCurPage(curPage);
			pinfo.setPageCount(pageCount);
			pinfo.setStartPage(startPage);
			pinfo.setEndPage(endPage);
			
			return pinfo;
			
		}
	  
	  	@MessageMapping("/hello")
	    @SendTo("/topic/greetings")
	    public Greeting greeting(HelloMessage message) throws Exception {
	        Thread.sleep(1000); // simulated delay
	        return new Greeting("안녕하세요? " + HtmlUtils.htmlEscape(message.getName()) + "님,");
	    }
	  
	  	@RequestMapping("/sendFCM")
		public String sendFCM(Model model) {
			return "sendFCM";
		}
		   
		@RequestMapping("/signIn")
		public String signIn(Model model) {
			return "signIn";
		}
			
		@RequestMapping("/signUp")
		public String signUp(Model model) {
			return "signUp";
		}
			
		@RequestMapping("/updateInfo")
		public String updateInfo(Model model) {
			return "updateInfo";
		}
			
		@RequestMapping("/updatePassword")
		public String updatePassword(Model model) {
			return "updatePassword";
		}
			
			
		@RequestMapping("/updateDeviceId")
		public String updateDeviceId(Model model) {
			return "updateDeviceId";
		}
			
		@RequestMapping("getDeviceId")
		public String getDeviceId(Model model) {
			return "getDeviceId";
		}
			
		@RequestMapping("/getReservationOptions")
		public String getReservationOptions(Model model) {
			return "getReservationOptions";
		}
			
		@RequestMapping("/insertReservationData")
		public String insertReservationData(Model model) {
			return "insertReservationData";
		}
		
		@RequestMapping("/getMenuOptions")
		public String getMenuOptions(Model model) {
			return "getMenuOptions";
		}

		@RequestMapping("/androidDB")
		public String androidDB(Model model, HttpServletRequest request) throws Exception {
				
				String kind = request.getParameter("kind");
				JSONObject obj = new JSONObject();
				
				if(kind.equals("list")) {
					String bMenu = request.getParameter("bMenu");
					
					JSONArray dtos = new JSONArray();
					List<MVC_BoardDto> list = null;
					List<Integer> list2 = null;
					Iterator it2 = null;
					if(bMenu.equals("리뷰")) {
						String menu_name = request.getParameter("menu_name");
						
						if(menu_name==null || menu_name.equals("")) {
							list = androidDao.reviewListDao();	
						}
						else {
							list2 = androidDao.reviewSearchNum(menu_name);
							it2 = list2.iterator();
							list = new ArrayList<MVC_BoardDto>();
							while(it2.hasNext()) {
								int bId = (int)it2.next();
								List<MVC_BoardDto> list3 = androidDao.contentDao(bId);
								Iterator it3 = list3.iterator();
								while(it3.hasNext()){
									list.add((MVC_BoardDto)it3.next());		
								}
							}
						}
						
					} else {
						list = androidDao.listDao(bMenu);
						list2 = androidDao.replyNum(bMenu);
						it2 = list2.iterator();
					}
					
					

					Iterator it = list.iterator();
					
					
					while(it.hasNext()) {
						JSONObject dto = new JSONObject();
			        	
						MVC_BoardDto item = (MVC_BoardDto) it.next();
						
						dto.put("bId", item.getBId());
						dto.put("bName", item.getBName());
						dto.put("bTitle", item.getBTitle());
						dto.put("bContent", item.getBContent());
						dto.put("bDate", item.getBDate().toString());
						dto.put("bHit", item.getBHit());
						dto.put("bGroup", item.getBGroup());
						dto.put("bStep", item.getBStep());
						dto.put("bIndent", item.getBIndent());
						dto.put("ID", item.getID());
						dto.put("bMenu", item.getBMenu());
						dto.put("bImageName", item.getBImageName());
						dto.put("img_extension", item.getImg_extension());
						dto.put("menu_name", item.getMenu_name());
						dto.put("secret", item.getSecret());
						dto.put("star", item.getStar());
						if(!bMenu.equals("리뷰")) {
							dto.put("replyNum", it2.next());
						}
						dtos.add(dto);
					}	
					
					obj.put("items", dtos);
				} else if (kind.equals("content")) {
					String boardID = request.getParameter("bId");
					int bId = Integer.valueOf(boardID);
					String way = request.getParameter("way");
					
					if(way.equals("read")) {
						androidDao.upHit(bId);	
					}
					List<MVC_BoardDto> list = androidDao.contentDao(bId);
					
					JSONArray dtos = new JSONArray();
					
					Iterator it = list.iterator();
					
					while(it.hasNext()) {
						JSONObject dto = new JSONObject();
			        	
						MVC_BoardDto item = (MVC_BoardDto) it.next();
						
						dto.put("bId", item.getBId());
						dto.put("bName", item.getBName());
						dto.put("bTitle", item.getBTitle());
						dto.put("bContent", item.getBContent());
						dto.put("bDate", item.getBDate().toString());
						dto.put("bHit", item.getBHit());
						dto.put("bGroup", item.getBGroup());
						dto.put("bStep", item.getBStep());
						dto.put("bIndent", item.getBIndent());
						dto.put("ID", item.getID());
						dto.put("bMenu", item.getBMenu());
						dto.put("bImageName", item.getBImageName());
						dto.put("img_extension", item.getImg_extension());
						dto.put("secret", item.getSecret());
						
						
						dtos.add(dto);
					}
					obj.put("items", dtos);
					
				} else if(kind.equals("write")) {
					Map<String, Object> map = new HashMap<>();
					
					map.put("bName", request.getParameter("bName"));
					map.put("bTitle", request.getParameter("bTitle"));
					map.put("bContent", request.getParameter("bContent"));
					map.put("bStep", Integer.parseInt(request.getParameter("bStep")));
					map.put("bIndent", (Integer.parseInt(request.getParameter("bIndent"))));
					map.put("ID", request.getParameter("ID"));
					map.put("bMenu", request.getParameter("bMenu"));
					map.put("star", request.getParameter("star"));
					map.put("bImageName", request.getParameter("bImageName"));
					map.put("img_extension", request.getParameter("img_extension"));
					map.put("menu_name", request.getParameter("menu_name"));
					map.put("secret", request.getParameter("secret"));
					
					
					int nResult = androidDao.writeDao(map);
				} else if(kind.equals("delete")) {
					androidDao.deleteDao(Integer.parseInt(request.getParameter("bId")));
				} else if(kind.equals("modify")) {
					
					Map<String, Object> map = new HashMap<String, Object>();
				
					map.put("bId", Integer.valueOf(request.getParameter("bId")));
					map.put("bTitle", request.getParameter("bTitle"));
					map.put("bContent", request.getParameter("bContent"));
					map.put("bImageName", request.getParameter("bImageName"));
					map.put("img_extension", request.getParameter("img_extension"));
					map.put("secret", request.getParameter("secret"));
					map.put("star", request.getParameter("star"));
					map.put("menu_name", request.getParameter("menu_name"));
					
					androidDao.modifyDao(map);			
				} else if (kind.equals("reply")) {
					String bGroup = request.getParameter("bGroup");
					String bStep = request.getParameter("bStep");
					
					androidDao.replyShape(bGroup, bStep);
					
					
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("bName", request.getParameter("bName"));
					map.put("ID", request.getParameter("ID"));
					map.put("bTitle", request.getParameter("bTitle"));
					map.put("bContent", request.getParameter("bContent"));
					map.put("bGroup", Integer.valueOf(bGroup));
					map.put("bStep", Integer.valueOf(bStep)+1);
					map.put("bIndent", Integer.valueOf(request.getParameter("bIndent"))+1);
					map.put("bMenu", request.getParameter("bMenu"));
					
					androidDao.replyDao(map);
				} else if(kind.equals("modifyReply")) {
					
					Map<String, Object> map = new HashMap<String, Object>();
				
					map.put("bId", Integer.valueOf(request.getParameter("bId")));
					map.put("bTitle", request.getParameter("bTitle"));
					map.put("bContent", request.getParameter("bContent"));
					
					androidDao.modifyReply(map);			
				} else if(kind.equals("menu")) {
					JSONArray dtos = new JSONArray();
					
					List<String> list = androidDao.getMenu();
					Iterator it = list.iterator();
					while(it.hasNext()) {
						JSONObject dto = new JSONObject();
						dto.put("menu", it.next());
						dtos.add(dto);
					}
					obj.put("items", dtos);
				} 		
		    	model.addAttribute("result", obj.toJSONString());
		    	return "androidDB";
			} 	
			
			@RequestMapping("/image")
			public String image() {
				return "image";
			}
	
			@RequestMapping("/singUpMain")
			public String signUpMain(Model model) {
		  		return "signUpMain";
			}

			@RequestMapping("/hwClause")
			public String hwClause(Model model) {
				return "hwClause";
			}
			
			@RequestMapping("/CeoClause")
			public String CeoClause(Model model) {			
				return "CeoClause";
			}
			
			@RequestMapping("/signUp_User")
			public String SignUp_User(Model model) {
				return "signUp_User";
		    }
				
			@RequestMapping("/signUp_Ceo")
			public String SignUp_Ceo(Model model) {
				return "signUp_Ceo";
		    }
			
			@RequestMapping("/insertMember")
			public String insertMember(HttpServletRequest request, Model model) throws SQLException {

				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String phone = request.getParameter("phone");
				String name = request.getParameter("name");
				String isadmin = request.getParameter("isadmin");
				String isvalid = request.getParameter("isvalid");
				String alert = request.getParameter("alert");
				String birth = request.getParameter("birth");
				String gender = request.getParameter("gender");
				
				String encryptPassword = password;
				PasswordEncoder encoder = PasswordEncoder.getInstance();
				encryptPassword = encoder.passwordEncoder().encode(password);
				MemberDAO memberDAO = MemberDAO.getInstance();
				MemberDTO dto = new MemberDTO();
				dto.setEmail(email);
				dto.setPassword(encryptPassword);
				dto.setPhone(phone);
				dto.setName(name);
				dto.setIsAdmin(isadmin);
				dto.setIsValid(isvalid);
				dto.setAlert(alert);
				dto.setBirth(birth);
				dto.setGender(gender);
				
				int resultInt = memberDAO.insertMember(dto);
				
				if (resultInt > 0) {
					return "mainPage";
				} else {
					return "signUpMain";
				}
			}
	 
}
