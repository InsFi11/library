package beingjavaguys;


import beingjavaguys.domain.TestJUnit;
import beingjavaguys.services.TestServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({TransactionalTestExecutionListener.class,
       DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = { "classpath:spring/root-context.xml" })
@Transactional
public class TestDB {

    @Autowired
    TestServiceImpl testService;
    TestJUnit test;

    @Before
    public void init(){
        test = new TestJUnit();
        test.setTestName("testInsert");

    }

    @Test
    public void testGetBean(){
        Assert.assertNotNull(testService);
    }

    @Test
    public void testInsertData() {


        testService.insertData(test);

    }
    
    @Test
    public void testUpdateData() {


       // test.setId( testService.getTest("2").getId());
        test.setTestName("testInsert2");
        testService.updateData(test);
    }

    @Test
    public void testSelectAllData() {

        List<TestJUnit> testList = testService.getTest();
        Assert.assertTrue(!testList.isEmpty());
    }

    @Test
    public void testSelectData() {

        Assert.assertNotNull(testService.getTest("1"));

    }

  /*  @After
    public void testDeleteData() {

        testService.deleteData("1");
    }
    */
}