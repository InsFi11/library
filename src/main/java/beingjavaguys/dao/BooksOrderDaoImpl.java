package beingjavaguys.dao;

import beingjavaguys.domain.BooksOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class BooksOrderDaoImpl  {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void insertData(BooksOrder booksOrder) {

        sessionFactory.getCurrentSession().save(booksOrder);

    }
    @Transactional
    public List<BooksOrder> getBooksOrderList() {

        List  booksOrderList = sessionFactory.getCurrentSession().createQuery("from BooksOrder")
                .list();
        return booksOrderList;
    }

    @Transactional
    public void deleteData(String id) {
        BooksOrder booksOrder = (BooksOrder) sessionFactory.getCurrentSession().get(
                BooksOrder.class, Integer.valueOf(id));
        if (null != booksOrder) {
            sessionFactory.getCurrentSession().delete(booksOrder);

        }

    }

    @Transactional
    public void updateData(BooksOrder booksOrder) {

        sessionFactory.getCurrentSession().update(booksOrder);
    }

    @Transactional
    public BooksOrder getBooksOrder(String id) {

        BooksOrder booksOrder = (BooksOrder)sessionFactory.getCurrentSession().get(BooksOrder.class, Integer.valueOf(id));
        return booksOrder;
    }
    @Transactional
    public List<BooksOrder> getBooksOrderFromUser(String id) {

        List booksOrderList = sessionFactory.getCurrentSession().createQuery("from BooksOrder where id_user =" + id).list();

        return booksOrderList;
    }

}
