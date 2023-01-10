package com.wmb2.service;

import com.wmb2.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(String id);
    void deleteById(String id);
    void updateById(User user);
    User create(User user);
}
