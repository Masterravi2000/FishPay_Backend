package com.fishpay.repository;

import com.fishpay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long>{
}