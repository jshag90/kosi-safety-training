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
                <h2>집체교육 소개</h2>
                <div class="border-top border-default my-4"></div>
                <div class="container mt-4">
                    <form class="row g-2">
                       
                        <div class="row gx-4 gx-lg-5">
                            <div class="col-md-30 mb-5">
                              <div class="card h-100">
                                <div class="card-body" style="background-color: #C0CDEF;">
                                  <h2 class="card-title">집체교육이란?</h2>
                                </div>
                                <div class="card-footer">
                                    현장 이외의 장소에서 (교육 전용 시설 또는 교육을 실시하기에 적합한 시설) 진행하는 교육으로,
                                    교육대상자를 공동으로 모아 회의장 또는 교육장에서 이루어지기 때문에 체계적인 장점이 있습니다.
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