package mapper;

import entity.author.Author;
import entity.author.AuthorCommand;
import entity.author.AuthorDTO;
import entity.book.Book;
import entity.book.BookCommand;
import entity.book.BookDTO;
import entity.order.Order;
import entity.order.OrderDTO;
import entity.publisher.Publisher;
import entity.publisher.PublisherDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
        bookDTO.setAuthors(book.getAuthors().stream().map(this::authorToDto).collect(Collectors.toSet()));
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setCategory(book.getCategory());
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

    @Override
    public Book bookCommandToBook(BookCommand bookCommand) {

        Book book = new Book();
        book.setTitle(bookCommand.getTitle());
        book.setDescription(bookCommand.getDescription());
        book.setNumberOfPages(bookCommand.getNumberOfPages());
        book.setIsbn(bookCommand.getIsbn());
        book.setDiscount(bookCommand.getDiscount());
        book.setPublicationDate(bookCommand.getPublicationDate());
        book.setEdition(bookCommand.getEdition());
        book.setCity(bookCommand.getCity());
        book.setCountry(bookCommand.getCountry());
        book.setRating(bookCommand.getRating());
        book.setFormat(bookCommand.getFormat());
        book.setLanguage(bookCommand.getLanguage());
        book.setPrice(bookCommand.getPrice());

        return book;
    }

    @Override
    public AuthorDTO authorToDto(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setEmail(author.getEmail());
        return authorDTO;
    }

    @Override
    public Author authorDtoToAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setEmail(authorDTO.getEmail());
        return author;
    }

    @Override
    public AuthorDTO authorCommandToDto(AuthorCommand authorCommand) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setFirstName(authorCommand.getFirstName());
        authorDTO.setLastName(authorCommand.getLastName());
        authorDTO.setEmail(authorDTO.getEmail());
        return authorDTO;
    }

    @Override
    public PublisherDTO publisherToDTO(Publisher publisher) {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setName(publisher.getName());
        publisherDTO.setAddress(publisher.getAddress());
        publisherDTO.setPhoneNumber(publisher.getPhoneNumber());
        publisherDTO.setEmail(publisher.getEmail());
        return publisherDTO;
    }

    @Override
    public OrderDTO orderToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderProcessed(order.isOrderProcessed());
        orderDTO.setOrderStatusSent(order.isOrderStatusSent());
        orderDTO.setBooks(order.getBooks());
        return orderDTO;
    }
}
