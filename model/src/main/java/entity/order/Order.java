package entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entity.book.Book;
import entity.user.User;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import util.LocalDateTimeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", insertable = false, updatable = false)
    private @Id
    @Setter(AccessLevel.PROTECTED)
    Long orderId;
    @Column(name = "order_processed")
    private boolean orderProcessed;
    @Column(name = "order_status_sent")
    private boolean orderStatusSent;
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @Fetch(FetchMode.JOIN)
    @ManyToMany(mappedBy = "orders",fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Order() {
    }

    public Order(boolean orderProcessed, boolean orderStatusSent, LocalDateTime orderDate, User user) {
        this.orderProcessed = orderProcessed;
        this.orderStatusSent = orderStatusSent;
        this.orderDate = orderDate;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
