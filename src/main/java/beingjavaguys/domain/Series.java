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
@Table(name = "series")
public class Series extends IPagination{

    @Id
    @Column(name="id_series")
    private int seriesId;
    @Column(name="nameSeries")
    private String seriesName;
    @Column(name="preorder_price")
    private int preorderPrice;
    @Column(name="genre")
    private String genre;
    @Column(name="about")
    private String about;
    @Column(name="author")
    private String author;
    @Column(name="picture")
    private String picturePass;
    @Column(name="dateAdd")
    private Date dateAdd;

    @Override
    public int getId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    @Override
    public String getName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
    public int getPreorderPrice() {
        return preorderPrice;
    }

    public void setPreorderPrice(int preorderPrice) {
        this.preorderPrice = preorderPrice;
    }

    @Override
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public Date getDate() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }
    @Override
    public String getPicturePass() {
        return picturePass;
    }

    public void setPicturePass(String picturePass) {
        this.picturePass = picturePass;
    }
    @Override
    public String getPrice(){return  preorderPrice+"";}
}
