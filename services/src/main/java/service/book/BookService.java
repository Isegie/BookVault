package service.book;

import entity.book.Book;
import entity.book.BookCommand;
import entity.book.BookDTO;
import entity.dto.BookReviewDTO;

import java.util.List;
import java.util.Optional;


public interface BookService {
    List<Book> findAll();

    Book save(BookDTO book);

    Optional<Book> findBookById(Long id);

    List<Book> findByTitleOrLanguage(String value);

    Optional<Book> findBookByISBN(String isbn);

    void deleteById(Long id);

    Optional<BookDTO> update(Long id, BookCommand book);

    List<BookReviewDTO> getBookReviews(Long id);
}
