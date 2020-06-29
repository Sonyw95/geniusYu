package com.Boad;

import org.hibernate.validator.constraints.NotEmpty;

// 게시판 글을 쓸때 받는 ValueOjbect 입니다.
public class BoadWriteVo {
    private String nickname,  Area_Boad;
    // Validator의 애노테이션중 하나 문자열이나 배열의 경우 null이 아니고 길이가 0이 아닌지 검사.
    @NotEmpty(message = "필수로 선택해주세요.")
    private String BoadType;
    @NotEmpty(message = "제목은 필수 입력입니다.")
    private String title;


    public BoadWriteVo(String nickname, String area_Boad, String boadType, String title) {
        this.nickname = nickname;
        Area_Boad = area_Boad;
        BoadType = boadType;
        this.title = title;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBoadType() {
        return BoadType;
    }

    public void setBoadType(String boadType) {
        BoadType = boadType;
    }

    public String getArea_Boad() {
        return Area_Boad;
    }

    public void setArea_Boad(String area_Boad) {
        Area_Boad = area_Boad;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
