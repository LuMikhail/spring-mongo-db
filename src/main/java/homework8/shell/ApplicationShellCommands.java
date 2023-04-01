package homework8.shell;

import homework8.model.Comment;
import homework8.service.AuthorService;
import homework8.service.BookService;
import homework8.service.CommentService;
import homework8.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final CommentService commentService;


    @ShellMethod(value = "Insert author", key = {"insert-author", "IA"})
    public void insertAuthor(@ShellOption String author) {
        authorService.insertAuthor(author);
    }

    @ShellMethod(value = "Find all authors", key = {"find-all-authors", "AA"})
    public void findAllAuthors() {
        authorService.findAllAuthors().forEach(author ->
                System.out.printf("%s , автор: %s\n",
                        author.getId(), author.getName()));
    }

    @ShellMethod(value = "Insert genre", key = {"insert-genre", "IG"})
    public void insertGenre(@ShellOption String genre) {
        genreService.insertGenre(genre);
    }


    @ShellMethod(value = "Find all genres", key = {"find-all-genres", "AG"})
    public void findAllGenres() {
        genreService.findAllGenres().forEach(genre ->
                System.out.printf("%s , жанр: %s\n",
                        genre.getId(), genre.getName()));
    }

    @ShellMethod(value = "Insert book", key = {"insert-book", "IB"})
    public void insertBook(@ShellOption String title,
                           @ShellOption String idAuthor,
                           @ShellOption String idGenre,
                           @ShellOption int amount) {
        bookService.insertBook(title, idAuthor, idGenre, amount);
    }

    @ShellMethod(value = "Find all books", key = {"find-all-books", "AB"})
    public void findAllBooks() {
        bookService.findAllBooks().forEach(book ->
                System.out.printf("%s название: %s, автор: %s, жанр: %s, количество: %s %s\n",
                        book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getAmount(), book.getComments()));

    }

    @ShellMethod(value = "Find book by author", key = {"find-books-by-author", "A"})
    public void findBookByAuthor(@ShellOption String authorId) {
        bookService.findBooksContainThisAuthor(authorId).forEach(book ->
                System.out.printf("%s название: %s, автор: %s, жанр: %s, количество: %s\n",
                        book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName(), book.getAmount()));
    }

    @ShellMethod(value = "Find book by genre", key = {"find-books-by-genre", "G"})
    public void findBookByGenre(@ShellOption String genreId) {
        bookService.findBooksContainThisGenre(genreId).forEach(book ->
                System.out.printf("%s название: %s, автор: %s, жанр: %s, количество: %s\n",
                        book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName(), book.getAmount()));
    }

    @ShellMethod(value = "Find book by id", key = {"find-book-by-id", "B"})
    public void findBookById(@ShellOption String bookId) {
        bookService.findBookById(bookId).ifPresent(book ->
                System.out.printf("%s название: %s, автор: %s, жанр: %s, количество: %s\n",
                        book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName(), book.getAmount()));
    }

    @ShellMethod(value = "Assign-comment-to-book", key = {"assign-comment-to-book", "ACB"})
    public void assignCommentByBook(@ShellOption String bookId,
                                    @ShellOption String commentId) {
        bookService.assignCommentsToBook(bookId, commentId);
    }

    @ShellMethod(value = "Update book by id", key = {"update-book", "UB"})
    public void updateBookById(@ShellOption String bookId,
                               @ShellOption String title,
                               @ShellOption String authorId,
                               @ShellOption String genreId,
                               @ShellOption int amount) {
        bookService.updateBookById(bookId, title, authorId, genreId, amount);
    }

    @ShellMethod(value = "Delete book by id", key = {"delete-book", "DB"})
    public void deleteBookById(@ShellOption String id) {
        bookService.deleteBookById(id);
    }

    @ShellMethod(value = "Insert comment", key = {"insert-comment", "IC"})
    public void insertComment(@ShellOption String comment, String bookId) {
        commentService.insertComment(comment, bookId);
    }

    @ShellMethod(value = "Find comment by id", key = {"find-comment-by-id", "C"})
    public void findCommentById(@ShellOption String commentId) {
        commentService.findCommentById(commentId).ifPresent(comment ->
                System.out.printf("%s комментарий: %s, относится к книге %s\n",
                        comment.getId(), comment.getComment(), comment.getBook().getTitle()));
    }

    @ShellMethod(value = "Find comments by book id", key = {"find-comments-by-book-id", "CB"})
    public void findCommentBookById(@ShellOption String bookId) {
        List<Comment> comments =commentService.findCommentsByBookId(bookId);
        comments.forEach(System.out::println);
    }


    @ShellMethod(value = "Update comment by id", key = {"update-comment", "UC"})
    public void updateCommentById(@ShellOption String id,
                                  @ShellOption String titleComment) {
        commentService.updateCommentById(id, titleComment);
    }

    @ShellMethod(value = "Delete comment by id", key = {"delete-comment", "DC"})
    public void deleteCommentById(@ShellOption String id) {
        commentService.deleteCommentById(id);
    }
}
