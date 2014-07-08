package beingjavaguys.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by InF on 04.06.2014.
 */
@Entity
@Table(name = "passticket")

public class PassTicket{

    @Id
    @Column(name="id_passTicket")
    private int passticketId;
    @Column(name="id_user")
    private int userId;
    @Column(name="datatime_start")
    private Date datetimeStart;
    @Column(name="datetime_end")
    private Date datetimeEnd;
    @Column(name="isPayed")
    private int isPayed;

    public int getPassticketId() {
        return  passticketId;
    }

    public void setPassticketId(int  passticketId) {
        this. passticketId =  passticketId;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;

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
    public int getIsPayed() {
        return  isPayed;
    }

    public void setIsPayed(int  isPayed) {
        this. isPayed =  isPayed;
    }
}
