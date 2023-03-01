package com.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@ToString
@Table(name = "import_records")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ImportRecord implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private Instant importedAt;
    @ManyToOne
    @JoinColumn(name = "imported_by")
    @CreatedBy
    private Employee importedBy;
    @ManyToOne
    @JoinColumn(name = "product_sku")
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private BigDecimal importPrice;
}
