package repository.order;

import entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByOrderDateEquals(LocalDate orderDate);

    List<Order> findOrdersByUser_Id(Long id);

    @Query(value = "select sum(book.price * book_order.amount) from book inner join book_order on book.book_id=book_order.id_book " +
            "inner join orders on orders.order_id=book_order.id_order" +
            " where order_processed=false and order_id=:id", nativeQuery = true)
    Double productSum(@Param("id") Long id);

}
