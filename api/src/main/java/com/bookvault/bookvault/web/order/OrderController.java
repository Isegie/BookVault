package com.bookvault.bookvault.web.order;

import entity.order.OrderDTO;
import mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.order.OrderRepository;
import service.order.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "order", produces = "application/hal+json")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final MapStructMapper mapper;

    @Autowired
    public OrderController(OrderService orderService, OrderRepository orderRepository, MapStructMapper mapper) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
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
}
