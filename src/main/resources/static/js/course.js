function getCourseStatusText(courseStatus) {
  if (courseStatus === "OK_APPLY") {
    return "접수진행중_교육진행대기";
  } else if (courseStatus === "OVER_PERSON_COUNT") {
    return "접수불가능_신청인원초과";
  } else if (courseStatus === "INVALID_APPLY_DATE") {
    return "접수불가능_신청기간아님";
  } else {
    return "알 수 없음";
  }
}

function getCourseStatusButtonClass(courseStatus) {
  return courseStatus === "OK_APPLY"
    ? "btn-outline-success"
    : "btn-outline-danger";
}

function initCourseFeeFormatting(elementId) {
  $(elementId).on("input", function () {
    const value = $(this).val().replace(/,/g, "");
    if (!isNaN(value) && value !== "") {
      const formattedValue = Number(value).toLocaleString("ko-KR");
      $(this).val(formattedValue);
    } else {
      $(this).val("");
    }
  });
}
