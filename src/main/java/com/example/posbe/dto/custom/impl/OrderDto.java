package com.example.posbe.dto.custom.impl;

import com.example.posbe.dto.custom.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements OrderStatus {
    private Long orderId;
    private String orderDate;
    private String customerId;
    private String userId;
    private List<OrderDetailDto> orderDetails;
}
