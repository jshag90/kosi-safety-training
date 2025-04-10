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
          <h2>교육과정등록</h2>
          <div class="border-top border-default my-4"></div>
          <div class="container mt-4">
            <form id="courseForm" class="row g-2">
              <div class="form-group row">
                <label class="col-md-2 col-form-label">노출여부</label>
                <div class="col-md-10 d-flex align-items-center">
                  <div class="form-check me-3">
                    <input
                      type="radio"
                      class="form-check-input"
                      id="visibleYes"
                      name="visibility"
                      value="Y"
                      required
                      checked
                    />
                    <label class="form-check-label" for="visibleYes"
                      >노출</label
                    >
                  </div>
                  <div class="form-check">
                    <input
                      type="radio"
                      class="form-check-input"
                      id="visibleNo"
                      name="visibility"
                      value="N"
                      required
                    />
                    <label class="form-check-label" for="visibleNo"
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

              <div class="form-group">
                <label for="courseName">교육명</label>
                <input
                  type="text"
                  class="form-control"
                  id="courseName"
                  name="courseName"
                  placeholder="교육명을 입력하세요."
                  required
                />
              </div>

              <div class="form-group">
                <label for="courseThumbnail">교육썸네일</label>
                <input
                  type="file"
                  class="form-control"
                  id="courseThumbnail"
                  name="courseThumbnail"
                  accept="image/*"
                  required
                />
              </div>

              <div class="form-group row">
                <div class="col-md-6">
                  <label for="startDate">교육 시작일</label>
                  <input
                    type="text"
                    class="form-control"
                    id="startDate"
                    name="startDate"
                    placeholder="교육 시작일을 선택하세요."
                    required
                  />
                </div>
                <div class="col-md-6">
                  <label for="endDate">교육 종료일</label>
                  <input
                    type="text"
                    class="form-control"
                    id="endDate"
                    name="endDate"
                    placeholder="교육 종료일을 선택하세요."
                    required
                  />
                </div>
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

              <div class="form-group text-center">
                <button
                  type="button"
                  class="btn btn-primary"
                  id="registerButton"
                  onclick="submitForm()"
                >
                  등록하기
                </button>
              </div>
            </form>
          </div>
        </main>
      </div>
    </div>

    <%@include file ="../../common/footer.jsp" %>
    <script src="${contextPath}/js/common.js"></script>
    <script>
      const accessToken = sessionStorage.getItem("accessToken");
      const contextUrl = "${contextPath}/";

      $(document).ready(function () {
        if (!accessToken) {
          requireLoginAlert(contextUrl);
          return;
        }

        const checkPermissionUrl = contextUrl + "api/check-permission";
        checkPermission(checkPermissionUrl, function (hasPermission) {
          if (hasPermission) {
            initCourseCategorySelector();
            initDatePickers();
            initTimePickers();
            initCourseFeeFormatting();
          } else {
            requireAdminRoleAlert(contextUrl);
          }
        });
      });

      function initCourseCategorySelector() {
        const categorySelect = $("#category");
        const apiEndpoint = contextUrl + "course-lecture/course-category";

        axios
          .get(apiEndpoint, {
            headers: {
              Authorization: "Bearer " + accessToken,
            },
          })
          .then((response) => {
            const categories = response.data.data;
            categories.forEach((category) => {
              const courseName =
                category.courseCategoryType + " - " + category.courseName;
              const option =
                "<option value=" + category.id + ">" + courseName + "</option>";
              categorySelect.append(option);
            });
          })
          .catch((error) => {
            console.error("Error fetching categories:", error);
            alert("카테고리 목록을 불러오는 중 오류가 발생했습니다.");
          });
      }

      function initDatePickers() {
        const datePickerOptions = {
          dateFormat: "yy-mm-dd",
          changeMonth: true,
          changeYear: true,
          showButtonPanel: true,
          closeText: "닫기",
          currentText: "오늘",
          showAnim: "slideDown",
          prevText: "이전",
          nextText: "다음",
          monthNames: [
            "1월",
            "2월",
            "3월",
            "4월",
            "5월",
            "6월",
            "7월",
            "8월",
            "9월",
            "10월",
            "11월",
            "12월",
          ],
          dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"],
        };

        $("#startDate").datepicker(datePickerOptions);
        $("#endDate").datepicker(datePickerOptions);

        $("#applyStartDate").datepicker(datePickerOptions);
        $("#applyEndDate").datepicker(datePickerOptions);
      }

      function initTimePickers() {
        const timePickerOptions = {
          timeFormat: "HH:mm",
          interval: 30,
          minTime: "00:00",
          maxTime: "23:59",
          startTime: "00:00",
          dynamic: false,
          dropdown: true,
          scrollbar: true,
        };

        $("#startTime").timepicker(timePickerOptions);
        $("#endTime").timepicker(timePickerOptions);
      }

      function initCourseFeeFormatting() {
        $("#courseFee").on("input", function () {
          const value = $(this).val().replace(/,/g, "");
          if (!isNaN(value) && value !== "") {
            const formattedValue = Number(value).toLocaleString("ko-KR");
            $(this).val(formattedValue);
          } else {
            $(this).val("");
          }
        });
      }

      function submitForm() {
        const form = $("#courseForm")[0];
        const formData = new FormData(form);

        const visibility = $('input[name="visibility"]:checked').val();
        console.log("Selected visibility:", visibility);
        formData.append("isPublished", visibility);

        console.log("Form Data:");
        for (let [key, value] of formData.entries()) {
          console.log(`${key}:`, value);
        }

        axios
          .post(contextUrl + "course-lecture/course/save", formData, {
            headers: {
              "Content-Type": "multipart/form-data",
              Authorization: "Bearer " + accessToken,
            },
          })
          .then((response) => {
            Swal.fire({
              title: "등록이 완료되었습니다.",
              icon: "success",
              confirmButtonText: "확인",
            }).then(() => {
              location.href = contextPath + "/";
            });
          })
          .catch((error) => {
            Swal.fire({
              title: "등록 중 오류가 발생했습니다.",
              text: "다시 시도해주세요.",
              icon: "error",
              confirmButtonText: "확인",
            });
          });
      }
    </script>
  </body>
</html>
