package com.demo.web;

import com.demo.service.CustomerService;
import com.demo.service.dto.CustomerDto;
import com.demo.service.mapper.CustomerMapper;
import com.demo.web.api.CustomerResource;
import com.demo.web.vm.CustomerVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CustomerResourceImpl implements CustomerResource {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Override
    public ResponseEntity<CustomerDto> createNewCustomer(CustomerVM customerVM) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerMapper.toDto(customerService.createCustomer(customerVM)));
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomer(String telephone) {
        return ResponseEntity
                .ok(customerMapper.toDto(customerService.getCustomerByTelephone(telephone)));
    }

    @Override
    public ResponseEntity<CustomerDto> updateCustomer(Long id, CustomerVM customerVM) {
        return ResponseEntity
                .ok(customerMapper.toDto(customerService.updateCustomer(id, customerVM)));
    }
}
