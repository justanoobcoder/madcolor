package com.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "1")
    private Long id;
    @Schema(example = "0911124536")
    private String telephone;
    @Schema(example = "Tran Huu Chien")
    private String fullName;
    @Schema(example = "male")
    @JsonProperty("gender")
    private String genderName;
    private Integer point = 0;
    @Schema(example = "Đồng")
    @JsonProperty("rank")
    private String rankName;
    @Schema(example = "0")
    @JsonProperty("discountPercent")
    private Integer discountPercent;
}