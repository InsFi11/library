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
    <script type="text/javascript" src="<c:url value="/resources/js/AJAX.js" />"></script>
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

                    <sec:authorize access="hasRole('ROLE_USER')">
                        <a href="user?userId=${map.userData.get(0)}">
                            <sec:authentication property="principal.username" />
                        </a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="librarian?userId=${map.userData.get(0)}">
                            <sec:authentication property="principal.username" />
                        </a>
                    </sec:authorize>
                    <a href="logOut">
                        Log out
                    </a>
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <a href="basket?userId=${map.userData.get(0)}">
                            <img src="<c:url value="/resources/basket1.jpg" />" width="20" height="20" alt="basket">
                        </a>
                   </sec:authorize>
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
                   <form method="post" action="bookFromReadingRoom">
                <table>
                    <tr>
                    <td>User Login/Email</td><td><input type="text" name="login" value="${map.readingRoomObj.getUserLogin()}"></td>
                    </tr>
                    <tr>
                    <td>First Name</td><td><input type="text" name="firstName" value="${map.readingRoomObj.getFirstName()}"></td>
                    </tr>
                    <tr>
                    <td>Last Name</td><td><input type="text" name="lastName" value="${map.readingRoomObj.getLastName()}"></td>
                    </tr>

                    <tr>
                    <td>Hours</td><td><input type="text" name="hours" value="${map.hours}"></td>
                    </tr>
                    <tr>
                        <td>Book</td><td><div class="block_button"> <input type="submit" value="Add Book"></div></td>
                    </tr>
                </table>
                   </form>
            <h2>Visitors</h2>
            <table>
                <tr>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>User Login</td>
                    <td>Book Id</td>
                    <td>DateTimeStart</td>
                    <td>DateTimeEnd</td>
                    <td>Delete</td>
                </tr>
                <c:forEach var="readingRoom" items="${map.readingRoomList}">
                    <tr>

                        <td>${readingRoom.getFirstName()}</td>
                        <td>${readingRoom.getLastName()}</td>
                        <td>${readingRoom.getUserLogin()}</td>
                        <td><a href="book?id_book=${readingRoom.getBookId()}">${readingRoom.getBookId()}</a></td>
                        <td>${readingRoom.getDatetimeStart()}</td>
                        <td>${readingRoom.getDatetimeEnd()}</td>

                        <td> <a onclick="doAjaxReadingRoom(${readingRoom.getReadingRoomId()})">Delete</a></td>

                    </tr>

                </c:forEach>
            </table>
            <div class="clear"></div>
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
