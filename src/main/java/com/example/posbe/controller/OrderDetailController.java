package com.example.posbe.controller;

import com.example.posbe.customStatusCode.SelectedOrderDetailErrorStatus;
import com.example.posbe.customStatusCode.SelectedOrderErrorStatus;
import com.example.posbe.dto.custom.OrderDetailStatus;
import com.example.posbe.dto.custom.OrderStatus;
import com.example.posbe.dto.custom.impl.OrderDetailDto;
import com.example.posbe.exception.DataPersistException;
import com.example.posbe.exception.OrderDetailNotFoundException;
import com.example.posbe.service.OrderDetailService;
import com.example.posbe.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrderDetail(@RequestBody OrderDetailDto orderDetailDto) {
        try {
            orderDetailService.saveOrderDetail(orderDetailDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException d){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{orderDetailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDetailStatus getOrderDetailById(@PathVariable("orderDetailId") String orderDetailId) {
        if (!RegexUtil.isValidOrderDetailId(orderDetailId)){
            return new SelectedOrderDetailErrorStatus(1, "Order Detail Not Found");
        }
        return orderDetailService.getOrderDetailById(orderDetailId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailDto> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    @PutMapping(value = "/{orderDetailId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrderDetail(@PathVariable("orderDetailId") String orderDetailId, @RequestBody OrderDetailDto orderDetailDto) {
        try {
            if (!RegexUtil.isValidOrderDetailId(orderDetailId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            orderDetailService.updateOrderDetail(orderDetailId, orderDetailDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderDetailNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{orderDetailId}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable("orderDetailId") String orderDetailId) {
        try {
            if (!RegexUtil.isValidOrderDetailId(orderDetailId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            orderDetailService.deleteOrderDetail(orderDetailId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (OrderDetailNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
