package com.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@ToString
@Table(name = "products")
@Entity
public class Product extends AbstractAuditEntity implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @Column(length = 20)
    private String sku;
    @Column(length = 100, nullable = false, unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @Column(nullable = false)
    private Double price;
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
    @Column(length = 20, nullable = false)
    private String status = "INACTIVE";
    @JoinColumn(nullable = false)
    private Integer quantity;
}
