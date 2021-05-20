package service.author;

import entity.author.Author;
import entity.author.AuthorDTO;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.author.AuthorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final MapStructMapper mapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, MapStructMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    public Author findAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
            throw new EntityNotFoundException(String.format("Author with id=%d not found", id));
        }
        return author.get();
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author save(AuthorDTO author) {
        return authorRepository.save(mapper.authorDtoToAuthor(author));
    }
}
