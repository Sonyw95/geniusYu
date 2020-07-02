package com.LoginService;

public class AuthInfo {
    private String email, nickname, oneline;

    public AuthInfo(String email, String nickname, String oneline) {
        this.email = email;
        this.oneline=oneline;
        this.nickname = nickname;
    }

    public String getOneline() {
        return oneline;
    }

    public void setOneline(String oneline) {
        this.oneline = oneline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
