package com.study.android.ex20_list6;

public class SingerItem {

    private String name;
    private String telnum;
    private int resId;

    public SingerItem(String name, String telnum, int resId) {
        this.name = name;
        this.telnum = telnum;
        this.resId = resId;
    }

    public void setName(String name) { this.name = name; }

    public void setTelnum(String telnum) { this.telnum = telnum; }

    public void setResId(int resId) { this.resId = resId; }

    public String getName() { return name; }

    public String getTelnum() { return telnum; }

    public int getResId() { return resId; }
}
