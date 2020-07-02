package com.AllException;

import com.Member.EditReq;
import com.Member.ReqReg;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//  커맨드 객체 에러 판별 .  * 커맨드 객체란? jsp화면애서 form태그로 전송되어 온 데이터를 받는 VO객체.
// 조건 에러코드를 전달하기 위한 ResourcBundleMessageSource의 스프링 빈 객체가 있어야 합니다. 추가 해당 빈을 사용하기 위해선 resources.message.lable.properties 메시지를 담는 곳이 필요
public class EditReqVali implements Validator {
    @Override
    // 파라미터로 전달 받는 class객체가 Reqreg 클래스로 타입을 변환이 가능한지 확인.
    public boolean supports(Class<?> aClass) {
        boolean assignableFrom = EditReq.class.isAssignableFrom(aClass);
        return assignableFrom;
    }

    @Override
    // 2개의 파라미터 object는 대상 커맨드 객체, error는 검사 결과 에러코드를 성정하기 위한 객체.
    // 검사 대상객체의 특정 프로퍼티나 상태가 올바른지, 혹여 올바르지 않다면 Erros의 rejectValue를 이용해서 에러코드를 저장.
    public void validate(Object o, Errors errors) {
        EditReq editReq = (EditReq) o;
//        if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
//            errors.rejectValue("email", "required");
//        }
        if(!editReq.isPasswordEqualToConfirmPassWord()){
            //    해당 에러 발생시 1번 파라미터 : 커맨드 객체의 프로퍼티 이름, 2번 파라미터 : 해당에러코드의 이름.
            errors.rejectValue("confirmPassword","NotMatch");
        }
    }
}
