package beingjavaguys.services;

import java.sql.Date;
import java.util.List;

import beingjavaguys.dao.BooksDaoImpl;
import beingjavaguys.dao.SeriesDaoImpl;
import beingjavaguys.dao.UserDaoImpl;
import beingjavaguys.domain.Book;
import beingjavaguys.domain.Series;
import org.springframework.beans.factory.annotation.Autowired;
import beingjavaguys.domain.User;
import org.springframework.stereotype.Service;

@Service
public class SeriesServiceImpl{

    @Autowired
    SeriesDaoImpl seriesDao;


    public void insertData(Series series) {
        seriesDao.insertData(series);
    }
    public Series findSeriesFetchGenre(int id) { return seriesDao.findSeriesFetchGenre(id);}
    public Series findSeriesFetchBook(int id) {return  seriesDao.findSeriesFetchBook(id);}
    public List<Series> getSeriesList() {
        return seriesDao.getSeriesList();
    }


    public void deleteData(String id) {
        seriesDao.deleteData(id);

    }
    public List<Series> getSeriesListFromName(String name) {
        return seriesDao.getSeriesListFromName(name);
    }
    public List<Series> getSeriesListFromGenre(String genre){return seriesDao.getSeriesListFromGenre(genre);}
    public List<Series> getSeriesListFromAuthor(String author){return seriesDao.getSeriesListFromAuthor(author);}

    public void updateDate(String id, Date date){seriesDao.updateDate(id,date);}



    public Series getSeries(String id) {
        return seriesDao.getSeries(id);
    }


    public void updateData(Series series) {
        seriesDao.updateData(series);

    }



}

