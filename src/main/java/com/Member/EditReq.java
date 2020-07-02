package com.Member;

import org.hibernate.validator.constraints.NotEmpty;

public class EditReq {

    private String nickname;

    private String email;

    private String oneline;
    @NotEmpty(message = "입력하셔야 변경됩니다.")
    private String password;
    @NotEmpty(message = "입력하셔야 변경됩니다.")
    private String confirmPassword;

    public boolean isPasswordEqualToConfirmPassWord() {
        return password.equals(confirmPassword);
    }

    public String getNickname() {
        return nickname;
    }

    public String getOneline() {
        return oneline;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setOneline(String oneline) {
        this.oneline = oneline;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
