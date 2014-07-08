package beingjavaguys.dao;

import beingjavaguys.domain.SeriesPreorder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class SeriesPreorderDaoImpl {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public void insertData(SeriesPreorder seriesPreOrder) {

        sessionFactory.getCurrentSession().save(seriesPreOrder);

    }
    @Transactional
    public List<SeriesPreorder> getSeriesPreorderList() {
        List  seriesPreOrderList = sessionFactory.getCurrentSession().createQuery("from SeriesPreorder")
                .list();
        return seriesPreOrderList;

    }

    @Transactional
    public void deleteData(String id) {
        SeriesPreorder seriesPreorder = (SeriesPreorder) sessionFactory.getCurrentSession().get(
                SeriesPreorder.class, Integer.valueOf(id));
        if (null != seriesPreorder) {
            sessionFactory.getCurrentSession().delete(seriesPreorder);

        }

    }

    @Transactional
    public void updateData(SeriesPreorder seriesPreOrder) {
        sessionFactory.getCurrentSession().update(seriesPreOrder);
    }

    @Transactional
    public SeriesPreorder getSeriesPreorder(String id) {
        SeriesPreorder seriesPreorder = (SeriesPreorder)sessionFactory.getCurrentSession().get(SeriesPreorder.class, Integer.valueOf(id));
        return seriesPreorder;
    }
    @Transactional
    public List<SeriesPreorder> getSeriesPreorderFromUser(String id) {

        List seriesPreOrderList = sessionFactory.getCurrentSession().createQuery("from SeriesPreorder where id_user =" + id).list();

        return seriesPreOrderList;

    }

}

