package service.publisher;

import entity.publisher.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.publisher.PublisherRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Publisher findPublisherById(Long id) {

        Publisher foundPublisher = publisherRepository.getOne(id);

        if (foundPublisher == null) {
            throw new EntityNotFoundException(String.format("Publisher with id=%d not found", id));
        }
        return foundPublisher;
    }
}
