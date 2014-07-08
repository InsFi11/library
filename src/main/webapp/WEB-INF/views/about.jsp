<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Library</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" style="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style2.css" />" style="">

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/about.css" />" style="">

    <!-- include the core styles of Alertify-->
    <link rel="stylesheet" href="  <c:url value="/resources/js/jquery_plugins/alertify/themes/alertify.core.css" />"/>
    <!-- Alertify. Include a theme, can be included into the core instead of 2 separate files -->
    <link rel="stylesheet" href="  <c:url value="/resources/js/jquery_plugins/alertify/themes/alertify.default.css" />"/>


    <!-- bxSlider CSS file -->
    <link href="<c:url value="/resources/js/jquery_plugins/bxslider/jquery.bxslider.css" />" rel="stylesheet"/>



    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.4.4.min.js" />"></script>

    <!-- Alertify js-->
    <script src="<c:url value="/resources/js/jquery_plugins/alertify/lib/alertify.min.js"/>"></script>


    <!-- bxSlider Javascript file -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery_plugins/bxslider/jquery.bxslider.min.js" />"></script>


    <script type="text/javascript" src="<c:url value="/resources/js/main.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/main.js" />"></script>
    <?jsp echo $jsList; ?>

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
            <c:if test="${userData.get(0) == '-123.2' && userData.get(1) == '-222.65'}">

                <div class="user_actions">
                    <a href="reg">
                        Registration
                    </a>
                    <a href="login">
                        Log In
                    </a>
                </div>
            </c:if>

            <c:if test="${userData.get(0) != '-123.2' && userData.get(1) != '-222.65'}">
                <div class="user_actions">

                    <c:if test="${userData.get(2) == 'false'}">
                        <a href="user?userId=${map.userData.get(0)}">
                                ${userData.get(1)}
                        </a>
                    </c:if>
                    <c:if test="${userData.get(2) == 'true'}">
                        <a href="librarian?userId=${userData.get(0)}">
                                ${userData.get(1)}
                        </a>
                    </c:if>
                    <a href="logOut">
                        Log out
                    </a>
                    <c:if test="${userData.get(2) != 'true'}">
                        <a href="basket?userId=${userData.get(0)}">
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


            <p>
                A library is an organized collection of sources of information and similar resources, made accessible to a defined community for reference or borrowing. It provides physical or digital access to material, and may be a physical building or room, or a virtual space, or both.
                <br>
                <br>
                <br>
                <br>
                <br>
            </p>
            <div class="text_center">Your Library</p>





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
