package com.study.springboot.android.dto;

public class MemberDTO {
	
	private String email;
	private String password;
	private String phone;
	private String name;
	private String isAdmin;
	private String isValid;
	private String alert;
	private String birth;
	private String gender;
	private String rsvok;
	private String rsvx;
	private String deviceid;
	private int mid;
	
	public MemberDTO() { }

	public MemberDTO(String email, String password, String phone, String name, String isAdmin, String isValid, String alert, String birth, String gender, String rsvok, String rsvx, String deviceid, int mid) {
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.name = name;
		this.isAdmin = isAdmin;
		this.isValid = isValid;
		this.alert = alert;
		this.birth = birth;
		this.gender = gender;
		this.rsvok = rsvok;
		this.rsvx = rsvx;
		this.deviceid = deviceid;
		this.mid = mid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRsvok() {
		return rsvok;
	}

	public void setRsvok(String rsvok) {
		this.rsvok = rsvok;
	}

	public String getRsvx() {
		return rsvx;
	}

	public void setRsvx(String rsvx) {
		this.rsvx = rsvx;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}
	
	
}
