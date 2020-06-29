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
    <title>Yu urlol Blog</title>
    <link rel="stylesheet" href="<c:url value='/css/BoadList.css' />">
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
                <li ><a href="<c:url value="/boad/list"/> ">전체</a></li>
                <li class="active"><a href="<c:url value="/boad/BoadTech"/> ">테크</a></li>
                <li style="text-align: inherit;"><a href="<c:url value="/Urlol/UrlolForm"/> "><img class="Lologo" src="<c:url value="/img/Lologo.png"/> " alt=""></a></li>
                <li class="paging">
                    <c:if test="${boadPage.nowPage >1}">
                        <a href="<c:url value="/boad/list?page=1"/> ">[처음]</a>
                    </c:if>
                    <c:if test="${boadPage.nowPage >1}">
                        <a href="<c:url value="/boad/list?page=${boadPage.nowPage-1}"/> ">[이전]</a>
                    </c:if>
                    <c:forEach var="num" begin="${boadPage.rangeStart}" end="${boadPage.rangeEnd}">
                        <c:choose>
                            <c:when test="${num == boadPage.nowPage}">
                                <span> ${num}</span>
                            </c:when>
                            <c:otherwise>
                                <a href="<c:url value="/boad/list?page=${num}"/> ">${num}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${boadPage.nowPage<boadPage.rangeEnd}">
                        <a href="<c:url value="/boad/list?page=${boadPage.nowRange+1}"/> ">[다음]</a>
                    </c:if>
                    <c:if test="${boadPage.nowPage<boadPage.rangeEnd}">
                        <a href="<c:url value="/boad/list?page=${boadPage.rangeEnd}"/> ">[끝 ]</a>
                    </c:if>
                </li>
            </ul>
        </nav>
    </article>
    <section>
        <c:if test="${!empty LoginInfo}">
            <div class="WriteBoad">
                <a  href="<c:url value="/boad/Write" />"><button class="button">글쓰기</button></a>
            </div>
        </c:if>
        <c:forEach items="${list}" var="list">
            <a href="<c:url value="/boad/BoadView?title=${list.title}&name=${list.nickname}"/> ">
                <div class="sect_div_img"><img src="<c:url value='/img/logo.png'/>" alt=""></div>
                <div class="sect_div_title"><p><strong>${list.title}</strong></p></div>
                <div class="sect_div_user_img"></div>
                <div class="sect-div-user">${list.nickname} <br>${list.localDateTime}</div>
            </a>
        </c:forEach>

    </section>
    <footer>
        <div class="DesFooter">
            Copyright 2020 © YoungU..
        </div>
    </footer>
</div>
</body>
</html>