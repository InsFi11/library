package beingjavaguys.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import beingjavaguys.domain.Book;
import beingjavaguys.domain.BookPassTicket;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class BooksDaoImpl  {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void insertData(Book book) {

        sessionFactory.getCurrentSession().save(book);

    }
    @Transactional
    public Book findBookFetchGenre(int id) {
        String select = "select distinct b from Book b " +
                "left join fetch b.genreList " +
                "where b.id = :id";

        return (Book) sessionFactory.getCurrentSession().createQuery(select)
                .setParameter("id", id)
                .list().get(0);
    }
    @Transactional
    public Book findBookFetchSeries(int id) {
        String select = "select distinct b from Book b " +
                "left join fetch b.series " +
                "where b.id = :id";

        return (Book) sessionFactory.getCurrentSession().createQuery(select)
                .setParameter("id", id)
                .list().get(0);
    }
    @Transactional
    public List<Book> getBookList() {


        List  bookList = sessionFactory.getCurrentSession().createQuery("from Book")
                .list();

        return bookList;
    }
    @Transactional
    public List<Book> getBookListFromGenre(String genre) {


        List bookList = sessionFactory.getCurrentSession().createQuery("from Book where genre like '%" + genre +"%' ORDER BY id_book DESC").list();

        return bookList;
    }
    @Transactional
    public List<Book> getBookListFromName(String bookName) {

        List bookList = sessionFactory.getCurrentSession().createQuery("from Book where nameBook like '%" + bookName +"%'ORDER BY id_book DESC").list();
        return bookList;
    }
    @Transactional
    public List<Book> getBookListFromAuthor(String author) {

        List bookList = sessionFactory.getCurrentSession().createQuery("from Book where author like '%" + author +"%' ORDER BY id_book DESC").list();
        return bookList;
    }

    @Transactional
    public void deleteData(String id) {


        Book book = (Book) sessionFactory.getCurrentSession().get(
                Book.class, Integer.valueOf(id));
        if (null != book) {
            sessionFactory.getCurrentSession().delete(book);

        }
    }
    @Transactional
    public void updateData(Book book) {

        sessionFactory.getCurrentSession().update(book);
    }
    @Transactional
    public Book getBook(String id) {

        Book book = (Book)sessionFactory.getCurrentSession().get(Book.class, Integer.valueOf(id));
        return book;
    }

}
