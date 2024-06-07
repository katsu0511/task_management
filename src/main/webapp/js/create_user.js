var create = document.getElementById('create');
var userName = document.getElementById('user_name');
var password1 = document.getElementById('password1');
var password2 = document.getElementById('password2');
var userNameError1 = document.getElementById('user_name_error1');
var userNameError2 = document.getElementById('user_name_error2');
var password1Error1 = document.getElementById('password1_error1');
var password1Error2 = document.getElementById('password1_error2');
var password2Error1 = document.getElementById('password2_error1');
var password2Error3 = document.getElementById('password2_error3');
create.addEventListener('click', function (event) {
    if (userName.value == '') {
        userNameError1.style.display = 'block';
    }
    else {
        userNameError1.style.display = 'none';
    }
    if (password1.value == '') {
        password1Error1.style.display = 'block';
    }
    else {
        password1Error1.style.display = 'none';
    }
    if (password2.value == '') {
        password2Error1.style.display = 'block';
    }
    else {
        password2Error1.style.display = 'none';
    }
    if (userName.value == '') {
        userNameError2.style.display = 'none';
    }
    else if (!userName.value.match(/^(?=.*[a-z])(?=.*\d)[a-zA-Z0-9._!?/@-]{6,32}$/)) {
        userNameError2.style.display = 'block';
    }
    else {
        userNameError2.style.display = 'none';
    }
    if (password1.value == '') {
        password1Error2.style.display = 'none';
    }
    else if (!password1.value.match(/^(?=.*[a-z])(?=.*\d)[a-zA-Z0-9._!?/@-]{8,32}$/)) {
        password1Error2.style.display = 'block';
    }
    else {
        password1Error2.style.display = 'none';
    }
    if (password2.value == '') {
        password2Error3.style.display = 'none';
    }
    else if (password1.value != password2.value) {
        password2Error3.style.display = 'block';
    }
    else {
        password2Error3.style.display = 'none';
    }
    if (userName.value == '') {
        userName.focus();
    }
    else if (!userName.value.match(/^(?=.*[a-z])(?=.*\d)[a-zA-Z0-9._!?/@-]{6,32}$/)) {
        userName.focus();
    }
    else if (password1.value == '') {
        password1.focus();
    }
    else if (!password1.value.match(/^(?=.*[a-z])(?=.*\d)[a-zA-Z0-9._!?/@-]{8,32}$/)) {
        password1.focus();
    }
    else if (password2.value == '') {
        password2.focus();
    }
    else if (password1.value != password2.value) {
        password2.focus();
        event.preventDefault();
    }
    else {
        if (!confirm('登録しますか？')) {
            event.preventDefault();
        }
    }
});
