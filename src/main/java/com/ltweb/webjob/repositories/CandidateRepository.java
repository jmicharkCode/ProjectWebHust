package com.ltweb.webjob.repositories;

import com.ltweb.webjob.entities.Candidate;
import com.ltweb.webjob.entities.Degree;
import com.ltweb.webjob.entities.FileDB;
import com.ltweb.webjob.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,String> {

    Optional<Candidate> findById(int id);

    Optional<Candidate> findByUser(User user);

    @Query("SELECT c FROM Candidate c WHERE lower(c.career) LIKE lower(CONCAT('%',?1,'%')) and c.degree = ?2 ")
    List<Candidate> findAllBySearchTerm(String career, Degree degree);

    @Query("SELECT c FROM Candidate c WHERE lower(c.career) LIKE lower(CONCAT('%',?1,'%')) ")
    List<Candidate> findAllBySearchTermNoDegree(String career);
}
