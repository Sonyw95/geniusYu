package com.LoginService;

import com.AllException.WrongMemberException;
import com.Member.DaoMember;
import com.Member.MemberVo;
// 로그인 할때 검증하는 클래스 입니다.
public class AuthLoginService {
    private DaoMember daoMember;

    public void setDaoMember(DaoMember daoMember) {
        this.daoMember = daoMember;
    }

    public AuthInfo authInfoService(String email, String password){
        // 이메일 파라미터로 받아서 해당 이메일이 쿼리에 검색합니다.
        MemberVo memberVo = daoMember.SearchByEmail(email);

        // 헤당 이메일로 검색된 쿼리가 없으면 Null 로 리턴을 받습니다.
        if(memberVo == null){
            throw new WrongMemberException();
        }
        // null 아닌 쿼리가 있다면 해당 쿼리의 이메일의 비밀번호가 입력받은 비밀번호라 같은지 확인을 합니다.
        if(!memberVo.matchLoginPassword(password)){
            throw new WrongMemberException();
        }
        // 이상이 없으면 객체에 저장하여 리턴 합니다.
        return new AuthInfo(memberVo.getEmail(), memberVo.getNickname());

    }
}
