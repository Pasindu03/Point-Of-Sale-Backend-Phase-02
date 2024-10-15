package com.example.posbe.controller;

import com.example.posbe.customStatusCode.SelectedOrderErrorStatus;
import com.example.posbe.dto.custom.OrderStatus;
import com.example.posbe.dto.custom.impl.OrderDto;
import com.example.posbe.exception.DataPersistException;
import com.example.posbe.exception.OrderNotFoundException;
import com.example.posbe.service.OrderService;
import com.example.posbe.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDto orderDto) {
        try {
            orderService.saveOrder(orderDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderStatus getOrderById(@PathVariable("orderId") String orderId) {
        if (!RegexUtil.isValidOrderId(orderId)) {
            return new SelectedOrderErrorStatus(1, "Order Not Found");
        }
        return orderService.getOrderById(orderId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDto> getAllOrders() {return orderService.getAllOrders();}

    @PutMapping(value = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder(@PathVariable("orderId") String orderId, @RequestBody OrderDto orderDto) {
        try {
            if (!RegexUtil.isValidOrderId(orderId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            orderService.updateOrder(orderId, orderDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") String orderId) {

        try {
            if (!RegexUtil.isValidOrderId(orderId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
