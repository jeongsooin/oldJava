package MemberManage;

/**
 * @Class Name : MemberDTO.java
 * @Description 회원 관리 기능에서 처리해야 하는회원 정보의 데이터를 정의하고 저장하는 클래스
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
public class MemberDTO {
	
	private String uId;				// 회원 ID
	private String uName;			// 회원 이름
	private String uGender;			// 회원 성별
	private String uPhone;			// 전화 번호
	private String uAddress;		// 회원 주소
	private String uMemo;			// 회원 설명
	private String uState;			// 회원 상태 (1-정상, 2-만료, 3-임박)
	private String uStartDate;		// 가입 일자 (YYYY-MM-DD)
	private String uExpireDate;		// 만료 일자 (YYYY-MM-DD)
	private String uIsActive;		// 사용 유무 ("Y" 또는 "N")
	private String uRegigterDate;	// 등록 일시
	private String uUpdateDate;		// 수정 일시
	
	// 회원 정보 저장 클래스 생성자 (인자를 갖는 생성자)
	public MemberDTO(String uId, String uName, String uGender, String uPhone, 
					String uAddress, String uMemo,String uState, String uStartDate, 
					String uExpireDate, String uIsActive, String uRegigterDate, String uUpdateDate) {
		
		this.uId = uId;
		this.uName = uName;
		this.uGender = uGender;
		this.uPhone = uPhone;
		this.uAddress = uAddress;
		this.uMemo = uMemo;
		this.uState = uState;
		this.uStartDate = uStartDate;
		this.uExpireDate = uExpireDate;
		this.uIsActive = uIsActive;
		this.uRegigterDate = uRegigterDate;
		this.uUpdateDate = uUpdateDate;
	}
		
	// 기본 생성자
	public MemberDTO() {
		
	}

	// 회원 정보에 대한 Getter 및 Setter
	public String getuId() {
		return uId;
	}
	
	public void setuId(String uId) {
		this.uId = uId;
	}
	
	public String getuName() {
		return uName;
	}
	
	public void setuName(String uName) {
		this.uName = uName;
	}
	
	public String getuGender() {
		return uGender;
	}
	
	public void setuGender(String uGender) {
		this.uGender = uGender;
	}
	
	public String getuPhone() {
		return uPhone;
	}
	
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	
	public String getuAddress() {
		return uAddress;
	}
	
	public void setuAddress(String uAddress) {
		this.uAddress = uAddress;
	}
	
	public String getuMemo() {
		return uMemo;
	}
	
	public void setuMemo(String uMemo) {
		this.uMemo = uMemo;
	}
	
	public String getuState() {
		return uState;
	}
	
	public void setuState(String uState) {
		this.uState = uState;
	}
	
	public String getuStartDate() {
		return uStartDate;
	}
	
	public void setuStartDate(String uStartDate) {
		this.uStartDate = uStartDate;
	}
	
	public String getuExpireDate() {
		return uExpireDate;
	}
	
	public void setuExpireDate(String uExpireDate) {
		this.uExpireDate = uExpireDate;
	}
	
	public String getuIsActive() {
		return uIsActive;
	}
	
	public void setuIsActive(String uIsActive) {
		this.uIsActive = uIsActive;
	}
	
	public String getuRegigterDate() {
		return uRegigterDate;
	}
	
	public void setuRegigterDate(String uRegigterDate) {
		this.uRegigterDate = uRegigterDate;
	}
	
	public String getuUpdateDate() {
		return uUpdateDate;
	}
	
	public void setuUpdateDate(String uUpdateDate) {
		this.uUpdateDate = uUpdateDate;
	}
		
}
