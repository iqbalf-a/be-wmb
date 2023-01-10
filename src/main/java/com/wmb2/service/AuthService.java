package com.wmb2.service;

import com.wmb2.exception.EntityExistException;
import com.wmb2.exception.NotFoundException;
import com.wmb2.exception.UnauthorizedException;
import com.wmb2.model.Auth;
import com.wmb2.model.User;
import com.wmb2.model.request.LoginRequest;
import com.wmb2.model.request.RegistrationRequest;
import com.wmb2.repository.IAuthRepository;
import com.wmb2.utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    IAuthRepository authRepository;
    IUserService IUserService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    JwtUtil jwtUtil;

    public AuthService(IAuthRepository authRepository, com.wmb2.service.IUserService IUserService, ModelMapper modelMapper, JwtUtil jwtUtil) {
        this.authRepository = authRepository;
        this.IUserService = IUserService;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    @Override
    public String register(RegistrationRequest registrationRequest) {
        try {
            Auth auth = modelMapper.map(registrationRequest, Auth.class);
            Auth authResult = authRepository.save(auth);

            User user = modelMapper.map(registrationRequest, User.class);
            user.setAuth(authResult);
            IUserService.updateById(user);
            String token = jwtUtil.generateToken(user.getAuth().getEmail());
            return token;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Transactional
    @Override
    public String login(LoginRequest loginRequest) {
        try {
            Optional<Auth> auth = authRepository.findById(loginRequest.getEmail());
            if (auth.isEmpty()) throw new NotFoundException();
            if (!auth.get().getPassword().equals(loginRequest.getPassword())) {
                throw new UnauthorizedException("Password not matched");
            }

            String token = jwtUtil.generateToken(loginRequest.getEmail());
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
