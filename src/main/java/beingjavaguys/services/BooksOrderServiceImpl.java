package beingjavaguys.services;

import beingjavaguys.dao.BooksOrderDaoImpl;
import beingjavaguys.domain.BooksOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksOrderServiceImpl{

    @Autowired
    BooksOrderDaoImpl booksOrderDao;


    public void insertData(BooksOrder booksOrder) {
        booksOrderDao.insertData(booksOrder);
    }


    public List<BooksOrder> getBooksOrderList() {
        return booksOrderDao.getBooksOrderList();
    }


    public void deleteData(String id) {
        booksOrderDao.deleteData(id);

    }


    public BooksOrder getBooksOrder(String id) {
        return booksOrderDao.getBooksOrder(id);
    }
    public List<BooksOrder> getBooksOrderFromUser(String id){return booksOrderDao.getBooksOrderFromUser(id);}

    public void updateData(BooksOrder booksOrder) {
        booksOrderDao.updateData(booksOrder);

    }



}
