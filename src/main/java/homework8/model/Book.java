package homework8.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String title;

    @DBRef
    private Author author;

    @DBRef
    private Genre genre;

    private int amount;

    @DBRef(lazy = true)
    private List<Comment> comments = new ArrayList<>();

    public Book(String title, Author author, Genre genre, int amount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.amount = amount;
    }

    public Book(String title, Author author, Genre genre, int amount, Comment... comments) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.amount = amount;
        this.comments = Arrays.asList(comments);
    }

    @Override
    public String toString() {
        return String.format("Book(id=%s ,title=%s ,author=%s ,genre=%s ,amount=%s)",
                this.id,
                this.title,
                this.author,
                this.genre,
                this.amount);
    }
}

