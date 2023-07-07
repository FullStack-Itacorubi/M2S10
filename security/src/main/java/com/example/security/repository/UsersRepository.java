package com.example.security.repository;

import com.example.security.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    UsersEntity findByUsername(String username);

    Optional<UsersEntity> findByUsernameAndPassword(String username, String password);

}
