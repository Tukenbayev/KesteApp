
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание учителей</title>
</head>
<body>
    <h3>Добавьте в систему учителей</h3>

    <form action="addTeacher" method="post">
        <h5>Номер телефона</h5><br>
        <input type="text" name="phone"/>
        <h5>Ф.И.О учителя</h5><br>
        <input type="text" name="phone"/>
        <form action="addSubject" method="post">
            <h5>Предметы</h5><br>
            <input type="radio" name="KZ" value="KZ">KZ
            <select>
                <option>Kazakh</option>
            </select><br>

            <input type="radio" name="RU" value="RU">RU
            <select>
                <option>Kazakh</option>
            </select><br>

            <input type="radio" name="EN" value="EN">EN
            <select>
                <option>English</option>
            </select><br>

            <input type="radio" name="newSubject" value="Добавить новый предмет">Добавить новый предмет <br>
            <input type="text" name="subject"/>
            <h5>Язык преподавания</h5>
            <input type="radio" name="KZ" value="KZ">KZ
            <input type="radio" name="RU" value="RU">RU
            <input type="radio" name="EN" value="EN">EN

            <input type="submit" value="Добавить предмет">
        </form>
        <input type="button" value="Отмена">
        <input type="submit" value="Добавить учителя">
        <input type="button" value="Далее" onclick="">
    </form>
</body>
</html>
