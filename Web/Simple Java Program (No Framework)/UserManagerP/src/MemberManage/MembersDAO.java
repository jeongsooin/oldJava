package MemberManage;

import java.util.Iterator;
import java.util.Scanner;

import MainMenuManage.*;

/**
 * @Class Name : MembersDAO.java
 * @Description : [1] 조회 [2] 추가, [3] 수정, [4] 삭제 기능을 구현한 클래스
 * 				  MemberDTO에 저장된 정보를 처리한다.
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
public class MembersDAO {
	
	/* MembersDAO 클래스 내부에서 쓰일 Scanner 클래스 객체를 전역변수로 선언함 */
	Scanner userInput;
	
	// 기본 생성자. 생성 시에 Scanner 클래스 객체 생성
	public MembersDAO() {
		this.userInput = new Scanner(System.in);
	}
	
	/**
	 * function명 	   : checkId
	 * function설명    : 회원 ID를 인자로 전달받아, 이미 저장되어있는지 여부를 boolean 형태로 반환하는 함수
	 * @param id       : 저장되어 있는지 확인할 ID를 문자열 타입으로 전달
	 * @return boolean : 입력한 회원ID가 이미 존재하는지 여부를 true, false 값으로 반환
	 */
	public boolean checkId(String id) {
		
		/* Id 존재 여부를 카운트할 정수 타입 변수 선언 */
		int count = 0;
		
		/* HashMap을 돌며 탐색할 수 있도록 반복자 선언 */
		Iterator<String> userList = MainMenu.allUsers.getAllUsers().keySet().iterator();
		
		/* 반복자를 통해 HashMap을 탐색하며 입력받은 ID가 존재하는지 검색 */
		while(userList.hasNext()) {
			
			String idInList = userList.next();
			
			// 해당 ID가 존재하면 카운트를 증가시킴
			if(idInList.equals(id)) {
				count = count + 1;
				// break;         // 사실 하나 검색 됐으면 break 해도 됨.
			}
		}
		
		// 카운트가 0보다 크면 아이디가 존재하는 것이므로 ID를 사용할 수 없다는 의미로 false 리턴
		if (count > 0) {
			return false; 
		} else {
			
			// 사용할 수 있다는 의미로 true 리턴 
			return true;
		}
	}

	/**
	 * function명 	: addUser
	 * function설명	: 1. 입력한 회원 ID가 이미 있는지 먼저 검증
	 * 				  2. 존재하는 ID면 입력을 다시 받도록 루프 시작점으로 되돌려보냄
	 * 				  3. 존재하지 않는다면 나머지 회원정보 항목을 입력받고 이를 바탕으로 MemberDTO 클래스 객체를 생성함
	 * 				  4. 생성한 클래스 객체를 value, 입력한 회원 ID를 Key로 하여 모든 회원 정보를 저장하는 HashMap에 추가
	 * @return int  : 정보 추가에 성공하면 1, 실패하면 0을 결과값으로 반환
	 */
	public int addUser() {

		MemberDTO newUser = new MemberDTO();
		
		String uId;				// 회원 ID
		String uName;			// 회원 이름
		String uGender;			// 회원 성별
		String uPhone;			// 전화 번호
		String uAddress;		// 회원 주소
		String uMemo;			// 회원 설명
		String uStartDate;		// 가입 일자 (YYYY-MM-DD)
		String uExpireDate;		// 만료 일자 (YYYY-MM-DD)
		String uRegigterDate;	// 등록 일시
		String uUpdateDate;     // 수정 일시
		long diffDays;
		
		System.out.println("┌──────────────────────────┐");
		System.out.println("│         회원등록         │");
		System.out.println("└──────────────────────────┘");
		
		// 회원 상태를 업데이트 하는 refreshStatus 함수 호출
		refreshStatus();
		
		/* 루프를 돌며 추가할 회원 ID를 입력받는다 */
		while(true) {
			try {	

				System.out.println("┌──────────────────────────┐");
				System.out.println("│ 사용하실 ID를 입력하세요 │");
				System.out.println("└──────────────────────────┘");
				
				// 등록할 회원 ID를 입력받는다
				uId = userInput.nextLine();	
				
				System.out.println(" ▶ 입력 : " + uId);

				/* 등록하려하는 회원 ID가 이미 존재하는지 checkID 함수를 호출하여 검사하고 있으면 반복문 시작점으로, 없으면 추가 정보를 입력받게 한다. */
				if(!MainMenu.membersDAO.checkId(uId)) {
						System.out.println("┌──────────────────────────┐");
						System.out.println("│ 이미 존재하는 ID 입니다. │");
						System.out.println("│ 다른 ID를 사용해 주세요. │");
						System.out.println("└──────────────────────────┘");
						continue;
				} else {

					/* 추가 회원 정보들을 사용자로부터 입력 받는다 */
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  회원이름를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uName = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + uName);
					newUser.setuName(uName);
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  회원성별을 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uGender = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + uGender);
					newUser.setuGender(uGender);
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  전화번호를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uPhone = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + uPhone);
					newUser.setuPhone(uPhone);
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  회원주소를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uAddress = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + uAddress);
					newUser.setuAddress(uAddress);
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  회원설명을 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uMemo = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + uMemo);
					newUser.setuMemo(uMemo);
										
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  가입일자를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uStartDate = userInput.nextLine();
					System.out.println(" ▶ 입력 : " + uStartDate);
					newUser.setuStartDate(uStartDate);
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  만료일자를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uExpireDate = userInput.nextLine();
					System.out.println(" ▶ 입력 : " + uExpireDate);
					newUser.setuExpireDate(uExpireDate);
					
					// 현재일자를 기준으로 입력한 만료일자와 비교해서 하루 단위의 날짜 차이를 구하는 getDiffDate 함수를 호출하여 결과를 바탕으로 회원 상태를 설정한다.
					diffDays = MainMenu.getDiffOfDate(MainMenu.getCurrentDate(), uExpireDate);
					
					if (diffDays != 500) {
						if (diffDays > 5) {  
		 					newUser.setuState("1");
						} else if (diffDays >= 0 && diffDays <= 5 ) {
							newUser.setuState("3");
						} else {
							newUser.setuState("2");
						}
					} // if End
					
					newUser.setuIsActive("Y");
					
					// 등록일자와 수정일자는 입력받지 않고, getCurruntDate 함수를 이용해 설정한다.
					uRegigterDate = MainMenu.getCurrentDate();
					uUpdateDate = MainMenu.getCurrentDate();
					
					newUser.setuRegigterDate(uRegigterDate);
					newUser.setuUpdateDate(uUpdateDate);

					//Exception exception = new Exception("예외 테스트");
					//throw exception;
					break;
				}
				
			} catch(Exception e) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           ERROR          │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : addUser() " + e.getMessage());
				e.printStackTrace();

				// 실패시 0 리턴
				return 0;
			}
		}
		
		/* 데이터 처리를 확정할 것인지 취소할 것인지 입력을 받아 판단하여 결과에 따라 수행한다 */
		if (MainMenu.confirmDataChange(1)) {
			MainMenu.allUsers.getAllUsers().put(uId, newUser);
			
			System.out.println("┌──────────────────────────┐ ");
			System.out.println("│      회원등록 완료       │");
			System.out.println("└──────────────────────────┘");
			
			// 완료(성공)시 1 리턴
			return 1;
		} else {
			
			System.out.println("┌──────────────────────────┐ ");
			System.out.println("│      회원등록 취소       │");
			System.out.println("└──────────────────────────┘");

			// 취소(실패)시 0 리턴
			return 0;
		}
	}
	
	/**
	 * function명 	: searchUser
	 * function설명	: 1. 입력한 회원 ID가 존재하는 우선 검증
	 * 				  2. 존재한다면 해당 회원 ID를 Key로 하여 Value를 HashMap에서 검색한 후, 저장된 데이터를 참조하여 화면에 출력
	 * 				  3. 존재하지 않는 ID라면 다른 ID를 입력받도록 루프 시작지점으로 되돌려보냄
	 */
	public void searchUser() {

		/* 회원 정보를 저장할 MemberDTO 클래스 객체를 생성한다 */
		MemberDTO searchUser = new MemberDTO();
		
		/* HashMap의 Key가 되며, 새로 입력할 회원정보의 PK값인 회원 ID를 저장할 문자열 타입 변수를 선언 */
		String uId;
		
		System.out.println("┌──────────────────────────┐");
		System.out.println("│         회원조회         │");
		System.out.println("└──────────────────────────┘");
		
		// 회원 상태를 업데이트 하는 refreshStatus 함수 호출
		refreshStatus();
		
		/* 루프를 돌며 조회할 회원 ID를 입력받는다 */
		while(true) {
			try {	

				System.out.println("┌──────────────────────────┐");
				System.out.println("│ 조회하실 ID를 입력하세요 │");
				System.out.println("└──────────────────────────┘");
								
				uId = userInput.nextLine();	
				
				System.out.println(" ▶ 입력 : " + uId);

				/* 조회할 회원 ID의 정보가 존재하는지 먼저 검사한다 */
				if(MainMenu.membersDAO.checkId(uId)) {
					
						System.out.println("┌──────────────────────────┐");
						System.out.println("│ 존재하지 않는 ID 입니다. │");
						System.out.println("│ ID를 다시 확인해 주세요. │");
						System.out.println("└──────────────────────────┘");
						
						// 존재하지 않으면 루프 시작점으로 돌아가 다시 입력을 받는다 
						continue;
				} else {
					
					// 입력한 회원 ID를 Key로 하여 HashMap에서 MemberDTO value를 찾아 참조한다
					searchUser = MainMenu.allUsers.getAllUsers().get(uId);
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│         회원이름         │");
					System.out.println("└──────────────────────────┘");
					
					System.out.println(" ▶ " + searchUser.getuName());
				
					System.out.println("┌──────────────────────────┐");
					System.out.println("│         회원성별         │");
					System.out.println("└──────────────────────────┘");
					
					System.out.println(" ▶ " + searchUser.getuGender());
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│         전화번호         │");
					System.out.println("└──────────────────────────┘");

					System.out.println(" ▶ " + searchUser.getuPhone());
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│         회원주소         │");
					System.out.println("└──────────────────────────┘");
					
					System.out.println(" ▶ " + searchUser.getuAddress());
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│         회원설명         │");
					System.out.println("└──────────────────────────┘");
					
					System.out.println(" ▶ " + searchUser.getuMemo());
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│         회원상태         │");
					System.out.println("└──────────────────────────┘");
					
					/* 회원상태 데이터를 참조하여 값에 따라 해당하는 문구를 분류하여 출력한다 */
					if (searchUser.getuState().equals("1")) {
						System.out.println(" ▶ 정상 " );
					} else if (searchUser.getuState().equals("2")) {
						System.out.println(" ▶ 만료 ");
					} else if (searchUser.getuState().equals("3")) {
						System.out.println(" ▶ 임박 ");
					} else {
						System.out.println(" ▶ 정보없음 ");
					}
										
					System.out.println("┌──────────────────────────┐");
					System.out.println("│         가입일자         │");
					System.out.println("└──────────────────────────┘");
					
					System.out.println(" ▶ " + searchUser.getuStartDate());
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│         만료일자         │");
					System.out.println("└──────────────────────────┘");
					
					System.out.println(" ▶ " + searchUser.getuExpireDate());
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│         사용유무         │");
					System.out.println("└──────────────────────────┘");
					
					System.out.println(" ▶ " + searchUser.getuIsActive());
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│         등록일시         │");
					System.out.println("└──────────────────────────┘");
					
					System.out.println(" ▶ " + searchUser.getuRegigterDate());
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│         수정일시         │");
					System.out.println("└──────────────────────────┘");
					
					System.out.println(" ▶ " + searchUser.getuUpdateDate());
					
					break;
				}
				
			} catch(Exception e) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           ERROR          │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : searchUser() " + e.getMessage());
				e.printStackTrace();

			}
		}
		
		System.out.println("┌──────────────────────────┐");
		System.out.println("│      회원조회 완료       │");
		System.out.println("└──────────────────────────┘");		
	}
	
	/**
	 * function명 	: updateUser
	 * function설명	: 1. 수정할 회원의 ID가 있는지 먼저 검증한다
	 * 				  2. 데이터가 존재하면 수정할 데이터를 입력받아 데이터를 수정처리한다.
	 * 				  3. 존재하지 않으면 루프 시작점으로 돌아가 다시 입력을 받는다. 
	 * 				  4. 최종 확인을 받아 수정 / 취소 작업을 수행한다. 
	 * @return int  : 정보 수정에 성공하면 1, 실패하면 0을 결과값으로 반환
	 */
	public int updateUser() {

		// 새롭게 수정될 데이터를 담을 MemberDTO 객체 생성
		MemberDTO newUser = new MemberDTO();
		
		String uId;				// 회원 ID
		String uName;			// 회원 이름
		String uGender;			// 회원 성별
		String uPhone;			// 전화 번호
		String uAddress;		// 회원 주소
		String uMemo;			// 회원 설명
		String uStartDate;		// 가입 일자 (YYYY-MM-DD)
		String uExpireDate;		// 만료 일자 (YYYY-MM-DD)
		String uRegigterDate;	// 등록 일시
		String uUpdateDate;     // 수정 일시

		System.out.println("┌──────────────────────────┐");
		System.out.println("│         회원수정         │");
		System.out.println("└──────────────────────────┘");
		
		// 회원 상태를 업데이트 하는 refreshStatus 함수 호출
		refreshStatus(); 
		
		/* 루프를 돌며 수정할 회원 ID를 입력받는다 */
		while(true) {
			try {	

				System.out.println("┌──────────────────────────┐");
				System.out.println("│ 수정하실 ID를 입력하세요 │");
				System.out.println("└──────────────────────────┘");
				
				uId = userInput.nextLine();	
				
				System.out.println(" ▶ 입력 : " + uId);

				/* 등록하려하는 회원 ID가 이미 존재하는지 checkID 함수를 호출하여 검사하고 있으면 반복문 시작점으로, 없으면 추가 정보를 입력받게 한다. */
				if(MainMenu.membersDAO.checkId(uId)) {					
						System.out.println("┌──────────────────────────┐");
						System.out.println("│ 존재하지 않는 ID 입니다. │");
						System.out.println("│ ID를 다시 확인해 주세요. │");
						System.out.println("└──────────────────────────┘");						
						continue;
				} else {

					/* 수정 처리에 필요한 데이터들을 입력 받는다 */
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  회원이름를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uName = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + uName);
					newUser.setuName(uName);
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  회원성별을 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uGender = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + uGender);
					newUser.setuGender(uGender);
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  전화번호를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uPhone = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + uPhone);
					newUser.setuPhone(uPhone);
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  회원주소를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uAddress = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + uAddress);
					newUser.setuAddress(uAddress);
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  회원설명을 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uMemo = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + uMemo);
					newUser.setuMemo(uMemo);
					
					newUser.setuState("1"); 
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  가입일자를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uStartDate = userInput.nextLine();		
					System.out.println(" ▶ 입력 : " + uStartDate);
					newUser.setuStartDate(uStartDate);
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  만료일자를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uExpireDate = userInput.nextLine();
					System.out.println(" ▶ 입력 : " + uExpireDate);
					newUser.setuExpireDate(uExpireDate);
					
					newUser.setuIsActive("Y");
					
					System.out.println("┌──────────────────────────┐");
					System.out.println("│  등록일자를 입력하세요   │");
					System.out.println("└──────────────────────────┘");
					
					uRegigterDate = userInput.nextLine();	
					System.out.println(" ▶ 입력 : " + uRegigterDate);
					newUser.setuRegigterDate(uRegigterDate);
					
					// 수정일자는 입력받지 않고, getCurruntDate 함수를 이용해 설정한다.
					uUpdateDate = MainMenu.getCurrentDate();								
					newUser.setuUpdateDate(uUpdateDate);

					// 입력받은 정보를 바탕으로 객체를 생성하고 HashMap에 데이터를 추가하기 위해 루프를 빠져나간다.
					break;
				}
				
			} catch(Exception e) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           ERROR          │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : updateUser() " + e.getMessage());
				e.printStackTrace();
				
				// 실패시 0 리턴
				return 0;
			}
		}
		
		/* 데이터 처리를 확정할 것인지 취소할 것인지 입력을 받아 판단하여 결과에 따라 수행한다 */
		if (MainMenu.confirmDataChange(2)) {
			MainMenu.allUsers.getAllUsers().remove(uId);
			MainMenu.allUsers.getAllUsers().put(uId, newUser);

			System.out.println("┌──────────────────────────┐");
			System.out.println("│      회원수정 완료       │");
			System.out.println("└──────────────────────────┘");
			
			// 완료(성공)시 1 리턴
			return 1;
		} else {
			System.out.println("┌──────────────────────────┐");
			System.out.println("│      회원수정 취소       │");
			System.out.println("└──────────────────────────┘");

			// 취소(실패)시 0 리턴
			return 0;
		}
	}
	
	/**
	 * function명 	: deleteUser
	 * function설명	: 1. 삭제할 회원 ID 데이터가 존재하는 먼저 검증한다.
	 * 				  2. 존재하면 삭제 작업을 수행한다
	 * 			      3. 작업을 수행하기 전, 최종 확인을 받아 삭제 / 취소 작업을 수행한다. 
	 * 				  4. 삭제할 ID가 존재하지 않으면 루프 시작점으로 돌아가 다시 입력받는다. 
	 * @return int  : 정보 삭제에 성공하면 1, 실패하면 0을 결과값으로 반환
	 */
	public int deleteUser() {

		String uId;

		System.out.println("┌──────────────────────────┐");
		System.out.println("│         회원삭제         │");
		System.out.println("└──────────────────────────┘");
		
		// 회원 상태를 업데이트 하는 refreshStatus 함수 호출
		refreshStatus(); 
		
		/* 루프를 돌며 삭제할 회원 ID를 입력받는다 */
		while(true) {
			try {	

				System.out.println("┌──────────────────────────┐");
				System.out.println("│ 삭제하실 ID를 입력하세요 │");
				System.out.println("└──────────────────────────┘");
				
				uId = userInput.nextLine();	
				
				System.out.println(" ▶ 입력 : " + uId);

				/* 삭제할 ID가 존재하는지 체크하고 존재하면 삭제 처리 수행, 존재하지 않으면 루프 시작점으로 돌아가 다시 ID를 입력 받는다. */
				if(MainMenu.membersDAO.checkId(uId)) {					
						System.out.println("┌──────────────────────────┐");
						System.out.println("│ 존재하지 않는 ID 입니다. │");
						System.out.println("│ ID를 다시 확인해 주세요. │");
						System.out.println("└──────────────────────────┘");						
						continue;
				} else {
					
					// 모든 회원 정보가 저장된 HashMap 에서 해당 Id를 Key로 갖는 value 데이터를 삭제한다.
					MainMenu.allUsers.getAllUsers().remove(uId);
					break;
				}
				
			} catch(Exception e) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           ERROR          │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : deleteUser() " + e.getMessage());
				e.printStackTrace();
				
				// 실패시 0 리턴
				return 0;
			}
		}
		
		/* 데이터 처리를 확정할 것인지 취소할 것인지 입력을 받아 판단하여 결과에 따라 수행한다 */
		if (MainMenu.confirmDataChange(3)) {
			System.out.println("┌──────────────────────────┐");
			System.out.println("│      회원삭제 완료       │");
			System.out.println("└──────────────────────────┘");

			// 완료(성공)시 1 리턴
			return 1;			
		} else {
			System.out.println("┌──────────────────────────┐");
			System.out.println("│      회원삭제 취소       │");
			System.out.println("└──────────────────────────┘");

			// 취소(실패)시 0 리턴
			return 0;
		}
	}
	
	/**
	 * function명 	: refreshStatus
	 * function설명	: 현재시각과 회원정보들에 저장된 만료일자를 비교해 회원상태 변동이 있으면 정보를 업데이트 하는 함수
	 */
	public void refreshStatus() {

		/* 모든 회원 정보가 저장된 HashMap을 순차적으로 탐색할 반복자 선언 */
		Iterator<String> userList = MainMenu.allUsers.getAllUsers().keySet().iterator();
		
		/* 현재시간을 기준시간으로 하여 MainMenu에 정의해둔 getCurrentDate 함수를 호출하여 값을 구해 문자열로 저장*/
		String start = MainMenu.getCurrentDate();
		
		/* 비교할 시간(만료일자)를 저장할 문자열 타입 변수 선언 */	
		String end;		
		
		/* 두 일자를 계산해서 나온 차이값을 저장할 long 타입 변수 선언 (millisecond 단위를 포함해서 long으로 설정함) */
		long diffDays;
		
		/* 반복자를 통해 HashMap을 순차적으로 돌며 저장되있는 만료일자와 기준시간을 비교하여 정보를 업데이트 한다 */
		while(userList.hasNext()) {
			
			// 반복자가 현재 가리키고 있는 데이터를 검색할 회원 ID(Key)로 설정
			String idInList = userList.next();
			
			// Key를 통해 얻은 객체에서 비교할 시간(만료일자)를 참조해 end 변수에 저장한다
			end = MainMenu.allUsers.getAllUsers().get(idInList).getuExpireDate();
			try {
				
				// 인자로 주어지는 두 문자열을 Date 형식으로 변환해서 하루 단위로 날짜간의 차이를 구하는 getDiffDate 함수 호출
				diffDays = MainMenu.getDiffOfDate(start, end);
				
				/* 차이가 5 이상이면 정상("1"), 5 이내면 임박("3"), 그리고 0미만("2") 만료로 상태를 업데이트한다 */
				if (diffDays != 500) { 				// 이 부분 없어도 됨. 수정할 사항
					if (diffDays > 5) {  
						MainMenu.allUsers.getAllUsers().get(idInList).setuState("1");
					} else if (diffDays >= 0 && diffDays <= 5 ) {
						MainMenu.allUsers.getAllUsers().get(idInList).setuState("3");
					} else if (diffDays < 0) {
						MainMenu.allUsers.getAllUsers().get(idInList).setuState("2");
					}
				} // if End
			} catch (Exception e) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│           ERROR          │");
				System.out.println("└──────────────────────────┘");
				System.out.println(" [Message] : refreshStatus() " + e.getMessage());
				e.printStackTrace();
			} // try - catch End				
		} // while End
	}
	
}
