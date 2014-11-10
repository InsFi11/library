package beingjavaguys.domain;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by InF on 04.06.2014.
 */
import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book extends IPagination implements Serializable{


    @Id
    @Column(name="id_book")
    private int bookId;

    @Column(name="author")
    private String author;

    @Column(name="nameBook")
    private String bookName;

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

    @ManyToMany(
            fetch = FetchType.LAZY,
            targetEntity=Genre.class,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(name="book_genre",
            joinColumns={@JoinColumn(name="id_book")},
            inverseJoinColumns={@JoinColumn(name="id_genre")})
    private List<String> genreList = new ArrayList<String>();
    public List<String> getGenreList() {
        return genreList;
    }


    public void setGenreList(List<String> genreList) {
        this.genreList = genreList;
    }
//    @ManyToOne(
//            fetch = FetchType.LAZY,
//            targetEntity = Series.class,
//            cascade = {CascadeType.ALL}
//    )
//    @JoinTable(name="series_content",
//            joinColumns={@JoinColumn(name="id_book")},
//            inverseJoinColumns={@JoinColumn(name="id_series")})
//    private Series series;
//    public Series getSeriesList() {
//        return series;
//    }
}
