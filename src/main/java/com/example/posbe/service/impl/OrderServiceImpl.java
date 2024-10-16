package com.example.posbe.service.impl;

import com.example.posbe.dao.OrderDao;
import com.example.posbe.dto.custom.impl.OrderDto;
import com.example.posbe.entity.impl.Order;
import com.example.posbe.exception.OrderNotFoundException;
import com.example.posbe.exception.DataPersistException;
import com.example.posbe.service.OrderService;
import com.example.posbe.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private Mapping mapping;

    // Save a new order
    @Override
    public void saveOrder(OrderDto orderDto) {
        Order order = mapping.toOrderEntity(orderDto);
        Order savedOrder = orderDao.save(order);

        if (savedOrder == null) {
            throw new DataPersistException("Order not saved");
        }
    }

    // Get an order by its ID
    @Override
    public OrderDto getOrderById(String orderId) {
        Optional<Order> orderOptional = orderDao.findById(orderId);

        if (orderOptional.isPresent()) {
            return mapping.toOrderDto(orderOptional.get());
        } else {
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
    }

    // Get all orders
    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderDao.findAll();
        return mapping.asOrderDto(orders);
    }

    @Override
    public void updateOrder(String orderId, OrderDto orderDto) {
        Optional<Order> orderOptional = orderDao.findById(orderId);

        if (!orderOptional.isPresent()) {
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        } else {
            Order orderToUpdate = orderOptional.get();
            orderToUpdate.setOrderDate(orderDto.getOrderDate());

            orderDao.save(orderToUpdate);
        }
    }

    @Override
    public void deleteOrder(String orderId) {
        Optional<Order> orderOptional = orderDao.findById(orderId);

        if (!orderOptional.isPresent()) {
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        } else {
            // Delete the order
            orderDao.deleteById(orderId);
        }
    }
}
