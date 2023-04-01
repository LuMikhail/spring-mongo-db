package homework8.service;

import homework8.model.Author;

import java.util.List;

public interface AuthorService {

    void insertAuthor(String name);

    List<Author> findAllAuthors();
}
