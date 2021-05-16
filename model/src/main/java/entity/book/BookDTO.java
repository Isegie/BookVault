package entity.book;

import entity.author.AuthorDTO;
import entity.category.Category;
import entity.publisher.Publisher;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

public class BookDTO extends RepresentationModel<BookDTO> {

    private String title;
    private String language;
    private BigDecimal price;
    private String isbn;
    private LocalDate publicationDate;
    private String city;
    private String country;
    private Integer numberOfPages;
    private String format;
    private byte[] picture;
    private Integer edition;
    private String description;
    private Float discount;
    private Float rating;
    private Set<AuthorDTO> authors;
    private Publisher publisher;
    private Category category;

    public BookDTO() {
    }

    public BookDTO(String title, String language, BigDecimal price, String isbn, LocalDate publicationDate, String city, String country, Integer numberOfPages, String format, byte[] picture, Integer edition, String description, Float discount, Float rating, Set<AuthorDTO> authors, Publisher publisher, Category category) {
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
        this.authors = authors;
        this.publisher = publisher;
        this.category = category;
    }


    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public byte[] getPicture() {
        return picture;
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

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDTO> authors) {
        this.authors = authors;
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

    @Override
    public String toString() {
        return "BookDTO{" +
                "title='" + title + '\'' +
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
                '}';
    }
}
