
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация школы</title>
    <link rel="stylesheet" href="../css/sign-up.css">
    <script src="../js/jquery-1.10.2.js"></script>
    <script src="../js/sign-up.js"></script>
</head>
<body>
<div class="back">
    <img src="../images/back.png" id="back"/>
</div>

<div id="container">
    <p class="big">Регистрация школы в системе KESTE</p>

    <form id="form" action="/register">
        <p class="label">Официальное название школы на казахском языке</p>
        <p class="error" id="kz_name_error">Поле является обязательным</p>
        <input type="text" name="schoolNameKZ" placeholder="KZ" class="input" id="nameKZ"/>
        <p class="label" >Официальное название школы на русском языке</p>
        <p class="error" id="ru_name_error">Поле является обязательным</p>
        <input type="text" name="schoolNameRU" placeholder="RU" class="input" id="nameRU"/>
        <p class="label">Официальное название школы на английском языке</p>
        <p class="error" id="en_name_error">Поле является обязательным</p>
        <input type="text" name="schoolNameEN" placeholder="EN" class="input" id="nameEN"/>
        <p class="label">Адрес школы</p>
        <p class="label small">Например: Алматинская обл., Карасайский р-н., п. Алмалыбак, ул. Карбишева 4</p>
        <p class="error" id="address_error">Поле является обязательным</p>
        <input type="text" name="address" class="input" id="address"/>
        <p class="label">Телефон школы. Используйте "-" и цифры</p>
        <input type="text" name="phone" class="input shrt" id="phone"/><span class="error right" id="phone_error">Минимум 5 символов</span>
        <p class="label">Адрес электронной почты. Будет использоваться для входа на сайт</p>
        <p class="error" id="email_null_error">Поле является обязательным</p>
        <p class="error" id="email_not_available">Адрес электронной почты уже используется</p>
        <input type="text" name="email" class="input shrt" placeholder="username@keste.kz" id="email"/><span id="email_match_error" class="error right">Неверный формат электронного адреса</span>
        <p class="label">Пароль</p>
        <input type="password" name="password" id="pass1" class="input shrt"/><span class="error right" id="password_min_error">Минимум 8 символов</span>
        <p class="label">Повторите пароль</p>
        <input type="password" id="pass2" class="input shrt"/><span class="error right" id="password_match_error">Пароли не совпадают</span><br>
        <input type="submit" id="submit" value="Зарегистрировать"/>
    </form>


</div>

</body>
</html>
