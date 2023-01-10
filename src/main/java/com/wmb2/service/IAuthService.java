package com.wmb2.service;

import com.wmb2.model.request.LoginRequest;
import com.wmb2.model.request.RegistrationRequest;
import org.springframework.stereotype.Service;

public interface IAuthService {
    String register(RegistrationRequest registrationRequest);
    String login(LoginRequest loginRequest);
}
