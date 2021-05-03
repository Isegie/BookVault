package entity.wishlistgroup;

import entity.wishlist.Wishlist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "wishlist_group")
public class WishlistGroup implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_wishlist")
    private Wishlist wishlist;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_group_id", updatable = false, nullable = false)
    private Long id;

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishlistGroup that = (WishlistGroup) o;
        return Objects.equals(wishlist, that.wishlist) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishlist, id);
    }
}
