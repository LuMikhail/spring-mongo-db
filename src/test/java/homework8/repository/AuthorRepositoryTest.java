package homework8.repository;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import homework8.model.Author;
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
class AuthorRepositoryTest {

    private static final int AUTHORS_COUNT = 4;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    @Order(2)
    public void shouldAddAuthor() {
        Author author = authorRepository.save(new Author("Джош Блох"));
        assertThat(authorRepository.findAll()).hasSize(AUTHORS_COUNT + 1);
    }

    @Test
    @Order(1)
    public void shouldFindAllAuthor() {
        List<Author> allAuthors = authorRepository.findAll();
        assertThat(allAuthors).hasSize(AUTHORS_COUNT);
    }
}