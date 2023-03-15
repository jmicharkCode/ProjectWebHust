package com.ltweb.webjob.repositories;

import com.ltweb.webjob.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(int id);


    List<User> findAll();

    Optional<User> findByEmail(String email);
}
