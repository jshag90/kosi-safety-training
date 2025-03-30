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

            <%@include file ="./sidebar.jsp" %>

            <!-- 메인 콘텐츠 -->
            <main class="col-md-9">
                <h2>수강안내 및 신청</h2>
                <div class="border-top border-default my-4"></div>
                <div class="container mt-4">
                    <form class="row g-2">
                       
                        <div class="row gx-4 gx-lg-5">
                             <div class="col-md-30 mb-5">
                               <div class="card h-100">
                                 <div class="card-body">
                                   인터넷교육 체계 구축 · 개발로 인하여 당분간 인터넷교육 수강이 불가능합니다.
                                   양해 부탁드리며, 빠른 시일 내에 인터넷교육 수강이 가능하도록 조치하도록 하겠습니다.
                                 </div>
                               </div>
                             </div>
                         </div>


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