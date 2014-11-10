package beingjavaguys.services;

import beingjavaguys.dao.GenreDaoImpl;
import beingjavaguys.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl{

    @Autowired
    GenreDaoImpl genreDao;

    public Genre getGenre(String genreName) {
        return genreDao.getGenre(genreName);
    }

    public Genre findGenreFromNameFetchBook(String genreName) {return genreDao.findGenreFromNameFetchBook(genreName);}
    public Genre findGenreFromNameFetchSeries(String genreName) {return genreDao.findGenreFromNameFetchSeries(genreName);}



}