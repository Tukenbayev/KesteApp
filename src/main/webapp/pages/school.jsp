<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>School main page</title>
    <link rel="stylesheet" href="../css/school.css">
    <link rel="stylesheet" href="../js/jquery-ui-1.10.3/themes/base/jquery-ui.css"/>
    <link rel="stylesheet" href="../js/jquery-ui-1.10.3/themes/base/jquery.ui.all.css">

    <script src="../js/jquery-1.10.2.js"></script>
    <script src="../js/jquery-ui-1.10.3/ui/jquery.ui.core.js"></script>
    <script src="../js/jquery-ui-1.10.3/ui/jquery.ui.widget.js"></script>
    <script src="../js/jquery-ui-1.10.3/ui/jquery.ui.mouse.js"></script>
    <script src="../js/jquery-ui-1.10.3/ui/jquery.ui.draggable.js"></script>
    <script src="../js/jquery-ui-1.10.3/ui/jquery.ui.position.js"></script>
    <script src="../js/jquery-ui-1.10.3/ui/jquery.ui.resizable.js"></script>
    <script src="../js/jquery-ui-1.10.3/ui/jquery.ui.dialog.js"></script>
    <script src="../js/jquery-ui-1.10.3/ui/jquery.ui.button.js"></script>
    <script src="../js/jquery-ui-1.10.3/ui/jquery.ui.core.js"></script>
    <script src="../js/jquery-ui-1.10.3/ui/jquery.ui.menu.js"></script>
    <script src="../js/jquery-ui-1.10.3/ui/jquery.ui.autocomplete.js"></script>
    <script src="../js/mustache.js"></script>
    <script src="../js/school.js"></script>


</head>
<body>

    <span id="school_id" style="display: none;"><%=session.getAttribute("school_id")%></span>

    <template id="cl_letter">
        <div class="letter lttr" datafld="{{class_id}}"><span>{{class_Letter}}</span><img class="close" src="../images/close.png"/></div>
    </template>

    <template id="no_class">
        <div class="letter no-cl"><p>Нет ни одного класса</p></div>
    </template>
    <div class="top">
        <img src="../images/login_avatar.png" class="profile"/>

        <img src="../images/keste.png" id="logo"/>
        <button id="tchers" class="btn tbtn">Учителя</button>
        <button id="classes" class="btn tbtn">Классы</button>
        <button id="schedule" class="btn tbtn">Расписание</button>

    </div>
    <div class="container">
        <div class="class create-class">
            <p>Создайте класс. Введите номер класса и букву</p>
            <p class="error" id="exist">Класс уже существует</p>
            <p class="error" id="empty_number">Обязательное поле. Используйте цифры от 0 до 11</p>
            <input id="number" type="text" class="input"/><span>Номер</span><br>
            <p class="error" id="empty_letter">Обязательное поле. Используйте только буквы</p>
            <input id="letter" type="text" class="input"/><span>Буква</span><br>
            <button id="createClass" class="btn">Создать класс</button>
        </div>
        <div class="class classes">
            <p class="clss">Классы</p>
            <div class="clcont">
                <span class="digit" id="0">0</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>
            <div class="clcont">
                <span class="digit" id="1">1</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>
            <div class="clcont">
                <span class="digit" id="2">2</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>
            <div class="clcont">
                <span class="digit" id="3">3</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>
            <div class="clcont">
                <span class="digit" id="4">4</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>
            <div class="clcont">
                <span class="digit" id="5">5</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>
            <div class="clcont">
                <span class="digit" id="6">6</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>
            <div class="clcont">
                <span class="digit" id="7">7</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>
            <div class="clcont">
                <span class="digit" id="8">8</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>
            <div class="clcont">
                <span class="digit" id="9">9</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>
            <div class="clcont">
                <span class="digit" id="10">10</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>
            <div class="clcont">
                <span class="digit" id="11">11</span>
                <div class="letter no-cl"><p>Нет ни одного класса</p></div>
            </div>

        </div>

        <!-- teachers html-->

        <template id="asubject">
            <div class="subject tsubject" datafld="{{subjectId}}">
                <img src="../images/close.png" class="remove"/>
                <p class="slabel">{{subjectName}}</p>
            </div>
        </template>
        <template id="ateacher">
            <div class="subject" datafld="{{teacherId}}">
                <img src="../images/edit.png" class="remove edit">
                <p class="tlabel">{{teacherName}}</p>
            </div>
        </template>


        <div id="main" class="class">
            <div id="teacher">
                <p class="label">Номер телефона учителя</p>
                <p class="empty" id="pempty">Поле является обязательным</p>
                <p class="empty" id="phone-exist">Номер уже используется в системе. Попробуйте другой</p>
                <p class="small">В формате: 87771234567</p>
                <input type="text" id="phone">
                <p class="label">Фамилия Имя Отчество</p>
                <p class="empty" id="tempty">Поле является обязательным</p>
                <p class="small">В формате: Рустемова Р.А.</p>
                <input type="text" id="name">
                <p class="label">Добавьте предметы</p>
                <input type="text" id="subject"/>
                <p class="empty" id="least">Учитель должен преподавать хотя бы 1 предмет</p>

                <button id="addSubject" class="btn">Добавить предмет</button>
            </div>
            <button id="addTeacher" class="btn">Добавить учителя</button>
            <button id="save" class="btn">Сохранить</button>
            <button id="cancel" class="btn">Отмена</button>
        </div>
        <div id="subjects" class="main teachers">
            <p class="subjectsLabel"><strong>Предметы: </strong></p>
        </div>
        <div class="main teachers" id="teachers">
            <p class=""><strong>Учителя школы</strong></p>

        </div>

        <!--end of container-->
    </div>

</body>
</html>
