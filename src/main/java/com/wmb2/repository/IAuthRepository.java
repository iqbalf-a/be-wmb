package com.wmb2.repository;

import com.wmb2.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthRepository extends JpaRepository<Auth, String> {
}
