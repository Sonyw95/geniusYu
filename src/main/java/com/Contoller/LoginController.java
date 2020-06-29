package com.Contoller;

import com.AllException.WrongMemberException;
import com.LoginService.AuthInfo;
import com.LoginService.AuthLoginService;
import com.LoginService.KakaoDao;
import com.LoginService.LoginVo;
import com.Member.KakaoVO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    private AuthLoginService authLoginService;

    public void setAuthLoginService(AuthLoginService authLoginService) {
        this.authLoginService = authLoginService;
    }


    @RequestMapping("/Login/LoginPage")
    public String LoginPage(LoginVo loginVo) {
        return "Login/LoginPage";
    }

    @RequestMapping("/FirstVisited")
    public String handleRedirectKakao(){
        return "main/FirstVisited";
    }

    @RequestMapping("/Login/KakaoLoginPage")
    public String handleKakao(){
        return "Login/KakaoLoginPage";
    }

    @RequestMapping ("/LoginPage")
    public String GetLoginPage(LoginVo loginVo){
        return "Login/LoginPage";
    }

    @RequestMapping("/Login/LoginSubmit")
    public String LoginSubmit(@Valid LoginVo loginVo, Errors errors, HttpSession session) {
        if (errors.hasErrors()) {
            return "Login/LoginPage";
        }
        try {
            AuthInfo authInfo = authLoginService.authInfoService(loginVo.getEmail(), loginVo.getPassword());
            session.setAttribute("LoginInfo", authInfo);
            return "main/FirstVisited";
        } catch (WrongMemberException e) {
            errors.rejectValue("email","idPasswordNotMatch");
            return "Login/LoginPage";

        }
    }

    @RequestMapping("/Login/logout")
    public String LogOut(HttpSession session) {
        session.invalidate();
        return "redirect:/main/FirstVisited";
    }
    @RequestMapping("/Login/kakao")
    // 카카오 api 코드 받기 중 파라미터로 &code= ~~~ 값이 옵니다 이 해당 코드를 받기 위하 파라미터.
    public String kakaoLogin(@RequestParam(name = "code") String code, HttpSession httpSession){
        KakaoDao kakaoDao = new KakaoDao();
        String type ="kakao";
        KakaoVO list = kakaoDao.UserInfo(kakaoDao.getToken(code));
        httpSession.setAttribute("LoginInfo",list);
        httpSession.setAttribute("LgoinType" ,type);
        return "Login/KakaoLoginPage";

    }
    @RequestMapping("/Logout/KakaLogout")
    public String KakaoLogout(HttpSession session){
        KakaoDao kakaoDao = new KakaoDao();
        KakaoVO kakaoVO=(KakaoVO)session.getAttribute("LoginInfo");
        kakaoDao.KakaoLogOut(kakaoVO);
        session.invalidate();

        return "redirect:/main/FirstVisited";
    }

}
