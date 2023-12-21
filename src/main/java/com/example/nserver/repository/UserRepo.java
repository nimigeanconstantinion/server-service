package com.example.nserver.repository;

import com.example.nserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query(value = "select u from User u where u.email=?1")
    Optional<User> findUserByEmail(String email);
}
