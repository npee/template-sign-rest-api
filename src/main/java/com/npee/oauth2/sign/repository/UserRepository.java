package com.npee.oauth2.sign.repository;

import com.npee.oauth2.sign.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNo(Long userNo);
    Optional<User> findByUid(String uid);
}
