package homework8.service;

import homework8.model.Genre;
import homework8.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.verify;

@SpringBootTest
class GenreServiceImplTest {

    @Configuration
    @Import(GenreServiceImpl.class)
    static class NestedTestConfiguration {
    }

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    GenreService genreService;

    @Test
    void calledCorrectlyInsertGenre() {
        Genre genre = new Genre("Драма");
        genreService.insertGenre("Драма");
        verify(genreRepository).save(genre);
    }

    @Test
    void calledCorrectlyFindAllAuthors() {
        genreService.findAllGenres();
        verify(genreRepository).findAll();
    }
}
