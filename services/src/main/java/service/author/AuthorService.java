package service.author;

import entity.author.Author;

public interface AuthorService {
    Author findAuthorById(Long id);
}
