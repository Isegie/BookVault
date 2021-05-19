package entity.dto;

import entity.book.Book;
import entity.user.User;

import java.time.LocalDateTime;

public class BookReviewDTO {

    private String content;
    private Integer rating;
    private LocalDateTime publishDate;
    private Book book;
    private String username;

    public BookReviewDTO() {
    }

    public BookReviewDTO(Book book, String content, Integer rating, LocalDateTime publishDate, String username) {
        this.content = content;
        this.rating = rating;
        this.publishDate = publishDate;
        this.book = book;
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
