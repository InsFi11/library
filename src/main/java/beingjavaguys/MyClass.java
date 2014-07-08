package beingjavaguys;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by InF on 23.06.2014.
 */
public class MyClass {

    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml");
        SessionFactory s = (SessionFactory)context.getBean("sessionFactory");
    }
}
