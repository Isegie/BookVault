package com.bookvault.bookvault.web.book;

import com.bookvault.bookvault.configuration.link.BookModelAssembler;
import entity.book.Book;
import entity.book.BookCommand;
import entity.book.BookDTO;
import entity.dto.BookReviewDTO;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import service.book.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @PreAuthorize("hasRole('ADMIN_ROLE')")
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

        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(BookController.class).findAll());

        bookDTOEntityModel.add(linkBuilder.withRel("books"));

        return bookDTOEntityModel;
    }

    @GetMapping("/isbn/{isbn}")
    public EntityModel<ResponseEntity<BookDTO>> findByISBN(@RequestParam(value = "isbn") String isbn) {

        Optional<Book> book = bookService.findBookByISBN(isbn);

        ResponseEntity<BookDTO> bookResponse = ResponseEntity.of(Optional.ofNullable(mapper.bookToDto(book.get())));

        EntityModel<ResponseEntity<BookDTO>> bookDTOEntityModel = EntityModel.of(bookResponse);

        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(BookController.class).findAll());

        bookDTOEntityModel.add(linkBuilder.withRel("books"));

        return bookDTOEntityModel;
    }

    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable("id") String id) {
        bookService.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(Long.valueOf(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @PutMapping("{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") Long id, @Valid @RequestBody BookCommand bookCommand) {
        return bookService.update(id, bookCommand).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("reviews/{id}")
    public ResponseEntity<List<BookReviewDTO>> findBookReviews(@PathVariable("id") Long id) {

        List<BookReviewDTO> bookReviews = bookService.getBookReviews(id);
        if (bookReviews.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(bookReviews, HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<BookDTO>> findBooksByCategory(@RequestParam("category") String category) {

        List<BookDTO> books = bookService.findBookByCategory(category)
                .stream().map(mapper::bookToDto).collect(Collectors.toList());

        if (books.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
