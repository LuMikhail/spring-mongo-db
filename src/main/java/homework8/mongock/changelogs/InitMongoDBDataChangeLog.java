package homework8.mongock.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import homework8.model.Author;
import homework8.model.Book;
import homework8.model.Comment;
import homework8.model.Genre;
import homework8.repository.AuthorRepository;
import homework8.repository.BookRepository;
import homework8.repository.CommentRepository;
import homework8.repository.GenreRepository;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private Author craigWalls;
    private Author johnLong;
    private Author antonioGarrido;
    private Author jessWalter;

    private Genre programming;
    private Genre detective;

    private Book overTumbledGraves;
    private Book bodyReader;
    private Book springInAction;
    private Book javaCloud;

    @ChangeSet(order = "000", id = "dropDB", author = "luzhkowskii", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "luzhkowskii", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        craigWalls = repository.save(new Author("Крейг Уоллс"));
        johnLong = repository.save(new Author("Джон Лонг"));
        antonioGarrido = repository.save(new Author("Антонио Гарридо"));
        jessWalter = repository.save(new Author("Джесс Уолтер"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "luzhkowskii", runAlways = true)
    public void initAuthors(GenreRepository repository) {
        programming = repository.save(new Genre("Программирование"));
        detective = repository.save(new Genre("Детектив"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "luzhkowskii", runAlways = true)
    public void initBooks(BookRepository repository) {
        overTumbledGraves = repository.save(new Book("Над осевшими могилами", jessWalter, detective, 3));
        bodyReader = repository.save(new Book("Читающий по телам", antonioGarrido, detective, 2));
        springInAction = repository.save(new Book("Spring в действии", craigWalls, programming, 4));
        javaCloud = repository.save(new Book("Java в облаке", johnLong, programming, 2));
    }

    @ChangeSet(order = "004", id = "initComments", author = "luzhkowskii", runAlways = true)
    public void initBooks(CommentRepository repository) {
        Comment comment1 = repository.save(new Comment("Не стоит читать", overTumbledGraves));
        Comment comment2 = repository.save(new Comment("Понравилось", overTumbledGraves));
        Comment comment3 = repository.save(new Comment("Советую друзьям", bodyReader));
        Comment comment4 = repository.save(new Comment("Подходит на подарок", bodyReader));
        Comment comment5 = repository.save(new Comment("Узнал много нового", springInAction));
        Comment comment6 = repository.save(new Comment("Нужно перечитать", springInAction));
        Comment comment7 = repository.save(new Comment("Стоит читать", javaCloud));
    }
}
