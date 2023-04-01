package homework8.repository;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import homework8.model.Book;
import homework8.model.Comment;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentRepositoryTest {

    private static final int COMMENTS_COUNT = 7;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    @Order(2)
    public void shouldAddComment() {
        Comment comment = commentRepository.save(new Comment("Новый комментарий"));
        assertThat(commentRepository.findAll()).hasSize(COMMENTS_COUNT + 1);
    }

    @Test
    void shouldDeleteBookAndAllCommentsByBookID() {
        Book book = bookRepository.findByTitle("Над осевшими могилами");
        String bookId = book.getId();
        List<Comment> commentsBeforeDelete = commentRepository.findAllByBookId(bookId);
        commentsBeforeDelete.forEach(System.out::println);
        commentRepository.removeAllByBookId(bookId);
        List<Comment> actualComments = commentRepository.findAllByBookId(bookId);
        assertThat(commentsBeforeDelete).isNotEmpty();
        assertThat(actualComments).isEmpty();
    }

    @Test
    @Order(1)
    public void shouldFindAllAuthor() {
        List<Comment> allComments = commentRepository.findAll();
        assertThat(allComments).hasSize(COMMENTS_COUNT);
    }
}
