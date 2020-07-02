package com.config;

import com.Contoller.BoadContoller;
import com.Boad.BoadDao;
import com.Contoller.JoinController;
import com.Contoller.LoginController;
import com.Contoller.UrlolController;
import com.LoginService.AuthLoginService;
import com.Member.MemberReg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
// 기능적인 요청이 오면 그 기능을 해결해줄 수 있는 컨트롤러로 접근을 해주는 스프링 설정입니다.
public class ControllerConfig {

    @Autowired
    // Bean의 자동주입 입니다 . BoadDao라는 빈을 사용하기 위해 주입을 합니다.
    // Spring의 특징으로 알아서 의존하는 @Bean 객체를 찾아서 주입,
    private BoadDao boadDao;

    @Autowired
    private MemberReg memberReg;

    @Autowired
    private AuthLoginService authLoginService;

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipart = new CommonsMultipartResolver();
        multipart.setMaxUploadSize(3 * 1024 * 1024);
        return multipart;
    }

//    @Bean
//    public MultipartFilter multipartFilter() {
//        MultipartFilter multipartFilter = new MultipartFilter();
//        multipartFilter.setMultipartResolverBeanName("multipartReso‌​lver");
//        return multipartFilter;
//    }

    @Bean
    // 기능실현을 위해 매칭 시켜주는 빈 객체 해당 컨트롤러는 회원가입에 대한 컨트롤러 입니다.
    public JoinController joinController() {
        JoinController joinController = new JoinController();
        // 회원 가입에 관한 쿼리를 처리하는 빈을 주입합니다.
        joinController.setMemberReg(memberReg);
        return joinController;
    }

    @Bean
    // 리그오브 레전드 api를 이용하여 검색기능을 활용하는 컨트롤러로 매칭 시켜 줍니다.
    public UrlolController urlolController() {
        return new UrlolController();
    }

    @Bean
    // 로그인 관련된 컨트롤러 입니다.
    public LoginController loginController() {
        LoginController loginController = new LoginController();
        // 회원가입과 마찬가지로 로그인에 관련된 쿼리및 정보를 처리하는 객체 입니다.
        loginController.setAuthLoginService(authLoginService);
        return loginController;
    }

    @Bean
    // 게시판 저장 삭제 수정 등 여러관리 하는 컨트롤러 입니다.
    public BoadContoller boadContoller() {
        BoadContoller ba = new BoadContoller();
        ba.setBoadController(boadDao);
        return ba;

    }

}