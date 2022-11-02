<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!-- 메뉴바 관련 태그 -->
<nav class="navbar navbar-expand-sm bg-light">
	<div class="container-fluid">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="${root}/board?action=list">공지 사항</a></li>
			<li class="nav-item"><a class="nav-link" href="${root}/house/getSido">아파트 거래 검색</a></li>
			<li class="nav-item"><a class="nav-link" href="${root}/favorite?action=getFavorite">관심지역</a></li>
		</ul>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" style="padding-right:160px"href="${root}/index.jsp"><h3>WhereIsMyHome</h3></a></li>
		</ul>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<c:choose>
			<c:when test="${Member ne null}">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="${root}/member?action=logout">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/member?action=check">My Page</a></li>

				</ul>
			</c:when>
			<c:otherwise>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="${root}/user/login">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/user/join">회원가입</a></li>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
</nav>