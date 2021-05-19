package service.author;

import entity.author.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.author.AuthorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
            throw new EntityNotFoundException(String.format("Author with id=%d not found", id));
        }
        return author.get();
    }
}
