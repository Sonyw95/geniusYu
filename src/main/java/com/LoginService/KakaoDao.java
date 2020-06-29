package com.LoginService;

import com.Member.KakaoVO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class KakaoDao {
    // 카카오 측에서 오는 요청을 받기위해 전역변수로 JsonObject를 생성 합니다.
    private JsonObject jo;

    // 동의 항목등으로 요청을 받아 생성된 code번호를 파라미터로 받습니다
    // 해당 코드로 사용자 인증 토큰을 받을 때 사용합니다 .  여기서 이해한 바로는 code라고 하는 일정 번호를 받아서 그 번호가 일치하면 카카오서버와 연결되는 세션값 즉 token을 넘겨주는거 같습니다.
    // 토큰의 기본 지속시간은 24시간이며 최조 인증후 시간은 변경되지 않스빈다.
    public String getToken(String code) {
        String token = null; // 토큰을 저장하기 위한 String 객체 선언
        // 토큰을 받아오는 apiUrl 입니다 . token?= 1번 타입은 인증코드 /2번 api코드 /3번, 해당 기능을 완료하고 리다이렉트 될 URI /4번 받아온 코드로 인증하기 위해
        String RequestURL = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=db3025daa10357d71f35ce5b1d9b9a6e&redirect_uri=http://urlol.kr/Login/kakao&code=" + code;

        try {
            // 파라미터로 지정되는 문자열(RequestURL)에 대한 URL을 생성 합니다.
            URL url = new URL(RequestURL);
            // 여기서 두가지 방법이 있습니다  자바 내부 클래스인 HttpURLConnection과 라이브러리인 HTTPClient가 있습니다.
            // HttpClient는 HttpURLConnection 보다 쉽게 작성할 수 있다는 장점이 있다고 합니다 사용해보진 않았습니다.
            // 저는 공부를 했던 방식인 HttpURLConnection으로 이용했습니다.

            //openConnection() 메서드는 URL 주소의 원격 객체에 접속한 뒤 통신할 수 있는 HttpURLCOnnection을 리턴 합니다
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 통신할 맵핑 방식 카카오측에선 GET/POST 둘다 요청 받을 수 있지만 토큰 값을 URI에 유출되는것을 가리기 위해 POST로 요청햤습니다.
            connection.setRequestMethod("POST");
            // coonnection으로 받는 스트림을 버퍼에 저장을 합니다 해당 스트림의 charset은 UTF-8로 지정합니다.
            // 결과 스트림은 Json타입
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String brLine;
            String result = "";
            while ((brLine = br.readLine()) != null) {
                result += brLine;
            }

            // 위에서 스트림을 문자열애 저장한뒤 해당 문자열 JsonObject로 파싱하기 위해 선언합니다.
            JsonParser jsonParser = new JsonParser();
            jo = (JsonObject) jsonParser.parse(result);

            //{
            //    "token_type":"bearer",
            //    "access_token":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
            //    "expires_in":43199,
            //    "refresh_token":"yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",
            //    "refresh_token_expires_in":25184000,
            //    "scope":"account_email profile"
            //} 이형태로 오는데 이 Json타입의 "access_token 속성의 이름의 키값을 저장합니다 고것이 토큰
            token = jo.get("access_token").getAsString();
            System.out.println(token);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

    public KakaoVO UserInfo(String token) {
        KakaoVO kakaoVO= null;
        String RequestURI = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(RequestURI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "bearer " + token);
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            JsonParser jsonParser = new JsonParser();
            jo = (JsonObject) jsonParser.parse(result);
            JsonObject kakao_accountJo = jo.get("kakao_account").getAsJsonObject();
            String mail = kakao_accountJo.get("email").getAsString();
            JsonObject propertiesJo = jo.get("properties").getAsJsonObject();
            String nickname = propertiesJo.get("nickname").getAsString();
            kakaoVO = new KakaoVO(nickname, mail,token);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kakaoVO;
    }

    public void KakaoLogOut(KakaoVO kakaoVO){
        String RequestUrl ="https://kauth.kakao.com/oauth/logout?client_id=db3025daa10357d71f35ce5b1d9b9a6e&logout_redirect_uri=http://urlol.kr/Logout/KakaLogout";
        try {
            URL url = new URL(RequestUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Authorization", "bearer " + kakaoVO.getToken());
            connection.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
