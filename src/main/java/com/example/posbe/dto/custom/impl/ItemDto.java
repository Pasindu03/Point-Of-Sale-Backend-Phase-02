package com.example.posbe.dto.custom.impl;

import com.example.posbe.dto.custom.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto implements ItemStatus {
    private String itemCode;
    private String description;
    private int qty;
    private double price;
    private String userId;
}
