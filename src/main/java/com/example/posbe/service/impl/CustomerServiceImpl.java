package com.example.posbe.service.impl;

import com.example.posbe.customStatusCode.SelectedCustomerErrorStatus;
import com.example.posbe.dao.CustomerDao;
import com.example.posbe.dto.custom.CustomerStatus;
import com.example.posbe.dto.custom.impl.CustomerDto;
import com.example.posbe.entity.impl.Customer;
import com.example.posbe.exception.CustomerNotFoundException;
import com.example.posbe.exception.DataPersistException;
import com.example.posbe.service.CustomerService;
import com.example.posbe.util.AppUtil;
import com.example.posbe.util.Mapping;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao dao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveCustomer(CustomerDto dto) {
        dto.setCustomerId(AppUtil.generateCustomerId());
        Customer savedCustomer = dao.save(mapping.toCustomerEntity(dto));

        if (savedCustomer == null){
            throw new DataPersistException("Customer has not saved");
        }
    }

    @Override
    public void deleteCustomer(String id) {
        Optional<Customer> foundCustomer = dao.findById(id);

        if (!foundCustomer.isPresent()){
            throw new CustomerNotFoundException("Customer Not Found");
        }
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> allCustomer = dao.findAll();
        return mapping.asCustomerDto(allCustomer);
    }

    @SneakyThrows
    @Override
    public CustomerStatus getCustomer(String id) {
        if (dao.existsById(id)){
            Customer selectedCustomer = dao.getReferenceById(id);
            return mapping.toCustomerDto(selectedCustomer);
        } else {
            throw new SelectedCustomerErrorStatus(2, "Selected Customer Not Found");
        }
    }

    @Override
    public void updateCustomer(CustomerDto dto, String id) {
        Optional<Customer> byId = dao.findById(id);

        if (!byId.isPresent()){
            throw new CustomerNotFoundException("Customer Not Found");
        } else {
            byId.get().setCustomerName(dto.getCustomerName());
            byId.get().setCustomerAddress(dto.getCustomerAddress());
            byId.get().setCustomerPhone(dto.getCustomerPhone());
        }
    }
}
