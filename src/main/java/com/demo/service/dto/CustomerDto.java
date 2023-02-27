package com.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * A DTO for the {@link com.demo.domain.Customer} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerDto implements Serializable {
    private Long id;
    private String telephone;
    private String fullName;
    @JsonProperty("gender")
    private String genderName;
    private Integer point = 0;
    @JsonProperty("rank")
    private String rankName;
    @JsonProperty("discount")
    private Float rankDiscount;
}