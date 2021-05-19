package entity.review;

import entity.book.Book;
import entity.user.User;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Review implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", insertable = false, updatable = false)
    private @Id
    @Setter(AccessLevel.PROTECTED)
    Long reviewId;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "content")
    private String content;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "published_date")
    private LocalDateTime publishDate;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Review() {
    }

    public Review(String content, Integer rating, Book book, User user) {
        this.content = content;
        this.rating = rating;
        this.book = book;
        this.user = user;
    }

    public Review(String content, Integer rating, LocalDateTime publishDate, Book book, User user) {
        this.content = content;
        this.rating = rating;
        this.publishDate = publishDate;
        this.book = book;
        this.user = user;
    }
}
