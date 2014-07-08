package beingjavaguys.services;

import beingjavaguys.dao.PassTicketDaoImpl;
import beingjavaguys.domain.PassTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassTicketServiceImpl{

    @Autowired
    PassTicketDaoImpl passTicketDao;


    public void insertData(PassTicket passticket) {
        passTicketDao.insertData(passticket);
    }


    public List<PassTicket> getPassTicketList() {
        return passTicketDao.getPassTicketList();
    }


    public void deleteData(String id) {
        passTicketDao.deleteData(id);

    }


    public PassTicket getPassTicket(String id) {
        return passTicketDao.getPassTicket(id);
    }
    public PassTicket getPassTicketFromUser(String id){return passTicketDao.getPassTicketFromUser(id);}

    public void updateData(PassTicket passticket) {
        passTicketDao.updateData(passticket);

    }



}