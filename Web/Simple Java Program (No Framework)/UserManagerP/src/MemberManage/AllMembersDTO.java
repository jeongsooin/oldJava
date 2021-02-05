package MemberManage;

import java.util.HashMap;

/**
 * @Class Name AllMembersDTO.java
 * @Description  모든 회원 정보의 데이터를 정의하고 저장하는 클래스
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
public class AllMembersDTO {

	/* 모든 회원 정보들을 HashMap 형태로 모아서 관리하도록 변수 선언. 이때 Key 값은 회원 ID, Value값은 MemberDTO 클래스 객체 */
	private HashMap<String, MemberDTO> allUsers;
	
	// 기본 생성자. 생성자 호출시 HashMap 할당
	public AllMembersDTO() {
		allUsers = new HashMap<String, MemberDTO>();
	}

	/* 데이터 객체에 대한 Getter 및 Setter */
	public HashMap<String, MemberDTO> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(HashMap<String, MemberDTO> allUsers) {
		this.allUsers = allUsers;
	}
		
}
