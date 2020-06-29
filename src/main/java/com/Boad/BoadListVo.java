package com.Boad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 게시판 리스트를 받기 위한 ValueObject 입니다.

public class BoadListVo {
    private String nickname, title, area, type;
    private String localDateTime;
    private int index;

    public BoadListVo(String nickname, String title, String area, int index, LocalDateTime localDateTime, String type) {
        this.nickname = nickname;
        this.title = title;
        this.area = area;
        // 쿼리에서 선언한 DATATIME 타입의 컬럼을 받기 위해 형변환 하는 과정입니다 .
        this.localDateTime=localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:MM"));
        this.index = index;
        this.type=type;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
