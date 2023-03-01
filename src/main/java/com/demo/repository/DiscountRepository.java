package com.demo.repository;

import com.demo.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    boolean existsByName(String name);
    Optional<Discount> findByName(String name);
}
