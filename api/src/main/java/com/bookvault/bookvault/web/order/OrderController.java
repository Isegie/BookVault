package com.bookvault.bookvault.web.order;

import entity.order.OrderDTO;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.order.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "order", produces = "application/hal+json")
public class OrderController {

    private final OrderService orderService;
    private final MapStructMapper mapper;

    @Autowired
    public OrderController(OrderService orderService, MapStructMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @GetMapping("users/{id}")
    public List<OrderDTO> findOrdersByUserId(@PathVariable Long id) {
        return orderService.findOrdersByUserId(id).stream()
                .map(mapper::orderToDTO).collect(Collectors.toList());
    }

    @GetMapping("sum/{id}")
    public Double productOrderSum(@PathVariable("id") Long id) {
        return orderService.productSum(id);
    }

    @DeleteMapping("remove/{id}")
    public void deleteBookInOrder(@PathVariable("id") Long id) {
        orderService.deleteBookFromOrder(id);
    }

    @GetMapping("orders/status/{id}")
    public List<OrderDTO> findExecutedOrders(@PathVariable("id") Long id) {
        return orderService.findExecutedOrdersForUser(id).stream()
                .map(mapper::orderToDTO).collect(Collectors.toList());
    }
}
