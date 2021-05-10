package entity.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import validator.image.ValidImage;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookCommand {
    @NotBlank(message = "Title must not be empty")
    @JsonProperty("title")
    private String title;
    @NotBlank(message = "Language must not be empty")
    @JsonProperty("language")
    private String language;
    @PositiveOrZero(message = "Price must be a positive value")
    @Digits(integer = 6, fraction = 2)
    @JsonProperty("price")
    private BigDecimal price;
    @Pattern(message = "ISBN must have 10 digits", regexp = "[\\d]{10}")
    @NotBlank(message = "ISBN must not be empty")
    @JsonProperty("isbn")
    private String isbn;
    @Past(message = "Publication date must be in the past")
    @JsonProperty("publicationDate")
    private LocalDate publicationDate;
    @NotBlank(message = "City must not be empty")
    @JsonProperty("city")
    private String city;
    @NotBlank(message = "Country must not be empty")
    @JsonProperty("country")
    private String country;
    @Min(message = "Book must have at least 10 pages", value = 10)
    @JsonProperty("numberOfPages")
    private Integer numberOfPages;
    @NotBlank(message = "Format must not be empty")
    @JsonProperty("format")
    private String format;
    @ValidImage
    @JsonProperty("picture")
    private byte[] picture;
    @Min(message = "Edition be equal to or more than one", value = 1)
    @JsonProperty("edition")
    private Integer edition;
    @NotBlank(message = "Description must not be empty")
    @JsonProperty("description")
    private String description;
    @Min(message = "Discount must be at least 5 percent", value = 5)
    @JsonProperty("discount")
    private Float discount;
    @Min(message = "Rating must be at least one", value = 1)
    @JsonProperty("rating")
    private Float rating;

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
}
