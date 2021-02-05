package com.study.android.signin_signup_sample;

import android.util.ArraySet;

import java.util.HashSet;
import java.util.Set;

public class TableItem {

    public Set<String> selectedTimes;
    private String time;
    private String name;
    private int tableNumber;
    public Boolean isSelectable = true;

    public TableItem() { }

    public TableItem(int tableNumber) {
        this.tableNumber = tableNumber;
        selectedTimes = new HashSet<>();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Boolean getSelectable() {
        return isSelectable;
    }

    public void setSelectable(Boolean selectable) {
        isSelectable = selectable;
    }
}
