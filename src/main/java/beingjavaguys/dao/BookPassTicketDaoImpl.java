package beingjavaguys.dao;

import beingjavaguys.domain.BookPassTicket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class BookPassTicketDaoImpl {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void insertData(BookPassTicket bookPassTicket) {

        sessionFactory.getCurrentSession().save(bookPassTicket);
    }
    @Transactional
    public List<BookPassTicket> getBookPassTicketList() {

        List  bookPassTicketList = sessionFactory.getCurrentSession().createQuery("from BookPassTicket")
                .list();

        return bookPassTicketList;
    }

    @Transactional
    public void deleteData(String id) {

        BookPassTicket bookPassTicket = (BookPassTicket) sessionFactory.getCurrentSession().get(
                BookPassTicket.class, Integer.valueOf(id));
        if (null != bookPassTicket) {
            sessionFactory.getCurrentSession().delete(bookPassTicket);
        }
    }

    @Transactional
    public void updateData(BookPassTicket bookPassTicket) {

     sessionFactory.getCurrentSession().update(bookPassTicket);
    }

    @Transactional
    public BookPassTicket getBookPassTicket(String id) {


        BookPassTicket bookPassTicket = (BookPassTicket)sessionFactory.getCurrentSession().get(BookPassTicket.class,  Integer.valueOf(id));

        return bookPassTicket;
    }

}
