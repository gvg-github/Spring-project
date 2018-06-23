<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:set value="${contextPath}/resources" var="resPath"/>

<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>IT SITE</title>

    <link href="${resPath}/style.css" rel="stylesheet" type="text/css"/>
    <script src="${resPath}/assets/ckeditor/ckeditor.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
</head>
<body>
<c:url value="/categories/add" var="add"/>
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
    <%--<li><a href="${contextPath}">Главная</a></li>--%>
    <%--<li><a href="${contextPath}/articles/add">Добавить категорию</a></li>--%>
    <%--</ul>--%>

    <%--</div> <!-- end of templatemo_menu -->--%>

</div> <!-- end of header wrapper -->

<div id="templatemo_main_wrapper">
    <div id="templatemo_add_content_wrapper">

        <div id="templatemo_content">
            <div class="post_section">
                <form action="${add}" method="POST">
                    <c:if test="${category != null}">
                        <c:set var="category" scope="page" value="${category}"/>
                        <%--<input placeholder="Id" type="number" name="id" value="${category.id}" disabled>--%>
                        <input placeholder="Id" type="hidden" name="id" value="${category.id}">
                        <input placeholder="Name" type="text" name="name" value="${category.name}">
                        <input type="submit" value="Save">
                    </c:if>
                    <c:if test="${category == null}">
                        <input placeholder="Id" type="hidden" name="id">
                        <input placeholder="Name" type="text" name="name">
                        <input type="submit" value="Save">
                    </c:if>
                </form>
            </div>

            <div class="cleaner_h40"><!-- a spacing between 2 posts --></div>

        </div>

        <div class="cleaner_h40"></div>

    </div>

    <div class="cleaner"></div>
</div> <!-- end of content wrapper -->

<div id="templatemo_footer_wrapper">
    <div id="templatemo_footer">

        Copyright © 2016 <a href="#">IT SITE</a> |


    </div> <!-- end of templatemo_copyright -->
</div> <!-- end of copyright wrapper -->


<script type="text/javascript">
    $(document).ready(function () {


        CKEDITOR.replace('content');
        CKEDITOR.config.width = "100%";
        CKEDITOR.config.height = 600;


    });
</script>

</body>