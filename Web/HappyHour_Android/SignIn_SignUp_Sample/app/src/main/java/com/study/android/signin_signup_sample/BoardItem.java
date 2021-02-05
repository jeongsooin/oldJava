package com.study.android.signin_signup_sample;

import java.sql.Timestamp;

public class BoardItem {

    private int bId;
    private String ID;
    private String bName;
    private String bTitle;
    private String bContent;
    private int bHit;
    private Timestamp bDate;
    private int bGroup;
    private int bIndent;
    private int bStep;
    private String bMenu;
    private String bImageName;
    private String img_extension;
    private String menu_name;
    private String secret; // 비밀글 유무(문의)
    private int star;

    private int replyNum;  // 답변 갯갯수


    public BoardItem() {}

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbTitle() {
        return bTitle;
    }

    public void setbTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    public String getbContent() {
        return bContent;
    }

    public void setbContent(String bContent) {
        this.bContent = bContent;
    }

    public int getbHit() {
        return bHit;
    }

    public void setbHit(int bHit) {
        this.bHit = bHit;
    }

    public Timestamp getbDate() {
        return bDate;
    }

    public void setbDate(Timestamp bDate) {
        this.bDate = bDate;
    }

    public int getbGroup() {
        return bGroup;
    }

    public void setbGroup(int bGroup) {
        this.bGroup = bGroup;
    }

    public int getbIndent() {
        return bIndent;
    }

    public void setbIndent(int bIndent) {
        this.bIndent = bIndent;
    }

    public int getbStep() {
        return bStep;
    }

    public void setbStep(int bStep) {
        this.bStep = bStep;
    }

    public String getbMenu() {
        return bMenu;
    }

    public void setbMenu(String bMenu) {
        this.bMenu = bMenu;
    }

    public String getbImageName() {
        return bImageName;
    }

    public void setbImageName(String bImageName) {
        this.bImageName = bImageName;
    }

    public String getImg_extension() {
        return img_extension;
    }

    public void setImg_extension(String img_extension) {
        this.img_extension = img_extension;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }
}
