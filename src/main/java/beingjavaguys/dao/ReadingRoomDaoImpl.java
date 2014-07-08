package beingjavaguys.dao;

import beingjavaguys.domain.ReadingRoom;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class ReadingRoomDaoImpl {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public void insertData(ReadingRoom readingRoom) {

        sessionFactory.getCurrentSession().save(readingRoom);

    }
    @Transactional
    public List<ReadingRoom> getReadingRoomList() {
        List  readingRoomList = sessionFactory.getCurrentSession().createQuery("from ReadingRoom")
                .list();


        return readingRoomList;
    }

    @Transactional
    public void deleteData(String id) {
        ReadingRoom readingRoom = (ReadingRoom) sessionFactory.getCurrentSession().get(
                ReadingRoom.class, id);
        if (null != readingRoom) {
            sessionFactory.getCurrentSession().delete(readingRoom);

        }

    }

    @Transactional
    public void updateData(ReadingRoom readingRoom) {

        sessionFactory.getCurrentSession().update(readingRoom);
    }

    @Transactional
    public ReadingRoom getReadingRoom(String id) {
         ReadingRoom readingRoom = (ReadingRoom)sessionFactory.getCurrentSession().get(ReadingRoom.class, Integer.valueOf(id));
        return readingRoom;
    }
    @Transactional
    public ReadingRoom getReadingRoomFromFirstName(String name) {

        ReadingRoom readingRoom = (ReadingRoom)sessionFactory.getCurrentSession().createQuery("from ReadingRoom where firstName like '" + name +"'").list().get(0);
        return readingRoom;
    }

}
