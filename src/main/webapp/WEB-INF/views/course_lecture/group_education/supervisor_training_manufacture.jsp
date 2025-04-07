<!-- prettier-ignore -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- prettier-ignore -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

        <!-- 메인 콘텐츠 -->
        <main class="col-md-9">
          <h2>수강안내 및 신청</h2>
          <div class="border-top border-default my-4"></div>
          <div class="container mt-4">
            <form class="row g-2">
              <div id="course-list" class="row"></div>
            </form>
          </div>
        </main>
      </div>
    </div>

    <%@include file ="../../common/footer.jsp" %>
    <script src="${contextPath}/js/course.js"></script>
    <script>
      $(document).ready(function () {
        axios
          .get('<c:url value="${contextPath}/course-lecture/course/list" />', {
            params: {
              courseCategory: "SUPERVISOR_TRAINING_MANUFACTURE",
              courseCategoryType: "GROUP_EDUCATION",
              page: 1,
              pageSize: 100,
            },
          })
          .then(function (response) {
            console.log("API 응답 결과:", response.data);

            const courses = response.data.data; // Assuming the API returns a JSON array
            const courseListContainer = document.getElementById("course-list");

            // Clear existing content
            courseListContainer.innerHTML = "";

            // Render each course as a Bootstrap card
            courses.forEach((course) => {
              // Get course status text using the new function
              const courseStatusText = getCourseStatusText(course.courseStatus);

              // Determine button class based on course status
              const buttonClass = getCourseStatusButtonClass(
                course.courseStatus
              );

              // Format course price
              const formattedPrice = Number(course.price).toLocaleString(
                "ko-KR"
              );

              const card =
                '<div class="col-12 mb-4">' + // col-12로 설정하여 한 줄에 하나의 카드만 표시
                '<div class="card h-100">' +
                '<div class="card-body">' + // card-body 안에서 이미지와 정보를 나눔
                '<div class="row g-0 d-flex align-items-center">' + // Bootstrap row와 d-flex를 사용하여 수평 정렬
                '<div class="col-md-4 me-3">' + // 왼쪽: 이미지, 오른쪽과 간격 추가
                '<img src="data:image/png;base64,' +
                course.courseThumbnailBase64 +
                '" class="img-fluid rounded" alt="' +
                course.title +
                '">' + // 네 모서리를 둥글게 처리
                '<div class="mt-2 text-center">' + // 이미지 하단에 버튼 배치
                '<button class="btn ' +
                buttonClass +
                ' btn-sm" disabled>' +
                courseStatusText +
                "</button>" +
                "</div>" +
                "</div>" +
                '<div class="col-md-7">' + // 오른쪽: 정보
                '<h5 class="card-title">' +
                course.title +
                "</h5>" +
                '<p class="card-text">' +
                "<strong>교육일정:</strong> " +
                course.courseStartDate +
                " ~ " +
                course.courseEndDate +
                "<br>" +
                "<strong>신청기간:</strong> " +
                course.applyStartDate +
                " ~ " +
                course.applyEndDate +
                "<br>" +
                "<strong>교육시간:</strong> " +
                course.courseTimeSum +
                "<br>" +
                "<strong>교육문의:</strong> " +
                course.courseQuestion +
                "<br>" +
                "<strong>모집인원:</strong> " +
                course.maxCapacity +
                '명 <span style="color: red;">(' +
                course.currentEnrollment +
                "/" +
                course.maxCapacity +
                ")</span><br>" +
                "<strong>교육장소:</strong> " +
                course.location +
                "<br>" +
                "<strong>교육비:</strong> " +
                formattedPrice +
                "원" +
                "</p>" +
                "</div>" +
                "</div>" +
                "</div>" +
                '<div class="card-footer text-end">' + // 하단의 card-footer는 오른쪽 정렬
                '<button class="btn btn-primary" onclick="enrollCourseDetail(' +
                course.courseId +
                ')">자세히 보기</button>' +
                "</div>" +
                "</div>" +
                "</div>";
              courseListContainer.innerHTML += card;
            });
          })
          .catch(function (error) {
            console.error("Error fetching course list:", error);
          });
      });

      function enrollCourseDetail(courseId) {
        event.preventDefault();
        const detailUrl =
          "${contextPath}/course-lecture/group-education/supervisor-training-manufacture/" +
          courseId;
        window.location.href = detailUrl;
      }
    </script>
  </body>
</html>
