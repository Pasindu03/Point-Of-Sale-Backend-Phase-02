package com.example.posbe.service.impl;

import com.example.posbe.dto.custom.impl.OrderDetailDto;
import com.example.posbe.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public void saveOrderDetail(OrderDetailDto orderDetailDto) {

    }

    @Override
    public OrderDetailDto getOrderDetailById(String orderDetailId) {
        return null;
    }

    @Override
    public List<OrderDetailDto> getAllOrderDetails() {
        return List.of();
    }

    @Override
    public void updateOrderDetail(String orderDetailId, OrderDetailDto orderDetailDto) {

    }

    @Override
    public void deleteOrderDetail(String orderDetailId) {

    }
}
