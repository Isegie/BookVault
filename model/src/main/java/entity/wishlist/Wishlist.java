package entity.wishlist;

import entity.user.User;
import entity.wishlistgroup.WishlistGroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "wishlist")
public class Wishlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id", updatable = false, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "wishlist", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<WishlistGroup> wishlistGroupList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Wishlist(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WishlistGroup> getWishlistGroupList() {
        return wishlistGroupList;
    }

    public void setWishlistGroupList(List<WishlistGroup> wishlistGroupList) {
        this.wishlistGroupList = wishlistGroupList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wishlist wishlist = (Wishlist) o;
        return Objects.equals(id, wishlist.id) &&
                Objects.equals(user, wishlist.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }
}
