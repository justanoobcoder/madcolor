package com.demo.repository;

import com.demo.domain.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByTelephone(String telephone);
    @EntityGraph(attributePaths = { "createdBy", "rank", "gender" })
    Optional<Customer> findByTelephone(String telephone);
}
