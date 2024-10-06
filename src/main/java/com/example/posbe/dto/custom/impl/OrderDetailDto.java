package com.example.posbe.dto.custom.impl;

import com.example.posbe.dto.custom.OrderDetailStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto implements OrderDetailStatus {
    private Long id;
    private String itemCode;
    private int quantity;
    private double unitPrice;
}
