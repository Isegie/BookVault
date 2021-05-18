package entity.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entity.author.Author;
import entity.basket.Basket;
import entity.category.Category;
import entity.order.Order;
import entity.publisher.Publisher;
import entity.review.Review;
import entity.wishlist.Wishlist;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "book")
@EqualsAndHashCode
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", insertable = false, updatable = false)
    private Long bookId;
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
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "picture")
    private byte[] picture;
    @Column(name = "edition")
    private Integer edition;
    @Column(name = "description")
    private String description;
    @Column(name = "discount")
    private Float discount;
    @Column(name = "rating")
    private Float rating;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_publisher")
    private Publisher publisher;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_category")
    private Category category;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "book_order",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_order"))
    private Set<Order> orders = new HashSet<>();

    @JsonIgnore
    @Fetch(FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_author"))
    private Set<Author> authors = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "book_wishlist",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_wishlist"))
    private List<Wishlist> wishlists = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_basket", joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_basket"))
    private List<Basket> baskets = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private List<Review> reviews;

    public Book() {

    }

    public Book(Long bookId, String title, String language, BigDecimal price, String isbn, LocalDate publicationDate, String city, String country, Integer numberOfPages, String format, byte[] picture, Integer edition, String description, Float discount, Float rating, Publisher publisher, Category category) {
        this.bookId = bookId;
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

    public Long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public List<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<Basket> baskets) {
        this.baskets = baskets;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                ", publicationDate=" + publicationDate +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", format='" + format + '\'' +
                ", picture=" + Arrays.toString(picture) +
                ", edition=" + edition +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", rating=" + rating +
                ", publisher=" + publisher +
                ", category=" + category +
                ", orders=" + orders +
                ", authors=" + authors +
                ", wishlists=" + wishlists +
                ", baskets=" + baskets +
                '}';
    }
}
