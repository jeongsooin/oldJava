package com.study.android.signin_signup_sample;

public class MenuItem {
    private int mid;
    private String menu_name;
    private String menu_description;
    private int menu_price;
    private String menu_imagename;
    private String menu_extension;
    private int menu_code;
    private int menu_qty;
    private String mwriter;

    public MenuItem() { }

    public MenuItem(int mid, String menu_name, String menu_description, int menu_price, String menu_imagename, String menu_extension, int menu_code, int menu_qty, String mwriter) {
        this.mid = mid;
        this.menu_name = menu_name;
        this.menu_description = menu_description;
        this.menu_price = menu_price;
        this.menu_imagename = menu_imagename;
        this.menu_extension = menu_extension;
        this.menu_code = menu_code;
        this.menu_qty = menu_qty;
        this.mwriter = mwriter;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_description() {
        return menu_description;
    }

    public void setMenu_description(String menu_description) {
        this.menu_description = menu_description;
    }

    public int getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(int menu_price) {
        this.menu_price = menu_price;
    }

    public String getMenu_imagename() {
        return menu_imagename;
    }

    public void setMenu_imagename(String menu_imagename) {
        this.menu_imagename = menu_imagename;
    }

    public String getMenu_extension() {
        return menu_extension;
    }

    public void setMenu_extension(String menu_extension) {
        this.menu_extension = menu_extension;
    }

    public int getMenu_code() {
        return menu_code;
    }

    public void setMenu_code(int menu_code) {
        this.menu_code = menu_code;
    }

    public int getMenu_qty() {
        return menu_qty;
    }

    public void setMenu_qty(int menu_qty) {
        this.menu_qty = menu_qty;
    }

    public String getMwriter() {
        return mwriter;
    }

    public void setMwriter(String mwriter) {
        this.mwriter = mwriter;
    }
}
