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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.js"></script>
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

                        <div class="form-group row">
                            <label class="col-md-2 col-form-label">노출여부</label>
                            <div class="col-md-10 d-flex align-items-center">
                                <div class="form-check me-3">
                                    <input
                                        type="radio"
                                        class="form-check-input"
                                        id="visibleYes"
                                        name="visibility"
                                        value="yes"
                                        required
                                        checked
                                    />
                                    <label class="form-check-label" for="visibleYes">노출</label>
                                </div>
                                <div class="form-check">
                                    <input
                                        type="radio"
                                        class="form-check-input"
                                        id="visibleNo"
                                        name="visibility"
                                        value="no"
                                        required
                                    />
                                    <label class="form-check-label" for="visibleNo">비노출</label>
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
                                onclick=""
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
    <script>
        $(document).ready(function () {
            initCourseCategorySelector();
            initDatePickers();
            initTimePickers();
            initCourseFeeFormatting();
        });

        // Function to initialize course category selector
        function initCourseCategorySelector() {
            const categorySelect = $('#category');
            const apiEndpoint = `${contextPath}/course-lecture/course-category`;
            const accessToken = sessionStorage.getItem("accessToken");

            if (!accessToken) {
                alert("로그인이 필요합니다. 다시 로그인해주세요.");
                return;
            }

            axios.get(apiEndpoint, {
                headers: {
                    "Authorization": "Bearer " + accessToken
                }
            })
            .then(response => {
                const categories = response.data.data; // Assuming the API returns an array of categories
                categories.forEach(category => {
                    const courseName = category.courseCategoryType + " - " + category.courseName;
                    const option = `<option value="${category.id}">${courseName}</option>`;
                    categorySelect.append(option);
                });
            })
            .catch(error => {
                console.error('Error fetching categories:', error);
                alert('카테고리 목록을 불러오는 중 오류가 발생했습니다.');
            });
        }

        // Function to initialize datepickers
        function initDatePickers() {
            const datePickerOptions = {
                dateFormat: "yy-mm-dd", // Format the date as YYYY-MM-DD
                changeMonth: true, // Allow changing the month
                changeYear: true, // Allow changing the year
                showButtonPanel: true, // Show button panel for "Today" and "Done"
                closeText: "닫기", // Text for the close button
                currentText: "오늘", // Text for the "Today" button
                showAnim: "slideDown", // Animation for showing the datepicker
                prevText: "이전", // Text for the previous month button
                nextText: "다음", // Text for the next month button
                monthNames: [
                    "1월", "2월", "3월", "4월", "5월", "6월",
                    "7월", "8월", "9월", "10월", "11월", "12월"
                ], // Month names in Korean
                dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"], // Day names in Korean
            };

            // Initialize datepickers for start and end dates
            $("#startDate").datepicker(datePickerOptions);
            $("#endDate").datepicker(datePickerOptions);

            // Initialize datepickers for application start and end dates
            $("#applyStartDate").datepicker(datePickerOptions);
            $("#applyEndDate").datepicker(datePickerOptions);
        }

        // Function to initialize timepickers
        function initTimePickers() {
            const timePickerOptions = {
                timeFormat: "HH:mm", // Format the time as HH:mm (24-hour format)
                interval: 30, // Time interval in minutes
                minTime: "00:00", // Minimum time
                maxTime: "23:59", // Maximum time
                startTime: "00:00", // Start time
                dynamic: false, // Dynamically adjust the dropdown
                dropdown: true, // Show dropdown for time selection
                scrollbar: true // Enable scrollbar for the dropdown
            };

            // Initialize timepickers for start and end times
            $("#startTime").timepicker(timePickerOptions);
            $("#endTime").timepicker(timePickerOptions);
        }

        // Function to initialize formatting for the "교육비" field
        function initCourseFeeFormatting() {
            $('#courseFee').on('input', function () {
                const value = $(this).val().replace(/,/g, ''); // Remove existing commas
                if (!isNaN(value) && value !== '') {
                    const formattedValue = Number(value).toLocaleString('ko-KR'); // Format as currency
                    $(this).val(formattedValue); // Update the input value
                } else {
                    $(this).val(''); // Clear the input if invalid
                }
            });
        }
    </script>
</body>
</html>