package beingjavaguys.dao;

import beingjavaguys.domain.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class SeriesDaoImpl  {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public void insertData(Series series) {

       sessionFactory.getCurrentSession().save(series);

    }
    @Transactional
    public List<Series> getSeriesList() {
        List  seriesList = sessionFactory.getCurrentSession().createQuery("from Series")
                .list();


        return seriesList;
    }
    @Transactional
    public List<Series> getSeriesListFromName(String name) {

        List seriesList = sessionFactory.getCurrentSession().createQuery("from Series where nameSeries like '%" + name + "%'ORDER BY id_series DESC").list();
        return seriesList;
    }
    @Transactional
    public List<Series> getSeriesListFromGenre(String genre) {


        List seriesList = sessionFactory.getCurrentSession().createQuery("from Series where genre like '%" + genre +"%'ORDER BY id_series DESC").list();

        return seriesList;
    }
    @Transactional
    public List<Series> getSeriesListFromAuthor(String author) {

        List seriesList = sessionFactory.getCurrentSession().createQuery("from Series where author like '%" + author +"%'ORDER BY id_series DESC").list();
        return seriesList;
    }

    @Transactional
    public void deleteData(String id) {
        Series series = (Series) sessionFactory.getCurrentSession().get(
                Series.class, id);
        if (null != series) {
            sessionFactory.getCurrentSession().delete(series);

        }

    }

    @Transactional
    public void updateData(Series series) {

        sessionFactory.getCurrentSession().update(series);

    }
    @Transactional
    public void updateDate(String id, Date date) {
/*
        String sql = "UPDATE series set dateAdd ='"+date+"' where id_series="+id;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


*/
        Series series = (Series) sessionFactory.getCurrentSession().get(
                Series.class, Integer.valueOf(id));
        series.setDateAdd(date);
        sessionFactory.getCurrentSession().update(series);

    }

    @Transactional
    public Series getSeries(String id) {
        Series series = (Series)sessionFactory.getCurrentSession().get(Series.class,  Integer.valueOf(id));
        return series;
    }

}
