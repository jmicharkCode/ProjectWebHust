package com.ltweb.webjob.repositories;

import com.ltweb.webjob.entities.Candidate;
import com.ltweb.webjob.entities.FileDB;
import com.ltweb.webjob.entities.Job;
import com.ltweb.webjob.entities.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication,String> {

    Optional<JobApplication> findById (int id);

    List<JobApplication> findAllByCandidate(Candidate candidate);

    List<JobApplication> findAllByJob(Job job);

    List<JobApplication> findAllByJobAndCandidate(Job job, Candidate candidate);
}
