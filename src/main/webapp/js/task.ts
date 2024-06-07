const submit: HTMLInputElement = <HTMLInputElement>document.getElementById('submit')
const taskName: HTMLInputElement = <HTMLInputElement>document.getElementById('task_name')
const taskDetail: HTMLTextAreaElement = <HTMLTextAreaElement>document.getElementById('task_detail')
const taskNameError: HTMLParagraphElement = <HTMLParagraphElement>document.getElementById('task_name_error')
const taskDetailError: HTMLParagraphElement = <HTMLParagraphElement>document.getElementById('task_detail_error')
submit.addEventListener('click', function (event) {
	if (taskName.value == '' || taskName.value.trim() == '') {
		taskNameError.style.display = 'block'
    } else {
		taskNameError.style.display = 'none'
	}
	
	if (taskDetail.value == '' || taskDetail.value.trim() == '') {
		taskDetailError.style.display = 'block'
    } else {
		taskDetailError.style.display = 'none'
	}
	
	if (taskName.value == '' || taskName.value.trim() == '') {
		taskName.focus()
		event.preventDefault()
	} else if (taskDetail.value == '' || taskDetail.value.trim() == '') {
		taskDetail.focus()
		event.preventDefault()
	} else {
		if (!confirm('保存しますか？')) {
			event.preventDefault()
		}
	}
})
