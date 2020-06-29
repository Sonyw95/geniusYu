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
    <title>Ur Login?</title>
    <link rel="stylesheet" href="<c:url value="/css/LoginJoin.css"/> "/>
</head>
<body>
<div id="wrap">
    <header>
        <a class="logo" href="<c:url value="/main/FirstVisited"/> "><img
                src="<c:url value='/img/logo_transparent.png'/>"></a>
        <nav>
            <input class="nav-toggle" id="nav-toggle" type="checkbox">
            <label class="navicon" for="nav-toggle"><span class="navicon-bar"></span></label>
            <ul class="nav-items">
                <c:if test="${empty LoginInfo}">
                    <li><a href="<c:url value="/Login/LoginPage"/> ">로그인</a></li>
                    <li><a href="<c:url value="/join/JoinUs"/> ">회원가입</a></li>
                </c:if>
                <c:if test="${!empty LoginInfo}">
                    <li><a href="#">${LoginInfo.nickname}</a></li>
                    <c:if test="${empty LoginType}">
                        <li><a href="<c:url value="/Login/KakaoLogout"/> ">로그아웃</a></li>
                    </c:if>
                    <c:if test="${!empty LoginType}">
                        <li><a href="<c:url value="/Login/logout"/> ">로그아웃</a></li>
                    </c:if>
                </c:if>
            </ul>
        </nav>
    </header>
    <section>
        <div class="Login-container">
            <div>
                <p><strong>Log In</strong><br>BLOG YU PROJECT</p>
                <form:form action="../Login/LoginSubmit" modelAttribute="loginVo">
                    <form:input path="email" cssClass="Login-InputForm" placeholder="이메일"/>
                    <form:errors path="email"/>
                    <form:password path="password" cssClass="Login-InputForm" placeholder="패스워드"/>
                    <form:errors path="password"/>
                    <input class="Login-InputForm" style="background-color:white; color:black; font-size:20px;"
                           type="submit" value="로그인">
                </form:form>
                <a href="#"
                   onclick='window.open("https://kauth.kakao.com/oauth/authorize?client_id=db3025daa10357d71f35ce5b1d9b9a6e&redirect_uri=http://urlol.kr/Login/kakao&response_type=code","_blank","height=800,width=800, status=yes,toolbar=no,menubar=no,location=no,window.close()");return false'><input
                        type="image" src="<c:url value="/img/KakaoBTN.png"/> " alt=""></a>
            </div>
        </div>

    </section>
</div>
</body>
</html>