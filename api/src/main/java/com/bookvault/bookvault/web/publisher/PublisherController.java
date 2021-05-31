package com.bookvault.bookvault.web.publisher;

import entity.publisher.PublisherDTO;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.publisher.PublisherService;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("publisher")
public class PublisherController {

    private final PublisherService publisherService;
    private final MapStructMapper mapper;

    @Autowired
    public PublisherController(PublisherService publisherService, MapStructMapper mapper) {
        this.publisherService = publisherService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('USER_ROLE') or hasRole('ADMIN_ROLE')")
    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> findPublisher(@PathVariable("id") Long id) {
        return publisherService.findPublisherById(id)
                .map(mapper::publisherToDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Publisher with id=%d not found", id)));
    }

}
