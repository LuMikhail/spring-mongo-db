package homework8.repository;

import homework8.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByGenre_Id(String genreId);

    List<Book> findByAuthor_Id(String authorId);

    Book findByTitle(String title);

}
