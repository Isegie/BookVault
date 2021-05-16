package com.bookvault.bookvault.web.book;

import com.bookvault.bookvault.configuration.link.BookModelAssembler;
import entity.book.Book;
import entity.book.BookCommand;
import entity.book.BookDTO;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.book.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "book", produces = "application/hal+json")
public class BookController {

    private final BookService bookService;
    private final MapStructMapper mapper;
    private final BookModelAssembler bookModelAssembler;

    @Autowired
    public BookController(BookService bookService, MapStructMapper mapper, BookModelAssembler bookModelAssembler) {
        this.bookService = bookService;
        this.mapper = mapper;
        this.bookModelAssembler = bookModelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<BookDTO>> findAll() {
        List<Book> books = bookService.findAll();
        return new ResponseEntity<>(bookModelAssembler.toCollectionModel(books), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody @Valid BookCommand bookCommand) {

        BookDTO bookDTO = mapper.bookCommandToDto(bookCommand);

        Book createdBook = bookService.save(bookDTO);

        Optional<Book> book = Optional.ofNullable(createdBook);

        return book.map(bookModelAssembler::toModel).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        return bookService.findBookById(id).map(bookModelAssembler::toModel)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{prop}")
    public EntityModel<ResponseEntity<List<BookDTO>>> findByProperty(@RequestParam(value = "prop") String prop) {

        List<BookDTO> books = bookService.findByTitleOrLanguage(prop)
                .stream().map(mapper::bookToDto).collect(Collectors.toList());

        ResponseEntity<List<BookDTO>> bookResponse = ResponseEntity.ok().body(books);

        EntityModel<ResponseEntity<List<BookDTO>>> bookDTOEntityModel = EntityModel.of(bookResponse);

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookController.class).findAll());

        bookDTOEntityModel.add(linkBuilder.withRel("books"));

        return bookDTOEntityModel;
    }

    @GetMapping("/isbn/{isbn}")
    public EntityModel<ResponseEntity<BookDTO>> findByISBN(@RequestParam(value = "isbn") String isbn) {

        Optional<Book> book = bookService.findBookByISBN(isbn);

        ResponseEntity<BookDTO> bookResponse = ResponseEntity.of(Optional.ofNullable(mapper.bookToDto(book.get())));

        EntityModel<ResponseEntity<BookDTO>> bookDTOEntityModel = EntityModel.of(bookResponse);


        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookController.class).findAll());

        bookDTOEntityModel.add(linkBuilder.withRel("books"));

        return bookDTOEntityModel;
    }
}
