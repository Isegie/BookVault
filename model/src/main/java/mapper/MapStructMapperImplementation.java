package mapper;

import entity.book.Book;
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
}
