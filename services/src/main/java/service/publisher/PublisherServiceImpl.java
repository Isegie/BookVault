package service.publisher;

import entity.publisher.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.publisher.PublisherRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Optional<Publisher> findPublisherById(Long id) {

        Optional<Publisher> foundPublisher = publisherRepository.findById(id);

        return foundPublisher.map(Optional::ofNullable).orElseThrow(() -> new EntityNotFoundException(String.format("Publisher with id=%d not found", id)));
    }
}
