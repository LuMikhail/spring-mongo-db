package homework8.service;

import homework8.model.Author;
import homework8.model.Book;
import homework8.model.Genre;
import homework8.repository.AuthorRepository;
import homework8.repository.BookRepository;
import homework8.repository.CommentRepository;
import homework8.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class BookServiceImplTest {

    @Configuration
    @Import({BookServiceImpl.class})
    static class NestedTestConfiguration {
    }

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void calledCorrectlyMethodInsertBook() {
        Author expectedAuthor = new Author("1", "Author");
        Genre expectedGenre = new Genre("2", "Genre");

        // Mock the AuthorRepository to return the expectedAuthor when findById is called with the ID "1"
        when(authorRepository.findById("1")).thenReturn(Optional.of(expectedAuthor));
        when(genreRepository.findById("2")).thenReturn(Optional.of(expectedGenre));

        // Call the insertBook method
        bookService.insertBook("Скриба", "1", "2", 3);

        // Use ArgumentCaptor to capture the argument passed to bookRepository.save()
        ArgumentCaptor<Book> argument = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(argument.capture());

        // Compare the captured argument with the expected book
        assertEquals("Скриба", argument.getValue().getTitle());
        assertEquals(expectedAuthor, argument.getValue().getAuthor());
        assertEquals(expectedGenre, argument.getValue().getGenre());
        assertEquals(3, argument.getValue().getAmount());
    }

    @Test
    void calledCorrectlyMethodFindBookById() {
        bookService.findBookById("1");
        verify(bookRepository).findById("1");
    }

    @Test
    void calledCorrectlyMethodFindAllBooks() {
        bookService.findAllBooks();
        verify(bookRepository).findAll();
    }

    @Test
    void calledCorrectlyMethodFindBooksContainThisGenre() {
        Genre genre = new Genre("2", "Genre");
        bookService.findBooksContainThisGenre("2");
        verify((bookRepository)).findByGenre_Id(genre.getId());
    }

    @Test
    void calledCorrectlyMethodFindBooksContainThisAuthor() {
        Author author = new Author("1", "Author");
        bookService.findBooksContainThisAuthor("1");
        verify(bookRepository).findByAuthor_Id(author.getId());
    }


    @Test
    void calledCorrectlyMethodDeleteBooksById() {
        bookService.deleteBookById("1");
        verify(bookRepository).deleteById("1");
    }
}