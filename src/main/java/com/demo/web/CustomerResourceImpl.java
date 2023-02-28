package com.demo.web;

import com.demo.service.CustomerService;
import com.demo.service.dto.CustomerDto;
import com.demo.web.api.CustomerResource;
import com.demo.web.vm.CustomerVM;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CustomerResourceImpl implements CustomerResource {
    private final CustomerService customerService;

    @Override
    public CustomerDto createNewCustomer(CustomerVM customerVM) {
        return customerService.createCustomer(customerVM);
    }

    @Override
    public CustomerDto getCustomer(String telephone) {
        return customerService.getCustomerByTelephone(telephone);
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerVM customerVM) {
        return customerService.updateCustomer(id, customerVM);
    }
}
