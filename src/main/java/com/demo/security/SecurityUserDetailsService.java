package com.demo.security;

import com.demo.domain.Employee;
import com.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SecurityUserDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository
                .findByUsername(username)
                .map(this::createSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    private User createSecurityUser(Employee e) {
        Collection<? extends GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(e.getRole().getName()));
        return new User(e.getUsername(), e.getPassword(), authorities);
    }
}
