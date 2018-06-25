<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:set value="${contextPath}/resources" var="resPath"/>
<!DOCTYPE >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>IT SITE</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <link href="${resPath}/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<c:url value="/categories/getCategories" var="getCategories"/>
<c:url value="/categories/deleteCategory" var="deleteCategory"/>
<c:url value="/categories/changeCategory" var="changeCategory"/>
<c:url value="/categories/add" var="addCategory"/>
<c:url value="/authors/getAuthors" var="getAuthors"/>
<c:url value="/authors/deleteAuthor" var="deleteAuthor"/>
<c:url value="/authors/changeAuthor" var="changeAuthor"/>
<c:url value="/authors/add" var="addAuthor"/>
<div id="templatemo_header_wrapper">
    <div id="templatemo_header">

        <div id="site_title">

        </div>

        <div id="templatemo_rss">
            <a href="" target="_parent">SUBSCRIBE<br/><span>OUR FEED</span></a>
        </div>

    </div> <!-- end of header -->

    <%--<div id="templatemo_menu">--%>

    <%--<ul>--%>
    <%--<li><a href="${contextPath}/edit">Главная</a></li>--%>
    <%--<li><a href="${contextPath}/editcategory">Категории</a></li>--%>
    <%--</ul>--%>

    <%--</div> <!-- end of templatemo_menu -->--%>

</div> <!-- end of header wrapper -->

<div id="templatemo_main_wrapper">
    <div id="templatemo_content_wrapper_edit">
        <div id="templatemo_sidebar_one">
            <ul class="templatemo_list">
                <li><a href="${contextPath}/" class=class="category_reff">Home</a></li>
                <a href="${getAuthors}" class="list-group-item">get authors</a>
                <li><a href="${getCategories}" class=class="category_reff">get categories</a></li>
            </ul>

            <div class="cleaner_h40"></div>
        </div>

        <div id="templatemo_sidebar_three">
                <c:if test="${not empty resultObject}">
                <c:if test="${type == 'category'}">
                <a href="${addCategory}">Add new category</a>
                <table>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Action</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="object" items="${resultObject}">
                        <tr>
                            <td>${object.id}</td>
                            <td>${object.name}</td>
                            <td><form action="${changeCategory}" method="post">
                                <button type="submit" name="id" value="${object.id}">Change</button>
                            </form>
                            </td>
                            <td><form action="${deleteCategory}" method="post">
                                <button type="submit" name="id" value="${object.id}">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </c:if>
                <c:if test="${type == 'author'}">
                <a href="${addAuthor}">Add new author</a>
                <table>
                    <tr>
                        <th>Id</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Action</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="object" items="${resultObject}">
                        <tr>
                            <td>${object.id}</td>
                            <td>${object.firstname}</td>
                            <td>${object.lastname}</td>
                            <td><form action="${changeAuthor}" method="post">
                                <button type="submit" name="id" value="${object.id}">Change</button>
                            </form>
                            </td>
                            <td><form action="${deleteAuthor}" method="post">
                                <button type="submit" name="id" value="${object.id}">Delete</button>
                            </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </c:if>
                </c:if>

                <div class="cleaner_h40"></div>


        </div>

        <%--<div id="templatemo_sidebar_three">--%>

        <%--<h4>Статьи</h4>--%>
        <%--<ul class="templatemo_list">--%>
        <%--<c:if test="${not empty articles}">--%>
        <%--<c:forEach items="${articles}" var="article">--%>
        <%--<li><a class="category_reff" href="./articles/${article.id}">${article.title}</a></li>--%>
        <%--<li>${article.author}</li>--%>
        <%--<li>${article.category}</li>--%>
        <%--</c:forEach>--%>
        <%--</c:if>--%>
        <%--</ul>--%>

        <%--<div class="cleaner_h40"></div>--%>

        <%--</div>--%>

        <%--<div id="templatemo_sidebar_two">--%>

        <%--<div class="banner_250x200">--%>
        <%--<a href="" target="_parent"><img src="${resPath}/images/250x200_banner.jpg" alt="templates"/></a>--%>
        <%--</div>--%>

        <%--<div class="banner_125x125">--%>
        <%--<a href="" target="_parent"><img src="${resPath}/images/templatemo_ads.jpg" alt="web 1"/></a>--%>
        <%--<a href="" target="_parent"><img src="${resPath}/images/templatemo_ads.jpg" alt="web 2"/></a>--%>
        <%--<a href="" target="_parent"><img src="${resPath}/images/templatemo_ads.jpg" alt="templates 2"/></a>--%>
        <%--<a href="" target="_parent"><img src="${resPath}/images/templatemo_ads.jpg" alt="templates 1"/></a>--%>
        <%--</div>--%>

        <%--<div class="cleaner_h40"></div>--%>

        <%--</div>--%>

        <div class="cleaner"></div>
    </div>
</div>
<%--<button class="btn_load">Загрузить еще</button>--%>
<div id="templatemo_footer_wrapper">
    <div id="templatemo_footer">
        Copyright © 2016 <a href="#">IT SITE</a> |
    </div>
</div>

<%--<script>--%>
<%--var url = "./articles/articles_ajax";--%>
<%--var contextPath = "${contextPath}";--%>
<%--</script>--%>
<%--<script src="${resPath}/assets/getData.js"></script>--%>
</body>
</html>