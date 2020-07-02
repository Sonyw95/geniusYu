package com.Member;

import java.time.LocalDateTime;

public class MemberVo {
    private String password, nickname, email,oneline;
    private LocalDateTime localDateTime;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public MemberVo(String password, String nickname, String email, String oneline) {
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.oneline=oneline;
    }

    public String getOneline() {
        return oneline;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public boolean matchLoginPassword(String password){
        return this.password.equals(password);
    }
}
