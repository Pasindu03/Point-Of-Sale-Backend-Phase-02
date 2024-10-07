package com.example.posbe.service;

import com.example.posbe.dto.custom.impl.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    void saveOrderDetail(OrderDetailDto orderDetailDto);
    OrderDetailDto getOrderDetailById(String orderDetailId);
    List<OrderDetailDto> getAllOrderDetails();
    void updateOrderDetail(String orderDetailId, OrderDetailDto orderDetailDto);
    void deleteOrderDetail(String orderDetailId);
}
