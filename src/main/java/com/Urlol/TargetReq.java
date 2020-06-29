package com.Urlol;

import org.hibernate.validator.constraints.NotEmpty;

public class TargetReq {
    @NotEmpty(message = "소환사 이름을 입력해주세요")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
