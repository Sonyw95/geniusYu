<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fomr" uri="http://www.springframework.org/tags/form" %>
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
    <link rel="stylesheet" href="<c:url value='/css/BoadView.css' />">
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
    <article>
        <c:forEach items="${list}" var="list">
            <c:set var="name" value="${list.nickname}"/>
            <c:set var="title" value="${list.title}"/>
            <c:set var="index" value="${list.index}"/>
            <c:set var="area" value="${list.area}"/>
            <c:set var="date" value="${list.localDateTime}"/>
            <div class="title">${title} <br> <span style="font-size:15px;">${date}</span></div>
        </c:forEach>
        <img src="<c:url value="/img/logo.png" />" alt="">
    </article>
    <section>
        <div class="WriteUser">${name} </div>
        <div class="Area"> ${area}</div>
        <div class="selectTab">
            <c:if test="${LoginInfo.nickname eq name}">
                <a class="buttonAtag" href="">
                    <button class="button">수정</button>
                </a>
                <a class="buttonAtag"
                   href="<c:url value="/boad/delBoad?title=${title}&name=${name}"/> ">
                    <button class="button">삭제</button>
                </a>
                <a class="buttonAtag" href="<c:url value="/boad/list"/> ">
                    <button class="button">목록</button>
                </a>
            </c:if>
            <c:if test="${LoginInfo.nickname ne name}">
                <a class="buttonAtag" href="<c:url value="/boad/list"/> ">
                    <button class="button">목록</button>
                </a>
            </c:if>
        </div>
        <div class="Comment">
            <c:if test="${empty commentList}">
                <div class="commentList"> 아직 입렵된 댓글이 없습니다 !</div>
            </c:if>
            <c:if test="${!empty commentList}">
                <c:forEach items="${commentList}" var="commentList">
                    <div class="commentList"><span class="commentInfo"
                                                   style="float:left;">${commentList.nickname}</span>
                        <div class="commentArea">${commentList.comment}</div>
                        <span class="commentInfo" style="float:right;"> ${commentList.localDateTime}</span></div>
                </c:forEach>
            </c:if>

            <c:if test="${empty LoginInfo}">
                <div class="DontComment"> 로그인된 사용자만 댓글을 작성할 수 있습니다.</div>
            </c:if>

            <c:if test="${!empty LoginInfo}">
                <c:url value="/boad/comment" var="commentUrl"/>
                <form:form action="${commentUrl}" method="post" modelAttribute="commentVo" acceptCharset="UTF-8">
                    <form:hidden path="boad_host" value="${name}"/>
                    <form:hidden path="title" value="${title}"/>
                    <form:hidden path="board_index" value="${index}"/>
                    <form:hidden path="nickname" value="${LoginInfo.nickname}"/>
                    <div class="inputComment"><form:input path="comment" cssClass="inComment"/></div>
                </form:form>
            </c:if>

        </div>

    </section>
    <footer>
        <div class="DesFooter">
            Copyright 2020 © YoungU..
        </div>
    </footer>
</div>
</body>
</html>