package beingjavaguys.services;

import beingjavaguys.dao.ReadingRoomDaoImpl;
import beingjavaguys.domain.ReadingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingRoomServiceImpl{

    @Autowired
    ReadingRoomDaoImpl readingRoomDao;


    public void insertData(ReadingRoom readingRoom) {
        readingRoomDao.insertData(readingRoom);
    }


    public List<ReadingRoom> getReadingRoomList() {
        return readingRoomDao.getReadingRoomList();
    }


    public void deleteData(String id) {
        readingRoomDao.deleteData(id);

    }


    public ReadingRoom getReadingRoom(String id) {
        return readingRoomDao.getReadingRoom(id);
    }


    public void updateData(ReadingRoom readingRoom) {
        readingRoomDao.updateData(readingRoom);

    }

    public ReadingRoom getReadingRoomFromFirstName(String name){return readingRoomDao.getReadingRoomFromFirstName(name);}

}
