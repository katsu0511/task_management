<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>タスク管理アプリ</title>
	<link rel="icon" href="${pageContext.request.contextPath}/img/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

	<header>
		<div class="inner">
			<div class="header-left">
				<h1>
					<a href="${pageContext.request.contextPath}/top">タスク管理アプリ</a>
				</h1>
			</div>
			<c:if test="${sessionScope.user_name != null && sessionScope.password != null}">
				<div class="header-right">
					<form action="${pageContext.request.contextPath}/logout" method="POST">
						<button type="submit" class="button">ログアウト</button>
					</form>
				</div>
			</c:if>
		</div>
	</header>