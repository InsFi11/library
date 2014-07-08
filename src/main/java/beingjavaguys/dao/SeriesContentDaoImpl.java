package beingjavaguys.dao;

import beingjavaguys.domain.SeriesContent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class SeriesContentDaoImpl  {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public void insertData(SeriesContent seriesContent) {

        sessionFactory.getCurrentSession().save(seriesContent);


    }
    @Transactional
    public List<SeriesContent> getSeriesContentList() {
        List seriesContentList = new ArrayList();


        List  readingRoomList = sessionFactory.getCurrentSession().createQuery("from SeriesContent")
                .list();


        return readingRoomList;
    }

    @Transactional
    public void deleteData(String id) {
        SeriesContent seriesContent = (SeriesContent) sessionFactory.getCurrentSession().get(
                SeriesContent.class, Integer.valueOf(id));
        if (null != seriesContent) {
            sessionFactory.getCurrentSession().delete(seriesContent);

        }

    }

    @Transactional
    public void updateData(SeriesContent seriesContent) {
        sessionFactory.getCurrentSession().update(seriesContent);
    }

    @Transactional
    public SeriesContent getSeriesContent(String id) {
        SeriesContent seriesContent = (SeriesContent)sessionFactory.getCurrentSession().get(SeriesContent.class, Integer.valueOf(id));
        return seriesContent;
    }
    @Transactional
    public List<SeriesContent> getSeriesContentFromSeries(String id) {

        List seriesContentList = sessionFactory.getCurrentSession().createQuery("from SeriesContent where id_series like '" + id +"'").list();
        return seriesContentList;
    }
    @Transactional
    public  List<SeriesContent> getSeriesContentFromBook(String id) {

        List seriesContentList = sessionFactory.getCurrentSession().createQuery("from SeriesContent where id_book like '" + id +"'").list();
        return seriesContentList;
    }


}
