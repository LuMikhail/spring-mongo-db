package homework8.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
public class Author {
    @Id
    private String id;

    private String name;

    public Author(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Author(id=%s ,name=%s)",
                this.id,
                this.name);
    }
}
