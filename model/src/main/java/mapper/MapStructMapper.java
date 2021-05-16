package mapper;

import entity.author.Author;
import entity.author.AuthorDTO;
import entity.book.Book;
import entity.book.BookCommand;
import entity.book.BookDTO;
import entity.publisher.Publisher;
import entity.publisher.PublisherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    BookDTO bookToDto(Book book);

    Book bookDtoToBook(BookDTO bookDTO);

    BookDTO bookCommandToDto(BookCommand bookCommand);

    AuthorDTO authorToDto(Author author);

    Author authorDtoToAuthor(AuthorDTO authorDTO);

    PublisherDTO publisherToDTO(Publisher publisher);
}
