package entity.basket;


import entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "basket")
@EqualsAndHashCode
@ToString
public class Basket implements Serializable {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @Getter
    @Setter
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id", updatable = false, nullable = false)
    @Getter
    @Setter
    private Long id;

    @Column(name = "total_price")
    @Getter
    @Setter
    private BigDecimal totalPrice;
}