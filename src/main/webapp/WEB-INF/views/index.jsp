<!-- prettier-ignore -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- prettier-ignore -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>
<html lang="ko">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>한국안전원(주) 에듀센터</title>
    <!-- Favicon-->
    <link
      rel="icon"
      type="image/x-icon"
      href="${contextPath}/assets/favicon.ico"
    />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link rel="stylesheet" href="${contextPath}/css/styles.css" />

    <!-- Slick Slider CSS -->
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdn.jsdelivr.net/npm/slick-carousel/slick/slick.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdn.jsdelivr.net/npm/slick-carousel/slick/slick-theme.css"
    />
    <script src="https://cdn.jsdelivr.net/npm/slick-carousel/slick/slick.min.js"></script>
    <style>
      .slick-slider div {
        text-align: center;
      }

      .slick-slider img {
        width: 100%;
        height: 16rem;
        object-fit: cover;
      }
    </style>
  </head>
  <body>
    <%@include file ="common/header.jsp" %>

    <!-- Page Content-->
    <div class="container px-4 px-lg-5">
      <!-- Heading Row-->
      <div class="row gx-4 gx-lg-5 align-items-center my-5">
        <div class="col-lg-12">
          <div class="slick-slider">
            <div>
              <img
                src="https://picsum.photos/1200/400?random=1"
                class="img-fluid"
                alt="Slide 1"
              />
            </div>
            <div>
              <img
                src="https://picsum.photos/1200/400?random=2"
                class="img-fluid"
                alt="Slide 2"
              />
            </div>
            <div>
              <img
                src="https://picsum.photos/1200/400?random=3"
                class="img-fluid"
                alt="Slide 3"
              />
            </div>
            <div>
              <img
                src="https://picsum.photos/1200/400?random=4"
                class="img-fluid"
                alt="Slide 4"
              />
            </div>
          </div>
        </div>
      </div>
      <!-- Call to Action-->
      <div class="card text-white bg-secondary my-5 py-4 text-center">
        <div class="card-body">
          <p class="text-white m-0">
            This call to action card is a great place to showcase some important
            information or display a clever tagline!
          </p>
        </div>
      </div>
      <!-- Content Row-->
      <div class="row gx-4 gx-lg-5">
        <div class="col-md-4 mb-5">
          <div class="card h-100">
            <div class="card-body">
              <h2 class="card-title">Card One</h2>
              <p class="card-text">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem
                magni quas ex numquam, maxime minus quam molestias corporis
                quod, ea minima accusamus.
              </p>
            </div>
            <div class="card-footer">
              <a class="btn btn-primary btn-sm" href="#!">More Info</a>
            </div>
          </div>
        </div>
        <div class="col-md-4 mb-5">
          <div class="card h-100">
            <div class="card-body">
              <h2 class="card-title">Card Two</h2>
              <p class="card-text">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod
                tenetur ex natus at dolorem enim! Nesciunt pariatur voluptatem
                sunt quam eaque, vel, non in id dolore voluptates quos eligendi
                labore.
              </p>
            </div>
            <div class="card-footer">
              <a class="btn btn-primary btn-sm" href="#!">More Info</a>
            </div>
          </div>
        </div>
        <div class="col-md-4 mb-5">
          <div class="card h-100">
            <div class="card-body">
              <h2 class="card-title">Card Three</h2>
              <p class="card-text">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem
                magni quas ex numquam, maxime minus quam molestias corporis
                quod, ea minima accusamus.
              </p>
            </div>
            <div class="card-footer">
              <a class="btn btn-primary btn-sm" href="#!">More Info</a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <%@include file ="common/footer.jsp" %>

    <script src="${contextPath}/js/scripts.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/slick-carousel/slick/slick.min.js"></script>
    <script>
      $(document).ready(function () {
        $(".slick-slider").slick({
          slidesToShow: 1, // 한 번에 보여줄 슬라이드 개수
          slidesToScroll: 1,
          autoplay: true, // 자동 재생
          autoplaySpeed: 10000, // 자동 재생 속도 (10초)
          arrows: true, // 이전/다음 화살표 표시
          dots: true, // 페이지네이션 표시
          responsive: [
            {
              breakpoint: 768,
              settings: {
                slidesToShow: 2,
              },
            },
            {
              breakpoint: 480,
              settings: {
                slidesToShow: 1,
              },
            },
          ],
        });
      });
    </script>
  </body>
</html>
