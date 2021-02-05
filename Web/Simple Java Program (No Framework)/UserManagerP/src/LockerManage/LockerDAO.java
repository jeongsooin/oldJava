package LockerManage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import MainMenuManage.MainMenu;

/**
 * @Class Name : LockerDAO.java
 * @Description : [1] ID로 조회, [2] 번호로 조회, [3] 사물함 등록, [4] 사물함 정보 수정, [5] 사물함 정보 삭제, [6] 사물함 정보 일괄 삭제 기능을 구현한 클래스
 * 				  LockerDTO에 저장된 [1]사물함번호, [2]회원 ID, [3]만료일자 정보를 처리한다.
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
public class LockerDAO {

	/* LockerDAO 클래스 내부에서 쓰일 Scanner 클래스 객체를 전역변수로 선언함 */
	Scanner userInput;
	
	// 기본 생성자. 생성 시에 Scanner 클래스 객체 생성
	public LockerDAO() {
		userInput = new Scanner(System.in);
	}
	
	/**
	 * function명 	: countLockers
	 * function설명	: 사물함 갯수를 99개로 제한하기 위해, 현재 사물함번호와 사물함정보 클래스를 Key와 Value로 갖는 HashMap의 size를 구하여 정수값으로 반환한다.
	 * @return int  : 현재 MainMenu 클래스에 선언된 HashMap의 <Key, Value> 쌍의 갯수를 저장하여 반환하는 값
	 */
	public int countLockers() {
		
		// 저장한 int 형 변수를 선언하고 HashMap의 size를 구하여 변수에 저장한다
		int count = MainMenu.lockers.getLockers().size();
		
		return count;
	}
	
	/**
	 * function명 	: searchLockerByMemberId
	 * function설명	: 1. 회원 ID를 통해 해당 회원 ID에 배정된 사물함 번호를 조회하여 정보를 출력하는 함수
	 * 				  2. 처음에 Iterator를 생성하여 HashMap을 돌면서 주어진 회원 ID 값을 정보로 갖는 <Key, Value> 쌍이 있는지 검사한다.
	 * 				  3. 검사해서 결과값이 존재하면 해당하는 결과값의 정보를 화면에 출력하고, 없으면 다시 검색할 회원 ID를 입력받는다.
	 */
	public void searchLockerByMemberId() {
		
		/* 사물함번호를 저장할 String 타입의 변수 선언 */
		String lockerNumber = "";
		
		/* 만료일자를 저장할 String 타입의 변수 선언 */
		String expireDate = "";
		
		/* 회원 ID를 저장할 String 타입의 변수 선언 */
		String memberId = "";
		
		/* 검색 결과가 존재하는지 여부를 true, false 값으로 저장할 boolan 타입 변수 선언 */
		boolean isExist = false;
		
		System.out.println("┌──────────────────────────┐");
		System.out.println("│    회원ID로 락커검색     │");
		System.out.println("└──────────────────────────┘");

		System.out.println("┌──────────────────────────┐");
		System.out.println("│    회원ID를 입력하세요   │");
		System.out.println("└──────────────────────────┘");
		
		// 검색할 회원 ID를 사용자에게 입력받음
		memberId = userInput.nextLine();	
				
		System.out.println(" ▶ 입력 : " + memberId);
		
		// 사물함 정보가 저장된 HashMap을 돌며 반복할 반복자를 선언
		Iterator<String> lockerList = MainMenu.lockers.getLockers().keySet().iterator();
		
		/* 반복자를 통해 HashMap을 순차적으로 돌면서 주어진 회원 ID 데이터를 갖고 있는 <Key, Value> 쌍 탐색 */
		while(lockerList.hasNext()) {
			
			// 반복자가 현재 가리키고 있는 데이터를 검색할 사물함 번호로 설정
			lockerNumber = lockerList.next();
			if (MainMenu.lockers.getLockers().get(lockerNumber).getMemberId().equals(memberId)) {
				
				// 검색 대상 정보가 존재함 => 데이터 참조하여 출력할 때 판별할 변수를 true로 변경
				isExist = true;
				
				// 정보 조회하여 데이터를 변수에 저장
				expireDate = MainMenu.lockers.getLockers().get(lockerNumber).getExpireDate();
				
				// 루프를 빠져나가 조회 결과를 출력할 수 있도록 준비
				break;
			} 
		}
		
		// 입력한 회원 ID 정보를 갖고 있는 사물함 번호가 HashMap에 존재할 시 
		if(isExist) {
			
			// 저장된 데이터를 출력
			System.out.println(" ▶ 락커번호 : " + lockerNumber);
			System.out.println(" ▶ 만료일자 : " + expireDate);
		} else {
			
			// 없을 시 출력 문구
			System.out.println(" ▶ 결과없음 ");
		}
	}
	
	/**
	 * function명 	: searchLockerByNumber
	 * function설명	: 입력한 사물함 번호로 해당 사물함에 저장된 정보를 조회하여 출력하는 함수
	 */
	public void searchLockerByNumber() {
		
		String lockerNumber = "";
		
		System.out.println("┌──────────────────────────┐");
		System.out.println("│   락커번호로 락커조회    │");
		System.out.println("└──────────────────────────┘");

		System.out.println("┌──────────────────────────┐");
		System.out.println("│   락커번호를 입력하세요  │");
		System.out.println("└──────────────────────────┘");
				
		lockerNumber = userInput.nextLine();	
				
		System.out.println(" ▶ 입력 : " + lockerNumber);
		
		// 사물함 번호에 해당하는 정보가 HashMap에 존재할 시 
		if(MainMenu.lockers.getLockers().get(lockerNumber) != null) {
			
			// 저장된 데이터를 출력
			System.out.println(" ▶ 락커번호 : " + lockerNumber);
			System.out.println(" ▶ 회원 ID  : " + MainMenu.lockers.getLockers().get(lockerNumber).getMemberId());
			System.out.println(" ▶ 만료일자 : " + MainMenu.lockers.getLockers().get(lockerNumber).getExpireDate());
		} else {
			
			// 없을 시 출력 문구
			System.out.println(" ▶ 결과없음 ");
		}
	}
	
	/**
	 * function명   : addLockerToMember
	 * function설명 : 1. 입력하는 사물함 번호에 배정된 회원 ID가 있는지 먼저 검증
	 * 				  2. 그 다음으로 저장된 사물함 정보 수가 99개를 초과하는지 검증
	 * 				  3. 저장된 정보가 있으면 다른 사물함 번호를 입력받음
	 * 				  4. 사물함 수가 이미 99개에 도달해 있으면 루프를 빠져나가 메뉴 선택 분기로 돌아감
	 * 				  5. 사물함 숫자가 99개 미만이고, 입력한 사물함 번호에 저장된 회원 정보가 없을 시 정보 입력하여 저장
	 * @return int  : 정보 추가에 성공하면 1, 실패하면 0을 결과값으로 반환
	 */
	public int addLockerToMember() {
		
		String lockerNumber = "";		
		String expireDate = "";
		String memberId = "";
		
		System.out.println("┌──────────────────────────┐");
		System.out.println("│         락커등록         │");
		System.out.println("└──────────────────────────┘");

		/* 루프를 돌며 추가할 사물함 번호를 입력받는다 */
		while(true) {
			try {	

				System.out.println("┌──────────────────────────┐");
				System.out.println("│   락커번호를 입력하세요  │");
				System.out.println("└──────────────────────────┘");
				
				lockerNumber = userInput.nextLine();	
				
				System.out.println(" ▶ 입력 : " + lockerNumber);

				// 입력하는 사물함 번호에 배정된 회원 ID가 있는지 먼저 검증
				if(MainMenu.lockers.getLockers().get(lockerNumber) != null) {
					
						System.out.println("┌──────────────────────────┐");
						System.out.println("│    이미 사용중 입니다    │");
						System.out.println("└──────────────────────────┘");
						
						// 저장된 정보가 있으면 다른 사물함 번호를 입력받음
						continue;
						
				// 사물함 수가 이미 99개에 도달해 있으면 루프를 빠져나가 메뉴 선택 분기로 돌아감		
				} else if (countLockers() >= 99) {
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  사용가능한 락커가       │");
					System.out.println("│       없습니다 (99/99)   │");
					System.out.println("└──────────────────────────┘");
					
					continue;
					
				} else {

					System.out.println("┌──────────────────────────┐");
					System.out.println("│  등록할 ID를 입력하세요  │");
					System.out.println("└──────────────────────────┘");
					
					memberId = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + memberId);
				
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  만료일자를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					expireDate = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + expireDate);
					
					//Exception exception = new Exception("예외 테스트");
					//throw exception;
					break;
				}
				
			} catch(Exception e) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           ERROR          │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : addLockerToMember() " + e.getMessage());
				e.printStackTrace();

				// 실패시 0 리턴
				return 0;
			}
		}
		
		/* 데이터 처리를 확정할 것인지 취소할 것인지 입력을 받아 판단하여 결과에 따라 수행한다 */
		if (MainMenu.confirmDataChange(1)) {			
			MainMenu.lockers.setLocker(lockerNumber, memberId, expireDate);
			System.out.println("┌──────────────────────────┐");
			System.out.println("│      락커등록 완료       │");
			System.out.println("└──────────────────────────┘");

			// 성공시 1 리턴
			return 1;
		} else {
			
			System.out.println("┌──────────────────────────┐");
			System.out.println("│      락커등록 취소       │");
			System.out.println("└──────────────────────────┘");

			// 실패시 0 리턴
			return 0;
		}
	}
	
	/**
	 * function명 	: updateLockerInfo
	 * function설명	: 입력하는 사물함 번호에 해당하는 회원ID와 만료일자 정보를 수정하는 함수
	 * @return int  : 정보 수정에 성공하면 1, 실패하면 0을 결과값으로 반환
	 */
	public int updateLockerInfo() {
		
		/* 수정 대상이 될 사물함 번호를 저장할 문자열 타입 변수 선언 */
		String lockerNumber = "";
		
		/* 입력한 사물함 번호에 수정되어 저장될 만료일자를 저장하는 문자열 타입 변수 선언 */
		String expireDate = "";
		
		/* 입력한 사물함 번호에 수정되어 저장될 회원 ID를 저장하는 문자열 타입 변수 선언 */
		String memberId = "";
		
		System.out.println("┌──────────────────────────┐");
		System.out.println("│         락커수정         │");
		System.out.println("└──────────────────────────┘");

		/* 루프를 돌며 수정할 사물함 번호를 입력받는다 */
		while(true) {
			try {	

				System.out.println("┌──────────────────────────┐");
				System.out.println("│   락커번호를 입력하세요  │");
				System.out.println("└──────────────────────────┘");
				
				lockerNumber = userInput.nextLine();	
				
				System.out.println(" ▶ 입력 : " + lockerNumber);

				/* 수정하려는 사물함 정보가 이미 존재하는지 검사하고 있으면 반복문 시작점으로, 없으면 필요 정보를 입력받게 한다. */
				if(MainMenu.lockers.getLockers().get(lockerNumber) == null) {
					
						System.out.println("┌──────────────────────────┐");
						System.out.println("│ 사용중인 락커가 아닙니다 │");
						System.out.println("└──────────────────────────┘");
						
						continue;
						
				} else {

					/* 변경할 정보들을 사용자에게 입력 받는다 */
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  변경할 ID를 입력하세요  │");
					System.out.println("└──────────────────────────┘");
					
					memberId = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + memberId);
				
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  만료일자를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					expireDate = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + expireDate);
					
					//Exception exception = new Exception("예외 테스트");
					//throw exception;
					break;
				}
				
			} catch(Exception e) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           ERROR          │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : updateLockerInfo() " + e.getMessage());
				e.printStackTrace();

				// 실패시 0 리턴
				return 0;
			}
		}
		
		/* 데이터 처리를 확정할 것인지 취소할 것인지 입력을 받아 판단하여 결과에 따라 수행한다 */
		if (MainMenu.confirmDataChange(2)) {
			
			MainMenu.lockers.getLockers().get(lockerNumber).setMemberId(memberId);
			MainMenu.lockers.getLockers().get(lockerNumber).setExpireDate(expireDate);
			
			System.out.println("┌──────────────────────────┐");
			System.out.println("│      락커수정 완료       │");
			System.out.println("└──────────────────────────┘");

			// 성공시 1 리턴
			return 1;
		} else {
			
			System.out.println("┌──────────────────────────┐");
			System.out.println("│      락커수정 취소       │");
			System.out.println("└──────────────────────────┘");

			// 실패시 0 리턴
			return 0;
		}
	}
	
	/**
	 * function명 	: deleteMemberFromLocker
	 * function설명	: 입력하는 사물함 번호에 해당하는 회원ID와 만료일자 정보를 삭제하는 함수
	 * @return int  : 정보 삭제에 성공하면 1, 실패하면 0을 결과값으로 반환
	 */
	public int deleteMemberFromLocker() {
		
		/* 삭제할 대상이 될 사물함 번호를 저장할 문자열 타입 변수 선언 */
		String lockerNumber = "";		
		
		System.out.println("┌──────────────────────────┐");
		System.out.println("│         락커삭제         │");
		System.out.println("└──────────────────────────┘");

		/* 루프를 돌며 삭제할 사물함 번호를 입력받는다 */
		while(true) {
			try {	

				System.out.println("┌──────────────────────────┐");
				System.out.println("│   락커번호를 입력하세요  │");
				System.out.println("└──────────────────────────┘");
				
				lockerNumber = userInput.nextLine();	
				
				System.out.println(" ▶ 입력 : " + lockerNumber);

				/* 수정하려는 사물함 정보가 이미 존재하는지 검사하고 있으면 반복문을 빠져나가 삭제 수행, 없으면 사물함 번호를 다시 입력받게 한다. */
				if(MainMenu.lockers.getLockers().get(lockerNumber) == null) {
					
						System.out.println("┌──────────────────────────┐");
						System.out.println("│ 사용중인 락커가 아닙니다 │");
						System.out.println("└──────────────────────────┘");
						
						continue;
						
				} else {
					
					//Exception exception = new Exception("예외 테스트");
					//throw exception;
					break;
				}
				
			} catch(Exception e) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           ERROR          │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : updateLockerInfo() " + e.getMessage());
				e.printStackTrace();

				// 실패시 0 리턴
				return 0;
			}
		}
		
		/* 데이터 처리를 확정할 것인지 취소할 것인지 입력을 받아 판단하여 결과에 따라 수행한다 */
		if (MainMenu.confirmDataChange(3)) {
			
			// HashMap에서 사물함 번호를 검색하여 해당하는 데이터를 삭제한다
			MainMenu.lockers.getLockers().remove(lockerNumber);
			
			System.out.println("┌──────────────────────────┐");
			System.out.println("│      락커삭제 완료       │");
			System.out.println("└──────────────────────────┘");

			// 성공시 1 리턴
			return 1;
		} else {
			
			System.out.println("┌──────────────────────────┐");
			System.out.println("│      락커삭제 취소       │");
			System.out.println("└──────────────────────────┘");

			// 실패시 0 리턴
			return 0;
		}
	}
	
	/**
	 * function명 	: sweepOutExpiredLockers
	 * function설명	: 
	 */
	public void sweepOutExpiredLockers() {
		
		/* 기간이 만료되어 정보를 삭제해야 하는 사물함의 정보를 저장할 ArrayList<String> 변수 선언 */
		List<String> expiredLockers = new ArrayList<String>();
		
		/* 저장된 만료일자와 비교할 기준날짜(현재시각)를 계산하여 문자열 형 변수에 저장 */
		String start = MainMenu.getCurrentDate();
		
		/* 저장된 만료일자를 담을 문자열 변수 선언*/
		String end = "";
		
		/* 삭제해야 할 데이터의 갯수가 몇개인지 카운트할 정수형 변수 선언 */
		int count = 0;
		
		// 사물함 정보가 저장된 HashMap을 돌며 반복할 반복자를 선언
		Iterator<String> lockerList = MainMenu.lockers.getLockers().keySet().iterator();
		
		/* 반복자를 통해 HashMap을 순차적으로 돌면서 만료일자를 검색해 기준일자랑 비교하여 삭제할 사물함 번호 탐색 */
		while(lockerList.hasNext()) {
			
			// 반복자가 현재 가리키고 있는 데이터를 검색할 사물함 번호로 설정
			String lockerNumber = lockerList.next();
			
			// 사물함 번호로 HashMap에서 만료일자 얻어서 비교일자 값으로 저장
			end = MainMenu.lockers.getLockers().get(lockerNumber).getExpireDate();
			
			try {
				// 만료일자와 현재일자간의 차이 구해서, 만료된 사물함(계산한 값이 0보다 작음)의 번호를 ArrayList에 저장
				if (MainMenu.getDiffOfDate(start, end) <= 0) {
					expiredLockers.add(lockerNumber);
					count = count + 1;
				}
				
			} catch (Exception e) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           ERROR          │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : sweepOutExpiredLockers() " + e.getMessage());
				e.printStackTrace();
			} // try - catch End
		} // while End
		
		// ArrayList의 크기만큼 반복하며 Key값에 저장된 Value 값을 HashMap에서 삭제
		for(int i = 0; i < expiredLockers.size(); i++) {
			MainMenu.lockers.getLockers().remove(expiredLockers.get(i));
		}
		
		System.out.println(" ▶ 총 [ " + count + " ] 개의 만료된 사물함 정보 삭제됨 ");
	}
}
