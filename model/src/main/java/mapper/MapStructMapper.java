package mapper;

import entity.book.Book;
import entity.book.BookCommand;
import entity.book.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    BookDTO bookToDto(Book book);

    Book bookDtoToBook(BookDTO bookDTO);

    BookDTO bookCommandToDto(BookCommand bookCommand);
}
