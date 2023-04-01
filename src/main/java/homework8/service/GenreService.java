package homework8.service;

import homework8.model.Genre;

import java.util.List;

public interface GenreService {

    void insertGenre(String genre);

    List<Genre> findAllGenres();

}
