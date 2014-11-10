package beingjavaguys.controller;

import java.lang.String;
import java.security.Timestamp;
import java.sql.Date;
import java.util.*;

import beingjavaguys.domain.Book;
import beingjavaguys.dao.SecurityUser;
import beingjavaguys.domain.*;
import beingjavaguys.domain.User;
import beingjavaguys.services.*;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
@SuppressWarnings("deprecation")

public class LibController {


    private List<Integer> idList;
    private  Map<String, List<IPagination>> privateMap;
    private int searchCheker = 0;
    private String userId = "-123.2";
    private String userLogin = "-222.65";
    List<String> userData;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    BookServiceImpl bookService;
    @Autowired
    SeriesServiceImpl seriesService;
    @Autowired
    SeriesContentServiceImpl seriesContentService;
    @Autowired
    BooksOrderServiceImpl booksOrderService;
    @Autowired
    SeriesPreorderServiceImpl seriesPreorderService;
    @Autowired
    PassTicketServiceImpl passTicketService;
    @Autowired
    ReadingRoomServiceImpl readingRoomService;
    @Autowired
    GenreServiceImpl genreService;

    @RequestMapping("/ind")
    public ModelAndView startData(@RequestParam(value = "pageNumber", defaultValue = "1" ) String pageNumber) {

        searchCheker = 0;

        List<List<SeriesContent>> listCon = new LinkedList<List<SeriesContent>>();
        List<Series> seriesList = seriesService.getSeriesList();
        for(Series ser : seriesList)
        {
            List<SeriesContent> seriesContentList = seriesContentService.getSeriesContentFromSeries(ser.getId()+"");
            if(!seriesContentList.isEmpty())
                listCon.add(seriesContentList);
        }

        for (List<SeriesContent> list : listCon)
        {
            Collections.sort(list, new Comparator<SeriesContent>() {
                public int compare(SeriesContent o1, SeriesContent o2) {

                    Long l1 = o1.getDatetimeOperation().getTime();
                    Long l2 = o2.getDatetimeOperation().getTime();

                    return l1.compareTo(l2);
                }
            });
        }
        for (List<SeriesContent> list : listCon)
        {
            Integer id = list.get(0).getSeriesId();
            java.sql.Date date = list.get(list.size() - 1).getDatetimeOperation();
            seriesService.updateDate(id.toString(),date);
        }

        List<IPagination> paginationList = new ArrayList<IPagination>();
        List<Book> bookList = bookService.getBookList();

        for(Book book : bookList)
        {
            // List<String> genreList = book.getGenreList();
            book.setIsInCollection(seriesContentService.getSeriesContentFromBook(((Integer)book.getId()).toString()).isEmpty()? 0 : 1);
            bookService.updateData(book);
        }

        List<IPagination> bookListTemp = new ArrayList<IPagination>(bookList);
        List<IPagination> seriesListTemp = new ArrayList<IPagination>(seriesList);
        Collections.sort(bookListTemp);
        Collections.sort(seriesListTemp);
        privateMap = new HashMap<String, List<IPagination>>();
        privateMap.put("bookList", bookListTemp);
        privateMap.put("seriesList", seriesListTemp);

        paginationList.addAll(seriesList);
        paginationList.addAll(bookList);

        Collections.sort(paginationList);

        userData = new ArrayList<String>();
        userData.add(userId);
        userData.add(userLogin);
        //userData.add(userIsLibrarian);


        Map<String,List> map = addPagination(paginationList, pageNumber);
        map.put("userData",userData);
        List<String> currentPage = new ArrayList<String>();
        currentPage.add(pageNumber);
        map.put("currentPage",currentPage);
        //Hibernate.initialize( map.get("list"));
        return new ModelAndView("ind", "map", map);


    }
    @Transactional
    public  Map<String,List> addPagination(List<IPagination> paginationList, String pageNumber)
    {
        int tempNumPage;
        if((paginationList.size())%10 != 0)
        {
            tempNumPage =  paginationList.size()/10 + 1;
        }
        else tempNumPage = paginationList.size()/10;

        List<List<IPagination>> listSeparatedPage = new ArrayList<List<IPagination>>();
        for(int i = 0; i < tempNumPage; i++)
            listSeparatedPage.add(new ArrayList<IPagination>());

        int i = 0;
        for(IPagination item : paginationList)
        {
            i++;
            if (i == 1)
                listSeparatedPage.get(0).add(item);
            else
                listSeparatedPage.get(i/11).add(item);

        }

        List<Integer> listPageNum = new ArrayList<Integer>();
        for(int j = 0; j < tempNumPage; j++)
        {
            listPageNum.add(j + 1);
        }

        Map<String,List> map = new HashMap<String, List>();
        //for(IPagination item :listSeparatedPage.get(Integer.parseInt(pageNumber) - 1))
        List<IPagination> tempList = listSeparatedPage.get(Integer.parseInt(pageNumber) - 1);

        for(int counter = 0; counter < tempList.size();counter++)

            if (tempList.get(counter).getClass() == Book.class) {
                Book b = (Book) tempList.get(counter);
                tempList.set(counter, bookService.findBookFetchGenre(b.getId()));
                //Hibernate.initialize(b.getGenreList());


            }
            else{
                Series s = (Series) tempList.get(counter);
                tempList.set(counter, seriesService.findSeriesFetchGenre(s.getId()));
            }



        map.put("list", listSeparatedPage.get(Integer.parseInt(pageNumber) - 1));
        map.put("listPageNum",listPageNum);

        return map;
    }

    @RequestMapping("/logOut")
    public String logOut()
    {
        userId = "-123.2";
        userLogin = "-222.65";
        return "redirect:/ind";
    }
    @RequestMapping("/bookList")
    public ModelAndView re(@RequestParam(value = "jspPage", defaultValue ="bookList" ) String jspPage,@RequestParam(value = "pageNumber", defaultValue = "1" ) String pageNumber) {

        List<IPagination> seriesList = new ArrayList<IPagination>();
        seriesList.addAll(privateMap.get("seriesList"));
        List<IPagination> bookList = new ArrayList<IPagination>();
        bookList.addAll(privateMap.get("bookList"));

        if(jspPage.equals("ind") || searchCheker == 0) {


            bookList.clear();
            bookList.addAll(bookService.getBookList());
            for(IPagination book : bookList)
            {
                book.setIsInCollection(seriesContentService.getSeriesContentFromBook(((Integer)book.getId()).toString()).isEmpty()? 0 : 1);
                bookService.updateData((Book)book);
            }

            Collections.sort(bookList);
            privateMap = new HashMap<String, List<IPagination>>();
            privateMap.put("bookList", bookList);
            privateMap.put("seriesList", seriesList);
        }
        Map<String,List> map = new HashMap<String, List>();
        if(!bookList.isEmpty()){
            map = addPagination(bookList, pageNumber);
        }
        else{
            map.put("list", bookList);
        }
        map.put("userData",userData);
        List<String> currentPage = new ArrayList<String>();
        currentPage.add(pageNumber);
        map.put("currentPage",currentPage);
        return  new  ModelAndView ("bookList", "map", map);


    }



    @RequestMapping("/seriesList")
    public ModelAndView res(@RequestParam(value = "jspPage", defaultValue ="seriesList") String jspPage,@RequestParam(value = "pageNumber", defaultValue = "1" ) String pageNumber) {

        List<IPagination> seriesList = new ArrayList<IPagination>();
        seriesList.addAll(privateMap.get("seriesList"));
        List<IPagination> bookList = new ArrayList<IPagination>();
        bookList.addAll(privateMap.get("bookList"));
        if(jspPage.equals("ind") || searchCheker == 0) {


            seriesList.clear();
            seriesList.addAll(seriesService.getSeriesList());
            Collections.sort(seriesList);
            privateMap = new HashMap<String, List<IPagination>>();
            privateMap.put("bookList", bookList);
            privateMap.put("seriesList", seriesList);
        }

        Map<String,List> map = new HashMap<String, List>();
        if(!seriesList.isEmpty()){
            map = addPagination(seriesList, pageNumber);
        }
        else{
            map.put("list", seriesList);
        }

        map.put("userData",userData);
        List<String> currentPage = new ArrayList<String>();
        currentPage.add(pageNumber);
        map.put("currentPage",currentPage);
        return new ModelAndView("seriesList", "map", map);
    }


    @Transactional
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam(value="search_input", defaultValue = "nothing") String string,@RequestParam(value = "pageNumber", defaultValue = "1" ) String pageNumber) {


        List<IPagination> paginationList = new ArrayList<IPagination>();

        if(!string.equals("nothing")) {

            paginationList = new ArrayList<IPagination>();
            Set<Book> bookListFromAuthor = new HashSet<Book>(bookService.getBookListFromAuthor(string));
            Set<Book> bookListFromName = new HashSet<Book>(bookService.getBookListFromName(string));
            Set<Book> bookListFromGenre = new HashSet<Book>();
            if(genreService.findGenreFromNameFetchBook(string) != null)
                bookListFromGenre = new HashSet<Book>(genreService.findGenreFromNameFetchBook(string).getBookList());


            Set<Series> seriesListFromName = new HashSet<Series>(seriesService.getSeriesListFromName(string));
            Set<Series> seriesListFromGenre = new HashSet<Series>();
            Genre s = new Genre();
            Book b = new Book();
            System.out.println(b +""+ s);
            if(genreService.findGenreFromNameFetchSeries(string) != null){
               s = genreService.findGenreFromNameFetchSeries(string);
                seriesListFromGenre = new HashSet<Series>(genreService.findGenreFromNameFetchSeries(string).getSeriesList());
            }

            Set<Series> seriesListFromAuthor = new HashSet<Series>(seriesService.getSeriesListFromAuthor(string));


            bookListFromAuthor.removeAll(bookListFromName);
            bookListFromName.removeAll(bookListFromGenre);
            bookListFromGenre.addAll(bookListFromAuthor);
            bookListFromGenre.addAll(bookListFromName);

            seriesListFromAuthor.removeAll(seriesListFromName);
            seriesListFromName.removeAll(seriesListFromGenre);
            seriesListFromGenre.addAll(seriesListFromAuthor);
            seriesListFromGenre.addAll(seriesListFromName);

            for(Book book : bookListFromGenre)
            {
                book.setIsInCollection(seriesContentService.getSeriesContentFromBook(((Integer)book.getId()).toString()).isEmpty()? 0 : 1);
                bookService.updateData(book);
            }


            paginationList.addAll(bookListFromGenre);
            paginationList.addAll(seriesListFromGenre);

            Collections.sort(paginationList);

            List<IPagination> seriesSortedList = new ArrayList<IPagination>();
            seriesSortedList.addAll(seriesListFromGenre);
            Collections.sort(seriesSortedList);

            List<IPagination> booksSortedList = new ArrayList<IPagination>();
            booksSortedList.addAll(bookListFromGenre);
            Collections.sort(booksSortedList);



            privateMap.put("bookList", booksSortedList);
            privateMap.put("seriesList", seriesSortedList);

           /* if(!booksSortedList.isEmpty())
                privateMap.put("bookList", booksSortedList);
            if(!seriesSortedList.isEmpty())
                privateMap.put("seriesList", seriesSortedList);
*/
        }
        else {
            paginationList.clear();

            paginationList.addAll(privateMap.get("bookList"));
            paginationList.addAll(privateMap.get("seriesList"));


            Collections.sort(paginationList);
        }

        searchCheker = 1;

        Map<String,List> map = addPagination(paginationList, pageNumber);
        map.put("userData",userData);
        List<String> currentPage = new ArrayList<String>();
        currentPage.add(pageNumber);
        map.put("currentPage",currentPage);
        return new ModelAndView("search", "map", map);
    }
    @RequestMapping("/searchBook")
    public ModelAndView searchBook(@RequestParam("search_input") String string, @RequestParam(value = "pageNumber", defaultValue = "1" ) String pageNumber) {

        List<IPagination> bookList = privateMap.get("bookList");
        List<IPagination> bookListTemp = new ArrayList<IPagination>();

        for(IPagination book : bookList)
        {
            book.setIsInCollection(seriesContentService.getSeriesContentFromBook(((Integer)book.getId()).toString()).isEmpty()? 0 : 1);
            bookService.updateData((Book)book);

            if(book.getName().contains(string))
                bookListTemp.add(book);
            else if(book.getGenreList().contains(string))
                bookListTemp.add(book);
            else if(book.getAuthor().contains(string))
                bookListTemp.add(book);
        }

        privateMap.put("bookList", bookListTemp);
        Map<String, List> map = addPagination(bookListTemp, pageNumber);
        searchCheker = 1;
        map.put("userData",userData);
        return new ModelAndView("bookList", "map", map);
    }

    @RequestMapping("/searchSeries")
    public ModelAndView searchSeries(@RequestParam("search_input") String string, @RequestParam(value = "pageNumber", defaultValue = "1" ) String pageNumber) {

        List<IPagination> seriesList = privateMap.get("seriesList");
        List<IPagination> seriesListTemp = new ArrayList<IPagination>();

        for(IPagination series : seriesList)
        {
            if(series.getName().contains(string))
                seriesListTemp.add(series);
            else if(series.getGenreList().contains(string))
                seriesListTemp.add(series);
            else if(series.getAuthor().contains(string))
                seriesListTemp.add(series);
        }
        privateMap.put("seriesList", seriesListTemp);
        searchCheker = 1;
        Map<String,List> map = addPagination(seriesListTemp, pageNumber);
        map.put("userData",userData);
        return new ModelAndView("seriesList", "map", map);
    }




    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView registerUser(@ModelAttribute User user) {

        List<String> genderList = new ArrayList<String>();
        genderList.add("male");
        genderList.add("female");

        idList = new ArrayList<Integer>();
        idList.add(0);




        Map<String, List> map = new HashMap<String, List>();
        map.put("genderList", genderList);
        map.put("idList",idList);
        map.put("userData",userData);
        return new ModelAndView("reg", "map", map);
    }



    @RequestMapping("/login")
    public ModelAndView getLoginPage(@RequestParam(value = "bookId", defaultValue = "-5") String bookId, @RequestParam(value = "seriesId", defaultValue = "-6") String seriesId)
    {
        if(bookId.equals("-5") && seriesId.equals("-6"))
            return new ModelAndView("login");
        else if(!bookId.equals("-5") && seriesId.equals("-6"))
            return new ModelAndView("loginToBook","bookId",bookId);
        else
            return new ModelAndView("loginToSeries","seriesId",seriesId);
    }



    @RequestMapping("/user")
    public ModelAndView goToUser(@RequestParam String userId)
    {
        User user = userService.getUser(userId);
        Map<String, List> map = new HashMap<String, List>();
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        PassTicket passTicket = passTicketService.getPassTicketFromUser(userId);


        map.put("userData",userData);
        map.put("userList",userList);
        if(passTicket != null) {
            List<String> passTicketDateList = new ArrayList<String>();
            passTicketDateList.add(passTicket.getDatetimeStart().toString());
            passTicketDateList.add(passTicket.getDatetimeEnd().toString());
            passTicketDateList.add(passTicket.getIsPayed()+"");
            map.put("passTicketDateList", passTicketDateList);
        }
        return new ModelAndView("user", "map", map);
    }

    @RequestMapping("/librarian")
    public ModelAndView goToLibrarian(@RequestParam String userId)
    {
        User librarian = userService.getUser(userId);
        Map<String, List> map = new HashMap<String, List>();
        List<User> userList = new ArrayList<User>();
        userList.add(librarian);

        map.put("userData",userData);
        map.put("userList",userList);
        return new ModelAndView("librarian", "map", map);
    }

    @RequestMapping("/loginRequstToDB")
    public ModelAndView loginUser() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails returnUser =
                (UserDetails) authentication.getPrincipal();

        userId = userService.getUserListFromLogin(returnUser.getUsername()).get(0).getUserId() + "";
        userLogin = returnUser.getUsername();
      //  userIsLibrarian = ((Boolean) userService.getUserListFromLogin(returnUser.getUsername()).get(0).getIsLibrarian()).toString();

        userData.set(0, userId);
        userData.set(1, userLogin);
      //  userData.set(2, userIsLibrarian);


//        if (userService.getUserListFromLogin(returnUser.getUsername()).get(0).getIsLibrarian())
//            return new ModelAndView("redirect:/librarian?userId=" + userId);
//
//        else
            return new ModelAndView("redirect:/user?userId=" + userId);
        //}
        // else return new ModelAndView("redirect:/login");
    }
    @RequestMapping("/loginRequstToDBandgoTobook")
    public ModelAndView loginUsertoBook(@RequestParam("bookId") String bookId, @RequestParam String login, @RequestParam String password) {



        List<User> userListFromLogin = userService.getUserListFromLoginandPass(login,password);
        List<User> userListFromEmail = userService.getUserListFromEmailandPass(login,password);
        userListFromEmail.addAll(userListFromLogin);
        User returnUser = userListFromEmail.get(0);

        userId      = returnUser.getUserId()+"";
        userLogin   = returnUser.getLogin();
        // userIsLibrarian = ((Boolean)returnUser.getIsLibrarian()).toString();

        userData.set(0,userId);
        userData.set(1,userLogin);
        // userData.set(2,userIsLibrarian);
        Map<String, List> map = new HashMap<String, List>();
        List<User> userList = new ArrayList<User>();
        userList.add(returnUser);
        map.put("userData",userData);
        map.put("userList",userList);



        return new ModelAndView("redirect:/book?id_book="+bookId);
    }
    @RequestMapping("/loginRequstToDBandgoToseries")
    public ModelAndView loginUsertoSeries(@RequestParam("seriesId") String seriesId, @RequestParam String login, @RequestParam String password) {



        List<User> userListFromLogin = userService.getUserListFromLoginandPass(login,password);
        List<User> userListFromEmail = userService.getUserListFromEmailandPass(login,password);
        userListFromEmail.addAll(userListFromLogin);
        User returnUser = userListFromEmail.get(0);

        userId      = returnUser.getUserId()+"";
        userLogin   = returnUser.getLogin();
        // userIsLibrarian = ((Boolean)returnUser.getIsLibrarian()).toString();

        userData.set(0,userId);
        userData.set(1,userLogin);
        //userData.set(2,userIsLibrarian);
        Map<String, List> map = new HashMap<String, List>();
        List<User> userList = new ArrayList<User>();
        userList.add(returnUser);
        map.put("userData",userData);
        map.put("userList",userList);



        return new ModelAndView("redirect:/series?id_series="+seriesId);
    }




    @RequestMapping("/insert")
    public ModelAndView inserData(@ModelAttribute User user) {
        if (user != null) {
            List<User> userList = userService.getUserListFromLogin(user.getLogin());
            if(!userList.isEmpty()) return new ModelAndView("redirect:/reg");
            else
                userService.insertData(user);
            List<User> userList1 = userService.getUserListFromLogin(user.getLogin());
            user.setUserId(userList1.get(0).getUserId());
            Map<String, List> map = new HashMap<String, List>();
            userId      = user.getUserId()+"";
            userLogin   = user.getLogin();
            //  userIsLibrarian = ((Boolean)user.getIsLibrarian()).toString();

            userData.set(0,userId);
            userData.set(1,userLogin);
            // userData.set(2,userIsLibrarian);

            map.put("userData",userData);
            return new ModelAndView("redirect:/user?userId="+user.getUserId(),"map",map);
        }
        else  return new ModelAndView("redirect:/reg");

    }





    @RequestMapping("/login1")
    public ModelAndView selectEmail(@ModelAttribute User user) {

        if (user != null)
            idList.set(0, userService.getUseridFromEmail(user.getEmail()));
        List<String> genderList = new ArrayList<String>();
        genderList.add("male");
        genderList.add("female");


        Map<String, List> map = new HashMap<String, List>();
        map.put("genderList", genderList);
        map.put("idList", idList);
        map.put("userData",userData);
        return new ModelAndView("reg", "map", map);
    }



    @RequestMapping("/book")
    public ModelAndView sendBook(@RequestParam("id_book") String id) {

        Book book = bookService.getBook(id);
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(book);
        Map<String, List> map = new HashMap<String, List>();
        map.put("bookList",bookList);
        map.put("userData",userData);

        return new ModelAndView("book", "map", map);

    }



    @RequestMapping("/series")
    public ModelAndView sendSeries(@RequestParam("id_series") String id) {

        Series series = seriesService.getSeries(id);
        List<Series> seriesList = new ArrayList<Series>();
        seriesList.add(series);
        List<IPagination> bookList = new ArrayList<IPagination>();
        // List<SeriesContent> seriesContentList = seriesContentService.getSeriesContentFromSeries(series.getId()+"");
        List<Book> tempBookList = seriesService.findSeriesFetchBook(series.getId()).getBookList();
//        for(SeriesContent seriesContent : seriesContentList)
//        {
//            bookList.add(bookService.getBook(seriesContent.getBookId() + ""));
//        }
        for(Book book : tempBookList)
        {
            bookList.add(bookService.getBook(book.getId()+""));
        }
        Collections.sort(bookList);

        Map<String, List> map = new HashMap<String, List>();
        map.put("seriesList",seriesList);
        map.put("userData",userData);
        map.put("bookList",bookList);


        return new ModelAndView("series", "map", map);

    }

    @RequestMapping("/addToCollection")
    public ModelAndView res1(@RequestParam("bookId") String bookId) {

        List<IPagination> seriesList = new ArrayList<IPagination>();

        seriesList.addAll(seriesService.getSeriesList());
        Collections.sort(seriesList);
        privateMap = new HashMap<String, List<IPagination>>();

        privateMap.put("seriesList", seriesList);

        List<String> bookIdList = new ArrayList<String>();
        bookIdList.add(bookId);
        Map<String,List> map = new HashMap<String, List>();
        map.put("list", seriesList);
        map.put("userData",userData);
        map.put("bookIdList", bookIdList);

        return new ModelAndView("seriesListWithBookId", "map", map);
    }

    @RequestMapping("/addToCollectionStep2")
    public String addBookToCollection(@RequestParam("id_series") String seriesId, @RequestParam("id_book") String bookId) {

        SeriesContent seriesContent = new SeriesContent();
        seriesContent.setBookId(Integer.valueOf(bookId));
        seriesContent.setSeriesId(Integer.valueOf(seriesId));
        seriesContent.setDatetimeOperation(new Date(Calendar.getInstance().getTimeInMillis()));
        seriesContentService.insertData(seriesContent);
        return "redirect:/ind";
    }
    @RequestMapping(value = "/deleteBookFromSeries", method = RequestMethod.GET)
    public @ResponseBody
    DataJSON deleteBookFromSeries(@RequestParam("id_series") String seriesId, @RequestParam("id_book") String bookId) {

        SeriesContent seriesContent = seriesContentService.getSeriesContentFromBook(bookId).get(0);
        seriesContentService.deleteData(seriesContent.getSeriesContentId()+"");
        Book book = bookService.getBook(bookId);
        Series series = seriesService.getSeries(seriesId+"");

        DataJSON result = new DataJSON();
        result.setString("Book  " + book.getName() + "  DELETED from " + series.getName() +" Series" );
        return result;

    }
    @RequestMapping("/changePriceCollection")
    public String changePriceCollection(@RequestParam("id_series") String seriesId, @RequestParam("price") String price) {

        Series series = seriesService.getSeries(seriesId+"");
        series.setPreorderPrice(Integer.valueOf(price));
        seriesService.updateData(series);

        return "redirect:/series?id_series="+seriesId;

    }



    ////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public @ResponseBody
    DataJSON order(@RequestParam("id_book") String bookId, @RequestParam("id_user") String userId) {

        Book book = bookService.getBook(bookId);
        BooksOrder booksOrder = new BooksOrder();
        booksOrder.setPrice(Integer.valueOf(book.getPrice()));
        booksOrder.setAmount(1);
        booksOrder.setUserId(Integer.valueOf(userId));
        booksOrder.setBookId(Integer.valueOf(bookId));
        booksOrder.setDatetimeOperation(new Date(Calendar.getInstance().getTimeInMillis()));
        booksOrder.setIsDone(0);
        booksOrderService.insertData(booksOrder);

        DataJSON result = new DataJSON();
        result.setString("Book  " + book.getName() + "  added to Basket");
        return result;

    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
    public @ResponseBody
    DataJSON checkLogin(@RequestParam("login") String login) {

        DataJSON result = new DataJSON();
        List<User> userList = userService.getUserListFromLogin(login);
        if(userList.isEmpty())
        {
            result.setString("This Login is free");
        }
        else
        {
            result.setString("This Login is using");
        }
        return result;

    }
    @RequestMapping(value = "/preorder", method = RequestMethod.GET)
    public @ResponseBody
    DataJSON preorder(@RequestParam("id_series") String seriesId, @RequestParam("id_user") String userId) {

        Series series = seriesService.getSeries(seriesId);
        SeriesPreorder seriesPreorder = new SeriesPreorder();
        seriesPreorder.setUserId(Integer.valueOf(userId));
        seriesPreorder.setSeriesId(Integer.valueOf(seriesId));
        seriesPreorder.setMonthAmount(1);
        seriesPreorder.setDatetimeOperation(new Date(Calendar.getInstance().getTimeInMillis()));
        seriesPreorder.setIsDone(0);
        seriesPreorderService.insertData(seriesPreorder);
        DataJSON result = new DataJSON();
        result.setString("Series  " + series.getName() + "  added to Basket");
        return result;
    }

    @RequestMapping(value = "/updateSeriesPage", method = RequestMethod.GET)
    public
    String order(@RequestParam("id_series") String seriesId) {

        return "redirect:/series?id_series=" + seriesId;
    }


    @RequestMapping("/editPersonalInf")
    public ModelAndView editUserInf(@RequestParam("id_user") String userId, @ModelAttribute User user) {


        user = userService.getUser(userId);
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        List<String> genderList = new ArrayList<String>();
        genderList.add("male");
        genderList.add("female");
        Map<String, List> map = new HashMap<String, List>();
        map.put("genderList", genderList);
        map.put("userList", userList);

        return new ModelAndView("editPersonalInf", "map", map);
    }
    @RequestMapping("/updateUserInf")
    public String updateUserInf(@ModelAttribute User user) {
        if (user != null)
            userService.updateData(user);
        // if(!user.getIsLibrarian())
        // return "redirect:/user?userId="+user.getUserId();
        //  else
        return "redirect:/librarian?userId="+user.getUserId();
    }


    @RequestMapping(value = "/buyPassTicket", method = RequestMethod.GET)
    public @ResponseBody
    DataJSON buyPassTicket(@RequestParam("id_user") String userId, @RequestParam("passTicketMonth") String passTicketMonth) {

        PassTicket passTicket = passTicketService.getPassTicketFromUser(userId);
        DataJSON result = new DataJSON();
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        if(passTicket != null) {
            if (passTicket.getDatetimeEnd().getTime() < date.getTime()) {
                PassTicket passTicket1 = new PassTicket();
                passTicket1.setUserId(Integer.valueOf(userId));
                Calendar calendar = GregorianCalendar.getInstance();
                Date date1 = new Date(calendar.getTimeInMillis());
                passTicket1.setDatetimeStart(date1);
                String calen1 = date1.toString();
                Calendar calendar2 = GregorianCalendar.getInstance();
                calendar2.add(Calendar.MONTH, Integer.valueOf(passTicketMonth));
                Date date2 = new Date(calendar2.getTimeInMillis());
                String calen2 = date2.toString();
                passTicket1.setDatetimeEnd(new Date(calendar2.getTimeInMillis()));
                passTicketService.insertData(passTicket1);
                result.setString("Your ticket is valid FROM   " + calen1 + "\nTO  " + calen2+"\nPlease PAY for it in Basket");
            } else
                result.setString("Your ticket is valid now");
        }
        else
        {
            PassTicket passTicket1 = new PassTicket();
            passTicket1.setUserId(Integer.valueOf(userId));
            Calendar calendar = GregorianCalendar.getInstance();
            Date date1 = new Date(calendar.getTimeInMillis());
            passTicket1.setDatetimeStart(date1);
            String calen1 = date1.toString();
            Calendar calendar2 = GregorianCalendar.getInstance();
            calendar2.add(Calendar.MONTH, Integer.valueOf(passTicketMonth));
            Date date2 = new Date(calendar2.getTimeInMillis());
            String calen2 = date2.toString();
            passTicket1.setDatetimeEnd(new Date(calendar2.getTimeInMillis()));
            passTicketService.insertData(passTicket1);
            result.setString("Your ticket is valid FROM   " + calen1 + "\nTO  " + calen2+"\nPlease PAY for it in Basket");
        }

        return result;

    }

    @RequestMapping(value = "/updateUserPage", method = RequestMethod.GET)
    public
    String updateUserPage(@RequestParam("id_user") String userId) {

        return "redirect:/user?id_user=" + userId;
    }
    @RequestMapping("/changePass")
    public ModelAndView editUserInf(@RequestParam("id_user") String userId) {

        return new ModelAndView("changePass","userId",userId);
    }

    @RequestMapping("/changePasswordSuccess")
    public ModelAndView changePasswordSuccess(@RequestParam("userId") String userId, @RequestParam("password") String password, @RequestParam("newpassword") String newpassword, @RequestParam("repassword") String repassword) {


        User user = userService.getUser(userId);
        if(user.getPassword().equals(password) && repassword.equals(newpassword))
        {
            user.setPassword(newpassword);
            userService.updateData(user);
            return new ModelAndView("redirect:/user?userId="+userId);
        }
        else
            return new ModelAndView("redirect:/changePass?id_user="+userId);

    }
    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(@RequestParam("userId") String userId) {



        return new ModelAndView("deleteUser","userId",userId);

    }


    @RequestMapping(value="/deleteUserSuccess", method = RequestMethod.GET)
    public ModelAndView deleteUserSuccess(@RequestParam("userId") String userId, @RequestParam("password") String password, @RequestParam("secretQuestion") String secretQuestion) {


        User user = userService.getUser(userId);
        if(user.getPassword().equals(password) && user.getSecretQuestion().equals(secretQuestion))
        {
            PassTicket pas = passTicketService.getPassTicketFromUser(userId);
            if(pas != null)
                passTicketService.deleteData(pas.getPassticketId()+"");
            List<SeriesPreorder> seriesPreorderList = seriesPreorderService.getSeriesPreorderFromUser(userId);
            if(!seriesPreorderList.isEmpty())
                for(SeriesPreorder ser : seriesPreorderList ) {

                    seriesPreorderService.deleteData(ser.getSeriesPreorderId()+"");
                }
            List<BooksOrder> booksOrderList = booksOrderService.getBooksOrderFromUser(userId);
            if(!booksOrderList.isEmpty())
                for(BooksOrder booksOrder : booksOrderList){
                    booksOrderService.deleteData(booksOrder.getBooksOrderId()+"");
                }
            userService.deleteData(userId);
            return new ModelAndView("redirect:/logOut");
        }
        else
            return new ModelAndView("redirect:/deleteUser?userId="+userId);

    }

    @RequestMapping("/basket")
    public ModelAndView basketView(@RequestParam("userId") String userId){


        List<BooksOrder> booksOrderList = booksOrderService.getBooksOrderFromUser(userId);
        List<SeriesPreorder> seriesPreorderList = seriesPreorderService.getSeriesPreorderFromUser(userId);
        List<PassTicket> passTicketList =new ArrayList<PassTicket>();
        passTicketList.add(passTicketService.getPassTicketFromUser(userId));

        if(!booksOrderList.isEmpty())
            for(BooksOrder booksOrder : booksOrderList)
            {
                booksOrder.setBookName(bookService.getBook(booksOrder.getBookId()+"").getName());
                booksOrder.setBookAuthor(bookService.getBook(booksOrder.getBookId() + "").getAuthor());
            }
        if(!seriesPreorderList.isEmpty())
            for(SeriesPreorder seriesPreorder : seriesPreorderList){
                seriesPreorder.setSeriesName(seriesService.getSeries(seriesPreorder.getSeriesId()+"").getName());
                seriesPreorder.setSeriesAuthor(seriesService.getSeries(seriesPreorder.getSeriesId() + "").getAuthor());
                seriesPreorder.setPrice(Integer.valueOf(seriesService.getSeries(seriesPreorder.getSeriesId()+"").getPrice()));
            }
        Map<String, List> map = new HashMap<String, List>();
        map.put("booksOrderList",booksOrderList);
        map.put("seriesPreorderList",seriesPreorderList);
        map.put("passTicketList",passTicketList);
        map.put("userData",userData);




        return new ModelAndView("basket","map",map);
    }
    @RequestMapping("/about")
    public ModelAndView about() {

        return new ModelAndView("about","userData",userData);

    }
    @RequestMapping("/readingRoom")
    public ModelAndView readinRoom() {

        ReadingRoom readingRoomObj = new ReadingRoom();
        Map<String, Object> map = new HashMap<String, Object>();
        List<ReadingRoom> readingRoomList = readingRoomService.getReadingRoomList();
        map.put("readingRoomObj", readingRoomObj);
        map.put("readingRoomList",readingRoomList);
        map.put("userData",userData);
        return new ModelAndView("readingRoom","map",map);

    }

    @RequestMapping("/bookFromReadingRoom")
    public ModelAndView bookFromReadingRoom(@RequestParam(value = "login",defaultValue = "") String login,@RequestParam(value = "firstName",defaultValue = "") String firstName,@RequestParam(value = "lastName",defaultValue = "") String lastName,@RequestParam(value = "hours",defaultValue = "") String hours) {

        ReadingRoom readingRoom = new ReadingRoom();
        readingRoom.setUserLogin("");
        if(!login.equals("")) {
            List<User> userListFromLogin = userService.getUserListFromLogin(login);
            List<User> userListFromEmail = userService.getUserListFromEmail(login);
            userListFromEmail.addAll(userListFromLogin);
            if (!userListFromEmail.isEmpty()){
                User returnUser = userListFromEmail.get(0);
                PassTicket passTicket = passTicketService.getPassTicketFromUser(returnUser.getUserId()+"");
                if(passTicket.getIsPayed() == 1) {

                    readingRoom.setFirstName(returnUser.getFirstName());
                    readingRoom.setLastName(returnUser.getLastName());
                    readingRoom.setUserLogin(returnUser.getLogin());

                }
            }

        }
        else
        {
            readingRoom.setFirstName(firstName);
            readingRoom.setLastName(lastName);
        }
        Calendar calendar = GregorianCalendar.getInstance();
        Date date1 = new Date(calendar.getTimeInMillis());
        Calendar calendar2 = GregorianCalendar.getInstance();
        java.sql.Timestamp timestamp1 = new java.sql.Timestamp(date1.getTime());
        readingRoom.setDatetimeStart(timestamp1);
        if(!hours.equals("")) {
            calendar2.add(Calendar.HOUR, Integer.valueOf(hours));
            Date date2 = new Date(calendar2.getTimeInMillis());
            java.sql.Timestamp timestamp2 = new java.sql.Timestamp(date2.getTime());
            readingRoom.setDatetimeEnd(timestamp2);
        }
        //readingRoom.setBookId(0);
        readingRoomService.insertData(readingRoom);
        List<IPagination> bookList = new ArrayList<IPagination>();

        bookList.addAll(bookService.getBookList());
        Collections.sort(bookList);
        ReadingRoom temp = readingRoomService.getReadingRoomFromFirstName(readingRoom.getFirstName());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("readingRoomId", temp.getReadingRoomId());
        map.put("bookList",bookList);
        map.put("hours",hours);

        return new ModelAndView("bookChoose","map",map);

    }
    @RequestMapping("/chooseBookStep2")
    public ModelAndView bookChooseStep2(@RequestParam("id_book") String bookId, @RequestParam("readingRoomId") String readingRoomId,@RequestParam("hours") String hours) {

        Book book = bookService.getBook(bookId);
        ReadingRoom readingRoom = readingRoomService.getReadingRoom(readingRoomId);
        readingRoom.setBookId(Integer.valueOf(bookId));
        readingRoomService.updateData(readingRoom);

        List<ReadingRoom> readingRoomList = readingRoomService.getReadingRoomList();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("readingRoomObj",readingRoom);
        map.put("readingRoomList",readingRoomList);
        map.put("hours",hours);
        return new ModelAndView("redirect:/readingRoom","map",map);
    }

    @RequestMapping("/deleteReadingRoom")
    public ModelAndView deleteReadingRoom(@RequestParam("readingRoomId") String readingRoomId) {

        readingRoomService.deleteData(readingRoomId);


        return new ModelAndView("redirect:/readingRoom");
    }
    @RequestMapping("/deleteOrder")
    public ModelAndView DeleteOrder(@RequestParam("id") String orderId,@RequestParam("userId") String userId) {

        booksOrderService.deleteData(orderId);
        return new ModelAndView("redirect:/basket?userId="+userId);
    }
    @RequestMapping("/deletePreoder")
    public ModelAndView DeletePreOrder(@RequestParam("id") String preorederId,@RequestParam("userId") String userId) {

        seriesPreorderService.deleteData(preorederId);
        return new ModelAndView("redirect:/basket?userId="+userId);
    }
    @RequestMapping("/deletePassTicket")
    public ModelAndView DeletePassTicket(@RequestParam("id") String passTicketId,@RequestParam("userId") String userId) {

        passTicketService.deleteData(passTicketId);
        return new ModelAndView("redirect:/basket?userId="+userId);
    }
    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public String index(ModelMap map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails =
                    (UserDetails) authentication.getPrincipal();
            map.addAttribute("userDetails", userDetails);
        }
        return "second";
    }
}