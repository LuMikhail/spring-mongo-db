package homework8.repository;

import homework8.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryCustom {

    List<Comment> findAllByBookId(String bookId);
}
