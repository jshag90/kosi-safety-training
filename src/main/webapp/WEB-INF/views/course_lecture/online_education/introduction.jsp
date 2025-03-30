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
                <h2>인터넷교육 소개</h2>
                <div class="border-top border-default my-4"></div>
                <div class="container mt-4">
                    <form class="row g-2">
                       
                        <div class="row gx-4 gx-lg-5">
                            <div class="col-md-30 mb-5">
                              <div class="card h-100">
                                <div class="card-body" style="background-color: #C0CDEF;">
                                  <h2 class="card-title">인터넷교육이란?</h2>
                                </div>
                                <div class="card-footer">
                                    현장업무로 바쁜 교육대상자 분들을 위한 과정으로 원하는 시간과 장소에서 컴퓨터를 이용하여
                                    자유롭게 학습 할 수 있는 교육을 제공하여  업무 공백 없이 안전보건교육을 실시할 수 있습니다.
                                </div>
                              </div>
                            </div>
                        </div>
                        <div class="row gx-4 gx-lg-5">
                            <div class="col-md-30 mb-5">
                              <div class="card h-100">
                                    <div class="card-body"  style="background-color: #C0CDEF;">
                                    <h2 class="card-title">교육 운영 절차</h2>
                                    </div>
                                    <div class="card-footer">
                                        [교육 운영 절차 이미지 추가]
                                    </div>
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