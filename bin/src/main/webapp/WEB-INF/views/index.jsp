<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/header.jsp"%>
</head>
<body>
	<%@ include file="common/nav.jsp"%>

	<!-- Carousel -->
	<div id="demo" class="carousel slide" data-bs-ride="carousel">

		<!-- Indicators/dots -->
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#demo" data-bs-slide-to="0"
				class="active"></button>
			<button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
			<button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
		</div>

		<!-- The slideshow/carousel -->
		<div class="carousel-inner" style="height: 600px">
			<div class="carousel-item active">
				<img src="/assets/img/img1.jpg" alt="one" class="d-block"
					style="width: 100%; height: 600px">
			</div>
			<div class="carousel-item">
				<img src="/assets/img/img2.jpg" alt="two" class="d-block"
					style="width: 100%; height: 600px">
			</div>
			<div class="carousel-item">
				<img src="/assets/img/img3.jpg" alt="three" class="d-block"
					style="width: 100%; height: 600px">
			</div>
		</div>

		<!-- Left and right controls/icons -->
		<button class="carousel-control-prev" type="button"
			data-bs-target="#demo" data-bs-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#demo" data-bs-slide="next">
			<span class="carousel-control-next-icon"></span>
		</button>
		<footer>
			<%@ include file="common/footer.jsp"%>
		</footer>
	</div>
</body>
</html>