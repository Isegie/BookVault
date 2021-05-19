package entity.review;

import entity.book.Book;
import entity.user.User;

import java.time.LocalDateTime;

public class ReviewDTO {

    private String content;
    private Integer rating;
    private LocalDateTime publishDate;
    private Book book;
    private User user;

    public ReviewDTO() {
    }

    public ReviewDTO(String content, Integer rating, LocalDateTime publishDate, Book book, User user) {
        this.content = content;
        this.rating = rating;
        this.publishDate = publishDate;
        this.book = book;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
