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
    <title>Yu urlol Blog</title>
    <link rel="stylesheet" href="<c:url value='/css/Urlol.css' />">
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
    <article>
        <nav class="Inside_Check">
            <ul class="Inside_list">
                <li ><a href="<c:url value="/boad/list"/>">전체</a></li>
                <li><a href="<c:url value="/boad/BoadTech"/> ">테크</a></li>
                <li style="text-align: inherit; width: 2.6%;" class="active"><a href="<c:url value="/Urlol/UrlolForm"/> "><img class="Lologo" src="<c:url value="/img/Lologo.png"/> " alt=""></a></li>
            </ul>
        </nav>
    </article>
    <section>
        <form:form action="../Urlol/MatchHistory" modelAttribute="targetReq">
            <form:input cssClass="urForm" path="name" placeholder="소환사 이름을 입력해주세요, 검색은 시간이 걸립니다!"/>
            <form:errors path="name"/>
        </form:form>
    </section>
    <footer>
        <div class="DesFooter">
            Copyright 2020 © YoungU..
        </div>
    </footer>
</div>
</body>
</html>