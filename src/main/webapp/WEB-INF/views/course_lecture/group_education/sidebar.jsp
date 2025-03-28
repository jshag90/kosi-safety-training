<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html lang="ko">
<style>
    .nav-link.active {
        font-weight: bold; /* 글씨를 굵게 설정 */
    }
</style>

<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>
      <!-- 왼쪽 사이드바 -->
      <nav class="col-md-2 d-none d-md-block bg-light sidebar">
        <div class="position-sticky">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <span class="nav-link fw-bold text-dark">집체교육</span> <!-- 서브메뉴 헤더 -->
                    <ul class="nav flex-column ms-3"> <!-- 하위 항목 -->
                        <li class="nav-item">
                            <a class="nav-link" href="/course-lecture/group-education/introduction">집체교육 소개</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">수강안내 및 신청</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">관리감독자 교육(제조업)</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">관리감독자 교육(기타업)</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>


</html>
<script>
    $(document).ready(function() {
        // 현재 페이지의 URL을 가져옵니다.
        var currentUrl = window.location.pathname;
        console.log(currentUrl);

        // URL에 맞는 메뉴 항목을 active로 설정
        $('.nav-link').each(function() {
            // 각 메뉴 항목의 링크 경로를 가져옵니다.
            var linkUrl = $(this).attr('href');

            // 현재 페이지의 URL과 메뉴 항목의 URL이 일치하면 active 클래스를 추가
            if (currentUrl === linkUrl) {
                $(this).addClass('active');
            }
        });
    });
</script>
