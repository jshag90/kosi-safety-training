<!-- prettier-ignore -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- prettier-ignore -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>
<html lang="ko">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>한국안전원(주) 에듀센터</title>
    <!-- Favicon-->
    <link
      rel="icon"
      type="image/x-icon"
      href="${contextPath}/assets/favicon.ico"
    />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link rel="stylesheet" href="${contextPath}/css/styles.css" />
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-white">
      <div class="container px-5">
        <a class="navbar-brand" style="color: black" href="#!"
          >한국안전원(주)</a
        >
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
       <div class="navbar-nav ms-auto mb-2 mb-lg-0">
         <li class="nav-item">
           <a class="nav-link" style="color: black" data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a>
         </li>
         <li class="nav-item">
           <a class="nav-link" style="color: black" href="#!">회원가입</a>
         </li>
       </div>

      </div>
    </nav>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container px-5">
        <a class="navbar-brand" href="#">한국안전원(주) 에듀센터</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
          aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <!-- 집체교육 -->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="groupTrainingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                집체교육
              </a>
              <ul class="dropdown-menu" aria-labelledby="groupTrainingDropdown">
                <li><a class="dropdown-item" href="#!">집체교육 소개</a></li>
                <li><a class="dropdown-item" href="#!">수강안내 및 신청</a></li>
              </ul>
            </li>
            <!-- 인터넷 교육 -->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="onlineTrainingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                인터넷 교육
              </a>
              <ul class="dropdown-menu" aria-labelledby="onlineTrainingDropdown">
                <li><a class="dropdown-item" href="#!">인터넷 교육소개</a></li>
                <li><a class="dropdown-item" href="#!">근로자 정기교육</a></li>
              </ul>
            </li>
            <!-- 알림마당 -->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="noticeDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                알림마당
              </a>
              <ul class="dropdown-menu" aria-labelledby="noticeDropdown">
                <li><a class="dropdown-item" href="${contextPath}/board/notice/list">공지사항</a></li>
                <li><a class="dropdown-item" href="${contextPath}/board/faq/list">자주하는 질문</a></li>
                <li><a class="dropdown-item" href="${contextPath}/board/qna/list">Q&A</a></li>
              </ul>
            </li>
            <!-- 마이페이지 -->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="mypageDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                마이페이지
              </a>
              <ul class="dropdown-menu" aria-labelledby="mypageDropdown">
                <li><a class="dropdown-item" href="#!">내 강의보기</a></li>
                <li><a class="dropdown-item" href="#!">수강신청내역</a></li>
                <li><a class="dropdown-item" href="#!">수료증발급</a></li>
                <li><a class="dropdown-item" href="#!">개인정보수정</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    

        <!-- 로그인 모달 -->
      <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true" data-bs-backdrop="static">
        <div class="modal-dialog modal-sm">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="loginModalLabel">로그인</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="closeModal()"></button>
            </div>
            <div class="modal-body">
              <form>
                <!-- 아이디 입력 -->
                <div class="mb-3">
                  <label for="loginId" class="form-label">아이디</label>
                  <input type="text" class="form-control" id="loginId" placeholder="아이디를 입력하세요">
                </div>
                <!-- 비밀번호 입력 -->
                <div class="mb-3">
                  <label for="loginPassword" class="form-label">비밀번호</label>
                  <input type="password" class="form-control" id="loginPassword" placeholder="비밀번호를 입력하세요">
                </div>
                <!-- 로그인 버튼 -->
                <div class="d-grid mb-3">
                  <button type="button" class="btn btn-primary" onclick="loginAuthentication()">로그인</button>
                </div>
              </form>
              <!-- 회원가입 | 아이디 찾기 | 비밀번호 찾기 -->
              <div class="d-flex justify-content-between">
                <a href="#!" class="text-decoration-none small">회원가입</a>
                <span class="text-muted small">|</span>
                <a href="#!" class="text-decoration-none small">아이디 찾기</a>
                <span class="text-muted small">|</span>
                <a href="#!" class="text-decoration-none small">비밀번호 찾기</a>
              </div>

            </div>
          </div>
        </div>
      </div>


    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="${contextPath}/js/scripts.js"></script>

    <script>

    $(document).ready(function () {

        $("#loginModal").on("hide.bs.modal", function () {
          $("button, input, select, textarea").each(function () {
            $(this).blur();
          });
        });

    });

    function closeModal() {
        $("#loginModal").modal("hide"); // 모달 닫기
    }

    function loginAuthentication() {

        const loginData = {
            username: $('#loginId').val(),
            password: $('#loginPassword').val()
        };

        axios.post("${contextPath}/api/authenticate", loginData)
            .then(response => {
                alert('로그인 성공');
            })
            .catch(error => {
                if (error.response && error.response.status === 401) {
                    alert('아이디(이메일) 또는 비밀번호가 일치하지 않습니다.');
                } else {
                    console.error('Error sending data:', error);
                    alert('로그인 중 오류가 발생했습니다.');
                }
          });

    }



    </script>
  </body>
</html>
