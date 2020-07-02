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
    <meta name="description" content="개발자 손영욱의 일기장 입니다.">
    <title>Yu urlol Blog</title>
    <link rel="stylesheet" href="<c:url value='/css/EditForm.css' />">
</head>
<body>
<div id="wrap">
    <header>
        <a class="logo" href="<c:url value="/boad/list"/> "><img
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
                <%--로   그인정보가 있을 때.--%>
                <c:if test="${!empty LoginInfo}">
                    <%-- 일반적인 로그인 이라믄--%>
                    <c:if test="${empty LgoinType}">
                        <%-- 로그인된 계정 이름을 표시.--%>
                        <li><a href="<c:url value="/editInfo"/> ">${LoginInfo.nickname}</a></li>
                        <li><a href="<c:url value="/Login/logout"/>">로그아웃</a></li>
                    </c:if>
                    <%-- 근데 해당 로그인이 카카오 로그인이라면--%>
                    <c:if test="${!empty LgoinType}">
                        <li>
                            <a href="https://kauth.kakao.com/oauth/logout?client_id=db3025daa10357d71f35ce5b1d9b9a6e&logout_redirect_uri=http://urlol.kr/Logout/KakaLogout">로그아웃</a>
                        </li>
                    </c:if>
                </c:if>
            </ul>
        </nav>
    </header>

    <%--Nav 바로 밑 게시판 탭에 관련된 아티클--%>
    <article>
        <nav class="Inside_Check">
            <ul class="Inside_list">
                <li class="active"><a href="<c:url value="/Edit/EditForm"/> ">정보변경</a></li>
                <li><a href="<c:url value="/Edit/LeaveMember"/> ">회원탈퇴</a></li>
            </ul>
        </nav>
    </article>
    <section>

        <div class="sect_container">

            <form:form action="${pageContext.request.contextPath}/Edit/EditSuccess" method="post" modelAttribute="editReq">

                <div> <h4>비밀번호를 입력하여야 변경이 가능합니다.</h4></div>

                <form:hidden path="email" value="${LoginInfo.email}"/>
                <form:hidden path="nickname" value="${LoginInfo.nickname}"/>

                <div><img src="<c:url value="/img/no-image.jpg"/> " alt=""></div>

                <div><form:input cssStyle="width: 30%;" path="oneline"/></div>

                <div>${LoginInfo.nickname}</div>

                <div>${LoginInfo.email}</div>

                <form:errors path="password"/>
                <div><form:password path="password" placeholder="비밀번호 입력"/>
                </div>

                <form:errors path="confirmPassword"/>
                <div><form:password path="confirmPassword" placeholder="비밀번호 확인"/>
                </div>

                <input class="button" type="submit" value="확인">

            </form:form>

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