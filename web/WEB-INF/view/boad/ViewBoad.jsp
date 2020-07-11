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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Yu Blog</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-light navbar-expand-sm"
     style="margin-bottom: 1em; background-color: white; box-shadow: 0 4px 12px 0 rgba(0, 0, 0, 0.05);">

    <div class="container">
        <div class="navbar-header">
            <a href="<c:url value="/boad/list"/>" class="navbar-brand"><img  style="width: 30px; height: 50px;"
                                                                            src="<c:url value='/img/logo_transparent.png'/>"
                                                                            alt="Brand"></a>
        </div>
        <button class="navbar-toggler navbar-toggler-right py-2" type="button" data-toggle="collapse"
                data-target="#collapseNav">
            <span class="navbar-toggler-icon"></span></button>

        <div class="collapse navbar-collapse" id="collapseNav">
            <ul class="navbar-nav ml-auto ">
                <c:if test="${empty LoginInfo}">
                    <li class="nav-item"><a class="nav-link" href="<c:url value="/Login/LoginPage"/> ">로그인</a></li>
                    <li class="nav-items"><a class="nav-link" href="<c:url value="/join/JoinUs"/> ">회원가입</a></li>
                </c:if>
                <%--로   그인정보가 있을 때.--%>
                <c:if test="${!empty LoginInfo}">
                    <%-- 일반적인 로그인 이라믄--%>
                    <c:if test="${empty LgoinType}">
                        <%-- 로그인된 계정 이름을 표시.--%>
                        <li class="nav-item"><a class="nav-link"
                                                href="<c:url value="/editInfo"/> ">${LoginInfo.nickname}</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value="/Login/logout"/>">로그아웃</a></li>
                    </c:if>
                    <%-- 근데 해당 로그인이 카카오 로그인이라면--%>
                    <c:if test="${!empty LgoinType}">
                        <li class="nav-items"><a class="nav-link"
                                                 href="<c:url value="/editInfo"/> ">${LoginInfo.nickname}</a></li>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="https://kauth.kakao.com/oauth/logout?client_id=db3025daa10357d71f35ce5b1d9b9a6e&logout_redirect_uri=http://urlol.kr/Logout/KakaLogout">로그아웃</a>
                        </li>
                    </c:if>
                </c:if>
            </ul>
        </div>

    </div>
</nav>
<c:forEach items="${list}" var="list">
    <c:set var="name" value="${list.nickname}"/>
    <c:set var="title" value="${list.title}"/>
    <c:set var="index" value="${list.index}"/>
    <c:set var="area" value="${list.area}"/>
    <c:set var="date" value="${list.localDateTime}"/>
</c:forEach>
<div class="row">
    <div class="container-fluid" style="padding:0; position: relative;">
        <div style="z-index:10; font-size:3em; text-align: center; position: absolute; color: #ccc; left: 3%; top: 45%;">${title} <br> <span
                style="font-size:15px;">${date}</span></div>
        <div class="col-md-12 col-xs-12" style="padding:0;">
            <img  style="height: 100vh; width: 100%;" src="<c:url value="/img/logo.png" />" alt="">
        </div>
    </div>
</div>
<section>
    <div class="container" style="margin-top:5em; margin-bottom: 5em; ">
        <div class="user" style="margin-bottom: 5em;">
            <div class="row">
                <div class="col-xs-4 col-md-4">${name} <span class="WriteSpan"> ${LoginInfo.oneline} </span></div>
            </div>
        </div>
        <div class="Area" style="margin-bottom: 5em;">
            ${area}
        </div>
        <div class="selectTab">
            <c:if test="${LoginInfo.nickname eq name}">
                <a class="buttonAtag" href="">
                    <button type="button" class="btn btn-primary">수정</button>
                </a>
                <a class="buttonAtag"
                   href="<c:url value="/boad/delBoad?title=${title}&name=${name}"/> ">
                    <button type="button" class="btn btn-warning">삭제</button>
                </a>
                <a class="buttonAtag" href="<c:url value="/boad/list"/> ">
                    <button type="button" class="btn btn-default">목록</button>
                </a>
            </c:if>
            <c:if test="${LoginInfo.nickname ne name}">
                <a class="buttonAtag" href="<c:url value="/boad/list"/> ">
                    <button type="button" class="btn btn-default">목록</button>
                </a>
            </c:if>
        </div>
    </div>
</section>
<div class="comment">
    <div class="container-fluid" style="padding-top:3em; background-color: rgba(0,0,0,0.05); ">
        <div class="row">
            <c:if test="${empty commentList}">
                <div class="col-xs-8 col-md-8" style="float: none; margin: 0 auto;">
                    <p class="bg-primary" style="color: white; padding:2em; box-shadow: 0 2px 6px 0 rgba(0,0,0,0.4);">아직
                        작성된 글이 없습니다.</p>
                </div>
            </c:if>
            <c:if test="${!empty commentList}">
                <c:forEach items="${commentList}" var="commentList">
                    <div class="col-xs-8 col-md-8" style="float: none; margin: 0 auto;">

                        <p class="bg-primary"
                           style="color: white; padding:2em; box-shadow: 0 2px 6px 0 rgba(0,0,0,0.4);">
                            <span style="font-size:10px;">${commentList.nickname}(${commentList.localDateTime})</span><br>${commentList.comment}
                        </p>

                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${empty LoginInfo}">
            <div class="col-xs-8 col-md-8" style="float: none; margin: 0 auto;">
                <p class="bg-warning" style="color: #856404; padding:1em; box-shadow: 0 2px 6px 0 rgba(0,0,0,0.4);">
                    로그인하셔야 작성이 가능합니다.</p>
                </c:if>
                <c:if test="${!empty LoginInfo}">
                    <div class="col-xs-8 col-md-8" style="float: none; margin: 0 auto;">
                        <form:form action="${commentUrl}" method="post" modelAttribute="commentVo"
                                   acceptCharset="UTF-8">
                            <c:url value="/boad/comment" var="commentUrl"/>
                            <form:hidden path="boad_host" value="${name}"/>
                            <form:hidden path="title" value="${title}"/>
                            <form:hidden path="board_index" value="${index}"/>
                            <form:hidden path="nickname" value="${LoginInfo.nickname}"/>
                            <form:input path="comment" style="margin-bottom: 5em;" cssClass="form-control"/>
                        </form:form>
                    </div>
                </c:if>
            </div>

        </div>
    </div>
</div>
<footer>
    <div class="container-fluid text-center"
         style="color: #ccc; font-size:20px; background-color: rgba(80, 90, 100, 0.8); height: 100px; padding-top:1em;">
        Copyright 2020 © YoungU
    </div>
</footer>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
        crossorigin="anonymous"></script>
</html>