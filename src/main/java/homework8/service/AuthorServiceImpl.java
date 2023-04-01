package homework8.service;

import homework8.model.Author;
import homework8.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    @Override
    public void insertAuthor(String name) {
        authorRepository.save(new Author(name));
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
}
