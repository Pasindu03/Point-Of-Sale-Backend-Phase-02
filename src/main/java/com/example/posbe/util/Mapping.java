package com.example.posbe.util;

import com.example.posbe.dto.custom.impl.CustomerDto;
import com.example.posbe.entity.impl.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //Customer DTO, Entity & List of Customers
    public Customer toCustomerEntity(CustomerDto dto){
        return modelMapper.map(dto, Customer.class);
    }

    public CustomerDto toCustomerDto(Customer customer){
        return modelMapper.map(customer, CustomerDto.class);
    }

    public List<CustomerDto> asCustomerDto(List<Customer> customerList){
        return modelMapper.map(customerList, new TypeToken<List<CustomerDto>>() {}.getType());
    }

    //Item DTO, Entity & List of Items

    //Order DTO, Entity & List of Orders

    //Order Details DTO, Entity & List of Order Details
}
