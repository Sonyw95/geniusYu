<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020-06-03
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ur Join us?</title>
    <link rel="stylesheet" href="<c:url value="/css/LoginJoin.css"/> "/>
</head>
<body>
<div id="wrap">
    <header>
        <a class="logo" href="<c:url value="/main/FirstVisited"/> "><img src="<c:url value='/img/logo_transparent.png'/>"></a>
        <nav>
            <input class="nav-toggle" id="nav-toggle" type="checkbox">
            <label class="navicon" for="nav-toggle"><span class="navicon-bar"></span></label>
            <ul class="nav-items">
                <%-- 로그인 정보가 없다면 로그인 안하거라서 로그인 nav--%>
                <c:if test="${empty LoginInfo}">
                    <li><a href="<c:url value="/Login/LoginPage"/> ">로그인</a></li>
                    <li><a href="<c:url value="/join/JoinUs"/> ">회원가입</a></li>
                </c:if>
                <%--로   그인정보가 있을 때.--%>
                <c:if test="${!empty LoginInfo}">
                    <%-- 로그인된 계정 이름을 표시.--%>
                    <li><a href="#">${LoginInfo.nickname}</a></li>
                    <%-- 일반적인 로그인 이라믄--%>
                    <c:if test="${empty LgoinType}">
                        <li><a href="<c:url value="/Login/logout"/>">로그아웃</a></li>
                    </c:if>
                    <%-- 근데 해당 로그인이 카카오 로그인이라면--%>
                    <c:if test="${!empty LgoinType}">
                        <li><a href="https://kauth.kakao.com/oauth/logout?client_id=db3025daa10357d71f35ce5b1d9b9a6e&logout_redirect_uri=http://urlol.kr/Logout/KakaLogout">로그아웃</a></li>
                    </c:if>
                </c:if>
            </ul>
        </nav>
    </header>
    <section>
        <div class="Login-container">
            <div>
                <p><strong>Log In</strong><br>BLOG YU PROJECT</p>
                <%--@elvariable id="ReqReg" type=""--%>
                <form:form action="../join/insertJoin" method="post" modelAttribute="reqReg">
                    <form:input cssClass="Login-InputForm" path="email" placeholder="이메일"/>
                    <form:errors path="email"/>
                    <form:input cssClass="Login-InputForm" path="nickname" placeholder="별명(블로그에 보여지는 이름)"/>
                    <form:errors path="nickname"/>
                    <form:password cssClass="Login-InputForm" path="password" placeholder="패스워드"/>
                    <form:errors path="password"/>
                    <form:password cssClass="Login-InputForm" path="confirmPassword" placeholder="패스워드 확인"/>
                    <form:errors path="confirmPassword"/>
                    <input class="Login-InputForm" style="background-color:white; color:black; font-size:20px;" type="submit" value="가입"/>
                </form:form>
                <a href="#"
                   onclick='window.open("https://kauth.kakao.com/oauth/authorize?client_id=a594da28e89a08c2ee147c00b3c0db6c&redirect_uri=http://urlol.kr/Login/kakao&response_type=code","_blank","height=800,width=800, status=yes,toolbar=no,menubar=no,location=no,window.close()");return false'><input
                        class="Login-InputForm" type="image" src="<c:url value="/img/KakaoBTN.png"/> " alt=""></a>
            </div>
        </div>

    </section>
</div>
</body>
<script>
</script>
</html>