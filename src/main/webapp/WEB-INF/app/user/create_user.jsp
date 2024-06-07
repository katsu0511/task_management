<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" flush="true"/>

<main>
	<div class="inner">
		<section class="box">
			<h2>アカウント登録</h2>
			
			<form class="form" action="${pageContext.request.contextPath}/create/user" method="POST">
				
				<c:if test="${errorMessage != null && errorMessage != ''}">
					<div class="error_message">
						${errorMessage}
					</div>
				</c:if>
			
				<div class="item">
					<label for="user_name">ユーザー名:</label>
					<div class="input">
						<input type="text" id="user_name" class="data_input" name="user_name" value="${user_name}" maxlength="32" pattern="^(?=.*[a-z])(?=.*\d)[a-zA-Z0-9._!?/@-]{6,32}$" autocomplete="off" required>
						<p id="user_name_error1" class="error">ユーザー名を入力してください</p>
						<p id="user_name_error2" class="error">正しいユーザー名の形式で入力して下さい</p>
					</div>
				</div>
				
				<div class="item">
					<label for="password">パスワード:</label>
					<div class="input">
						<input type="password" id="password1" class="data_input" name="password1" value="${password}" maxlength="32" pattern="^(?=.*[a-z])(?=.*\d)[a-zA-Z0-9._!?/@-]{8,32}$" autocomplete="new-password" required>
						<p id="password1_error1" class="error">パスワードを入力してください</p>
						<p id="password1_error2" class="error">正しいパスワードの形式で入力して下さい</p>
					</div>
				</div>
				
				<div class="item">
					<label for="password">パスワード確認:</label>
					<div class="input">
						<input type="password" id="password2" class="data_input" name="password2" value="${password}" maxlength="32" pattern="^(?=.*[a-z])(?=.*\d)[a-zA-Z0-9._!?/@-]{8,32}$" autocomplete="new-password" required>
						<p id="password2_error1" class="error">パスワード確認を入力してください</p>
						<p id="password2_error3" class="error">同じパスワードを入力してください</p>
					</div>
				</div>
				
				<div class="buttons">
					<input type="submit" id="create" class="button" value="登録">
					<a href="${pageContext.request.contextPath}/login" class="button">戻る</a>
				</div>
			</form>
		
		</section>
	</div>
</main>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/create_user.js"></script>

<jsp:include page="../common/footer.jsp" flush="true"/>
