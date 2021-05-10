package com.bookvault.bookvault.web.book;

import entity.book.BookDTO;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.book.BookService;

import java.util.List;
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

}
