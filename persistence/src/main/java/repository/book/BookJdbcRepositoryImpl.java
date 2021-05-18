package repository.book;

import entity.book.Book;
import entity.book.BookCommand;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookJdbcRepositoryImpl implements BookJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;
    private final MapStructMapper mapper;

    @Autowired
    public BookJdbcRepositoryImpl(JdbcTemplate jdbcTemplate, MapStructMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("book").usingGeneratedKeyColumns("book_id");
        this.mapper = mapper;
    }

    @Override
    public Optional<Book> update(Long id, BookCommand book) {

        int status = jdbcTemplate
                .update("update book set description=?,number_of_pages=?,discount=?,edition=?,rating=?,format=?,price=?",
                        book.getDescription(), book.getNumberOfPages(), book.getDiscount(), book.getEdition(), book.getRating(), book.getFormat(), book.getPrice());

        if (status != 0) {
            return Optional.ofNullable(mapper.bookCommandToBook(book));
        } else {
            return Optional.empty();
        }
    }
}
