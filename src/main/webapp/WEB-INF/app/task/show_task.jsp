<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" flush="true"/>

<main>
	<div class="inner">
		<div class="box">
		
			<input type="hidden" id="context_path" value="${pageContext.request.contextPath}">
			
			<div class="table">
				<div class="tr">
					<div class="th">タスクID:</div>
					<div class="td">
						${task.TASKID}
					</div>
				</div>
				
				<div class="tr flex_start">
					<div class="th">タスク名:</div>
					<div class="td">
						${task.TASKNM}
					</div>
				</div>
				
				<div class="tr flex_start">
					<div class="th">タスク詳細:</div>
					<div class="td">
						${task.TASKDT}
					</div>
				</div>
				
				<div class="tr">
					<div class="th">タスク状況:</div>
					<div class="td state_td">
						<c:choose>
							<c:when test="${task.TSKSTT == 'completed'}">
								<p>完了</p>
							</c:when>
							<c:otherwise>
								<p>未完了</p>
							</c:otherwise>
						</c:choose>
						
						<c:if test="${task.TSKSTT == 'incompleted'}">
							<div class="state">
								<button class="complete_btn button">完了</button>
								<input type="hidden" class="task_id" value="${task.TASKID}">
							</div>
						</c:if>
					</div>
				</div>
				
				<div class="tr">
					<div class="th">登録日時:</div>
					<div class="td">
						${task.RGDTTM.replaceAll("([0-9]+)-([0-9]+)-([0-9]+) ([0-9]+):([0-9]+):([0-9]+)","$1年$2月$3日 $4時$5分$6秒")}
					</div>
				</div>
				
				<div class="tr">
					<div class="th">更新日時:</div>
					<div class="td">
						${task.UPDTTM.replaceAll("([0-9]+)-([0-9]+)-([0-9]+) ([0-9]+):([0-9]+):([0-9]+)","$1年$2月$3日 $4時$5分$6秒")}
					</div>
				</div>
			</div>
			
			<div class="show_buttons">
				<form action="${pageContext.request.contextPath}/delete/task?id=${task.TASKID}" method="post">
					<input type="submit" id="delete" class="button" value="削除">
				</form>
				<a href="${pageContext.request.contextPath}/edit/task?id=${task.TASKID}" class="button">編集</a>
				<div class="back_button">
					<a href="${pageContext.request.contextPath}/top" class="button">戻る</a>
				</div>
			</div>
			
		</div>
		
	</div>
</main>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajax.js"></script>
<script type="text/javascript">
$('#delete').click(function() {
	if (!confirm('削除しますか？')) {
		return false;
	}
});
</script>

<jsp:include page="../common/footer.jsp" flush="true"/>
