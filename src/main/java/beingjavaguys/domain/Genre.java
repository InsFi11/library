package beingjavaguys.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by InF on 07.11.2014.
 */
@Entity
@Table(name = "genre")
public class Genre implements Serializable {


    @Id
    @Column(name="id_genre")
    private int genreId;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            mappedBy = "genreList",
            targetEntity = Book.class
    )
    private List<Book> bookList;
    public List<Book> getBookList() {
        return bookList;
    }
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            mappedBy = "genreList",
            targetEntity = Series.class
    )
    private List<Series> seriesList;
    public List<Series> getSeriesList() {
        return seriesList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
   @Override
    public String toString(){
       return name;
   }
}
