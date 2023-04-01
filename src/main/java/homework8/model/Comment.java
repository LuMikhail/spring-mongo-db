package homework8.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String comment;

    @DBRef
    private Book book;

    public Comment(String comment, Book book) {
        this.comment = comment;
        this.book = book;
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return String.format("Comment(id=%s ,comment=%s ,book=%s)",
                this.id,
                this.comment,
                this.book);
    }
}
