package com.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
@Table(name = "orders")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Order implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    @CreatedDate
    private Instant createdAt;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    @Column(nullable = false)
    private Double totalPrice;
}