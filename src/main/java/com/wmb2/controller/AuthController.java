package com.wmb2.controller;

import com.wmb2.model.request.LoginRequest;
import com.wmb2.model.request.RegistrationRequest;
import com.wmb2.model.response.SuccessResponse;
import com.wmb2.service.IAuthService;
import com.wmb2.utils.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlMapping.AUTH)
public class AuthController {
    private IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping(UrlMapping.REGISTER)
    public ResponseEntity register(@RequestBody RegistrationRequest registrationRequest) {
        String token = authService.register(registrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success registration", token));
    }

    @PostMapping(UrlMapping.LOGIN)
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success login", token));
    }
}
