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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao dao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveCustomer(CustomerDto dto) {
        dto.setCustomerId(AppUtil.generateCustomerId());
        Customer customerEntity = mapping.toCustomerEntity(dto);

        Customer savedCustomer = dao.save(customerEntity);
        if (savedCustomer == null) {
            throw new DataPersistException("Customer has not been saved");
        }
    }

    @Override
    public void deleteCustomer(String id) {
        Optional<Customer> foundCustomer = dao.findById(id);
        if (!foundCustomer.isPresent()) {
            throw new CustomerNotFoundException("Customer Not Found");
        }

        dao.deleteById(id);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> allCustomers = dao.findAll();
        return mapping.asCustomerDto(allCustomers);
    }

    @SneakyThrows
    @Override
    public CustomerStatus getCustomer(String id) {
        if (dao.existsById(id)) {
            Customer selectedCustomer = dao.getReferenceById(id);
            return mapping.toCustomerDto(selectedCustomer);
        } else {
            return new SelectedCustomerErrorStatus(2, "Selected Customer Not Found");
        }
    }

    @Override
    public void updateCustomer(CustomerDto dto, String id) {
        Optional<Customer> byId = dao.findById(id);
        if (!byId.isPresent()) {
            throw new CustomerNotFoundException("Customer Not Found");
        } else {
            Customer customerToUpdate = byId.get();
            customerToUpdate.setCustomerName(dto.getCustomerName());
            customerToUpdate.setCustomerAddress(dto.getCustomerAddress());
            customerToUpdate.setCustomerPhone(dto.getCustomerPhone());

            dao.save(customerToUpdate);
        }
    }
}
