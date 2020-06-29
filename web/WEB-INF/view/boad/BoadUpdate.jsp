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
    <script src="<c:url value="/ckeditor/ckeditor.js"/> "></script>
    <title>Yu urlol Blog</title>
    <link rel="stylesheet" href="<c:url value='/css/BoadWrite.css' />">
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
                <li><a href="<c:url value="/boad/list"/> ">전체</a></li>
                <li><a href="<c:url value="/boad/BoadTech"/> ">테크</a></li>
                <li style="text-align: inherit;"><a href="<c:url value="/Urlol/UrlolForm"/> "><img CLASS="Lologo" src="<c:url value="/img/Lologo.png"/> " alt=""></a></li>

            </ul>
        </nav>
    </article>
    <section>
        <form:form action="../boad/EditBoad" method="post" modelAttribute="boadWriteVo">
            <div class="BoadInfo">
                    ${LoginInfo.nickname}<strong>님</strong>
                <form:select cssClass="BoadType" path="BoadType">
                    <option value="">--- 게시판 선택 ---</option>
                    <option value="전체">전체</option>
                    <option value="테크">테크</option>
                </form:select>
                <form:errors path="BoadType"/>
            </div>
            <form:hidden path="nickname" value="${LoginInfo.nickname}"/>
            <div class="WriteTitle">
                <fomr:hidden path="title" cssClass="write_title_input" placeholder="제목"/>
                <form:errors path="title"/>
            </div>
            <div class="WriteArea">
                <form:textarea path="Area_Boad" cssClass="write_area_input"/>
                <script>
                    var ckeditor_config = {
                        resize_enable: false,
                        enterMode: CKEDITOR.ENTER_BR,
                        shiftEnterMode: CKEDITOR.ENTER_P,
                        filebrowserUploadUrl: "/UploadFie"
                    };
                    CKEDITOR.replace("Area_Boad", {height: 600}, ckeditor_config);
                </script>
            </div>
            <input class="button" type="submit" value="완료">
        </form:form>
        <div class="backBtn"><a href="<c:url value="/boad/list"/> "><button class="button">뒤로가기</button></a></div>

    </section>
    <footer>
        <div class="DesFooter">
            Copyright 2020 © YoungU..
        </div>
    </footer>
</div>
</body>
<script src="<c:url value="/ckeditor/ckeditor.js"/> "></script>
</html>