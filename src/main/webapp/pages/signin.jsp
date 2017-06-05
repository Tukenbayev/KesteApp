
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Keste</title>
    <link rel="stylesheet" href="../css/sign-in.css">
    <script src="../js/jquery-1.10.2.js"></script>
    <script src="../js/signin.js"></script>
</head>
<body>
<div class="header">

    <p class="speech">Один аккаунт для управления расписанием всей школы</p>
    <p class="enter">Войдите, чтобы продолжить работу на сайте</p>
</div>
<div id="container">
    <div id="first">
        <div class="avatar">
            <img src="../images/login_avatar.png" class="img_ava">
        </div>
        <div class="inputs">
            <input type="text" id="username" class="input" placeholder="Телефон или адрес электронной почты"/>
            <p id="empty" class="error">Введите адрес электронной почты или номер телефона.</p>
            <p id="email" class="error">Некорректный номер телефона или адрес электронной почты.</p>
            <p id="ntexist" class="error">Такого пользователя не существует</p>
            <button id="next">Далее</button>
        </div>
        <div>
            <p class="back2main"><a href="/">Назад на главную</a></p>
        </div>
    </div>
    <div id="second">
        <div>
            <img src="../images/back.png" class="back" id="back"/>
        </div>
        <div class="avatar">
            <img src="../images/login_avatar.png" class="img_ava2">
            <p id="userholder" class="username">8-777-206-13-18</p>
        </div>
        <div class="inputs">
            <input type="password" id="password" class="input" placeholder="Пароль"/>
            <p id="pass_error" class="error">Неверный пароль. Повторите попытку</p>
            <button id="submit">Войти</button>
        </div>
    </div>

</div>

</body>
</html>
