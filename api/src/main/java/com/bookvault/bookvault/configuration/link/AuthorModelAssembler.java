package com.bookvault.bookvault.configuration.link;

import com.bookvault.bookvault.web.author.AuthorController;
import entity.author.Author;
import entity.author.AuthorDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AuthorModelAssembler extends RepresentationModelAssemblerSupport<Author, AuthorDTO> {


    public AuthorModelAssembler() {
        super(AuthorController.class, AuthorDTO.class);
    }

    @Override
    public AuthorDTO toModel(Author entity) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setFirstName(entity.getFirstName());
        authorDTO.setLastName(entity.getLastName());
        authorDTO.setEmail(entity.getEmail());
        return authorDTO;
    }

    @Override
    public CollectionModel<AuthorDTO> toCollectionModel(Iterable<? extends Author> entities) {

        CollectionModel<AuthorDTO> authors = super.toCollectionModel(entities);
        authors.add(linkTo
                (methodOn(AuthorController.class)
                        .authors()).withSelfRel());

        return authors;
    }
}
