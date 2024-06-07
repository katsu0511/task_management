<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" flush="true"/>

<main>
	<div class="inner">
		<div class="box">
			
			<c:choose>
				<c:when test="${tasks.isEmpty()}">
					<div class="no_display">完了しているタスクがありません。</div>
					<div class="add_button">
						<a href="${pageContext.request.contextPath}/create/task" class="button">タスク追加</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="title">
						<h2>タスク一覧</h2>
					</div>
					
					<div class="add_button">
						<a href="${pageContext.request.contextPath}/create/task" class="button">タスク追加</a>
					</div>
					
					<c:if test="${tasks.size() >= 10}">
						<div class="front_buttons">
							<a href="${pageContext.request.contextPath}/top" class="button">トップ</a>
							<a href="${pageContext.request.contextPath}/index/all_tasks" class="button">全てのタスク</a>
						</div>
					</c:if>
					
					<div class="tasks">
						<c:forEach var="task" items="${tasks}">
							<div class="task">
								<a href="${pageContext.request.contextPath}/show/task?id=${task.TASKID}">
									${task.TASKNM}
								</a>
								<p>${task.TASKCT}</p>
							</div>
				    	</c:forEach>
				    </div>
				    
				</c:otherwise>
			</c:choose>
			
			<div class="front_buttons">
				<a href="${pageContext.request.contextPath}/top" class="button">トップ</a>
				<a href="${pageContext.request.contextPath}/index/all_tasks" class="button">全てのタスク</a>
			</div>
		</div>
	</div>
</main>
	
<jsp:include page="../common/footer.jsp" flush="true"/>
