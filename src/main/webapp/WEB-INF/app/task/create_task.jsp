<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.io.*,java.util.*,java.text.*" %>
<jsp:include page="../common/header.jsp" flush="true"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main>
	<div class="inner">
		<div class="box">
		
			<h2>タスク追加</h2>
		
			<form class="form" action="${pageContext.request.contextPath}/create/task" method="POST">
			
				<div class="item">
					<label for="task_name">タスク名:</label>
					<div class="input">
						<input type="text" id="task_name" class="data_input" name="task_name" maxlength="100" required>
						<p id="task_name_error" class="error">タスク名を入力してください</p>
					</div>
				</div>
	
				<div class="item">
					<label for="task_detail">タスク詳細:</label>
					<div class="input">
						<textarea id="task_detail" class="data_input" name="task_detail" maxlength="8000" required></textarea>
						<p id="task_detail_error" class="error">タスク詳細を入力してください</p>
					</div>
				</div>
				
				<div class="buttons">
					<input type="submit" id="submit" class="submit_btn button" name="submit" value="追加">
					<button type="button" class="button" onclick="history.back()">戻る</button>
				</div>
				
			</form>
			
		</div>
	</div>
</main>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/task.js"></script>

<jsp:include page="../common/footer.jsp" flush="true"/>
