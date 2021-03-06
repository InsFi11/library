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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/basket.css" />" style="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/user.css" />" style="">
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
                    <a href="basket?userId=${map.userData.get(0)}">
                        <img src="<c:url value="/resources/basket1.jpg" />" width="20" height="20" alt="basket">
                    </a>
                </div>
            </sec:authorize>
            <div class="clear"></div>
        </header>

        <div id="menu">
            <ul class="ca-menu">
                <li>
                    <a href="bookList?jspPage=user">
                        <span class="ca-icon">F</span>
                        <div class="ca-content">
                            <p class="ca-main">Books</p>

                        </div>
                    </a>
                </li>
                <li>
                    <a href="seriesList?jspPage=user">
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
            <h2>User Page</h2>
              <div>

            <div class="block_information">
               <font size="5"> Personal Information:</font>
                <br>
               <table>
                <tr> <td>First Name </td>  <td>:  <font color="black" size="5" face="Buxton Sketch"> ${map.userList.get(0).firstName}</font></td></tr>
                <tr> <td>Last Name  </td>  <td>:  <font color="black" size="5" face="Buxton Sketch"> ${map.userList.get(0).lastName}</font></td></tr>
                <tr> <td>Gender     </td>  <td>:  <font color="black" size="5" face="Buxton Sketch"> ${map.userList.get(0).gender}</font></td></tr>
                <tr> <td> BirthDate </td>  <td>:  <font color="black" size="5" face="Buxton Sketch"> ${map.userList.get(0).dateOfBirth}</font></td></tr>
               </table>
                <c:if test="${map.passTicketDateList.get(2) == '1'}">
                <br>
                <font size="5"> PassTicket:</font>

                <table>
                    <tr>
                        <td> DateTime Start</td><td>:${map.passTicketDateList.get(0)}</td>
                    </tr>
                    <tr>
                        <td> DateTime End</td><td>:${map.passTicketDateList.get(1)}</td>
                    </tr>
                </table>
                </c:if>
            </div>
            <div class="block_photoAndButtons">
                <img class="user" src="<c:url value="/resources/user.jpg" />" alt="userPhoto">
                <div>
                <a href="changePass?id_user=${map.userList.get(0).userId}">Change password</a>

                </div>
                <div>
                <a href="editPersonalInf?id_user=${map.userList.get(0).userId}">Change personal information</a>
                </div>
                <div>


                    <input type="checkbox" onclick="this.nextSibling.style.display=this.checked?'inline':'none';"><input class="input_text" type="text" id="passTicketMonth" name="passTicketMonth"  value=""  style="display: none;" required="required">
                    <input type="hidden" id="id_user" value="${map.userList.get(0).userId}">

                    <a onclick="doAjaxPassTicket()">Buy passticket</a>
                    </div>
                <div>
                    <a href="deleteUser?userId=${map.userList.get(0).userId}">Delete Account</a>
                </div>




            </div>
            </div>
            <div class="clear"></div>
            <div class="block_button">
                <a href="basket?userId=${map.userList.get(0).userId}">Go to Basket</a>
            </div>


        </div>
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
