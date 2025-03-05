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
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/i18n/datepicker-ko.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
</head>
<body>
    <div class="container px-4 px-lg-5 mt-4 mb-5">
        <div class="row">

           <%@include file ="../sidebar.jsp" %>

            <!-- 메인 콘텐츠 -->
            <main class="col-md-10">
                <h2>교육과정등록</h2>
                <div class="border-top border-default my-4"></div>
                <div class="container mt-4">
                    <form class="row g-2">

                        <div class="form-group">
                            <label for="exampleInputEmail1">과정카테고리</label>
                            <input
                              type="text"
                              class="form-control"
                              id="NAME"
                              name="NAME"
                              aria-describedby="emailHelp"
                              placeholder="이름을 입력하세요."
                              required
                            />
                          </div>
                          <div class="form-group">
                            <label for="exampleInputEmail1">과정명</label>
                            <input
                              type="NUMBER"
                              class="form-control"
                              id="PHONE"
                              name="PHONE"
                              aria-describedby="emailHelp"
                              placeholder="연락처를 입력하세요.(ex.01012341234)"
                              required
                            />
                          </div>
                          <div class="form-group">
                            <label for="exampleInputEmail1">과정내용</label>
                            <input
                              type="text"
                              class="form-control"
                              id="LOGIN_ID"
                              name="LOGIN_ID"
                              placeholder="로그인 아이디를 입력하세요."
                              required
                            />
                          </div>
                          <div class="form-group">
                            <label for="exampleInputPassword1">수강인원(최대)</label>
                            <input
                              type="password"
                              class="form-control"
                              id="PASSWORD"
                              name="PASSWORD"
                              placeholder="비밀번호를 입력하세요.(8자리 이상)"
                              required
                            />
                          </div>
                  
                          <div class="form-group">
                            <label for="exampleInputPassword1">강사정보</label>
                            <input
                              type="password"
                              class="form-control"
                              id="PASSWORD_CHECK"
                              name="PASSWORD_CHECK"
                              placeholder="확인 비밀번호를 입력하세요."
                              required
                            />
                          </div>
                          <div class="form-group">
                            <label for="exampleInputEmail1">금액</label>
                            <input
                              type="EMAIL"
                              class="form-control"
                              id="EMAIL"
                              name="EMAIL"
                              aria-describedby="emailHelp"
                              placeholder="이메일을 입력하세요."
                              required
                            />
                          </div>
                          <div class="form-group">
                            <label for="exampleInputEmail1">썸네일</label>
                            <input
                              type="text"
                              class="form-control"
                              id="COMPANY_NAME"
                              name="COMPANY_NAME"
                              placeholder="회사명을 입력하세요."
                              required
                            />
                          </div>
                  
                          <div class="form-group">
                            <label for="exampleInputEmail1">학습기간</label>
                            <input
                              type="NUMBER"
                              class="form-control"
                              id="COMPANY_NUMBER"
                              name="COMPANY_NUMBER"
                              placeholder="회사 번호를 입력하세요."
                              required
                            />
                          </div>
                  
                          <div class="form-group">
                            <label for="exampleInputEmail1">가격</label>
                            <input
                              type="NUMBER"
                              class="form-control"
                              id="COMPANY_FAX"
                              name="COMPANY_FAX"
                              placeholder="회사 팩스 번호를 입력하세요."
                              required
                            />
                          </div>
                  
                          <div class="form-group text-right">
                            <button
                              type="button"
                              class="btn btn-primary"
                              onclick=""
                            >
                              교육과정생성하기
                            </button>
                          </div>


                    </form>
                </div>
            </main>
        </div>
    </div>

    <%@include file ="../../common/footer.jsp" %>
    <script>

    </script>
</body>
</html>