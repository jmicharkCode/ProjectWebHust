package com.ltweb.webjob.repositories;

import com.ltweb.webjob.entities.FileDB;
import com.ltweb.webjob.entities.Interview;
import com.ltweb.webjob.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterviewRepository extends JpaRepository<Interview,String> {
    Optional<Interview> findById (int id);

    List<Interview> findAllByJob(Job job);
}
