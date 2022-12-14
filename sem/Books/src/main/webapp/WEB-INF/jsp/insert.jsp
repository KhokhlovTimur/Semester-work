<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Page</title>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
</head>
<body>
<div class="container">
    <div class="block1">
        <form action="/admin">
            <button type="submit" class="reverse admin-button">Назад</button>
        </form>
        <form action="/profile">
            <button type="submit" class="reverse">В профиль</button>
        </form>
        <form action="/admin/books">
            <button type="submit" class="reverse admin-button">Информация о книгах</button>
        </form>
        <form method="post" class="insert-book-form form" action="" enctype="multipart/form-data">
            <h2>Добавить книгу</h2>
            <input type="text" name="title" placeholder="title" class="w100" required>
            <input type="text" name="authorId" placeholder="authorId" id="authorId" required>
            <input type="text" name="year" placeholder="year of publication" id="bookYear" required>
            <input type="text" name="price" placeholder="price" id="price" required>
            <input type="file" name="image" placeholder="image" class="file" required>
            <textarea name="descriptionBook" class="text-area"></textarea>
            <button type="submit" class="insertbutton" name="save" value="book" id="saveBook">Сохранить</button>
        </form>
        <div class="authors-div authors">
            <h2>Авторы</h2>
            <c:set var="authors" value="${applicationScope.authorsService.findAllAuthors()}"></c:set>
            <c:forEach var="author" items="${authors}">
                <div class="line">
                    <br>
                    <span>id:</span>
                    <em><c:out value="${author.id}"></c:out></em>
                    <br>
                    <span>name:</span>
                    <c:out value="${author.name}"></c:out>
                    <br>
                    <span>surname:</span>
                    <c:out value="${author.surname}"></c:out>
                </div>
            </c:forEach>
        </div>
        <button type="button" class="show-author update-button admin-button" onclick="showAuthor()">Добавить автора
        </button>
        <button type="button" class="hide-author update-button admin-button">Скрыть</button>
        <form method="post" class="insert-author-form form" action="">
            <h2>Добавить автора</h2>
            <input type="text" name="name" placeholder="name" class="w100" required>
            <input type="text" name="surname" placeholder="surname" required>
            <input type="text" name="year" placeholder="birth year" id="authorYear" required>
            <button type="submit" class="button-auth" name="save" value="author">Сохранить</button>
        </form>

        <button type="button" class="show-author-update author-upd">Изменить автора
        </button>
        <button type="button" class="hide-author-update author-upd">Скрыть</button>
        <form method="post" class="update-author-form form" action="">
            <h2>Изменить информацию об авторе</h2>
            <input type="text" name="updateId" placeholder="id" class="w100" id="upd-id" required>
            <input type="text" name="updateName" placeholder="name" class="w100" required>
            <input type="text" name="updateSurname" placeholder="surname" required>
            <input type="text" name="updateYear" placeholder="birth year" id="authorYearUpdate" required>
            <button type="submit" class="button-auth"  name="save" value="updateAuthor" id="updateAuthor">Сохранить</button>
        </form>

    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/insert.js"></script>

<style>
    <%@include file="/resources/css/insert.css" %>
</style>

</body>
</html>