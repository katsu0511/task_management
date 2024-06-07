<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" flush="true"/>

<main>
	<div class="inner">
		<div class="box">
		
			<input type="hidden" id="context_path" value="${pageContext.request.contextPath}">
			
			<c:choose>
				<c:when test="${tasks.isEmpty()}">
					<div class="no_display">タスクがありません。</div>
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
							<a href="${pageContext.request.contextPath}/index/completed_tasks" class="button">完了したタスク</a>
						</div>
					</c:if>
					
					<div class="tasks">
						<c:forEach var="task" items="${tasks}">
							<div class="task">
								<a href="${pageContext.request.contextPath}/show/task?id=${task.TASKID}">
									${task.TASKNM}
								</a>
								<p>${task.TASKCT}</p>
								<c:if test="${task.TSKSTT == 'incompleted'}">
									<div class="complete">
										<button class="complete_btn button">完了</button>
										<input type="hidden" class="task_id" value="${task.TASKID}">
									</div>
								</c:if>
							</div>
				    	</c:forEach>
				    </div>
				    
				</c:otherwise>
			</c:choose>
			
			<div class="front_buttons">
				<a href="${pageContext.request.contextPath}/top" class="button">トップ</a>
				<a href="${pageContext.request.contextPath}/index/completed_tasks" class="button">完了したタスク</a>
			</div>
		</div>
	</div>
</main>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>

<jsp:include page="../common/footer.jsp" flush="true"/>
