package mapper;

import entity.book.Book;
import entity.book.BookCommand;
import entity.book.BookDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class MapStructMapperImplementation implements MapStructMapper {
    @Override
    public BookDTO bookToDto(Book book) {
        if (book == null) {
            return null;
        }
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setNumberOfPages(book.getNumberOfPages());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setDiscount(book.getDiscount());
        bookDTO.setPublicationDate(book.getPublicationDate());
        bookDTO.setEdition(book.getEdition());
        bookDTO.setCity(book.getCity());
        bookDTO.setCountry(book.getCountry());
        bookDTO.setRating(book.getRating());
        bookDTO.setFormat(book.getFormat());
        bookDTO.setLanguage(bookDTO.getLanguage());
        bookDTO.setPrice(book.getPrice());
        return bookDTO;
    }

    @Override
    public Book bookDtoToBook(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setNumberOfPages(bookDTO.getNumberOfPages());
        book.setIsbn(bookDTO.getIsbn());
        book.setDiscount(bookDTO.getDiscount());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setEdition(bookDTO.getEdition());
        book.setCity(bookDTO.getCity());
        book.setCountry(bookDTO.getCountry());
        book.setRating(bookDTO.getRating());
        book.setFormat(bookDTO.getFormat());
        book.setLanguage(bookDTO.getLanguage());
        book.setPrice(bookDTO.getPrice());
        return book;
    }

    @Override
    public BookDTO bookCommandToDto(BookCommand bookCommand) {
        if (bookCommand == null) {
            return null;
        }
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(bookCommand.getTitle());
        bookDTO.setDescription(bookCommand.getDescription());
        bookDTO.setNumberOfPages(bookCommand.getNumberOfPages());
        bookDTO.setIsbn(bookCommand.getIsbn());
        bookDTO.setDiscount(bookCommand.getDiscount());
        bookDTO.setPublicationDate(bookCommand.getPublicationDate());
        bookDTO.setEdition(bookCommand.getEdition());
        bookDTO.setCity(bookCommand.getCity());
        bookDTO.setCountry(bookCommand.getCountry());
        bookDTO.setRating(bookCommand.getRating());
        bookDTO.setFormat(bookCommand.getFormat());
        bookDTO.setLanguage(bookCommand.getLanguage());
        bookDTO.setPrice(bookCommand.getPrice());
        return bookDTO;
    }
}
