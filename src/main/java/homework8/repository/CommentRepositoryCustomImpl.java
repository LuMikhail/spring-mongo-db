package homework8.repository;

import homework8.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void removeAllByBookId(String bookId) {
        Query query = Query.query(Criteria.where("bookId").is(bookId));
        mongoTemplate.findAllAndRemove(query, Comment.class);
    }
}
