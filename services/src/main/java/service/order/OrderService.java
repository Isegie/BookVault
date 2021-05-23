package service.order;

import entity.order.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    Double productSum(Long id);

    List<Order> findOrdersByOrderDate(LocalDate orderDate);

    List<Order> findOrdersByUserId(Long id);

    void deleteBookFromOrder(Long id);

    List<Order> findExecutedOrdersForUser(Long id);
}
