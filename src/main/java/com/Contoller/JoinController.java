package com.Contoller;

import com.AllException.DuplicateEmailException;
import com.AllException.NotEqualPassword;
import com.AllException.ReqRegVali;
import com.Member.MemberReg;
import com.Member.ReqReg;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class JoinController {

    private MemberReg memberReg;

    public void setMemberReg(MemberReg memberReg) {
        this.memberReg = memberReg;
    }

    @GetMapping("/JoinUs")
    public String RedirectJoinUs(ReqReg reqReg){
        return "join/JoinUs";
    }

    @RequestMapping("/join/JoinUs")
    public String joinForm(ReqReg reqReg) {
        return "join/JoinUs";
    }

    @RequestMapping("/join/insertJoin")
    public String insertJoin(@Valid ReqReg reqReg, Errors errors) {
        new ReqRegVali().validate(reqReg,errors);
//        if(!reqReg.isPasswordEqualToConfirmPassWord()){
//            errors.rejectValue("");
//        }
        if (errors.hasErrors()) {
            return "join/JoinUs";
        }
        try {
            memberReg.reist(reqReg);
            return "main/FirstVisited";

        } catch (DuplicateEmailException e) {
            errors.rejectValue("email","duplicate");
            return "join/JoinUs";
        }
    }
    @RequestMapping("/main/Notice")
    public String Notice(){
        return "main/Notice";
    }



}
