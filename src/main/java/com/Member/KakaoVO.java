package com.Member;

public class KakaoVO {
    private String nickname, email, token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public KakaoVO(String nickname, String email, String token) {
        this.nickname = nickname;
        this.email = email;
        this.token=token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
