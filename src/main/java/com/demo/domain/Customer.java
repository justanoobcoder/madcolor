package com.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Table(name = "customers")
@Entity
public class Customer extends AbstractAuditEntity implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false, unique = true)
    private String telephone;
    @Column(length = 50, nullable = false)
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;
    private Integer point = 0;
    @ManyToOne
    @JoinColumn(name = "rank_id")
    private Rank rank;
}
