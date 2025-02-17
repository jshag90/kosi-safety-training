<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 목록</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
</head>
<body>

    <h2>공지사항 목록</h2>

    <!-- 검색 필터 -->
    <select id="searchField">
        <option value="title">제목</option>
        <option value="author">작성자</option>
    </select>
    <input type="text" id="searchWord" placeholder="검색어 입력">
    <button id="searchBtn">검색</button>

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

    <script>
         var ctx = "${contextPath}";
        $(document).ready(function () {
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
                            pg: Math.floor(d.start / d.length) + 1,  // 현재 페이지 번호 (1부터 시작)
                            pgSize: d.length,                     // 페이지 크기
                            searchField: $('#searchField').val(), // 검색 필드
                            searchWord: $('#searchWord').val()    // 검색어
                        });
                    },
                    dataSrc: function (json) {
                        if (json.returnCode !== 0) {  // 서버 응답 코드 확인
                            alert("공지사항 데이터를 불러오는 데 실패했습니다.");
                            return [];
                        }
                        // DataTables가 인식할 수 있도록 total 값을 직접 매핑
                        json.recordsTotal = json.data.total;
                        json.recordsFiltered = json.data.total;
                        return json.data.list;
                    },
                    beforeSend: function () {
                        console.log("요청 전송 중...");
                    },
                    complete: function (xhr) {
                        console.log("서버 응답 완료", xhr);
                    },
                    error: function (xhr, status, error) {
                        console.error("데이터 로딩 오류:", error);
                    }
                },
                columns: [
                    { data: "rownum" },
                    {
                        data: "title",
                        render: function (data, type, row) {
                            console.log("Row Data:", row);  // row 객체 확인
                            console.log("Title:", row.title);  // title 값 확인
                            console.log("ID:", row.id);  // id 값 확인

                            if (!row.title) {
                                return "제목 없음"; // title이 null이면 기본값 설정
                            }
                            return "<a href="+ctx+"'/notice/detail/${row.id}'>"+row.title+"</a>";
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
                    "search": "검색:",
                    "paginate": {
                        "first": "처음",
                        "last": "마지막",
                        "next": "다음",
                        "previous": "이전"
                    }
                }
            });

            // 검색 버튼 클릭 시 테이블 리로드
            $('#searchBtn').on('click', function () {
                table.ajax.reload();
            });
        });
    </script>

</body>
</html>
