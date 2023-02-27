package com.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Table(name = "employees")
@Entity
public class Employee implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, unique = true, nullable = false)
    private String username;
    @JsonIgnore
    @Column(name = "hashed_password", length = 100, nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    private String fullName;
    @Column(length = 50)
    private String email;
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;
    @Column(length = 20, nullable = false, unique = true)
    private String telephone;
    @Column(nullable = false)
    private Boolean activated;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
