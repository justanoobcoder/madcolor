package com.demo.service;

import com.demo.domain.Employee;
import com.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Employee getCurrentEmployeeLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Employee> e = employeeRepository.findByUsername(username);
        return e.orElseThrow(() -> new RuntimeException("Can't get current logged in user"));
    }
}
