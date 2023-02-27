package com.demo.web.api;

import com.demo.web.vm.LoginVM;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api")
public interface AuthenticationResource {
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    void authenticate(@RequestBody LoginVM loginVM, HttpServletResponse response);
}
