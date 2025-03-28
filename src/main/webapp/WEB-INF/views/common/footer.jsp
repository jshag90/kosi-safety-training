<!-- prettier-ignore -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- prettier-ignore -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="<%= request.getContextPath() %>"></c:set>  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="EUC-KR">
<title>한국안전원(주)</title>
<style>
  .address-mark{
  	color:#4a4a4a;
  	font-weight:600;
  	font-size:26px;
  }
  .p {
    color:#ffffff
  }
</style>
<footer class="py-5 bg-light">
    <div class="container px-4 px-lg-5">


      <div class="row">
	    <div class="col-sm-2">
			<p style="font-size:15px;font-weight:600;">
				한국안전원(주) 에듀센터 | <a style="font-size:10px;">Since 2025</a>
			</p>
			<a href="${contextPath}"  class="address-mark">
				www.koosi.kr
			</a>
		</div>
			<div class="col-sm-7">
			<p>
				<a style="font-size:13px;" href="${contextPath}/introduce/gretting">회사소개</a>&nbsp;&nbsp;
				<a style="font-size:13px;" href="${contextPath}/introduce/personal">개인정보취급방침</a>&nbsp;&nbsp;
	      <a style="font-size:13px;" href="${contextPath}/introduce/serviceagree">서비스이용약관</a>&nbsp;&nbsp;
	      <a style="font-size:13px;" href="${contextPath}/introduce/maps">Contact Us</a>
      </p>
            
			<p style="font-size:12px;">
				<b>강릉사무실</b> : 강원특별자치도 강릉시 강릉대로 344, 2층(포남동)  <span style="margin-left: 15px;">TEL (033) 645-6330</span>  <span style="margin-left: 15px;">FAX (033) 645-6331</span><br>
				<b>원주사무실 </b>: 강원특별자치도 원주시 만대로 196-6, 3층(무실동)  <span style="margin-left: 13px;">TEL (033) 732-6330</span><span style="margin-left: 13px;">FAX (033) 732-6331</span>
			</p>
			
			<p style="font-size:13px;">Copyright©2025 <b>한국안전원(주)</b> All Right Reserved.</p>
			</div>
			
			<div class="col-sm-3">
			<p style="font-size:12px;border: 1px solid #d1d1d1;background:#ffffff; margin-top:1px; margin-bottom:1px; padding:3px;">
				<b>&nbsp;&nbsp;&nbsp;&nbsp;<img src="${contextPath}/image/korea_mark.jpg">&nbsp;고용노동부</b> 지정 안전관리전문기관
			</p>
			
			</div>
			


			<br>
</div>



    </div>
 </footer>