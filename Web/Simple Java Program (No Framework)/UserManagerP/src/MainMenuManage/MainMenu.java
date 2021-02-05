package MainMenuManage;

import java.text.SimpleDateFormat;
import java.util.*;

import LockerManage.*;
import MemberManage.*;
import StatisticsManage.MemberStatics;

/**
 * @Class Name : MainMenu.java
 * @Description : 메인메뉴 기능을 정의하고, 각 기능이 구현된 클래스들을 선언해 사용하고 관리하는 클래스
 * @Modification 
 *
 * @author 정수인
 * @since 2019.09.10
 * @version 1.0
 * @see
 *  
 *  --------------------------------------
 *  수정일			수정자			수정내용
 *  --------------------------------------
 *  2019-09-10		정수인		 	최초 기술
 *  
 *  --------------------------------------
 *  
 *  Copyright (C)  All right reserved.
 */

public class MainMenu {

	/* 사용자가 선택하는 메뉴 옵션을 저장하는 String 변수 */
	public static String menuOption = "";
	
	/* 모든 회원정보를 저장하는 AllMembersDTO 클래스 객체를 공유할 수 있도록 메인 클래스에 static 전역변수로 선언 */
	public static AllMembersDTO allUsers = new AllMembersDTO();
	
	/* 사물함 정보를 저장하는 LockerDTO 클래스 객체를 공유할 수 있도록 메인 클래스에 static 전역변수로 선언 */
	public static LockerDTO lockers = new LockerDTO();
	
	/* 사물함 관리 관련 기능을 제공하는 LockerDAO 클래스 객체를 공유할 수 있도록 메인 클래스에 static 전역변수로 선언 */
	public static LockerDAO lockerDAO = new LockerDAO();
	
	/* 회원 관리 관련 기능을 제공하는 MembersDAO 클래스 객체를 공유할 수 있도록 메인 클래스에 static 전역변수로 선언 */
	public static MembersDAO membersDAO = new MembersDAO();
	
	/* 회원 통계 관련 기능을 제공하는 MemberStatics 클래스 객체를 공유할 수 있도록 메인 클래스에 static 전역변수로 선언 */
	public static MemberStatics memberStatics = new MemberStatics();
	
	/* 사용자의 키보드 입력을 받는 Scanner 클래스와 그 입력 정보를 MainMenu 클래스 안의 함수들이 공유해서 쓸 수 있도록 전역변수로 선언*/
	static Scanner userInput = new Scanner(System.in);
	
	/**
	 * function명 	: main
	 * function설명	: 4가지로 분류되는 기능그룹들을 실행하는 mainMenu() 함수 호출하여 실행
	 */
	public static void main(String[] args) {
					
		mainMenu();
	}
	
	/**
	 * function명 	: mainMenu
	 * function설명	: 1. 콘솔에서 실행시 처음으로 진입하는 메인화면을 구성
	 * 				  2. 메뉴 선택에 대한 입력을 받아 각 메뉴별로 진입하고 진출하는 분기점을 구성
	 *                3. [1]회원관리, [2] 락커관리, [3] 회원통계, [0] 종료하기의 4개 기능을 가장 바깥쪽 while 무한루프에서 돌리며 메인메뉴 유지
	 *                4. [0] 종료하기 메뉴에 대한 입력이 들어오기 전까지 루프를 돌며 전체 루프를 포함하고, 종료 메뉴 입력 시 리턴없이 함수 종료
	 */
	public static void mainMenu() {
		
		/* 모든 기능들이 돌아가도록 종료 분기 입력 전까지 유지되는 메인 루프*/
		while(true) {
			
			System.out.println("┌──────────────────────────┐");
			System.out.println("│ [1] 회원관리             │");
			System.out.println("│ [2] 락커관리             │");
			System.out.println("│ [3] 회원통계             │");
			System.out.println("│ [0] 종료하기             │");
			System.out.println("└──────────────────────────┘");
			
			// 메인 메뉴 안내 문구를 출력하고, 메뉴 선택 입력을 받는 함수
			printSelectMenu();		
			
			/* 호출한 printSelectMenu 함수가 입력받은 값이 유효한지 검사해 menuOption(전역변수)에 저장하면, 저장 결과에 따라 기능별로 분기가 나누어짐 */
			if (menuOption.equals("0")) {
				
				// "0" 입력시 종료 문구 출력한 후, 메인 루프를 빠져나가고 그대로 mainMenu => main 함수 순으로 종료
				System.out.println(" [Message] : 프로그램 종료 ... ");
				break;
			} else if (menuOption.equals("1")) {
				
				// "1" 입력시 회원관리 기능을 묶어서 관리하는 memberManage 함수 호출
				memberManage();
			} else if (menuOption.equals("2")) {
				
				// "2" 입력시 사물함관리 기능을 묶어서 관리하는 lockerManage 함수 호출
				lockerManage();
			} else if (menuOption.equals("3")) {
				
				// "3" 입력시 회원통계 기능을 묶어서 관리하는 memberStatics 함수 호출
				memberStatics();
			} else {
				
				// default 처리 : 종료 입력("0")이 들어오기 전까지 정의되지 않은 입력들은 모두 루프 시작점으로 되돌아 가게 함
				continue;
			} // if - else End
		} // while End
	}
	
	/**
	 * function명 	: memberManage
	 * function설명	: 1. 회원관리 기능에 요구되는 [1] 조회, [2] 추가, [3] 수정, [4] 삭제 동작이 작동하는 함수
	 * 				  2. 세부 기능을 선택하도록 사용자에게 입력을 받고, 입력받은 값 검증하여 해당하는 기능을 수행하는 함수 호출
	 * 				  3. mainMenu 함수와 마찬가지로 "0" 입력시, 해당 기능 루프의 종료 분기가 되어 빠져나감.
	 * 				  4. 정의한 유형의 사용자 입력 받을 시 해당하는 세부 기능으로 분기가 갈라짐
	 * 			      5. 정의되지 않은 유형의 입력을 받을 시, 다시 입력 받도록 처음으로 돌아감.
	 */
	public static void memberManage() {

		/* 사용자가 선택한 메뉴 옵션을 저장할 String 변수 */
		String choice = "";
		
		/* 4가지 회원 관리 기능을 반복해서 수행하는 루프 */
		while(true) {
			
			/* 사용자가 메뉴 선택하도록 입력을 받고, 입력한 결과에 따라 세부 메뉴 분기점 구성 */
			try {
				
				System.out.println("┌──────────────────────────┐");
				System.out.println("│ [1] 회원조회             │");
				System.out.println("│ [2] 회원추가             │");
				System.out.println("│ [3] 회원수정             │");
				System.out.println("│ [4] 회원삭제             │");
				System.out.println("│ [0] 돌아가기             │");
				System.out.println("└──────────────────────────┘");
				
				// 사용자의 키보드 입력 문자열을 변수에 저장
				choice = userInput.nextLine();	
				
				System.out.println(" ▶ 선택 : " + choice);
				
				/* 세부 기능으로 진입하게 할 입력 유형 정의하고, 분기해서 해당 기능 수행하는 함수 호출 */
				if(choice.contentEquals("0")) {
					
					// "0" 입력시 종료 문구 출력한 후 루프를 빠져나가고 mainMenu 루프로 돌아감
					System.out.println("┌──────────────────────────┐");
					System.out.println("│           EXIT           │");
					System.out.println("└──────────────────────────┘");
					break;
				} else if(choice.contentEquals("1")) {
					
					// "1" 입력 시 회원 조회 기능을 제공하는 MemberDAO 객체의 searchUser 함수 참조하여 호출
					membersDAO.searchUser();
				} else if(choice.contentEquals("2")) {
					
					// "2" 입력 시 회원 추가 기능을 제공하는 MemberDAO 객체의 addhUser 함수 참조하여 호출
					membersDAO.addUser();
				} else if(choice.contentEquals("3")) {
					
					// "3" 입력 시 회원 정보 수정 기능을 제공하는 MemberDAO 객체의 updateUser 함수 참조하여 호출
					membersDAO.updateUser();
				} else if(choice.contentEquals("4")) {
					
					// "4" 입력 시 회원 정보 삭제 기능을 제공하는 MemberDAO 객체의 deleteUser 함수 참조하여 호출
					membersDAO.deleteUser();
				} else {
					
					// 정의하지 않은 입력 유형은 분기 시작점으로 돌아가도록 처리
					System.out.println("┌──────────────────────────┐");
					System.out.println("│    잘못된 입력입니다     │");
					System.out.println("└──────────────────────────┘");
					continue;
				} // if - else End
				
			} catch (Exception e) {
				
				// 예외 발생 시 콘솔 창과 로그에 메시지 출력
				System.out.println("┌──────────────────────────┐");
				System.out.println("│          ERROR           │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : memberManage() " + e.getMessage());
				e.printStackTrace();
				break;				
			} // try - catch End
		} // while End		
	}
	
	
	/**
	 * function명 	: lockerManage
	 * function설명	: 1. 사물함관리 기능에 요구되는 [1][2] 조회, [3] 추가, [4] 수정, [5] 삭제, [6] 일괄삭제 동작이 작동하는 함수
	 * 				  2. 세부 기능을 선택하도록 사용자에게 입력을 받고, 입력받은 값 검증하여 해당하는 기능을 수행하는 함수 호출
	 * 				  3. mainMenu 함수와 마찬가지로 "0" 입력시, 해당 기능 루프의 종료 분기가 되어 빠져나감.
	 * 				  4. 정의한 유형의 사용자 입력 받을 시 해당하는 세부 기능으로 분기가 갈라짐
	 * 			      5. 정의되지 않은 유형의 입력을 받을 시, 다시 입력 받도록 처음으로 돌아감.
	 */
	public static void lockerManage() {

		/* 사용자가 선택한 메뉴 옵션을 저장할 String 변수 */
		String choice;
		
		/* 6가지 사믈함 관리 기능을 반복해서 수행하는 루프 */
		while(true) {
			try {
				
				System.out.println("┌──────────────────────────┐");
				System.out.println("│ [1] 락커조회 - 회원 ID   │");
				System.out.println("│ [2] 락커조회 - 락커번호  │");
				System.out.println("│ [3] 락커추가             │");
				System.out.println("│ [4] 락커수정             │");
				System.out.println("│ [5] 락커삭제             │");
				System.out.println("│ [6] 일괄삭제             │");
				System.out.println("│ [0] 돌아가기             │");
				System.out.println("└──────────────────────────┘");
				
				// 사용자의 키보드 입력 문자열을 변수에 저장
				choice = userInput.nextLine();	
				
				System.out.println(" ▶ 선택 : " + choice);
				
				/* 세부 기능으로 진입하게 할 입력 유형 정의하고, 분기해서 해당 기능 수행하는 함수 호출 */
				if(choice.contentEquals("0")) {
					
					// "0" 입력시 종료 문구 출력한 후 루프를 빠져나가고 mainMenu 루프로 돌아감
					System.out.println("┌──────────────────────────┐");
					System.out.println("│           EXIT           │");
					System.out.println("└──────────────────────────┘");
					
					break;
		
				} else if(choice.contentEquals("1")) {
					
					// "1" 입력 시 회원 ID로 사물함 정보 조회 기능을 제공하는 LockerDAO 객체의 searchLockerByMemberId 함수 참조하여 호출
					lockerDAO.searchLockerByMemberId();
				} else if(choice.contentEquals("2")) {
					
					// "2" 입력 시 번호로 사물함 정보 조회 기능을 제공하는 LockerDAO 객체의 searchLockerByNumber 함수 참조하여 호출
					lockerDAO.searchLockerByNumber();
				} else if(choice.contentEquals("3")) {
					
					// "3" 입력 시 사물함 배정(추가) 기능을 제공하는 LockerDAO 객체의 addLockerToMembe 함수 참조하여 호출
					lockerDAO.addLockerToMember();
				} else if(choice.contentEquals("4")) {
					
					// "4" 입력 시 사물함 정보 수정 기능을 제공하는 LockerDAO 객체의 updateLockerInfo 함수 참조하여 호출
					lockerDAO.updateLockerInfo();
				} else if(choice.contentEquals("5")) {
					
					// "5" 입력 시 사물함 정보 삭제 기능을 제공하는 LockerDAO 객체의 deleteMemberFromLocker 함수 참조하여 호출
					lockerDAO.deleteMemberFromLocker();
				} else if(choice.contentEquals("6")) {
					
					// "6" 입력 시 만료일자가 지난 사물함 정보 일괄 삭제 기능을 제공하는 LockerDAO 객체의 sweepOutExpiredLockers 함수 참조하여 호출
					lockerDAO.sweepOutExpiredLockers();
				} else {
					
					// 정의하지 않은 입력 유형은 분기 시작점으로 돌아가도록 처리
					System.out.println("┌──────────────────────────┐");
					System.out.println("│     잘못된 선택입니다    │");
					System.out.println("└──────────────────────────┘");
					continue;
				} // if - else End
				
			} catch (Exception e) {
				
				// 예외 발생 시 콘솔 창과 로그에 메시지 출력하고 루프 빠져나가며 함수 종료
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           ERROR          │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : lockerManage() " + e.getMessage());
				e.printStackTrace();
				break;				
			} // try - catch End
		} // while End
	}
	
	/**
	 * function명 	: memberStatics
	 * function설명	: 1. 회원통계 기능에 요구되는 [1] 회원통계 동작이 작동하는 함수
	 * 				  2. 세부 기능을 선택하도록 사용자에게 입력을 받고, 입력받은 값 검증하여 해당하는 기능을 수행하는 함수 호출
	 * 				  3. mainMenu 함수와 마찬가지로 "0" 입력시, 해당 기능 루프의 종료 분기가 되어 빠져나감.
	 * 				  4. 정의한 유형의 사용자 입력 받을 시 해당하는 세부 기능으로 분기가 갈라짐
	 * 			      5. 정의되지 않은 유형의 입력을 받을 시, 다시 입력 받도록 처음으로 돌아감.
	 */
	public static void memberStatics() {

		/* 사용자가 선택한 메뉴 옵션을 저장할 String 변수 */
		String choice;
		
		/* 원하는 시점에 메뉴에서 빠져나갈 수 있도록 루프를 돌리며 기능을 수행하고 종료 분기 설정 */
		while(true) {
			try {
				
				System.out.println("┌──────────────────────────┐");
				System.out.println("│ [1] 회원통계             │");
				System.out.println("│ [0] 돌아가기             │");
				System.out.println("└──────────────────────────┘");
				
				// 사용자의 키보드 입력 문자열을 변수에 저장
				choice = userInput.nextLine();	
				
				System.out.println(" ▶ 선택 : " + choice);
				
				/* 기능으로 진입하거나 기능에서 빠져나갈 수 있도록 입력 유형 정의하고 분기 설정 */
				if(choice.contentEquals("0")) {
					
					// "0" 입력시 종료 문구 출력한 후 루프를 빠져나가고 mainMenu 루프로 돌아감
					System.out.println("┌──────────────────────────┐");
					System.out.println("│           EXIT           │");
					System.out.println("└──────────────────────────┘");
					break;
				} else if(choice.contentEquals("1")) {
					
					// "1" 입력 시 회원 통계 정보를 처리하고 출력하는 기능을 제공하는 MemberStatics 객체의 showStatistics 함수 참조하여 호출
					memberStatics.showStatistics();
					// 출력이 완료되어도 원할 때 기능을 종료할 수 있도록 루프 유지하도록 설정
					continue;
				} else {
					
					// 정의하지 않은 입력 유형은 분기 시작점으로 돌아가도록 처리
					System.out.println("┌──────────────────────────┐");
					System.out.println("│     잘못된 선택입니다    │");
					System.out.println("└──────────────────────────┘");
					continue;
				} // if - else End
				
			} catch (Exception e) {
				
				// 예외 발생 시 콘솔 창과 로그에 메시지 출력하고 루프 빠져나가며 함수 종료
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           ERROR          │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : memberStatics() " + e.getMessage());
				e.printStackTrace();
				break;				
			} // try - catch End
		} // while End
	}
	
	/**
	 * function명 	: printSelectMenu
	 * function설명	: 1. 메인 메뉴에서 [1]회원관리, [2]사물함관리, [3]회원통계, [0]종료의 4가지 기능을 선택할 수 있도록 사용자 입력을 받음
	 * 				  2. 입력받은 값은 checkUserInput 함수 호출하여 검증된 값으로 반환하여 돌려줌.
	 * 				  3. 정의한 유형의 사용자 입력 받을 시 예외가 발생하지 않도록 반환값을 주어 메인 루프에서 판단하고 입력을 다시 받을 수 있게 함
	 */
	public static void printSelectMenu() {
		
		/* 사용자가 선택한 메뉴 옵션을 저장할 String 변수 */
		String inputStr;
		
		/* 메뉴 옵션별로 switch 문에서 분기를 나누는 역할을 하는 정수형 변수 */
		int selectedOption;
		
		System.out.println(" [Message] : 메뉴를 선택하세요 ");
		
		// 선언한 문자열 변수에 사용자의 입력값을 받아 저장함
		inputStr = userInput.nextLine();
		
		// 사용자로부터 입력받은 String 값을 판별하여 유형별로 int 값으로 반환하는 checkUserInput 함수 호출
		selectedOption = checkUserInput(inputStr);
		
		/* 메인 메뉴가 처리하는 5가지 메뉴 옵션과, 예외 유형을 정의하여 menuOption 전역변수에 저장 */
		switch (selectedOption) {
		case 0:
			
			// 사용자가 0 입력 시 전역변수 menuOption에 "0" 저장
			menuOption = inputStr;
			System.out.println(" ▶ 입력 : " + menuOption);
			break;
		case 1:
			
			// 사용자가 1 입력 시 전역변수 menuOption에 "1" 저장
			menuOption = inputStr;
			System.out.println(" ▶ 입력 : " + menuOption);
			break;
		case 2:
			
			// 사용자가 2 입력 시 전역변수 menuOption에 "2" 저장
			menuOption = inputStr;
			System.out.println(" ▶ 입력 : " + menuOption);
			break;
		case 3:
			
			// 사용자가 3 입력 시 전역변수 menuOption에 "3" 저장
			menuOption = inputStr;
			System.out.println(" ▶ 입력 : " + menuOption);
			break;
		case 4:
			
			// 사용자가 4 입력 시 전역변수 menuOption에 "4" 저장
			menuOption = inputStr;
			System.out.println(" ▶ 입력 : " + menuOption);
			break;
		case 5:
			
			// 사용자가 5 입력 시 전역변수 menuOption에 "5" 저장
			menuOption = inputStr;
			System.out.println(" ▶ 입력 : " + menuOption);
			break;
		case -1:
			
			// 정의되지 않은 입력 유형은 -1로 반환되어 분류됨.
			System.out.println("┌──────────────────────────┐");
			System.out.println("│     잘못된 선택입니다    │");
			System.out.println("└──────────────────────────┘");
			break;
		default:
			
			// -1로 분류하였으나 default case는 무조건 정의. (-1 case와 동일하게 설정하여 처리)
			System.out.println("┌──────────────────────────┐");
			System.out.println("│     잘못된 선택입니다    │");
			System.out.println("└──────────────────────────┘");
			break;
		}
	}

	/**
	 * function명 	    : checkUserInput
	 * function설명	    : 1. 사용자가 입력한 값을 판단하고 그 결과를 분류하여 정수형 값으로 해석하는 기능을 하는 함수
	 * 					  2. 호출한 함수에서 인자로 넘겨 받은 문자열을 해석하여 7가지 정수로 분류하여 정수값을 반환
	 * 					  3. 해당 함수는 mainMenu 뿐만이 아닌, 각 기능 별 함수에서도 호출하여 사용하기 때문에 분류할 수 있는 유형으로 정의하여
	 * 					  안전한 값으로 바꾸어 넘겨주기만 함. 넘겨받은 분류값에 대해서는 각 기능을 수행하는 함수에서 개별적으로 판단함
	 * @param  inputStr	: 사용자의 키보드 입력을 받는 함수가 해당 함수를 호출하여 입력받은 문자열을 인자로 넘겨줌.
	 * @return int      : 입력한 문자열을 동일한 의미로 해석할 수 있는 정수형으로 반환(비교 처리 시 간단하게 하려고 String 대신 int 사용)
	 */
	public static int checkUserInput(String inputStr) {	
		
		/* 전달받은 문자열을 7가지 값으로 정의하여 분류한 후 해당하는 정수형 값을 반환 */
		if (inputStr.equals("1")) {
			
			// 문자열 "1" 입력시 정수 1 반환
			return 1;
		} else if (inputStr.equals("2")) {
			
			// 문자열 "2" 입력시 정수 2 반환
			return 2;
		} else if (inputStr.equals("3")) {
			
			// 문자열 "3" 입력시 정수 3 반환
			return 3;
		} else if (inputStr.equals("4")) {
			
			// 문자열 "4" 입력시 정수 4 반환
			return 4;
		} else if (inputStr.equals("5")) {
			
			// 문자열 "5" 입력시 정수 5 반환
			return 5;
		} else if (inputStr.equals("6")) {
			
			// 문자열 "6" 입력시 정수 6 반환
			return 6;
		} else if (inputStr.equals("0")) {
			
			// 문자열 "0" 입력시 정수 0 반환
			return 0;
		} else {
			
			// 분류한 유형의 값이 아닐 경우 정수 -1을 반환하여 예외 케이스로 판단하게 설정
			return -1;
		}
	}
	
	/**
	 * function명 	        : confirmDataChange
	 * function설명	        : 1. 데이터 [1]추가, [2]수정, [3]삭제 기능 수행 시, 기능 수행을 확정할 것인지 확인하는 역할을 하는 함수
	 * 						  2. 세 가지 경우([1]추가, [2]수정, [3]삭제)에 따라 상황에 맞는 안내 문구를 출력함
	 * 						  3. 사용자의 의사 결정 입력값의 유효성을 검증하는 checkYN 함수를 호출하여 입력을 받고 결과에 따라 참, 거짓으로 반환 값 전달
	 * 						  4. 세 가지 유형([1]추가, [2]수정, [3]삭제)은 switch 문으로 문구를 나누어 출력을 수행
	 * @param  mode     	: 사용자의 키보드 입력을 받는 함수가 해당 함수를 호출하여 입력받은 문자열을 인자로 넘겨줌.
	 * @return boolean      : 입력한 문자열을 동일한 의미로 해석할 수 있는 정수형으로 반환(비교 처리 시 간단하게 하려고 String 대신 int 사용)
	 */
	public static boolean confirmDataChange(int mode) {
		
		/* 세 가지 유형([1]추가, [2]수정, [3]삭제)의 출력 문구를 switch 문으로 문구를 나누어 출력을 수행 */
		switch(mode) {
		case 1 :
			
			// mode = 1 case : 데이터를 추가하는 경우 출력할 문구와 반환할 값
			System.out.println("┌──────────────────────────┐");
			System.out.println("│     등록하시겠습니까?    │");
			System.out.println("└──────────────────────────┘");
			
			/* 입력값이 Y와 N이 맞는지 검증하여 안전한 값으로 반환하는 checkYN 함수를 호출하고 결과에 따라 true or false 리턴 */
			if (checkYN().equals("Y")) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│       등록되었습니다     │");
				System.out.println("└──────────────────────────┘");
				return true;
			} else {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│       취소하였습니다     │");
				System.out.println("└──────────────────────────┘");
				return false;
			}
		case 2 :
			
			// mode = 2 case : 데이터를 수정하는 경우 출력할 문구와 반환할 값
			System.out.println("┌──────────────────────────┐");
			System.out.println("│     수정하시겠습니까?    │");
			System.out.println("└──────────────────────────┘");
			
			/* 입력값이 Y와 N이 맞는지 검증하여 안전한 값으로 반환하는 checkYN 함수를 호출하고 결과에 따라 true or false 리턴 */
			if (checkYN().equals("Y")) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│       수정되었습니다     │");
				System.out.println("└──────────────────────────┘");
				return true;
			} else {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│       취소하였습니다     │");
				System.out.println("└──────────────────────────┘");
				return false;
			}
		case 3 :
			
			// mode = 3 case : 데이터를 삭제하는 경우 출력할 문구와 반환할 값
			System.out.println("┌──────────────────────────┐");
			System.out.println("│     삭제하시겠습니까?    │");
			System.out.println("└──────────────────────────┘");
			
			/* 입력값이 Y와 N이 맞는지 검증하여 안전한 값으로 반환하는 checkYN 함수를 호출하고 결과에 따라 true or false 리턴 */
			if (checkYN().equals("Y")) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│       삭제되었습니다     │");
				System.out.println("└──────────────────────────┘");
				return true;
			} else {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│       취소하였습니다     │");
				System.out.println("└──────────────────────────┘");
				return false;
			}
		
		
		default :
			return false;
		}
	}
	
	/**
	 * function명 	  : checkYN
	 * function설명	  : 1. 사용자의 의사결정 여부를 키보드 입력을 통해 문자열로 받아 변수에 저장
	 * 					2. 저장한 변수 값을 미리 정의한 두 가지 값("Y","N")으로 나누어 판별
	 * 					3. 소문자 및 대문자 입력 시 동일하게 처리되도록 조건문을 설정함
	 * @return String : 입력한 문자열을 해당 함수를 호출한 곳에서 판단하도록 정의한 값("Y","N")으로 나누어 판단하여 반환
	 */
	public static String checkYN() {

		/* 사용자의 입력을 저장할 String 변수 */
		String answer = "";
		
		/* 정의한 유형의 입력값이 들어올 때까지 입력을 받도록 하는 루프 */
		while(true) {
			System.out.println("┌──────────────────────────┐");
			System.out.println("│     [Y] 진행 [N] 취소    │");
			System.out.println("└──────────────────────────┘");
			
			// 선언한 문자열 변수에 사용자의 입력을 받음
			answer = userInput.nextLine();
			
			/* 입력값이 "Y"("y") 혹은 "N"("n") 일 때만 분류하여 해당하는 값을 리턴하고, 두 경우에 속하지 않을 시 루프 시작점으로 돌아가 다시 입력을 받음 */
			if (answer.equals("Y") || answer.equals("y")) {
				return "Y";
			} else if (answer.equals("N") || answer.equals("n")) {
				return "N";
			} else {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│     잘못된 입력입니다    │");
				System.out.println("└──────────────────────────┘");
				continue;
			}
		}		
	}
	
	/**
	 * function명 	: getCurrentDate
	 * function설명	: 1. 프로그램이 실행되고 있는 현재 시간을 구해 년, 월, 일 형식으로 바꾸고 문자열 값을 반환해주는 함수
	 * 				  2. 회원 정보과 사물함 정보의 만료기간 여부를 판별할 때 해당 함수를 이용하여 현재일자와 만료일자를 비교함
	 * @return String : 변환된 현재 시간(년, 월, 일)을 yyyy-MM-dd 형식의 문자열 값으로 반환
	 */
	public static String getCurrentDate() {
		
		int nYear;
		int nMonth;
		int nDay;
		String today = "";
		
		// 현재 날짜 구하기
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		nYear = calendar.get(Calendar.YEAR);
		nMonth = calendar.get(Calendar.MONTH) + 1;
		nDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		if (nMonth >= 1 && nMonth <= 9) {
			today = nYear + "-0" + nMonth + "-" + nDay;
		} else {
			today = nYear + "-" + nMonth + "-" + nDay;
		}
		
		return today;
	}
	
	/**
	 * function명 	: getDiffOfDate
	 * function설명	: 인자로 입력한 두 날짜의 차이를 하루 단위로 계산하는 함수
	 * @param start : Date 형으로 변환될 문자열 타입의 기준 일자
	 * @param end   : Date 형으로 변환될 문자열 타입의 비교 일자
	 * @return long : 두 일자를 비교하여 나온 하루 단위의 날짜 차이 값
	 * @throws Exception
	 */
	public static long getDiffOfDate(String start, String end) throws Exception {
		
		/* 입력받은 문자열로된 일자를 원하는 형식의 Date 타입으로 변환하기 위한 객체 선언 */
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		/* 입력받은 start 문자열을 Date 타입의 기준일자로 변환 */
		Date beginDate = formatter.parse(start);
		
		/* 입력받은 end 문자열을 Date 타입의 비교일자로 변환 */
		Date endDate = formatter.parse(end);
			
		// 시간차이를 시간, 분, 초를 곱한 값으로 나누면 하루 단위가 나온다.
		// diffDays > 0 : diffDays 만큼 남음
		// diffDays < 0 : diffDays 만큼 초과
		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff / (24 * 60* 60* 1000);
						
		// 계산한 값을 반환한다
		return diffDays;
	}
}

