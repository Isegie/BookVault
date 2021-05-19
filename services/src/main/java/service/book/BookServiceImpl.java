package service.book;

import entity.book.Book;
import entity.book.BookCommand;
import entity.book.BookDTO;
import entity.dto.BookReviewDTO;
import mapper.MapStructMapperImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.book.BookJdbcRepository;
import repository.book.BookRepository;
import specification.book.BookSpecifications;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final MapStructMapperImplementation mapper;
    private final BookJdbcRepository bookJdbcRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, MapStructMapperImplementation mapper, BookJdbcRepository bookJdbcRepository) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
        this.bookJdbcRepository = bookJdbcRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(BookDTO bookDTO) {
        Book book = mapper.bookDtoToBook(bookDTO);
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book;
        } else {
            throw new EntityNotFoundException(String.format("Book with id=%d not found", id));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findByTitleOrLanguage(String value) {
        Specification<Book> bookSpecification = BookSpecifications.likeTitleOrLanguage(value);
        List<Book> books = bookRepository.findAll(bookSpecification);
        return books;
    }

    @Override
    public Optional<Book> findBookByISBN(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()) {
            return book;
        } else {
            throw new EntityNotFoundException(String.format("Book with ISBN=%d not found", isbn));
        }
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteByBookId(id);
    }

    @Override
    public Optional<BookDTO> update(Long id, BookCommand book) {
        return bookJdbcRepository.update(id, book).map(mapper::bookToDto);
    }

    @Override
    public List<BookReviewDTO> getBookReviews(Long id) {
        return bookRepository.findBookReviews(id);
    }

    @Override
    public List<Book> findBookByCategory(String category) {
        return bookRepository.findBooksByCategory_Name(category);
    }

}
