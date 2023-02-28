package com.demo.security;

import com.demo.domain.Employee;
import com.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuditAwareImpl implements AuditorAware<Employee> {
    private final EmployeeService employeeService;
    @Override
    public Optional<Employee> getCurrentAuditor() {
        return Optional.ofNullable(employeeService.getCurrentEmployeeLogin());
    }
}
