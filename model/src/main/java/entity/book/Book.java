package entity.book;

import entity.author.Author;
import entity.basket.Basket;
import entity.category.Category;
import entity.order.Order;
import entity.publisher.Publisher;
import entity.wishlist.Wishlist;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Book implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", insertable = false, updatable = false)
    private @Id
    @Setter(AccessLevel.PROTECTED)
    Long bookId;
    @Column(name = "title")
    private String title;
    @Column(name = "language")
    private String language;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "number_of_pages")
    private Integer numberOfPages;
    @Column(name = "format")
    private String format;
    @Lob
    @Column(name = "picture")
    private byte[] picture;
    @Column(name = "edition")
    private Integer edition;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "discount")
    private Float discount;
    @Column(name = "rating")
    private Float rating;

    @ManyToOne
    @JoinColumn(name = "id_publisher")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToMany
    @JoinTable(name = "book_order",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_order"))
    private Set<Order> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_author"))
    private Set<Author> authors = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "book_wishlist",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_wishlist"))
    private List<Wishlist> wishlists = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_basket", joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_basket"))
    private List<Basket> baskets = new ArrayList<>();

    public Book(String title, String language, BigDecimal price, String isbn, LocalDate publicationDate, String city, String country, Integer numberOfPages, String format, byte[] picture, Integer edition, String description, Float discount, Float rating) {
        this.title = title;
        this.language = language;
        this.price = price;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.city = city;
        this.country = country;
        this.numberOfPages = numberOfPages;
        this.format = format;
        this.picture = picture;
        this.edition = edition;
        this.description = description;
        this.discount = discount;
        this.rating = rating;
        this.publisher = publisher;
        this.category = category;
    }
}
