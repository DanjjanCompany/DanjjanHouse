<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!-- 메뉴바 관련 태그 -->
<nav class="navbar navbar-expand-sm bg-light">
	<div class="container-fluid">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link"
				href="${root}/board?action=list">공지 사항</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${root}/house?action=goSearch">아파트 검색</a></li>
			<c:if test="${userinfo ne null}">
				<li class="nav-item"><a class="nav-link"
					href="${root}/favorite?action=getFavorite">관심지역</a></li>
			</c:if>
		</ul>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link"
				style="text-align: center" href="${root}/index.jsp"><h3>WhereIsMyHome</h3></a></li>
		</ul>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<c:choose>
			<c:when test="${userinfo ne null}">
				<ul class="navbar-nav">
					<li class="nav-item"
						style="color: #f754a5; width: 170px; height: 40px; line-height: 40px"><strong>${userinfo.userName}</strong>님
						반갑습니다.</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/user/logout">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link"
						href="http://localhost:9000/go/member-list">My Page</a></li>

				</ul>
			</c:when>
			<c:otherwise>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="${root}/user/login">로그인</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/user/join">회원가입</a></li>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
</nav>