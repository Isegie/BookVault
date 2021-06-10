package com.bookvault.bookvault.web.author;

import com.bookvault.bookvault.configuration.link.AuthorModelAssembler;
import entity.author.Author;
import entity.author.AuthorCommand;
import entity.author.AuthorDTO;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.author.AuthorService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @PreAuthorize("hasRole('USER_ROLE') or hasRole('ADMIN_ROLE')")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> findAuthorById(@PathVariable Long id) {
        AuthorDTO authorDTO = mapper.authorToDto(authorService.findAuthorById(id));
        return ResponseEntity.ok(authorDTO);
    }

    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody @Valid AuthorCommand authorCommand) {
        AuthorDTO authorDTO = mapper.authorCommandToDto(authorCommand);
        Author createdAuthor = authorService.save(authorDTO);
        Optional<Author> author = Optional.ofNullable(createdAuthor);
        return author.map(modelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{prop}")
    public EntityModel<ResponseEntity<List<AuthorDTO>>> findByFirstNameOrLastName(@RequestParam(value = "prop") String prop) {

        List<AuthorDTO> authors = authorService.findByFirstOrLastName(prop)
                .stream().map(mapper::authorToDto).collect(Collectors.toList());

        ResponseEntity<List<AuthorDTO>> authorResponse = ResponseEntity.ok().body(authors);

        EntityModel<ResponseEntity<List<AuthorDTO>>> authorDTOEntityModel = EntityModel.of(authorResponse);

        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(AuthorController.class).authors());

        authorDTOEntityModel.add(linkBuilder.withRel("authors"));

        return authorDTOEntityModel;
    }
}
