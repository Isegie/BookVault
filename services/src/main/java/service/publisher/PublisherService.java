package service.publisher;

import entity.publisher.Publisher;

import java.util.Optional;

public interface PublisherService {
    Optional<Publisher> findPublisherById(Long id);
}
