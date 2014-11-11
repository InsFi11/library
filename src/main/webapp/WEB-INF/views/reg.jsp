<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Library</title>
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" style="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reg.css" />" style="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style2.css" />" style="">

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
    <script type="text/javascript" src="<c:url value="/resources/js/AJAX.js" />"></script>

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

            <sec:authorize access="isAnonymous()">

                <div class="user_actions">
                    <a href="reg">
                        Registration
                    </a>
                    <a href="login">
                        Log In
                    </a>
                </div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <div class="user_actions">

                    <a href="user?userId=${map.userData.get(0)}">
                        <sec:authentication property="principal.username" />
                    </a>
                    <a href="logOut">
                        Log out
                    </a>
                </div>
            </sec:authorize>
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
            <sec:authorize access="isAnonymous()">

            <h2>Registration</h2>
            <form:form action="/insert" method="post" id="registration" modelAttribute="user" commandName="user">
                <table>
                    <tr>
                        <td>

                            <label for="first_name">
                                <span>First name*</span>
                            </label>

                        </td>
                        <td>
                            <form:input id="first_name" type="text" name="first_name" value="" path="firstName" required="required"/>

                        </td>


                    </tr>
                    <tr>
                        <td>

                            <label for="last_name">
                                <span>Last name*</span>
                            </label>

                        </td>
                        <td>
                            <form:input id="last_name" type="text" name="last_name" value="" path="lastName" required="required" />
                        </td>

                    </tr>
                    <tr>
                        <td>

                                <span>Sex*</span>

                        </td>
                        <td>
                            <table class="sex">
                                <tr>
                                    <form:radiobuttons path="gender"
                                                           items="${map.genderList}" required="required"/>
                                </tr>

                            </table>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <label for="email">
                                <span>Email*</span><sup>*</sup>
                            </label>
                        </td>
                        <td>

                                    <form:input id="email"  type="email" name="email" value="" path = "email" required="required" />
                        </td>

                    </tr>

                    <tr>
                        <td>
                            <label for="login">
                                <span>Login*</span><sup>*</sup>
                            </label>
                        </td>
                        <td>
                            <form:input id="login" type="text" name="login" value="" path = "login" required="required" />
                        </td>
                        <td>
                            <div class="block_button">
                                <a onclick="validate()">Check</a>
                            </div>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <label for="password">
                                <span>Password*</span><sup>*</sup>
                            </label>
                        </td>
                        <td>
                            <form:input id="password" type="password" name="password" value=""  path="password" required="required"/>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <label for="repassword">
                                <span>Repeat password*</span><sup>*</sup>
                            </label>
                        </td>
                        <td>
                            <input id="repassword" type="password" name="repassword" value="" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="birthday">
                                <span>Birthday*</span>
                            </label>
                        </td>
                        <td>
                            <form:input id="birthday" type="date" name="birthday" value="" path="dateOfBirth" required="required"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="secretQuest">
                                <span>Whats your favourite color?*</span>
                            </label>
                        </td>
                        <td>
                            <form:input id="secretQuest" type="text" name="secretQuest" value="" path="secretQuestion" required="required" />
                        </td>

                    </tr>
                </table>

                <div class="spacer"></div>

                <input class="submit_button" id="submit_reg" type="submit" value="Registrate"  onclick="validateRegForm(this);return false;"/>
                <div class="clear"></div>

                <div class="spacer"></div>

            </form:form>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
            <center>
            Please <a href="logOut">
                Log out
            </a>
            first!!
            </center>
            </sec:authorize>
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