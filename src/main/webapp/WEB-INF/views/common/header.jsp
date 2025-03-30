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
    <link rel="icon" type="image/x-icon" href="${contextPath}/assets/favicon.ico"/>
    <link rel="stylesheet" href="${contextPath}/css/styles.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/i18n/datepicker-ko.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <style>
      .nav-item.dropdown:hover .dropdown-menu {
        display: block;
      }
      .dropdown-menu {
        display: none;
      }
    </style>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-white">
      <div class="container px-5">
        <a class="navbar-brand" style="color: black" href="http://www.koosi.kr/"
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
       <div class="navbar-nav ms-auto mb-2 mb-lg-0" id="authLinks">
         <li class="nav-item">
           <a class="nav-link" style="color: black" data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a>
         </li>
         <li class="nav-item">
           <a class="nav-link" style="color: black" href="${contextPath}/member/sign-up">회원가입</a>
         </li>
       </div>

      </div>
    </nav>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <div class="container px-5">
        <a class="navbar-brand me-5" href="${contextPath}/">한국안전원(주) 에듀센터</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
          aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav w-100 d-flex justify-content-between">
            <!-- 집체교육 메뉴 -->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="groupTrainingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <b>집체교육</b>
              </a>
              <ul class="dropdown-menu" aria-labelledby="groupTrainingDropdown">
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/group-education/introduction">집체교육 소개</a></li>
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/group-education/supervisor-training-manufacture">수강안내 및 신청</a></li>
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/group-education/supervisor-training-manufacture" style="color: gray;">&nbsp;<i class="fa-regular fa-circle fa-sm"></i> 관리감독자 교육(제조업)</a></li>
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/group-education/supervisor-training-etc" style="color: gray;">&nbsp;<i class="fa-regular fa-circle fa-sm"></i> 관리감독자 교육(기타업)</a></li>
              </ul>
            </li>
    
            <!-- 인터넷 교육 메뉴 -->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="onlineTrainingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <b>인터넷 교육</b>
              </a>
              <ul class="dropdown-menu" aria-labelledby="onlineTrainingDropdown">
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/online-education/introduction">인터넷 교육소개</a></li>
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/online-education/regular-training">수강안내 및 신청</a></li>
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/online-education/regular-training" style="color: gray;">&nbsp;<i class="fa-regular fa-circle fa-sm"></i> 근로자 정기교육</a></li>
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/online-education/onboarding-training" style="color: gray;">&nbsp;<i class="fa-regular fa-circle fa-sm"></i> 채용 시 교육</a></li>
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/online-education/change-work-training"" style="color: gray;">&nbsp;<i class="fa-regular fa-circle fa-sm"></i> 작업내용 변경 시 교육</a></li>
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/online-education/supervisor-training-manufacture" style="color: gray;">&nbsp;<i class="fa-regular fa-circle fa-sm"></i> 관리감독자 교육(제조업)</a></li>
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/online-education/supervisor-training-etc" style="color: gray;">&nbsp;<i class="fa-regular fa-circle fa-sm"></i> 관리감독자 교육(기타업)</a></li>
              </ul>
            </li>
    
            <!-- 커뮤니티 메뉴 -->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="noticeDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <b>커뮤니티</b>
              </a>
              <ul class="dropdown-menu" aria-labelledby="noticeDropdown">
                <li><a class="dropdown-item" href="${contextPath}/board/notice/list">공지사항</a></li>
                <li><a class="dropdown-item" href="${contextPath}/board/qna/list">문의사항</a></li>
                <li><a class="dropdown-item" href="${contextPath}/board/brc/list">사업자등록증 다운로드</a></li>
              </ul>
            </li>
    
            <!-- 마이페이지 메뉴 -->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="mypageDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <b>마이페이지</b>
              </a>
              <ul class="dropdown-menu" aria-labelledby="mypageDropdown">
                <li><a class="dropdown-item" href="#!">개인정보수정</a></li>
                <li><a class="dropdown-item" href="#!">나의 강의실</a></li>
                <li><a class="dropdown-item" href="#!">수강신청 내역</a></li>
                <li><a class="dropdown-item" href="#!">수료증발급</a></li>
                <li><a class="dropdown-item" href="${contextPath}/course-lecture/course/save">교육과정등록(관리자용)</a></li>
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
                  <button id="loginBtn" type="button" class="btn btn-primary" onclick="loginAuthentication()">
                    <span id="loginSpinner" class="spinner-border spinner-border-sm d-none" role="status" aria-hidden="true"></span>
                    로그인
                  </button>
                </div>

              </form>
              <!-- 회원가입 | 아이디 찾기 | 비밀번호 찾기 -->
              <div class="d-flex justify-content-between">
                <a href="${contextPath}/member/sign-up" class="text-decoration-none small">회원가입</a>
                <span class="text-muted small">|</span>
                <a href="#!" class="text-decoration-none small">아이디 찾기</a>
                <span class="text-muted small">|</span>
                <a href="#!" class="text-decoration-none small">비밀번호 찾기</a>
              </div>

            </div>
          </div>
        </div>
      </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${contextPath}/js/scripts.js"></script>

    <script>

    $(document).ready(function () {

        $("#loginModal").on("hide.bs.modal", function () {
          $("button, input, select, textarea").each(function () {
            $(this).blur();
          });
        });

        const accessToken = sessionStorage.getItem("accessToken");
        if (accessToken) {
          $('#authLinks').children().hide();
          $('#authLinks').html(`
              <li class="nav-item">
                  <span class="nav-link" style="color: black">`+sessionStorage.getItem("userName")+`님</span>
              </li>
              <li class="nav-item">
                  <a class="nav-link" style="color: black" href="#" onclick="logout()">로그아웃</a>
              </li>
          `);
        }

    });

    function closeModal() {
        $("#loginModal").modal("hide"); // 모달 닫기
    }

    function loginAuthentication() {

        var $btn = $("#loginBtn");
        var $spinner = $("#loginSpinner");

        // 버튼 비활성화 및 로딩 스피너 표시
        $btn.prop("disabled", true);
        $spinner.removeClass("d-none");


        const loginData = {
            username: $('#loginId').val(),
            password: $('#loginPassword').val()
        };

        axios.post("${contextPath}/api/authenticate", loginData)
            .then(response => {

              console.log("Login Response:", response); // Debugging log

              const accessToken = response.data.data.accessToken;
              console.log("Access Token:", accessToken); // Debugging log

              sessionStorage.setItem("accessToken", accessToken);

              closeModal();
              getUserInfo();

            })
            .catch(error => {
                if (error.response && error.response.status === 401) {
                    Swal.fire({
                      title: '아이디 또는 비밀번호가 일치하지 않습니다.',
                      icon: 'error',
                      confirmButtonText: '확인',
                      didClose: () => {
                          $('#loginId').focus();
                      }
                    });
                } else {
                   Swal.fire({
                      title: '로그인 중 오류가 발생했습니다.',
                      icon: 'error',
                      confirmButtonText: '확인',
                      didClose: () => {
                          $('#loginId').focus();
                      }
                    });
                }
          })
          .finally(() => {
            $btn.prop("disabled", false);
            $spinner.addClass("d-none");
          });
    }

    function logout() {
        sessionStorage.removeItem("accessToken");
        axios.post("${contextPath}/api/logout", {}, { withCredentials: true })
            .then(response => {
                Swal.fire({
                    title: '로그아웃',
                    text: '로그아웃이 완료되었습니다.',
                    icon: 'success',
                    confirmButtonText: '완료',
                    backdrop: false
                }).then((result) => {
                  $('#authLinks').children().hide();
                  $('#authLinks').html(`
                      <li class="nav-item">
                          <a class="nav-link" style="color: black" data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link" style="color: black" href="${contextPath}/member/sign-up">회원가입</a>
                      </li>
                  `);
                });
            })
            .catch(error => {
                console.error('Error during logout:', error);
                alert('로그아웃 중 오류가 발생했습니다.');
            });
    }

    function getUserInfo() {
    const accessToken = sessionStorage.getItem("accessToken");

    if (!accessToken) {
        alert("로그인 정보가 없습니다. 다시 로그인해주세요.");
        return;
    }

    axios.get("${contextPath}/api/user", {
        headers: {"Authorization": ` Bearer `+accessToken}
    })
    .then(response => {
        console.log("User Info:", response.data);
        const userName = response.data.name; // 사용자 이름을 받아옵니다.
        console.log("User Info:", userName);
        sessionStorage.setItem("userName", userName); // 사용자 이름을 세션 스토리지에 저장합니다.
        // #authLinks 안의 기존 내용을 숨기고, 로그인 후 사용자 이름과 로그아웃 버튼을 표시합니다.
        $('#authLinks').children().hide();
        $('#authLinks').html(`
            <li class="nav-item">
                <span class="nav-link" style="color: black">`+userName+`님</span>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="color: black" href="#" onclick="logout()">로그아웃</a>
            </li>
        `);
    })
    .catch(error => {
        if (error.response) {
            if (error.response.status === 401) {
                alert("인증이 필요합니다. 로그인해주세요.");
            } else {
                console.error("Error:", error.response.data);
            }
        } else {
            console.error("Error:", error);
            alert("서버와의 연결에 실패했습니다.");
        }
    });
}

    </script>
  </body>
</html>
