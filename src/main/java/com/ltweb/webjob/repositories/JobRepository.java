package com.ltweb.webjob.repositories;

import com.google.api.client.util.DateTime;
import com.ltweb.webjob.dtos.JobDto;
import com.ltweb.webjob.entities.Company;
import com.ltweb.webjob.entities.Degree;
import com.ltweb.webjob.entities.FileDB;
import com.ltweb.webjob.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job,Integer> {
/*
    Optional<Job> findById(int id);
*/

    List<Job> findAllByCompany(Company company);

    @Query("SELECT j FROM Job j WHERE UPPER (j.company.user.location) LIKE UPPER(CONCAT('%',?1, '%')) and UPPER(j.career) LIKE UPPER(CONCAT('%',?2, '%')) and j.degree = ?3 and j.salary >= ?4 ")
    List<Job> findAllBySearchTerm(String location, String career, Degree degree, int salary);

    @Query("SELECT j FROM Job j WHERE UPPER(j.company.user.location) LIKE UPPER(CONCAT('%',?1, '%')) and UPPER(j.career) LIKE UPPER(CONCAT('%',?2, '%')) and j.salary >= ?3 ")
    List<Job> findAllBySearchTermNoDegree(String location, String career, int salary);
}
