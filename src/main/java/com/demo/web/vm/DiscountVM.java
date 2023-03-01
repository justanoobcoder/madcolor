package com.demo.web.vm;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record DiscountVM(
        @Size(max = 50, message = "Discount name too long")
        @NotBlank(message = "Discount name is required")
        @Schema(example = "Lễ 8-3-2023")
        String name,
        @Min(value = 0, message = "Discount percent must not be negative")
        @Max(value = 100, message = "Discount percent must not exceed 100")
        @Schema(example = "20")
        Integer percent,
        @Size(max = 200, message = "Discount description too long")
        @Schema(example = "Giảm giá quốc tế phụ nữ 8-3")
        String description,
        @NotBlank(message = "Timezone is required")
        @Schema(example = "Asia/Ho_Chi_Minh")
        String timezone,
        @NotBlank
        @Schema(example = "2023-03-01 13:20:00")
        String startAt,
        @NotBlank
        @Schema(example = "2023-03-01 14:20:00")
        String endAt
) {
}