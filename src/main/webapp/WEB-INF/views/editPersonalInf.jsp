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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style2.css" />" style="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/user.css" />" style="">


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
            <ul>
                <li><a href="ind">Home</a></li>
                <li><a href="about">About</a></li>

            </ul>
        </div>
        <div class="spacer"></div>
        <div class="main_content">
            <form:form action="/updateUserInf" method="post" id="registration" modelAttribute="user" commandName="user">
                <table>
                    <tr>
                        <td>

                            <label for="first_name">
                                <span>First name</span>
                            </label>

                        </td>
                        <td>
                            <form:input id="first_name" type="text" name="first_name" value="${map.userList.get(0).getFirstName()}" path="firstName" />

                        </td>


                    </tr>
                    <tr>
                    <td>

                        <label for="last_name">
                            <span>Last name</span>
                        </label>

                    </td>
                    <td>
                        <form:input id="last_name" type="text" name="last_name" value="${map.userList.get(0).getLastName()}" path="lastName" />
                    </td>

                </tr>

                    <tr>
                        <td>

                            <span>Sex</span>

                        </td>
                        <td>
                            <table class="sex">
                                <tr>
                                    <td><spring:bind path="gender">
                                        <c:forEach items='${map.genderList}' var='genderName'>
                                            <c:choose>
                                                <c:when test="${genderName eq map.userList.get(0).getGender()}">
                                                    <input type="radio" name="gender" value="${genderName}"
                                                           checked="checked">${genderName}
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="radio" name="gender" value="${genderName}">${genderName}
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </spring:bind>
                                    </td>
                                </tr>

                            </table>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <label for="email">

                            </label>
                        </td>
                        <td>

                            <form:input id="email"  type="hidden" name="email" value="${map.userList.get(0).getEmail()}" path = "email" required="required" />
                        </td>

                    </tr>

                    <tr>
                        <td>
                            <label for="login">

                            </label>
                        </td>
                        <td>
                            <form:input id="login" type="hidden" name="login" value="${map.userList.get(0).getLogin()}" path = "login" required="required" />
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <label for="password">

                            </label>
                        </td>
                        <td>
                            <form:input id="password" type="hidden" name="password" value="${map.userList.get(0).getPassword()}"  path="password" required="required"/>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <label for="repassword">

                            </label>
                        </td>
                        <td>
                            <input id="repassword" type="hidden" name="repassword" value="" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="birthday">
                                <span>Birthday</span>
                            </label>
                        </td>
                        <td>
                            <form:input id="birthday" type="date" name="birthday" value="${map.userList.get(0).getDateOfBirth()}" path="dateOfBirth"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="secretQuest">

                            </label>
                        </td>
                        <td>
                            <form:input id="secretQuest" type="hidden" name="secretQuest" value="${map.userList.get(0).getSecretQuestion()}" path="secretQuestion" required="required"/>
                        </td>

                    </tr>
                </table>

                <div class="spacer"></div>

                <input class="submit_button" id="submit_reg" type="submit" value="Save Changes"/>
               <br>
                <div class="block_button">
                    <a href="librarian?userId=${map.userData.get(0).getUserId()}" style="float: left">Back</a>

                </div>
                <div class="clear"></div>

                <div class="spacer"></div>
                <form:hidden path="userId" value="${map.userList.get(0).getUserId()}" />
                <form:hidden path="isLibrarian" value="${map.userList.get(0).getIsLibrarian()}" />
            </form:form>
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
