package homework8.service;

import homework8.model.Author;
import homework8.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.verify;

@SpringBootTest
class AuthorServiceImplTest {

    @Configuration
    @Import(AuthorServiceImpl.class)
    static class NestedTestConfiguration {
    }

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    AuthorService authorService;

    @Test
    void calledCorrectlyInsertAuthor() {
        Author author = new Author("Джош Блох");
        authorService.insertAuthor("Джош Блох");
        verify(authorRepository).save(author);
    }

    @Test
    void calledCorrectlyFindAllAuthors() {
        authorService.findAllAuthors();
        verify(authorRepository).findAll();
    }
}