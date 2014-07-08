package beingjavaguys.services;

import beingjavaguys.dao.BookPassTicketDaoImpl;
import beingjavaguys.domain.BookPassTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookPassTicketServiceImpl{

    @Autowired
    BookPassTicketDaoImpl bookPassTicketDao;


    public void insertData(BookPassTicket bookPassTicket) {
        bookPassTicketDao.insertData(bookPassTicket);
    }


    public List<BookPassTicket> getBookPassTicketList() {
        return bookPassTicketDao.getBookPassTicketList();
    }


    public void deleteData(String id) {
        bookPassTicketDao.deleteData(id);

    }


    public BookPassTicket getBookPassTicket(String id) {
        return bookPassTicketDao.getBookPassTicket(id);
    }


    public void updateData(BookPassTicket bookPassTicket) {
        bookPassTicketDao.updateData(bookPassTicket);

    }



}
