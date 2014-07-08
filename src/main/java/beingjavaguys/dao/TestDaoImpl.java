package beingjavaguys.dao;

import beingjavaguys.domain.BookPassTicket;
import beingjavaguys.domain.TestJUnit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class TestDaoImpl {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void insertData(TestJUnit testJUnit) {

        sessionFactory.getCurrentSession().save(testJUnit);
    }
    @Transactional
    public List<TestJUnit> getTestList() {

        List  testJUnit = sessionFactory.getCurrentSession().createQuery("from TestJUnit")
                .list();

        return testJUnit;
    }

    @Transactional
    public void deleteData(String id) {

        TestJUnit testJUnit = (TestJUnit) sessionFactory.getCurrentSession().get(
                TestJUnit.class, Integer.valueOf(id));
        if (null != testJUnit) {
            sessionFactory.getCurrentSession().delete(testJUnit);
        }
    }

    @Transactional
    public void updateData(TestJUnit testJUnit) {

        sessionFactory.getCurrentSession().update(testJUnit);
    }

    @Transactional
    public TestJUnit getTest(String id) {


        TestJUnit testJUnit = (TestJUnit)sessionFactory.getCurrentSession().get(TestJUnit.class,  Integer.valueOf(id));

        return testJUnit;
    }

}
