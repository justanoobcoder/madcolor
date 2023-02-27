package com.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Table(name = "ranks")
@Entity
public class Rank implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private Integer point;
    @Column(precision = 3, scale = 2, nullable = false)
    private Float discount;
}
