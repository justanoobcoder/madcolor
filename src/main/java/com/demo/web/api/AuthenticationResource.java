package com.demo.web.api;

import com.demo.web.vm.LoginVM;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api")
public interface AuthenticationResource {
    @Operation(summary = "Login as an employee",
            description = "Login as an employee using username, password and return a JWT token.",
            tags = "authentication"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Authenticated successfully"),
            @ApiResponse(responseCode = "401", description = "Incorrect username or password")
    }
    )
    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    void authenticate(@RequestBody LoginVM loginVM, HttpServletResponse response);
}
