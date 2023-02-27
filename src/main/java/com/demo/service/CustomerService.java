package com.demo.service;

import com.demo.domain.Customer;
import com.demo.domain.Gender;
import com.demo.domain.Rank;
import com.demo.exception.ResourceAlreadyExistsException;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.CustomerRepository;
import com.demo.repository.GenderRepository;
import com.demo.repository.RankRepository;
import com.demo.service.dto.CustomerDto;
import com.demo.service.mapper.CustomerMapper;
import com.demo.web.vm.CustomerVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final GenderRepository genderRepository;
    private final RankRepository rankRepository;
    public CustomerDto createCustomer(CustomerVM customerVM) {
        if (customerRepository.existsByTelephone(customerVM.telephone())) {
            throw new ResourceAlreadyExistsException("Customer with telephone " + customerVM.telephone() + " already existed");
        }
        Customer customer = new Customer();
        customer.setTelephone(customerVM.telephone());
        customer.setFullName(customerVM.fullName());
        Gender gender = genderRepository
                .findByName(customerVM.gender())
                .orElseThrow(() -> new ResourceNotFoundException("Gender " + customerVM.gender() + " not found"));
        customer.setGender(gender);
        Rank rank = rankRepository
                .findRankByPoint(customer.getPoint())
                .orElseThrow(() -> new ResourceNotFoundException("Rank not found"));
        customer.setRank(rank);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public CustomerDto getCustomerByTelephone(String telephone) {
        Customer customer = customerRepository
                .findByTelephone(telephone)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return customerMapper.toDto(customer);
    }
}
