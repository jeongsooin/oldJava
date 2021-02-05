package com.study.android.signin_signup_sample;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatDTO implements Comparable<ChatDTO> {
    private String userName;        // 채팅 보낸사람
    private String message;         // 채팅 내용
    private String chatTime;        // 채팅시간
    private String roomName;        // 채팅방 이름 (고객 아이디)
    private String customerName;    // 채팅 상대이름 (고객 이름)

    public ChatDTO(){}

    public ChatDTO(String userName, String message, String chatTime) {
        this.userName = userName;
        this.message = message;
        this.chatTime = chatTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChatTime() {
        return chatTime;
    }

    public void setChatTime(String chatTime) {
        this.chatTime = chatTime;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // 가장 빠른 시간이 리스트 위로 오도록
    @Override
    public int compareTo(ChatDTO item) {
        DateFormat format = new SimpleDateFormat("a HH:mm");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(chatTime);
            date2 = format.parse(item.getChatTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date1.after(date2)) {
            return -1;
        } else if(date1.before(date2)){
            return 1;
        }
        return 0;
    }


}
