<!-- prettier-ignore -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- prettier-ignore -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@include file ="../../common/header.jsp" %>
<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <div class="container px-4 px-lg-5 mt-4 mb-5">
        <div class="row">

           <%@include file ="../sidebar.jsp" %>

            <!-- 메인 콘텐츠 -->
            <main class="col-md-10">
                <h2>사업자등록증 다운로드</h2>
                <div class="border-top border-default my-4"></div>
                <div class="container mt-4">
                    <form class="row g-2">
                        사업자 등록증, 교육기관 지정서 이미지 조회 및 다운로드 페이지입니다.
                    </form>
                </div>
            </main>
        </div>
    </div>

    <%@include file ="../../common/footer.jsp" %>
    <script>

        $(document).ready(function () {

        });

    </script>
</body>
</html>