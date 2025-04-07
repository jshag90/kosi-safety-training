<!-- prettier-ignore -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- prettier-ignore -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<%@include file ="../../common/header.jsp" %>
<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  </head>
  <body>
    <div class="container px-4 px-lg-5 mt-4 mb-5">
      <div class="row">
        <%@include file ="./sidebar.jsp" %>

        <main class="col-md-9">
          <h2>수강안내 및 신청</h2>
          <div class="border-top border-default my-4"></div>

          <!-- 상단 카드 -->
          <div class="card mb-4">
            <div class="card-body">
              <div class="row">
                <div class="col-md-4">
                  <img
                    src="data:image/png;base64,${COURSE_DTO.courseThumbnailBase64}"
                    class="img-fluid rounded"
                    alt="${COURSE_DTO.title}"
                  />
                  <!-- 이미지 하단에 버튼 추가 -->
                  <div class="mt-2 text-center">
                    <button
                      class="btn btn-sm"
                      id="courseStatusButton"
                      disabled
                    >
                      <c:choose>
                        <c:when test="${COURSE_DTO.courseStatus == 'OK_APPLY'}">
                          접수진행중_교육진행대기
                        </c:when>
                        <c:when
                          test="${COURSE_DTO.courseStatus == 'OVER_PERSON_COUNT'}"
                        >
                          접수불가능_신청인원초과
                        </c:when>
                        <c:when
                          test="${COURSE_DTO.courseStatus == 'INVALID_APPLY_DATE'}"
                        >
                          접수불가능_신청기간아님
                        </c:when>
                        <c:otherwise> 알 수 없음 </c:otherwise>
                      </c:choose>
                    </button>
                  </div>
                </div>
                <div class="col-md-8">
                  <h5 class="card-title">${COURSE_DTO.title}</h5>
                  <p class="card-text">
                    <strong>교육일정:</strong> ${COURSE_DTO.courseStartDate} ~
                    ${COURSE_DTO.courseEndDate}<br />
                    <strong>신청기간:</strong> ${COURSE_DTO.applyStartDate} ~
                    ${COURSE_DTO.applyEndDate}<br />
                    <strong>교육시간:</strong> ${COURSE_DTO.courseTimeSum}<br />
                    <strong>교육문의:</strong>
                    ${COURSE_DTO.courseQuestion}<br />
                    <strong>모집인원:</strong> ${COURSE_DTO.maxCapacity}명
                    <span style="color: red"
                      >(${COURSE_DTO.currentEnrollment}/${COURSE_DTO.maxCapacity})</span
                    ><br />
                    <strong>교육장소:</strong> ${COURSE_DTO.location}<br />
                    <strong>교육비:</strong>
                    <fmt:formatNumber
                      value="${COURSE_DTO.price}"
                      type="number"
                      groupingUsed="true"
                    />원
                  </p>
                </div>
              </div>
            </div>
            <div class="card-footer text-end">
              <button
                class="btn btn-primary"
                <c:if test="${COURSE_DTO.courseStatus != 'OK_APPLY'}">disabled</c:if>
                onclick="applyCourse(${COURSE_DTO.courseId})"
              >
                신청하기
              </button>
            </div>
          </div>

          <!-- 상세 내용 -->
          <div class="container mt-4">
            <h4>강의소개</h4>
            <p>${COURSE_DTO.description}</p>

            <h4>강의목록</h4>
          </div>
        </main>
      </div>
    </div>

    <input type="hidden" id="registrationPopupMessage" value="${COURSE_DTO.registrationPopupMessage}" />

    <%@include file ="../../common/footer.jsp" %>
    <script src="${contextPath}/js/course.js"></script>
    <script>
      $(document).ready(function () {
        const courseStatus = "${COURSE_DTO.courseStatus}";
        const $button = $("#courseStatusButton");
        const buttonClass = getCourseStatusButtonClass(courseStatus);
        $button.removeClass("btn-outline-success btn-outline-danger").addClass(buttonClass);
      });
       
      function applyCourse(courseId) {
        event.preventDefault();

        Swal.fire({
          title: "해당 교육을 수강하시겠습니까?",
          icon: "question",
          showCancelButton: true,
          confirmButtonText: "예",
          cancelButtonText: "아니오",
        }).then((result) => {
          if (result.isConfirmed) {
          // 사용자가 "예"를 클릭한 경우
          const registrationPopupMessage = $("#registrationPopupMessage").val();

          // 줄바꿈(\n)을 <br> 태그로 변환
          const formattedMessage = registrationPopupMessage.replace(/\n/g, "<br>");
          
            Swal.fire({
              title: "신청 안내",
              html: formattedMessage, // text 대신 html 속성을 사용
              icon: "info",
              confirmButtonText: "확인",
            });
          }
        });
      }

    </script>
  </body>
</html>
