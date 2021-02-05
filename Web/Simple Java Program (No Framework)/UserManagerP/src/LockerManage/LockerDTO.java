package LockerManage;

import java.util.HashMap;

import MainMenuManage.MainMenu;

/**
 * @Class Name : LockerDTO.java
 * @Description 사물함 관리 기능에서 처리해야 하는 데이터를 정의하고 저장하는 클래스
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
public class LockerDTO {
	
	/* 저장할 사물함 정보들을 HashMap 형태로 모아서 관리하도록 변수 선언. 이때 Key 값은 사물함번호, Value값은 회원 ID와 만료일자 정보를 갖는 LockerInfo 클래스 객체*/
	private HashMap<String, LockerInfo> lockers; // LockerInfo 클래스 -> 해당 클래스의 Inner class
	
	// 기본 생성자. 생성자 호출시 HashMap 할당
	public LockerDTO() {
		lockers = new HashMap<String, LockerDTO.LockerInfo>();
	}
	
	/* 클래스 변수에 대한 Getter 및 Setter */
	public HashMap<String, LockerInfo> getLockers() {
		return lockers;
	}

	public void setLockers(HashMap<String, LockerInfo> lockers) {
		this.lockers = lockers;
	}
	
	// 사물함번호, 회원 ID, 만료일자를 인자로 하여 사물함 정보를 생성하는 Setter. [추가] 기능에서 사용됨
	public void setLocker(String lockerNumber, String memberId, String expireDate) {
		LockerInfo lockerInfo = new LockerInfo(memberId, expireDate);
		MainMenu.lockers.getLockers().put(lockerNumber, lockerInfo);
	}

	/* HashMap의 Value로 저장될 회원ID와 만료일자를 멤버 변수로 갖는 Inner Class */
	class LockerInfo {
		
		/* 회원 ID를 저장하는 문자열 타입 변수 */
		private String memberId;
		
		/* 만료일자를 저장하는 문자열 타입 변수*/
		private String expireDate;
		
		
		// 기본 생성자로 생성시 지정한 기본 값을 가지고 생성됨
		public LockerInfo() {
			memberId = "**********";
			expireDate = "yyyy-MM-dd";
		}
		
		// 회원 ID와 만료일자를 받아서, 이 정보를 가지는 객체를 생성하는 생성자
		public LockerInfo(String memberId, String expireDate) {
			this.memberId = memberId;
			this.expireDate = expireDate;
		}

		/* LockerInfo 클래스의 Getter 및 Setter */
		public String getMemberId() {
			return memberId;
		}

		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}

		public String getExpireDate() {
			return expireDate;
		}

		public void setExpireDate(String expireDate) {
			this.expireDate = expireDate;
		}
				
	}
}
