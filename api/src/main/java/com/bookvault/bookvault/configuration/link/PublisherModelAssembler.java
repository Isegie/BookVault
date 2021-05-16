package com.bookvault.bookvault.configuration.link;

import com.bookvault.bookvault.web.publisher.PublisherController;
import entity.publisher.Publisher;
import entity.publisher.PublisherDTO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PublisherModelAssembler extends RepresentationModelAssemblerSupport<Publisher, PublisherDTO> {

    public PublisherModelAssembler() {
        super(PublisherController.class, PublisherDTO.class);
    }

    @Override
    public PublisherDTO toModel(Publisher entity) {
        PublisherDTO publisherDTO = instantiateModel(entity);

        publisherDTO.add(linkTo(methodOn(PublisherController.class)
                .findPublisher(entity.getPublisherId())).withSelfRel());

        publisherDTO.setName(entity.getName());
        publisherDTO.setAddress(entity.getAddress());
        publisherDTO.setPhoneNumber(entity.getPhoneNumber());
        publisherDTO.setEmail(entity.getEmail());

        return publisherDTO;
    }
}
