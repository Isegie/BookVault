package entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entity.book.Book;
import entity.user.UserDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private boolean orderProcessed;
    private boolean orderStatusSent;
    private LocalDateTime orderDate;
    @JsonIgnore
    private UserDTO user;
    private List<Book> books = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(boolean orderProcessed, boolean orderStatusSent, LocalDateTime orderDate, UserDTO user, List<Book> books) {
        this.orderProcessed = orderProcessed;
        this.orderStatusSent = orderStatusSent;
        this.orderDate = orderDate;
        this.user = user;
        this.books = books;
    }

    public boolean isOrderProcessed() {
        return orderProcessed;
    }

    public void setOrderProcessed(boolean orderProcessed) {
        this.orderProcessed = orderProcessed;
    }

    public boolean isOrderStatusSent() {
        return orderStatusSent;
    }

    public void setOrderStatusSent(boolean orderStatusSent) {
        this.orderStatusSent = orderStatusSent;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
