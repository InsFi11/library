<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Library</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" style="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css" />" style="">
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

    <?php echo $jsList; ?>

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
          <!--  <form action="/loginRequstToDB" method="post">
                <table class="login">
                    <tr>
                        <td>
                            <label for="login">
                                <span>Login</span><sup>*</sup>
                            </label>
                        </td>
                        <td>
                            <input id="login" type="text" name="login" value="" required />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="password">
                                <span>Password</span><sup>*</sup>
                            </label>
                        </td>
                        <td>
                            <input id="password" type="password" name="password" value=""  required/>
                        </td>
                    </tr>
                </table>

                <div class="spacer"></div>

                <input class="submit_button" id="submit_login" type="submit" value="Log In"/>
                -->
                <div class="clear"></div>



                <c:if test="${not empty param.error}">
                    <font color="red"> <spring:message code="label.loginerror" />
                        : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
                </c:if>
                <form method="POST" action="<c:url value="/j_spring_security_check" />">
                    <table class="login">
                        <tr>
                            <td>
                                <label >
                                    <span>Login</span><sup>*</sup>
                                </label>
                            </td>
                            <td><input type="text" name="j_username" /></td>
                        </tr>
                        <tr>
                            <td>
                                <label >
                                    <span>Password</span><sup>*</sup>
                                </label>
                            </td>
                            <td><input type="password" name="j_password" /></td>
                        </tr>
                        <tr>
                            <td>
                                <label >
                                    <span>Remember me</span><sup>*</sup>
                                </label>
                            </td>
                            <td><input type="checkbox" name="_spring_security_remember_me" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="right"><input class="submit_button" type="submit" value="Login" />
                                <input class="submit_button" type="reset" value="Reset" /></td>
                        </tr>
                    </table>
                </form>


                <div class="spacer"></div>
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
