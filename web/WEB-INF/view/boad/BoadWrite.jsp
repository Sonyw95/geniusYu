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
    <%--<script src="https://cdn.ckeditor.com/ckeditor5/20.0.0/classic/ckeditor.js"></script>--%>
    <%--<script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>--%>
    <script src="<c:url value="/ckeditor/ckeditor.js"/> "></script>
    <title>Yu urlol Blog</title>
    <link rel="stylesheet" href="<c:url value='/css/BoadWrite.css' />">
</head>
<body>
<div id="wrap">
    <header>
        <a class="logo" href="<c:url value="/boad/list"/> "><img
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
                        <li>
                            <a href="https://kauth.kakao.com/oauth/logout?client_id=db3025daa10357d71f35ce5b1d9b9a6e&logout_redirect_uri=http://urlol.kr/Logout/KakaLogout">로그아웃</a>
                        </li>
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
                <li style="text-align: inherit;"><a href="<c:url value="/Urlol/UrlolForm"/> "><img class="Lologo"
                                                                                                   src="<c:url value="/img/Lologo.png"/> "
                                                                                                   alt=""></a></li>

            </ul>
        </nav>
    </article>
    <section>
        <form:form action="../boad/SuccessWirte" method="post" modelAttribute="boadWriteVo">
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
                <fomr:input path="title" cssClass="write_title_input" placeholder="제목"/>
                <form:errors path="title"/>
            </div>
            <div class="WriteArea">
                <form:textarea id="Area_Boad" path="Area_Boad" cssClass="write_area_input"/>
                <script type="text/javascript">
                    CKEDITOR.replace("Area_Boad", {
                        enterMode: CKEDITOR.ENTER_BR,
                        filebrowserUploadUrl:'../boad/writeImage?command=QuickUpload&type=Images&responseType=json'
                    });
                    // ClassicEditor
                    //     .create(document.querySelector('#Area_Boad'), {
                    //         headers:{
                    //             'Content-Type': "multipart/form-data;"
                    //         },
                    //         language: 'ko',
                    //         ckfinder: {
                    //             uploadUrl: '../boad/writeImage?command=QuickUpload&type=Images&responseType=json' // 내가 지정한 업로드 url (post로 요청감)
                    //         },
                    //         options: {
                    //             resourceType: 'Images'
                    //         },
                    //         alignment: {
                    //             options: ['left', 'center', 'right']
                    //         }
                    //     })
                    //     .then(editor => {
                    //         console.log('Editor was initialized', editor);
                    //         myEditor = editor;
                    //     })
                    //     .catch(error => {
                    //         console.error(error);
                    //     });
                </script>
            </div>
            <input class="button" type="submit" value="완료">
        </form:form>
        <div class="backBtn"><a href="<c:url value="/boad/list"/> ">
            <button class="button">뒤로가기</button>
        </a></div>

    </section>
    <footer>
        <div class="DesFooter">
            Copyright 2020 © YoungU..
        </div>
    </footer>
</div>
</body>
</html>