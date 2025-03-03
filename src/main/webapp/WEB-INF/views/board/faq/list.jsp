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
                <h2>자주하는질문(FAQ)</h2>
                <div class="border-top border-default my-4"></div>
                <div class="container mt-4">
                    <form class="row g-2">
                        <div class="col-md-2">
                            <select id="searchField" class="form-select">
                                <option value="ALL">전체</option>
                                <option value="회원가입">회원가입</option>
                                <option value="교육안내">교육안내</option>
                                <option value="신청안내">신청안내</option>
                                <option value="결제/환불">결제/환불</option>
                                <option value="법적근거">법적근거</option>
                                <option value="고용보험환급">고용보험환급</option>
                                <option value="고용보험환급">인터넷교육</option>
                            </select>
                        </div>
                        <div class="col-md-6"></div>
                        <div class="col-md-3">
                            <input type="text" id="searchWord" class="form-control" placeholder="검색어 입력">
                        </div>
                        <div class="col-md-1">
                            <button type="button" id="searchBtn" class="btn btn-primary w-100">검색</button>
                        </div>
                    </form>
                </div> <br/>
                <table id="faqTable" class="display" style="width:100%">
                </table>
            </main>
        </div>
    </div>

    <%@include file ="../../common/footer.jsp" %>
    <script>

        var ctx = "${contextPath}";
        $(document).ready(function () {

        $('#searchField').val('ALL'); // 기본 선택값 설정

        let table = $('#faqTable').DataTable({
            processing: true,
            serverSide: true,
            searching: false,
            ordering: false,
            ajax: {
                url: ctx+"/api/faq/list",
                type: "POST",
                contentType: "application/json; charset=UTF-8",
                data: function (d) {
                    return JSON.stringify({
                        pg: Math.floor(d.start / d.length) + 1,
                        pgSize: d.length,
                        searchField: $('#searchField').val(),
                        searchWord: $('#searchWord').val()
                    });
                },
                dataSrc: function (json) {
                    if (json.returnCode !== 0) {
                        alert("FAQ 데이터를 불러오는 데 실패했습니다.");
                        return [];
                    }
                    json.recordsTotal = json.data.total;
                    json.recordsFiltered = json.data.total;
                    return json.data.list;
                }
            },
            columns: [
                { 
                    data: "rownum",
                    render: function (data, type, row) {
                        return "Q "+row.rownum+".";
                    } 
                },
                {
                    data: "question",
                    render: function (data, type, row) {
                        return "<span class='question-text' data-id='"+row.id+"' style='cursor: pointer; color: blue; text-decoration: underline;'>"+row.question+"</span>";
                    }
                }
            ],
            language: {
                "lengthMenu": "페이지당 _MENU_ 개씩 보기",
                "zeroRecords": "데이터가 없습니다.",
                "info": "_START_ - _END_ (총 _TOTAL_ 개)",
                "infoEmpty": "데이터 없음",
                "infoFiltered": "(전체 _MAX_ 개 중 필터링됨)",
                "paginate": {
                    "first": "처음",
                    "last": "마지막",
                    "next": "다음",
                    "previous": "이전"
                }
            }
        });

        $('#searchBtn').on('click', function () {
            table.ajax.reload();
        });

        $('#searchWord').on('keypress', function (e) {
            if (e.which === 13) { // Enter 키 코드
                e.preventDefault(); // 기본 이벤트 방지
                $('#searchBtn').click(); // 검색 버튼 클릭 이벤트 호출
            }
        });


        // Question 클릭 이벤트 (아코디언 방식)
        $('#faqTable tbody').on('click', '.question-text', function () {
            let row = $(this).closest('tr');  // 클릭한 질문의 부모 행
            let faqId = $(this).data('id');   // FAQ의 ID

            // 이미 열려있는 아코디언이 있으면 닫기
            if (row.next().hasClass('accordion-content')) {
                row.next().remove();
                return;
            }

            // 기존의 열린 아코디언 모두 제거
            $('.accordion-content').remove();

            // API 요청해서 답변 가져오기
            $.ajax({
                url: ctx + "/api/faq/find-one/answer/" + faqId, // 상세 API 엔드포인트
                type: "GET",
                success: function (data) {
                    console.log(data.data.answer);
                    let answerHtml = "<tr class='accordion-content'><td colspan='2'><div style='padding: 10px; background: #f9f9f9; border-left: 3px solid #007bff;'>" +
                    "<strong>답변:</strong><br/>" + data.data.answer + "</div></td></tr>";

                    row.after(answerHtml); // 클릭한 행 아래에 추가
                },
                error: function () {
                    alert("FAQ 답변을 불러오는 데 실패했습니다.");
                }
            });
        });
});


    </script>
</body>
</html>