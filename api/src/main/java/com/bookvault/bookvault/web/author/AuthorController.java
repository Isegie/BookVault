package com.bookvault.bookvault.web.author;

import com.bookvault.bookvault.configuration.link.AuthorModelAssembler;
import entity.author.Author;
import entity.author.AuthorCommand;
import entity.author.AuthorDTO;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.author.AuthorService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("author")
public class AuthorController {

    private final AuthorService authorService;
    private final MapStructMapper mapper;
    private final AuthorModelAssembler modelAssembler;

    @Autowired
    public AuthorController(AuthorService authorService, MapStructMapper mapper, AuthorModelAssembler modelAssembler) {
        this.authorService = authorService;
        this.mapper = mapper;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<AuthorDTO>> authors() {
        List<Author> authors = authorService.findAll();
        return new ResponseEntity<>(modelAssembler.toCollectionModel(authors), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> findAuthorById(@PathVariable Long id) {
        AuthorDTO authorDTO = mapper.authorToDto(authorService.findAuthorById(id));
        return ResponseEntity.ok(authorDTO);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody @Valid AuthorCommand authorCommand) {
        AuthorDTO authorDTO = mapper.authorCommandToDto(authorCommand);
        Author createdAuthor = authorService.save(authorDTO);
        Optional<Author> author = Optional.ofNullable(createdAuthor);
        return author.map(modelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
