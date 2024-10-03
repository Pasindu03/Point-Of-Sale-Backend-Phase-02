package com.example.posbe.service;

import com.example.posbe.dto.custom.CustomerStatus;
import com.example.posbe.dto.custom.impl.CustomerDto;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDto dto);
    void deleteCustomer(String id);
    List<CustomerDto> getAllCustomers();
    CustomerStatus getCustomer(String id);
    void updateCustomer(CustomerDto dto, String id);
}
