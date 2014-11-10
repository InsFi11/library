package beingjavaguys.services;

import java.util.List;

import beingjavaguys.dao.BooksDaoImpl;
import beingjavaguys.dao.UserDaoImpl;
import beingjavaguys.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import beingjavaguys.domain.User;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl{

    @Autowired
    BooksDaoImpl booksDao;


    public void insertData(Book book) {
        booksDao.insertData(book);
    }


    public List<Book> getBookList() {
        return booksDao.getBookList();
    }
    public Book findBookFetchGenre(int id) {return  booksDao.findBookFetchGenre(id);}

    public void deleteData(String id) {
        booksDao.deleteData(id);

    }
    public List<Book> getBookListFromName(String bookName){return booksDao.getBookListFromName(bookName);}
    public List<Book> getBookListFromGenre(String genre){return booksDao.getBookListFromGenre(genre);}
    public List<Book> getBookListFromAuthor(String author){return booksDao.getBookListFromAuthor(author);}

    public Book getBook(String id) {
        return booksDao.getBook(id);
    }


    public void updateData(Book book) {
        booksDao.updateData(book);

    }



}
