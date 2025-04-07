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
    <link
      rel="stylesheet"
      href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css"
    />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <link
      rel="stylesheet"
      href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"
    />
    <script src="https://code.jquery.com/ui/1.12.1/i18n/datepicker-ko.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.css"
    />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
      .row {
        --bs-gutter-x: 0.5rem; /* Adjust the horizontal spacing between columns */
      }
      .form-group {
        margin-bottom: 0.7rem; /* 아래쪽 여백 추가 */
      }
    </style>
  </head>
  <body>
    <div class="container px-4 px-lg-5 mt-4 mb-5">
      <div class="row">
        <%@include file ="../sidebar.jsp" %>

        <!-- 메인 콘텐츠 -->
        <main class="col-md-10">
          <h2>교육과정관리</h2>
          <div class="border-top border-default my-4"></div>
          <div class="container mt-4">
            <form id="courseForm" class="row g-2">
              <div class="form-group row"></div>
            </form>
          </div>
          <div class="container mt-4">
            <table id="courseTable" class="display" style="width: 100%">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>교육과정명</th>
                  <th>모집인원</th>
                  <th>신청인원</th>
                  <th>서명신청인원</th>
                  <th>삭제</th>
                  <th>강의관리</th>
                </tr>
              </thead>
              <tbody>
                <!-- 서버에서 데이터를 가져와 동적으로 채워질 부분 -->
              </tbody>
            </table>
          </div>
        </main>
      </div>
    </div>

    <!-- Modal HTML 추가 -->
    <div
      class="modal fade"
      id="editCourseModal"
      tabindex="-1"
      aria-labelledby="editCourseModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editCourseModalLabel">교육과정 수정</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <form id="editCourseForm">
              <div class="mb-3">
                <label for="courseTitle" class="form-label">교육과정명</label>
                <input
                  type="text"
                  class="form-control"
                  id="courseTitle"
                  name="title"
                />
              </div>
              <div class="mb-3">
                <label for="courseDescription" class="form-label">설명</label>
                <textarea
                  class="form-control"
                  id="courseDescription"
                  name="description"
                ></textarea>
              </div>
              <div class="mb-3">
                <label for="courseStartDate" class="form-label"
                  >교육 시작일</label
                >
                <input
                  type="date"
                  class="form-control"
                  id="courseStartDate"
                  name="startDate"
                />
              </div>
              <div class="mb-3">
                <label for="courseEndDate" class="form-label"
                  >교육 종료일</label
                >
                <input
                  type="date"
                  class="form-control"
                  id="courseEndDate"
                  name="endDate"
                />
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              닫기
            </button>
            <button
              type="button"
              class="btn btn-primary"
              id="saveCourseChanges"
            >
              저장
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 강의관리 Modal HTML 추가 -->
    <div
      class="modal fade"
      id="lectureManagementModal"
      tabindex="-1"
      aria-labelledby="lectureManagementModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="lectureManagementModalLabel">
              강의 관리
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <table id="lectureTable" class="table table-bordered">
              <thead>
                <tr>
                  <th>강의 ID</th>
                  <th>강의명</th>
                  <th>강의 시작일</th>
                  <th>강의 종료일</th>
                </tr>
              </thead>
              <tbody>
                <!-- 서버에서 데이터를 가져와 동적으로 채워질 부분 -->
              </tbody>
            </table>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              닫기
            </button>
          </div>
        </div>
      </div>
    </div>

    <%@include file ="../../common/footer.jsp" %>
    <script>
      $(document).ready(function () {
        // DataTables 초기화
        const table = $("#courseTable").DataTable({
          ajax: {
            url: "${contextPath}/course-lecture/courses", // API 엔드포인트
            type: "GET",
            data: function (d) {
              // DataTables의 기본 파라미터를 서버 요구사항에 맞게 변환
              d.page = d.start / d.length + 1; // DataTables의 start를 page로 변환
              d.pageSize = d.length; // DataTables의 length를 pageSize로 변환
            },
            dataSrc: function (json) {
              console.log("서버 응답 데이터:", json);
              if (json.returnCode !== 0) {
                alert("공지사항 데이터를 불러오는 데 실패했습니다.");
                return [];
              }
              json.recordsTotal = json.data.total;
              json.recordsFiltered = json.data.total;
              return json.data.list;
            },
          },
          serverSide: true, // 서버 사이드 처리 활성화
          processing: true, // 로딩 표시 활성화
          ordering: false,
          searching: false,
          columns: [
            { data: "courseId" }, // ID
            { data: "title" }, // 교육과정명
            { data: "maxCapacity" }, // 모집인원
            { data: "currentEnrollment" }, // 신청인원
            { data: "writtenApplicationCount" }, // 서명신청인원
            {
              data: null,
              render: function (data, type, row) {
                return (
                  "<button class='btn btn-sm btn-danger' onclick=\"deleteCourse('" +
                  row.courseId +
                  "')\">삭제</button>"
                );
              },
            },
            {
              data: null, // 강의관리 버튼
              render: function (data, type, row) {
                return `
                  <button class="btn btn-sm btn-primary" data-id="${row.courseId}">관리</button>
                `;
              },
            },
          ],
          language: {
            info: "총 _TOTAL_개의 교육과정 중 _START_부터 _END_까지 표시", // 전체 갯수 표시
            infoEmpty: "표시할 데이터가 없습니다.", // 데이터가 없을 때 표시
            infoFiltered: "(총 _MAX_개의 데이터에서 필터링됨)", // 필터링된 데이터 정보
            paginate: {
              first: "처음", // "처음" 버튼 텍스트
              last: "마지막", // "마지막" 버튼 텍스트
              next: "다음", // "다음" 버튼 텍스트
              previous: "이전", // "이전" 버튼 텍스트
            },
          },
        });

        // 교육과정명 클릭 이벤트
        $("#courseTable").on("click", ".edit-course-title", function (e) {
          e.preventDefault();

          // 데이터 가져오기
          const courseId = $(this).data("id");
          const title = $(this).data("title");
          const description = $(this).data("description");
          const startDate = $(this).data("start-date");
          const endDate = $(this).data("end-date");

          // 모달에 데이터 채우기
          $("#courseTitle").val(title);
          $("#courseDescription").val(description);
          $("#courseStartDate").val(startDate);
          $("#courseEndDate").val(endDate);

          // 모달 표시
          $("#editCourseModal").modal("show");
        });

        // 저장 버튼 클릭 이벤트
        $("#saveCourseChanges").on("click", function () {
          const updatedData = {
            title: $("#courseTitle").val(),
            description: $("#courseDescription").val(),
            startDate: $("#courseStartDate").val(),
            endDate: $("#courseEndDate").val(),
          };

          // 서버로 수정 요청 보내기 (예: AJAX 요청)
          console.log("수정된 데이터:", updatedData);

          // 모달 닫기
          $("#editCourseModal").modal("hide");

          // 테이블 갱신
          table.ajax.reload();
        });

        // 삭제 버튼 클릭 이벤트
        $("#courseTable").on("click", ".delete-course", function () {
          const courseId = $(this).data("courseId");
          Swal.fire({
            title: "교육과정을 삭제하시겠습니까?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "예",
            cancelButtonText: "아니오",
          }).then((result) => {
            if (result.isConfirmed) {
              // axios를 이용한 DELETE 요청
              axios
                .delete(
                  "${contextPath}/course-lecture/courses?courseId=" + courseId
                )
                .then((response) => {
                  Swal.fire(
                    "삭제 완료",
                    "교육과정이 삭제되었습니다.",
                    "success"
                  );
                  table.ajax.reload(); // 테이블 데이터 갱신
                })
                .catch((error) => {
                  console.error("삭제 실패:", error);
                  Swal.fire(
                    "삭제 실패",
                    "교육과정을 삭제할 수 없습니다.",
                    "error"
                  );
                });
            }
          });
        });

        // 강의관리 버튼 클릭 이벤트
        $("#courseTable").on("click", ".btn-primary", function () {
          const courseId = $(this).data("id");
          $("#lectureManagementModal").modal("show");
          // 강의 데이터를 서버에서 가져오기
          /*      $.ajax({
            url: `${contextPath}/api/courses/${courseId}/lectures`, // 강의 조회 API 엔드포인트
            type: "GET",
            success: function (response) {
              if (response.returnCode === 0) {
                const lectures = response.data; // 강의 데이터 배열
                const lectureTableBody = $("#lectureTable tbody");
                lectureTableBody.empty(); // 기존 데이터를 초기화

                // 강의 데이터를 테이블에 추가
                lectures.forEach((lecture) => {
                  const row = `
                    <tr>
                      <td>${lecture.lectureId}</td>
                      <td>${lecture.title}</td>
                      <td>${lecture.startDate}</td>
                      <td>${lecture.endDate}</td>
                    </tr>
                  `;
                  lectureTableBody.append(row);
                });

                // 모달 표시
                $("#lectureManagementModal").modal("show");
              } else {
                Swal.fire(
                  "오류",
                  "강의 데이터를 불러오는 데 실패했습니다.",
                  "error"
                );
              }
            },
            error: function () {
              Swal.fire(
                "오류",
                "강의 데이터를 불러오는 데 실패했습니다.",
                "error"
              );
            },
          }); */
        });
      });

      function deleteCourse(courseId) {
        Swal.fire({
          title: "교육과정을 삭제하시겠습니까?",
          icon: "warning",
          showCancelButton: true,
          confirmButtonText: "예",
          cancelButtonText: "아니오",
        }).then((result) => {
          if (result.isConfirmed) {
            // axios를 이용한 DELETE 요청
            axios
              .delete(
                `${contextPath}/course-lecture/courses?courseId=` + courseId
              )
              .then((response) => {
                Swal.fire("삭제 완료", "교육과정이 삭제되었습니다.", "success");
                // DataTables 테이블 갱신
                $("#courseTable").DataTable().ajax.reload();
              })
              .catch((error) => {
                console.error("삭제 실패:", error);
                Swal.fire(
                  "삭제 실패",
                  "교육과정을 삭제할 수 없습니다.",
                  "error"
                );
              });
          }
        });
      }
    </script>
  </body>
</html>
