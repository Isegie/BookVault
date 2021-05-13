package com.bookvault.bookvault.web.book;

import entity.book.Book;
import entity.book.BookCommand;
import entity.book.BookDTO;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.book.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookService bookService;
    private final MapStructMapper mapper;

    @Autowired
    public BookController(BookService bookService, MapStructMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> books = bookService.findAll().stream()
                .map(mapper::bookToDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(books);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody @Valid BookCommand bookCommand) {

        BookDTO bookDTO = mapper.bookCommandToDto(bookCommand);

        Book createdBook = bookService.save(bookDTO);

        if (createdBook == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(createdBook);
        }
    }

    @GetMapping("/{id}")
    public EntityModel<ResponseEntity<BookDTO>> findById(@PathVariable Long id) {

        Optional<Book> book = bookService.findBookById(id);

        ResponseEntity<BookDTO> bookResponse = ResponseEntity.of(Optional.ofNullable(mapper.bookToDto(book.get())));

        EntityModel<ResponseEntity<BookDTO>> bookDTOEntityModel = EntityModel.of(bookResponse);

        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookController.class).findAll());

        bookDTOEntityModel.add(linkBuilder.withRel("books"));

        return bookDTOEntityModel;
    }

}
