package beingjavaguys.services;

import beingjavaguys.dao.SeriesPreorderDaoImpl;
import beingjavaguys.domain.SeriesPreorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesPreorderServiceImpl{

    @Autowired
    SeriesPreorderDaoImpl seriesPreorderDao;


    public void insertData(SeriesPreorder seriesPreorder) {
        seriesPreorderDao.insertData(seriesPreorder);
    }


    public List<SeriesPreorder> getSeriesPreorderList() {
        return seriesPreorderDao.getSeriesPreorderList();
    }


    public void deleteData(String id) {
        seriesPreorderDao.deleteData(id);

    }


    public SeriesPreorder getSeriesPreorder(String id) {
        return seriesPreorderDao.getSeriesPreorder(id);
    }
    public List<SeriesPreorder> getSeriesPreorderFromUser(String id){return seriesPreorderDao.getSeriesPreorderFromUser(id);}

    public void updateData(SeriesPreorder seriesPreorder) {
        seriesPreorderDao.updateData(seriesPreorder);

    }



}