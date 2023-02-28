package com.demo.service.mapper;

import com.demo.domain.Customer;
import com.demo.service.dto.CustomerDto;
import com.demo.web.vm.CustomerVM;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class CustomerMapper {
    private final ModelMapper modelMapper;
    public CustomerDto toDto(Customer customer) {
        return Objects.isNull(customer) ? null : modelMapper.map(customer, CustomerDto.class);
    }
    public Customer toEntity(CustomerDto customerDto) {
        return Objects.isNull(customerDto) ? null : modelMapper.map(customerDto, Customer.class);
    }

    public void map(CustomerDto customerDto, Customer customer) {
        modelMapper.map(customerDto, customer);
    }
}
