package dev.lanfr0.jwtauth.controller;

import dev.lanfr0.jwtauth.model.dto.ResponseMessage;
import dev.lanfr0.jwtauth.model.rest.AuthenticationRequest;
import dev.lanfr0.jwtauth.model.rest.RegisterRequest;
import dev.lanfr0.jwtauth.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationApi {

    private final AuthenticationService authenticationService;

    public AuthenticationApi(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseMessage<String> register(@RequestBody RegisterRequest request) {
        return ResponseMessage.success(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseMessage<String> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseMessage.success(authenticationService.authenticate(request));
    }
}
