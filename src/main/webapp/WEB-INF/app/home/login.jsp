<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" flush="true"/>

<main>
	<div class="inner">
		<section class="box">
			<h2>ログイン</h2>
		
			<form class="form" action="${pageContext.request.contextPath}/login" method="POST">
				
				<div class="item">
					<label for="user_name">ユーザー名:</label>
					<div class="input">
						<input type="text" id="user_name" class="data_input" name="user_name" value="${user_name}">
					</div>
				</div>
				
				<div class="item">
					<label for="password">パスワード:</label>
					<div class="input">
						<input type="password" id="password" class="data_input" name="password" value="${password}">
					</div>
				</div>
				
				<c:if test="${errorMessage != null && errorMessage != ''}">
					<div class="error_message">
						${errorMessage}
					</div>
				</c:if>
					
				<div class="item">
					<div class="login_btn">
						<input type="submit" class="button" name="login_btn" value="ログイン">
					</div>
				</div>
			
			</form>
			
			<div class="create_account">
				<p>アカウント登録は</p>
				<a href="${pageContext.request.contextPath}/create/user">こちら</a>
			</div>
		</section>
	</div>
</main>

<jsp:include page="../common/footer.jsp" flush="true"/>