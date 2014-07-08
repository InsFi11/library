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
@Table(name = "book")
public class Book extends IPagination{

    @Id
    @Column(name="id_book")
    private int bookId;

    @Column(name="author")
    private String author;

    @Column(name="nameBook")
    private String bookName;

    @Column(name="genre")
    private String genre;

    @Column(name="amount")
    private int amount;

    @Column(name="wholesale_price")
    private int wholesalePrice;

    @Column(name="retail_price")
    private  int retailPrice;

    @Column(name="picture")
    private String picturePass;

    @Column(name="about")
    private String about;

    @Column(name="date_add")
    private Date dateAdd;


    @Column(name="isInCollection")
    private int isInCollection;


    @Override
    public int getId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String getName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setWholesalePrice(int wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }
    public int getWholesalePrice() {
        return wholesalePrice;
    }
    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Override
    public String getPicturePass() {
        return picturePass;
    }

    public void setPicturePass(String picturePass) {
        this.picturePass = picturePass;
    }
    @Override
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public Date getDate() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    @Override
    public int getIsInCollection() {
        return isInCollection;
    }
    @Override
    public void setIsInCollection(int isInCollection) {
        this.isInCollection = isInCollection;
    }
    @Override
    public String getPrice(){return  retailPrice+"";}

}
