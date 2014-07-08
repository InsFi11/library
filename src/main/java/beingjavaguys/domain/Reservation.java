package beingjavaguys.domain;

import java.sql.Date;

/**
 * Created by InF on 04.06.2014.
 */
public class Reservation{

    private int reservationId;
    private int bookId;
    private int userId;
    private Date datetimeOperation;
    private Date datetimeEndOfReservation;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;

    }
    public Date getDatetimeOperation() {
        return datetimeOperation;
    }

    public void setDatetimeOperation(Date datetimeOperation) {
        this.datetimeOperation = datetimeOperation;

    }
    public Date getDatetimeEndOfReservation() {
        return datetimeEndOfReservation;
    }

    public void setDatetimeEndOfReservation(Date datetimeEndOfReservation) {
        this.datetimeEndOfReservation = datetimeEndOfReservation;

    }


}
