package beingjavaguys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name = "test")
public class TestJUnit {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="testName")
    private String testName;

   public void setId(int id){
       this.id = id;
   }
    public void setTestName(String testName){
        this.testName = testName;
    }
    public int getId(){
        return id;
    }
    public String getTestName(){
        return testName;
    }
}
