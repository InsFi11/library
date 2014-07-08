<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Library</title>


    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" style="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/book.css" />" style="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reg.css" />" style="">
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

                    <a href="user?userId=${map.userData.get(0)}">
                            ${map.userData.get(1)}
                    </a>
                    <a href="logOut">
                        Log out
                    </a>
                    <a href="basket?userId=${map.userData.get(0)}">
                        <img src="<c:url value="/resources/basket1.jpg" />" width="20" height="20" alt="basket">
                    </a>
                </div>
            </c:if>

            <div class="clear"></div>
        </header>

        <div id="menu">
            <ul class="ca-menu">
                <li>
                    <a href="bookList?jspPage=basket">
                        <span class="ca-icon">F</span>
                        <div class="ca-content">
                            <p class="ca-main">Books</p>

                        </div>
                    </a>
                </li>
                <li>
                    <a href="seriesList?jspPage=basket">
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
            <table>
                <tr>
                    <td>Item</td>
                    <td>Name</td>
                    <td>Author</td>
                    <td>Amount</td>
                    <td>Price(each)</td>
                    <td>Delete</td>

                </tr>
        <c:set var="expire" value="0"/>
        <c:forEach var="book" items="${map.booksOrderList}">
            <tr>
                <td>Book</td>
                <td>${book.getBookName()}</td>
                <td>${book.getBookAuthor()}</td>
                <td><input type="text" name="amount" value="${book.getAmount()}" required="required"></td>
                <td>$${book.getPrice()}</td>
                <td> <a onclick="DeleteOrder(${book.getBooksOrderId()},${map.userData.get(0)})">Delete</a></td>

            </tr>
            <c:set var="expire" value="${expire + book.getAmount() * book.getPrice()}"/>
        </c:forEach>
            <c:forEach var="series" items="${map.seriesPreorderList}">
                <tr>
                    <td>Series</td>
                    <td>${series.getSeriesName()}</td>
                    <td>${series.getSeriesAuthor()}</td>
                    <td><input type="text" name="month count" value="${series.getMonthAmount()}" required="required"></td>
                    <td>$${series.getPrice()}</td>
                    <td> <a onclick="DeletePreOrder(${series.getSeriesPreorderId()},${map.userData.get(0)})">Delete</a></td>
                </tr>
                <c:set var="expire" value="${expire + series.getMonthAmount() * series.getPrice()}"/>
                </c:forEach>
                <c:forEach var="ticket" items="${map.passTicketList}">
                    <td>PassTicket</td>
                    <td></td>
                    <td></td>
                    <fmt:formatNumber var="expiry"
                                      value="${(ticket.getDatetimeEnd().getTime() - ticket.getDatetimeStart().getTime())/(1000 * 60 * 60 * 24 * 30)}"
                                      maxFractionDigits="0" />
                    <td><input type="text" name="month count" value="${expiry}"></td>

                    <td>$5</td>
                    <td> <a onclick="DeletePassTicket(${ticket.getPassticketId()},${map.userData.get(0)})">Delete</a></td>
                    </c:forEach>
                <c:set var="expire" value="${expire + expiry * 5}"/>
            </table>
            <div class="total-sum">Total : $${expire}</div>
            <div class="block_button">
                <input type="button" value="Pay" onclick="">
            </div>
            <div class="clear"></div>
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
