package com.demo.web.vm;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginVM(
        @Schema(example = "hiepnh")
        String username,
        @Schema(example = "12345")
        String password) {
}
