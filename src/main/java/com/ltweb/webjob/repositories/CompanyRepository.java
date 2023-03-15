package com.ltweb.webjob.repositories;

import com.ltweb.webjob.entities.Company;
import com.ltweb.webjob.entities.FileDB;
import com.ltweb.webjob.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,String> {
    Optional<Company> findByUser(User user);

    Optional<Company> findById(int id);

}
