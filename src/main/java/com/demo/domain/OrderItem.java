package com.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
@Table(name = "order_items")
@Entity
public class OrderItem implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_sku", nullable = false)
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
    @Column(precision = 2, nullable = false)
    private BigDecimal cost;
}
