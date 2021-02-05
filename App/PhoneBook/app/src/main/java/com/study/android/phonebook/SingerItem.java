package com.study.android.phonebook;

public class SingerItem {

    private String name = "";
    private String telnum = "";
    private int resId = -70057;
    private Boolean isFemale = false;

    public SingerItem(String name, String telnum, int resId, Boolean isFemale) {
        this.name = name;
        this.telnum = telnum;
        this.resId = resId;
        this.isFemale = isFemale;
    }

    public void setName(String name) { this.name = name; }

    public void setTelnum(String telnum) { this.telnum = telnum; }

    public void setResId(int resId) { this.resId = resId; }

    public String getName() { return name; }

    public String getTelnum() { return telnum; }

    public int getResId() { return resId; }

    public Boolean getIsFemale() { return isFemale; }

    public void setIsFemale(Boolean isFemale) { this.isFemale = isFemale; }
}
