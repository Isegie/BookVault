package repository.book;

import entity.book.Book;
import entity.book.BookCommand;

import java.util.Optional;

public interface BookJdbcRepository {
    Optional<Book> update(Long id, BookCommand book);
}
