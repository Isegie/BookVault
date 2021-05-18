package repository.book;

import entity.book.Book;
import entity.dto.BookReviewDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Optional<Book> findByIsbn(String isbn);

    void deleteByBookId(Long id);

    @Query("select new entity.dto.BookReviewDTO(r.book,r.content,r.rating,r.publishDate,u.username) from Book b," +
            "User u, Review r where u=r.user and b=r.book and b.bookId=:id")
    List<BookReviewDTO> findBookReviews(@Param("id") Long id);
}
