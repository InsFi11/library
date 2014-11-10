package beingjavaguys.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import beingjavaguys.domain.Book;
import beingjavaguys.domain.BookPassTicket;

import beingjavaguys.domain.Genre;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class GenreDaoImpl  {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public Genre getGenre(String genreName) {

        // Genre genre = (Genre)sessionFactory.getCurrentSession().get(Genre.class, id);
        // Genre genre = (Genre)sessionFactory.getCurrentSession().createQuery("from Genre where name = " + genreName).list().get(0);
        Genre genre = (Genre)sessionFactory.getCurrentSession().createQuery("from Genre where (name='" + genreName +"')").list().get(0);
        return genre;
    }
    @Transactional
    public Genre findGenreFromNameFetchBook(String genreName) {
        String select = "select distinct g from Genre g " +
                "left join fetch g.bookList " +
                "where g.name = :genreName";

        List tempList =  sessionFactory.getCurrentSession().createQuery(select)
                .setParameter("genreName", genreName)
                .list();
        if(!tempList.isEmpty()) return (Genre)tempList.get(0);
        else return null;
//          return (Genre) sessionFactory.getCurrentSession().createQuery(select)
//                .setParameter("genreName", genreName)
//                .list().get(0);
    }
    @Transactional
    public Genre findGenreFromNameFetchSeries(String genreName) {
        String select = "select distinct g from Genre g " +
                "left join fetch g.seriesList " +
                "where g.name = :genreName";

        List tempList =  sessionFactory.getCurrentSession().createQuery(select)
                .setParameter("genreName", genreName)
                .list();
        if(!tempList.isEmpty()) return (Genre)tempList.get(0);
        else return null;
//          return (Genre) sessionFactory.getCurrentSession().createQuery(select)
//                .setParameter("genreName", genreName)
//                .list().get(0);
    }
}

