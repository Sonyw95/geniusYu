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
    <meta name="description" content="개발자 손영욱의 일기장 입니다.">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Yu urlol Blog</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='/css/BoadList.css'/>">
</head>
<body>
<nav class="navbar navbar-light navbar-expand-sm"
     style="background-color: white; box-shadow: 0 4px 12px 0 rgba(0, 0, 0, 0.05);">

    <div class="container">
        <div class="navbar-header">
            <a href="<c:url value="/boad/list"/>" class="navbar-brand"><img style="width: 30px; height: 50px;"
                                                                            src="<c:url value='/img/logo_transparent.png'/>"></a>
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

<article>
    <div class="container ">
        <nav class="navbar navbar-light Board-Tab">
            <ul class="navbar-nav display-inline-block">
                <li class="nav-item display-inline-block"><a class="nav-link active"
                                                             href="<c:url value="/boad/list"/> "> 전체</a></li>
                <li class="nav-item display-inline-block"><a class="nav-link" href="<c:url value="/boad/BoadTech" />">
                    테크</a></li>
                <li class="nav-item display-inline-block"><a class="nav-link" href="<c:url value="/Urlol/UrlolForm"/> ">
                    LOL</a></li>
            </ul>
        </nav>
    </div>
</article>

<section>
    <div class="container">

        <c:if test="${!empty LoginInfo}">
            <div class="WriteBoad">
                <a href="<c:url value="/boad/Write" />">
                    <button type="button" class="btn btn-warning">글쓰기</button>
                </a>
            </div>
        </c:if>

        <div class="row">
            <c:forEach items="${list}" var="list">
                <div class="col-lg-4 ">
                    <div class="thumbnail">
                        <c:set var="title" value="${list.title}"/>
                        <c:set var="name" value="${list.nickname}"/>
                        <a href="<c:url value="/boad/BoadView?boad-index=${list.index}"/> ">
                            <div class="caiption">
                                <div class="sect_div_img"><img class="img-fluid list-image"
                                                               src="<c:url value='/img/logo.png'/>" alt="">
                                </div>
                                <div class="thumbnail-Detail"><p><strong>${title}</strong></p></div>
                                <div class="thumbnail-Detail"></div>
                                <div class="thumbnail-Detail">${name}님이 작성함 <br><span style="font-size: 12px;">${list.localDateTime}</span></div>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>


</section>


<%--여기서는 페이지 블록에 관한 처리. --%>
<div class="page container">
    <ul class="pagination justify-content-end">
        <c:if test="${boadPage.nowPage >1}">
            <li class="page-item"><a class="page-link page-setting" href="<c:url value="/boad/list?page=1"/> ">&laquo;</a></li>
        </c:if>
        <c:if test="${boadPage.nowPage >1}">
            <li class="page-item"><a class="page-link" href="<c:url value="/boad/list?page=${boadPage.nowPage-1}"/> ">&laquo;</a>
            </li>
        </c:if>

        <c:forEach var="num" begin="${boadPage.rangeStart}" end="${boadPage.rangeEnd}">
            <c:choose>
                <c:when test="${num == boadPage.nowPage}">
                    <li class="page-item active"><a class="page-link" href="#">${num}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="<c:url value="/boad/list?page=${num}"/>">${num}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${boadPage.nowPage < boadPage.rangeEnd}">
            <li class="page-item"><a class="page-link"
                                     href="<c:url value="/boad/list?page=${boadPage.nowRange-1}"/> ">&raquo;</a>
            </li>
        </c:if>
        <c:if test="${boadPage.nowPage < boadPage.rangeEnd}">
            <li class="page-item"><a class="page-link"
                                     href="<c:url value="/boad/list?page=${boadPage.rangeEnd}"/> ">&raquo;</a></li>
        </c:if>
    </ul>
</div>

<footer>
    <div class="container text-center" style="color:#ccc; height:3em;">
        Copyright 2020 ©<span style="color:gray;"> YoungU.. </span>
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