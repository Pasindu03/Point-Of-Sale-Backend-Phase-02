package com.example.posbe.controller;

import com.example.posbe.customStatusCode.SelectedCustomerErrorStatus;
import com.example.posbe.dto.custom.CustomerStatus;
import com.example.posbe.dto.custom.impl.CustomerDto;
import com.example.posbe.exception.CustomerNotFoundException;
import com.example.posbe.exception.DataPersistException;
import com.example.posbe.service.CustomerService;
import com.example.posbe.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto dto) {
        try {
            service.saveCustomer(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException d) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerStatus getSelectedCustomer(@PathVariable("customerId") String customerId) {
        if (!RegexUtil.isValidCustomerId(customerId)) {
            return new SelectedCustomerErrorStatus(1, "Customer Not Found");
        }
        return service.getCustomer(customerId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getAllCustomers() {
        return service.getAllCustomers();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId) {
        try {
            if (!RegexUtil.isValidCustomerId(customerId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException c) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDto dto) {
        try {
            if (!RegexUtil.isValidCustomerId(customerId) || dto == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            service.updateCustomer(dto, customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
