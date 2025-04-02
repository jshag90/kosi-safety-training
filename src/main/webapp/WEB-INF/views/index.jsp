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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
          <div class="slick-slider"></div>
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
    <script src="https://cdn.jsdelivr.net/npm/slick-carousel/slick/slick.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
      $(document).ready(function () {
        // 서버에서 이미지 데이터를 가져오는 함수
        async function fetchSliderImages() {
          try {
            // 서버 API 호출
            const response = await axios.get(
              "${contextPath}/web-settings/main-slide-images"
            );

            // 서버에서 받은 데이터를 처리
            if (
              response.data.returnCode === 0 &&
              Array.isArray(response.data.data)
            ) {
              const images = response.data.data.map((image) => {
                const base64Image = image.imageData; // Base64 데이터 사용
                console.log(JSON.stringify(base64Image)); // 디버깅을 위한 로그 출력
                return {
                  src: "data:image/png;base64," + base64Image,
                  alt: image.imageName || "Slide Image",
                };
              });

              return images;
            } else {
              console.error(
                "이미지 데이터 형식이 올바르지 않습니다:",
                response.data
              );
              return [];
            }
          } catch (error) {
            console.error("이미지 데이터를 가져오는 중 오류 발생:", error);
            return [];
          }
        }

        // 슬라이더 초기화 함수
        async function initSlider() {
          const sliderImages = await fetchSliderImages();
          console.log(JSON.stringify(sliderImages)); // 디버깅을 위한 로그 출력
          // 슬라이더에 이미지 추가
          const $slider = $(".slick-slider");
          sliderImages.forEach(function (image) {
            $slider.append(
              '<div><img src="' +
                image.src +
                '" class="img-fluid" alt="' +
                image.alt +
                '" /></div>'
            );
          });

          // Slick Slider 초기화
          $slider.slick({
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
        }

        // 슬라이더 초기화 실행
        initSlider();
      });
    </script>
  </body>
</html>
