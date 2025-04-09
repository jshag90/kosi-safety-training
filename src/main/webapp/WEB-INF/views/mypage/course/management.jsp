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
                  <th>노출여부</th>
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

    <div
      class="modal fade"
      id="editCourseModal"
      tabindex="-1"
      aria-labelledby="editCourseModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
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
              <!-- 노출여부 라디오 버튼 추가 -->
              <div class="mb-3">
                <label class="form-label">노출여부</label>
                <div>
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="isPublished"
                      id="isPublishedYes"
                      value="true"
                    />
                    <label class="form-check-label" for="isPublishedYes"
                      >노출</label
                    >
                  </div>

                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="isPublished"
                      id="isPublishedNo"
                      value="false"
                    />
                    <label class="form-check-label" for="isPublishedNo"
                      >비노출</label
                    >
                  </div>
                </div>
              </div>

              <div class="form-group">
                <label for="category">과정카테고리(노출위치)</label>
                <select
                  class="form-control"
                  id="category"
                  name="category"
                  required
                >
                  <option value="">카테고리를 선택하세요</option>
                </select>
              </div>

              <div class="mb-3">
                <label for="courseName" class="form-label">교육명</label>
                <input
                  type="text"
                  class="form-control"
                  id="courseName"
                  name="courseName"
                />
              </div>

              <div class="mb-3">
                <label for="courseThumbnail" class="form-label"
                  >교육썸네일</label
                >
                <input
                  type="file"
                  class="form-control"
                  id="courseThumbnail"
                  name="courseThumbnail"
                />
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

              <div class="form-group row">
                <div class="col-md-6">
                  <label for="startTime">교육 시작시간</label>
                  <input
                    type="text"
                    class="form-control"
                    id="startTime"
                    name="startTime"
                    placeholder="교육 시작시간을 선택하세요."
                    required
                  />
                </div>
                <div class="col-md-6">
                  <label for="endTime">교육 종료시간</label>
                  <input
                    type="text"
                    class="form-control"
                    id="endTime"
                    name="endTime"
                    placeholder="교육 종료시간을 선택하세요."
                    required
                  />
                </div>
              </div>

              <div class="form-group row">
                <div class="col-md-6">
                  <label for="applyStartDate">신청 시작일</label>
                  <input
                    type="text"
                    class="form-control"
                    id="applyStartDate"
                    name="applyStartDate"
                    placeholder="신청 시작일을 선택하세요."
                    required
                  />
                </div>
                <div class="col-md-6">
                  <label for="applyEndDate">신청 종료일</label>
                  <input
                    type="text"
                    class="form-control"
                    id="applyEndDate"
                    name="applyEndDate"
                    placeholder="신청 종료일을 선택하세요."
                    required
                  />
                </div>
              </div>

              <div class="form-group">
                <label for="recruitmentCount">모집인원</label>
                <input
                  type="number"
                  class="form-control"
                  id="recruitmentCount"
                  name="recruitmentCount"
                  placeholder="모집인원을 입력하세요."
                  required
                />
              </div>

              <div class="form-group">
                <label for="writtenApplicationCount">서면 신청 인원</label>
                <input
                  type="number"
                  class="form-control"
                  id="writtenApplicationCount"
                  name="writtenApplicationCount"
                  placeholder="서면 신청 인원을 입력하세요."
                  required
                />
              </div>

              <div class="form-group">
                <label for="courseLocation">교육장소</label>
                <input
                  type="text"
                  class="form-control"
                  id="courseLocation"
                  name="courseLocation"
                  placeholder="교육장소를 입력하세요."
                  required
                />
              </div>

              <div class="form-group">
                <label for="courseFee">교육비(원)</label>
                <input
                  type="text"
                  class="form-control"
                  id="courseFee"
                  name="courseFee"
                  placeholder="교육비를 입력하세요."
                  required
                />
              </div>

              <div class="form-group">
                <label for="courseDescription">교육설명</label>
                <textarea
                  class="form-control"
                  id="courseDescription"
                  rows="3"
                  name="courseDescription"
                  placeholder="교육설명을 입력하세요."
                  required
                ></textarea>
              </div>

              <!-- 안내 공문 등록 -->
              <div class="form-group">
                <label for="courseNotice">안내 공문</label>
                <input
                  type="file"
                  class="form-control"
                  id="courseNotice"
                  name="courseNotice"
                  accept=".pdf,.doc,.docx,.xlsx,.hwp,.hwpx"
                  required
                />
              </div>

              <div class="form-group">
                <label for="popupNotice">수강신청 팝업 안내문</label>
                <textarea
                  class="form-control"
                  id="popupNotice"
                  name="popupNotice"
                  rows="3"
                  placeholder="수강신청 팝업 안내문을 입력하세요."
                  required
                ></textarea>
              </div>

              <input type="hidden" id="courseId" name="courseId" />
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
              onclick="updateCourseChanges()"
            >
              수정
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
                  <th>노출여부</th>
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
        initCourseTable();
      });

      function initCourseCategorySelector(selectedCategoryId) {
        const categorySelect = $("#category");
        const apiEndpoint = `${contextPath}/course-lecture/course-category`;
        const accessToken = sessionStorage.getItem("accessToken");

        if (!accessToken) {
          alert("로그인이 필요합니다. 다시 로그인해주세요.");
          return;
        }

        axios
          .get(apiEndpoint, {
            headers: {
              Authorization: "Bearer " + accessToken,
            },
          })
          .then((response) => {
            const categories = response.data.data; // Assuming the API returns an array of categories
            categorySelect.empty(); // 기존 옵션 초기화
            categorySelect.append(
              '<option value="">카테고리를 선택하세요</option>'
            ); // 기본 옵션 추가

            categories.forEach((category) => {
              const courseName =
                category.courseCategoryType + " - " + category.courseName;
              const option =
                "<option value='" +
                category.id +
                "'" +
                (selectedCategoryId === category.id ? " selected" : "") +
                ">" +
                courseName +
                "</option>";
              categorySelect.append(option);
            });
          })
          .catch((error) => {
            console.error("Error fetching categories:", error);
            alert("카테고리 목록을 불러오는 중 오류가 발생했습니다.");
          });
      }

      function deleteCourse(courseId) {
        Swal.fire({
          title: "교육과정을 삭제하시겠습니까?",
          icon: "warning",
          showCancelButton: true,
          confirmButtonText: "예",
          cancelButtonText: "아니오",
        }).then((result) => {
          if (result.isConfirmed) {
            axios
              .delete(
                `${contextPath}/course-lecture/courses?courseId=` + courseId
              )
              .then((response) => {
                Swal.fire("삭제 완료", "교육과정이 삭제되었습니다.", "success");
                $("#courseTable").DataTable().ajax.reload(); // 테이블 데이터 갱신
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

      function updateCourseChanges() {
        const form = $("#editCourseForm")[0];
        const formData = new FormData(form);

        console.log("TEST :", JSON.stringify(formData));

        axios
          .put(`${contextPath}/course-lecture/course`, formData, {
            headers: {
              "Content-Type": "multipart/form-data",
              Authorization: "Bearer " + sessionStorage.getItem("accessToken"),
            },
          })
          .then(function (response) {
            Swal.fire(
              "수정 완료",
              "교육과정이 성공적으로 수정되었습니다.",
              "success"
            );
            $("#editCourseModal").modal("hide");
            $("#courseTable").DataTable().ajax.reload();
          })
          .catch(function (error) {
            console.error("수정 실패:", error);
            Swal.fire("오류", "교육과정을 수정하는 데 실패했습니다.", "error");
          });
      }

      function initCourseTable() {
        const courseDataTable = $("#courseTable").DataTable({
          ajax: {
            url: `${contextPath}/course-lecture/courses`,
            type: "GET",
            data: function (d) {
              d.page = d.start / d.length + 1;
              d.pageSize = d.length;
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
            {
              data: "title", // 교육과정명
              render: function (data, type, row) {
                return (
                  "<a href='#' onclick=\"initModifyCourseModal('" +
                  row.courseId +
                  "')\">" +
                  row.title +
                  "</a>"
                );
              },
            },
            {
              data: "isPublished",
              render: function (data, type, row) {
                return data ? "노출" : "비노출";
              },
            },
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
                return (
                  "<button class='btn btn-sm btn-primary' onclick=\"initLectureList('" +
                  row.courseId +
                  "')\">관리</button>"
                );
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

        return courseDataTable;
      }

      function initModifyCourseModal(courseId) {
        axios
          .get(`${contextPath}/course-lecture/course?courseId=` + courseId)
          .then(function (response) {
            const courseData = response.data.data;
            setModifyCourseModalFormData(courseData);
            $("#editCourseModal").modal("show");
          })
          .catch(function (error) {
            console.error("강의 정보를 가져오는 데 실패했습니다:", error);
            Swal.fire("오류", "강의 정보를 가져오는 데 실패했습니다.", "error");
          });
      }

      function setModifyCourseModalFormData(courseData) {
        $("#courseName").val(courseData.title);
        $("#courseDescription").val(courseData.description);
        $("#courseStartDate").val(courseData.courseStartDate);
        $("#courseEndDate").val(courseData.courseEndDate);
        $("#startTime").val(courseData.formattedCourseStartTime);
        $("#endTime").val(courseData.formattedCourseEndTime);
        $("#applyStartDate").val(courseData.applyStartDate);
        $("#applyEndDate").val(courseData.applyEndDate);
        $("#recruitmentCount").val(courseData.maxCapacity);
        $("#writtenApplicationCount").val(courseData.writtenApplicationCount);
        $("#courseLocation").val(courseData.location);
        $("#courseFee").val(Number(courseData.price).toLocaleString("ko-KR"));
        $("#popupNotice").val(courseData.registrationPopupMessage);
        $("#courseId").val(courseData.courseId);

        const courseCategoryId = courseData.courseCategoryId;

        // 노출 여부 라디오 버튼 설정
        courseData.isPublished === true
          ? $("#isPublishedYes").prop("checked", true)
          : $("#isPublishedNo").prop("checked", true);

        // 카테고리 초기화 및 선택
        initCourseCategorySelector(courseCategoryId);
      }

      function initLectureList(courseId) {
        $("#lectureManagementModal").modal("show");
        axios
          .get(`${contextPath}/course-lecture/lectures?courseId=` + courseId)
          .then(function (response) {
            const lectureData = response.data.data;
            const lectureTable = $("#lectureTable").DataTable();
            lectureTable.clear();
            lectureTable.rows.add(lectureData.list);
            lectureTable.draw();
          })
          .catch(function (error) {
            console.error("강의 데이터를 가져오는 데 실패했습니다:", error);
            Swal.fire(
              "오류",
              "강의 데이터를 가져오는 데 실패했습니다.",
              "error"
            );
          });
      }
    </script>
  </body>
</html>
