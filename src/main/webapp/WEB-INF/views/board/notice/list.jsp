<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@include file ="../../common/header.jsp" %>
<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/i18n/datepicker-ko.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
</head>
<body>
    <div class="container px-4 px-lg-5 mt-4">
        <div class="row">

            <%@include file ="./sidebar.jsp" %>
            
            <!-- 메인 콘텐츠 -->
            <main class="col-md-10">
                <h2>공지사항</h2>
                <div class="container mt-4">
                    <form class="row g-2">
                        <div class="col-md-2">
                            <input type="text" id="startDate" class="form-control datepicker" placeholder="시작일">
                        </div>
                        <div class="col-md-2">
                            <input type="text" id="endDate" class="form-control datepicker" placeholder="종료일">
                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-2">
                            <select id="searchField" class="form-select">
                                <option value="title">제목</option>
                                <option value="author">작성자</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <input type="text" id="searchWord" class="form-control" placeholder="검색어 입력">
                        </div>
                        <div class="col-md-1">
                            <button type="button" id="searchBtn" class="btn btn-primary w-100">검색</button>
                        </div>
                    </form>
                </div> <br/>
                <table id="noticeTable" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>조회수</th>
                            <th>등록일</th>
                            <th>첨부파일</th>
                        </tr>
                    </thead>
                </table>
            </main>
        </div>
    </div>

    <%@include file ="../../common/footer.jsp" %>
    <script>

        var ctx = "${contextPath}";
        $(document).ready(function () {

            datepickEvt();

            $('.datepicker').datepicker({
                format: "yyyy-mm-dd",
                autoclose: true,
                todayHighlight: true,
                language: "ko"
            });


            let table = $('#noticeTable').DataTable({
                processing: true,
                serverSide: true,
                searching: false,
                ordering: false,
                ajax: {
                    url: ctx+"/api/notice/list",
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
                            alert("공지사항 데이터를 불러오는 데 실패했습니다.");
                            return [];
                        }
                        json.recordsTotal = json.data.total;
                        json.recordsFiltered = json.data.total;
                        return json.data.list;
                    }
                },
                columns: [
                    { data: "rownum" },
                    {
                        data: "title",
                        render: function (data, type, row) {
                            return row.title ? `<a href="${ctx}/notice/detail/${row.id}">${row.title}</a>` : "제목 없음";
                        }
                    },
                    { data: "author" },
                    { data: "views" },
                    { 
                        data: "createdAt",
                        render: function (data) {
                            return new Date(data).toLocaleString('ko-KR');
                        }
                    },
                    { 
                        data: "hasUploadFile",
                        render: function (data) {
                            return data ? "📎 있음" : "❌ 없음";
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
        });

        function datepickEvt(){
            $.datepicker.setDefaults({
                dateFormat: 'yy-mm-dd',
                prevText: '이전 달',
                nextText: '다음 달',
                monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                dayNames: ['일', '월', '화', '수', '목', '금', '토'],
                dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
                dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
                showMonthAfterYear: true,
                yearSuffix: '년'
            });
            //실행  
            $(function () {
                $('#datepick').datepicker();
            });
        }
    </script>
</body>
</html>