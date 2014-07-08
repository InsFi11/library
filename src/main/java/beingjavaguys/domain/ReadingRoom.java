package beingjavaguys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by InF on 05.06.2014.
 */
@Entity
@Table(name = "reading_room")
public class ReadingRoom {

    @Id
    @Column(name="id_reading_room")
    private int readingRoomId;
    @Column(name="id_book")
    private int bookId;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="datetime_start")
    private Timestamp datetimeStart;
    @Column(name="datetime_end")
    private Timestamp datetimeEnd;
    @Column(name="userLogin")
    private String userLogin;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getReadingRoomId() {
        return readingRoomId;
    }

    public void setReadingRoomId(int readingRoomId) {
        this.readingRoomId = readingRoomId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getDatetimeStart() {
        return datetimeStart;
    }

    public void setDatetimeStart(Timestamp datetimeStart) {
        this.datetimeStart = datetimeStart;

    }
    public Timestamp getDatetimeEnd() {
        return datetimeEnd;
    }

    public void setDatetimeEnd(Timestamp datetimeEnd) {
        this.datetimeEnd = datetimeEnd;

    }
}
