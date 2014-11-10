<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Library</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />" style="">

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/books.css" />" style="">


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


        </div>
        <div class="spacer"></div>
        <div class="main_content">

            <div id="posts-list">
                <c:if test="${map.userData.get(0) == '-123.2' && map.userData.get(1) == '-222.65'}">
                    <center>
                        To ORDER, Please
                        <a href="login">
                            Log in
                        </a>
                    </center>
                </c:if>
                <c:forEach var="series" items="${map.list}">
                    <article class="format-standard">
                        <div class="blockInfoBookPrev">
                            <div class="block_image_left">
                                <h1>Series</h1>
                                <a href="addToCollectionStep2?id_series=${series.getId()}&id_book=${map.bookIdList.get(0)}" title="book">
                                    <img class="book" src="<c:url value="/resources/${series.getPicturePass()}" />"  >
                                </a>
                            </div>
                            <div class="book_info_prev">
                                <a href="book" title="read book">
                                    <div class="book_info_prev_title">
                                        <a href="addToCollectionStep2?id_series=${series.getId()}&id_book=${map.bookIdList.get(0)}" title="book">
                                            ${series.getName()}
                                            </a>
                                    </div>
                                </a>
                                <div class="block_book_prev_category">
                                    <div class="book_info_prev_category">Author: </div>
                                    ${series.getAuthor()}
                                </div>
                                <div class="block_book_prev_category">
                                    <div class="book_info_prev_category">genre:</div>

                                    <c:forEach var="item" items="${series.getGenreList()}">
                                        <a href="search?search_input=${item}">

                                        <span itemprop="genre">${item}

                                        </span>

                                        </a>
                                        <c:if test="${series.getGenreList().indexOf(item) != series.getGenreList().size() - 1}"> ,
                                        </c:if>
                                    </c:forEach>


                                </div>
                            </div>
                            <div class="clear"></div>
                        </div>

                        <div class="blockInfoBookPrevDesc">

                            <div class="InfoBookPrevDesc_Text">
                                <br>

                                <iframe width=400 height=155 src=" <c:url value="/resources/${series.getAbout()}" />" scrolling="no" frameborder="no"></iframe>
                                <br>

                            </div>

                        </div>
                        <div class="clear"></div>
                    </article>


                </c:forEach>

            </div>

            <div class="clear"></div>
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
                    <a href="ind.jsp">
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
