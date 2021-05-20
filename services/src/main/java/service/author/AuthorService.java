package service.author;

import entity.author.Author;
import entity.author.AuthorDTO;

import java.util.List;

public interface AuthorService {
    Author findAuthorById(Long id);

    List<Author> findAll();

    Author save(AuthorDTO author);
}
