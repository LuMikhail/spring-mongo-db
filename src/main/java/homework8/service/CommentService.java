package homework8.service;

import homework8.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void insertComment(String comment, String idBook);

    List<Comment> findCommentsByBookId(String id);

    Optional<Comment> findCommentById(String id);

    void updateCommentById(String id, String comment);

    void deleteCommentById(String id);
}
