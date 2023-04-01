package homework8.service;

import homework8.exception.EntityNotFoundException;
import homework8.model.Book;
import homework8.model.Comment;
import homework8.repository.BookRepository;
import homework8.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public void insertComment(String comment, String idBook) {
        Book book = bookRepository.findById(idBook)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + idBook));
        Comment newComment = new Comment(comment, book);
        commentRepository.save(newComment);
        book.getComments().add(newComment);
        bookRepository.save(book);
    }

    @Override
    public Optional<Comment> findCommentById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findCommentsByBookId(String bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));
        return book.getComments();
    }

    @Override
    public void updateCommentById(String id, String comment) {
        commentRepository.findById(id).ifPresent(updateComment -> {
            updateComment.setComment(comment);
            commentRepository.save(updateComment);
        });
    }

    @Override
    public void deleteCommentById(String commentId) {
        Comment commentToDelete = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id " + commentId));
        String bookId = commentToDelete.getBook().getId();
        Book bookToUpdate = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + bookId));
        bookToUpdate.getComments().removeIf(comment -> comment.getId().equals(commentId));
        bookRepository.save(bookToUpdate);
        commentRepository.delete(commentToDelete);
    }
}
