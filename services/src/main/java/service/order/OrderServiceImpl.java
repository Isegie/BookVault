package service.order;

import entity.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.order.OrderRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Double productSum(Long id) {
        return orderRepository.productSum(id);
    }

    @Override
    public List<Order> findOrdersByOrderDate(LocalDate orderDate) {
        return orderRepository.findOrdersByOrderDateEquals(orderDate);
    }

    @Override
    public List<Order> findOrdersByUserId(Long id) {
        return orderRepository.findOrdersByUser_Id(id);
    }

    @Transactional
    @Override
    public void deleteBookFromOrder(Long id) {
        orderRepository.deleteBookFromOrder(id);
    }

    @Override
    public List<Order> findExecutedOrdersForUser(Long id) {
        return orderRepository.usersExecutedOrders(id);
    }
}
