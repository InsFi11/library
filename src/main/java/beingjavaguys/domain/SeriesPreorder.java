package beingjavaguys.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by InF on 04.06.2014.
 */
@Entity
@Table(name = "series_preorder")
public class SeriesPreorder{

    @Id
    @Column(name="id_series_preorder")
    private int seriesPreorderId;
    @Column(name="id_series")
    private int seriesId;
    @Column(name="id_user")
    private int userId;
    @Column(name="monthAmount")
    private int monthAmount;
    @Column(name="datetime_operation")
    private Date datetimeOperation;
    @Column(name="isDone")
    private int isDone;
    @Transient
    private String seriesName;
    @Transient
    private String seriesAuthor;
    @Transient
    private int seriesPrice;

    public int getSeriesPreorderId() {
        return seriesPreorderId;
    }

    public void setSeriesPreorderId(int seriesPreorderId) {
        this.seriesPreorderId = seriesPreorderId;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }
    public int getMonthAmount() {
        return monthAmount;
    }
    public void setMonthAmount(int price) {
        this.monthAmount = monthAmount;
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
    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }
    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
    public String getSeriesAuthor() {
        return seriesAuthor;
    }

    public void setSeriesAuthor(String seriesAuthor) {
        this.seriesAuthor = seriesAuthor;
    }
    public int getPrice() {
        return seriesPrice;
    }

    public void setPrice(int seriesPrice) {
        this.seriesPrice = seriesPrice;
    }
}
