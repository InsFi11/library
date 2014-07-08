package beingjavaguys.services;

import beingjavaguys.dao.BookPassTicketDaoImpl;
import beingjavaguys.dao.TestDaoImpl;
import beingjavaguys.domain.BookPassTicket;
import beingjavaguys.domain.TestJUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl{

    @Autowired
    TestDaoImpl testDaoImpl;


    public void insertData(TestJUnit testJUnit) {
        testDaoImpl.insertData(testJUnit);
    }


    public List<TestJUnit> getTest() {
        return testDaoImpl.getTestList();
    }


    public void deleteData(String id) {
        testDaoImpl.deleteData(id);

    }


    public TestJUnit getTest(String id) {
        return testDaoImpl.getTest(id);
    }


    public void updateData(TestJUnit testJUnit) {
        testDaoImpl.updateData(testJUnit);

    }



}
