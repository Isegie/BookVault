package com.bookvault.bookvault.configuration.link;

import com.bookvault.bookvault.web.author.AuthorController;
import com.bookvault.bookvault.web.book.BookController;
import entity.author.Author;
import entity.author.AuthorDTO;
import entity.book.Book;
import entity.book.BookDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookModelAssembler extends RepresentationModelAssemblerSupport<Book, BookDTO> {

    public BookModelAssembler() {
        super(BookController.class, BookDTO.class);
    }

    @Override
    public BookDTO toModel(Book entity) {

        BookDTO bookDTO = instantiateModel(entity);

        bookDTO.add(linkTo(methodOn(BookController.class)
                .findById(entity.getBookId())).withSelfRel());

        bookDTO.setTitle(entity.getTitle());
        bookDTO.setLanguage(entity.getLanguage());
        bookDTO.setRating(entity.getRating());
        bookDTO.setPublicationDate(entity.getPublicationDate());
        bookDTO.setPrice(entity.getPrice());
        bookDTO.setNumberOfPages(entity.getNumberOfPages());
        bookDTO.setIsbn(entity.getIsbn());
        bookDTO.setFormat(entity.getFormat());
        bookDTO.setEdition(entity.getEdition());
        bookDTO.setDescription(entity.getDescription());
        bookDTO.setCountry(entity.getCountry());
        bookDTO.setDiscount(entity.getDiscount());
        bookDTO.setCity(entity.getCity());
        bookDTO.setAuthors(convertAuthorEntityToAuthorDTO(entity.getAuthors()));
        bookDTO.setPublisher(entity.getPublisher());
        bookDTO.setCategory(entity.getCategory());

        return bookDTO;
    }

    @Override
    public CollectionModel<BookDTO> toCollectionModel(Iterable<? extends Book> books) {

        CollectionModel<BookDTO> bookDTOS = super.toCollectionModel(books);

        bookDTOS.add(linkTo(methodOn(BookController.class).findAll()).withSelfRel());

        return bookDTOS;
    }

    protected Set<AuthorDTO> convertAuthorEntityToAuthorDTO(Set<Author> authors) {

        if (authors.isEmpty())
            return Collections.EMPTY_SET;

        return authors.stream().map(author -> new AuthorDTO(
                author.getFirstName(),
                author.getLastName(),
                author.getEmail())
                .add(linkTo(methodOn(AuthorController.class)
                        .findAuthorById(author.getId()))
                        .withSelfRel()))
                .collect(Collectors.toSet());
    }

}
