package beingjavaguys.services;

import beingjavaguys.dao.SeriesContentDaoImpl;
import beingjavaguys.domain.SeriesContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesContentServiceImpl{

    @Autowired
    SeriesContentDaoImpl seriesContentDao;


    public void insertData(SeriesContent seriesContent) {
        seriesContentDao.insertData(seriesContent);
    }


    public List<SeriesContent> getSeriesContentList() {
        return seriesContentDao.getSeriesContentList();
    }


    public void deleteData(String id) {
        seriesContentDao.deleteData(id);

    }

    public List<SeriesContent> getSeriesContentFromSeries(String id) { return seriesContentDao.getSeriesContentFromSeries(id); }
    public List<SeriesContent> getSeriesContentFromBook(String id) {return  seriesContentDao.getSeriesContentFromBook(id);}

    public SeriesContent getSeriesContent(String id) {
        return seriesContentDao.getSeriesContent(id);
    }


    public void updateData(SeriesContent seriesContent) {
        seriesContentDao.updateData(seriesContent);

    }



}
