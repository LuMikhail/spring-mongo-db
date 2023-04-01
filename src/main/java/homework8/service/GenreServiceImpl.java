package homework8.service;

import homework8.model.Genre;
import homework8.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public void insertGenre(String genre) {
        genreRepository.save(new Genre(genre));
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }
}
