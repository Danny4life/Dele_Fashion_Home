package com.example.dele_fashion_home.repository;

import com.example.dele_fashion_home.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail (String email);

    Optional<UserEntity> findByEmail(String email);
}
