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
    <title>Ur Join us?</title>
    <link rel="stylesheet" href="<c:url value="/css/EditPage.css"/> "/>
</head>
<body>
<div id="wrap">
    <header>
        <a class="logo" href="<c:url value="/main/FirstVisited"/> "><img src="<c:url value='/img/logo_transparent.png'/>"></a>
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
                    <li><a href="<c:url value="/Login/logout"/> ">로그아웃</a></li>
                </c:if>
            </ul>
        </nav>
    </header>
    <section>
        <div class="Edit-container">
            <div>

            </div>
        </div>

    </section>
</div>
</body>
<script>
</script>
</html>