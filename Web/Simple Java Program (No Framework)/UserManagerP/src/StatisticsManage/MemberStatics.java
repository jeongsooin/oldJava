package StatisticsManage;

import java.util.Iterator;

import MainMenuManage.MainMenu;

/**
 * @Class Name : MemberStatics.java
 * @Description : 회원 정보를 처리하여 기준에 따라 통계를 내고, 그 결과를 출력하는 기능을 정의하는 클래스
 * @Modification information
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
public class MemberStatics {
	
	/**
	 * function명 	: countAllMembers
	 * function설명	: MainMenu 클래스에 선언된 모든 회원 정보를 저장하는 HashMap의 크기값을 반환하는 함수
	 * @return int  : 모든 회원 정보를 저장하는 HashMap의 크기
	 */
	public int countAllMembers() {
		
		/* 회원 수를 저장할 정수현 변수 선언 */
		int count = 0;
		
		/* HashMap을 돌며 검색할 수 있도록 반복자 선언*/
		Iterator<String> userList = MainMenu.allUsers.getAllUsers().keySet().iterator();
		
		/* 반복자가 가리키는 값이 null이 아닐 때까지 while 문을 돌며 반복 */
		while(userList.hasNext()) {
			
			// HaspMap에서 현재 가리키고 있는 값이 null 이 아님 == 데이터가 존재함 => 회원 수 count 증가시킴
			if(userList.next() != null) {
				count = count + 1;
			}
		}
		
		// 계산한 회원 수 반환
		return count;
	}
	
	/**
	 * function명 	: countImminentMembers
	 * function설명	: 회원상태가 [임박] (만료일자가 5일 이내로 남음) 인 회원의 수를 세어 그 값을 반환하는 함수
	 * @return int  : 회원상태가 [임박] (만료일자가 5일 이내로 남음) 인 회원의 수
	 */
	public int countImminentMembers() {
		
		/* 만료기간이 임박한 회원 수를 저장한 정수현 변수 선언 */
		int count = 0;
		
		/* HashMap을 돌며 검색할 수 있도록 반복자 선언*/
		Iterator<String> userList = MainMenu.allUsers.getAllUsers().keySet().iterator();
		
		/* 반복자가 가리키는 값이 null이 아닐 때까지 while 문을 돌며 반복 */
		while(userList.hasNext()) {
			
			// 반복자가 현재 가리키는 값을 검색할 Key(idInList) 값으로 저장
			String idInList = userList.next();
			
			// 해당 Key값의 Value(MemberDTO 객체)에 저장된 회원 상태가 "3"(임박)일 경우 만료가 임박한 회원 수 count 증가
			if (MainMenu.allUsers.getAllUsers().get(idInList).getuState().equals("3")) {
				count = count + 1;
			}
		}
		
		// 계산한 만료일자 임박한 회원 수 반환
		return count;
	}
	
	/**
	 * function명 	: countExpiredMembers
	 * function설명	: 회원상태가 [만료] (만료일자가 지남)인 회원의 수를 세어 그 값을 반환하는 함수
	 * @return int  : 회원상태가 [만료] (만료일자가 지남)인 회원의 수
	 */
	public int countExpiredMembers() {
		
		/* 만료기간이 지나 만료된 회원 수를 저장한 정수현 변수 선언 */
		int count = 0;
		
		/* HashMap을 돌며 검색할 수 있도록 반복자 선언*/
		Iterator<String> userList = MainMenu.allUsers.getAllUsers().keySet().iterator();
		
		/* 반복자가 가리키는 값이 null이 아닐 때까지 while 문을 돌며 반복 */
		while(userList.hasNext()) {
			
			// 반복자가 현재 가리키는 값을 검색할 Key(idInList) 값으로 저장
			String idInList = userList.next();
			
			// 해당 Key값의 Value(MemberDTO 객체)에 저장된 회원 상태가 "2"(만료)일 경우 만료된 회원 수 count 증가
			if (MainMenu.allUsers.getAllUsers().get(idInList).getuState().equals("2")) {
				count = count + 1;
			}
		}
		
		// 계산한 만료된 회원 수 반환
		return count;
	}
	
	/**
	 * function명 	: allMembersList
	 * function설명	: 모든 회원 정보가 저장된 HashMap을 순차적으로 돌면서 Key에 해당하는 회원 ID를 화면에 출력하는 함수
	 */
	public void allMembersList() {
		
		/* HashMap을 돌며 검색할 수 있도록 반복자 선언*/
		Iterator<String> userList = MainMenu.allUsers.getAllUsers().keySet().iterator();
		
		/* 회원 수를 저장할 정수현 변수 선언 */
		int count = 1;
		
		System.out.println("============================");
		
		/* 반복자가 가리키는 값이 null이 아닐 때까지 while 문을 돌며 반복 */
		while(userList.hasNext()) {
			
			String idInList = userList.next();
			
			// 회원 수 카운트 한 것을 번호로 하여(시작값 1) 회원 ID와 함께 출력
			System.out.println(" ▶ "+ count + ". " + idInList);
			
			// 회원 수 카운트
			count = count + 1;
		}

		System.out.println("============================");
	}
	
	/**
	 * function명 	: showStatistics
	 * function설명	: 1. countAllMembers 함수를 호출하여 전체 회원 수를 구해 화면에 출력한다
	 * 				  2. countImminentMembers 함수를 호출하여 만료일자가 임박한 회원의 수를 구해 화면에 출력한다
	 * 				  3. countExpiredMembers 함수를 호출하여 만료일자가 지나 만료된 회원의 수를 구해 화면에 출력한다
	 */
	public void showStatistics() {
		System.out.println("┌──────────────────────────┐");
		System.out.println("│ 전체 회원 수 : " + countAllMembers() + " 명      │");       // countAllMembers 호출하여 전체 회원 수 구하여 출력
		System.out.println("│ 임박 회원 수 : " + countImminentMembers() + " 명      │");  // countImminentMembers 호출하여 만료기간 임박한 회원 수 구하여 출력
		System.out.println("│ 만료 회원 수 : " + countExpiredMembers() + " 명      │");   // countExpiredMembers 호출하여 만료기간이 지난 회원 수 구하여 출력
		System.out.println("└──────────────────────────┘");
		allMembersList(); 																  // allMembersList 호출하여 모든 회원 ID를 출력
	}

}
