<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Library</title>


    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" style="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/book.css" />" style="">

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/user.css" />" style="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reg.css" />" style="">


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
    <script type="text/javascript" src="<c:url value="/resources/js/reg.js" />"></script>

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

            <div class="clear"></div>
        </header>

        <div id="menu">

        </div>
        <div class="spacer"></div>
        <div class="main_content">
            <form action="/changePasswordSuccess" method="post" id="registration" >
                <table>

                    <tr>
                        <td>
                            <label for="password">
                                <span>Old Password</span>
                            </label>
                        </td>
                        <td>
                            <input id="password" type="password" name="password" value=""  required="required"/>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <label for="newpassword">
                                <span>New Password</span>
                            </label>
                        </td>
                        <td>
                            <input id="newpassword" type="password" name="newpassword" value="" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="repassword">
                                <span>Repeat Password</span>
                            </label>
                        </td>
                        <td>
                            <input id="repassword" type="password" name="repassword" value="" required/>
                        </td>
                    </tr>


                </table>

                <div class="spacer"></div>

                <input class="submit_button" id="submit_reg" type="submit" value="Save Changes" onclick="validateChangePass(this);return false;" />
                <div class="clear"></div>

                <div class="spacer"></div>
                <input type="hidden" name="userId" id="userId" value="${userId}" />
            </form>
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
