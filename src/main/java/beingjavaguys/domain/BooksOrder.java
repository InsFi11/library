package beingjavaguys.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by InF on 04.06.2014.
 */
@Entity
@Table(name = "books_order")
public class BooksOrder {

    @Id
    @Column(name="id_books_order")
    private int booksOrderId;
    @Column(name="id_user")
    private int userId;
    @Column(name="id_book")
    private int bookId;
    @Column(name="amount")
    private int amount;
    @Column(name="price")
    private int price;
    @Column(name="datetime_operation")
    private Date datetimeOperation;
    @Column(name="isDone")
    private int isDone;
    @Transient
    private String bookName;
    @Transient
    private String bookAuthor;

    public int getBooksOrderId() {
        return booksOrderId;
    }

    public void setBooksOrderId(int booksOrderId) {
        this.booksOrderId = booksOrderId;
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
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
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
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}
