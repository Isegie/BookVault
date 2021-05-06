package entity.publisher;

import entity.book.Book;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publisher")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Publisher implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", insertable = false, updatable = false)
    private @Id
    @Setter(AccessLevel.PROTECTED)
    Long publisherId;
    @Column(name = "publisher_name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();

    public Publisher(String name, String address, String phoneNumber, String email, List<Book> books) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.books = books;
    }
}
