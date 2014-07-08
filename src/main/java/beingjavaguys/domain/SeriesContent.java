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
@Table(name = "series_content")
public class SeriesContent {

    @Id
    @Column(name="id_series_content")
    private int seriesContentId;
    @Column(name="id_series")
    private int seriesId;
    @Column(name="id_book")
    private int bookId;
    @Column(name="datetime_operation")
    private Date datetimeOperation;

    public int getSeriesContentId() {
        return seriesContentId;
    }

    public void setSeriesContentIdd(int seriesContentId) {
        this.seriesContentId = seriesContentId;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public Date getDatetimeOperation() {
        return datetimeOperation;
    }

    public void setDatetimeOperation(Date datetimeOperation) {
        this.datetimeOperation = datetimeOperation;

    }

}
