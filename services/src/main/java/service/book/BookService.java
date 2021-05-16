package service.book;

import entity.book.Book;
import entity.book.BookDTO;

import java.util.List;
import java.util.Optional;


public interface BookService {
    List<Book> findAll();

    Book save(BookDTO book);

    Optional<Book> findBookById(Long id);

    List<Book> findByTitleOrLanguage(String value);

    Optional<Book> findBookByISBN(String isbn);
}
