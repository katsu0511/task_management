var completeBtn = $('.complete_btn');
var context_path = $('#context_path');

completeBtn.click(function() {
	var task_id = $(this).parent().find('.task_id');
	var req = new XMLHttpRequest();
	var url = context_path.val() + "/change/task_state";
	req.open('POST',url);
	req.setRequestHeader('content-type', 'application/x-www-form-urlencoded;charset=UTF-8');
	req.send('id=' + task_id.val());

	req.onreadystatechange = function() {
		if (req.readyState === 4 && req.status === 200) {
			alert('完了しました');
			location.reload();
		}
	}
});
