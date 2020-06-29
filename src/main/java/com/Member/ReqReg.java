package com.Member;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Size;

public class ReqReg {
    @NotBlank(message = "이메일은 필수로 입력하셔야 됩니다.")
    @Email(message = "메일 형식에 맞지 않는 이메일입니다.")
    private String email;
    @Size(min = 6, message = "패스워드는 촤소 6자 이상 이어야 합니다")
    private String password;
    @NotEmpty(message = "필수로 채워야 합니다.")
    private String  confirmPassword;
    @NotEmpty(message = "필수로 채워야 합니다.")
    private String nickname;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isPasswordEqualToConfirmPassWord(){
        return password.equals(confirmPassword);
    }
}
