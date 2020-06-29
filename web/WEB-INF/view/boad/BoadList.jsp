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
            <%-- 로그인에 관련된 상단--%>
            <input class="nav-toggle" id="nav-toggle" type="checkbox">
            <label class="navicon" for="nav-toggle"><span class="navicon-bar"></span></label>
            <ul class="nav-items">
                <%-- 로그인 정보가 없다면 로그인 안하거라서 로그인 nav--%>
                <c:if test="${empty LoginInfo}">
                    <li><a href="<c:url value="/Login/LoginPage"/> ">로그인</a></li>
                    <li><a href="<c:url value="/join/JoinUs"/> ">회원가입</a></li>
                </c:if>
                <%--로그인정보가 있을 때.--%>
                <c:if test="${!empty LoginInfo}">
                    <%-- 로그인된 계정 이름을 표시.--%>
                    <li><a href="#">${LoginInfo.nickname}</a></li>
                    <%-- 근데 해당 로그인이 카카오 로그인이라면--%>
                    <c:if test="${empty LgoinType}">
                        <li><a href="<c:url value="/Login/KakaoLogout"/> ">로그아웃</a></li>
                    </c:if>
                    <%-- 일반적인 로그인 이라믄--%>
                    <c:if test="${!empty LgoinType}">
                        <li><a href="<c:url value="/Login/logout"/> ">로그아웃</a></li>
                    </c:if>
                </c:if>
            </ul>
        </nav>
    </header>

    <%--Nav 바로 밑 게시판 탭에 관련된 아티클--%>
    <article>
        <nav class="Inside_Check">
            <ul class="Inside_list">
                <li class="active"><a href="<c:url value="/boad/list"/> ">전체</a></li>
                <li><a href="<c:url value="/boad/BoadTech"/> ">테크</a></li>
                <li style="text-align: inherit;"><a href="<c:url value="/Urlol/UrlolForm"/> "><img class="Lologo"
                                                                                                   src="<c:url value="/img/Lologo.png"/> "
                                                                                                   alt=""></a></li>
                <li class="paging">
                    <%-- 현재 페이지 블록이 1보다 크면 처음으로 돌아가게끔 페이징--%>
                    <c:if test="${boadPage.nowPage >1}">
                        <a href="<c:url value="/boad/list?page=1"/> ">[처음]</a>
                    </c:if>
                    <%-- 위랑 동일 1보다 크면 이전 한칸전으로 이동 --%>
                    <c:if test="${boadPage.nowPage >1}">
                        <a href="<c:url value="/boad/list?page=${boadPage.nowPage-1}"/> ">[이전]</a>
                    </c:if>
                    <%--여기서는 페이지 블록에 관한 처리. --%>
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
                <a href="<c:url value="/boad/Write" />">
                    <button class="button">글쓰기</button>
                </a>
            </div>
        </c:if>
        <c:forEach items="${list}" var="list">
            <c:set var="title" value="${list.title}"/>
            <c:set var="name" value="${list.nickname}"/>
            <a href="<c:url value="/boad/BoadView?boad-index=${list.index}"/> ">
                <div class="sect_div_img"><img src="<c:url value='/img/logo.png'/>" alt=""></div>
                <div class="sect_div_title"><p><strong>${title}</strong></p></div>
                <div class="sect_div_user_img"></div>
                <div class="sect-div-user">${name} <br>${list.localDateTime}</div>
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