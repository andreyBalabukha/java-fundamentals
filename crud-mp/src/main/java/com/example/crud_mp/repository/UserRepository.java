package com.example.crud_mp.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crud_mp.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    @Query("SELECT * FROM users where role = 'user'")
    List<User> findAllUsers();

    Optional<User> findByEmail(String email);
}
