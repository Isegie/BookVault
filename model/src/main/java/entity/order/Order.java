package entity.order;

import entity.book.Book;
import entity.user.User;
import lombok.*;
import util.LocalDateTimeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
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
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToMany(mappedBy = "orders")
    private List<Book> books = new ArrayList<>();

    public Order(boolean orderProcessed, boolean orderStatusSent, LocalDateTime orderDate, User user) {
        this.orderProcessed = orderProcessed;
        this.orderStatusSent = orderStatusSent;
        this.orderDate = orderDate;
        this.user = user;
    }
}
