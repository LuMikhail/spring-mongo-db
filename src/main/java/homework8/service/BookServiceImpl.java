package homework8.service;

import homework8.exception.EntityNotFoundException;
import homework8.model.Author;
import homework8.model.Book;
import homework8.model.Comment;
import homework8.model.Genre;
import homework8.repository.AuthorRepository;
import homework8.repository.BookRepository;
import homework8.repository.CommentRepository;
import homework8.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    @Override
    public void insertBook(String title, String idAuthor, String idGenre, int amount) {
        Author author = authorRepository.findById(idAuthor)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + idAuthor));
        Genre genre = genreRepository.findById(idGenre)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + idGenre));
        bookRepository.save(new Book(title, author, genre, amount));
    }

    @Override
    public Book assignCommentsToBook(String bookId, String commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));
        comment.setBook(book);
        commentRepository.save(comment);
        book.getComments().add(comment);
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findBookById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBooksContainThisGenre(String genreId) {
        return bookRepository.findByGenre_Id(genreId);
    }

    @Override
    public List<Book> findBooksContainThisAuthor(String authorId) {
        return bookRepository.findByAuthor_Id(authorId);
    }

    @Override
    public void updateBookById(String bookId, String title, String authorId, String genreId, int amount) {
        bookRepository.findById(bookId).ifPresent(book -> {
            authorRepository.findById(authorId).ifPresent(book::setAuthor);
            genreRepository.findById(genreId).ifPresent(book::setGenre);
            book.setTitle(title);
            book.setAmount(amount);
            bookRepository.save(book);
        });
    }

    @Override
    public void deleteBookById(String id) {
        bookRepository.deleteById(id);
        commentRepository.removeAllByBookId(id);
    }
}
