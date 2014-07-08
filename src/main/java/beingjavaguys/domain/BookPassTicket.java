package beingjavaguys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by InF on 05.06.2014.
 */
@Entity
   @Table(name = "book_passticket")
public class BookPassTicket {

    @Id
    @Column(name="id_book_passticket")
    private int passTicket_BookId;
    @Column(name="id_passticket")
    private int passticketId;
    @Column(name="id_book")
    private int bookId;
    @Column(name="datetime_operation")
    private Date datetimeStart;
    @Column(name="datetime_back")
    private Date datetimeEnd;

    public int getPassticketId() {
        return  passticketId;
    }

    public void setPassticketId(int  passticketId) {
        this. passticketId =  passticketId;
    }


    public int getPassTicket_BookId() {
        return  passTicket_BookId;
    }
    public void setPassTicket_BookId(int  passTicket_BookId) {
        this. passTicket_BookId =  passTicket_BookId;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getDatetimeStart() {
        return datetimeStart;
    }

    public void setDatetimeStart(Date datetimeStart) {
        this.datetimeStart = datetimeStart;

    }
    public Date getDatetimeEnd() {
        return datetimeEnd;
    }

    public void setDatetimeEnd(Date datetimeEnd) {
        this.datetimeEnd = datetimeEnd;
    }
}
