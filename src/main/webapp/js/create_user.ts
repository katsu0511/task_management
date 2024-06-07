const create: HTMLInputElement = <HTMLInputElement>document.getElementById('create')
const userName: HTMLInputElement = <HTMLInputElement>document.getElementById('user_name')
const password1: HTMLInputElement = <HTMLInputElement>document.getElementById('password1')
const password2: HTMLInputElement = <HTMLInputElement>document.getElementById('password2')
const userNameError1: HTMLParagraphElement = <HTMLParagraphElement>document.getElementById('user_name_error1')
const userNameError2: HTMLParagraphElement = <HTMLParagraphElement>document.getElementById('user_name_error2')
const password1Error1: HTMLParagraphElement = <HTMLParagraphElement>document.getElementById('password1_error1')
const password1Error2: HTMLParagraphElement = <HTMLParagraphElement>document.getElementById('password1_error2')
const password2Error1: HTMLParagraphElement = <HTMLParagraphElement>document.getElementById('password2_error1')
const password2Error3: HTMLParagraphElement = <HTMLParagraphElement>document.getElementById('password2_error3')
create.addEventListener('click', function (event) {
	if (userName.value == '') {
		userNameError1.style.display = 'block'
    } else {
		userNameError1.style.display = 'none'
	}
	
	if (password1.value == '') {
		password1Error1.style.display = 'block'
    } else {
		password1Error1.style.display = 'none'
	}
	
	if (password2.value == '') {
		password2Error1.style.display = 'block'
    } else {
		password2Error1.style.display = 'none'
	}
	
	if (userName.value == '') {
		userNameError2.style.display = 'none'
	} else if (!userName.value.match(/^(?=.*[a-z])(?=.*\d)[a-zA-Z0-9._!?/@-]{6,32}$/)) {
		userNameError2.style.display = 'block'
	} else {
		userNameError2.style.display = 'none'
	}
	
	if (password1.value == '') {
		password1Error2.style.display = 'none'
	} else if (!password1.value.match(/^(?=.*[a-z])(?=.*\d)[a-zA-Z0-9._!?/@-]{8,32}$/)) {
		password1Error2.style.display = 'block'
	} else {
		password1Error2.style.display = 'none'
	}
	
	if (password2.value == '') {
		password2Error3.style.display = 'none'
	} else if (password1.value != password2.value) {
		password2Error3.style.display = 'block'
	} else {
		password2Error3.style.display = 'none'
	}
	
	if (userName.value == '') {
		userName.focus()
	} else if (!userName.value.match(/^(?=.*[a-z])(?=.*\d)[a-zA-Z0-9._!?/@-]{6,32}$/)) {
		userName.focus()
	} else if (password1.value == '') {
		password1.focus()
	} else if (!password1.value.match(/^(?=.*[a-z])(?=.*\d)[a-zA-Z0-9._!?/@-]{8,32}$/)) {
		password1.focus()
	} else if (password2.value == '') {
		password2.focus()
	} else if (password1.value != password2.value) {
		password2.focus()
		event.preventDefault()
	} else {
		if (!confirm('登録しますか？')) {
			event.preventDefault()
		}
	}
})
