<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <meta name="description" content="개발자 손영욱의 일기장 입니다.">
    <title>Yu urlol Blog</title>
    <link rel="stylesheet" href="<c:url value='/css/main.css' />">
    <script>
        window.open("<c:url value="/main/Notice"/> ", "pop", "width=400,height=500,history=no,resizable=no,status=no,scrollbars=yes,menubar=no")

    </script>
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
                        <li>
                            <a href="https://kauth.kakao.com/oauth/logout?client_id=db3025daa10357d71f35ce5b1d9b9a6e&logout_redirect_uri=http://urlol.kr/Logout/KakaLogout">로그아웃</a>
                        </li>
                    </c:if>
                    <c:if test="${!empty LoginType}">
                        <li><a href="<c:url value="/Login/logout"/> ">로그아웃</a></li>
                    </c:if>
                </c:if>
            </ul>
        </nav>
    </header>
    <section>
        <a href="<c:url value="/boad/list"/> ">
            <div><img src="<c:url value="/img/My.jpeg"/> " alt=""></div>
            <p><strong> 오렌지색을 좋아하는</strong><br>Yu의 Blog [PUSH]</p>
        </a>
    </section>
    <footer>
        <div class="DesFooter">
            Copyright 2020 © <a href="https://sonyw95.github.io/SonPortFolio/Index">YoungU..</a>
        </div>
    </footer>
</div>
</body>
</html>