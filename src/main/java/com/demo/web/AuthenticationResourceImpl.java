package com.demo.web;

import com.demo.security.jwt.JwtTokenProvider;
import com.demo.web.api.AuthenticationResource;
import com.demo.web.vm.LoginVM;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class AuthenticationResourceImpl implements AuthenticationResource {
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @Override
    public void authenticate(LoginVM loginVM, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginVM.username(),
                loginVM.password()
        );
        Authentication authentication = authenticationManagerBuilder.getOrBuild().authenticate(authenticationToken);
        String jwt = tokenProvider.generateToken(authentication);
        response.setHeader("access_token", jwt);
    }
}
