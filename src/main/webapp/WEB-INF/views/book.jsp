<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Library</title>


    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" style="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/book.css" />" style="">



    <!-- include the core styles of Alertify-->
    <link rel="stylesheet" href="  <c:url value="/resources/js/jquery_plugins/alertify/themes/alertify.core.css" />"/>
    <!-- Alertify. Include a theme, can be included into the core instead of 2 separate files -->
    <link rel="stylesheet" href="  <c:url value="/resources/js/jquery_plugins/alertify/themes/alertify.default.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style2.css" />" style="">

    <!-- bxSlider CSS file -->
    <link href="<c:url value="/resources/js/jquery_plugins/bxslider/jquery.bxslider.css" />" rel="stylesheet"/>



    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.4.4.min.js" />"></script>

    <!-- Alertify js-->
    <script src="<c:url value="/resources/js/jquery_plugins/alertify/lib/alertify.min.js"/>"></script>


    <!-- bxSlider Javascript file -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery_plugins/bxslider/jquery.bxslider.min.js" />"></script>


    <script type="text/javascript" src="<c:url value="/resources/js/main.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/AJAX.js" />"></script>
    <script language="javascript" type="text/javascript">
        function resizeIframe(obj) {
            obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
        }
    </script>
</head>
<body>

<div id="content">
    <div class="wrap">
        <header>
            <div>
                <a class="logo_img" href="ind">
                    <img src="<c:url value="/resources/img/logo.png" />" alt="Main">
                </a>
                <a class="logo_title" href="ind">
                   Library
                </a>
            </div>

            <c:if test="${map.userData.get(0) == '-123.2' && map.userData.get(1) == '-222.65'}">

                <div class="user_actions">
                    <a href="reg">
                        Registration
                    </a>
                    <a href="login">
                        Log In
                    </a>
                </div>
            </c:if>
            <c:if test="${map.userData.get(0) != '-123.2' && map.userData.get(1) != '-222.65'}">
                <div class="user_actions">
                    <c:if test="${map.userData.get(2) == 'false'}">
                    <a href="user?userId=${map.userData.get(0)}">
                            ${map.userData.get(1)}
                    </a>
                    </c:if>
                    <c:if test="${map.userData.get(2) == 'true'}">
                        <a href="librarian?userId=${map.userData.get(0)}">
                                ${map.userData.get(1)}
                        </a>
                    </c:if>
                    <a href="logOut">
                        Log out
                    </a>
                <c:if test="${map.userData.get(2) != 'true'}">
                    <a href="basket?userId=${map.userData.get(0)}">
                        <img src="<c:url value="/resources/basket1.jpg" />" width="20" height="20" alt="basket">
                    </a>
                    </c:if>
                </div>
            </c:if>
            <div class="clear"></div>
        </header>

        <div id="menu">
            <ul class="ca-menu">
                <li>
                    <a href="bookList?jspPage=ind">
                        <span class="ca-icon">F</span>
                        <div class="ca-content">
                            <p class="ca-main">Books</p>

                        </div>
                    </a>
                </li>
                <li>
                    <a href="seriesList?jspPage=ind">
                        <span class="ca-icon">H</span>
                        <div class="ca-content">
                            <p class="ca-main">Series</p>

                        </div>
                    </a>
                </li>
                <li class="search">
                    <form action="/search" method="post">
                        <input type="text" id="search_input" name="search_input" class="input_text" value=""  required="required"/>

                        <input class="ca-icon2" id="search_submit" type="submit" value="L" style="background: transparent; border: 0" />
                    </form>
                </li>
            </ul>
        </div>
        <div class="spacer"></div>
        <div class="main_content">
            <c:if test="${map.userData.get(0) == '-123.2' && map.userData.get(1) == '-222.65'}">
                <center>
                    To ORDER, Please
                    <a href="login?bookId=${map.bookList.get(0).getId()}">
                        Log in
                    </a>
                </center>
            </c:if>
            <p>
            Book    : ${map.bookList.get(0).getName()}<br>

            Author  : ${map.bookList.get(0).getAuthor()}<br>

            Genre   : ${map.bookList.get(0).getGenre()}<br>

            Price   : $${map.bookList.get(0).getPrice()}
            </p>
            <div class="blockForImage">
            <img class="book" src="<c:url value="/resources/${map.bookList.get(0).getPicturePass()}" />" >


            </div>

            <iframe width=500 id="iframe" onload='javascript:resizeIframe(this);'  src=" <c:url value="/resources/${map.bookList.get(0).getAbout()}" />" scrolling="no" frameborder="no"></iframe>
            <div class="clear"></div>
            <hr>
            <div class="block_button">
            <c:if test="${map.userData.get(0) != '-123.2' && map.userData.get(1) != '-222.65'}">
                <c:if test="${map.userData.get(2) == 'false'}">

                    <input type="hidden" id="id_user" value="${map.userData.get(0)}">
                    <input type="button" value="Order" onclick="doAjaxOrder(${map.bookList.get(0).getId()})">
                </c:if>
                <c:if test="${map.userData.get(2) == 'true' && map.bookList.get(0).getIsInCollection() != 1}">
                    <a href="addToCollection?bookId=${map.bookList.get(0).getId()}" title="AddToCollection" class="but_comments">Add To Collection</a>
                </c:if>
            </c:if>
            </div>
        </div>
    </div>
    <div id="footer_spacer"></div>
</div>
<footer>
    <div class="wrap">
        <a class="big_logo" href="ind">
        </a>

        <div class="footer_navigation">
            <ul>
                <li>
                    <a href="ind">
                        Home
                    </a>
                </li>
                <li>
                    <a href="about">
                        About
                    </a>
                </li>
            </ul>
        </div>
        <div class="links">

        </div>
        <div class="clear"></div>
    </div>
</footer>
</body>
</html>
</html>
