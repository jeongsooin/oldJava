package com.study.android.signin_signup_sample;

public class MemberDTO {

    public static MemberDTO getInstance() { return new MemberDTO("", "", "", "", "", "", "", "", "", 0, 0, ""); }

    private String email;
    private String password;
    private String name;
    private String phone;
    private String isadmin;
    private String isvalid;
    private String alert;
    private String birth;
    private String gender;
    private int rsvok;
    private int rsvx;
    private String deviceid;

    public MemberDTO(String email) { }

    public MemberDTO(String email, String password, String name, String phone, String isadmin, String isvalid, String alert, String birth, String gender, int rsvok, int rsvx, String deviceid) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.isadmin = isadmin;
        this.isvalid = isvalid;
        this.alert = alert;
        this.birth = birth;
        this.gender = gender;
        this.deviceid = deviceid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
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

    public int getRsvok() { return rsvok; }

    public void setRsvok(int rsvok) { this.rsvok = rsvok; }

    public int getRsvx() { return rsvx; }

    public void setRsvx(int rsvx) { this.rsvx = rsvx; }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
}