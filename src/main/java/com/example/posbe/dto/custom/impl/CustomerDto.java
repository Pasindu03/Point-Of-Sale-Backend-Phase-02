package com.example.posbe.dto.custom.impl;

import com.example.posbe.dto.custom.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements CustomerStatus {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String userId;
}
