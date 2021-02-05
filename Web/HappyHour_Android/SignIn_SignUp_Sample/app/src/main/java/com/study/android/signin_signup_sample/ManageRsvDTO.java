package com.study.android.signin_signup_sample;

public class ManageRsvDTO {

    private String mdate;
    private int mtable;
    private String opentime;
    private String closetime;

    public ManageRsvDTO() { }

    public ManageRsvDTO(String mdate, int mtable, String opentime, String closetime) {
        this.mdate = mdate;
        this.mtable = mtable;
        this.opentime = opentime;
        this.closetime = closetime;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public int getMtable() {
        return mtable;
    }

    public void setMtable(int mtable) {
        this.mtable = mtable;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }
}
