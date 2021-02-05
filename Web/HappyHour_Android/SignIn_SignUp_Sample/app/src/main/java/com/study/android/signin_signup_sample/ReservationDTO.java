package com.study.android.signin_signup_sample;

public class ReservationDTO {

    public static ReservationDTO getInstance() { return new ReservationDTO("", "", "", 0, 0, "", ""); }

    private String RName;
    private String RType;
    private String RPayment;
    private int RNum;
    private int RTable;
    private String RDate;
    private String RTime;

    public ReservationDTO () { }

    public ReservationDTO(String RName, String RType, String RPayment, int RNum, int RTable, String RDate, String RTime) {
        this.RName = RName;
        this.RType = RType;
        this.RPayment = RPayment;
        this.RNum = RNum;
        this.RTable = RTable;
        this.RDate = RDate;
        this.RTime = RTime;
    }

    public String getRName() {
        return RName;
    }

    public void setRName(String RName) {
        this.RName = RName;
    }

    public String getRType() {
        return RType;
    }

    public void setRType(String RType) {
        this.RType = RType;
    }

    public String getRPayment() {
        return RPayment;
    }

    public void setRPayment(String RPayment) {
        this.RPayment = RPayment;
    }

    public int getRNum() {
        return RNum;
    }

    public void setRNum(int RNum) {
        this.RNum = RNum;
    }

    public int getRTable() {
        return RTable;
    }

    public void setRTable(int RTable) {
        this.RTable = RTable;
    }

    public String getRDate() {
        return RDate;
    }

    public void setRDate(String RDate) {
        this.RDate = RDate;
    }

    public String getRTime() {
        return RTime;
    }

    public void setRTime(String RTime) {
        this.RTime = RTime;
    }
}
