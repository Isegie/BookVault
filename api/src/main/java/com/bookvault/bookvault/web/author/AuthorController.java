package com.bookvault.bookvault.web.author;

import entity.author.AuthorDTO;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.author.AuthorService;

@RestController
@RequestMapping("author")
public class AuthorController {

    private final AuthorService authorService;
    private final MapStructMapper mapper;

    @Autowired
    public AuthorController(AuthorService authorService, MapStructMapper mapper) {
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> findAuthorById(@PathVariable Long id) {
        AuthorDTO authorDTO = mapper.authorToDto(authorService.findAuthorById(id));
        return ResponseEntity.ok(authorDTO);
    }

}
