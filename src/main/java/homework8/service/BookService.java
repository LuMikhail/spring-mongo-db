package homework8.service;

import homework8.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void insertBook(String title, String idAuthor, String idGenre, int amount);

    Book assignCommentsToBook(String bookId, String commentId);

    Optional<Book> findBookById(String id);

    List<Book> findAllBooks();

    List<Book> findBooksContainThisGenre(String genre);

    List<Book> findBooksContainThisAuthor(String author);

    void updateBookById(String bookId, String title, String authorId, String genreId, int amount);

    void deleteBookById(String id);
}
