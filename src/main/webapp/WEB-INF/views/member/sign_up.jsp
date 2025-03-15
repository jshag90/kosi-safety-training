<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@include file ="../common/header.jsp" %>
<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
</head>
<body>
    <div class="container px-4 px-lg-5 mt-4 mb-5">
        <div class="row">
           
           <%@include file ="./sidebar.jsp" %>

            <!-- 메인 콘텐츠 -->
            <main class="col-md-10">
                <h2>회원가입</h2>
                <div class="border-top border-default my-4"></div>
                <div class="container mt-4">
                    <form class="row g-2" action="${contextPath}/api/signup" method="post">
                        <!-- 로그인 아이디 -->
                        <div class="col-12">
                            <label for="username" class="form-label">로그인 아이디</label>
                            <input type="text" id="username" name="username" class="form-control" required>
                        </div>
                        
                        <!-- 비밀번호 -->
                        <div class="col-12">
                            <label for="password" class="form-label">비밀번호</label>
                            <input type="password" id="password" name="password" class="form-control" required>
                        </div>
                        
                        <!-- 비밀번호 확인 -->
                        <div class="col-12">
                            <label for="confirmPassword" class="form-label">비밀번호 확인</label>
                            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
                        </div>
                        
                        <!-- 사용자 이름 -->
                        <div class="col-12">
                            <label for="name" class="form-label">이름</label>
                            <input type="text" id="name" name="name" class="form-control" required>
                        </div>
                        
                        <!-- 이메일 -->
                        <div class="col-12">
                            <label for="email" class="form-label">이메일</label>
                            <input type="email" id="email" name="email" class="form-control" required>
                        </div>
                        
                        <!-- 생년월일 -->
                        <div class="col-12">
                            <label for="birthday" class="form-label">생년월일</label>
                            <input type="date" id="birthday" name="birthday" class="form-control" required>
                        </div>
                        
                        <!-- 휴대폰 번호 -->
                        <div class="col-12">
                            <label for="phoneNumber" class="form-label">휴대폰 번호</label>
                            <input type="tel" id="phoneNumber" name="phoneNumber" class="form-control" required>
                        </div>
                        
                        <!-- 회사명 -->
                        <div class="col-12">
                            <label for="companyName" class="form-label">회사명</label>
                            <input type="text" id="companyName" name="companyName" class="form-control" required>
                        </div>
                        
                        <!-- 회사번호 -->
                        <div class="col-12">
                            <label for="companyNumber" class="form-label">회사 전화번호</label>
                            <input type="tel" id="companyNumber" name="companyNumber" class="form-control" required>
                        </div>
                        
                        <!-- 제출 버튼 -->
                        <div class="col-12 text-center mt-4">
                            <button type="button" class="btn btn-primary" onclick="signup()">회원가입</button>
                        </div>
                    </form>
                </div>
            </main>
        </div>
    </div>

    <%@include file ="../common/footer.jsp" %>
</body>
<script>
    function signup() {
        // 입력된 값 가져오기
        const username = $('#username').val();
        const password = $('#password').val();
        const confirmPassword = $('#confirmPassword').val();
        const name = $('#name').val();
        const email = $('#email').val();
        const birthday = $('#birthday').val();
        const phoneNumber = $('#phoneNumber').val();
        const companyName = $('#companyName').val();
        const companyNumber = $('#companyNumber').val();

        // 비밀번호 확인
        if (password !== confirmPassword) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        // AJAX를 이용하여 서버에 회원가입 요청 보내기
        $.ajax({
            url: '${contextPath}/api/signup', // JSP 컨텍스트 경로 적용
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                username: username,
                password: password,
                name: name,
                email: email,
                birthday: birthday,
                phoneNumber: phoneNumber,
                companyName: companyName,
                companyNumber: companyNumber
            }),
            success: function(response) {
                console.log('Response:', response);
                alert('회원가입 성공!');
                window.location.href = '${contextPath}/'; // 로그인 페이지로 이동
            },
            error: function(xhr, status, error) {
                console.error('Error:', xhr.responseText);
                alert('회원가입 실패! 다시 시도해주세요.');
            }
        });
    }

    </script>
</html>
