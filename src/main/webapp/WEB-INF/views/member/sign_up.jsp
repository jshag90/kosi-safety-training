<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="../common/header.jsp" %>
<c:set var="contextPath" value="<%= request.getContextPath() %>"></c:set>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
</head>
<body>
    <div class="container px-4 px-lg-5 mt-4 mb-5">
        <div class="row">
            <%@ include file="./sidebar.jsp" %>
            <!-- 메인 콘텐츠 -->
            <main class="col-md-10">
                <h2>회원가입</h2>
                <div class="border-top border-default my-4"></div>
                <div class="container mt-4">
                    <form class="row g-2">
                        <!-- 로그인 아이디 -->
                        <div class="col-12">
                            <label for="username" class="form-label">로그인 아이디</label>
                            <input type="text" id="username" name="username" class="form-control" required>
                            <small id="usernameError" style="color: red; display: none;">영문 소문자와 숫자만 입력가능합니다.</small>
                        </div>
                        
                        <!-- 비밀번호 -->
                        <div class="col-12">
                            <label for="password" class="form-label">비밀번호</label>
                            <input type="password" id="password" name="password" class="form-control" placeholder="패스워드는 영문자와 특수문자 최소 1개를 포함해야합니다." required>
                            <small id="passwordError" style="color: red; display: none;">영문자와 특수문자 최소 1개를 포함해야합니다.</small>
                        </div>
                        
                        <!-- 비밀번호 확인 -->
                        <div class="col-12">
                            <label for="confirmPassword" class="form-label">비밀번호 확인</label>
                            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
                        </div>
                        
                        <!-- 사용자 이름 -->
                        <div class="col-12">
                            <label for="name" class="form-label">이름</label>
                            <input type="text" id="name" name="name" class="form-control" required>
                            <small id="nameError" style="color: red; display: none;">한글 혹은 영문만 입력가능합니다.</small>
                        </div>
                        
                        <!-- 이메일 -->
                        <div class="col-12">
                            <label for="email" class="form-label">이메일</label>
                            <input type="email" id="email" name="email" class="form-control" required>
                            <small id="emailError" style="color: red; display: none;">올바른 이메일 형식을 입력해주세요.</small>
                        </div>
                        
                        <!-- 생년월일 -->
                        <div class="col-12">
                            <label for="birthday" class="form-label">생년월일</label>
                            <input type="date" id="birthday" name="birthday" class="form-control" required>
                        </div>
                        
                        <!-- 휴대폰 번호 -->
                        <div class="col-12">
                            <label for="phoneNumber" class="form-label">휴대폰 번호</label>
                            <input type="tel" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="예: 010-1234-5678" required>
                            <small id="phoneError" style="color: red; display: none;">올바른 전화번호 형식을 입력해주세요.</small>
                        </div>
                        
                        <!-- 회사명 -->
                        <div class="col-12">
                            <label for="companyName" class="form-label">회사명</label>
                            <input type="text" id="companyName" name="companyName" class="form-control">
                        </div>
                        
                        <!-- 회사번호 -->
                        <div class="col-12">
                            <label for="companyNumber" class="form-label">회사 전화번호</label>
                            <input type="tel" id="companyNumber" name="companyNumber" class="form-control" placeholder="예: 02-1234-5678">
                            <small id="companyPhoneError" style="color: red; display: none;">올바른 전화번호 형식을 입력해주세요.</small>
                        </div>
                        
                        <!-- 제출 버튼 -->
                        <div class="col-12 text-center mt-4">
                            <button type="button" class="btn btn-primary" onclick="signup()">회원가입</button>
                        </div>
                    </form>
                </div>
            </main>
        </div>
    </div>

    <%@ include file="../common/footer.jsp" %>
</body>
<script>
    const usernameRegex = /^[a-z0-9]+$/;
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[^\w\s]).{6,20}$/;
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const phoneRegex = /^01[0-9]-\d{3,4}-\d{4}$/;
    const companyPhoneRegex = /^(01[0-9]-\d{3,4}-\d{4}|0\d{1,2}-\d{3,4}-\d{4})$/;
    const nameRegex = /^[가-힣a-zA-Z]+$/;

    $(document).ready(function() {
        $('#password').on('input', function() {
            const password = $(this).val();
            if (!passwordRegex.test(password)) {
                $('#passwordError').show();
                $(this).css('border', '2px solid red');
            } else {
                $('#passwordError').hide();
                $(this).css('border', '2px solid green');
            }
        });

        $('#username').on('input', function() {
            const username = $(this).val();
            if (!usernameRegex.test(username)) {
                $('#usernameError').show();
                $(this).css('border', '2px solid red');
            } else {
                $('#usernameError').hide();
                $(this).css('border', '2px solid green');
            }
        });

        $('#name').on('input', function() {
            const name = $(this).val();
            if (!nameRegex.test(name)) {
                $('#nameError').show();
                $(this).css('border', '2px solid red');
            } else {
                $('#nameError').hide();
                $(this).css('border', '2px solid green');
            }
        });

        $('#email').on('input', function() {
            const email = $(this).val();
            if (!emailRegex.test(email)) {
                $('#emailError').show();
                $(this).css('border', '2px solid red');
            } else {
                $('#emailError').hide();
                $(this).css('border', '2px solid green');
            }
        });

        $('#phoneNumber').on('input', function() {
            const phoneNumber = $(this).val();
            if (!phoneRegex.test(phoneNumber)) {
                $('#phoneError').show();
                $(this).css('border', '2px solid red');
            } else {
                $('#phoneError').hide();
                $(this).css('border', '2px solid green');
            }
        });

        $('#companyNumber').on('input', function() {
            const companyPhoneNumber = $(this).val();
            if (!companyPhoneRegex.test(companyPhoneNumber)) {
                $('#companyPhoneError').show();
                $(this).css('border', '2px solid red');
            } else {
                $('#companyPhoneError').hide();
                $(this).css('border', '2px solid green');
            }
        });
    });

    function signup() {
        // 입력된 값 가져오기
        const username = $('#username').val();
        const password = $('#password').val();
        const confirmPassword = $('#confirmPassword').val();
        const name = $('#name').val();
        const email = $('#email').val();
        const birthday = $('#birthday').val();
        const phoneNumber = $('#phoneNumber').val();
        const companyName = $('#companyName').val();
        const companyNumber = $('#companyNumber').val();

        // username 검증 (소문자 영어, 숫자만 허용)
        if (!usernameRegex.test(username)) {
            Swal.fire({
                title: '아이디는 영문 소문자와 숫자만 입력 가능합니다.',
                icon: 'error',
                confirmButtonText: '확인',
                didClose: () => {
                    $('#username').focus();
                }
            });
            return;
        }

        // password 검증 (영문자, 특수문자 최소 1개 포함)
        if (!passwordRegex.test(password)) {
            Swal.fire({
                title: '패스워드는 영문자, 특수문자를 최소 1개 포함해야합니다.',
                icon: 'error',
                confirmButtonText: '확인',
                didClose: () => {
                    $('#password').focus();
                }
            });
            return;
        }

        // 비밀번호 확인
        if (password !== confirmPassword) {
            Swal.fire({
                title: '비밀번호가 일치하지 않습니다.',
                icon: 'error',
                confirmButtonText: '확인',
                didClose: () => {
                    $('#password').focus();
                }
            });
            return;
        }

        // 사용자 이름 검증 (한글, 영어만 허용)
        if (!nameRegex.test(name)) {
            Swal.fire({
                title: '이름은 한글과 영어만 입력 가능합니다.',
                icon: 'error',
                confirmButtonText: '확인',
                didClose: () => {
                    $('#name').focus();
                }
            });
            return;
        }

        // email 검증 (올바른 이메일 형식 체크)
        if (!emailRegex.test(email)) {
            Swal.fire({
                title: '올바른 이메일 주소를 입력해주세요.',
                icon: 'error',
                confirmButtonText: '확인',
                didClose: () => {
                    $('#email').focus();
                }
            });
            return;
        }

        // 휴대폰 번호 검증 (010-1234-5678)
        if (phoneNumber !== '' && !phoneRegex.test(phoneNumber)) {
            Swal.fire({
                title: '올바른 휴대폰 번호를 입력해주세요. 입력형식을 확인해주세요.',
                icon: 'error',
                confirmButtonText: '확인',
                didClose: () => {
                    $('#phoneNumber').focus();
                }
            });
            return;
        }

        if (birthday === '') {
            Swal.fire({
                title: '생년월일을 입력해주세요.',
                icon: 'error',
                confirmButtonText: '확인',
                didClose: () => {
                    $('#birthday').focus();
                }
            });
            return;
        }

        // 회사 전화번호 검증 (02-1234-5678, 010-1234-5678)
        if (companyNumber.length > 0 && !companyPhoneRegex.test(companyNumber)) {
            Swal.fire({
                title: '올바른 회사 전화번호를 입력해주세요.',
                icon: 'error',
                confirmButtonText: '확인',
                didClose: () => {
                    $('#companyNumber').focus();
                }
            });
            return;
        }

        axios.post(`${contextPath}/api/signup`, {
            username: username,
            password: password,
            name: name,
            email: email,
            birthday: birthday,
            phoneNumber: phoneNumber,
            companyName: companyName,
            companyNumber: companyNumber
        }, {
            headers: { 'Content-Type': 'application/json' }
        })
        .then(response => {
            console.log('Response:', response);

            Swal.fire({
                title: '회원가입',
                text: '회원가입이 완료되었습니다.',
                icon: 'success',
                confirmButtonText: '완료',
                backdrop: false
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = "${contextPath}/";
                }
            });
        })
        .catch(error => {
            if (error.response.status === 409) {
                Swal.fire({
                    title: '회원가입 실패',
                    text: '이미 존재하는 로그인 아이디입니다. 로그인 아이디를 확인바랍니다.',
                    icon: 'warning',
                    confirmButtonText: '확인',
                    didClose: () => {
                        $('#username').focus();
                    }
                });
            } else {
                console.error('Error:', error.response ? error.response.data : error.message);
                Swal.fire({
                    title: '회원가입 실패',
                    text: '회원가입에 실패하였습니다. 관리자에게 문의하세요.',
                    icon: 'error',
                    confirmButtonText: '확인'
                });
            }
        });
    }
</script>
</html>
