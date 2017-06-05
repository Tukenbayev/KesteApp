<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Результаты поиска</title>
    <link rel="stylesheet" type="text/css" href="../css/main.css" >
    <script src="../js/jquery-3.2.1.min.js" ></script>
    <script src="../js/mustache.js"></script>
    <script src="../js/mainJS.js"></script>
</head>
<body>
<div class="top">
    <img src="../images/keste.png" class="element logo">
    <form class="element form" id="form">
        <input id="searchName" name="searchName" type="text" class="search" placeholder="Поиск по названию школы"/>
        <input type="submit" class="btn" value="Найти"/>
    </form>

    <div class="right">
        <button class="btn" id="signin">Войти</button>
        <button class="btn" id="signup">Регистрация</button>
    </div>
</div>
<div class="container">
    <p class="address resultCounter" id="NoRecordFound" style="display: none;">Поиск не дал результатов</p>

    <template id="result">
        <div class="result">
            <p class="schoolName">{{schoolNameRu}}</p>
            <p class="address"><span>Адрес: </span>{{address}}</p>
            <p class="address"><span>Номер телефона: </span>{{phone}}</p>
        </div>
    </template>

</div>
</body>
</html>
