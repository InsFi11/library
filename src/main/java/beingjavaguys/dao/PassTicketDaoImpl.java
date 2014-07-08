package beingjavaguys.dao;

import beingjavaguys.domain.PassTicket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class PassTicketDaoImpl  {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public void insertData(PassTicket passTicket) {

       sessionFactory.getCurrentSession().save(passTicket);

    }
    @Transactional
    public List<PassTicket> getPassTicketList() {
        List  passticketList = sessionFactory.getCurrentSession().createQuery("from PassTicket")
                .list();

        return passticketList;
    }

    @Transactional
    public void deleteData(String id) {
        PassTicket passTicket = (PassTicket) sessionFactory.getCurrentSession().get(
                PassTicket.class, id);
        if (null != passTicket) {
            sessionFactory.getCurrentSession().delete(passTicket);

        }

    }

    @Transactional
    public void updateData(PassTicket passticket) {
        sessionFactory.getCurrentSession().update(passticket);

    }

    @Transactional
    public PassTicket getPassTicket(String id) {
        PassTicket passTicket = (PassTicket)sessionFactory.getCurrentSession().get(PassTicket.class, Integer.valueOf(id));
        return passTicket;
    }
    @Transactional
    public PassTicket getPassTicketFromUser(String id) {

        List passticketList = sessionFactory.getCurrentSession().createQuery("from PassTicket where id_user like " + id).list();
        if(!passticketList.isEmpty())
        return (PassTicket)passticketList.get(0);
        else
            return null;
    }

}