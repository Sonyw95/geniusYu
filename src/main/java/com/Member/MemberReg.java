package com.Member;

import com.AllException.DuplicateEmailException;
import com.AllException.NotEqualPassword;

public class MemberReg {
    private DaoMember daoMember;

    public MemberReg(DaoMember daoMember){ this.daoMember=daoMember; }

    public String reist(ReqReg reqReg){
        MemberVo memberVo =daoMember.SearchByEmail(reqReg.getEmail());

        if(memberVo != null){
            throw new DuplicateEmailException();
        }
            MemberVo newMember = new MemberVo(reqReg.getPassword(), reqReg.getNickname(), reqReg.getEmail(),reqReg.getOneline());
            daoMember.MemberInsert(newMember);
            return newMember.getNickname();
    }
}
