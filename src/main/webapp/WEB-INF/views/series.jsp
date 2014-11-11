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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/series.css" />" style="">


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
            <sec:authorize access="isAnonymous()">
                <center>
                    To ORDER, Please
                    <a href="login?seriesId=${map.seriesList.get(0).getId()}">
                        Log in
                    </a>
                </center>
            </sec:authorize>
            <h2>
                Series             : ${map.seriesList.get(0).getName()}<br>
                Author             : ${map.seriesList.get(0).getAuthor()}<br>
                PreOreder price    : $${map.seriesList.get(0).getPrice()}

            </h2>
            <img class="series" src="<c:url value="/resources/${map.seriesList.get(0).getPicturePass()}" />" width="256" height="400" >
            <div class="block_button changePreorder">
                <sec:authorize access="isAnonymous()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <form action="changePriceCollection?id_series=${map.seriesList.get(0).getId()}" method="post">
                        <input type="checkbox" onclick="this.nextSibling.style.display=this.checked?'inline':'none';"><input type="text" name="price" class="input_text" value=""  required="required">

                        <input class="submit_button" type="submit" value="Change preOrder price of Collection" />
                    </form>



                </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_USER')">

                    <input type="hidden" id="id_user" value="${map.userData.get(0)}">
                    <input type="button" value="PreOrder" onclick="doAjaxPreOrder(${book.getId()})">
                    </sec:authorize>

            </sec:authorize>
                </div>
            <div>

                <iframe width=500 id="iframe" onload='javascript:resizeIframe(this);'  src=" <c:url value="/resources/${map.seriesList.get(0).getAbout()}" />" scrolling="no" frameborder="no"></iframe>

            </div>
            <div class="clear"></div>

            <hr/>
            <div id = "posts-list">
            <c:forEach var="book" items="${map.bookList}">

                <c:if test="${book.getClass().name == 'beingjavaguys.domain.Book' && book.getAmount() != 0}">

                    <article class="format-standard">
                        <div class="blockInfoBookPrev">
                            <div class="block_image_left">

                                <h1>Book</h1>
                                <a href="book?id_book=${book.getId()}" title="book">
                                    <img class="book" src="<c:url value="/resources/${book.getPicturePass()}" />"  >
                                </a>


                            </div>
                            <div class="book_info_prev">

                                <a href="book?id_book=${book.getId()}" title="book">
                                    <div class="book_info_prev_title">
                                            ${book.getName()}
                                    </div>
                                </a>
                                Price : $${book.getPrice()}

                            </div>

                            <div class="clear"></div>

                        </div>

                        <div class="blockInfoBookPrevDesc">


                            <div class="block_button">
                    <sec:authorize access="isAuthenticated()">
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <input type="hidden" id="id_series" value="${map.seriesList.get(0).getId()}">
                                    <input type="button" value="Delete Book from Collection" onclick="doAjaxDeleteBookFromCollection(${book.getId()})">
                                </sec:authorize>

                                <sec:authorize access="hasRole('ROLE_USER')">
                                    <input type="hidden" id="id_user" value="${map.userData.get(0)}">
                                    <input type="button" value="Order" onclick="doAjaxOrder(${book.getId()})">
                                </sec:authorize>

                </sec:authorize>
                            </div>
                        </div>
                        <div class="clear"></div>
                    </article>
                </c:if>

            </c:forEach>
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
