package homework8.repository;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import homework8.model.Author;
import homework8.model.Book;
import homework8.model.Genre;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookRepositoryTest {

    private static final int BOOK_COUNT = 4;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @Order(1)
    void shouldFindAllBooksByAuthor() {
        String authorId = mongoOperations.findOne(query(where("name").is("Антонио Гарридо")), Author.class).getId();
        List<Book> booksByAuthor = bookRepository.findByAuthor_Id(authorId);
        assertThat(booksByAuthor)
                .hasSize(1)
                .allMatch(book -> book.getAuthor().getId().equals(authorId));
    }

    @Test
    @Order(2)
    void shouldFindAllBooksByGenre() {
        String genreId = mongoOperations.findOne(query(where("name").is("Программирование")), Genre.class).getId();
        List<Book> booksByGenre = bookRepository.findByGenre_Id(genreId);
        assertThat(booksByGenre)
                .hasSize(2)
                .allMatch(book -> book.getGenre().getId().equals(genreId));
    }

    @Test
    @Order(3)
    void shouldReturnBookById() {
        Book expectedBook = bookRepository.findByTitle("Читающий по телам");
        String expectedBookId = expectedBook.getId();
        Optional<Book> actualBook = bookRepository.findById(expectedBookId);
        assertAll(
                () -> assertTrue(actualBook.isPresent(), "Book not found"),
                () -> {
                    Book book = actualBook.get();
                    assertEquals(expectedBookId, book.getId(), "Book id doesn't match expected id");
                    assertEquals(expectedBook.getTitle(), book.getTitle(), "Book title doesn't match expected title");
                }
        );
    }

    @Test
    void shouldFindAllBook() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(BOOK_COUNT);
    }

    @Test
    void shouldAddBook() {
        Book expectedBook = new Book("Java ....",
                mongoOperations.findOne(query(where("name").is("Джон Лонг")), Author.class),
                mongoOperations.findOne(query(where("name").is("Программирование")), Genre.class),
                45);
        Book actualBook = bookRepository.save(expectedBook);
        assertEquals(expectedBook, actualBook, "Saved book is not equal to expected book");
    }

    @Test
    void shouldUpdateBook() {
        Book expectedBook = mongoOperations.findOne(query(where("title").is("Читающий по телам")), Book.class);
        assert expectedBook != null;
        expectedBook.setTitle("Новое название книги");
        Book updatedBook = bookRepository.save(expectedBook);
        assertEquals(expectedBook, updatedBook, "Updated book is not equal to expected book");
    }

    @Test
    void shouldDeleteBookById() {
        String book = Objects.requireNonNull(mongoOperations.findOne(query(where("title").is("Над осевшими могилами")), Book.class)).getId();
        bookRepository.deleteById(book);
        List<Book> bookList = mongoOperations.findAll(Book.class);
        assertAll(() -> assertThat(bookList).hasSize(BOOK_COUNT - 1),
                () -> assertThat(bookList.stream()).noneMatch(b -> b.getId().equals(book)));
    }
}